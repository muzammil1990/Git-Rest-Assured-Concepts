package com.volvo.restassuredbasics.beginner;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import com.volvo.restassured.utility.Payload;
import com.volvo.restassured.utility.ReUsableMethods;

/**
 * This pg is validate if Add place api is working fine and then validate the status code
 */

public class Basics {

	public static void main(String[] args) {
	    
        // validate if Add Place API is workimg as expected 
		//Add place>> Update Place with New Address >> Get Place to validate if New address is present in response
		
	    
// 1. given - What is attached to given, all input details= parameters, headers, body, cookies
// 2. when - What we are sending to the API - http method, resource
// 3. then - What we receive back, validate the response
	    
// 4. assertion on body level of response equalTo()	, eclips does not suggest package since equalTo belongs to static package
//	    org.hamcrest
	    
// 5. For json body to set automatically = Window > Preferences > Type Editor in search box above
//	    > Go to Java > Editor >Typing> In String literal > Select checkbox "Esc text when pasting into a string literal"
	    
	    
	    
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.addPlace()).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP")) //equalTo belongs to static package  org.hamcrest //after assertThat() , everything is considered as assertion, even on body level of response
		.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString(); // verify if the response is coming from our own server.
		
		System.out.println(response);
		JsonPath js=new JsonPath(response); //for parsing Json; converts into json 
		String placeId=js.getString("place_id");
		
		System.out.println(placeId);
		
		//Update Place
		String newAddress = "Summer Walk, Africa";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}").
		when().put("maps/api/place/update/json")
		
		//assertion in RestAssure
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		
		//Get Place: to validate if new address updated successfully or not
		
	String getPlaceResponse=	given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id",placeId)
		.when().get("maps/api/place/get/json")
//		.then().assertThat().log().all().statusCode(200).body("address", equalTo(newAddress));		
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
	
	JsonPath js1=ReUsableMethods.rawToJson(getPlaceResponse);
	
	String actualAddress =js1.getString("address");
	System.out.println(actualAddress);
	Assert.assertEquals(actualAddress, "Pacific ocean");
	//Cucumber Junit, Testng
	
	
	
	
	
	
	
	
	

		
		
		
		
		
		
		
		
		
		
		
	}

}
