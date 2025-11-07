package com.api.tests;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginAPITest2 {

    @Test(description="verify if login API is working ")
    public void loginTest(){

//        baseURI="http://64.227.160.186:8080";


        Response response = given()
                .baseUri("http://64.227.160.186:8080")
                .header("Content-Type", "application/json").body("{ \"username\": \"shraddha.qa\", \"password\": \"Pass@12345\" }").post("/api/auth/login");
        System.out.println(response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200);


    }
}
