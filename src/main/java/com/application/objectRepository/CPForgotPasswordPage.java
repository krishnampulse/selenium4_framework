package com.application.objectRepository;

import org.openqa.selenium.WebDriver;

import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CPForgotPasswordPage {
	WebDriver driver;
	ExtentTest testReporter;
	WebDriverWait wait;
	
	@FindBy(xpath = "//ul[@id='progressSteps']/li[1]/span[1]")
	private WebElement enterUsernameText;
	
	@FindBy(id = "username")
	private WebElement usernameTextBox;
	
	@FindBy(id = "resetPassword")
	private WebElement resetPasswordButton;
	
	@FindBy(xpath = "//*[@id='hasBeenSent']/ul/li[@class='header']/h1")
	private WebElement oneStepAwayText;
	
	@FindBy(id = "goToLogin")
	private WebElement goToLoginButton;
	
	public CPForgotPasswordPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
		wait = new WebDriverWait(driver, 60);
	}
	
	public boolean isEnterUsernameTextPresent() {
		String text = "Enter username/email";
		if (wait.until(ExpectedConditions.textToBePresentInElement(this.enterUsernameText, text))) {
			return true;
		} else
			return false;
	}
	
	public boolean isUsernameTextBoxPresent() {
		if (wait.until(ExpectedConditions.visibilityOf(this.usernameTextBox)).isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public boolean isResetPasswordButtonPresent() {
		if (wait.until(ExpectedConditions.visibilityOf(this.resetPasswordButton)).isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public boolean isOneStepAwayTextPresent() {
		String text = "You're one step away from a new password";
		if (wait.until(ExpectedConditions.textToBePresentInElement(this.oneStepAwayText, text))) {
			return true;
		} else
			return false;
	}
	
	public boolean isGoToLoginButtonPresent() {
		if (wait.until(ExpectedConditions.visibilityOf(this.goToLoginButton)).isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public CPForgotPasswordPage enterUsername(String username) {
		if (this.isUsernameTextBoxPresent()) {
			this.usernameTextBox.sendKeys(username);
			testReporter.log(LogStatus.INFO, username + " Username entered in username text box");
		} else {
			SysLogger.log("Error: Username text box not present on forgot password page");
			testReporter.log(LogStatus.ERROR, "Error: Username text box not present on forgot password page");
		}
		return new CPForgotPasswordPage(this.driver);
	}
	
	public CPForgotPasswordPage clickOnResetPasswordButton() {
		if (this.isResetPasswordButtonPresent()) {
			this.resetPasswordButton.click();
			testReporter.log(LogStatus.INFO, "Clicked on reset password button");
		} else {
			SysLogger.log("Error: Reset password button not present on forgot password page");
			testReporter.log(LogStatus.ERROR, "Error: Reset password button not present on forgot password page");
		}
		return new CPForgotPasswordPage(this.driver);
	}
	
	public LoginPage clickOnGoToLoginButton() {
		if (this.isGoToLoginButtonPresent()) {
			this.goToLoginButton.click();
			testReporter.log(LogStatus.INFO, "Clicked on goToLogin Button");
		} else {
			SysLogger.log("Error: goToLogin button not present on forgot password page");
			testReporter.log(LogStatus.ERROR, "Error: goToLogin button not present on forgot password page");
		}
		return new LoginPage(this.driver);
	}
}
