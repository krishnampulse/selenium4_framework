package com.application.testCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.application.factories.BrowserFactory;
import com.application.factories.ReporterFactory;
import com.application.objectRepository.HomePage;
import com.application.utility.SysLogger;
import com.application.utility.TextNow;
import com.application.utility.UtilityMethods;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class UnitTest extends BaseTest {
	
	WebDriver driver;
	
	@Test(groups = { "sso_unit_test" })
	public void unit_test1() throws Exception {
		this.driver = BrowserFactory.getBrowser();
		TextNow textNow = new TextNow(this.driver);
		String message = textNow.readLastMT("krishna.yadav12", "mPulse@4321", "24192");
		System.out.println("Message is - "+message);
	}
}