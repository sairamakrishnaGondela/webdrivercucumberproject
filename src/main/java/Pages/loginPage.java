package Pages;


import Utility.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public  class loginPage
{
    private WebDriver driver;
    @FindBy(xpath = "//div[@id='username_container']//input[@id='username']")
    static WebElement username;

    @FindBy(xpath = "//input[@id='password']")
    static WebElement password;

    @FindBy(xpath = "//input[@id='Login']")
    static WebElement submit;

    @FindBy(xpath = "//div[@class='loginError' and @id = 'error']")
    static WebElement errormssg;



    public loginPage(WebDriver driver)
    {
        PageFactory.initElements(DriverFactory.getDriver(),this);
        this.driver = driver;
    }

    public boolean validateerrormessage()
    {
       return errormssg.isDisplayed();
    }
    public String returntitle()
    {
       return driver.getTitle();
    }
    public void enterusername(String usernametext)
    {
        username.sendKeys(usernametext);
    }

    public void enterpassowrd(String passwordtext)
    {
        password.sendKeys(passwordtext);
    }

    public void clicksubmit()
    {
        submit.click();
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






}
