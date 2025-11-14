package com.api.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

public class AccountControllerResponse {
 
 private String accountNumber;
 private String accountType;
 private double balance;
 private String status; 
 private String branch; 
 private Date createdAt;
 private String ownerName; 
 
 public AccountControllerResponse(){}

@JsonIgnoreProperties(ignoreUnknown = true)
    public AccountControllerResponse(String accountNumber, String accountType, Double balance, String branch, Date createdAt, String ownerName, String status) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.branch = branch;
        this.createdAt = createdAt;
        this.ownerName = ownerName;
        this.status = status;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AccountControllerResponse{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountType='" + accountType + '\'' +
                ", balance='" + balance + '\'' +
                ", status='" + status + '\'' +
                ", branch='" + branch + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}
