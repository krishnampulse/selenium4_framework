package com.application.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.application.factories.ReporterFactory;
import com.relevantcodes.extentreports.ExtentTest;

public class EditAudienceMemberPage extends AudienceMembersPage {
	
	public EditAudienceMemberPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	WebDriver driver;
	ExtentTest testReporter;
}
