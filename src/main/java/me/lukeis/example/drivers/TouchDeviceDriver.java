package me.lukeis.example.drivers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasTouchScreen;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TouchScreen;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteTouchScreen;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class TouchDeviceDriver extends RemoteWebDriver implements HasTouchScreen, TakesScreenshot {
  private RemoteTouchScreen touchScreen;

  public TouchDeviceDriver(URL url, Capabilities caps) {
    super(url, caps);
    touchScreen = new RemoteTouchScreen(new RemoteExecuteMethod(this));
  }

  @Override
  public TouchScreen getTouch() {
    return touchScreen;
  }

  @Override
  public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
    String base64 = execute(DriverCommand.SCREENSHOT).getValue().toString();
    return target.convertFromBase64Png(base64);
  }
}
