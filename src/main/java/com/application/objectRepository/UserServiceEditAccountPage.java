package com.application.objectRepository;

import org.openqa.selenium.WebDriver;

import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class UserServiceEditAccountPage {
	WebDriver driver;
	ExtentTest testReporter;
	
	@FindBy(id = "id_name")
	private WebElement accountNameInputBox;
	
	@FindBy(id = "id_two_factor_enabled")
	private WebElement twoFactorEnabled;
	
	@FindBy(id = "id_sso_enabled")
	private WebElement ssoEnabledCheckBox;
	
	@FindBy(id = "id_sub_domain")
	private WebElement subDomainInputBox;
	
	@FindBy(id = "id_metadata_url")
	private WebElement metadataUrlInputBox;
	
	@FindBy(id = "id_entity_id")
	private WebElement entityIdInputBox;
	
	@FindBy(id = "id_whitelist_ip")
	private WebElement whitelistIpInputBox;
	
	@FindBy(id = "id_platform_account_id")
	private WebElement platformAccountIdInputBox;
	
	@FindBy(id = "id_hermes_account_id")
	private WebElement hermesAccountIdInputBox;
	
	@FindBy(id = "id_mec_account_id")
	private WebElement mecAccountIdInputBox;
	
	@FindBy(id = "id_services_from")
	private WebElement servicesSelectDropDown;
	
	@FindBy(id = "id_services_add_link")
	private WebElement servicesAddLink;
	
	@FindBy(name = "_save")
	private WebElement saveButton;
	
	@FindBy(name = "_addanother")
	private WebElement saveAndAddAnotherButton;
	
	@FindBy(name = "_continue")
	private WebElement saveAndContinueEditingButton;
	
	@FindBy(xpath = "//p[@class='errornote']")
	private WebElement errorNote;
	
	@FindBy(xpath = "//ul[@class='errorlist']")
	private WebElement errorList;
	
	public UserServiceEditAccountPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
	}
	
	public UserServiceEditAccountPage updateAccountName(String accountName) {
		if (this.accountNameInputBox.isDisplayed()) {
			this.accountNameInputBox.clear();
			this.accountNameInputBox.sendKeys(accountName);
			return new UserServiceEditAccountPage(this.driver);
		}
		else {
			return new UserServiceEditAccountPage(this.driver);
		}
	}
	
	public UserServiceEditAccountPage checkSSOEnabledCheckBox() {
		if (this.ssoEnabledCheckBox.isDisplayed()) {
			this.ssoEnabledCheckBox.click();
			return new UserServiceEditAccountPage(this.driver);
		}
		else {
			return new UserServiceEditAccountPage(this.driver);
		}
	}
	
	public UserServiceEditAccountPage uncheckSSOEnabledCheckBox() {
		if (this.ssoEnabledCheckBox.isDisplayed() && this.ssoEnabledCheckBox.isSelected()) {
			this.ssoEnabledCheckBox.click();
			return new UserServiceEditAccountPage(this.driver);
		}
		else {
			return new UserServiceEditAccountPage(this.driver);
		}
	}
	
	public UserServiceEditAccountPage disableTwoFactor() {
		if (this.twoFactorEnabled.isDisplayed()) {
			Select sc = new Select(this.twoFactorEnabled);
			sc.selectByVisibleText(("disabled"));
			return new UserServiceEditAccountPage(this.driver);
		}
		else {
			return new UserServiceEditAccountPage(this.driver);
		}
	}
	
	public UserServiceEditAccountPage selectTwoFactorEnabledByName(String name) {
		if (this.twoFactorEnabled.isDisplayed()) {
			Select sc = new Select(this.twoFactorEnabled);
			sc.selectByVisibleText(name);
			return new UserServiceEditAccountPage(this.driver);
		}
		else {
			return new UserServiceEditAccountPage(this.driver);
		}
	}
	
	public UserServiceEditAccountPage updateSubDomain(String SubDomain) {
		if (this.subDomainInputBox.isDisplayed()) {
			this.subDomainInputBox.clear();
			this.subDomainInputBox.sendKeys(SubDomain);
			return new UserServiceEditAccountPage(this.driver);
		}
		else {
			return new UserServiceEditAccountPage(this.driver);
		}
	}
	
	public UserServiceEditAccountPage updateMetadataUrl(String metadataUrl) {
		if (this.metadataUrlInputBox.isDisplayed()) {
			this.metadataUrlInputBox.clear();
			this.metadataUrlInputBox.sendKeys(metadataUrl);
			return new UserServiceEditAccountPage(this.driver);
		}
		else {
			return new UserServiceEditAccountPage(this.driver);
		}
	}
	
	public UserServiceEditAccountPage updateEntityId(String entityId) {
		if (this.entityIdInputBox.isDisplayed()) {
			this.entityIdInputBox.clear();
			this.entityIdInputBox.sendKeys(entityId);
			return new UserServiceEditAccountPage(this.driver);
		}
		else {
			return new UserServiceEditAccountPage(this.driver);
		}
	}
	
	public UserServiceEditAccountPage updateWhitelistIp(String whitelistIp) {
		if (this.whitelistIpInputBox.isDisplayed()) {
			this.whitelistIpInputBox.clear();
			this.whitelistIpInputBox.sendKeys(whitelistIp);
			return new UserServiceEditAccountPage(this.driver);
		}
		else {
			return new UserServiceEditAccountPage(this.driver);
		}
	}
	
	public UserServiceEditAccountPage updatePlatformAccountId(String platformAccountIdInputBox) {
		if (this.platformAccountIdInputBox.isDisplayed()) {
			this.platformAccountIdInputBox.clear();
			this.platformAccountIdInputBox.sendKeys(platformAccountIdInputBox);
			return new UserServiceEditAccountPage(this.driver);
		}
		else {
			return new UserServiceEditAccountPage(this.driver);
		}
	}
	
	public UserServiceEditAccountPage updateHermesAccountId(String hermesAccountId) {
		if (this.hermesAccountIdInputBox.isDisplayed()) {
			this.hermesAccountIdInputBox.clear();
			this.hermesAccountIdInputBox.sendKeys(hermesAccountId);
			return new UserServiceEditAccountPage(this.driver);
		}
		else {
			return new UserServiceEditAccountPage(this.driver);
		}
	}
	
	public UserServiceEditAccountPage updateMecAccountId(String mecAccountId) {
		if (this.mecAccountIdInputBox.isDisplayed()) {
			this.mecAccountIdInputBox.clear();
			this.mecAccountIdInputBox.sendKeys(mecAccountId);
			return new UserServiceEditAccountPage(this.driver);
		}
		else {
			return new UserServiceEditAccountPage(this.driver);
		}
	}
	
	public UserServiceAccountsPage clickOnSaveButton() {
		if (this.saveButton.isDisplayed()) {
			this.saveButton.click();
			return new UserServiceAccountsPage(this.driver);
		}
		else {
			return new UserServiceAccountsPage(this.driver);
		}
	}
	
	public UserServiceAddNewAccountPage clickOnSaveAndAddAnotherButton() {
		if (this.saveAndAddAnotherButton.isDisplayed()) {
			this.saveAndAddAnotherButton.click();
			return new UserServiceAddNewAccountPage(this.driver);
		}
		else {
			return new UserServiceAddNewAccountPage(this.driver);
		}
	}
	
	public UserServiceEditAccountPage clickOnSaveAndContinueEditingButton() {
		if (this.saveAndContinueEditingButton.isDisplayed()) {
			this.saveAndContinueEditingButton.click();
			return new UserServiceEditAccountPage(this.driver);
		}
		else {
			return new UserServiceEditAccountPage(this.driver);
		}
	}
	
	public String getErrorMessage() {
		if (this.errorNote.isDisplayed()) {
			String errorMessage = this.errorNote.getText();
			testReporter.log(LogStatus.INFO, "Error Message on Page - "+errorMessage);
			SysLogger.log("Error Message on Page - "+errorMessage);
			return errorMessage;
		}
		else {
			SysLogger.log("Error message is not displayed on Page");
			return null;
		}
	}
	
	public String getFieldErrorMessage() {
		if (this.errorList.isDisplayed()) {
			String errorMessage = this.errorList.getText();
			testReporter.log(LogStatus.INFO, "Error Message on particular field - "+errorMessage);
			SysLogger.log("Error Message on particular field - "+errorMessage);
			return errorMessage;
		}
		else {
			SysLogger.log("Error message is not displayed for particular field");
			return null;
		}
	}
}
