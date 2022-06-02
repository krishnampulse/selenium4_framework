package com.application.testCases;

import com.application.factories.BrowserFactory;
import com.application.factories.DatabaseFactory;
import com.application.factories.ReporterFactory;
import com.application.objectRepository.AuthServiceLoginPage;
import com.application.objectRepository.AuthServiceLogoutPage;
import com.application.objectRepository.CCHomePage;
import com.application.objectRepository.CCLoginPage;
import com.application.objectRepository.HomePage;
import com.application.objectRepository.UserServiceHomePage;
import com.application.objectRepository.UserServiceLoginPage;
import com.application.utility.ConfigReader;
import com.application.utility.EmailSendGrid;
import com.application.utility.SysLogger;
import com.application.utility.TestNGMaker;
import com.application.utility.ThreadSafeNumber;
import com.application.utility.UtilityMethods;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public class BaseTest {
	
	private int passedTestsCounter;
	private int failedTestsCounter;
	private int totalSuiteRun;
	private static int failedTestRerunCount = 0;
	
	@BeforeSuite(alwaysRun = true)
	public synchronized void startSuite(ITestContext context) throws Exception {
		SysLogger.log("**************************************************************************************");
		SysLogger.log(" ##### Test Suite Started On "+ConfigReader.getENVConfigValue().toUpperCase()+" ##### ");
		SysLogger.log("Inside BeforeSuite");
		
		String xmlFileName = context.getSuite().getXmlSuite().getFileName();
		xmlFileName = xmlFileName.substring(xmlFileName.lastIndexOf("/")+1);
		ReporterFactory.setTestNgXmlFileName(xmlFileName);
		ReporterFactory.nameToTestMap.clear();
		ReporterFactory.threadToExtentTestMap.clear();
		SysLogger.log("***************************  Running testNg xml file name - "+xmlFileName+" - ***************************");
		if(ConfigReader.getConfig("allowAllMemberToDelete").equalsIgnoreCase("true")) {
			String accountId = ConfigReader.getConfig("account_id");
			SysLogger.log("Deleting All audience member by audience api for account id "+accountId);
//			AudienceApi.deleteAllMemberById(accountId);
		}
		if(ConfigReader.getConfig("allowAllCampaignToRetire").equalsIgnoreCase("true")) {
			// Retiring all active campaign because previously active campaign can impact on desired result.
			// it is config based so can be turned off/on 
			String accountId = ConfigReader.getConfig("account_id");
			SysLogger.log("Retiring all active campaign for account id "+accountId);
			UtilityMethods.retireAllActiveCampaignByDBUpdate(accountId);
		}
	}

	@AfterSuite(alwaysRun = true)
	public synchronized void endSuite(ITestContext context) throws IOException, InterruptedException {
		ReporterFactory.closeReport();
		
		double startTime = context.getStartDate().getTime();
		double endTime = context.getEndDate().getTime();
		double totalTimeTakenInMS = endTime-startTime;
		float totalTimeTakenInSeconds = (float) (totalTimeTakenInMS/1000);
	    DecimalFormat dft = new DecimalFormat("0.00");
	    String formatedTime = dft.format(totalTimeTakenInSeconds);
	    
		String xmlFileName = context.getSuite().getXmlSuite().getFileName();		
		xmlFileName = xmlFileName.substring(xmlFileName.lastIndexOf("/")+1);
		String failedCasesXmlFileName = xmlFileName.replace(".xml", "_")+"failed_cases_rerun.xml";
		
		String result = getTestSuiteResult(context.getSuite());
		StringBuilder str = new StringBuilder("Hi Team,\n");
		str.append("\n");
		str.append("Please find Automation suite run report!\n");
		str.append("\n");
		str.append("Note:- This is Computer generated Email message");
		str.append("\n");
		str.append("*******************************************");
		str.append("\nTotal Time Taken to run suite - "+formatedTime+"s");
		str.append("\nTotal number of test suite run - "+this.totalSuiteRun);
		str.append("\nTotal Passed test cases - "+(this.passedTestsCounter));
		str.append("\nTotal Failed test cases - "+(this.failedTestsCounter));
		str.append("\n");
		str.append("*******************************************");
		
		if(failedTestRerunCount == 0 && failedTestsCounter >= 1 
				&& ConfigReader.allowToRerunFailedTest().equalsIgnoreCase("true")) {
			str.append("\n");
			str.append("Note - Some test cases have failed, re-running failed test cases one more time, "
					+ "you will receive final test report in some time. Re-run failed test report name will be like "+failedCasesXmlFileName);
		}
		str.append("\n"+result);
		String textMessage = str.toString();
		SysLogger.log(textMessage);
		DateFormat df = new SimpleDateFormat("dd-MMM-YYYY");
	    Date date = new Date();
		String subject = "["+ConfigReader.getENVConfigValue().toUpperCase()+"] mPulse Automation Suite Report | Date - "+df.format(date)+" | XmlFile - "+xmlFileName;
		String filePath = System.getProperty("user.dir")+"/testReports";
		String fileName = UtilityMethods.getTheNewestFile(filePath, "html").getName();
		filePath = filePath +"/"+fileName;
		if(ConfigReader.allowToSendEmail().equalsIgnoreCase("true")) {
			EmailSendGrid.sendEmailMessage(subject, textMessage, filePath, fileName);
		}
		
		SysLogger.log(" ##### Test Suite Ended ##### ");
		
		if(failedTestRerunCount == 0 && failedTestsCounter >= 1 
				&& ConfigReader.allowToRerunFailedTest().equalsIgnoreCase("true") ) {
			
			SysLogger.log("Re running only failed test cases ..... ");
					
			TestNGMaker testNg = new TestNGMaker(getAllFailedTestClassAndMethodsName(context.getSuite()));
			testNg.makeTestNGFile(failedCasesXmlFileName.replace(".xml", ""));
			
			Thread.sleep(10000); // waiting for 10 seconds before starting 2nd Suite
			
			failedTestRerunCount++;
			passedTestsCounter=0;
			failedTestsCounter=0;
			totalSuiteRun=0;
			
			TestNGRunnerTest runner = new TestNGRunnerTest();
			runner.runTestSuite(failedCasesXmlFileName);
		}
		failedTestRerunCount=0;
	}

	@BeforeTest(alwaysRun = true)
	public synchronized void startTest(final ITestContext testContext) {

		SysLogger.log("----- Test '" + testContext.getName() + "' Started -----");
	}

	@AfterTest(alwaysRun = true)
	public synchronized void endTest(final ITestContext testContext) {

		SysLogger.log("----- Test '" + testContext.getName() + "' Ended -----");
		BrowserFactory.closeWebDriver();
		DatabaseFactory.closeConnection();
		DatabaseFactory.closeAmgDBConnection();
		DatabaseFactory.closeTxnDBConnection();
	}

	@BeforeMethod(alwaysRun = true)
	public synchronized void startMethod(Method method) {
		ReporterFactory.getTest(method.getName(), "Test Method '" + method.getName() + "' Started");
		SysLogger.log("**** Test Method '" + method.getName() + "' Started ****");
		ExtentTest testReporter = ReporterFactory.getTest();
		if (ConfigReader.getConfig("allowRandomPhone").equalsIgnoreCase("false")) {
			testReporter.log(LogStatus.INFO, "Inside start test method, deleting used phone numbers - "+Arrays.toString(ThreadSafeNumber.getAllPhone()));
//			AudienceApi.deleteMemberByPhoneNumber2(ThreadSafeNumber.getAllPhone());
			ThreadSafeNumber.resetNextPhoneCounter();
			testReporter.log(LogStatus.INFO, "All used phone numbers are deleted");
		}
	}

	@AfterMethod(alwaysRun = true)
	public synchronized void endMethod(ITestResult result) {
		double startTime = result.getStartMillis();
		double endTime = result.getEndMillis();
		double totalTimeTakenInMS = endTime-startTime;
		float totalTimeTakenInSeconds = (float) (totalTimeTakenInMS/1000);
	    DecimalFormat df = new DecimalFormat("0.00");
	    String formatedTime = df.format(totalTimeTakenInSeconds);
	    
	    
		ExtentTest testReporter = ReporterFactory.getTest();
		if (ITestResult.FAILURE == result.getStatus()) {
			SysLogger.log(">>>>>>>> Error :- Test Method '" + result.getName() + "' Failed <<<<<<<<");
			testReporter.log(LogStatus.FAIL, "**** Test Method '" + result.getMethod().getMethodName().replace("_", " ") + "' Failed ****");
			StringWriter sw = new StringWriter(); 
			result.getThrowable().printStackTrace(new PrintWriter(sw));
			String stacktrace = sw.toString(); 
			// Write the stack trace to extent reports 
			testReporter.log(LogStatus.FAIL, "<span class='label failure'>" + result.getName() + "</span>", "<pre>Stacktrace:\n" + stacktrace + "</pre>");
			String screenshot_path = UtilityMethods.getScreenShot("Method_" + result.getName() + "_onFailure");
			String image = testReporter.addScreenCapture(screenshot_path);
			testReporter.log(LogStatus.FAIL, result.getMethod().getMethodName(), image);
			testReporter.log(LogStatus.INFO, "Total time taken in seconds to run test case - "+formatedTime+"s");
			SysLogger.log("Screen Shot taken");
		} if(ITestResult.SUCCESS == result.getStatus()){
			testReporter.log(LogStatus.PASS, "**** Test Method '" + result.getMethod().getMethodName().replace("_", " ") + "' Passed ****");
			testReporter.log(LogStatus.INFO, "Total time taken in seconds to run test case - "+formatedTime+"s");
		}if(ITestResult.SKIP == result.getStatus()) {
			testReporter.log(LogStatus.INFO, "**** Test Method '" + result.getMethod().getMethodName().replace("_", " ") + "' Skipped ****");
			testReporter.log(LogStatus.INFO, "Total time taken in seconds to run test case - "+formatedTime+"s");
		}
		ReporterFactory.closeTest(result.getMethod().getMethodName());
		SysLogger.log("**** Test Method '" + result.getName() + "' Ended ****");
	}

	public static synchronized HomePage loginToMPulseCP(WebDriver driver) {
		String authUsername = ConfigReader.getConfig("ip_2fa_username");
		String authPassword = ConfigReader.getConfig("ip_2fa_password");
		try {
			goToMPulseHomePage(driver);
			if (driver.getCurrentUrl().contains(ConfigReader.getConfig("mPulse_homePage"))) {
				return new HomePage(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		AuthServiceLogoutPage authLogOut = new AuthServiceLogoutPage(driver);
		if(driver.getTitle().equalsIgnoreCase("Logout Page") && driver.getCurrentUrl().contains("/logout")) {
			authLogOut.clickOnBackToLoginButton();
		}
		
		AuthServiceLoginPage login = new AuthServiceLoginPage(driver);

		if (driver.getCurrentUrl().equalsIgnoreCase(ConfigReader.getConfig("auth_service_login_url")) && driver.getTitle().equalsIgnoreCase("Single SignOn")
				&& login.isSignInTextDisplayed()) {
			return login.loginToCP(authUsername, authPassword);
		} else {
			SysLogger.log("Auth Login page not present on current screen");
		}

		logOut(driver);
		login = new AuthServiceLoginPage(driver);
		return login.loginToCP(authUsername, authPassword);
	}
	
	public static synchronized UserServiceHomePage loginToUserService(WebDriver driver) {
		UserServiceLoginPage login = new UserServiceLoginPage(driver);
		UserServiceHomePage home = new UserServiceHomePage(driver);
		String username = ConfigReader.getConfig("auth_username");
		String password = ConfigReader.getConfig("auth_password");
		driver.navigate().to(ConfigReader.getConfig("Django_admin_url")+"login");
		String currentUrl = driver.getCurrentUrl();
		if (currentUrl.endsWith("login/")) {
			return login.login(username, password);
		}
		if (currentUrl.equalsIgnoreCase(ConfigReader.getConfig("Django_admin_url"))){
			return home;
		}
		return home;
	}

	public static synchronized CCHomePage loginToMPulseCC(WebDriver driver) throws InterruptedException{
		CCLoginPage login = new CCLoginPage(driver);
		String ccUsername = ConfigReader.getConfig("cc_username");
		String ccPassword = ConfigReader.getConfig("cc_password");
		driver.navigate().to(ConfigReader.getConfig("command_center_login_page"));
		String currentUrl = driver.getCurrentUrl();
		if (currentUrl.endsWith("/login")) {
			return login.login(ccUsername, ccPassword);
		}
		else if (currentUrl.equalsIgnoreCase(ConfigReader.getConfig("command_center_home_page"))){
			return new CCHomePage(driver);
		}
		ccLogOut(driver);
		return login.login(ccUsername, ccPassword);
	}

	public static synchronized void goToMPulseHomePage(WebDriver driver) throws Exception {
		try {
			SysLogger.log("Opening mPulse Home Page");
			driver.navigate().to(ConfigReader.getConfig("mPulse_homePage"));
			Thread.sleep(3000);
			SysLogger.log("on mPulse Home Page");
		} catch (Exception e) {
			if (e.toString().contains("Timed out") || e.toString().contains("Permission"))
				try {
					Thread.sleep(2000);
				} catch (Exception e2) {
				}
		}

	}

	public static synchronized void logOut(WebDriver driver) {
		HomePage home;
		try {
			home = new HomePage(driver);
			if (home.isLogOutLinkPresent()) {
				home.logOut();
				AuthServiceLogoutPage authLogOut = new AuthServiceLogoutPage(driver);
				if(driver.getTitle().equalsIgnoreCase("Logout Page") && driver.getCurrentUrl().contains("/logout")) {
					authLogOut.clickOnBackToLoginButton();
				}
			}
		} catch (Exception e) {
			SysLogger.log("Log out link not Present");
		}
	}

	public static synchronized void ccLogOut(WebDriver driver) {
		CCHomePage ccHomePage;
		try {
			ccHomePage = new CCHomePage(driver);
			if (ccHomePage.isLogoutButtonPresent()) {
				ccHomePage.ccLogOut();
			}
		} catch (Exception e) {
			SysLogger.log("Log out link not Present");
		}
	}
	
	private String getTestSuiteResult(ISuite suite) {
        Collection<ISuiteResult> suiteResults = suite.getResults().values();
        this.totalSuiteRun = suiteResults.size();
        StringBuilder str = new StringBuilder();
        String failedTestPrepend = "\nFailed Test Cases -\n"+"*******************************************"+"\n";
        for (ISuiteResult suiteResult : suiteResults) {
        	for (ITestResult result : suiteResult.getTestContext().getFailedTests().getAllResults()) {
        		failedTestsCounter++;
        		if(failedTestsCounter==1) {
    				str.append(failedTestPrepend);
    			}
        		if(failedTestsCounter<51) {
    				str.append(getFormatedResult(result, failedTestsCounter));
    			}
        	}
        }
        if(failedTestsCounter>0) {
        	str.append("*******************************************");
        }
        if(failedTestsCounter>50) {
        	str.append("\n");
        	str.append("Note- For further Failed Test Cases and complete Automation Report, please download the attached file and open in your browser.");
        }
        str.append("\n");
        
        String passedTestPrepend = "\nPassed Test Cases -\n"+"*******************************************"+"\n";
        for (ISuiteResult suiteResult : suiteResults) {
        	for (ITestResult result : suiteResult.getTestContext().getPassedTests().getAllResults()) {
        		passedTestsCounter++;
        		if(passedTestsCounter==1) {
    				str.append(passedTestPrepend);
    			}
        		if(passedTestsCounter<51) {
    				str.append(getFormatedResult(result, passedTestsCounter));
    			}
        	}
        }
        if(passedTestsCounter>0) {
        	str.append("*******************************************");
        }
        if(passedTestsCounter>50) {
        	str.append("\n");
        	str.append("Note- For further Passed Test Cases and complete Automation Report, please download the attached file and open in your browser.");
        }
        return str.toString();
    }

    private String getFormatedResult(ITestResult result, int count) {
    	double startTime = result.getStartMillis();
		double endTime = result.getEndMillis();
		double totalTimeTakenInMS = endTime-startTime;
		float totalTimeTakenInSeconds = (float) (totalTimeTakenInMS/1000);
	    DecimalFormat df = new DecimalFormat("0.00");
	    String formatedTime = df.format(totalTimeTakenInSeconds);
	    
        return count+": " + result.getMethod().getMethodName() + ". ("+formatedTime+" s)\n";
    }
    
    private HashMap<String, ArrayList<String>> getAllFailedTestClassAndMethodsName(ISuite suite) {
    	Collection<ISuiteResult> suiteResults = suite.getResults().values();
    	HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    	
        for (ISuiteResult suiteResult : suiteResults) {
        	for (ITestResult result : suiteResult.getTestContext().getFailedTests().getAllResults()) {
        		String className = result.getTestClass().getName();
        		String methodName = result.getMethod().getMethodName();
        		
        		if(map.containsKey(className)) {
        			map.get(className).add(methodName);
        		}
        		else {
        			map.put(className, new ArrayList<String>());
        			map.get(className).add(methodName);
        		}
        	}
        }
        return map;
    }
    
    public static synchronized HomePage cutomLoginToMPulseCP(WebDriver driver, String authUsername, String authPassword) {
		String username = authUsername;
		String password = authPassword;
		AuthServiceLoginPage login = new AuthServiceLoginPage(driver);
		try {
			goToMPulseHomePage(driver);
			if (driver.getCurrentUrl().contains(ConfigReader.getConfig("mPulse_homePage"))) {
				logOut(driver);
				login = new AuthServiceLoginPage(driver);
				return login.loginToCP(username, password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		AuthServiceLogoutPage authLogOut = new AuthServiceLogoutPage(driver);
		if(driver.getTitle().equalsIgnoreCase("Logout Page") && driver.getCurrentUrl().contains("/logout")) {
			authLogOut.clickOnBackToLoginButton();
		}		

		if (driver.getCurrentUrl().equalsIgnoreCase(ConfigReader.getConfig("auth_service_login_url")) && driver.getTitle().equalsIgnoreCase("Single SignOn")
				&& login.isSignInTextDisplayed()) {
			return login.loginToCP(username, password);
		} else {
			SysLogger.log("Auth Login page not present on current screen");
		}

		logOut(driver);
		login = new AuthServiceLoginPage(driver);
		return login.loginToCP(username, password);
	}
     
}
