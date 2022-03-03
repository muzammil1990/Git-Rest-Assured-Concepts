package Utilities;

import io.restassured.path.json.JsonPath;

// This class is used for parsing, Accepts response as arguments returns jsonpath class object
// which contains the entire response

public class ReusableMethods {
    
    public JsonPath rawToJson(String response){  //non static method
        
        JsonPath js= new JsonPath(response);
        return js;
    }

}
