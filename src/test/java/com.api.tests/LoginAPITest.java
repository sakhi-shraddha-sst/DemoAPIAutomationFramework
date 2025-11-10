package com.api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginAPITest {

    @Test(description="verify if login API is working ")
    public void loginTest(){

        RestAssured.baseURI="http://64.227.160.186:8080";
        RequestSpecification x =  RestAssured.given();
       RequestSpecification y = x.header("Content-Type", "application/json");
        RequestSpecification z = y.body("{ \"username\": \"shraddha.qa\", \"password\": \"Pass@12345\" }");
        Response response = z.post("/com/api/auth/login");
        System.out.println(response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200);


    }
}
