package Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    public static ExtentReports extent;
    public static ExtentTest test;

    static {
        ExtentSparkReporter reporter = new ExtentSparkReporter("target/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }


    public static void startTest(String testName) {
        test = extent.createTest(testName);
    }

    public static void logPass(String message) {
        test.pass(message);
    }

    public static void logFailWithScreenshot(String message, String path) {
        test.fail(message, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
    }

    public static void endTest() {
        extent.flush();
    }
}
