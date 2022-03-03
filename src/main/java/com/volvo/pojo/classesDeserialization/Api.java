/**
 *  > Section 11: Deserialization 
 * 64. Strategies in Parsing Complex nested
 * 65. Creating POJO classes for the real time nested array
 *  > Refer section11 for "json" saved in personal document
 *  > To generate setter and getters shortcut keys are alt + shift + s
 */
package com.volvo.pojo.classesDeserialization;

/**
 * Nested class; Inject this class in Courses class
 */
public class Api {
    
    private String courseTitle;
    private String price;
    
    
    public void setcourseTitle(String courseTitle){
       this.courseTitle= courseTitle;
    }   
    
    public String getcourseTitle(){
        return courseTitle;
    }
    
    
    public void setPrice(String price){
        this.price = price;
    }
    
    public String getPrice(){
        return price;
    }
}
