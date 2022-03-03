
package com.volvo.restassuredbasics.beginner;

import org.testng.annotations.Test;

import com.volvo.pojo.classesSerialization.AddPlace;
import com.volvo.pojo.classesSerialization.Location;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. This class is used to develop framework. RequestSpecBuilder class helps developing it
 */
public class SpecBuilderTest {

    @Test
    public void testSerializationConceptUsingSpecBuilder() {
        
        AddPlace adPlace= new AddPlace();
        adPlace.setAccuracy(50);
        adPlace.setName("Frontline house");
        adPlace.setPhoneNo("(+91) 983 893 3937");
        adPlace.setAddress("29, side layout, cohen 09");
        
        List<String> myList = new ArrayList<String>(); //List is an interface. so cannot create object for interface
        myList.add("shoe park");
        myList.add("shop");
        
        adPlace.setTypes(myList);
        
        Location location= new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        
        adPlace.setLocation(location);
        adPlace.setWebsite("http://google.com");
        adPlace.setLanguage("French-IN");
        
                                           //sending our json request(input details) ; generalising code
  RequestSpecification reqSpecBuilder = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
                                   .setContentType(ContentType.JSON).build(); // at the last used is build(); so return type changes as per last method used
                                                     //build(): Build RequestSpecBuilder ,@return The assembled request specification                      
        
        //generalising code for ADD api/ Delete api/ put api etc (common things in all api)
     ResponseSpecification responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
                                                        // at the last used is build(); so return type changes as per last method used     
       
        
//keeping request separately  , generalising request
        RequestSpecification requestSpec =given().spec(reqSpecBuilder)
                .body(adPlace);
          
        // so on this request(requestSpec) we are submiting something to post to get the response 
        // also keeping response separately , generalising response
        Response response =  requestSpec.when().post("/maps/api/place/add/json")
                .then().spec(responseSpec).extract().response();
                                                                                           
        String responseString= response.asString();
        System.out.println(responseString);
    }
}
