package com.api.tests;

import com.api.base.AuthService;
import com.api.models.request.SignUpRequest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountCreationTest {

    @Test(description="verify if Sign up API is working ")
    public void createAccountTest() {

        SignUpRequest signUpRequest = new SignUpRequest.Builder()
                .userName("Daisu15")
                .firstName("Daisy")
                .lastName("Jone")
                .email("daisu15@yahoo.com")
                .passWord("12345678")
                .mobileNumber("9876543213")
                .build();


        AuthService authService = new AuthService();
        Response response = authService.signUp(signUpRequest);

        System.out.println(response.asPrettyString());
        Assert.assertEquals(response.asPrettyString(), "User registered successfully!");
        System.out.println(response.statusCode());
        Assert.assertEquals(response.statusCode(), 200);


    }

    }
