package com.example.restassuredapitestautomation.api;

import com.example.restassuredapitestautomation.commons.Constant;
import com.example.restassuredapitestautomation.commons.PropertiesLoader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class ApiConfig {

    private static final String APPLICATION_PROPERTIES = "/application.properties";

    protected String baseUrl;

    public RequestSpecification create(){
        defineRelaxedHttps();
        RequestSpecification specification = defineBaseUrl();
        specification = verifyProxy(specification);
        return specification;
    }

    private RequestSpecification defineBaseUrl(){
        String environment = null;
        if(System.getenv(Constant.ENVIRONMENT) == null || System.getenv(Constant.ENVIRONMENT).equals("")){
            environment = getEnvironment();
        }else{
            environment = System.getenv(Constant.ENVIRONMENT);
        }
        System.setProperty(Constant.ENVIRONMENT, environment);
        String url = getBaseUrl(environment, this.baseUrl);
        RequestSpecification espc = new RequestSpecBuilder().setBaseUri(url).build();
        return RestAssured.given().spec(espc);
    }

    public String getBaseUrl(String environment, String pathBaseUrl){
        StringBuilder tag = new StringBuilder();
        String baseUrl = PropertiesLoader.getInstance().getValue(APPLICATION_PROPERTIES,
                tag.append(environment.toLowerCase()).append(".").append(pathBaseUrl).toString());
        return baseUrl;
    }

    public String getEndpoint(String pathEndpoint){
        String endpoint = PropertiesLoader.getInstance().getValue(APPLICATION_PROPERTIES, pathEndpoint);
        return endpoint;
    }

    private String getEnvironment(){
        String environment = PropertiesLoader.getInstance().getValueEnvironment(Constant.ENVIRONMENT).toLowerCase();
        return environment;
    }

    private void defineRelaxedHttps(){
        RestAssured.useRelaxedHTTPSValidation();
    }

    private RequestSpecification verifyProxy(RequestSpecification specification){
        PropertiesLoader propertiesLoader = PropertiesLoader.getInstance();
        boolean proxy = Boolean.parseBoolean(propertiesLoader.getValueEnvironment("proxy").trim().toLowerCase());
        if (proxy){
            specification.proxy(propertiesLoader.getValue("proxy.host").trim(), Integer.parseInt(propertiesLoader.getValue("proxy.port").trim()));
        }
        return specification;
    }

}
