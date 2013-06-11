package me.lukeis.example.pageobjects.common;

import me.lukeis.example.pageobjects.ChatterFeedPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CommonChatterFeedPage extends CommonPage implements ChatterFeedPage {

  private By postFieldLocator = By.id("publishereditablearea");
  private By shareBtn = By.id("publishersharebutton");
  private By feedItemText = By.className("feeditemtext");


  public CommonChatterFeedPage(WebDriver driver) {
    super(driver);
  }
  @Override
  public ChatterFeedPage createPost(String message) {
    final WebElement postField = shortWait.until(ExpectedConditions.presenceOfElementLocated(postFieldLocator));
    postField.clear();
    postField.sendKeys(message);

    driver.findElement(shareBtn).click();
    shortWait.until(new ExpectedCondition<Boolean>() {
      @Override
      public Boolean apply(WebDriver input) {
        return postField.getAttribute("value").equals("What are you working on?");
      }
    });
    return this;
  }

  @Override
  public String getStringOfLatestPost() {
    return driver.findElement(feedItemText).getText();
  }

}
