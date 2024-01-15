package com.example.restassuredapitestautomation.contracts.Auth;

import org.json.simple.JSONObject;

public class ObtainTokenContract {

    public static JSONObject getValidRequest(){
        JSONObject request = new JSONObject();

        request.put("username", "kminchelle");
        request.put("password", "0lelplR");

        return request;
    }
}
