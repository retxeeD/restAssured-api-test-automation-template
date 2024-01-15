package com.example.restassuredapitestautomation.api;

import com.example.restassuredapitestautomation.commons.Constant;
import com.example.restassuredapitestautomation.commons.DataTest;
import com.example.restassuredapitestautomation.commons.PropertiesLoader;
import io.restassured.http.ContentType;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import io.restassured.response.Response;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Log4j
public class Api extends ApiConfig implements IApi{

    private Configuration configuration;
    private ResultDescription resultDescription;
    public DataTest data;

    public Api(){
        super();
        this.data = data;
        this.configuration = new Configuration();
    }

    public void initialize(String baseUrl, String endpoint){
        this.baseUrl = PropertiesLoader.getInstance().getValueWithEnvironment(baseUrl);
        configuration.endpoint = endpoint;
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

    public Response postMethod(DataTest data, String fileName) throws IOException {
        String jsonSchema = FileUtils.readFileToString(new File(Constant.JSON_SCHEMA_PATH_BASE + fileName));
        configuration.initialization();
        setBody(data.request);
        data.response = configuration.requestSpecification.when().post(configuration.endpoint);
        data.response
                .then()
                .assertThat()
                .body(matchesJsonSchema(jsonSchema));
        this.data = data;
        summaryResult();
        return data.response;
    }

    public Response getMethod(DataTest data){
        configuration.initialization();
        data.response = configuration.requestSpecification.when().get(configuration.endpoint);
        return data.response;
    }

    public Response putMethod(DataTest data){
        configuration.initialization();
        setBody(data.request);
        data.response = configuration.requestSpecification.when().put(configuration.endpoint);
        return data.response;
    }

    public Response patchMethod(DataTest data){
        configuration.initialization();
        setBody(data.request);
        data.response = configuration.requestSpecification.when().patch(configuration.endpoint);
        return data.response;
    }

    public Response deleteMethod(DataTest data){
        configuration.initialization();
        setBody(data.request);
        data.response = configuration.requestSpecification.when().delete(configuration.endpoint);
        return data.response;
    }

    private void setBody(JSONObject request){
        if (request != null){
            configuration.requestSpecification.contentType(ContentType.JSON).body(request.toJSONString());
        }
    }

    private void summaryResult(){
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
        //data.scenario.log(requestAndResponseLog.toString());
        configuration.requestSpecification = create();
        configuration.initialization();
    }

}
