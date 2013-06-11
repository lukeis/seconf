package me.lukeis.example.pageobjects.ios;

import me.lukeis.example.drivers.TouchDeviceDriver;
import me.lukeis.example.pageobjects.ChatterFeedPage;
import me.lukeis.example.pageobjects.common.CommonChatterFeedPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class IOSChatterFeedPage extends CommonChatterFeedPage {

  private By addBtn = By.id("publishermenu compose ico");
  private By newPostBtn = By.id("chatter.composer.post");
  private By shareBtn = By.id("Share");

  public IOSChatterFeedPage(WebDriver driver) {
    super(driver);
    longWait.until(ExpectedConditions.presenceOfElementLocated(addBtn));
  }

  @Override
  public ChatterFeedPage createPost(String message) {
    driver.findElement(addBtn).click();
    shortWait.until(ExpectedConditions.visibilityOfElementLocated(newPostBtn)).click();
    driver.findElement(By.tagName("UIATextView")).sendKeys(message);
    driver.findElement(shareBtn).click();
    return this;
  }

  @Override
  public String getStringOfLatestPost() {
//    new TouchActions(driver).flick(driver.findElement(By.tagName("UIATableCell")), 0, 120, 0).perform();
//
//    shortWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("Pull to Refresh")));
    return driver.findElement(By.id("body")).getText();
  }

}
