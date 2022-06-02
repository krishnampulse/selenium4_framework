package com.application.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.application.factories.ReporterFactory;
import com.relevantcodes.extentreports.ExtentTest;

public class ImportSegmentPage {
	
	WebDriver driver;
	ExtentTest testReporter;
	
	public ImportSegmentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
	}
}
