package com.api.base;

import com.api.models.request.LoginRequest;
import io.restassured.response.Response;

import java.util.HashMap;

public class AdminControllerService extends BaseService{

    private static final String BASE_PATH = "/com/api/admin";

    public Response createAccount(LoginRequest payload){
        return postRequest(payload, BASE_PATH );
    }

    public Response getAccounts(String token){
        setAuthToken(token);
        return getRequest(BASE_PATH + "/accounts");
    }

    public Response getAccountTransactions(String token){
        setAuthToken(token);
        return getRequest(BASE_PATH + "/transactions");
    }

    public Response getAccountUsers(String token){
        setAuthToken(token);
        return getRequest(BASE_PATH + "/users");
    }

    public Response getAccountUserID(String token, String userID){
        setAuthToken(token);
        return getRequest(BASE_PATH + "/users/" + userID);
    }

    // this one can use it for a single payload
    public Response forgotPassword(String emailAddress){
        HashMap<String, String> payload = new HashMap<>();
        payload.put("email", emailAddress);
        return postRequest(payload, BASE_PATH + "forgot-password");
    }


}
