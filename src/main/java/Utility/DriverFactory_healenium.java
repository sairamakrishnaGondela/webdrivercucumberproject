package Utility;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory_healenium {

    private static final ThreadLocal<SelfHealingDriver> healingDriver = new ThreadLocal<>();

    // URL for Healenium Proxy (replace with your setup if different)
    private static final String HEALENIUM_PROXY_URL = "http://localhost:8085/wd/hub";

    // Method to initialize driver based on the browser passed as parameter
    public static WebDriver init_driver(String browser) {
        System.out.println("Initializing browser through Healenium Proxy: " + browser);

        RemoteWebDriver baseDriver;

        try {
            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--no-sandbox");
                    baseDriver = new RemoteWebDriver(new URL(HEALENIUM_PROXY_URL), chromeOptions);
                    break;
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    baseDriver = new RemoteWebDriver(new URL(HEALENIUM_PROXY_URL), firefoxOptions);
                    break;
                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    baseDriver = new RemoteWebDriver(new URL(HEALENIUM_PROXY_URL), edgeOptions);
                    break;
                default:
                    System.out.println("Unsupported browser. Defaulting to Chrome.");
                    ChromeOptions defaultOptions = new ChromeOptions();
                    baseDriver = new RemoteWebDriver(new URL(HEALENIUM_PROXY_URL), defaultOptions);
                    break;
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Healenium Proxy URL: " + HEALENIUM_PROXY_URL, e);
        }

        // Wrap the RemoteWebDriver with Healenium's SelfHealingDriver
        SelfHealingDriver shDriver = SelfHealingDriver.create(baseDriver);

        healingDriver.set(shDriver);

        // Maximize window and clear cookies
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();

        return getDriver(); // Return the SelfHealingDriver instance
    }

    // Get the ThreadLocal SelfHealingDriver instance
    public static synchronized SelfHealingDriver getDriver() {
        return healingDriver.get();
    }

    // Quit the driver session
    public static void quitDriver() {
        WebDriver driver = healingDriver.get();
        if (driver != null) {
            driver.quit();
            healingDriver.remove();
        }
    }
}
