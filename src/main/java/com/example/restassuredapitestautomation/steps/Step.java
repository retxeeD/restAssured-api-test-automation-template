package com.example.restassuredapitestautomation.steps;

import com.example.restassuredapitestautomation.api.Api;
import com.example.restassuredapitestautomation.commons.Constant;
import com.example.restassuredapitestautomation.commons.DataTest;
import io.cucumber.java.en.Given;
import lombok.extern.log4j.Log4j;

@Log4j
public class Step {
    DataTest dataTest;
    Api api;

    public Step(DataTest dataTest, Api api){
        this.dataTest = dataTest;
        this.api = api;
    }

    @Given("I have valid data to do a get request")
    public void iHaveValidDataToDoAGetRequest(){

    }

    private void init(){
        this.api.initialize(Constant.BASE_URL, Constant.ENDPOINT);
    }


}
