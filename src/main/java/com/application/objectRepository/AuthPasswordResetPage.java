package com.application.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;
import com.relevantcodes.extentreports.ExtentTest;

public class AuthPasswordResetPage {
	
	WebDriver driver;
	ExtentTest testReporter;
	
	@FindBy (xpath = "//div[@class='inner-header' and text()='Reset password']")
	private WebElement resetPasswordText;
	
	@FindBy (id = "id_new_password1")
	private WebElement password1;
	
	@FindBy (id = "id_new_password2")
	private WebElement password2;
	
	@FindBy (xpath = "//button[@type='submit' and text()='Confim']")
	private WebElement submitButton;
	
	@FindBy (xpath = "//div[@class='inner-header' and text()='Password reset successfully.']")
	private WebElement passwordResetSuccessText;

	public AuthPasswordResetPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
	}
	
	public boolean isResetPasswordTextPresent() {
		if (this.resetPasswordText.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isPasswordResetSuccessTextPresent() {
		if (this.passwordResetSuccessText.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public AuthPasswordResetPage enterNewPassword(String newPassword) {
		if (this.password1.isDisplayed()) {
			this.password1.click();
			this.password1.sendKeys(newPassword);
		} else {
			SysLogger.log("Error: Password field not present or not displayed");
		}
		return new AuthPasswordResetPage(this.driver);
	}
	
	public AuthPasswordResetPage enterConfirmPassword(String newPassword) {
		if (this.password2.isDisplayed()) {
			this.password2.click();
			this.password2.sendKeys(newPassword);
		} else {
			SysLogger.log("Error: Confirm Password field not present or not displayed");
		}
		return new AuthPasswordResetPage(this.driver);
	}
	
	public AuthPasswordResetPage clickOnConfirmButton() {
		if (this.submitButton.isDisplayed()) {
			this.submitButton.click();
		} else {
			SysLogger.log("Error: Confirm Button field not present or not displayed");
		}
		return new AuthPasswordResetPage(this.driver);
	}
}
