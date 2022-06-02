package com.application.objectRepository;

import com.application.factories.ApplicationDB;
import com.application.factories.ReporterFactory;
import com.application.utility.ConfigReader;
import com.application.utility.SysLogger;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CCEditAccountPage {

	WebDriver driver;
	ExtentTest testReporter;
	Actions act;
	JavascriptExecutor js;
	private int callbackFormNumber;

	@FindBy(xpath = "//a[@class='add_callback_btn' and text()='Add another Callback URL']")
	@CacheLookup
	private WebElement addAnotherCallbackButton;

	@FindBy(id = "submit")
	@CacheLookup
	private WebElement updateButton;
	
	@FindBy(id = "first_name")
	private WebElement firstNameTextBox;
	
	@FindBy(id = "last_name")
	private WebElement lastNameTextBox;
	
	@FindBy(xpath = "//ul[@id='ip_list']/li/input[@value='']")
	private WebElement blankIPTextBox;
	
	@FindBy(id = "add_ip")
	private WebElement addIPButton;
	
	@FindBy(id = "allow_custom_field_encryption")
	private WebElement allowCustomFieldEncryptionCheckBox;

	public CCEditAccountPage(WebDriver driver) {
		this.driver = driver;
		act = new Actions(driver);
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
	}
	
	public CCEditAccountPage(WebDriver driver, int nextNumber) {
		this.driver = driver;
		act = new Actions(driver);
		this.callbackFormNumber = nextNumber;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
	}
	
	private WebElement urlField() {
		String xPath="//fieldset[@id='sync_url']//ul[@class='callback fields']/li["+this.callbackFormNumber+"]//input[@class='input_url']";
		return this.driver.findElement(By.xpath(xPath));
	}

	private WebElement usernameField() {
		String xPath="//fieldset[@id='sync_url']//ul[@class='callback fields']/li["+this.callbackFormNumber+"]//input[@class='input_username']";
		return this.driver.findElement(By.xpath(xPath));
	}

	private WebElement passwordField() {
		String xPath="//fieldset[@id='sync_url']//ul[@class='callback fields']/li["+this.callbackFormNumber+"]//input[@class='input_password']";
		return this.driver.findElement(By.xpath(xPath));
	}
	
	private WebElement addCallbackType() {
		String xPath="//fieldset[@id='sync_url']//ul[@class='callback fields']/li["+this.callbackFormNumber+"]//button[@class='callback_type']";
		return this.driver.findElement(By.xpath(xPath));
	}
	
	private WebElement callbackTypeDropdown() {
		String xPath="//fieldset[@id='sync_url']//ul[@class='callback fields']/li["+this.callbackFormNumber+"]//select[contains(@name, 'callback_action')]";
		return this.driver.findElement(By.xpath(xPath));
	}
	
	private WebElement callbackVersionDropdown() {
		String xPath="//fieldset[@id='sync_url']//ul[@class='callback fields']/li["+this.callbackFormNumber+"]//select[contains(@name, 'callback_version')]";
		return this.driver.findElement(By.xpath(xPath));
	}
	
	private WebElement callbackFormatDropdown() {
		String xPath="//fieldset[@id='sync_url']//ul[@class='callback fields']/li["+this.callbackFormNumber+"]//select[contains(@name, 'callback_format')]";
		return this.driver.findElement(By.xpath(xPath));
	}
	
	private WebElement includeApiCheckbox() {
		String xPath="//fieldset[@id='sync_url']//ul[@class='callback fields']/li["+this.callbackFormNumber+"]//input[@value='API']";
		return this.driver.findElement(By.xpath(xPath));
	}
	
	private void deleteExistingCallbackByURL(String callbackUrl) throws Exception {
		String query = "select count(id) from callback_url where url='"+callbackUrl+"' and account_id="+ConfigReader.getConfig("account_id");		
		int count = ApplicationDB.getIntData(query);
		if (count>0) {
			query = "select id from callback_url where url='"+callbackUrl+"' and account_id="+ConfigReader.getConfig("account_id");
			String callbackId = ApplicationDB.getStringData(query);
			String currentUrl = this.driver.getCurrentUrl();
			WebElement deleteButton = this.driver.findElement(By.id(callbackId));
			act.moveToElement(deleteButton).build().perform();
			act.click(deleteButton).build().perform();
			this.updateButton.click();
			this.driver.navigate().to(currentUrl);
		}
	}

	public CCEditAccountPage clickOnAddNewCallBack() throws Exception {
		this.deleteExistingCallbackByURL(ConfigReader.getConfig("callback_endpoint"));
		int count = ApplicationDB.getIntData("select count(*) from callback_url where account_id ="+ConfigReader.getConfig("account_id"));
		count = count+1; // next callback form number
		this.callbackFormNumber=count;
		if (this.addAnotherCallbackButton.isDisplayed() && this.addAnotherCallbackButton.isEnabled()) {
			testReporter.log(LogStatus.INFO, "button found and going to click button");
			this.addAnotherCallbackButton.click();
			testReporter.log(LogStatus.INFO, "button clicked");
		} else {
			SysLogger.log("Error: Element not Displayed or not Enabled");
		}
		return new CCEditAccountPage(this.driver, callbackFormNumber);
	}

	public CCEditAccountPage enterUrlForCallback() {
		String cc_callback_url = ConfigReader.getConfig("callback_endpoint");
		if (this.urlField().isDisplayed() && this.urlField().isEnabled()) {
			testReporter.log(LogStatus.INFO, "url field found and going to fill this out");
			urlField().sendKeys(cc_callback_url);
			testReporter.log(LogStatus.INFO, "sent url to the field");
		} else {
			SysLogger.log("Error: Element 'url field' not Displayed or not Enabled");
		}
		return new CCEditAccountPage(this.driver, callbackFormNumber);
	}

	public CCEditAccountPage enterWrongUrlForCallback() {
		String cc_callback_url = "https://krishnanandan.x..y.youpipe.net/";
		if (this.urlField().isDisplayed() && this.urlField().isEnabled()) {
			testReporter.log(LogStatus.INFO, "url field found and going to fill this out");
			urlField().sendKeys(cc_callback_url);
			testReporter.log(LogStatus.INFO, "sent url to the field");
		} else {
			SysLogger.log("Error: Element 'url field' not Displayed or not Enabled");
		}
		return new CCEditAccountPage(this.driver, callbackFormNumber);
	}

	public CCEditAccountPage enterUsernameForCallback() {
		String cc_callback_username = ConfigReader.getConfig("callback_username");
		if (this.usernameField().isDisplayed() && this.usernameField().isEnabled()) {
			testReporter.log(LogStatus.INFO, "username field found and going to fill this out");
			usernameField().sendKeys(cc_callback_username);
			testReporter.log(LogStatus.INFO, "sent username to the field");
		} else {
			SysLogger.log("Error: Element 'username field' not Displayed or not Enabled");
		}
		return new CCEditAccountPage(this.driver, callbackFormNumber);
	}

	public CCEditAccountPage enterPasswordForCallback(){
		String cc_callback_password = ConfigReader.getConfig("callback_password");
		if (this.passwordField().isDisplayed() && this.passwordField().isEnabled()) {
			testReporter.log(LogStatus.INFO, "password field found and going to fill this out");
			this.passwordField().sendKeys(cc_callback_password);
			testReporter.log(LogStatus.INFO, "sent password to the field");
		} else {
			SysLogger.log("Error: Element 'password field' not Displayed or not Enabled");
		}
		return new CCEditAccountPage(this.driver, callbackFormNumber);
	}

	public CCEditAccountPage enterCredentialsForCallback() {
		this.enterUrlForCallback();
		this.enterUsernameForCallback();
		this.enterPasswordForCallback();
		return new CCEditAccountPage(this.driver, callbackFormNumber);
	}

	public CCEditAccountPage clickAddCallbackType() {
		if (this.addCallbackType().isDisplayed() && this.addCallbackType().isEnabled()) {
			testReporter.log(LogStatus.INFO, "add callback type button found and going to click on it");
			addCallbackType().click();
			testReporter.log(LogStatus.INFO, "add callback type button clicked");
		} else {
			SysLogger.log("Error: Element 'add callback type button' not Displayed or not Enabled");
		}
		return new CCEditAccountPage(this.driver, callbackFormNumber);
	}

	public CCEditAccountPage selectCallbackType(String option) {
		if (this.callbackTypeDropdown().isDisplayed() && this.callbackTypeDropdown().isEnabled()) {
			testReporter.log(LogStatus.INFO, "callback type dropdown found and going to click on it");
			this.callbackTypeDropdown().click();
			Select dropdown = new Select(this.callbackTypeDropdown());
			dropdown.selectByVisibleText(option);
			testReporter.log(LogStatus.INFO, "Callback Option" + option + "selected");
		} else {
			SysLogger.log("Error: Element 'callback type dropdown' not Displayed or not Enabled");
		}
		return new CCEditAccountPage(this.driver, callbackFormNumber);
	}
	
	public CCEditAccountPage selectToUpdateCallbackType(String option) throws Exception {
		int count = ApplicationDB.getIntData("select count(*) from callback_url where account_id ="+ConfigReader.getConfig("account_id"));
		this.callbackFormNumber=count;
		if (this.callbackTypeDropdown().isDisplayed() && this.callbackTypeDropdown().isEnabled()) {
			testReporter.log(LogStatus.INFO, "callback type dropdown found and going to click on it");
			this.callbackTypeDropdown().click();
			Select dropdown = new Select(this.callbackTypeDropdown());
			dropdown.selectByVisibleText(option);
			testReporter.log(LogStatus.INFO, "Callback Option" + option + "selected");
		} else {
			SysLogger.log("Error: Element 'callback type dropdown' not Displayed or not Enabled");
		}
		return new CCEditAccountPage(this.driver, callbackFormNumber);
	}

	public CCEditAccountPage selectCallbackVersion(String option) {
		if (this.callbackVersionDropdown().isDisplayed() && this.callbackVersionDropdown().isEnabled()) {
			testReporter.log(LogStatus.INFO, "callback version dropdown found and going to click on it");
			this.callbackVersionDropdown().click();
			Select dropdown = new Select(this.callbackVersionDropdown());
			dropdown.selectByVisibleText(option);
			testReporter.log(LogStatus.INFO, "Callback Version - " + option + "selected");
		} else {
			SysLogger.log("Error: Element 'callback type dropdown' not Displayed or not Enabled");
		}
		return new CCEditAccountPage(this.driver, callbackFormNumber);
	}
	
	public CCEditAccountPage selectToUpdateCallbackVersion(String option) throws Exception {
		int count = ApplicationDB.getIntData("select count(*) from callback_url where account_id ="+ConfigReader.getConfig("account_id"));
		this.callbackFormNumber=count;
		if (this.callbackVersionDropdown().isDisplayed() && this.callbackVersionDropdown().isEnabled()) {
			testReporter.log(LogStatus.INFO, "callback version dropdown found and going to click on it");
			this.callbackVersionDropdown().click();
			Select dropdown = new Select(this.callbackVersionDropdown());
			dropdown.selectByVisibleText(option);
			testReporter.log(LogStatus.INFO, "Callback Version - " + option + "selected");
		} else {
			SysLogger.log("Error: Element 'callback type dropdown' not Displayed or not Enabled");
		}
		return new CCEditAccountPage(this.driver, callbackFormNumber);
	}

	public CCEditAccountPage selectCallbackFormat(String option) {
		if (this.callbackFormatDropdown().isDisplayed() && this.callbackFormatDropdown().isEnabled()) {
			testReporter.log(LogStatus.INFO, "callback format dropdown found and going to click on it");
			this.callbackFormatDropdown().click();
			Select dropdown = new Select(this.callbackFormatDropdown());
			dropdown.selectByVisibleText(option);
			testReporter.log(LogStatus.INFO, "Callback Format - " + option + "selected");
		} else {
			SysLogger.log("Error: Element 'callback type dropdown' not Displayed or not Enabled");
		}
		return new CCEditAccountPage(this.driver, callbackFormNumber);
	}
	
	public CCEditAccountPage selectToUpdateCallbackFormat(String option) throws Exception {
		int count = ApplicationDB.getIntData("select count(*) from callback_url where account_id ="+ConfigReader.getConfig("account_id"));
		this.callbackFormNumber=count;
		if (this.callbackFormatDropdown().isDisplayed() && this.callbackFormatDropdown().isEnabled()) {
			testReporter.log(LogStatus.INFO, "callback format dropdown found and going to click on it");
			this.callbackFormatDropdown().click();
			Select dropdown = new Select(this.callbackFormatDropdown());
			dropdown.selectByVisibleText(option);
			testReporter.log(LogStatus.INFO, "Callback Format - " + option + "selected");
		} else {
			SysLogger.log("Error: Element 'callback type dropdown' not Displayed or not Enabled");
		}
		return new CCEditAccountPage(this.driver, callbackFormNumber);
	}

	public CCEditAccountPage clickOnUpdate() {
		if (this.updateButton.isDisplayed() && this.updateButton.isEnabled()) {
			testReporter.log(LogStatus.INFO, "Update button found and going to click on it");
			this.updateButton.click();
			testReporter.log(LogStatus.INFO, "Update button clicked");
		} else {
			SysLogger.log("Error: Element 'update button' not Displayed or not Enabled");
		}
		return new CCEditAccountPage(this.driver, callbackFormNumber);
	}

	public CCEditAccountPage addIncludeApiCheckbox() {
		if (this.includeApiCheckbox().isDisplayed() && !this.includeApiCheckbox().isSelected()) {
			this.includeApiCheckbox().click();
		}
		return new CCEditAccountPage(this.driver, callbackFormNumber);
	}
	
	public CCEditAccountPage checkAllowCustomFieldEncryptionCheckBox() {
		if (this.allowCustomFieldEncryptionCheckBox.isDisplayed()) {
			if(this.allowCustomFieldEncryptionCheckBox.isSelected()) {
				SysLogger.log("Allow Custom Field Encryption Check Box is already checked");
				return this;
			} 
			this.allowCustomFieldEncryptionCheckBox.click();
			return this;
		}
		else SysLogger.log("Error: Allow Custom Field Encryption Check Box not displayed");
		return this;
	}
	
	public CCEditAccountPage updateFirstName(String firstName) {
		if (this.firstNameTextBox.isDisplayed()) {
			this.firstNameTextBox.click();
			this.firstNameTextBox.clear();
			this.firstNameTextBox.sendKeys(firstName);
			return this;
		}
		else SysLogger.log("Error: First Name text box not displayed");
		return this;
	}
	
	public CCEditAccountPage updateLastName(String lastName) {
		if (this.lastNameTextBox.isDisplayed()) {
			this.lastNameTextBox.click();
			this.lastNameTextBox.clear();
			this.lastNameTextBox.sendKeys(lastName);
			return this;
		}
		else SysLogger.log("Error: last Name text box not displayed");
		return this;
	}
	
	public CCEditAccountPage clickOnAddIPButton() {
		if (this.addIPButton.isDisplayed()) {
			this.addIPButton.click();
			return this;
		}
		else SysLogger.log("Error: Add IP button not displayed");
		return this;
	}
	
	public CCEditAccountPage enterNewIP(String ip) {
		if (this.blankIPTextBox.isDisplayed()) {
			this.blankIPTextBox.click();
			this.blankIPTextBox.clear();
			this.blankIPTextBox.sendKeys(ip);
			return this;
		}
		else SysLogger.log("Error: new blank IP text box not displayed");
		return this;
	}
	
	public CCEditAccountPage addNewIP(String ip) {
		this.clickOnAddIPButton();
		this.enterNewIP(ip);
		return this;
	}
	
	public CCAccountListPage clickOnUpdateAccountButton() {
		if (this.updateButton.isDisplayed() && this.updateButton.isEnabled()) {
			testReporter.log(LogStatus.INFO, "Update button found and going to click on it");
			this.updateButton.click();
			testReporter.log(LogStatus.INFO, "Update button clicked");
		} else {
			SysLogger.log("Error: Element 'update button' not Displayed or not Enabled");
		}
		return new CCAccountListPage(this.driver);
	}
}
