package com.api.tests;

import com.api.base.AccountControllerService;
import com.api.base.AuthService;
import com.api.models.request.LoginRequest;
import com.api.models.response.AccountControllerResponse;
import com.api.models.response.LoginResponse;
import com.api.reporting.ExtentReportManager;
import com.api.utilities.JsonUtils;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class AccountControllerTest {


    @Test(description="verify account creation is working or not ")
    public void createNewAccount() {
        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest("shraddha.qa", "Pass@12345"));

        LoginResponse loginResponse = response.as(LoginResponse.class);
        AccountControllerService accountControllerService = new AccountControllerService();

        response = accountControllerService.createAccount( "SAVINGS", "main", loginResponse.getToken());

        List<AccountControllerResponse> accountControllerResponseList = new ArrayList<>();

        String responseBody = response.asString();

        if(responseBody.trim().startsWith("[")){
            accountControllerResponseList = response.as(new TypeRef<List<AccountControllerResponse>>() {});
        }else if(responseBody.trim().startsWith("{")){
            AccountControllerResponse single = response.as(AccountControllerResponse.class);
            accountControllerResponseList.add(single);
        }

        for(AccountControllerResponse account : accountControllerResponseList) {
            String details = account.getAccountNumber() + " - " + account.getOwnerName();
            System.out.println(details);
            System.out.println(account.getAccountNumber());
            System.out.println(account.getAccountType());
            System.out.println(account.getBalance());
            System.out.println(account.getStatus());
            System.out.println(account.getBranch());
            System.out.println(account.getCreatedAt());
            System.out.println(account.getOwnerName());
            ExtentReportManager.logInfo("Account details = " + details);
            ExtentReportManager.logInfo("Account Type = " + account.getAccountType());
            ExtentReportManager.logInfo("Account Branch = " + account.getBranch());


        }


    }


    @Test(description="verify if user account is displaying or not ")
    public void getUserAccounts() {
        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest("shraddha.qa", "Pass@12345"));

        LoginResponse loginResponse = response.as(LoginResponse.class);
        AccountControllerService accountControllerService = new AccountControllerService();

        response = accountControllerService.getAccountUser(loginResponse.getToken());


        List<AccountControllerResponse> accountControllerResponseList = new ArrayList<>();

        String responseBody = response.asString();

        if(responseBody.trim().startsWith("[")){
            accountControllerResponseList = response.as(new TypeRef<List<AccountControllerResponse>>() {});
        }else if(responseBody.trim().startsWith("{")){
            AccountControllerResponse single = response.as(AccountControllerResponse.class);
            accountControllerResponseList.add(single);
        }

        for(AccountControllerResponse account : accountControllerResponseList) {
            String details = account.getAccountNumber() + " - " + account.getOwnerName();
            System.out.println(details);
            System.out.println(account.getAccountNumber());
            System.out.println(account.getAccountType());
            System.out.println(account.getBalance());
            System.out.println(account.getStatus());
            System.out.println(account.getBranch());
            System.out.println(account.getCreatedAt());
            System.out.println(account.getOwnerName());
            ExtentReportManager.logInfo("Account details = " + details);
            ExtentReportManager.logInfo("Account Type = " + account.getAccountType());

        }

    }


    @Test(description="verify if user's account details is displaying by number or not ")
    public void getNewAccountNumberDetails() {
        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest("shraddha.qa", "Pass@12345"));

        LoginResponse loginResponse = response.as(LoginResponse.class);
        AccountControllerService accountControllerService = new AccountControllerService();

        // create new account
        response = accountControllerService.createAccount( "SAVINGS", "main", loginResponse.getToken());

        List<AccountControllerResponse> createdAccounts = JsonUtils.parseAccountResponse(response);

        // Expecting 1 new account created
        AccountControllerResponse newAccount = createdAccounts.get(0);

        String newAccountNumber =  newAccount.getAccountNumber();
        ExtentReportManager.logInfo("Newly Created Account Number: " + newAccountNumber);

        // GET NEW ACCOUNT DETAILS FROM API

        response = accountControllerService.getAccountNumber(loginResponse.getToken(), newAccountNumber);

        List<AccountControllerResponse> fetchedAccounts = JsonUtils.parseAccountResponse(response);

        for(AccountControllerResponse acc : fetchedAccounts){
            String details = acc.getAccountNumber() + " - " + acc.getOwnerName();
            System.out.println(details);
            ExtentReportManager.logInfo("Fetched Account Details: " + details);
            ExtentReportManager.logInfo("Account Type: " + acc.getAccountType());
            ExtentReportManager.logInfo("Account Status: " + acc.getStatus());

        }



    }


}
