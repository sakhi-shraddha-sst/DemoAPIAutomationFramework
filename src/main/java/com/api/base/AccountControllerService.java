package com.api.base;

import com.api.models.request.LoginRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.restassured.response.Response;

import java.util.HashMap;
@JsonIgnoreProperties(ignoreUnknown = true)

public class AccountControllerService extends BaseService {


    private static final String BASE_PATH = "/api/accounts";

    public Response createAccount(String accountType, String branch, String token){
        HashMap<String, String> payload = new HashMap<>();
        payload.put("accountType", accountType);
        payload.put("branch", branch);
        setAuthToken(token);
        return postRequest(payload, BASE_PATH );
    }

    public Response getAccountNumber(String token, String accountNumber){
        setAuthToken(token);
        return getRequest(BASE_PATH + "/" + accountNumber);
    }

    public Response getAccountUser(String token){
        setAuthToken(token);
        return getRequest(BASE_PATH + "/user");
    }

    // this one can use it for a single payload
//    public Response forgotPassword(String emailAddress){
//        HashMap<String, String> payload = new HashMap<>();
//        payload.put("email", emailAddress);
//        return postRequest(payload, BASE_PATH + "forgot-password");
//    }
}




