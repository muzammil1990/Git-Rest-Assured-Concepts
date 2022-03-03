package restassuredbasicsAgain.beginner;

import org.eclipse.jetty.util.ajax.JSON;

import Utilities.Payload;
import io.restassured.path.json.JsonPath;

/*  Goal= Section 6 of rahul shetty course
1. Print No of courses returned by API

2.Print Purchase Amount

3. Print Title of the first course

4. Print All course titles and their respective Prices

5. Print no of copies sold by RPA Course

6. Verify if Sum of all Course prices matches with Purchase Amount

*/
public class ComplexJsonParseA {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

//        Payload p= new Payload();
        
        JsonPath js= new JsonPath(Payload.coursePrice());
        
//       1. Print No of courses returned by API
       int courseCount= js.getInt("courses.size()"); // size() method to be applied only on array to get count
       System.out.println(courseCount);
       
//       2.Print Purchase Amount
       int purchaseAmount= js.getInt("dashboard.purchaseAmount");
       System.out.println(purchaseAmount);
       
//       3. Print Title of the first course
       
       String firstCourseTitle1= js.get("courses[0].title");// @return= The object matching the Object path. This may be any primitive type, a List or a Map.  A {@link java.lang.ClassCastException} will be thrown if the object cannot be casted to the expected type.
       String firstCourseTitle= js.getString("courses[0].title"); //returns= Get the result of an Object path expression as a string.
       System.out.println(firstCourseTitle);
       
//       4. Print All course titles and their respective Prices(25. Iterating over every element of Json Array and accessing elements in it)
       for(int i=0; i<courseCount; i++){
         String coursesTitle = js.get("courses[" + i + "].title"); //First way of printing all titles
          System.out.println(coursesTitle);
          
        // get() can also be used for int value , String value 
           
//          System.out.println(js.get("courses[" + i + "].title").toString());//second way of printing all course titles // converting object type to string
          
          int coursesPrice = js.getInt("courses[" + i + "].price"); // here I can also use js.get().
          System.out.println(coursesPrice);
       }
       
       
//       5. Print no of copies sold by RPA Course(26. Retrieving Json Nodes on Condition logic using JsonPath)
        
      /* course RPA can change its index position tomorrow since its dynamic JSON. So we must write proper pg so that it can identify
       RPA course and print its correspongding copies*/
       
       for(int i=0; i<courseCount; i++){
           
           String coursesTitle= js.get("courses[" + i + "].title"); //here I can also use js.getString()
           if(coursesTitle.equalsIgnoreCase("rpa"))
           {
             int rpaCopies= js.get("courses[" + i + "].copies"); //here I can use also use js.getInt()
             System.out.println("rpa copies sold: " + rpaCopies); 
             break;
           }
       }
       
    }

}
