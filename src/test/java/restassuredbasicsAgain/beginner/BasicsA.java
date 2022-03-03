/**
 * 
 */
package restassuredbasicsAgain.beginner;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


/**
 * revising the Rest Assured concepts again. 
 * //Section 4 of rahul shetty course
 */
public class BasicsA {

    /**
     * This pg is validate if Add place api is working fine and then validate the status code
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        RestAssured.baseURI="https://rahulshettyacademy.com";
        given().log().all().queryParam("key ", "qaclick123").header("Content-Type","application/json")
        .body("{\r\n" + 
                "  \"location\": {\r\n" + 
                "    \"lat\": -38.383494,\r\n" + 
                "    \"lng\": 33.427362\r\n" + 
                "  },\r\n" + 
                "  \"accuracy\": 50,\r\n" + 
                "  \"name\": \"Frontline house\",\r\n" + 
                "  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
                "  \"address\": \"29, side layout, cohen 09\",\r\n" + 
                "  \"types\": [\r\n" + 
                "    \"shoe park\",\r\n" + 
                "    \"shop\"\r\n" + 
                "  ],\r\n" + 
                "  \"website\": \"http://google.com\",\r\n" + 
                "  \"language\": \"French-IN\"\r\n" + 
                "}")
        .when().post("/maps/api/place/add/json")
        .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")); 
        
        //add place , then update place with new address, then get place to validate if new address updated successfully
    }

}
