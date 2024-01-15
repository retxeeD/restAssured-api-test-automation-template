package com.example.restassuredapitestautomation.util;


import com.example.restassuredapitestautomation.commons.Constant;
import com.example.restassuredapitestautomation.commons.PropertiesLoader;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Report {

    public static void newReport(){

        PropertiesLoader propertiesLoader = PropertiesLoader.getInstance();
        File reportOutoutDirectory = new File("target");
        List<String> jsonFiles = new ArrayList<String>();
        String reportJsonPath = "./target/report.json";
        jsonFiles.add(reportJsonPath);
        String projectName, environment;
        projectName = propertiesLoader.getValue("CI_PROJECT_NAME");
        environment = Constant.ENVIRONMENT;

        if (environment.toLowerCase().contains("dev")){
            environment = "Development";
        } else if (environment.toLowerCase().contains("hom")) {
            environment = "Homologation";
        } else if (environment.toLowerCase().contains("sdb")) {
            environment = "Sandbox";
        }

        Configuration configuration = new Configuration(reportOutoutDirectory, projectName);
        configuration.addClassifications("Environment", environment);
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();


    }

}