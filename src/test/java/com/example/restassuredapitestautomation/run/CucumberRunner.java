package com.example.restassuredapitestautomation.run;

import com.example.restassuredapitestautomation.util.Report;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.apache.log4j.BasicConfigurator;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

/**
 * @author Pedro Lima
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        plugin = {"pretty", "json:target/report.json", "junit:target/junit-report.xml"},
        snippets = SnippetType.CAMELCASE,
        features = {"features/"},
        glue = {"com.example.restassuredapitestautomation"},
        tags = "@GetAuthToken and not @ignore"
)
public class CucumberRunner {
    @AfterClass
    public static void Runtest() {
        BasicConfigurator.configure();
        Report.newReport();
    }
}
