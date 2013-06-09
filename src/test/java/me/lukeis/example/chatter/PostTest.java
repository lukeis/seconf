package me.lukeis.example.chatter;

import me.lukeis.example.drivers.MobileSafariIPhoneDriver;
import me.lukeis.example.drivers.SelendroidDriver;
import me.lukeis.example.drivers.iOSDriver;
import me.lukeis.example.pageobjects.ChatterFeedPage;
import me.lukeis.example.pageobjects.LoginPage;
import me.lukeis.example.pageobjects.common.CommonClassMapper;
import me.lukeis.example.pageobjects.common.CommonLoginPage;
import me.lukeis.example.pageobjects.common.CommonPage;
import org.fest.assertions.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Arrays;
import java.util.Collection;
import java.util.ResourceBundle;

@RunWith(Parameterized.class)
public class PostTest {
  private LoginPage startPage;

  public PostTest(LoginPage p) {
    startPage = p;
  }

  @Parameterized.Parameters
  public static Collection drivers() {
    CommonClassMapper ccm = new CommonClassMapper();
    return Arrays.asList(
        new Object[][]{
            {MobileSafariIPhoneDriver.getInstance().getPageObject().navigateTo()},
            {new iOSDriver().getPageObject()},
            {SelendroidDriver.getInstance().getPageObject()},
            {new CommonLoginPage(new ChromeDriver(), ccm).navigateTo()},
            {new CommonLoginPage(new FirefoxDriver(), ccm).navigateTo()},
            {new CommonLoginPage(new PhantomJSDriver(new DesiredCapabilities()), ccm).navigateTo()},
        }
    );
  }

  @Test
  public void newChatterPostTest() throws Exception {
    ResourceBundle rb = ResourceBundle.getBundle("user");

    String postText = "Doing a live demo with Page Objects at #seleniumconf on " +
        ((CommonPage)startPage).getDriver().getClass().getSimpleName();

    ChatterFeedPage cp = startPage.login(rb.getString("username"), rb.getString("password"))
        .createPost(postText);

    Assertions.assertThat(cp.getStringOfLatestPost())
        .as("the most recent chatter post (that I should have just posted)")
        .isEqualTo(postText);
  }

  @After
  public void detroyDriver() {
    ((CommonPage)startPage).getDriver().quit();
  }
}
