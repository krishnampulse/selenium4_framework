package com.application.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.application.factories.ReporterFactory;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AuthServiceLogoutPage {
	private WebDriver driver;
	private ExtentTest testReporter;
	private WebDriverWait wait;
	
	@FindBy(xpath = "//button[@type='submit' and contains(text(), 'Back to Login')]")
	private WebElement backToLoginButton;
	
	@FindBy(xpath = "//div[@class='inner-header' and text()='Signed Out']")
	private WebElement signedOutText;
	
	public AuthServiceLogoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
		wait = new WebDriverWait(driver, 120);
	}
	
	public AuthServiceLogoutPage verifyPageTitleIsLogoutPage() {
		String title = this.driver.getTitle();
		Assert.assertEquals(title, "Logout Page", "Error: Page title is not matched");
		return this;
	}
	
	public boolean isSignedOutTextPresent() {
		if(this.signedOutText.isDisplayed()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public AuthServiceLogoutPage verifySignedOutTextPresent() {
		if(this.isSignedOutTextPresent()) {
			return this;
		}
		else {
			Assert.assertTrue(false, "Error: Signed Out text is not present on logout page");
		}
		return this;
	}
	
	public AuthServiceLoginPage clickOnBackToLoginButton() {
		if(this.wait.until(ExpectedConditions.visibilityOf(this.backToLoginButton)).isDisplayed()) {
			this.backToLoginButton.click();
			this.testReporter.log(LogStatus.INFO, "clicked on back to login button");
			return new AuthServiceLoginPage(this.driver);
		}
		else {
			this.testReporter.log(LogStatus.ERROR, "Error: back to login button is not present on logout page");
			Assert.assertTrue(false, "Error: back to login button is not present on logout page");
		}
		return new AuthServiceLoginPage(this.driver);
	}
}
