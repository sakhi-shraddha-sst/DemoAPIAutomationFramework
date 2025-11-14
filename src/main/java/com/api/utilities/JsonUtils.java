package com.api.utilities;

import com.api.models.response.AccountControllerResponse;
import com.api.reporting.ExtentReportManager;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {


    public static List<AccountControllerResponse> parseAccountResponse(Response response){

        List<AccountControllerResponse> accounts = new ArrayList<>();

    String responseBody = response.asString().trim();

        if(responseBody.trim().startsWith("[")){
        accounts = response.as(new TypeRef<List<AccountControllerResponse>>() {});
    }else if(responseBody.startsWith("{")){
        AccountControllerResponse single = response.as(AccountControllerResponse.class);
        accounts.add(single);
    }

        return accounts;

    }



}