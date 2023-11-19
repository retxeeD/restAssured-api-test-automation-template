package com.example.restassuredapitestautomation.api;

public class ErrorResponse {

    public String status;
    public String message;

    public ErrorResponse(String status, String message){
        super();
        this.status = status;
        this.message = message;
    }

    public ErrorResponse(){
        super();
    }
}
