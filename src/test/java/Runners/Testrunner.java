package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/Features",
        glue = {"StepDefinition","Hooks"},
       //monochrome = false,
       // dryRun = true
        plugin = {"pretty","html:Hooks/misreport.html"}

)
public class Testrunner extends AbstractTestNGCucumberTests{

}
