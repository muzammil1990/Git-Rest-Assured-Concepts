package com.volvo.restassuredbasics.beginner;

import com.volvo.restassured.utility.Payload;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        JsonPath js = new JsonPath(Payload.coursePrice());

        // Print No of courses returned by API
        int count = js.getInt("courses.size()");
        System.out.println(count);

        // Print Purchase Amount
        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        // Print Title of the first course
        String titleFirstCourse = js.get("courses[0].title");
        System.out.println(titleFirstCourse);

        // Print All course titles and their respective Prices
        for (int i = 0; i < count; i++) {
            String courseTitle = js.get("courses[" + i + "].title");
            int coursePrice = js.getInt("courses[" + i + "].price");
            int coursePrice1 = js.get("courses[" + i + "].price"); // get() can also be used for int value
            System.out.println(courseTitle + " -> " + coursePrice);
            System.out.println(courseTitle + " -> " + coursePrice1);

            // or
            // get(): Returns:
            // The object matching the Object path. This may be any primitive type, a List or a Map.
            // A java.lang.ClassCastException will be thrown if the object cannot be casted to the expected type

            System.out.println(js.get("courses[" + i + "].title").toString()); // converting object type to string
            System.out.println(js.get("courses[" + i + "].price").toString()); // converting object type to string
        }

        // Print no of copies sold by RPA Course
        System.out.println("no. of copies sold by RPA course");

        for (int i = 0; i < count; i++) {

            String coursesTitles = js.get("courses[" + i + "].title");
            if (coursesTitles.equalsIgnoreCase("RPA")) {
                int copies = js.get("courses[" + i + "].copies"); // get() can also be used for int value
                System.out.println(copies);
                break;
            }
        }

    }
}
