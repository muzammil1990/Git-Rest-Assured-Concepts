package com.volvo.restassured.utility;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods {

    

    public static JsonPath rawToJson(String response) {
        
        JsonPath js1 = new JsonPath(response); //for parsing the json; 
                                                //add/delete/modify/update = parsing
        return js1;
        
    }
}
