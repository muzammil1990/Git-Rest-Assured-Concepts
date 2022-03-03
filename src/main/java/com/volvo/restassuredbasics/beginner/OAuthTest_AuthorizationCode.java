/**
 * 
 */
package com.volvo.restassuredbasics.beginner;

import static io.restassured.RestAssured.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.volvo.restassured.utility.ReUsableMethods;

import io.restassured.path.json.JsonPath;

/**
 * this class is to automate the OAuth2.0 authorization grant type= section 10
 */
public class OAuthTest_AuthorizationCode {
    
    

        @Test
        public void buildingOAuthGrantType() throws InterruptedException{
            
            //3rd step in automation= 1st step of manual
           /* System.setProperty("webdriver.chrome.driver", "./resources/chromedriver.exe");
            WebDriver driver= new ChromeDriver();
            driver.get("https://accounts.google.com/o/oauth2/v2/auth/identifier?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&state=abcd&flowName=GeneralOAuthFlow");
            driver.findElement(By.xpath("//input[@type='email']")).sendKeys("muzifire");
            driver.findElement(By.xpath("//input[@type='email']")).sendKeys(Keys.ENTER);
            Thread.sleep(3000);
            driver.findElement(By.xpath("//input[@type='password']")).sendKeys("sonulove143#");
            driver.findElement(By.xpath("//input[@type='password']")).sendKeys(Keys.ENTER);
            Thread.sleep(4000);
            String url = driver.getCurrentUrl();*/
            
    //IMP NOTE; Every time this below url needs to be constructed manually or else authenitcation will fail
          //how to build url: follow-  OAuth 2.0 Contract Details.docx file of section 9:
            String url = "https://rahulshettyacademy.com/getCourse.php?state=abcd&code=4%2F0AY0e-g6s7vSDW52oAxw2yQBnwcAJSXCqw8R79_pLPx9Jx_fAeIX0mrNGD2PH_M_Hdm5Gnw&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
            String partialCode =  url.split("code=")[1];
            String code= partialCode.split("&scope")[0];
            System.out.println("CODE IS- "+ code);
            
           
            //2nd step in automation= 2nd step of manual
            
            //urlEncodingEnabled(false)= bcoz rest assured encodes any special char present in the code. so to avoid encoding need to make it false
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
            String response = given().queryParam("access_token", accessToken)
            .when().log().all().get("https://rahulshettyacademy.com/getCourse.php").asString();
            System.out.println(response);   
            }
}
