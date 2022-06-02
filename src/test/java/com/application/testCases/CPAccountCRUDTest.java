package com.application.testCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.application.factories.ApplicationDB;
import com.application.factories.BrowserFactory;
import com.application.factories.ReporterFactory;
import com.application.objectRepository.CCAccountListPage;
import com.application.objectRepository.CreateControlPanelAccountPage;
import com.application.utility.ConfigReader;
import com.application.utility.RandomeUtility;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CPAccountCRUDTest extends BaseTest{
	WebDriver driver;
	
	String automationAccountId = ConfigReader.getConfig("account_id");
	String allIPs = ConfigReader.getConfig("default_ips_ui");
	String allMSWSIPs = ConfigReader.getConfig("default_msws_ips_ui");
	String defaultHermesURL = ConfigReader.getConfig("default_hermes_callback_url");
	String clientName = ConfigReader.getConfig("client_name");
	String sharedShortCode = ConfigReader.getConfig("eventApiList_sc");
	
	@Test(groups = { "cp_account_CRUD", "sanity_cp_account_CRUD"})
	public void Verify_default_IPs_for_CP_UI_access_when_creating_new_CP_account() throws InterruptedException {
		this.driver = BrowserFactory.getBrowser();
		ExtentTest testReporter = ReporterFactory.getTest();
		testReporter.log(LogStatus.INFO, "Test started on browser");
		testReporter.assignCategory("CP Account Creation");
		CreateControlPanelAccountPage newAccountPage = loginToMPulseCC(this.driver)
        .clickOnClientsButton()
        .clickOnAccountListButton()
        .clickOnAddNewAccountLink();
		
		ArrayList<String> allIPs = newAccountPage.getAllIPs();
		List<String> allIPsFromCofig = (List<String>)Arrays.asList(this.allIPs.split(","));
		Assert.assertTrue(allIPs.containsAll(allIPsFromCofig), 
		"Error: Default IPs for UI access from new account page are not same as given in config, Actual - "+allIPs+", Expected - "+this.allIPs);
		testReporter.log(LogStatus.INFO, "Verified default IPs for CP UI Access on CP Account create page");
	}

	@Test(groups = { "cp_account_CRUD", "sanity_cp_account_CRUD"})
	public void Verify_default_MSWS_IPs_for_API_access_when_creating_new_CP_account() throws InterruptedException {
		this.driver = BrowserFactory.getBrowser();
		ExtentTest testReporter = ReporterFactory.getTest();
		testReporter.log(LogStatus.INFO, "Test started on browser");
		testReporter.assignCategory("CP Account Creation");
		
		CreateControlPanelAccountPage newAccountPage = loginToMPulseCC(this.driver)
		.clickOnClientsButton()
		.clickOnAccountListButton()
		.clickOnAddNewAccountLink();
				
		ArrayList<String> allMSWSIPs = newAccountPage.getAllMSWSIPs();
		List<String> allMSWSIPsFromCofig = (List<String>)Arrays.asList(this.allMSWSIPs.split(","));
		Assert.assertTrue(allMSWSIPs.containsAll(allMSWSIPsFromCofig), 
		"Error: Default MSWS IPs for API access from new account page are not same as given in config, Actual - "+allMSWSIPs+", Expected - "+this.allMSWSIPs);
		testReporter.log(LogStatus.INFO, "Verified default MSWS IPs for CP APIs Access on CP Account create page");
	}

	@Test(groups = { "cp_account_CRUD" })
	public void Verify_default_Hermes_MO_callback_set_for_hermes_endpoint_when_creating_new_CP_account() throws InterruptedException {
		this.driver = BrowserFactory.getBrowser();
		ExtentTest testReporter = ReporterFactory.getTest();
		testReporter.log(LogStatus.INFO, "Test started on browser");
		testReporter.assignCategory("CP Account Creation");
		
		CreateControlPanelAccountPage newAccountPage = loginToMPulseCC(this.driver)
		.clickOnClientsButton()
		.clickOnAccountListButton()
		.clickOnAddNewAccountLink();
		
		Assert.assertEquals(newAccountPage.getAllCallBackURLCount(), 1, "Error: expected default callback count not matching");
		Assert.assertEquals(newAccountPage.getDefaultHermesCallbackURL(), this.defaultHermesURL, "Error: expected default callback URL not matching");
		Assert.assertEquals(newAccountPage.getDefaultSelectedCallback(), "MO", "Error: expected default selected callback type not matching");
		Assert.assertEquals(newAccountPage.getDefaultSelectedCallbackVersion(), "Version 1.1", "Error: expected default selected callback Version not matching");
		Assert.assertEquals(newAccountPage.getDefaultSelectedCallbackFormat(), "XML", "Error: expected default selected callback format not matching");
		testReporter.log(LogStatus.INFO, "Verified default Hermes MO callback endpoint on CP Account create page");
	}
	
	@Test(groups = { "cp_account_CRUD" })
	public void Verify_that_default_Enable_Segment_From_ES_checkbox_is_enabled_while_creating_new_CP_account() throws InterruptedException {
		this.driver = BrowserFactory.getBrowser();
		ExtentTest testReporter = ReporterFactory.getTest();
		testReporter.log(LogStatus.INFO, "Test started on browser");
		testReporter.assignCategory("CP Account Creation");
		
		CreateControlPanelAccountPage newAccountPage = loginToMPulseCC(this.driver)
		.clickOnClientsButton()
		.clickOnAccountListButton()
		.clickOnAddNewAccountLink();
		
		Assert.assertTrue(newAccountPage.isSegmentFromESCheckboxChecked(), "Error: default ES Segment check box is not checked while creating new Account");
		
		testReporter.log(LogStatus.INFO, "Verified default Enable Segment From ES checkbox is enabled");
	}
	
	@Test(groups = { "cp_account_CRUD", "sanity_cp_account_CRUD"})
	public void Verify_that_user_can_create_new_CP_account_with_all_default_values() throws Exception {
		this.driver = BrowserFactory.getBrowser();
		ExtentTest testReporter = ReporterFactory.getTest();
		testReporter.log(LogStatus.INFO, "Test started on browser");
		testReporter.assignCategory("CP Account Creation");
		
		String accountName = RandomeUtility.getNewAccountName();
		String accountUserEmail = RandomeUtility.getRandomEmails(6);
		String accountUserPhone = RandomeUtility.getRandomPhoneNumber();
		String accountUserFirstName = "Test_Automation";
		String accountUserLastName = "mPulse Mobile";
		
		CCAccountListPage accountListPage = loginToMPulseCC(this.driver)
		.clickOnClientsButton()
		.clickOnAccountListButton()
		.clickOnAddNewAccountLink()
		.enterAccountName(accountName)
		.selectClient(this.clientName)
		.selectTimeZone("(GMT+05:30) India Standard Time")
		.checkAllowCustomFieldEncryptionCheckBox()
		.enterUsername(accountUserEmail)
		.enterEmail(accountUserEmail)
		.enterFirstName(accountUserFirstName)
		.enterLastName(accountUserLastName)
		.enterMobilePhone(accountUserPhone)
		.addNewShortcode(1, "Shared", this.sharedShortCode)
		.enterAppmailAppName(1, "default_app")
		.checkIOSApp(1)
		.checkAndroidApp(1)
		.checkAudienceAPIAllowedCheckBox()
		.checkMessageAPIAllowedCheckBox()
		.clickOnGenerateKeyButton()
		.enterNotifyEmail(accountUserEmail)
		.clickOnCreateButton();
		Thread.sleep(10000);
		
		accountListPage.verifyAccountListHeadingPresent();
		Assert.assertEquals(accountListPage.getAccountStatusByAccountName(accountName), "Active", "Error: Newly created account status is not Active");
		
		Map<String, String> account = ApplicationDB.getResultMap("select * from account where name='"+accountName+"'");
		String accountID = account.get("id");
		Assert.assertEquals(account.get("enable_segment_from_es"), "t", "Error: Account's enable_segment_from_es flag is not true");
		Assert.assertEquals(account.get("allow_custom_field_encryption"), "t", "Error: Account's allow_custom_field_encryption flag is not true");
		Assert.assertEquals(account.get("status"), "100", "Error: Account's active status is not 100");
		Assert.assertEquals(account.get("allow_from"), this.allIPs, "Error: Account's allow from IPs are not matching from DB");
		Assert.assertEquals(account.get("time_zone"), "Asia/Calcutta", "Error: Account's time zone is not matching");
		
		Map<String, String> accountUser = ApplicationDB.getResultMap("select * from account_user where account_id="+accountID);
		Assert.assertEquals(accountUser.get("email"), accountUserEmail, "Error: User's Email is not matching");
		Assert.assertEquals(accountUser.get("username"), accountUserEmail, "Error: User's email is not matching");
		Assert.assertEquals(accountUser.get("status"), "250", "Error: User's active status should be 250");
		Assert.assertEquals(accountUser.get("role_type_id"), "4", "Error: User role type id is not 4");
		Assert.assertEquals(accountUser.get("create_other_admin"), "t", "Error: Create Other Admin flag is not true");
		Assert.assertEquals(accountUser.get("first_name"), accountUserFirstName, "Error: User's first Name is not matching");
		Assert.assertEquals(accountUser.get("last_name"), accountUserLastName, "Error: user's Last name is not matching");
		Assert.assertEquals(accountUser.get("mobile_phone"), accountUserPhone, "Error: user's phone number is not matching");
		testReporter.log(LogStatus.INFO, "CP account created successfully, and verified account and account user data from DB");
		testReporter.log(LogStatus.INFO, "Verified that user is able to create new CP account with all default values");
		accountListPage.deleteAccountById(accountID);
	}
	
	@Test(groups = { "cp_account_CRUD" })
	public void Verify_that_user_can_update_existing_CP_account() throws Exception {
		this.driver = BrowserFactory.getBrowser();
		ExtentTest testReporter = ReporterFactory.getTest();
		testReporter.log(LogStatus.INFO, "Test started on browser");
		testReporter.assignCategory("CP Account Creation");
		
		String accountName = RandomeUtility.getNewAccountName();
		String accountUserEmail = RandomeUtility.getRandomEmails(6);
		String accountUserPhone = RandomeUtility.getRandomPhoneNumber();
		String accountUserFirstName = "Test_Automation";
		String accountUserLastName = "mPulse Mobile";
		
		CCAccountListPage accountListPage = loginToMPulseCC(this.driver)
		.clickOnClientsButton()
		.clickOnAccountListButton()
		.clickOnAddNewAccountLink()
		.enterAccountName(accountName)
		.selectClient(this.clientName)
		.selectTimeZone("(GMT+05:30) India Standard Time")
		.enterUsername(accountUserEmail)
		.enterEmail(accountUserEmail)
		.enterFirstName(accountUserFirstName)
		.enterLastName(accountUserLastName)
		.enterMobilePhone(accountUserPhone)
		.addNewShortcode(1, "Shared", this.sharedShortCode)
		.enterAppmailAppName(1, "default_app")
		.checkIOSApp(1)
		.checkAndroidApp(1)
		.checkAudienceAPIAllowedCheckBox()
		.checkMessageAPIAllowedCheckBox()
		.clickOnGenerateKeyButton()
		.enterNotifyEmail(accountUserEmail)
		.clickOnCreateButton();
		Thread.sleep(10000);
		
		accountListPage.verifyAccountListHeadingPresent();
		Assert.assertEquals(accountListPage.getAccountStatusByAccountName(accountName), "Active", "Error: Newly created account status is not Active");
		
		Map<String, String> account = ApplicationDB.getResultMap("select * from account where name='"+accountName+"'");
		String accountID = account.get("id");
		Assert.assertEquals(account.get("enable_segment_from_es"), "t", "Error: Account's enable_segment_from_es flag is not true");
		Assert.assertEquals(account.get("allow_custom_field_encryption"), "f", "Error: Account's allow_custom_field_encryption flag is not false");
		Assert.assertEquals(account.get("status"), "100", "Error: Account's active status is not 100");
		Assert.assertEquals(account.get("allow_from"), this.allIPs, "Error: Account's allow from IPs are not matching from DB");
		Assert.assertEquals(account.get("time_zone"), "Asia/Calcutta", "Error: Account's time zone is not matching");
		
		Map<String, String> accountUser = ApplicationDB.getResultMap("select * from account_user where account_id="+accountID);
		Assert.assertEquals(accountUser.get("email"), accountUserEmail, "Error: User's Email is not matching");
		Assert.assertEquals(accountUser.get("username"), accountUserEmail, "Error: User's email is not matching");
		Assert.assertEquals(accountUser.get("status"), "250", "Error: User's active status should be 250");
		Assert.assertEquals(accountUser.get("role_type_id"), "4", "Error: User role type id is not 4");
		Assert.assertEquals(accountUser.get("create_other_admin"), "t", "Error: Create Other Admin flag is not true");
		Assert.assertEquals(accountUser.get("first_name"), accountUserFirstName, "Error: User's first Name is not matching");
		Assert.assertEquals(accountUser.get("last_name"), accountUserLastName, "Error: user's Last name is not matching");
		Assert.assertEquals(accountUser.get("mobile_phone"), accountUserPhone, "Error: user's phone number is not matching");
		
		testReporter.log(LogStatus.INFO, "CP account created successfully, and verified account and account user data from DB");
		
		accountUserFirstName = "Updated Test_Automation";
		accountUserLastName = "Updated mPulse Mobile";
		accountListPage = accountListPage.clickOnAccountEditButton(accountID)
		.checkAllowCustomFieldEncryptionCheckBox()
		.updateFirstName(accountUserFirstName).updateLastName(accountUserLastName)
		.addNewIP("52.9.0.100").clickOnUpdateAccountButton();
		
		account = ApplicationDB.getResultMap("select * from account where id="+accountID);
		Assert.assertEquals(account.get("allow_from"), this.allIPs+",52.9.0.100", "Error: Account's allow from IPs are not matching from DB");
		Assert.assertEquals(account.get("allow_custom_field_encryption"), "t", "Error: Account's allow_custom_field_encryption flag is not true");
		
		accountUser = ApplicationDB.getResultMap("select * from account_user where account_id="+accountID);
		Assert.assertEquals(accountUser.get("first_name"), accountUserFirstName, "Error: User's first Name is not matching");
		Assert.assertEquals(accountUser.get("last_name"), accountUserLastName, "Error: user's Last name is not matching");
		
		accountListPage.verifyAccountListHeadingPresent();
		Assert.assertEquals(accountListPage.getAccountStatusByAccountName(accountName), "Active", "Error: Newly created account status is not Active");
		
		testReporter.log(LogStatus.INFO, "Verified that user is able to update existing CP account");
		accountListPage.deleteAccountById(accountID);
	}
	
	@Test(groups = { "cp_account_CRUD", "sanity_cp_account_CRUD"})
	public void Verify_that_user_can_create_new_CP_account_by_cloning_existing_account() throws Exception {
		this.driver = BrowserFactory.getBrowser();
		ExtentTest testReporter = ReporterFactory.getTest();
		testReporter.log(LogStatus.INFO, "Test started on browser");
		testReporter.assignCategory("CP Account Creation");
		
		String accountName = "Clone_"+RandomeUtility.getNewAccountName();
		String accountUserEmail = RandomeUtility.getRandomEmails(6);
		String accountUserPhone = RandomeUtility.getRandomPhoneNumber();
		String accountUserFirstName = "Test_Automation_clone";
		String accountUserLastName = "mPulse Mobile";
		
		CCAccountListPage accountListPage = loginToMPulseCC(this.driver)
		.clickOnClientsButton()
		.clickOnAccountListButton().clickOnCloneAccountById(this.automationAccountId)
		.enterAccountName(accountName)
		.selectClient(this.clientName)
		.selectTimeZone("(GMT+05:30) India Standard Time")
		.checkAllowCustomFieldEncryptionCheckBox()
		.enterUsername(accountUserEmail)
		.enterEmail(accountUserEmail)
		.enterFirstName(accountUserFirstName)
		.enterLastName(accountUserLastName)
		.enterMobilePhone(accountUserPhone)
		.unAssignAllShortcode()
		.deleteAllExistingMSWSUser()
		.clickOnGenerateKeyButton()
		.enterNotifyEmail(accountUserEmail)
		.clickOnCreateButton();
		Thread.sleep(10000);
		
		accountListPage.verifyAccountListHeadingPresent();
		Assert.assertEquals(accountListPage.getAccountStatusByAccountName(accountName), "Active", "Error: Newly created account status is not Active");
		
		Map<String, String> account = ApplicationDB.getResultMap("select * from account where name='"+accountName+"'");
		String accountID = account.get("id");
		Assert.assertEquals(account.get("enable_segment_from_es"), "t", "Error: Account's enable_segment_from_es flag is not true");
		Assert.assertEquals(account.get("status"), "100", "Error: Account's active status is not 100");
		Assert.assertEquals(account.get("time_zone"), "Asia/Calcutta", "Error: Account's time zone is not matching");
		
		Map<String, String> accountUser = ApplicationDB.getResultMap("select * from account_user where account_id="+accountID);
		Assert.assertEquals(accountUser.get("email"), accountUserEmail, "Error: User's Email is not matching");
		Assert.assertEquals(accountUser.get("username"), accountUserEmail, "Error: User's email is not matching");
		Assert.assertEquals(accountUser.get("status"), "250", "Error: User's active status should be 250");
		Assert.assertEquals(accountUser.get("role_type_id"), "4", "Error: User role type id is not 4");
		Assert.assertEquals(accountUser.get("create_other_admin"), "t", "Error: Create Other Admin flag is not true");
		Assert.assertEquals(accountUser.get("first_name"), accountUserFirstName, "Error: User's first Name is not matching");
		Assert.assertEquals(accountUser.get("last_name"), accountUserLastName, "Error: user's Last name is not matching");
		Assert.assertEquals(accountUser.get("mobile_phone"), accountUserPhone, "Error: user's phone number is not matching");
		
		testReporter.log(LogStatus.INFO, "CP account created successfully by cloning, and verified account and account user data from DB");
		
		accountListPage.deleteAccountById(accountID);
	}
	
	@Test(groups = { "cp_account_CRUD", "sanity_cp_account_CRUD"})
	public void Verify_that_user_can_suspend_and_activate_existing_CP_account() throws Exception {
		this.driver = BrowserFactory.getBrowser();
		ExtentTest testReporter = ReporterFactory.getTest();
		testReporter.log(LogStatus.INFO, "Test started on browser");
		testReporter.assignCategory("CP Account Creation");
		
		String accountName = RandomeUtility.getNewAccountName();
		String accountUserEmail = RandomeUtility.getRandomEmails(6);
		String accountUserPhone = RandomeUtility.getRandomPhoneNumber();
		String accountUserFirstName = "Test_Automation";
		String accountUserLastName = "mPulse Mobile";
		
		CCAccountListPage accountListPage = loginToMPulseCC(this.driver)
		.clickOnClientsButton()
		.clickOnAccountListButton()
		.clickOnAddNewAccountLink()
		.enterAccountName(accountName)
		.selectClient(this.clientName)
		.selectTimeZone("(GMT+05:30) India Standard Time")
		.enterUsername(accountUserEmail)
		.enterEmail(accountUserEmail)
		.enterFirstName(accountUserFirstName)
		.enterLastName(accountUserLastName)
		.enterMobilePhone(accountUserPhone)
		.addNewShortcode(1, "Shared", this.sharedShortCode)
		.enterAppmailAppName(1, "default_app")
		.checkIOSApp(1)
		.checkAndroidApp(1)
		.checkAudienceAPIAllowedCheckBox()
		.checkMessageAPIAllowedCheckBox()
		.clickOnGenerateKeyButton()
		.enterNotifyEmail(accountUserEmail)
		.clickOnCreateButton();
		Thread.sleep(10000);
		
		accountListPage.verifyAccountListHeadingPresent();
		Assert.assertEquals(accountListPage.getAccountStatusByAccountName(accountName), "Active", "Error: Newly created account status is not Active");
		
		testReporter.log(LogStatus.INFO, "CP account created successfully, and verified account active status");
		
		Map<String, String> account = ApplicationDB.getResultMap("select * from account where name='"+accountName+"'");
		String accountID = account.get("id");
		
		accountListPage = accountListPage.suspendAccountById(accountID).verifyAccountListHeadingPresent();
		
		Assert.assertEquals(accountListPage.getAccountStatusByAccountName(accountName), "Suspended", "Error: Newly suspended account status is not Suspended");
		
		account = ApplicationDB.getResultMap("select * from account where id="+accountID);
		Assert.assertEquals(account.get("status"), "200", "Error: Suspended Account's status should be 200");
		
		testReporter.log(LogStatus.INFO, "CP account suspended successfully, and verified account user status from DB");
		
		accountListPage = accountListPage.activateAccountById(accountID).verifyAccountListHeadingPresent();
		
		Assert.assertEquals(accountListPage.getAccountStatusByAccountName(accountName), "Active", "Error: account status is not active");
		account = ApplicationDB.getResultMap("select * from account where id="+accountID);
		Assert.assertEquals(account.get("status"), "100", "Error: Account's active status is not 100");
		
		testReporter.log(LogStatus.INFO, "CP account re-activated successfully, and verified account user status from DB");
		
		accountListPage.deleteAccountById(accountID);
	}
	
	@Test(groups = { "cp_account_CRUD" })
	public void Verify_that_user_delete_existing_CP_account() throws Exception {
		this.driver = BrowserFactory.getBrowser();
		ExtentTest testReporter = ReporterFactory.getTest();
		testReporter.log(LogStatus.INFO, "Test started on browser");
		testReporter.assignCategory("CP Account Creation");
		
		String accountName = RandomeUtility.getNewAccountName();
		String accountUserEmail = RandomeUtility.getRandomEmails(6);
		String accountUserPhone = RandomeUtility.getRandomPhoneNumber();
		String accountUserFirstName = "Test_Automation";
		String accountUserLastName = "mPulse Mobile";
		
		CCAccountListPage accountListPage = loginToMPulseCC(this.driver)
		.clickOnClientsButton()
		.clickOnAccountListButton()
		.clickOnAddNewAccountLink()
		.enterAccountName(accountName)
		.selectClient(this.clientName)
		.selectTimeZone("(GMT+05:30) India Standard Time")
		.enterUsername(accountUserEmail)
		.enterEmail(accountUserEmail)
		.enterFirstName(accountUserFirstName)
		.enterLastName(accountUserLastName)
		.enterMobilePhone(accountUserPhone)
		.addNewShortcode(1, "Shared", this.sharedShortCode)
		.enterAppmailAppName(1, "default_app")
		.checkIOSApp(1)
		.checkAndroidApp(1)
		.checkAudienceAPIAllowedCheckBox()
		.checkMessageAPIAllowedCheckBox()
		.clickOnGenerateKeyButton()
		.enterNotifyEmail(accountUserEmail)
		.clickOnCreateButton();
		Thread.sleep(10000);
		
		accountListPage.verifyAccountListHeadingPresent();
		Assert.assertEquals(accountListPage.getAccountStatusByAccountName(accountName), "Active", "Error: Newly created account status is not Active");
		
		Map<String, String> account = ApplicationDB.getResultMap("select * from account where name='"+accountName+"'");
		String accountID = account.get("id");
		
		testReporter.log(LogStatus.INFO, "CP account created successfully, and verified account active status");
		
		accountListPage.deleteAccountById(accountID);
		
		accountListPage.verifyAccountListHeadingPresent();
		Assert.assertEquals(accountListPage.getAccountStatusByAccountName(accountName), "Deleted", "Error: Account status is not Deleted after deleting account");
		
		account = ApplicationDB.getResultMap("select * from account where id="+accountID);
		Assert.assertEquals(account.get("status"), "-100");
		
		Map<String, String> accountUser = ApplicationDB.getResultMap("select * from account_user where account_id="+accountID);
		Assert.assertEquals(accountUser.get("status"), "-100");
		
		testReporter.log(LogStatus.INFO, "CP account deleted successfully, and verified account and account user status from DB");
	}
}
