package com.example.concurrent.enums;

public enum STATUS {

    SUCCESS("SUCCESS"), FAILURE("FAILURE");

    private String responseMessage;

    public String getResponseMessage() {
        return responseMessage;
    }

    private STATUS(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
