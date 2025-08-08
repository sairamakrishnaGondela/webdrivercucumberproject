package Pages;

import Utility.DriverFactory;
import Utility.ElementUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class HomePage
{

    private WebDriver driver;

    JavascriptExecutor js ;
    ElementUtils utils;



    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        this.js = (JavascriptExecutor) this.driver;
        this.utils = new ElementUtils(this.driver);
    }

    @FindBy(xpath = "//button[@title='App Launcher']")
    private WebElement appLauncher;

   // @FindBy(css = "input[type='search'][placeholder*='Search apps and items']")
    @FindBy(xpath = "//input[@type='search' and contains(@placeholder,'Search apps and items')]")
    private WebElement searchtext;

    @FindBy(xpath = "//a[text()='Setup Home']")
    private WebElement homewelcometext;

    @FindBy(xpath = "//a[@title='New']")
    private WebElement Newbutton;

    @FindBy(css = "button[title='Cancel and close']")
    private WebElement closepopup;

    //Teachers page
    @FindBy(xpath = "//label[text()='Teachers Name']/following-sibling::div//input")
    private WebElement Teachername;

    @FindBy(xpath = "//label[text()='textsecurity']/following-sibling::div//input")
    private WebElement textsecurity;

    @FindBy(xpath ="//button[@name ='SaveEdit']")
    private WebElement savebutton;

    public void enterrequiredteacherdetails()
    {
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        utils.sendkeys(Teachername,"raamkrishna");
        utils.cleardataintextbox(Teachername);
        utils.cleardataintextbox(Teachername);
        utils.jsentertext(Teachername,"ramakrishna");
        utils.scrolldown();
        utils.mouseover(textsecurity);
        utils.jsentertext(textsecurity,"12345");
        utils.jsClick(savebutton);
        if(driver.findElement(By.xpath("//button[text()='New Contact']")).isDisplayed())
        {
            System.out.println("element is displayed");
        }

        else
        {
            System.out.println("element is not displayed");
        }
    }
    public void validatewelcoetext()
    {
        String text = homewelcometext.getText();
        System.out.println(text);
    }
    public void searchtext() throws InterruptedException {
        if(searchtext.isDisplayed())
        {
            searchtext.sendKeys("Teachers");

            Thread.sleep(5000);
            searchtext.sendKeys(Keys.ENTER);
            Thread.sleep(20000);
            if(Newbutton.isDisplayed())
            {
                System.out.println("new button is displayed");
                js.executeScript("arguments[0].click()",Newbutton);
            }

            else
            {
                System.out.println("New button is not displayed");
            }

        }
    }

    public void enteraallrequiredfields()
    {

    }

    public void clicknewbutton()
    {
        Newbutton.click();
    }

    public void clickappLauncher() throws InterruptedException {
         Thread.sleep(5000);
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         driver.switchTo().defaultContent();

        if(appLauncher.isDisplayed())
        {

            System.out.printf("in If method");
            //appLauncher.click();
            //searchtext.sendKeys("text", Keys.ENTER);
            js.executeScript("arguments[0].click();", appLauncher);
            System.out.println("clicked on app launcher");
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
