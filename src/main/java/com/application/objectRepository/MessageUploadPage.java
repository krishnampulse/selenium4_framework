package com.application.objectRepository;

import org.openqa.selenium.WebDriver;

import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

public class MessageUploadPage {
	WebDriver driver;
	ExtentTest testReporter;
	
	@FindBy(id = "file")
	private WebElement chooseFileInput;
	
	@FindBy(id = "upload")
	private WebElement uploadButton;
	
	public MessageUploadPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
	}
	
	public MessageUploadPage chooseFileToUpload(String filePath) {
		if (this.chooseFileInput.isDisplayed()) {
			this.chooseFileInput.sendKeys(filePath);
			return new MessageUploadPage(this.driver);
		} else {SysLogger.log("Error: choose file input box not displayed on page");}
		return new MessageUploadPage(this.driver);
	}
	
	public UploadProcessingPage clickOnUploadButton() {
		if (this.uploadButton.isDisplayed()) {
			this.uploadButton.click();
			return new UploadProcessingPage(this.driver);
		} else {SysLogger.log("Error: upload button not displayed on page");}
		return new UploadProcessingPage(this.driver);
	}
}
