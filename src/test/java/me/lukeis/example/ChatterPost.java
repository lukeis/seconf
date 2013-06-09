package me.lukeis.example;

import org.fest.assertions.Assertions;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ChatterPost {

//  @Test
  public void PostToChatter() throws Exception {

//    WebDriver driver = new RemoteWebDriver(new URL("http://localhost:5555/wd/hub"), DesiredCapabilities.iphone());
    WebDriver driver = new FirefoxDriver();
    WebDriverWait shortWait = new WebDriverWait(driver, 5);

    try {
    driver.get("https://login.salesforce.com/");

    ResourceBundle rb = ResourceBundle.getBundle("user");

    ((JavascriptExecutor)driver).executeScript(
        "document.getElementById('username').value = arguments[0];" +
        "document.getElementById('password').value = arguments[1];",
        rb.getString("username"), rb.getString("password"));

    driver.findElement(By.id("Login")).click();

    List<WebElement> maintenanceContinue = driver.findElements(By.linkText("Continue"));
    if (maintenanceContinue.size() > 0) {
      maintenanceContinue.get(0).click();
    }

    if (!driver.getCurrentUrl().endsWith("_ui/core/chatter/ui/ChatterPage")) {
      driver.findElement(By.linkText("Chatter")).click();
      shortWait.until(ExpectedConditions.titleContains("Chatter"));
    }

    String newPostText = "I'm doing a LIVE demo at Selenium Conference!!!! " + (new Date()).toString();
    WebElement postField = driver.findElement(By.id("publishereditablearea"));
    postField.click();
    postField.sendKeys(newPostText);

    final WebElement share = driver.findElement(By.id("publishersharebutton"));
    share.click();
    shortWait.until(new ExpectedCondition<Boolean>() {
      @Override
      public Boolean apply(WebDriver input) {
        return share.getCssValue("background-color").equals("rgba(138, 181, 41, 1)");
      }
    });

    WebElement newPost = driver.findElement(By.cssSelector(".feeditemtext"));

    Assertions.assertThat(newPost.getText())
        .as("checking new post text")
        .isEqualTo(newPostText);

    } finally {
      driver.quit();
    }

  }
}
