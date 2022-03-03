package com.volvo.restassured.utility;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;


//Handling dynamic json payloads with parameterization
public class DynamicJson {

    // 32.
    // Rest assured test for Library API, Add Book
    // 33.
    // Sending parameters to payload from test (Here passed only 1 parameter)
//                                            Payload.addingBook("novel", "robin123")
    @Test(dataProvider="booksData")
    public void addBook(String isbn, String aisle ) {

        RestAssured.baseURI = "http://216.10.245.166";

        String addResponse = given().log().all().header("Content-Type", "application/json")
                .body(Payload.addingBook(isbn, aisle))
                .when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath js = ReUsableMethods.rawToJson(addResponse);
        System.out.println("ID is- " + js.getString("ID")); // parsiong the json response using object = js

    }
 
  //34. Understanding TestNG Data provider for parameterization  
  //this concept will be used when multile datasets needs to be used like 20 books
   
    //35. Parameterization of API tests with multiple datasets eg. here 3 books set
    
    @DataProvider(name="booksData")
    public Object[][] getData(){
       //array= collection of elements
      // multidimensional array= collection of array
        
       Object[][] obj= new Object[][]{{"RS","Megaliving_1"},{"APJ_KALAM", "Turning Points_2"},{"CB","Revolution2020"}};
       return obj;
    }
    
}
