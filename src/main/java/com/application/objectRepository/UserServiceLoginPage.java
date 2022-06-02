package com.application.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;

public class UserServiceLoginPage {
	WebDriver driver;
	ExtentTest testReporter;
	
	@FindBy(id = "id_username")
	private WebElement userName;

	@FindBy(id = "id_password")
	private WebElement password;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement loginButton;

	@FindBy(xpath = "//*[@id='site-name']/a[@href='/admin/']")
	private WebElement djangoAdminLogo;
	
	@FindBy(xpath = "//p[@class='errornote']")
	private WebElement errorNote;
	
	public UserServiceLoginPage (WebDriver driver) {
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
	
	public boolean isDjangoAdminLogoDisplay() {
		if (this.djangoAdminLogo.isDisplayed()) {
			return true;
		}
		else return false;
	}

	public UserServiceHomePage login(String userName, String password) {
		enterUserName(userName);
		enterPassword(password);
		clickOnLoginButton();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (this.isDjangoAdminLogoDisplay()) {
			testReporter.log(LogStatus.INFO, "Login successfully with user name '"+userName+"' and password '"+password+"'");
			SysLogger.log("Login successfully with user name '"+userName+"' and password '"+password+"'");
			return new UserServiceHomePage(driver);
		}
		else {
			testReporter.log(LogStatus.ERROR, "Login failed with user name '"+userName+"' and password '"+password+"'");
			SysLogger.log("Login failed with user name '"+userName+"' and password '"+password+"'");
			return new UserServiceHomePage(driver);
		}
	}
	
	public String getErrorMessage() {
		if (this.errorNote.isDisplayed()) {
			String errorMessage = this.errorNote.getText();
			testReporter.log(LogStatus.INFO, "Error Message on Page - "+errorMessage);
			SysLogger.log("Error Message on Page - "+errorMessage);
			return errorMessage;
		}
		else {
			SysLogger.log("Error message is not displayed on Page");
			return null;
		}
	}
}
