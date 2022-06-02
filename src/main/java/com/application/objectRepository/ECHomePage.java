package com.application.objectRepository;

import org.openqa.selenium.WebDriver;

import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ECHomePage {
	WebDriver driver;
	ExtentTest testReporter;
	WebDriverWait wait;
	
	@FindBy(xpath = "//img[@class='navbar-logo' and contains(@src, 'Engagement-Console')]")
	private WebElement ecLogo;
	
	@FindBy(xpath = "//div[@id='navbar']//span[@class='nav-user-identifier']")
	private WebElement userDropDown;
	
	@FindBy (xpath = "//div[contains(@class, 'popup user-options-popup')]//li[@class='popup-option']")
	private WebElement logOutLink;

	public ECHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
		wait = new WebDriverWait(driver, 60);
	}

	public boolean isECLogoPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.ecLogo)).isDisplayed()) {
			return true;
		} else
			return false;
	}

	public boolean isUserDropDownPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.userDropDown)).isDisplayed()) {
			return true;
		} else
			return false;
	}

	public boolean isLogOutLinkPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.logOutLink)).isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public AuthServiceLogoutPage logOut() {
		if (this.isUserDropDownPresent()) {
			this.userDropDown.click();
			if(this.isLogOutLinkPresent()) {
				this.logOutLink.click();
			}
			else{
				SysLogger.log("Error: Logout option on user drop down on EC not present");
			}
		} else {
			SysLogger.log("Error: User Dropdown on EC contacts page not present");
		}
		return new AuthServiceLogoutPage(this.driver);
	}

}
