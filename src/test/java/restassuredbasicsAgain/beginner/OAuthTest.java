package restassuredbasicsAgain.beginner;

import static io.restassured.RestAssured.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

/**
 * section 10
 * this class is to automate the OAuth2.0; grant type = authorization;
 * 
 * 
 * Bottom up approach of manual way of test we should use while automating rest api for OAuth2.0 authorization grant type 
 * 
       Manual Way of test or Rest API:-
1. User sign into google by hitting google authorization server and get code.
2. Application will use the code(Authorization code) to hit the google resource server to get the access token
 */
public class OAuthTest {

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub

        //3rd step of automation= 1st step of manual
       
      
       // GOOGLE HAS STOPPED SUPPORTING THE GMAIL SIGIN USING AUTOMATION SOFTWARE.
      
       System.setProperty("webdriver.chrome.driver", "./resources/chromedriver.exe"); 
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
      

        
 //IMP NOTE; Every time this below url needs to be constructed manually or else authenitcation will fail
//how to build url: follow-  OAuth 2.0 Contract Details.docx file of section 9:   
//      String url="https://rahulshettyacademy.com/getCourse.php?state=abcd&code=4%2F0AX4XfWjmtX9wO4zmdHLaJFezERtZu5tsV4oBlHTmdkHZB_Be7zoWGKnqY_M7l1r0d6Z6PA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent#";
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
        
        
//      1st step of automation= 3rd step of manual                     //when we are expecting response as json
        String response = given().queryParam("access_token",accessToken).expect().defaultParser(Parser.JSON)
                .when().get("https://rahulshettyacademy.com/getCourse.php").asString();
        System.out.println(response);
        
        
        
    }

}
