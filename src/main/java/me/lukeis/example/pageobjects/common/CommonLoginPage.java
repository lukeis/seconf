package me.lukeis.example.pageobjects.common;

import me.lukeis.example.pageobjects.ChatterFeedPage;
import me.lukeis.example.pageobjects.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CommonLoginPage extends CommonPage implements LoginPage {

  protected By loginBtn = By.id("Login");
  protected By continueLink = By.linkText("Continue");

  public CommonLoginPage(WebDriver driver) {
    super(driver);
  }

  public LoginPage navigateTo() {
    driver.get("https://login.salesforce.com");
    return this;
  }

  public ChatterFeedPage login(String name, String pwd) {
    ((JavascriptExecutor)driver).executeScript(
        "document.getElementById('username').value = arguments[0];" +
            "document.getElementById('password').value = arguments[1];",
        name, pwd);

    driver.findElement(loginBtn).click();

    List<WebElement> maintenanceContinue = driver.findElements(continueLink);
    if (maintenanceContinue.size() > 0) {
      maintenanceContinue.get(0).click();
    }

    if (!driver.getCurrentUrl().endsWith("_ui/core/chatter/ui/ChatterPage")) {
      driver.findElement(By.linkText("Chatter")).click();
      shortWait.until(ExpectedConditions.titleContains("Chatter"));
    }

    return new CommonChatterFeedPage(driver);
  }
}
