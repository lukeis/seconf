package me.lukeis.example.drivers;

import me.lukeis.example.pageobjects.android.AndroidClassMapper;
import me.lukeis.example.pageobjects.android.AndroidLoginPage;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class SelendroidDriver extends TouchDeviceDriver implements Driver {

  public SelendroidDriver(URL url, Capabilities caps) {
    super(url, caps);
  }

  @Override
  public AndroidLoginPage getPageObject() {
    return new AndroidLoginPage(this, new AndroidClassMapper());
  }

  public static SelendroidDriver getInstance() {
    try {
      return new SelendroidDriver(new URL("http://localhost:8080/wd/hub"), DesiredCapabilities.android());
    } catch (MalformedURLException me) {
      throw new RuntimeException(me);
    }
  }
}
