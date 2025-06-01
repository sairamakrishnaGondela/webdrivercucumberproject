package Utility;

import com.epam.healenium.SelfHealingDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactoryold {

    private static final ThreadLocal<SelfHealingDriver> healingDriver = new ThreadLocal<>();

    // Method to initialize driver based on the browser passed as parameter
    public static WebDriver init_driver(String browser) {
        System.out.println("Initializing browser: " + browser);

        WebDriver baseDriver;

        // Set up WebDriver based on the provided browser
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                // Add any Chrome options here if needed
                baseDriver = new ChromeDriver(options);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                baseDriver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                baseDriver = new EdgeDriver();
                break;
            case "safari":
                baseDriver = new SafariDriver();
                break;
            default:
                System.out.println("Unsupported browser. Defaulting to Chrome.");
                WebDriverManager.chromedriver().setup();
                baseDriver = new ChromeDriver();
                break;
        }

        // Wrap the base driver with Healenium's SelfHealingDriver
        SelfHealingDriver shDriver = SelfHealingDriver.create(baseDriver);

        // Set in ThreadLocal
        healingDriver.set(shDriver);

        // Maximize window and clear cookies
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();

        return getDriver(); // Return SelfHealingDriver
    }

    // Get the ThreadLocal SelfHealingDriver instance
    public static synchronized SelfHealingDriver getDriver() {
        return healingDriver.get();
    }
//DriverFactory.getDriver().findElement(By.id("username")).sendKeys("testuser");
    // Quit the driver session
    public static void quitDriver() {
        WebDriver driver = healingDriver.get();
        if (driver != null) {
            driver.quit();
            healingDriver.remove();
        }
    }
}
