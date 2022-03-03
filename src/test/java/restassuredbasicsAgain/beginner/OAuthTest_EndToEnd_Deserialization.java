package restassuredbasicsAgain.beginner;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojoDeserializationAgain.Api;
import pojoDeserializationAgain.GetCourse;
import pojoDeserializationAgain.WebAutomation;

/*
* 1.This class is to show the end to end automation of api using  deserialization concept
* 2.DESERIALIZATION= USED WHEN WE GET JSON RESPONSE ---after sending request when we get response back
* 3.DESERIALIZATION= uses getters method
* 4.DESERIALIZATION= process of converting response body back to Java object(means java class)
*/

public class OAuthTest_EndToEnd_Deserialization {

    public static void main(String[] args) {

        
        
        // GOOGLE HAS STOPPED SUPPORTING THE GMAIL SIGIN USING AUTOMATION SOFTWARE.
       
     /*  System.setProperty("webdriver.chrome.driver", "./resources/chromedriver.exe"); 
       WebDriver driver= new ChromeDriver();
       driver.manage().window().maximize();
       driver.get("https://accounts.google.com/o/oauth2/v2/auth/identifier?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&state=abcd&flowName=GeneralOAuthFlow");
       driver.findElement(By.xpath("//input[@type='email']")).sendKeys("muzifire");
       driver.findElement(By.xpath("//input[@type='email']")).sendKeys(Keys.ENTER);
       Thread.sleep(3000);
       driver.findElement(By.xpath("//input[@type='password']")).sendKeys("sonulove143#");
       driver.findElement(By.xpath("//input[@type='password']")).sendKeys(Keys.ENTER);
       Thread.sleep(4000);
       String url = driver.getCurrentUrl();
       */

         
  //IMP NOTE; Every time this below url needs to be constructed manually or else authenitcation will fail
 //how to build url: follow-  OAuth 2.0 Contract Details.docx file of section 9:   
       String url="https://rahulshettyacademy.com/getCourse.php?state=abcd&code=4%2F0AX4XfWhqMQLo-dkvRjsleRpnM3eKY8ayHn5PHO4fRbuyEFHIsuAmgmDP9mZsK2k3ShhJtw&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent#";
       String partialCode = url.split("code=")[1];
       String code = partialCode.split("&scope")[0];
       
       
         //2nd step of automation= 2nd step on manaul
     //urlEncodingEnabled(false)= bcoz rest assured encodes any special char present in the code. so to avoid encoding need to make it false
     String accessTokenResponse = given().urlEncodingEnabled(false)
             .queryParams("code",code)
         .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
         .queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
         .queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
         .queryParams("grant_type", "authorization code").
         when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
     JsonPath js= new JsonPath(accessTokenResponse);
     String accessToken = js.getString("access_token");
         
         
//       1st step of automation= 3rd step of manual                         //when we are expecting response as json
         GetCourse getCourse = given().queryParam("access_token",accessToken).expect().defaultParser(Parser.JSON)
                 .when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
     
//also expect().defaultParser(Parser.JSON) can be avoided here only if during manual test in header section  the
// content-type= application/json 
         
         
         //parsing the GetCourse class response
         System.out.println(getCourse.getLinkedIn()); //getters, hence deserialization
         System.out.println(getCourse.getInstructor());

         
// ====================================================================================     
         //section 67. Solving Complex Queries from Json 
      // Ques: to find out the price of soapUI Webservices testing
         
         System.out.println(getCourse.getCourses().getApi().get(1).getCourseTitle());
         
         //or
         
       List<Api> api=  getCourse.getCourses().getApi();
       for(int i=0; i<api.size(); i++){
           if(api.get(i).getCourseTitle().equalsIgnoreCase("SoapUI WebServices testing")){
               System.out.println(api.get(i).getPrice());
           }
       }
         
     // Ques: to find all the courses title of WebAutomation
       List<WebAutomation> wa = getCourse.getCourses().getWebAutomation();
       for(int i=0; i< wa.size(); i++ ){
           System.out.println(wa.get(i).getCourseTitle());
       }

       
//In real time, we will not print the course titles in console. So we will store the these titles in an arraylist.
// In real time since json will be dynamic we will not know the size or json size can increase so to support we use ArrayList class or LinkedList class

//then we must compare our actual data from runtime to expected data (expected data will be picked from excel)
// here expected data I will be hardcoding now since excel is not there
       
       String expectedData[]={"Selenium Webdriver Java", "Cypress", "Protractor"};   // here expected data   
       
       ArrayList<String> actualCoursesTitles= new ArrayList<String>();
       List<WebAutomation> wA = getCourse.getCourses().getWebAutomation();
       for(int i=0; i< wA.size(); i++ ){
           actualCoursesTitles.add(wa.get(i).getCourseTitle());// actual data is captured from runtime
       }
       
// now need to compare expected data with actual data; but expected data is an array so cannot compare an array and Arraylist
// so we will convert the array to arraylist using asList()
       
      List<String> expectedCoursesTitles= Arrays.asList(expectedData);
      
     Assert.assertEquals(actualCoursesTitles, expectedCoursesTitles);
       
//      or
     Assert.assertTrue(actualCoursesTitles.equals(expectedCoursesTitles)); 
       }         

}
