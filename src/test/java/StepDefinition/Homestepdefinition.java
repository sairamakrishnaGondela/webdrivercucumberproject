package StepDefinition;

import Hooks.ApplicationHooks;
import Pages.HomePage;
import Utility.DriverFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;


public class Homestepdefinition
{
    private WebDriver driver;
    private Pages.HomePage hp;
    private ApplicationHooks hooks;
    public Homestepdefinition()
    {
        this.hp = new HomePage(DriverFactory.getDriver());
    }
    //HomePage hp = new HomePage();


    @Then("I will be clicking on APP launcher and search with object")
    public void i_will_be_clicking_on_app_launcher_and_search_with_object()
    {
        hp.validatewelcoetext();
        // Write code here that turns the phrase above into concrete actions

    }
   /* @Then("I will be clicking on APP launcher and search with object")

    public void I_will_be_clicking_on_app_launcher_and_search_with_object() throws InterruptedException {
       try {
           hp.validatewelcoetext();
           //hp.clickappLauncher();
           //hp.searchtext(string);
           }
       catch (Exception e)
       {
           System.out.println(e.getMessage());
       }

        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }*/

    @Then("I will navigate to Teachers object page")
    public void i_will_navigate_to_teachers_object_page()
    {
        hp.clicknewbutton();
        hp.clickclosebutton();
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


}
