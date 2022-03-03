
package com.volvo.restassuredbasics.beginner;

import org.testng.annotations.Test;

import com.volvo.pojo.classesSerialization.AddPlace;
import com.volvo.pojo.classesSerialization.Location;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.This class is to serialization- converting java object into request body(payload-json)
 * 2.SERIALIZATION= uses setters method
 * 3.SERIALIZATION=Used when we need to send Json to API( we send json body using java object)
 */
public class EndToEnd_Pojo_SerializationTest {

    @Test
    public void testSerializationConcept() {
        
        AddPlace adPlace= new AddPlace();
        adPlace.setAccuracy(50);
        adPlace.setName("Frontline house");
        adPlace.setPhoneNo("(+91) 983 893 3937");
        adPlace.setAddress("29, side layout, cohen 09");
        
        List<String> myList= new ArrayList<String>(); //List is an interface. so cannot create object for interface
        myList.add("shoe park");
        myList.add("shop");
        
        adPlace.setTypes(myList);
        
        Location location= new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        
        adPlace.setLocation(location);
        adPlace.setWebsite("http://google.com");
        adPlace.setLanguage("French-IN");
        
//body is missing. n
        String response = RestAssured.baseURI = "https://rahulshettyacademy.com";
        given().log().all().queryParam("key ", "qaclick123").body(adPlace).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().response()
               .asString();
        
        System.out.println(response);
    }
}
