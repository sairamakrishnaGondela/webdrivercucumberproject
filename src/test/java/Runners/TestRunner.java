package Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
//import io.cucumber.testng.AbstractTestNGCucumberTests;
//import io.cucumber.testng.CucumberOptions;
//import io.cucumber.testng.FeatureWrapper;
//import io.cucumber.testng.TestNGCucumberRunner;

import org.junit.runner.RunWith;

/*import cucumber.api.CucumberOptions;sss
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;*/

@RunWith(Cucumber.class)
@CucumberOptions( features = "src/test/java/Features",
        glue = {"StepDefinition","Hooks"},
                plugin = {"pretty"}
                //monochrome = false,
                //dryRun = true

                        //plugin = {"com.epam.reportportal.cucumber.StepReporter"})
                //plugin = {"pretty","html.target/cucumber-html-report","json:cucumber.json"}
        )

public class TestRunner
{


}
