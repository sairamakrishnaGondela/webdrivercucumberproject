
package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/resources/Features",
        glue = {"StepDefinition","Hooks"},
       //monochrome = false,
        // dryRun = true,
        plugin = {"pretty",
                "pretty",
                "json:target/cucumber.json",
                "html:target/cucumber-reports/cucumber-html-report.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/failed_scenarios.txt"},
//        plugin = {"pretty","html:Hooks/misreport.html","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
//                "rerun:target/failed_scenarios.txt"},
        tags = "@smoke"


)
public class Testrunner extends AbstractTestNGCucumberTests{
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }



}


