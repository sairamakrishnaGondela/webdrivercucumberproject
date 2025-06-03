package Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactoryremote {

    private static final ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    // Set Docker container URLs
    private static final String CHROME_URL = "http://localhost:4444/wd/hub";
    private static final String FIREFOX_URL = "http://localhost:4445/wd/hub";
    private static final String EDGE_URL = "http://localhost:4446/wd/hub";

    public static WebDriver init_driver(String browser) {
        System.out.println("Initializing remote browser: " + browser);

        try {
            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--disable-dev-shm-usage", "--no-sandbox", "--disable-gpu");
                    driver.set(new RemoteWebDriver(new URL(CHROME_URL), chromeOptions));
                    break;

                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    driver.set(new RemoteWebDriver(new URL(FIREFOX_URL), firefoxOptions));
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    driver.set(new RemoteWebDriver(new URL(EDGE_URL), edgeOptions));
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            // Maximize window and clear cookies
            getDriver().manage().window().maximize();
            getDriver().manage().deleteAllCookies();

        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Remote WebDriver URL", e);
        }

        return getDriver();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
