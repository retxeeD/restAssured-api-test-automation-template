package com.example.restassuredapitestautomation.commons;

import com.example.restassuredapitestautomation.api.ResultDescription;
import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class DataTest {

    final static Logger logger = Logger.getLogger(DataTest.class);

    public ResultDescription result;
    public JSONObject request;
    public Response response;

    public HashMap<String, String> mapPathParam;
    public HashMap<String, String> mapQueryString;
    public HashMap<String, String> mapToken;
    public HashMap<String, String> mapHeader;
    public HashMap<String, String> mapLastResult;
    public String tokenTitle;
    public Scenario scenario;

}
