package com.example.restassuredapitestautomation.api;

import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ResultDescription {

    public String requestDescription;
    public String responseDescription;
    public ValidatableResponse validation;

    public ResultDescription(ValidatableResponse validation){
        this.validation = validation;
    }
}
