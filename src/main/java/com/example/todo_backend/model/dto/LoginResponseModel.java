package com.example.todo_backend.model.dto;


public class LoginResponseModel {
    private int statusCode;
    private boolean success;
    private String message;
    private String token;

    public LoginResponseModel(int statusCode,boolean success,String message,String token){
        this.statusCode = statusCode;
        this.success = success;
        this.message = message;
        this.token = token;
    }

    public boolean getSuccess() {
        return success;
    }
    

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }
    

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getToken() {
        return token;
    }

    public void setUserId(String token) {
        this.token = token;
    }

}
