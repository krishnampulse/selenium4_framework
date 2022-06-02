package com.application.objectRepository;

import org.openqa.selenium.WebDriver;

import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

public class UploadProcessingPage {
	WebDriver driver;
	ExtentTest testReporter;
	
	@FindBy(id = "upload_successful")
	private WebElement uploadSuccessfulButton;
	
	@FindBy(id = "fail")
	private WebElement failedRowsButton;
	
	@FindBy(id = "success")
	private WebElement successRowsButton;
	
	@FindBy(xpath = "//button[@type='button' and @role='button']")
	private WebElement successOKButton;
	
	public UploadProcessingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
	}
	
	public UploadProcessingPage clickOnUploadSuccessfulButton() {
		if (this.uploadSuccessfulButton.isDisplayed()) {
			this.uploadSuccessfulButton.click();
			return new UploadProcessingPage(this.driver);
		} else {SysLogger.log("Error: upload Successful Button not displayed on page");}
		return new UploadProcessingPage(this.driver);
	}
	
	public CommunicationPage clickOnSuccessOKButton() {
		if (this.successOKButton.isDisplayed()) {
			this.successOKButton.click();
			return new CommunicationPage(this.driver);
		} else {SysLogger.log("Error: success OK Button not displayed on page");}
		return new CommunicationPage(this.driver);
	}
}
