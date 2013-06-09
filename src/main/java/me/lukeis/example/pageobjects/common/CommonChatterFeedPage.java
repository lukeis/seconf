package me.lukeis.example.pageobjects.common;

import me.lukeis.example.pageobjects.ChatterFeedPage;
import org.openqa.selenium.WebDriver;

public class CommonChatterFeedPage extends CommonPage implements ChatterFeedPage {
  public CommonChatterFeedPage(WebDriver driver) {
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
