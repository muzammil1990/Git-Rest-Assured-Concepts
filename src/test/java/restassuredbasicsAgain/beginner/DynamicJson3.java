package restassuredbasicsAgain.beginner;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilities.Payload;
import Utilities.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

//Goal2:
//From picture of section 7= AdvancePayloadCreationStrategies= "Parameterize API with Multiple data sets"

public class DynamicJson3 {

    
    // section 7, topic 33- Understanding TestNg Data provider for parameterization 
//                topic 34. Example on Parameterization of API Tests with multiple data sets  
     //this method or test case have 3 datasets
       @Test(dataProvider="getData")
       public void addMultipleBooksInLibrary(String isbn, String aisle){
           RestAssured.baseURI="http://216.10.245.166"; 
           String response= given().log().all().header("Content-Type","application/json").body(Payload.addNewBookAlways(isbn, aisle)).
           when().post("Library/Addbook.php").
           then().log().all().assertThat().statusCode(200).extract().response().asString();
           
           ReusableMethods rm= new ReusableMethods();
           JsonPath js= rm.rawToJson(response); // calling non static method of other class, create object and it return the class itself or class object
           String id= js.get("ID"); //returns string id
           System.out.println(id);
           
           
           
           //delete book API
      //test case so that everytime after adding the book in lib it will delete the book and addbook api will be fresh to create/add again
           
       }
       
       @DataProvider
       public Object[][] getData(){
           
//           Array = Java array is an object which contains elements of a similar data type.
//           Additionally, The elements of an array are stored in a contiguous memory location.
//           It is a data structure where we store similar elements
           
           //Multidimensional Array = In such case, data is stored in row and column based index (also known as matrix form).
//                                  = Collection of arrays
          return new Object[][]{{"abqc","112"},{"xyqs","324"},{"dfdg","57"}};
       }

}
