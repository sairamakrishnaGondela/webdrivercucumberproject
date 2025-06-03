package Pages;



import Utility.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Utility.DriverFactory;

public  class loginPage extends DriverFactory
{
    private WebDriver driver;

    // Constructor to initialize driver and page elements
    public loginPage(WebDriver driver)
    {

        this.driver = DriverFactory.getDriver();  // Always use SelfHealingDriver
        PageFactory.initElements(this.driver, this);
    }


    // Locators for login page elements
    @FindBy(xpath = "//input[@id='username']")
    private WebElement username;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(id = "Login")
    private WebElement submit;

    @FindBy(id = "error")
    private WebElement errorMessage;

    // Method to validate error message visibility
    public boolean validateErrorMessage() {
        return errorMessage.isDisplayed();
    }

    // Method to return page title
    public String returnTitle() {
        return driver.getTitle();
    }

    // Method to enter username
    public void enterUsername(String userName) {
        username.sendKeys(userName);
    }

    // Method to enter password
    public void enterPassword(String passWord) {
        password.sendKeys(passWord);
    }

    // Method to click the login button
    public void clickSubmit() {
        submit.click();
    }
    }
//    public static  void click(WebElement element)
//    {
//        element.click();
//    }
//
//
//
//    public static void fillusernameandpassword(String name,String assword)
//    {
//
//        //driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//        enterText(username,name);
//        enterText(password,assword);
//        click(submit);
//
//
//
//
//    }







