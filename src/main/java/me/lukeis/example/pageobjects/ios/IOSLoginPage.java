package me.lukeis.example.pageobjects.ios;

import me.lukeis.example.pageobjects.ChatterFeedPage;
import me.lukeis.example.pageobjects.android.AndroidChatterFeedPage;
import me.lukeis.example.pageobjects.common.CommonLoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class IOSLoginPage extends CommonLoginPage {

  private By approveOauth = By.id("oaapprove");
  private By acceptEULA = By.id("EULA.agree");
  private By webview = By.tagName("UIAWebView");
  private By loginBtn = By.id("Login");

  public IOSLoginPage(WebDriver driver) {
    super(driver);
  }

  public ChatterFeedPage login(String name, String pwd) {

    driver.findElement(acceptEULA).click();

    WebElement password = longWait.until(ExpectedConditions.presenceOfElementLocated(By.id("Password")));
    final WebElement username = driver.findElement(By.tagName("UIATextField"));
    shortWait.until(new ExpectedCondition<Boolean>() {
      @Override
      public Boolean apply(WebDriver input) {
        username.click();
        if (input.findElements(By.tagName("UIAKeyboard")).size() > 0) {
          return true;
        }
        return false;
      }
    });
    username.sendKeys(name);

    password.click();

    // type in your password and login.

    return new IOSChatterFeedPage(driver);
  }
}
