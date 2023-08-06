package com.test.api.automation.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "pretty", "html:target/cucumber-report.html", "json:target/TestAPIRunner.json", "rerun:target/FAILED/TestAPIRunner.txt"},
        features = "src/main/resources/features/TestAPI.feature:12",
        // tags = "@test1",
        glue = {"com.test.api.automation.steps"},
        dryRun = false
)

public class TestAPIRunner {

}
