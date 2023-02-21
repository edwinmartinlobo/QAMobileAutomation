package com.mtaf.runners;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = { "pretty", "html:Reports/cucumber-report.html"},
        features = {"src/test/resources/features" },
        glue = {"com.mtaf.stepDefinations" },
        dryRun = false,
        monochrome = true,
        tags = "@splash-screen-feature")
public class splashScreenRunner extends BaseRunner  {


}
