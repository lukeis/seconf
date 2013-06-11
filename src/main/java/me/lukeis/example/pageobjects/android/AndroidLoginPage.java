package me.lukeis.example.pageobjects.android;

import me.lukeis.example.pageobjects.ChatterFeedPage;
import me.lukeis.example.pageobjects.common.CommonLoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AndroidLoginPage extends CommonLoginPage {

  private By approveOauth = By.id("oaapprove");

  public AndroidLoginPage(WebDriver driver) {
    super(driver);
  }

  public ChatterFeedPage login(String name, String pwd) {
//    longWait.until(ExpectedConditions.presenceOfElementLocated(By.id("oauth_webview")));
//
//    driver.switchTo().window("WEBVIEW");
//
//    ((JavascriptExecutor)driver).executeScript(
//        "document.getElementById('username').value = arguments[0];" +
//            "document.getElementById('password').value = arguments[1];",
//        name, pwd);
//
//    driver.findElement(loginBtn).click();
//
//    List<WebElement> maintenanceContinue = driver.findElements(continueLink);
//    if (maintenanceContinue.size() > 0) {
//      maintenanceContinue.get(0).click();
//    }
//
//    List<WebElement> els = null;
//    try {
//      els = shortWait.until(ExpectedConditions
//          .presenceOfAllElementsLocatedBy(approveOauth));
//    } catch (TimeoutException te) {
//      // pass
//    }
//    if (els != null && els.size() > 0) {
//      els.get(0).click();
//    }
//
//    driver.switchTo().window("NATIVE_APP");

    return new AndroidChatterFeedPage(driver);
  }
}
