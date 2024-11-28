package StepDefinition;

import Pages.loginPage;
import Utility.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import org.testng.annotations.*;

import static Utility.DriverFactory.*;
import static Pages.loginPage.*;




public class loginstepdefinition
{



        loginPage pg = new loginPage(DriverFactory.getDriver());
        @Given("I am on login page")
        public void I_am_on_login_page()
        {
                try {
                        DriverFactory.getDriver().get("https://login.salesforce.com/");
                        System.out.println("I am ready to go to ");


                }
                catch(Exception e)
                {
                        System.out.print(e.getMessage());
                }

        }

        @When("^I enter user name and password and click on login button$")
        public void enterusernameandpassword()
        {
               // pg.fillusernameandpassword();


        }
        @Then("^I will be landing on home page$")
        public void validateelementvisibility()
        {
                System.out.println("it is on the heom page");
        }


        @When("I enter {string} or {string} and click on login button")
        public void I_enter_or_and_click_on_login_button(String uname, String pwd)
        {
                  pg.enterusername(uname);
                  pg.enterpassowrd(pwd);
                  pg.clicksubmit();

        }

        @Then("I will be seeing error message")
        public void i_will_be_seeing_error_message()
        {
                Assert.assertTrue(pg.validateerrormessage());
                System.out.println("I am  seeing error message for wrong user name and password");
        }

        @Then("I will be seeing success message")
        public void i_will_be_seeing_success_message()
        {
                System.out.println("I have logged in to the application");
                //Assert.assertEquals("sai","sai");
                // Write code here that turns the phrase above into concrete actions
                //throw new io.cucumber.java.PendingException();
        }


        @Given("I am on Home page")
        public void i_am_on_home_page()
        {

        }

        @Then("I will be navigating to Home page and validate title {string}")
        public void I_will_be_navigating_to_Home_page_and_validate_title(String arg0)
        {
                Assert.assertEquals(pg.returntitle(),arg0);
        }



//        @AfterClass
//        public void cleanup_testng() {
//                driver.quit();
//        }
//        @AfterTest
//        public void cleanup() {
//                driver.quit();
//        }


}