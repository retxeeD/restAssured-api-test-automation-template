package com.example.restassuredapitestautomation.steps;

import com.example.restassuredapitestautomation.api.Api;
import com.example.restassuredapitestautomation.commons.Constant;
import com.example.restassuredapitestautomation.commons.DataTest;
import com.example.restassuredapitestautomation.commons.PropertiesLoader;
import com.example.restassuredapitestautomation.contracts.Auth.ObtainTokenContract;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Log4j
public class AuthSteps{
    DataTest dataTest;
    Api api;

    public AuthSteps(DataTest dataTest, Api api){
        this.dataTest = dataTest;
        this.api = api;
    }

    @Given("I have valid authentication route request")
    public void iHaveValidAuthenticationRouteRequest() {
        init(PropertiesLoader.getInstance().getValue(Constant.OBTAIN_TOKEN_ENDPOINT));
    }

    @And("I have valid data to obtain authentication token")
    public void iHaveValidDataToObtainAuthenticationToken() {
        dataTest.request = ObtainTokenContract.getValidRequest();
    }

    @Then("I receive the token")
    public void iReceiveTheToken() {
        assertEquals(dataTest.response.getStatusCode(), 200);
        assertNotNull(dataTest.response.getBody().path("token"));
    }

    private void init(String endpoint){
        this.api.initialize(Constant.BASE_URL, endpoint);
    }
}
