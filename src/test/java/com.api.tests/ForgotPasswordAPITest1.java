package com.api.tests;

import com.api.base.AuthService;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class ForgotPasswordAPITest1 {

    @Test(description="verify if Forgot Password API is working ")
    public void forgotPasswordTest(){

//        ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest("shraddha.st.qa@gmail.com");
//        LoginRequest loginRequest = new LoginRequest("shraddha.qa", "Pass@12345");
        AuthService authService = new AuthService();

        Response response = authService.forgotPassword("daisu12@yahoo.com");


        System.out.println(response.asPrettyString());
//        System.out.println(response.getMessagefp());
//        System.out.println(loginResponse.getEmail());
//        System.out.println(loginResponse.getEmail());
//        System.out.println(loginResponse.getId());
//
//
//        Assert.assertTrue(loginResponse.getToken() != null);
//        Assert.assertEquals(loginResponse.getEmail() , "shraddha.st.qa@gmail.com");
//        Assert.assertEquals(loginResponse.getId() , 2737);

    }
}
