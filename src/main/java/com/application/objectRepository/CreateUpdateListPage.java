package com.application.objectRepository;

import org.openqa.selenium.JavascriptExecutor;

import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;

public class CreateUpdateListPage {
	
	WebDriver driver;
	ExtentTest testReporter;
	WebDriverWait wait;
	Actions act;
	JavascriptExecutor js;
	
	@FindBy(id = "name")
	private WebElement listNameTextBox;
	
	@FindBy(id = "useEmail")
	private WebElement enableEmailChannelCheckBox;
	
	@FindBy(id = "useSms")
	private WebElement enableSMSChannelCheckBox;
	
	@FindBy(id = "add_address")
	private WebElement addAddress;
	
	@FindBy(id = "confirm_email_address")
	private WebElement confirmEmailAddress;
	
	@FindBy(xpath = "//span[contains(text(),'Send Confirmation')]")
	private WebElement sendConfirmation;
	
	@FindBy(xpath = "//span[contains(text(),'OK')]")
	private WebElement okButton;
	
	@FindBy(id = "submit")
	private WebElement createListButton;
	
	public CreateUpdateListPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		act = new Actions(driver);
		js = (JavascriptExecutor) driver;
		testReporter = ReporterFactory.getTest();
		wait = new WebDriverWait(driver, 120);
	}
	
	public CreateUpdateListPage enterListName(String listName) {
		if(this.listNameTextBox.isDisplayed()) {
			this.listNameTextBox.clear();
			this.listNameTextBox.sendKeys(listName);
		}
		else {
			SysLogger.log("Error : List name text box not present");
		}
		return new CreateUpdateListPage(this.driver);
	}
	
	public CreateUpdateListPage disableAlreadyEnabledEmailChannelCheckBox() {
		if(this.enableEmailChannelCheckBox.isDisplayed()) {
			this.enableEmailChannelCheckBox.click();
		}
		else {
			SysLogger.log("Error : Enable Email Channel Check Box not present");
		}
		return new CreateUpdateListPage(this.driver);
	}
	
	public CreateUpdateListPage disableAlreadyEnabledSMSChannelCheckBox() {
		if(this.enableSMSChannelCheckBox.isDisplayed()) {
			this.enableSMSChannelCheckBox.click();
		}
		else {
			SysLogger.log("Error : Enable SMS Channel Check Box not present");
		}
		return new CreateUpdateListPage(this.driver);
	}
	
	public CreateUpdateListPage addFromEmailAddress(String emailId) {
		this.addAddress.click();
		this.confirmEmailAddress.sendKeys(emailId);
		this.sendConfirmation.click();
		this.okButton.click();
		return new CreateUpdateListPage(this.driver);
		
	}
	
	public AudienceListsPage clickOnCreateListButton() throws InterruptedException {
		if(this.createListButton.isDisplayed()) {
			Thread.sleep(6000);
			SysLogger.log("Inside clickOnCreateListButton() method ...");
			act.moveToElement(this.createListButton).build().perform();
			this.createListButton.click();
//			js.executeScript("arguments[0].click();", this.createListButton);
			Thread.sleep(5000);
		}
		else {
			SysLogger.log("Error : Create list button not present");
		}
		return new AudienceListsPage(this.driver);
	}
}
