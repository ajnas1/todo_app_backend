package com.example.todo_backend.model.dto;

public class RegisterReponseModel {
    private String message;
    private int statusCode;
    private int userId;

    public RegisterReponseModel(String message,int statusCode,int userId) {
        this.message = message;
        this.statusCode = statusCode;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
