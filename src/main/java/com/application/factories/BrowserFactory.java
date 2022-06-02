package com.application.factories;

import com.application.listeners.WebActionListeners;
import com.application.utility.ConfigReader;
import com.application.utility.SysLogger;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

import java.time.Duration;
import java.util.HashMap;

public class BrowserFactory {
	
	private BrowserFactory() {}
	private static final String browser = ConfigReader.getBrowserName();
	private static final String chromedriverPath = getChromedriverPath();
	private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getBrowser(){

		if (threadDriver.get() == null) {
			WebDriver driver = createWebDriver();
			threadDriver.set(driver);
			return threadDriver.get();
		}
		else {
			return threadDriver.get();
		}
	}
	
	public static synchronized void closeWebDriver() {
		WebDriver driver =threadDriver.get();
		if (driver != null) {
			driver.close();
			driver.quit();
			driver=null;
			threadDriver.remove();
			SysLogger.log("Closing WebDriver Connection for threadId - "+Thread.currentThread().getId());
		}
	}
	
	private static synchronized WebDriver createWebDriver() {
		System.setProperty("webdriver.chrome.driver", chromedriverPath);
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriver chromedriver = new ChromeDriver(getChromeOptions(false));
			chromedriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
			chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
			chromedriver.manage().window().maximize();
			WebDriverListener webListeners = new WebActionListeners();
			WebDriver eventDriver = new EventFiringDecorator(webListeners).decorate(chromedriver);
			chromedriver = eventDriver;
			return chromedriver;
		}
		if(browser.equalsIgnoreCase("chrome-Headless")) {
			WebDriver chromedriver = new ChromeDriver(getChromeOptions(true));
			chromedriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
			chromedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
			chromedriver.manage().window().maximize();
			WebDriverListener webListeners = new WebActionListeners();
			WebDriver eventDriver = new EventFiringDecorator(webListeners).decorate(chromedriver);
			chromedriver = eventDriver;
			return chromedriver;
		}
		
		if(browser.equalsIgnoreCase("firefox")) {
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("network.proxy.type", ProxyType.AUTODETECT.ordinal());
			WebDriver firefoxdriver = new FirefoxDriver();
			firefoxdriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
			firefoxdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
			firefoxdriver.manage().window().maximize();
			WebDriverListener webListeners = new WebActionListeners();
			WebDriver eventDriver = new EventFiringDecorator(webListeners).decorate(firefoxdriver);
			firefoxdriver = eventDriver;
			return firefoxdriver;
		}
		return null;
	}
	
	public synchronized static String getChromedriverPath() {
		String os = System.getProperty("os.name");
		if(os.toLowerCase().contains("linux")) {
			return System.getProperty("user.dir") + "/src/test/resources/chromedriver_linux";
		}
		else if (os.toLowerCase().contains("mac")) {
			return System.getProperty("user.dir") + "/src/test/resources/chromedriver_mac";
		}
		else if (os.toLowerCase().contains("window")) {
			return System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe";
		}
		else {
			SysLogger.log("ERROR: Operating System is not in the defined range - "+os);
		}
		return "";
	}
	
	private synchronized static ChromeOptions getChromeOptions(boolean isHeadless){
		String downloadFilepath = System.getProperty("user.dir") + "/downloads";
		HashMap <String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		ChromeOptions options = new ChromeOptions();		
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--no-proxy-server=socks5");
		options.addArguments("--whitelisted-ips");
		if (isHeadless) {
			options.addArguments("--window-size=1920,1080");
			options.addArguments("--start-maximized");
			options.addArguments("--headless");
		}
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-gpu");
        options.addArguments("--disable-browser-side-navigation");
        options.addArguments("enable-automation");
        options.addArguments("--dns-prefetch-disable");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		return options;
	}
	
	public static synchronized WebDriver getNewBrowser(){
		return createWebDriver();
	}	
}
