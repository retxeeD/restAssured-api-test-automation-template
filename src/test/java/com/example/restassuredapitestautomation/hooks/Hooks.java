package com.example.restassuredapitestautomation.hooks;

import com.example.restassuredapitestautomation.commons.DataTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import cucumber.api.Scenario;
import lombok.extern.log4j.Log4j;

import java.util.HashMap;

@Log4j
public class Hooks{
    DataTest dataTest;

    public Hooks(DataTest dataTest){
        this.dataTest = dataTest;
    }

    @Before
    public void init(Scenario scenario){
        log.info("-".repeat(50));
        log.info("Scenario: ".concat(scenario.getName()));
        log.info("-".repeat(50));
        prepareData(scenario);
    }

    private void prepareData(Scenario scenario){
        dataTest.mapPathParam = new HashMap<String, String>();
        dataTest.mapQueryString = new HashMap<String, String>();
        dataTest.mapHeader = new HashMap<String, String>();
        dataTest.mapToken = new HashMap<String, String>();
        dataTest.mapLastResult = new HashMap<String, String>();
        dataTest.scenario = scenario;
    }

    @After
    public void afterScenario(Scenario scenario){
        log.info("-".repeat(50));
        log.info("Scenario end: '".concat(scenario.getName())
                .concat("'receive status") +
                (scenario.getStatus().toString().toUpperCase()));
        log.info("-".repeat(50));
    }
}
