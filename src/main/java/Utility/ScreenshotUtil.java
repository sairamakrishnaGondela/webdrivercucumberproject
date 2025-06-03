package Utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public  class ScreenshotUtil {
    public static String capture(WebDriver driver, String name) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "target/screenshots/" + name + "_" + System.currentTimeMillis() + ".png";
        File dest = new File(path);
        FileUtils.copyFile(src, dest);
        return path;
    }
}
