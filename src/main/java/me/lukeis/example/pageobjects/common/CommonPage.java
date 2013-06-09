package me.lukeis.example.pageobjects.common;

import me.lukeis.example.pageobjects.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonPage implements Page {

  protected WebDriver driver;
  protected WebDriverWait shortWait;
  protected WebDriverWait longWait;

  public CommonPage(WebDriver driver) {
    this.driver = driver;
    shortWait = new WebDriverWait(driver, 5);
    longWait = new WebDriverWait(driver, 30);
  }

  public WebDriver getDriver() {
    return driver;
  }
}
