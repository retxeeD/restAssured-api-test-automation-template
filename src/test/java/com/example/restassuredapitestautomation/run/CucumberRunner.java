package com.example.restassuredapitestautomation.run;

import com.example.restassuredapitestautomation.util.Report;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

/**
 * @author Pedro Lima
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        strict = true,
        junit = "--step-notifications",
        plugin = {"pretty", "json:target/report.json", "junit:target/junit-report.xml"},
        snippets = SnippetType.CAMELCASE,
        features = {"features/"},
        glue = {"com.example.restassuredapitestautomation"},
        tags = {"@test","~@ignore"}
)


public class CucumberRunner {
    @AfterClass
    public static void Runtest() {
        Report.newReport();
    }
}
