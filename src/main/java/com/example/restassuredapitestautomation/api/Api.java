package com.example.restassuredapitestautomation.api;

import com.example.restassuredapitestautomation.commons.DataTest;
import com.example.restassuredapitestautomation.commons.PropertiesLoader;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.log4j.Log4j;

import java.util.Map;

@Log4j
public class Api  extends ApiConfig implements IApi{

    private Configuration configuration;
    private ResultDescription resultDescription;
    private DataTest data;

    public Api(){
        super();
        this.data = data;
        this.configuration = new Configuration();
    }

    public void initialize(String baseUrl, String endpoint){
        this.baseUrl = baseUrl;
        configuration.endpoint = PropertiesLoader.getInstance().getValue("/application.properties", endpoint);
        configuration.requestSpecification = create();
        configuration.initialization();
    }

    public Api setHeader(Map<String, String> mapHeader){
        if (null != mapHeader){
            configuration.requestSpecification.headers(mapHeader);
        }
        return this;
    }

    public Api setToken(String token){
        if (null != token){
            configuration.requestSpecification.auth().preemptive().oauth2(token);
        }
        return this;
    }

    public Api setTokenJWT(String fieldName, String token){
        if (null != token) {
            configuration.requestSpecification.header(fieldName, token);
        }
        return this;
    }

    public Api setPathParam(Map<String, String> pathParams){
        configuration.requestSpecification.pathParams(pathParams);
        return this;
    }

    public Api setQueryParam(Map<String, String> queryParams){
        configuration.requestSpecification.pathParams(queryParams);
        return this;
    }

    public ResultDescription postMethod(Request request){
        configuration.initialization();
        setBody(request);
        ValidatableResponse validatableResponse = configuration.requestSpecification
                .when()
                .post(configuration.endpoint)
                .then();
        return summaryResult(validatableResponse);
    }

    public ResultDescription getMethod(){
        configuration.initialization();
        ValidatableResponse validatableResponse = configuration.requestSpecification
                .contentType(ContentType.JSON)
                .when()
                .get(configuration.endpoint)
                .then();
        return summaryResult(validatableResponse);
    }

    public ResultDescription putMethod(Request request){
        configuration.initialization();
        setBody(request);
        ValidatableResponse validatableResponse = this.configuration.requestSpecification
                .put(configuration.endpoint)
                .then();
        return summaryResult(validatableResponse);
    }

    public ResultDescription patchMethod(Request request){
        configuration.initialization();
        setBody(request);
        ValidatableResponse validatableResponse = this.configuration.requestSpecification
                .patch(configuration.endpoint)
                .then();
        return summaryResult(validatableResponse);
    }

    public ResultDescription deleteMethod(Request request){
        configuration.initialization();
        setBody(request);
        ValidatableResponse validatableResponse = this.configuration.requestSpecification
                .when()
                .delete(configuration.endpoint)
                .then();
        return summaryResult(validatableResponse);
    }

    private void setBody(Request request){
        if (request != null){
            configuration.requestSpecification.contentType(ContentType.JSON).body(request);
        }
    }

    private ResultDescription summaryResult(ValidatableResponse validatableResponse){
        ResultDescription result = new ResultDescription(validatableResponse);
        StringBuilder requestAndResponseLog = new StringBuilder();
        requestAndResponseLog.append("[JSON REQUEST API ")
                .append(configuration.endpoint)
                .append("] \n")
                .append(configuration.requestWriter.toString())
                .append("\n[JSON RESPONSE API ")
                .append(configuration.endpoint)
                .append("] \n")
                .append(configuration.responseWriter.toString())
                .append("\n");
        log.info(requestAndResponseLog.toString());
        data.scenario.write(requestAndResponseLog.toString());
        configuration.requestSpecification = create();
        configuration.initialization();
        return result;
    }

}
