package com.application.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.application.factories.ReporterFactory;
import com.application.utility.ConfigReader;
import com.application.utility.SysLogger;
import com.relevantcodes.extentreports.ExtentTest;

public class SSOLoginPage {
	WebDriver driver;
	String url;
	String msUrl = "login.microsoftonline.com";
	ExtentTest testReporter;
	WebDriverWait wait;
	
	@FindBy(xpath = "//input[@name='loginfmt' and @type='email']")
	private WebElement userName;
	
	@FindBy(xpath = "//input[@type='submit' and @value='Next']")
	private WebElement nextButton;

	@FindBy(id = "displayName")
	private WebElement emailDisplayName;

	@FindBy(xpath = "//input[@id='idSIButton9' and @type='submit']")
	private WebElement signInButton;

	@FindBy(name = "passwd")
	private WebElement password;
	
	@FindBy(id = "iShowSkip")
	private WebElement skipButton;
	
	@FindBy(xpath = "//input[@id='idSIButton9' and @value='Yes']")
	private WebElement staySignedInBtn;
	
	public SSOLoginPage (String loginUrl, WebDriver driver) {
		this.url = loginUrl;
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
		wait = new WebDriverWait(driver, 60);
	}
	
	public void enterUserName(String userName) {
		if(this.wait.until(ExpectedConditions.visibilityOf(this.userName)).isDisplayed()) {
			this.userName.clear();
			this.userName.sendKeys(userName);
		} else {
			SysLogger.log("Error: Username text box is not displayed on microsoft login page");
		}
	}
	
	public void clickOnNextButton() throws InterruptedException {
		if(this.wait.until(ExpectedConditions.visibilityOf(this.nextButton)).isDisplayed()) {
			this.nextButton.click();
			Thread.sleep(2000);
		} else {
			SysLogger.log("Error: Next button is not displayed on microsoft login page");
		}
	}
	
	public void enterPassword(String password) {
		if(this.wait.until(ExpectedConditions.visibilityOf(this.password)).isDisplayed()) {
			this.password.clear();
			this.password.sendKeys(password);
		} else {
			SysLogger.log("Error: Password text box is not displayed on microsoft login page");
		}
	}
	
	public void clickOnSignInButton() throws InterruptedException {
		if(this.wait.until(ExpectedConditions.visibilityOf(this.signInButton)).isDisplayed()) {
			this.signInButton.click();
		} else {
			SysLogger.log("Error: sign in button is not displayed on microsoft login page");
		}
	}
	
	public void skipValidationIfPresent() throws InterruptedException {
		try {
			if(this.skipButton.isDisplayed()) {
				this.skipButton.click();
			}
		} catch (Exception e) {
			SysLogger.log("Validation page is not present");
		}
	}
	
	public void clickOnStaySignedInIfPresent() throws InterruptedException {
		try {
			if(this.staySignedInBtn.isDisplayed()) {
				this.staySignedInBtn.click();
			}
		} catch (Exception e) {
			SysLogger.log("Stay_signed_in page not present");
		}
	}
	
	public HomePage loginToCpBySSO(String ssoUserName, String ssoPassword) throws InterruptedException {
		this.driver.navigate().to(this.url);
		Thread.sleep(3000); // waiting for redirect to SSO service
		String currentUrl = this.driver.getCurrentUrl();
		if (currentUrl.contains(msUrl)) {
			this.enterUserName(ssoUserName);
			this.clickOnNextButton();
		} else {
			SysLogger.log("Error: SSO service is not redirected to microsoft adfs url");
			Assert.assertTrue(false, "Error: SSO service is not redirected to microsoft adfs url");
		}
		Thread.sleep(5000); // time taken to validate email by microsoft
		if (this.wait.until(ExpectedConditions.visibilityOf(this.emailDisplayName)).isDisplayed()) {
			this.enterPassword(ssoPassword);
			this.clickOnSignInButton();
			Thread.sleep(5000);
			this.skipValidationIfPresent();
			this.clickOnStaySignedInIfPresent();
			Thread.sleep(5000);
			return new HomePage(this.driver);
		} else {
			SysLogger.log("Error: Microsoft login page is not asking for password to enter");
			Assert.assertTrue(false, "Error: Microsoft login page is not asking for password to enter");
		}
		return new HomePage(this.driver);
	}

	public ECHomePage loginToEcBySSO(String ssoUserName, String ssoPassword) throws InterruptedException {
		this.driver.navigate().to(this.url);
		Thread.sleep(3000); // waiting for redirect to SSO service
		String currentUrl = this.driver.getCurrentUrl();
		if (currentUrl.contains(msUrl)) {
			this.enterUserName(ssoUserName);
			this.clickOnNextButton();
		} else {
			SysLogger.log("Error: SSO service is not redirected to microsoft adfs url");
			Assert.assertTrue(false, "Error: SSO service is not redirected to microsoft adfs url");
		}
		Thread.sleep(5000); // time taken to validate email by microsoft
		if (this.wait.until(ExpectedConditions.visibilityOf(this.emailDisplayName)).isDisplayed()) {
			this.enterPassword(ssoPassword);
			this.clickOnSignInButton();
			Thread.sleep(5000);
			this.skipValidationIfPresent();
			this.clickOnStaySignedInIfPresent();
			Thread.sleep(5000);
			return new ECHomePage(this.driver);
		} else {
			SysLogger.log("Error: Microsoft login page is not asking for password to enter");
			Assert.assertTrue(false, "Error: Microsoft login page is not asking for password to enter");
		}
		return new ECHomePage(this.driver);
	}
}
