package me.lukeis.example.pageobjects.ios;

import me.lukeis.example.pageobjects.ChatterFeedPage;
import me.lukeis.example.pageobjects.common.CommonChatterFeedPage;
import org.openqa.selenium.WebDriver;

public class IOSChatterFeedPage extends CommonChatterFeedPage {
  public IOSChatterFeedPage(WebDriver driver) {
    super(driver);
  }

  @Override
  public ChatterFeedPage createPost(String message) {
    return this;
  }

  @Override
  public String getStringOfLatestPost() {
    return "";
  }

}
