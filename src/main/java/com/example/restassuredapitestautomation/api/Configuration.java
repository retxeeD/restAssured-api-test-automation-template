package com.example.restassuredapitestautomation.api;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.output.WriterOutputStream;

import java.io.PrintStream;
import java.io.StringWriter;

public class Configuration {

    protected RequestSpecification requestSpecification;
    private ValidatableResponse target;
    protected StringWriter requestWriter;
    protected PrintStream requestCapture;
    protected StringWriter responseWriter;
    protected PrintStream responseCapture;
    public String endpoint;

    public void target(ValidatableResponse target){
        this.target = target;
    }

    public ValidatableResponse validations(){
        return target;
    }

    public ExtractableResponse<Response> response(){
        return this.target.extract();
    }

    public void setEndpoint(String endpoint){
        this.endpoint = endpoint;
    }

    public void setRequestSpecification(RequestSpecification requestSpecification){
        this.requestSpecification = requestSpecification;
    }

    public RequestSpecification initialization(){
        requestWriter = new StringWriter();
        requestCapture =  new PrintStream(new WriterOutputStream(requestWriter), true);
        responseWriter = new StringWriter();
        responseCapture = new PrintStream(new WriterOutputStream(responseWriter), true);
        requestSpecification
                .filter(new RequestLoggingFilter(requestCapture))
                .filter(new ResponseLoggingFilter(responseCapture));
        return requestSpecification;
    }

}
