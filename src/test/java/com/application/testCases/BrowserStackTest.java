package com.application.testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.application.utility.SysLogger;

import java.net.URL;

public class BrowserStackTest {

  public static final String USERNAME = "<username>";
  public static final String AUTOMATE_KEY = "<key>";
  public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

  public static void main(String[] args) throws Exception {

    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability("browser", "IE");
    caps.setCapability("browser_version", "11.0");
    caps.setCapability("os", "Windows");
    caps.setCapability("os_version", "10");
    caps.setCapability("resolution", "1024x768");

    WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
    driver.get("http://www.google.com");
    WebElement element = driver.findElement(By.name("q"));

    element.sendKeys("BrowserStack");
    element.submit();

    SysLogger.log(driver.getTitle());
    driver.quit();

  }
}