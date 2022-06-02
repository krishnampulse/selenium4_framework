package com.application.objectRepository;

import org.openqa.selenium.WebDriver;

import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPage {

	WebDriver driver;
	ExtentTest testReporter;

	@FindBy(id = "email")
	@CacheLookup
	private WebElement userName;

	@FindBy(id = "password")
	@CacheLookup
	private WebElement password;

	@FindBy(id = "login_button")
	@CacheLookup
	private WebElement loginButton;

	@FindBy(xpath = "//*[@id='remmeber_user_password']/label")
	@CacheLookup
	private WebElement rememberMe;

	@FindBy(linkText = "Forgot Password")
	@CacheLookup
	private WebElement forgotPassword;
	
	@FindBy(xpath = "//*[@id='content']/div/div/p[1]")
	private WebElement accessDeniedTextMessageAfterLogin;
	
	@FindBy(id = "userLogout")
	private WebElement backToLoginButtonOnAccessDeniedPage;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
	}

	public void enterUserName(String userName) {
		if (this.userName.isDisplayed() && this.userName.isEnabled()) {
			testReporter.log(LogStatus.INFO, "UserName textbox found and going to fill user name");
			this.userName.clear();
			this.userName.sendKeys(userName);
			testReporter.log(LogStatus.INFO, "user name '"+userName+"' entered in username textbox");
		} else
			SysLogger.log("Error: Element 'user name' not Displayed or not Enabled");
	}

	public void enterPassword(String password) {
		if (this.password.isDisplayed() && this.password.isEnabled()) {
			testReporter.log(LogStatus.INFO, "password textbox found and going to fill password");
			this.password.clear();
			this.password.sendKeys(password);
			testReporter.log(LogStatus.INFO, "password '"+password+"' entered in password textbox");
		} else {
			SysLogger.log("Error: Element 'password' not Displayed or not Enabled");
		}
	}

	public void clickOnLoginButton() {
		if (this.loginButton.isDisplayed() && this.loginButton.isEnabled()) {
			testReporter.log(LogStatus.INFO, "Login button found and going to click login button");
			loginButton.click();
			testReporter.log(LogStatus.INFO, "Login button clicked");
		} else {
			SysLogger.log("Error: Element 'login button' not Displayed or not Enabled");
		}
	}

	public HomePage login(String userName, String password) {
		enterUserName(userName);
		enterPassword(password);
		clickOnLoginButton();
		String url = driver.getCurrentUrl();
		String title = driver.getTitle();
		SysLogger.log("Current CP url is "+url+" and title is "+title);
		if (url.endsWith("home") && title.equalsIgnoreCase("Home")) {
			testReporter.log(LogStatus.INFO, "Login successfully with user name '"+userName+"' and password '"+password+"'");
			return new HomePage(driver);
		}
		if (url.endsWith("forgot") && title.equalsIgnoreCase("Forgot Password")) {
			CPForgotPasswordPage forgotPage = new CPForgotPasswordPage(this.driver);
			Assert.assertTrue(forgotPage.isEnterUsernameTextPresent());
			Assert.assertTrue(forgotPage.isUsernameTextBoxPresent());
			testReporter.log(LogStatus.ERROR, "Password Expired: Your password is older than 90 days, Please reset your password. ");
			Assert.assertTrue(false, "Password Expired: Your password is older than 90 days, Please reset your password. ");
			return new HomePage(driver);
		}
		if (title.equalsIgnoreCase("Access Denied Error")) {
			String message = this.accessDeniedTextMessageAfterLogin.getText();
			testReporter.log(LogStatus.INFO, message);
			Assert.assertTrue(false, message);
		}
		return new HomePage(driver);
	}

	public boolean isRememberMePresent() {
		if (this.rememberMe.isDisplayed() && this.rememberMe.isEnabled()) {
			return true;
		} else {
			SysLogger.log("Error: Remember me text not present on login page");
			return false;
		}
	}

	public boolean isForgotPasswordPresent() {
		if (this.forgotPassword.isDisplayed() && this.forgotPassword.isEnabled()) {
			return true;
		}
		return false;
	}

}
