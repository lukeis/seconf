package me.lukeis.example.pageobjects.android;

import me.lukeis.example.pageobjects.ChatterFeedPage;
import me.lukeis.example.pageobjects.common.CommonChatterFeedPage;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AndroidChatterFeedPage extends CommonChatterFeedPage {

  private By MDPOpenButton = By.id("mdp_layout_button_background");
  private By textArea = By.id("post_text");
  private By shareBtn = By.id("mi_share");
  private By shareContainer = By.tagName("ActionMenuView");
  private final By PTR_status = By.id("fl_inner");

  public AndroidChatterFeedPage(WebDriver driver) {
    super(driver);
    longWait.until(ExpectedConditions.visibilityOfElementLocated(MDPOpenButton));
  }

  @Override
  public ChatterFeedPage createPost(String message) {
    driver.findElement(MDPOpenButton).click();
    shortWait.until(
        ExpectedConditions.visibilityOfElementLocated(By
            .linkText("Post"))).click();
    shortWait.until(ExpectedConditions.visibilityOfElementLocated(shareContainer));

    driver.findElement(textArea).sendKeys(message);
    driver.findElement(shareBtn).click();

    return this;
  }

  @Override
  public String getStringOfLatestPost() {
    new TouchActions(driver)
        .flick(driver.findElement(By.tagName("PullToRefreshListView")), 0, 200, 0)
        .perform();

    shortWait.until(new ExpectedCondition<Boolean>() {
      @Override
      public Boolean apply(WebDriver d) {
        List<WebElement> els = driver.findElements(PTR_status);
        return els.size() > 0
            && (els.get(0).isDisplayed() && els.get(0)
            .getLocation().getY() > 123);
      }
    });

    longWait.until(new ExpectedCondition<Boolean>() {
      @Override
      public Boolean apply(WebDriver d) {
        List<WebElement> els = driver.findElements(PTR_status);
        try {
          return els.size() > 0
              && (!els.get(0).isDisplayed() || els.get(0)
              .getLocation().getY() <= 123);
        } catch (StaleElementReferenceException se) {
          return false;
        }
      }
    });

    return shortWait.until(new ExpectedCondition<String>() {
      @Override
      public String apply(WebDriver input) {
        try {
          return input.findElement(By.id("feed_detail")).getText();
        } catch (StaleElementReferenceException se) {
          return null;
        }
      }
    });
  }
}
