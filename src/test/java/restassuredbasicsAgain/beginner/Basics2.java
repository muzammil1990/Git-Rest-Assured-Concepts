package restassuredbasicsAgain.beginner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import Utilities.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


//Section 4 and 5 of rahul shetty course
public class Basics2 {

    public static void main(String[] args) {
        
        // validate if Add Place API is workimg as expected 
        //Add place>> Update Place with New Address >> Get Place to validate if New address is present in response
        
        
// 1. given - What is attached to given, all input details= parameters, headers, body, cookies
// 2. when - What we are sending to the API - http method, resource
// 3. then - What we receive back, validate the response
        
// 4. assertion on body level of response equalTo() , eclips does not suggest package since equalTo belongs to static package
//      org.hamcrest
        
// 5. For json body to set automatically = Window > Preferences > Type Editor in search box above
//      > Go to Java > Editor >Typing> In String literal > Select checkbox "Esc text when pasting into a string literal"

              
        
       //First set of code; only run first set not second set 
       
        
     /* RestAssured.baseURI="https://rahulshettyacademy.com";
        given().log().all().queryParam("key ", "qaclick123").header("Content-Type","application/json")
        .body(Payload.addPlace())
        .when().post("/maps/api/place/add/json")
        .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")); 
     */
        
   
 // Next goal
    //add place , then update place with new address, then get place to validate if new address updated
    
    //second set of code; Run only second set not first
       RestAssured.baseURI="https://rahulshettyacademy.com";
       String response = given().log().all().queryParam("key ", "qaclick123").header("Content-Type","application/json")
        .body(Payload.addPlace())
        .when().post("/maps/api/place/add/json")
        .then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();
       
       System.out.println(response); //This response var will have entire json response, from there we only need to extract place_id so that we can update the address
 //  Parsing in its most general sense is the extraction of the necessary information from some piece of data, most often textual data. ... Usually the parse() method receives some string as input, "extracts" the necessary information from it and converts it into an object of the calling class.
    
    //For Parsing json response by default we use one class = JsonPath 
       JsonPath js= new JsonPath(response);
       String placeID= js.getString("place_id");
       
       System.out.println(placeID);
       
       //Update Place
       String updatedAddress= "Isha Misty Green, India";
       
       given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
       .body("{\r\n" + 
               "\"place_id\":\""+ placeID +"\",\r\n" + 
               "\"address\":\""+ updatedAddress +"\",\r\n" + 
               "\"key\":\"qaclick123\"\r\n" + 
               "}")
       .when().put("/maps/api/place/update/json")
       
       //assertion in RestAssured
       .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
       
       
       //Get Place: to validate if new address updated successfully or not
       
       String getPlaceResponse=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)
       .when().get("/maps/api/place/get/json")
//       .then().assertThat().log().all().statusCode(200).body("address", equalTo(updatedAddress));
       .then().assertThat().log().all().statusCode(200).extract().response().asString();
       
       JsonPath js1= new JsonPath(getPlaceResponse);
       String actualAddress = js1.getString("address");
 //Cucumber Junit, Testng   
       
       //testng framework assertion
       Assert.assertEquals(actualAddress, "pacific"); // intentionalle changed the expec result so that assertion will fail
//       Assert.assertEquals(actualAddress, updatedAddress);
    }

}
