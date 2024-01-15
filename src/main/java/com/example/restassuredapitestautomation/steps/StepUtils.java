package com.example.restassuredapitestautomation.steps;

import com.example.restassuredapitestautomation.api.Api;
import com.example.restassuredapitestautomation.commons.DataTest;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j;

import java.io.IOException;

@Log4j
public class StepUtils {
    DataTest dataTest;
    Api api;

    public StepUtils(DataTest dataTest, Api api){
        this.dataTest = dataTest;
        this.api = api;
    }

    @When("I do a simple Post request")
    public void IdoASimplePostRequest() throws IOException {
        dataTest.response = api.postMethod(dataTest,"/Auth/JsonSchema.json" );
    }

}
