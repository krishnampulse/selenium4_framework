package com.application.objectRepository;

import org.openqa.selenium.WebDriver;

import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

public class MyAccountPage {
	WebDriver driver;
	ExtentTest testReporter;
	
	@FindBy (xpath = "//a[@class='custom_fields']")
	private WebElement customFieldsPageLink;
	
	@FindBy (xpath = "//a[@class = 'event_definitions']")
	private WebElement eventDefinitionPageLink;

	@FindBy (xpath = "//a[@class = 'Subscription_Preferences']")
	private WebElement subscriptionPreferencePageLink;
	
	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
	}
	
	public boolean isCustomFieldsPageButtonPresent() {
		if (this.customFieldsPageLink.isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public boolean isEventDefinitionPageButtonPresent() {
		if (this.eventDefinitionPageLink.isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public boolean isSubscriptionPreferencePageButtonPresent() {
		if (this.subscriptionPreferencePageLink.isDisplayed()) {
			return true;
		} else
			return false;
	}

	public CustomFieldsPage goToCustomFieldsPage() {
		
		if (this.isCustomFieldsPageButtonPresent()) {
			this.customFieldsPageLink.click();
		} else {
			SysLogger.log("Error: Custom Fields page link not present");
		}
		return new CustomFieldsPage(this.driver);
	}
	
	public EventDefinitionsPage goToEventDefinitionPage() {
		
		if (this.isEventDefinitionPageButtonPresent()) {
			this.eventDefinitionPageLink.click();
		} else {
			SysLogger.log("Error: Event definition page link not present");
		}
		return new EventDefinitionsPage(this.driver);
	}
	
	public CPSubscriptionPreferencePage goToSubscriptionPreferencePage() {

		if(this.isSubscriptionPreferencePageButtonPresent()) {
			this.subscriptionPreferencePageLink.click();
		} else {
			SysLogger.log("Error: Subscription Preference page link is not present");
		}
		return new CPSubscriptionPreferencePage(this.driver);
	}
}
