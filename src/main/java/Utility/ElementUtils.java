package Utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ElementUtils extends DriverFactory
{
    WebDriver driver;
    Actions act;
    JavascriptExecutor js;

    // ✅ Constructor properly initializes everything
    public ElementUtils(WebDriver driver) {
        this.driver = driver;
        this.act = new Actions(driver); // Initialize AFTER driver is set
        this.js = (JavascriptExecutor) driver;
    }

    public void jsClick(WebElement element)
    {
     js.executeScript("arguments[0].click()",element);
    }

    public  void jsentertext(WebElement element,String message)
    {
        js.executeScript("arguments[0].value='+message+'",element);

    }

    public  void scrolldown()
    {
        js.executeScript("window.scrollBy(0,500);");
    }

    public  void scrollTobuttonofpage()
    {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

    }

    public void scrolltocurrentelement(WebElement element)
    {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public String getUrl()
    {
        String title = (String) js.executeScript("return document.URL;");
        return title;


    }

    public String getTitleofthepage()
    {

        String title = (String) js.executeScript("return document.title;");
        return title;
    }
    //Action components
   public void mouseover(WebElement element)
   {
       act.moveToElement(element).build().perform();
   }

   public void Draganddrop(WebElement source,WebElement destination)
   {
       act.dragAndDrop(source,destination).perform();
   }

   public void rightclick(WebElement element)
   {
       act.contextClick(element).perform();
//       act.contextClick();
   }

    public void rightclick()
    {
        act.contextClick().perform();
//
    }

    public void doubleclick()
    {
        act.doubleClick().perform();
    }

    public void doubleclick(WebElement element)
    {
        act.doubleClick(element).perform();
    }



   public void cleardataintextbox(WebElement element)
   {
       act.click(element)
               .keyDown(Keys.CONTROL)
               .sendKeys("a")
               .keyUp(Keys.CONTROL)
               .sendKeys(Keys.DELETE)
               .perform();
   }

   public void sendkeys(WebElement element,String text)
   {
       act.sendKeys(element,text).perform();
   }

   //Robot class
   /*The Robot class (from java.awt.Robot) is used to simulate native OS-level keyboard and mouse events — outside the scope of the browser DOM.

🧠 Key Reasons to Use Robot in Selenium:
    Use Case	Why Robot is Needed
    Handling OS-level popups	Such as file upload/download dialogs that aren't part of the browser
    Simulating real keyboard input	E.g., pressing TAB, ESC, CTRL+S, etc., at system level
    Taking screenshots using native APIs	Capturing screen outside the browser
    Working with non-DOM components	Interacting with Java applets, system prompts, native alerts, etc.
    Typing into file upload windows	Where Selenium can’t interact directly*
    /*/

    /*⚠️ Notes:
        robot.delay(milliseconds) helps simulate real-time user delay (optional but useful).

    Robot works at the OS level, not the DOM.

    Avoid using it heavily in headless environments or Selenium Grid runs — it's not compatible with remote execution.
   *?

     */

    public void enterTextthroughrobot(String text) throws AWTException
    {
        char[] a = text.toCharArray();
        Robot robot = new Robot();
        for(char ch : a) {


            robot.keyPress(KeyEvent.VK_H);
            robot.keyRelease(KeyEvent.VK_H);
            robot.keyPress(KeyEvent.VK_I);
            robot.keyRelease(KeyEvent.VK_I);
        }
    }


}
