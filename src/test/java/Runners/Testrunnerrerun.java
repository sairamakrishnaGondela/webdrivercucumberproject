package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"@target/failed_scenarios.txt"},
        glue = {"StepDefinition","Hooks"},
       //monochrome = false,
        // dryRun = true
        plugin = {"pretty","html:Hooks/misreport.html","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                }


)
public class Testrunnerrerun extends AbstractTestNGCucumberTests{
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
