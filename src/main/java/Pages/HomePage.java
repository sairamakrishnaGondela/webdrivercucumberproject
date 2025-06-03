package Pages;

import Utility.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends DriverFactory
{
    private WebDriver driver;

    JavascriptExecutor js ;




    // Constructor to initialize driver and page elements
    public HomePage(WebDriver driver)
    {

        this.driver = DriverFactory.getDriver();  // Always use SelfHealingDriver
        PageFactory.initElements(this.driver, this);
        js = (JavascriptExecutor)this.driver;
    }

    @FindBy(xpath = "//button[@title='App Launcher']")
    private WebElement appLauncher;

   // @FindBy(css = "input[type='search'][placeholder*='Search apps and items']")
    @FindBy(xpath = "//input[@type='search' and contains(@placeholder,'Search apps and items')]")
    private WebElement searchtext;

    @FindBy(xpath = "//h1[text()='Seller Home']//span")
    private WebElement homewelcometext;

    @FindBy(css = "div[title='New']")
    private WebElement Newbutton;

    @FindBy(css = "button[title='Cancel and close']")
    private WebElement closepopup;

    public void validatewelcoetext()
    {
        String text = homewelcometext.getText();
        System.out.println(text);
    }
    public void searchtext(String text)
    {
        if(searchtext.isDisplayed())
        {
            searchtext.sendKeys(text, Keys.ENTER);
        }
    }

    public void clicknewbutton()
    {
        Newbutton.click();
    }

    public void clickappLauncher() throws InterruptedException {
         Thread.sleep(5000);
         driver.switchTo().defaultContent();

        if(appLauncher.isDisplayed())
        {

            System.out.printf("in If method");
            appLauncher.click();
            //searchtext.sendKeys("text", Keys.ENTER);
            js.executeScript("arguments[0].click();", appLauncher);
        }

        else
        {
            System.out.printf("not able to find the element");
        }
     }

     public void clickclosebutton()
     {

         js.executeScript("arguments[0].click();", closepopup);

     }


}
