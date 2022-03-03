/**
 * 
 */
package restassuredbasicsAgain.beginner;

import org.testng.annotations.Test;

import Utilities.Payload;
import Utilities.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

/**   Goal:
 * // step1: From picture of section 7= AdvancePayloadCreationStrategies= "Dynamically build json payload with external data inputs"
 */
public class DynamicJson {

    // section 7, topic 31 
    //goal, from response of addbook api , need to parse the ID and print it.
    @Test
    public void addBook(){
       RestAssured.baseURI="http://216.10.245.166"; 
       String response= given().log().all().header("Content-Type","application/json").body(Payload.addBook()).
       when().post("Library/Addbook.php").
       then().log().all().assertThat().statusCode(200).extract().response().asString();
       
       ReusableMethods rm= new ReusableMethods();
       JsonPath js= rm.rawToJson(response); // calling non static method of other class, create object and it return the class itself or class object
       String id = js.get("ID");  //returns string id
       System.out.println(id);
       
       
   //delete book API
//test case so that everytime after adding the book in lib it will delete the book and addbook api will be fresh to create/add again
       
       
    }
}
