package com.api.tests;

import com.api.base.AuthService;
import com.api.models.request.LoginRequest;
import com.api.models.response.LoginResponse;
import io.restassured.response.Response;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginAPITest3 {

    @Test(description="verify if login API is working ")
    public void loginTest(){

        LoginRequest loginRequest = new LoginRequest("shraddha.qa", "Pass@12345");
        AuthService authService = new AuthService();
//        Response response = authService.login("{ \"username\": \"shraddha.qa\", \"password\": \"Pass@12345\" }");

        Response response = authService.login(loginRequest);

        LoginResponse loginResponse = response.as(LoginResponse.class);

        System.out.println(response.asPrettyString());
        System.out.println(loginResponse.getToken());
        System.out.println(loginResponse.getEmail());
        System.out.println(loginResponse.getId());


        Assert.assertTrue(loginResponse.getToken() != null);
        Assert.assertEquals(loginResponse.getEmail() , "shraddha.st.qa@gmail.com");
        Assert.assertEquals(loginResponse.getId() , 2737);

    }
}
