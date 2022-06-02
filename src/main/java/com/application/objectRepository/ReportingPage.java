package com.application.objectRepository;

import org.openqa.selenium.WebDriver;

import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

public class ReportingPage {
	
	WebDriver driver;
	ExtentTest testReporter;
	
	@FindBy(xpath = "//tbody[@id='messages_report']/tr[@class='channel-sms']/td[2]/div/a")
	private WebElement smsReportLink;
	
	@FindBy(xpath = "//tbody[@id='messages_report']/tr[@class='channel-email']/td[2]/div/a")
	private WebElement emailReportLink;

	public ReportingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
	}
	
	public boolean isSmsReportLinkPresent() {
		if(this.smsReportLink.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isEmailReportLinkPresent() {
		if(this.emailReportLink.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public SMSMessageReportPage clickOnSMSReportLink() throws InterruptedException {
		if(this.isSmsReportLinkPresent()) {
			this.smsReportLink.click();
			Thread.sleep(5000);
		}
		else {
			SysLogger.log("Error: Not able to click on SMS Report link");
		}
		return new SMSMessageReportPage(this.driver);
	}
	
	public EmailMessageReportPage clickOnEmailReportLink() throws InterruptedException {
		if(this.isEmailReportLinkPresent()) {
			this.emailReportLink.click();
			Thread.sleep(5000);
		}
		else {
			SysLogger.log("Error: Not able to click on Email Report link");
		}
		return new EmailMessageReportPage(this.driver);
	}
}
