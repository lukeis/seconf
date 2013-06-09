package me.lukeis.example.drivers;

import me.lukeis.example.pageobjects.common.CommonClassMapper;
import me.lukeis.example.pageobjects.common.CommonLoginPage;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class MobileSafariIPhoneDriver extends TouchDeviceDriver implements Driver {
  public MobileSafariIPhoneDriver(URL url, Capabilities caps) {
    super(url, caps);
  }

  @Override
  public CommonLoginPage getPageObject() {
    return new CommonLoginPage(this, new CommonClassMapper());
  }

  public static MobileSafariIPhoneDriver getInstance() {
    try {
      return new MobileSafariIPhoneDriver(new URL("http://localhost:3001/wd/hub"), DesiredCapabilities.iphone());
    } catch (MalformedURLException me) {
      throw new RuntimeException(me);
    }
  }
}
