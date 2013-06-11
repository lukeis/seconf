package me.lukeis.example;

import org.fest.assertions.Assertions;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.iphone.IPhoneDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ChatterPost {

  @Test
  public void PostToChatter() throws Exception {

    WebDriver driver = new IPhoneDriver();
    WebDriverWait wait = new WebDriverWait(driver, 30);

    try {
      driver.get("https://login.salesforce.com/");

      ResourceBundle rb = ResourceBundle.getBundle("user");

      ((JavascriptExecutor)driver).executeScript(
          "document.getElementById('username').value = arguments[0];" +
          "document.getElementById('password').value = arguments[1];",
          rb.getString("username"), rb.getString("password"));

      driver.findElement(By.id("Login")).click();

      wait.until(ExpectedConditions.elementToBeClickable(By.id("Chatter_Tab"))).click();
      wait.until(ExpectedConditions.titleContains("Chatter"));

      String newPostText = "I'm doing a demo at Selenium Conference!!!!";
      final WebElement postField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("publishereditablearea")));
      postField.sendKeys(newPostText);

      driver.findElement(By.id("publishersharebutton")).click();
      wait.until(new ExpectedCondition<Boolean>() {
        @Override
        public Boolean apply(WebDriver input) {
          return postField.getAttribute("value").equals("What are you working on?");
        }
      });

      WebElement newPost = driver.findElement(By.className("feeditemtext"));

      Assertions.assertThat(newPost.getText())
          .as("checking new post text")
          .isEqualTo(newPostText);

    } finally {
      driver.quit();
    }

  }
}
