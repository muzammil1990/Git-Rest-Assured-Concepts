/**
 * 
 */
package Utilities;

/**
 * This Class is to load or provide json
 * // payload means how we put/send a json file 
 */
public class Payload {

    public static String addPlace(){
        
        String jsonBody = "{\r\n" + 
                "  \"location\": {\r\n" + 
                "    \"lat\": -38.383494,\r\n" + 
                "    \"lng\": 33.427362\r\n" + 
                "  },\r\n" + 
                "  \"accuracy\": 50,\r\n" + 
                "  \"name\": \"Frontline house\",\r\n" + 
                "  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
                "  \"address\": \"29, side layout, cohen 09\",\r\n" + 
                "  \"types\": [\r\n" + 
                "    \"shoe park\",\r\n" + 
                "    \"shop\"\r\n" + 
                "  ],\r\n" + 
                "  \"website\": \"http://google.com\",\r\n" + 
                "  \"language\": \"French-IN\"\r\n" + 
                "}";
        
        return jsonBody;
    }
    
  //course price api= Assume this is an API since proper API is not yet developed by developer
    public static String coursePrice(){
        return "{\r\n" + 
                "  \"dashboard\": {\r\n" + 
                "    \"purchaseAmount\": 910,\r\n" + 
                "    \"website\": \"rahulshettyacademy.com\"\r\n" + 
                "  },\r\n" + 
                "  \"courses\": [\r\n" + 
                "    {\r\n" + 
                "      \"title\": \"Selenium python\",\r\n" + 
                "      \"price\": 50,\r\n" + 
                "      \"copies\": 6\r\n" + 
                "    },\r\n" + 
                "    {\r\n" + 
                "      \"title\": \"RPA\",\r\n" + 
                "      \"price\": 45,\r\n" + 
                "      \"copies\": 10\r\n" + 
                "    },\r\n" + 
                "    {\r\n" + 
                "      \"title\": \"Cypress\",\r\n" + 
                "      \"price\": 40,\r\n" + 
                "      \"copies\": 4\r\n" + 
                "    },\r\n" + 
                "    {\r\n" + 
                "      \"title\": \"Appium\",\r\n" + 
                "      \"price\": 450,\r\n" + 
                "      \"copies\": 9\r\n" + 
                "    }\r\n" + 
                "  ]\r\n" + 
                "}";
                
    }
    //assume API Exists
    public static String addBook(){
        
        String addBookPayload = "{\r\n" + 
                "\r\n" + 
                "\"name\":\"Learn Appium Automation with Java\",\r\n" + 
                "\"isbn\":\"bcade\",\r\n" + 
                "\"aisle\":\"2327\",\r\n" + 
                "\"author\":\"John foe\"\r\n" + 
                "}\r\n" + 
                "";
        
        return addBookPayload;
    }
    
    //assume API Exists
//this method is used to create the json dynamically 
//i.e. everytime a json will be created with new value bcoz of parameters isbn and aisle
    public static String addNewBookAlways(String isbn, String aisle){
        
        String addBookPayload= "{\r\n" + 
                "\"name\":\"Learn Appium Automation with Java\",\r\n" + 
                "\"isbn\":\""+isbn+"\",\r\n" + 
                "\"aisle\":\""+aisle+"\",\r\n" + 
                "\"author\":\"John foe\"\r\n" + 
                "}";
        return addBookPayload;
    }

}
