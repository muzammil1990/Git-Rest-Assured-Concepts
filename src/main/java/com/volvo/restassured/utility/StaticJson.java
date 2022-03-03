/**
 * 
 */
package com.volvo.restassured.utility;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

/**
 * how to handle static json file which is kept externally.
 * 
 * to read json file externally from the file:- convert the content of the file into string format
 *  How:- 1. Convert content of file into byte- java class Files and its method readAllBytes
 *        2. Convert content from byte to string-  using string object
 *        
 */
public class StaticJson {

    @Test
    public void readStaticJsonFile() throws IOException {

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                                 .body(new String(Files.readAllBytes(Paths.get("./resources/Static_AddPlace.json")))).when().post("maps/api/place/add/json").then().log()
                                 .all().assertThat().statusCode(200).extract().response().asString();

        JsonPath js = ReUsableMethods.rawToJson(response);
        String placeID = js.getString("place_id");
        System.out.println("place id is:-  " + placeID);
    }
}
