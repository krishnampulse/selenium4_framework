package com.application.testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.application.factories.BrowserFactory;
import com.application.factories.ReporterFactory;
import com.application.objectRepository.AuthServiceLoginPage;
import com.application.objectRepository.AuthServiceLogoutPage;
import com.application.objectRepository.ECHomePage;
import com.application.objectRepository.HomePage;
import com.application.objectRepository.SSOLoginPage;
import com.application.objectRepository.UserServiceAccountsPage;
import com.application.utility.ConfigReader;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SSOIntegrationTest extends BaseTest {
	private WebDriver driver;
	
	@Test(groups = { "sanity", "SSO_Auth_Integration"})
	public void Verify_that_user_can_login_to_platform_by_IP_Whitelisting_for_single_account_access() throws InterruptedException {
		driver = BrowserFactory.getBrowser();
		ExtentTest testReporter = ReporterFactory.getTest();
		testReporter.log(LogStatus.INFO, "Test started on browser ");
		testReporter.assignCategory("SSO Auth integration with platform Login");
		
		
		String cpAuthUrl = ConfigReader.getConfig("cp_auth_url");
		String userEmail = ConfigReader.getConfig("ip_2fa_user_email");
		String password = ConfigReader.getConfig("ip_2fa_password");
		String accountName = ConfigReader.getConfig("auth_account_name");

		super.loginToUserService(this.driver).goToAccountsPage().editAccount(accountName)
		.uncheckSSOEnabledCheckBox().disableTwoFactor().clickOnSaveAndContinueEditingButton();
		testReporter.log(LogStatus.INFO, "Changed the auth account to ip-whitelisting");
		
		AuthServiceLoginPage auth = new AuthServiceLoginPage(cpAuthUrl, this.driver);
		HomePage mPulseHome = auth.loginToCpByIPWhitelisting(userEmail, password);
		Thread.sleep(10000);
		Assert.assertTrue(mPulseHome.isHomePageIconPresent());
		Assert.assertTrue(mPulseHome.isLogOutLinkPresent());
		testReporter.log(LogStatus.INFO, "Successfully logged in to Control Panel using IP Whitelisting");
		mPulseHome.logOut();
		AuthServiceLogoutPage authLogOut = new AuthServiceLogoutPage(driver);
		authLogOut.verifyPageTitleIsLogoutPage();
		Assert.assertTrue(authLogOut.isSignedOutTextPresent());
	}
	
	@Test(groups = { "sanity", "SSO_Auth_Integration"})
	public void Verify_that_user_can_login_to_platform_by_2fa_auth_service() throws InterruptedException {
		driver = BrowserFactory.getBrowser();
		ExtentTest testReporter = ReporterFactory.getTest();
		testReporter.log(LogStatus.INFO, "Test started on browser ");
		testReporter.assignCategory("SSO Auth integration with platform Login");
		
		
		String cpAuthUrl = ConfigReader.getConfig("cp_auth_url");
		String userEmail = ConfigReader.getConfig("ip_2fa_user_email");
		String password = ConfigReader.getConfig("ip_2fa_password");
		String accountName = ConfigReader.getConfig("auth_account_name");
		
		SoftAssert softAssertion= new SoftAssert();

		try {
			
		super.loginToUserService(driver).goToAccountsPage().editAccount(accountName)
		.uncheckSSOEnabledCheckBox().selectTwoFactorEnabledByName("Email").clickOnSaveButton();
			
		AuthServiceLoginPage auth = new AuthServiceLoginPage(cpAuthUrl, driver);
		HomePage mPulseHome = auth.loginToCpByEmail2fa(userEmail, password);
		Thread.sleep(6000);
		softAssertion.assertTrue(mPulseHome.isHomePageIconPresent());
		softAssertion.assertTrue(mPulseHome.isLogOutLinkPresent());
		testReporter.log(LogStatus.INFO, "Successfully logged in to Control Panel using 2FA");
		mPulseHome.logOut();
		AuthServiceLogoutPage authLogOut = new AuthServiceLogoutPage(this.driver);
		authLogOut.verifyPageTitleIsLogoutPage();
		softAssertion.assertTrue(authLogOut.isSignedOutTextPresent());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			super.loginToUserService(driver).goToAccountsPage().editAccount(accountName).disableTwoFactor().clickOnSaveButton();
			softAssertion.assertAll();
		}
	}
	
	@Test(groups = { "sanity", "SSO_Auth_Integration"})
	public void Verify_that_user_can_login_to_platform_by_SSO_auth_service() throws InterruptedException {
		driver = BrowserFactory.getBrowser();
		ExtentTest testReporter = ReporterFactory.getTest();
		testReporter.log(LogStatus.INFO, "Test started on browser ");
		testReporter.assignCategory("SSO Auth integration with platform Login");
		
		
		String cpSSOUrl = ConfigReader.getConfig("cp_sso_auth_url");
		String userName = ConfigReader.getConfig("adfs_username");
		String password = ConfigReader.getConfig("adfs_password");
		String authAccountName = ConfigReader.getConfig("auth_account_name");
		String metadataUrl = ConfigReader.getConfig("sso_metadata_url");
		String accountName = ConfigReader.getConfig("auth_account_name");
		
		SoftAssert softAssertion= new SoftAssert();
		
		try {
		SSOLoginPage auth = new SSOLoginPage(cpSSOUrl, driver);
		
		super.loginToUserService(driver).goToAccountsPage().editAccount(authAccountName)
		.checkSSOEnabledCheckBox().updateMetadataUrl(metadataUrl).updateSubDomain(cpSSOUrl).clickOnSaveAndContinueEditingButton();
		testReporter.log(LogStatus.INFO, "Changed the auth account to IDP enabled with given subdomain");
		
		HomePage mPulseHome = auth.loginToCpBySSO(userName, password);
		Thread.sleep(12000);
		softAssertion.assertTrue(mPulseHome.isHomePageIconPresent());
		softAssertion.assertTrue(mPulseHome.isLogOutLinkPresent());
		testReporter.log(LogStatus.INFO, "Successfully logged in to Control Panel using IDP(Azure)");
		mPulseHome.logOut();
		AuthServiceLogoutPage authLogOut = new AuthServiceLogoutPage(this.driver);
		authLogOut.verifyPageTitleIsLogoutPage();
		softAssertion.assertTrue(authLogOut.isSignedOutTextPresent());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			super.loginToUserService(driver).goToAccountsPage().editAccount(accountName).uncheckSSOEnabledCheckBox().clickOnSaveButton();
			softAssertion.assertAll();
		}
	}

	@Test(groups = { "sanity", "SSO_Auth_Integration" })
    public void Verify_that_user_can_login_to_engagement_console_by_SSO_auth_service() throws InterruptedException {
        driver = BrowserFactory.getBrowser();
        ExtentTest testReporter = ReporterFactory.getTest();
        testReporter.log(LogStatus.INFO, "Test started on browser ");
        testReporter.assignCategory("SSO Auth integration with EC Login");
        
        
        String ecSSOUrl = ConfigReader.getConfig("ec_sso_auth_url");
        String userName = ConfigReader.getConfig("ec_adfs_username");
        String password = ConfigReader.getConfig("ec_adfs_password");
        String authAccountName = ConfigReader.getConfig("ec_auth_account_name");
        String metadataUrl = ConfigReader.getConfig("ec_sso_metadata_url");
        
        SoftAssert softAssertion= new SoftAssert();
        
        try {
        super.loginToUserService(driver).goToAccountsPage().editAccount(authAccountName)
        .checkSSOEnabledCheckBox().updateMetadataUrl(metadataUrl).updateSubDomain(ecSSOUrl).clickOnSaveAndContinueEditingButton();
        testReporter.log(LogStatus.INFO, "Changed the auth account to IDP enabled with given subdomain for EC");
		
		SSOLoginPage auth = new SSOLoginPage(ecSSOUrl, driver);
        ECHomePage mPulseHome = auth.loginToEcBySSO(userName, password);
        Thread.sleep(12000);
        softAssertion.assertTrue(mPulseHome.isECLogoPresent());
        softAssertion.assertTrue(mPulseHome.isUserDropDownPresent());
        testReporter.log(LogStatus.INFO, "Successfully logged in to Engagement Console using IDP(Azure)");
        mPulseHome.logOut();
        softAssertion.assertEquals(driver.getCurrentUrl(), ecSSOUrl+"logout/");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            super.loginToUserService(driver).goToAccountsPage().editAccount(authAccountName).uncheckSSOEnabledCheckBox().clickOnSaveButton();
            softAssertion.assertAll();
        }
    }
	
	@Test(groups = { "sanity", "SSO_Auth_Integration"})
	public void Verify_the_flow_where_user_without_phone_tries_to_login_through_2fa_mobile() throws InterruptedException {
		driver = BrowserFactory.getBrowser();
		ExtentTest testReporter = ReporterFactory.getTest();
		testReporter.log(LogStatus.INFO, "Test started on browser ");
		testReporter.assignCategory("SSO Auth integration with platform Login");
		
		
		String cpAuthUrl = ConfigReader.getConfig("cp_auth_url");
		String userEmail = ConfigReader.getConfig("ip_2fa_user_email");
		String password = ConfigReader.getConfig("ip_2fa_password");
		String accountName = ConfigReader.getConfig("auth_account_name");

		SoftAssert softAssertion= new SoftAssert();
		
		try {	
			loginToUserService(driver)
			.goToAuthUsersPage()
			.editUser(userEmail)
			.clearPhoneNumberField()
			.clickOnSaveButton()
			.returnToHomePage()
			.goToAccountsPage()
			.editAccount(accountName)
			.uncheckSSOEnabledCheckBox()
			.selectTwoFactorEnabledByName("mobile")
			.clickOnSaveButton();
			
			AuthServiceLoginPage auth = new AuthServiceLoginPage(cpAuthUrl, driver);
			auth.negativeLoginToCp2faMobile(userEmail, password);
			Thread.sleep(5000);
			softAssertion.assertEquals(true, auth.isErrorTextPresent());		
			testReporter.log(LogStatus.INFO, "Successfully verified 2FA mobile login flow where user does not have mobile phone");		
			}		
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			loginToUserService(driver).goToAccountsPage().editAccount(accountName).disableTwoFactor().clickOnSaveButton();
			softAssertion.assertAll();
		}		
	}
}
