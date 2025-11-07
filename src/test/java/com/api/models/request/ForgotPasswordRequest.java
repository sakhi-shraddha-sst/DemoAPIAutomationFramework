package com.api.models.request;

public class ForgotPasswordRequest {

    private String email;
    private String message;

    public ForgotPasswordRequest(String email) {
        super();
        this.email = email;

    }


    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    @Override
    public String toString() {
        return "ForgotPasswordRequest{" +
                "email='" + email + '\'' +
                '}';
    }
}
