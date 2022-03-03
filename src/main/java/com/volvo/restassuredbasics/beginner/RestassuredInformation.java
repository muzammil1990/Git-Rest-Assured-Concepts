package com.volvo.restassuredbasics.beginner;

import java.net.URI;
import java.nio.file.Path;

public class RestassuredInformation {
public static void main(String[] args) {
    /**
     * 1. RestAssured           -  Java class
     * 
     * 2. RequestSpecification  - Java interface
     *      methods provided by RequestSpecification- 
     *      given(),query parameter(), header(), body(), when(), build()
     *      
     * 3. RequestSenderOptions - Java interface
     *      methods provided by RequestSenderOptions-
     *      post()- return type- Response{interface}
     *      
     * 4. Validatable           - Java interface
     *      methods provided by Validatable-
     *      then()- return type- validatableResponse{interfcae}
     *      
     * 5. ValidatableResponseOptions  - Java interface
     *      methods provided by ValidatableResponseOptions-
     *      assertThat()- return type- validatableResponse{interfcae}
     *      statusCode()- return type- validatableResponse{interfcae}
     *      
     * 6. Files- Java class= This class consists exclusively of static methods that operate on files,
     *                       directories, or other types of files.
     * 
     * 7. readAllBytes- method- reads all the bytes from the file
     *                  returns- a byte array containing the bytes which are read from the file
     *                  
     * 8. Paths-  This class consists exclusively of static methods that return a {@link Path}
     *            by converting a path string or {@link URI}
     *            
     * 9. RequestSpecBuilder()- Java Class = You can use the builder to construct a request specification. 
     *                                      The specification can be used as e.g.

            ResponseSpecification responseSpec = new ResponseSpecBuilder().expectStatusCode(200).build();
            RequestSpecification requestSpec = new RequestSpecBuilder().addParam("parameter1", "value1").build();
             
            methods provided RequestSpecBuilder are-
            setBaseURI()= for base uri
            addQueryParam(Key , value)  = query parameters
            setContentType() = for headers  
       
       10. ResponseSpecification= Java Interface 
               methods  are
               build()        
                
     */
    
    
    
    
}
}
