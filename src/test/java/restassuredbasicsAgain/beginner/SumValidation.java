package restassuredbasicsAgain.beginner;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.Payload;
import io.restassured.path.json.JsonPath;

// continuation of ComplexJsonParseA class ; Section 6 of Rahul Shetty course
// 6. Verify if Sum of all Course prices matches with Purchase Amount
public class SumValidation {

    @Test
    public void sumOfCourses() {

        JsonPath js = new JsonPath(Payload.coursePrice());
        int courseCount = js.getInt("courses.size()");
        System.out.println("total courses: " + courseCount);

        int sum = 0;
        for (int i = 0; i < courseCount; i++) {

            int price = js.getInt("courses[" + i + "].price");
            int copies = js.getInt("courses[" + i + "].copies");
            int amount = price * copies;
             System.out.println(amount);
            sum = sum + amount;
        }
        System.out.println("Total Sum of courses: " + sum);

        int purchaseAmount = js.getInt("dashboard.purchaseAmount");

        Assert.assertEquals(sum, purchaseAmount);
    }
}
