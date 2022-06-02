package com.application.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.application.factories.ReporterFactory;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class UnsubscribeAudiencePage {
	
	WebDriver driver;
	ExtentTest testReporter;
	
	@FindBy (id="firstRowIsHeader")
	WebElement firstRowIsHeaderCheckbox;
	
	@FindBy (id="allowPipeDelimiter")
	WebElement allowPipeDelimiterCheckbox;
	
	@FindBy (id="emailChannel")
	WebElement emailChannelCheckbox;
	
	@FindBy (id="smsChannel")
	WebElement smsChannelCheckbox;
	
	@FindBy (id="appmailChannel")
	WebElement appmailChannelCheckbox;
	
	@FindBy (id="pnChannel")
	WebElement pnChannelCheckbox;
	
	@FindBy (id ="file")
	private WebElement browseFileButton;
	
	@FindBy (id ="upload")
	private WebElement uploadButton;
	
	public UnsubscribeAudiencePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
	}
	
	public boolean isSmsChannelCheckboxPresent() {
		if(this.smsChannelCheckbox.isDisplayed()) {
			return true;
		}
		else return false;
	}
	
	public boolean isEmailChannelCheckboxPresent() {
		if(this.emailChannelCheckbox.isDisplayed()) {
			return true;
		}
		else return false;
	}

	public boolean isAppmailChannelCheckboxPresent() {
		if(this.appmailChannelCheckbox.isDisplayed()) {
			return true;
		}
		else return false;
	}
	
	public boolean isPnChannelCheckboxPresent() {
		if(this.pnChannelCheckbox.isDisplayed()) {
			return true;
		}
		else return false;
	}
	
	public boolean isFirstRowIsHeaderCheckboxPresent() {
		if(this.firstRowIsHeaderCheckbox.isDisplayed()) {
			return true;
		}
		else return false;
	}
	
	public boolean isAllowPipeDelimiterCheckboxPresent() {
		if(this.allowPipeDelimiterCheckbox.isDisplayed()) {
			return true;
		}
		else return false;
	}
	
	public boolean isBrowseFileButtonPresent() {
		if(this.browseFileButton.isDisplayed()) {
			return true;
		}
		else return false;
	}
	
	public boolean isUploadButtonPresent() {
		if(this.uploadButton.isDisplayed()) {
			return true;
		}
		else return false;
	}
	
	public UnsubscribeAudiencePage selectFirstRowIsHeaderCheckbox() {
		if(this.isFirstRowIsHeaderCheckboxPresent()) {
			this.firstRowIsHeaderCheckbox.click();
			testReporter.log(LogStatus.INFO, "Clicked on first row is header checkbox");
			return new UnsubscribeAudiencePage(this.driver);
		}
		else Assert.assertTrue(false, "Error : first row is header checkbox is not present");
		return new UnsubscribeAudiencePage(this.driver);
	}
	
	public UnsubscribeAudiencePage selectAllowPipeDelimiterCheckbox() {
		if(this.isAllowPipeDelimiterCheckboxPresent()) {
			this.allowPipeDelimiterCheckbox.click();
			testReporter.log(LogStatus.INFO, "Clicked on allow pipe delimiter checkbox");
			return new UnsubscribeAudiencePage(this.driver);
		}
		else Assert.assertTrue(false, "Error : allow pipe delimiter checkbox is not present");
		return new UnsubscribeAudiencePage(this.driver);
	}
	
	public UnsubscribeAudiencePage selectEmailChannelCheckbox() {
		if(this.isEmailChannelCheckboxPresent()) {
			this.emailChannelCheckbox.click();
			testReporter.log(LogStatus.INFO, "Clicked on email checkbox");
			return new UnsubscribeAudiencePage(this.driver);
		}
		else Assert.assertTrue(false, "Error : email checkbox is not present");
		return new UnsubscribeAudiencePage(this.driver);
	}
	
	public UnsubscribeAudiencePage selectSmsChannelCheckbox() {
		if(this.isSmsChannelCheckboxPresent()) {
			this.smsChannelCheckbox.click();
			testReporter.log(LogStatus.INFO, "Clicked on sms checkbox");
			return new UnsubscribeAudiencePage(this.driver);
		}
		else Assert.assertTrue(false, "Error : sms checkbox is not present");
		return new UnsubscribeAudiencePage(this.driver);
	}
	
	public UnsubscribeAudiencePage selectAppmailChannelCheckbox() {
		if(this.isAppmailChannelCheckboxPresent()) {
			this.appmailChannelCheckbox.click();
			testReporter.log(LogStatus.INFO, "Clicked on appmail checkbox");
			return new UnsubscribeAudiencePage(this.driver);
		}
		else Assert.assertTrue(false, "Error : appmail checkbox is not present");
		return new UnsubscribeAudiencePage(this.driver);
	}
	
	public UnsubscribeAudiencePage selectPnChannelCheckbox() {
		if(this.isPnChannelCheckboxPresent()) {
			this.pnChannelCheckbox.click();
			testReporter.log(LogStatus.INFO, "Clicked on pn checkbox");
			return new UnsubscribeAudiencePage(this.driver);
		}
		else Assert.assertTrue(false, "Error : pn checkbox is not present");
		return new UnsubscribeAudiencePage(this.driver);
	}
	
	public MappingColumnsPage clickOnUploadButton(String filePath) throws InterruptedException {
		this.browseFileButton.sendKeys(filePath);
		testReporter.log(LogStatus.INFO, "CSV file from path "+filePath+" is selected");
		this.uploadButton.click();
		Thread.sleep(5000);
		testReporter.log(LogStatus.INFO, "Clicked on upload button");
		return new MappingColumnsPage(this.driver);
	}
	

}
