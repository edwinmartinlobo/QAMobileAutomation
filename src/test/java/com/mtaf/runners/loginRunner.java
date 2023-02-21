package com.mtaf.runners;


import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = { "pretty", "html:cucumber-report.html"},
        features = {"src/test/resources/features" },
        glue = {"com.mtaf.stepDefinations" },
        dryRun = false,
        monochrome = true,
        tags = "@login-feature")
public class loginRunner extends BaseRunner  {


}
