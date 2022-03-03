package com.volvo.restassuredbasics.beginner;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.volvo.restassured.utility.Payload;

import io.restassured.path.json.JsonPath;

public class SumValidation_Excercise {

    @Test
    public void getSumOfCourses() {

        JsonPath js = new JsonPath(Payload.coursePrice());

        int count = js.getInt("courses.size()");
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println("purchase amount  " + purchaseAmount);

        System.out.println("sum of all courses matches with the purchase amount or not");

        int sum = 0;
        for (int i = 0; i < count; i++) {

            int coursesPrices = js.get("courses[" + i + "].price");
            int coursesCopies = js.get("courses[" + i + "].copies");

            sum = sum + coursesPrices * coursesCopies;

        }
        
        Assert.assertEquals(sum, purchaseAmount);
    }
}
