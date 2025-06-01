package Hooks;

import Utility.DriverFactoryold;
import Utility.DriverFactory;
import Utility.configreader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class ApplicationHooks
{

    public WebDriver driver;
    private configreader configvaluereader;
    private Properties prop;

    @Before(order = 0)
    public void initializeProperties() {
        configvaluereader = new configreader();
        prop = configvaluereader.init_prop();
        System.out.println("Initialized properties.");
        System.out.println("Using Healenium Proxy URL: http://localhost:8085/wd/hub");
    }

    @Before(order = 1)
    public void initializeBrowser()
    {
        String browserName = System.getProperty("browser","chrome");
        System.out.println("Browser selected: " + browserName);

        // Initialize the driver using the DriverFactory
        driver = DriverFactory.init_driver(browserName);
    }

    @After(order = 0)
    public void quitBrowser() {
        DriverFactory.quitDriver();
        System.out.println("Browser closed.");
    }

    //@After(order = 1)
    /*public void takeScreenshot(Scenario scenario)
    {
        WebDriver augmentedDriver = new Augmenter().augment(driver);
        if (augmentedDriver != null) {
            TakesScreenshot ts = (TakesScreenshot) augmentedDriver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            // ...save
        } else {
            System.out.println("Driver is null. Skipping screenshot.");
        }

        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            scenario.attach(screenshot, "image/png", screenshotName);
            System.out.println("Screenshot captured for failed scenario.");
        }
    }*/
}


