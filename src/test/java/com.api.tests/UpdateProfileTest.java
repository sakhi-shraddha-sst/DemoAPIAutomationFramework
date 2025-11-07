package com.api.tests;

import com.api.base.AuthService;
import com.api.base.UserProfileManangementService;
import com.api.models.request.LoginRequest;
import com.api.models.request.UpdateProfileRequest;
import com.api.models.response.LoginResponse;
import com.api.models.response.UserProfleResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateProfileTest {


    @Test(description="verify if profile udpate API is working ")
    public void updateProfileTest(){
        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest("shraddha.qa", "Pass@12345"));
      LoginResponse loginResponse =  response.as(LoginResponse.class);
        System.out.println(response.asPrettyString());

        System.out.println("------------------------------------------------------------------------");

        UserProfileManangementService userProfileManangementService = new UserProfileManangementService();
        response = userProfileManangementService.getProfile(loginResponse.getToken());
        System.out.println(response.asPrettyString());

       UserProfleResponse userProfleResponse = response.as(UserProfleResponse.class);

        Assert.assertEquals(userProfleResponse.getUsername(), "shraddha.qa");

        System.out.println("------------------------------------------------------------------------");

        UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest.Builder()
                .firstName("Jass").lastName("Joe")
                .email("shraddha.st.qa@gmail.com").mobileNumber("9876764677")
                .build();


       response = userProfileManangementService.updateProfile(loginResponse.getToken(), updateProfileRequest);
        System.out.println(response.asPrettyString());

    }


}
