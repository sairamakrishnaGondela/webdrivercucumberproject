package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/java/Features",
        glue = {"StepDefinition","Hooks"},
       //monochrome = false,
        // dryRun = true,
        plugin = {"pretty","html:Hooks/misreport.html","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:src/test/failed/rerun.txt"}


)
public class Testrunner2 extends AbstractTestNGCucumberTests{
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}


