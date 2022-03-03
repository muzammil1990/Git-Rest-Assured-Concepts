/**
 * 
 */
package com.volvo.restassuredbasics.beginner;

/**
 * Interview Ques: Different security meachanism you can have- Talk about grant types
 */
public class OAuthTest_ClientCredentials {
    
    //Grant type= Client Credentials
    
/*  1. When no 3rd party(user) is involved then client credential type is used.
    
    2. Why? Obvioulsy we are not asking anybody's data, we are asking our own data. So no
       need of verifying other user(ie to get code from autorization server step need not be used during automation)
    
    3. Need to interact with only resource server*/
    
    
    
    // there while implementing API test for client credentials type only 2 steps of automation will be required
       // 2 steps are required for all type of - GRANT TYPES. only Authorization_Type(access token) requires 3 steps 
    
    // co relate and automate the api in company by comparing OAuthTest_AuthorizationCode class in this package
}
