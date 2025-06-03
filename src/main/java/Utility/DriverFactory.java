package Utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

    // ThreadLocal for thread-safe WebDriver instances
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Initialize driver based on browser name
    public static WebDriver init_driver(String browser) {
        System.out.println("Initializing browser: " + browser);

        WebDriver localDriver;

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options= new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-extensions");
                options.addArguments("--disable-gpu");
                options.addArguments("--remote-allow-origins=*");

                // Optional: Avoid CDP use where possible
                options.setExperimentalOption("useAutomationExtension", false);
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

                //WebDriver driver = new ChromeDriver(options);

                // Optional chromeOptions
                // chromeOptions.addArguments("--disable-dev-shm-usage");
                // chromeOptions.addArguments("--no-sandbox");
                localDriver = new ChromeDriver(options);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                localDriver = new FirefoxDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                localDriver = new EdgeDriver();
                break;

            case "safari":
                // SafariDriver does not require WebDriverManager
                localDriver = new SafariDriver();
                break;

            default:
                System.out.println("Unsupported browser. Defaulting to Chrome.");
                WebDriverManager.chromedriver().setup();
                localDriver = new ChromeDriver();
                break;
        }

        driver.set(localDriver);

        // Maximize and clean session
        getDriver().manage().window().maximize();
        // getDriver().manage().deleteAllCookies(); // Optional

        return getDriver();
    }

    // Get WebDriver instance for current thread
    public static synchronized WebDriver getDriver() {
        return driver.get();
    }

    // Quit and clean up WebDriver instance
    public static void quitDriver() {
        WebDriver currentDriver = driver.get();
        if (currentDriver != null) {
            currentDriver.quit();
            driver.remove();
        }
    }
}
