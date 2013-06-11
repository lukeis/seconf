package me.lukeis.example.drivers;

import me.lukeis.example.pageobjects.common.CommonLoginPage;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class MobileSafariIPadDriver extends TouchDeviceDriver implements Driver {
  public MobileSafariIPadDriver(URL url, Capabilities caps) {
    super(url, caps);
  }

  @Override
  public CommonLoginPage getPageObject() {
    return new CommonLoginPage(this);
  }

  public static MobileSafariIPadDriver getInstance() {
    try {
      return new MobileSafariIPadDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.ipad());
    } catch (MalformedURLException me) {
      throw new RuntimeException(me);
    }
  }
}
