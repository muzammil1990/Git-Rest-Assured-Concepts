/**
 * 
 */
package com.volvo.restassuredbasics.beginner;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.volvo.pojo.classesDeserialization.Api;
import com.volvo.pojo.classesDeserialization.GetCourse;
import com.volvo.pojo.classesDeserialization.Mobile;
import com.volvo.pojo.classesDeserialization.WebAutomation;
import com.volvo.restassured.utility.ReUsableMethods;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

/**
 * 1.This class is to show the end to end automation of api using  deserialization concept
 * 2.DESERIALIZATION= USED WHEN WE GET JSON RESPONSE ---after sending request when we get response back
 * 3.DESERIALIZATION= uses getters method
 * 4.DESERIALIZATION= process of converting response body back to Java object(means java class)
 */
public class OAuthTest_EndToEnd_PojoDeserializaiton {

    @Test
    public void buildAccessToken() throws InterruptedException{
//        System.setProperty("webdriver.chrome.driver", "./resources/chromedriver.exe");
//        WebDriver driver= new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("https://accounts.google.com/o/oauth2/v2/auth/identifier?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&state=abcd&flowName=GeneralOAuthFlow");
//        driver.findElement(By.xpath("//input[@type='email']")).sendKeys("muzifire");
//        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(Keys.ENTER);
//        Thread.sleep(3000);
//        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("sonulove143#");
//        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(Keys.ENTER);
//        Thread.sleep(4000);
//        String url = driver.getCurrentUrl();
        
      //  bcoz google restricted using api
         String url ="https://rahulshettyacademy.com/getCourse.php?state=abcd&code=4%2F0AY0e-g4JIaEzK1QeQfTI0E96bke-Y8Jp441ldMYhFYhECKeJmq9F1-GcoBGUO3ONd6A-dQ&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none"; 
         String partialCode =  url.split("code=")[1];
         String code= partialCode.split("&scope")[0];
         System.out.println("CODE IS- "+ code);
        
         //2nd step automation= 2nd step of manual= sending this data to resource server
         String accessTokenResponse =  given().urlEncodingEnabled(false).queryParams("code", code)
                 .queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                 .queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                 .queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
                 .queryParams("grant_type","authorization_code")
                 .when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
               
         //parsiong the response
                 JsonPath js =  ReUsableMethods.rawToJson(accessTokenResponse);
                 String accessToken = js.getString("access_token");
        
        //1st step in automation= 3rd step of manual   
                                                                                    //when we are expecting response as json
        GetCourse getCourse = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON) 
        .when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class); //response is being saved as java class(java object)
                                                                                        //deserialization concept

        //also expect().defaultParser(Parser.JSON) can be avoided here only if during manual test in header section  the
        // content-type= application/json
        
      //parsing json using java object(deserialization)
      System.out.println(getCourse.getLinkedIn());
      System.out.println(getCourse.getInstructor());

 // ====================================================================================     
      //section 64. Solving Complex Queries from Json 
      // to find out the price of soapUI Webservices testing
      
      System.out.println(getCourse.getCourses().getApi().get(1).getPrice());
      
      //or
      
      List<Api> apiCourses=getCourse.getCourses().getApi();
      for(int i=0; i< apiCourses.size();i++){
        if(apiCourses.get(i).getcourseTitle().equalsIgnoreCase("SoapUI Webservices testing")){
            System.out.println("api course price: " + apiCourses.get(i).getPrice());
        }
        
      }  
      
      // get the mobile course price
      
      List<Mobile> mobileCourses =  getCourse.getCourses().getMobile();
      for(int i=0; i< mobileCourses.size(); i++){
          if(mobileCourses.get(i).getcourseTitle().equalsIgnoreCase("appium-mobile automation using java")){
              System.out.println("Mobile course price: " + mobileCourses.get(i).getPrice());
          }
      }
    
      // Get all course titles of webautomation
      //68. Solving Complex Queries from Json
      
          List<WebAutomation> wa = getCourse.getCourses().getWebAutomation();
           for(int i=0; i< wa.size();i++){
               System.out.println(wa.get(i).getcourseTitle());
           }
           
               //or
      String courseTitles[]={"Selenium Webdriver Java", "Cypress", "Protractor"};
      
        ArrayList<String> al= new ArrayList<String>();
        
        List<WebAutomation> wA =  getCourse.getCourses().getWebAutomation();
        for(int i=0; i<wA.size();i++){
           al.add(wA.get(i).getcourseTitle()) ;
        }
        
        //converting array into arraylist so that comparision becomes easy
       List<String> expectedList= Arrays.asList(courseTitles); //converted array to arraylist
       
       Assert.assertEquals(al, expectedList); //assertEquals(Collection<?>) comparing one list with Arraylist, both implements collection itself
//        or
       
       Assert.assertTrue(al.equals(expectedList));
       
    }
   
   }

