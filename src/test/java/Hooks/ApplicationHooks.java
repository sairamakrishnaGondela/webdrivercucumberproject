package Hooks;

import Utility.DriverFactory;
import Utility.configreader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class ApplicationHooks
{

    private WebDriver driver;
    private configreader configvaluereader;
    Properties prop;
    private DriverFactory driverFactory;

    @Before(order = 0)
    public void initilizeproperties()
    {
        configvaluereader= new configreader();
        prop= configvaluereader.init_prop();
    }

    @Before(order = 1)
    public void initilizebroswer()
    {
        String browsername = prop.getProperty("browser");
        driverFactory = new DriverFactory();
        driver= driverFactory.init_driver(browsername);
    }

    @After(order =0)
    public  void quitbrowser()
    {
       driver.quit();
    }
    // for after annotations the sequence will be executed in reverse way means first order =1 will be executed
    //and later order =0 is executed
    @After(order =1)
    public  void takescreenshot(Scenario scenario)
    {
        if(scenario.isFailed())
        {
            String screenshotname = scenario.getName().replaceAll(" ","_");
            byte[] sourcepath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcepath,"image/png",screenshotname);
        }

    }

}
