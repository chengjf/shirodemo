package com.chengjf.shiro.demo.common.model;

public class ErrorMessage {

    private String status;

    private String message;

    public ErrorMessage(String stauts, String message) {

        this.status = stauts;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
