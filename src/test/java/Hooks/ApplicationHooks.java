package Hooks;

import Utility.DriverFactory;
import Utility.ExtentReportManager;
import Utility.ScreenshotUtil;
import Utility.configreader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import tech.grasshopper.pdf.config.ReportConfig;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import static Utility.ExtentReportManager.*;

public class ApplicationHooks
{

    public WebDriver driver;
    private configreader configvaluereader;
    private Properties prop;
    private static ExtentTest test;
    //private static ExtentReports extent = ExtentReportManager.getInstance();

    @Before(order = 0)
    public void initializeProperties()
    {

        configvaluereader = new configreader();
        prop = configvaluereader.init_prop();
        System.out.println("Initialized properties.");
        System.out.println("Using Healenium Proxy URL: http://localhost:8085/wd/hub");
    }

//    @AfterStep
//    public void logStepResult(Scenario scenario) {
//        if (scenario.isFailed()) {String screenshotPath = ScreenshotUtil.captureScreenshot(DriverFactory.getDriver(), scenario.getName());
//            test.fail("Step failed").addScreenCaptureFromPath(screenshotPath);
//        } else {
//            test.pass("Step passed");
//        }
//    }


    @Before(order = 1)
    public void initializeBrowser(Scenario scenario)
    {
        ExtentReportManager.startTest(scenario.getName());
        //test = extent.createTest(scenario.getName());
        String browserName = System.getProperty("browser","chrome");
        System.out.println("Browser selected: " + browserName);

        // Initialize the driver using the DriverFactory
        driver = DriverFactory.init_driver(browserName);
    }

    @AfterStep
    public void afterStep(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            String screenshotPath = ScreenshotUtil.capture(driver, scenario.getName());
            ExtentReportManager.logFailWithScreenshot("Step failed", screenshotPath);
        } else {
            ExtentReportManager.logPass("Step passed");
        }
    }
    @After(order = 0)
    public void quitBrowser() {
        DriverFactory.quitDriver();
        ExtentReportManager.extent.flush();
        System.out.println("Browser closed.");
    }

    /*public class ScreenshotUtil {
        public static String captureScreenshot(WebDriver driver, String name) {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String path = "reports/screenshots/" + name + "_" + System.currentTimeMillis() + ".png";
            try {
                FileUtils.copyFile(src, new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return path;
        }
    }*/


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


