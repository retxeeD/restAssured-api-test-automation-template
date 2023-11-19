package com.example.restassuredapitestautomation.commons;

import com.example.restassuredapitestautomation.api.Request;
import com.example.restassuredapitestautomation.api.Response;
import com.example.restassuredapitestautomation.api.ResultDescription;
import org.apache.log4j.Logger;
import cucumber.api.Scenario;

import java.util.HashMap;

public class DataTest {

    final static Logger logger = Logger.getLogger(DataTest.class);

    public ResultDescription result;
    public Request request;
    public Response response;

    public HashMap<String, String> mapPathParam;
    public HashMap<String, String> mapQueryString;
    public HashMap<String, String> mapToken;
    public HashMap<String, String> mapHeader;
    public HashMap<String, String> mapLastResult;
    public String tokenTitle;
    public Scenario scenario;

}
