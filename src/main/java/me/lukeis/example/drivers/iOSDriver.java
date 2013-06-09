package me.lukeis.example.drivers;

import me.lukeis.example.pageobjects.LoginPage;
import me.lukeis.example.pageobjects.ios.IOSClassMapper;
import me.lukeis.example.pageobjects.ios.IOSLoginPage;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class iOSDriver extends TouchDeviceDriver implements Driver {
  private static final DesiredCapabilities iosChatter = new DesiredCapabilities(){{
    setCapability("device", "iphone");
    setCapability("language", "en");
    setCapability("locale", "en_GB");
    setCapability("CFBundleName", "chatter");
    setCapability("CFBundleVersion", "3.3.1");
    setCapability("variation", "Regular");
    setCapability("simulator", true);
  }};
  private static URL url;
  static {
    try {
      url = new URL("http://localhost:3001/wd/hub");
    } catch (MalformedURLException me) {
      me.printStackTrace();
    }
  }

  public iOSDriver() {
    super(url, iosChatter);
  }

  @Override
  public LoginPage getPageObject() {
    return new IOSLoginPage(this, new IOSClassMapper());
  }

}
