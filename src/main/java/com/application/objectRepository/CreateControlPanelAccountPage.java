package com.application.objectRepository;

import java.util.ArrayList;

import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;

public class CreateControlPanelAccountPage {
	private WebDriver driver;
	private ExtentTest testReporter;
	private Actions act;

	@FindBy(id = "submit")
	private WebElement updateButton;
	
	@FindBy(id = "account_name")
	private WebElement accountNameTextBox;
	
	@FindBy(id = "client")
	private WebElement clientSelectDropDown;
	
	@FindBy(xpath = "//ul[@id='ip_list']/li/input")
	private List<WebElement> allIPs;
	
	@FindBy(xpath = "//ul[@id='msws_ip_list']/li/input")
	private List<WebElement> allMSWSIPs;
	
	@FindBy(id = "add_ip")
	private WebElement addIPButton;
	
	@FindBy(id = "msws_add_ip")
	private WebElement mswsAddIPButton;
		
	@FindBy(xpath = "//ul[@id='ip_list']/li/input[@value='']")
	private WebElement blankIPTextBox;
	
	@FindBy(xpath = "//ul[@id='msws_ip_list']/li/input[@value='']")
	private WebElement blankMSWSIPTextBox;
		
	@FindBy(id = "delete_cp_account_ip_button")
	private WebElement deleteCPAccountIPButton;
	
	@FindBy(id = "timeZone")
	private WebElement timeZoneDropDown;
	
	@FindBy(id = "allow_custom_field_encryption")
	private WebElement allowCustomFieldEncryptionCheckBox;
	
	@FindBy(id = "allow_shared_contact_information")
	private WebElement allowSharedContactInformationCheckBox;
	
	@FindBy(id = "configure_callback_at_list_level")
	private WebElement configureCallbackListLevelCheckBox;
		
	@FindBy(id = "enable_last_mt_lookup")
	private WebElement enableLastMTLookupCheckBox;
		
	@FindBy(id = "allow_ivr")
	private WebElement allowIvrCheckBox;
		
	@FindBy(id = "secure_deeplink_branding")
	private WebElement secureDeeplinkBrandingTextBox;
	
	@FindBy(id = "username")
	private WebElement usernameTextBox;
	
	@FindBy(id = "email")
	private WebElement emailTextBox;
	
	@FindBy(id = "first_name")
	private WebElement firstNameTextBox;
	
	@FindBy(id = "last_name")
	private WebElement lastNameTextBox;
	
	@FindBy(id = "mobile_phone")
	private WebElement mobilePhoneTextBox;
	
	@FindBy(id = "add_shortcode")
	private WebElement addShortcodeButton;
	
	@FindBy(id = "addApp")
	private WebElement addNewAppmailAppButton;
		
	@FindBy(id = "audience_api_allowed")
	private WebElement audienceAPIAllowedCheckBox;
	
	@FindBy(id = "gprl_api_allowed")
	private WebElement gprlAPIAllowedCheckBox;
	
	@FindBy(id = "message_api_allowed")
	private WebElement messageAPIAllowedCheckBox;
	
	@FindBy(id = "communication_api_allowed")
	private WebElement communicationAPIAllowedCheckBox;
	
	@FindBy(id = "reporting_api_allowed")
	private WebElement reportingAPIAllowedCheckBox;
	
	@FindBy(id = "rtcallback_api_allowed")
	private WebElement realTimeEventCallbacksCheckBox;
		
	@FindBy(id = "generate_key")
	private WebElement generateKeyButton;
	
	@FindBy(id = "msws_add_users")
	private WebElement addMSWSUsersButton;
	
	@FindBy(xpath = "//ul[@id='msws_user_list']/li/button[contains(@id, '_delete') and @type='button']")
	private List<WebElement> allExistingMSWSUserDeleteButtons;
	
	@FindBy(name = "notify_email")
	private WebElement notifyEmailTextBox;
	
	@FindBy(id = "enable_segment_from_es")
	private WebElement enableSegmentFromESCheckbox;
	
	@FindBy(xpath = "//select[contains(@class, 'shortcode_assign_type') and not(contains(@id,'{'))]")
	private List<WebElement> allAssignedShortCode;
	
	@FindBy(xpath = "//ul[contains(@class, 'callback')]/li[@class='callback_border']")
	private List<WebElement> allCallBack;
	
	@FindBy(xpath = "//ul[contains(@class, 'callback')]/li[@class='callback_border'][1]/div/div/input[@class='input_url' and @type='text']")
	private WebElement defaultHermesCallback;
	
	@FindBy(xpath = "//ul[contains(@class, 'callback')]/li[@class='callback_border']/div[@class='clear']/table/tbody/tr/td[1]/select")
	private WebElement defaultSelectedCallback;
	
	@FindBy(xpath = "//ul[contains(@class, 'callback')]/li[@class='callback_border']/div[@class='clear']/table/tbody/tr/td[3]/select")
	private WebElement defaultSelectedCallbackVersion;
	
	@FindBy(xpath = "//ul[contains(@class, 'callback')]/li[@class='callback_border']/div[@class='clear']/table/tbody/tr/td[4]/select")
	private WebElement defaultSelectedCallbackFormat;
	
	@FindBy(xpath = "//a[@class='add_callback_btn' and text()='Add another Callback URL']")
	private WebElement addAnotherCallbackButton;

	public CreateControlPanelAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		act = new Actions(driver);
		testReporter = ReporterFactory.getTest();
	}
	
	public boolean isSegmentFromESCheckboxChecked() {
		act.moveToElement(this.enableSegmentFromESCheckbox).build().perform();
		if(this.enableSegmentFromESCheckbox.isSelected()){
			return true;
		} return false;
	}
	
	public CreateControlPanelAccountPage enterAccountName(String accountName) {
		if (this.accountNameTextBox.isDisplayed()) {
			this.accountNameTextBox.click();
			this.accountNameTextBox.clear();
			this.accountNameTextBox.sendKeys(accountName);
			return this;
		}
		else SysLogger.log("Error: Account name text box not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage selectClient(String clientName) {
		if (this.clientSelectDropDown.isDisplayed()) {
			this.clientSelectDropDown.click();
			Select sc = new Select(this.clientSelectDropDown);
			sc.selectByVisibleText(clientName);
			return this;
		}
		else SysLogger.log("Error: Client name drop down not displayed");
		return this;
	}
	
	public ArrayList<String> getAllIPs() {
		ArrayList<String> ips = new ArrayList<String>();
		for(WebElement ip: allIPs) {
			ips.add(ip.getAttribute("value"));
		}
		return ips;
	}
	
	public CreateControlPanelAccountPage unAssignAllShortcode() throws InterruptedException {
		for(WebElement scType: allAssignedShortCode) {
			Select sc = new Select(scType);
			sc.selectByVisibleText("Assign type");
			Thread.sleep(6000);
		}
		return this;
	}
	
	public CreateControlPanelAccountPage clickOnAddIPButton() {
		if (this.addIPButton.isDisplayed()) {
			this.addIPButton.click();
			return this;
		}
		else SysLogger.log("Error: Add IP button not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage enterNewIP(String ip) {
		if (this.blankIPTextBox.isDisplayed()) {
			this.blankIPTextBox.click();
			this.blankIPTextBox.clear();
			this.blankIPTextBox.sendKeys(ip);
			return this;
		}
		else SysLogger.log("Error: new blank IP text box not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage addNewIP(String ip) {
		this.clickOnAddIPButton();
		this.enterNewIP(ip);
		return this;
	}
	
	public CreateControlPanelAccountPage selectTimeZone(String timeZone) {
		if (this.timeZoneDropDown.isDisplayed()) {
			this.timeZoneDropDown.click();
			Select sc = new Select(this.timeZoneDropDown);
			sc.selectByVisibleText(timeZone);
			return this;
		}
		else SysLogger.log("Error: Time Zone drop down not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage checkAllowCustomFieldEncryptionCheckBox() {
		if (this.allowCustomFieldEncryptionCheckBox.isDisplayed()) {
			this.allowCustomFieldEncryptionCheckBox.click();
			return this;
		}
		else SysLogger.log("Error: Allow Custom Field Encryption Check Box not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage checkAllowSharedContactInformationCheckBox() {
		if (this.allowSharedContactInformationCheckBox.isDisplayed()) {
			this.allowSharedContactInformationCheckBox.click();
			return this;
		}
		else SysLogger.log("Error: Allow Shared Contact Information Check Box not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage checkConfigureCallbackListLevelCheckBox() {
		if (this.configureCallbackListLevelCheckBox.isDisplayed()) {
			this.configureCallbackListLevelCheckBox.click();
			return this;
		}
		else SysLogger.log("Error: ConfigallAssignedShortCodeure Callback List Level Check Box not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage checkEnableLastMTLookupCheckBox() {
		if (this.enableLastMTLookupCheckBox.isDisplayed()) {
			this.enableLastMTLookupCheckBox.click();
			return this;
		}
		else SysLogger.log("Error: Enable Last MT Lookup Check Box not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage checkAllowIvrCheckBox() {
		if (this.allowIvrCheckBox.isDisplayed()) {
			this.allowIvrCheckBox.click();
			return this;
		}
		else SysLogger.log("Error: Allow Ivr Check Box not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage enterSecureDeeplinkBrandingText(String brandingText) {
		if (this.secureDeeplinkBrandingTextBox.isDisplayed()) {
			this.secureDeeplinkBrandingTextBox.click();
			this.secureDeeplinkBrandingTextBox.clear();
			this.secureDeeplinkBrandingTextBox.sendKeys(brandingText);
			return this;
		}
		else SysLogger.log("Error: Secure Deeplink branding text box not displayed");
		return this;
	}
	
	public ArrayList<String> getAllMSWSIPs() {
		ArrayList<String> ips = new ArrayList<String>();
		for(WebElement ip: allMSWSIPs) {
			ips.add(ip.getAttribute("value"));
		}
		return ips;
	}
	
	public CreateControlPanelAccountPage clickOnAddMSWSIPButton() {
		if (this.mswsAddIPButton.isDisplayed()) {
			this.mswsAddIPButton.click();
			return this;
		}
		else SysLogger.log("Error: Add MSWS IP button not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage enterNewMSWSIP(String ip) {
		if (this.blankMSWSIPTextBox.isDisplayed()) {
			this.blankMSWSIPTextBox.click();
			this.blankMSWSIPTextBox.clear();
			this.blankMSWSIPTextBox.sendKeys(ip);
			return this;
		}
		else SysLogger.log("Error: new blank MSWS IP text box not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage addNewMSWSIP(String ip) {
		this.clickOnAddMSWSIPButton();
		this.enterNewMSWSIP(ip);
		return this;
	}
	
	public CreateControlPanelAccountPage enterUsername(String username) {
		if (this.usernameTextBox.isDisplayed()) {
			this.usernameTextBox.click();
			this.usernameTextBox.clear();
			this.usernameTextBox.sendKeys(username);
			return this;
		}
		else SysLogger.log("Error: username text box not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage enterEmail(String email) {
		if (this.emailTextBox.isDisplayed()) {
			this.emailTextBox.click();
			this.emailTextBox.clear();
			this.emailTextBox.sendKeys(email);
			return this;
		}
		else SysLogger.log("Error: Email text box not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage enterFirstName(String firstName) {
		if (this.firstNameTextBox.isDisplayed()) {
			this.firstNameTextBox.click();
			this.firstNameTextBox.clear();
			this.firstNameTextBox.sendKeys(firstName);
			return this;
		}
		else SysLogger.log("Error: First Name text box not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage enterLastName(String lastName) {
		if (this.lastNameTextBox.isDisplayed()) {
			this.lastNameTextBox.click();
			this.lastNameTextBox.clear();
			this.lastNameTextBox.sendKeys(lastName);
			return this;
		}
		else SysLogger.log("Error: last Name text box not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage enterMobilePhone(String phone) {
		if (this.mobilePhoneTextBox.isDisplayed()) {
			this.mobilePhoneTextBox.click();
			this.mobilePhoneTextBox.clear();
			this.mobilePhoneTextBox.sendKeys(phone);
			return this;
		}
		else SysLogger.log("Error: Mobile phone text box not displayed");
		return this;
	}
	
	public int getAssignedShortCodeCount() {
		return this.allAssignedShortCode.size();
	}
	
	public int getAllCallBackURLCount() {
		return this.allCallBack.size();
	}
	
	public CreateControlPanelAccountPage selectShortCodeType(int shortCodeSequence, String shortCodeType) throws InterruptedException {
		WebElement element = this.driver.findElement(By.id("shortcodes_"+(shortCodeSequence-1)+"_dedicated"));
		if (element.isDisplayed()) {
			Select sc = new Select(element);
			sc.selectByVisibleText(shortCodeType);
			Thread.sleep(6000);
			return this;
		}
		else SysLogger.log("Error: ShortCode Type drop down not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage selectShortCode(int shortCodeSequence, String shortCode) throws InterruptedException {
		WebElement element = this.driver.findElement(By.id("shortcodes_"+(shortCodeSequence-1)+"_shortcode"));
		if (element.isDisplayed()) {
			Select sc = new Select(element);
			sc.selectByVisibleText(shortCode);
			Thread.sleep(7000);
			return this;
		}
		else SysLogger.log("Error: ShortCode drop down not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage clickOnAddShortcodeButton() {
		if (this.addShortcodeButton.isDisplayed()) {
			this.addShortcodeButton.click();
			return this;
		}
		else SysLogger.log("Error: Add Shortcode button not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage addNewShortcode(int shortCodeSequence, String shortCodeType, String shortCode) throws InterruptedException {
		this.clickOnAddShortcodeButton().selectShortCodeType(shortCodeSequence, shortCodeType).selectShortCode(shortCodeSequence, shortCode);
		return this;
	}
	
	public CreateControlPanelAccountPage clickOnAddNewAppmailButton() {
		if (this.addNewAppmailAppButton.isDisplayed()) {
			this.addNewAppmailAppButton.click();
			return this;
		}
		else SysLogger.log("Error: Add new Appmail button not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage clickOnGenerateKeyButton() {
		if (this.generateKeyButton.isDisplayed()) {
			this.generateKeyButton.click();
			return this;
		}
		else SysLogger.log("Error: Generate Key button not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage clickOnAddMSWSUsersButton() {
		if (this.addMSWSUsersButton.isDisplayed()) {
			this.addMSWSUsersButton.click();
			return this;
		}
		else SysLogger.log("Error: Add MSWS Users button not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage deleteAllExistingMSWSUser() {
		for(WebElement mswsUserDeleteButton: this.allExistingMSWSUserDeleteButtons) {
			act.moveToElement(mswsUserDeleteButton).build().perform();
			mswsUserDeleteButton.click();
		}
		return this;
	}
	
	public CreateControlPanelAccountPage enterNotifyEmail(String email) {
		if (this.notifyEmailTextBox.isDisplayed()) {
			this.notifyEmailTextBox.click();
			this.notifyEmailTextBox.clear();
			this.notifyEmailTextBox.sendKeys(email);
			return this;
		}
		else SysLogger.log("Error: Notify Email text box not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage checkAudienceAPIAllowedCheckBox() {
		if (this.audienceAPIAllowedCheckBox.isDisplayed()) {
			act.moveToElement(this.audienceAPIAllowedCheckBox).build().perform();
			this.audienceAPIAllowedCheckBox.click();
			return this;
		}
		else SysLogger.log("Error: Audience API Allowed Check Box not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage checkGprlAPIAllowedCheckBox() {
		if (this.gprlAPIAllowedCheckBox.isDisplayed()) {
			this.gprlAPIAllowedCheckBox.click();
			return this;
		}
		else SysLogger.log("Error: GPRL API Allowed Check Box not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage checkMessageAPIAllowedCheckBox() {
		if (this.messageAPIAllowedCheckBox.isDisplayed()) {
			this.messageAPIAllowedCheckBox.click();
			return this;
		}
		else SysLogger.log("Error: Message API Allowed Check Box not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage checkCommunicationAPIAllowedCheckBox() {
		if (this.communicationAPIAllowedCheckBox.isDisplayed()) {
			this.communicationAPIAllowedCheckBox.click();
			return this;
		}
		else SysLogger.log("Error: Communication API Allowed Check Box not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage checkReportingAPIAllowedCheckBox() {
		if (this.reportingAPIAllowedCheckBox.isDisplayed()) {
			this.reportingAPIAllowedCheckBox.click();
			return this;
		}
		else SysLogger.log("Error: Reporting API Allowed Check Box not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage checkRealTimeEventCallbacksCheckBox() {
		if (this.realTimeEventCallbacksCheckBox.isDisplayed()) {
			this.realTimeEventCallbacksCheckBox.click();
			return this;
		}
		else SysLogger.log("Error: Real Time Event Callbacks Check Box not displayed");
		return this;
	}
	
	public CCAccountListPage clickOnCreateButton() throws InterruptedException {
		if (this.updateButton.isDisplayed()) {
			this.updateButton.click();
			Thread.sleep(5000);
			return new CCAccountListPage(this.driver);
		}
		else SysLogger.log("Error: create/update button not displayed");
		return new CCAccountListPage(this.driver);
	}
	
	public CreateControlPanelAccountPage enterAppmailAppName(int appIdSequence, String appName) {
		WebElement element = this.driver.findElement(By.id("apps_"+(appIdSequence-1)+"_app_id"));
		if (element.isDisplayed()) {
			act.moveToElement(element).build().perform();
			element.click();
			element.clear();
			element.sendKeys(appName);
			return this;
		}
		else SysLogger.log("Error: Appmail App Name text box not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage checkIOSApp(int appIdSequence) {
		WebElement element = this.driver.findElement(By.id("apps_"+(appIdSequence-1)+"_ios_app_platform_checked"));
		if (element.isDisplayed()) {
			element.click();
			return this;
		}
		else SysLogger.log("Error: IOS App check box not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage checkAndroidApp(int appIdSequence) {
		WebElement element = this.driver.findElement(By.id("apps_"+(appIdSequence-1)+"_android_app_platform_checked"));
		if (element.isDisplayed()) {
			element.click();
			return this;
		}
		else SysLogger.log("Error: Android App check box not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage checkIOSIpadApp(int appIdSequence) {
		WebElement element = this.driver.findElement(By.id("apps_"+(appIdSequence-1)+"_ios_ipad_app_platform_checked"));
		if (element.isDisplayed()) {
			element.click();
			return this;
		}
		else SysLogger.log("Error: IOS iPad App check box not displayed");
		return this;
	}
	
	public CreateControlPanelAccountPage checkMobilewebApp(int appIdSequence) {
		WebElement element = this.driver.findElement(By.id("apps_"+(appIdSequence-1)+"_mobileweb_app_platform_checked"));
		if (element.isDisplayed()) {
			element.click();
			return this;
		}
		else SysLogger.log("Error: MobileWeb check box not displayed");
		return this;
	}
	
	public String getDefaultHermesCallbackURL() {
		if(this.defaultHermesCallback.isDisplayed()) {
			return this.defaultHermesCallback.getAttribute("value").trim();
		} else return null;
	}
	
	public String getDefaultSelectedCallback() {
		if(this.defaultSelectedCallback.isDisplayed()) {
			Select sc = new Select(this.defaultSelectedCallback);
			return sc.getFirstSelectedOption().getText();
		} else return null;
	}
	
	public String getDefaultSelectedCallbackVersion() {
		if(this.defaultSelectedCallbackVersion.isDisplayed()) {
			Select sc = new Select(this.defaultSelectedCallbackVersion);
			return sc.getFirstSelectedOption().getText();
		} else return null;
	}
	
	public String getDefaultSelectedCallbackFormat() {
		if(this.defaultSelectedCallbackFormat.isDisplayed()) {
			Select sc = new Select(this.defaultSelectedCallbackFormat);
			return sc.getFirstSelectedOption().getText();
		} else return null;
	}
	
	public CreateControlPanelAccountPage clickOnAddAnotherCallbackButton() {
		if (this.addAnotherCallbackButton.isDisplayed()) {
			this.addAnotherCallbackButton.click();
			return this;
		}
		else SysLogger.log("Error: add Another Callback Button not displayed");
		return this;
	}
}
