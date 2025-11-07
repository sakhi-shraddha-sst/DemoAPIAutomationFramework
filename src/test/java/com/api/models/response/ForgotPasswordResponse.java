package com.api.models.response;

public class ForgotPasswordResponse {

    private String messagefp;
    private String email;
    public ForgotPasswordResponse() {
    }

    public ForgotPasswordResponse(String messagefp) {
        this.messagefp = messagefp;
    }

    public String getMessagefp() {
        return messagefp;
    }

    public void setMessagefp(String messagefp) {
        this.messagefp = messagefp;
    }

    @Override
    public String toString() {
        return "ForgotPasswordResponse{" +
                "messagefp='" + messagefp + '\'' +
                '}';
    }
}
