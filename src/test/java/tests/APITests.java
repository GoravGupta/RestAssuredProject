package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITests {

    //Response response = null;

    @Test
    public void TC_Get_1() {
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println("Status Code : " + response.getStatusCode());
        System.out.println("Status Line : " + response.getStatusLine());
        System.out.println("Response Body : " + response.getBody().asString());
        System.out.println("Content Type : " + response.contentType());
        System.out.println("Headers : " + response.getHeaders());
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(actualStatusCode, 200, "Response of the API is Not Success. Response Status Code is " + actualStatusCode);

    }

    @Test
    public void TC_Get_2() {
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");

        //Fetch all the emails from the response
        String responseBody = response.getBody().asString();
        JsonPath json = response.jsonPath();
        String emails = json.getString("data.email");
        System.out.println("All Emails  : \n" + emails);

        String testEmail = "george.bluth@reqres.in";
        Assert.assertTrue(emails.contains(testEmail), "Response of the API doesn't contains Test Email " + testEmail);
        //Fetch first email
        System.out.println(json.getString("data[0].email"));
    }
}
