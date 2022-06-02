package com.application.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.application.factories.ReporterFactory;
import com.relevantcodes.extentreports.ExtentTest;

public class UserServiceAddNewServicePage {
	WebDriver driver;
	ExtentTest testReporter;
	
	@FindBy(id = "id_service_name")
	private WebElement serviceNameInputBox;
	
	@FindBy(name = "_save")
	private WebElement saveButton;
	
	@FindBy(name = "_addanother")
	private WebElement saveAndAddAnotherButton;
	
	@FindBy(name = "_continue")
	private WebElement saveAndContinueEditingButton;
	
	public UserServiceAddNewServicePage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
	}
	
	public UserServiceAddNewServicePage enterServiceName(String serviceName) {
		if (this.serviceNameInputBox.isDisplayed()) {
			this.serviceNameInputBox.clear();
			this.serviceNameInputBox.sendKeys(serviceName);
			return new UserServiceAddNewServicePage(this.driver);
		}
		else {
			return new UserServiceAddNewServicePage(this.driver);
		}
	}
	
	public UserServiceServicesPage clickOnSaveButton() {
		if (this.saveButton.isDisplayed()) {
			this.saveButton.click();
			return new UserServiceServicesPage(this.driver);
		}
		else {
			return new UserServiceServicesPage(this.driver);
		}
	}
	
	public UserServiceAddNewServicePage clickOnSaveAndAddAnotherButton() {
		if (this.saveAndAddAnotherButton.isDisplayed()) {
			this.saveAndAddAnotherButton.click();
			return new UserServiceAddNewServicePage(this.driver);
		}
		else {
			return new UserServiceAddNewServicePage(this.driver);
		}
	}
	
	public UserServiceAddNewServicePage clickOnSaveAndContinueEditingButton() {
		if (this.saveAndContinueEditingButton.isDisplayed()) {
			this.saveAndContinueEditingButton.click();
			return new UserServiceAddNewServicePage(this.driver);
		}
		else {
			return new UserServiceAddNewServicePage(this.driver);
		}
	}
}
