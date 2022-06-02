package com.application.objectRepository;

import org.openqa.selenium.By;

import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class UserServiceAccountsPage {
	WebDriver driver;
	ExtentTest testReporter;
	
	@FindBy(xpath = "//a[@href='/admin/account/account/add/' and @class='addlink']")
	private WebElement addAccountLink;
	
	@FindBy(name = "action")
	private WebElement actionSelectBox;
	
	@FindBy(name = "index")
	private WebElement actionGoButton;
	
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement deleteYesButton;
	
	@FindBy(xpath = "//*[@id='site-name']/a[@href='/admin/']")
	private WebElement djangoAdminLogo;
	
	@FindBy(xpath = "//input[@id='searchbar' and @type='text']")
	private WebElement searchTextBox;
	
	@FindBy(xpath = "//input[@type='submit' and @value='Search']")
	private WebElement searchButton;
	
	public UserServiceAccountsPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
	}
	
	public boolean isAddAccountLinkDisplayed() {
		if(this.addAccountLink.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public UserServiceAccountsPage searchAccountByName(String accountName) {
		if(this.searchButton.isDisplayed() && this.searchTextBox.isDisplayed()) {
			this.searchTextBox.sendKeys(accountName);
			this.searchButton.click();
			testReporter.log(LogStatus.INFO, "Searching given account name");
			return new UserServiceAccountsPage(this.driver);
		}
		else {
			SysLogger.log("Error: Not able to search account");
			testReporter.log(LogStatus.ERROR, "Error: Not able to search account");
			return new UserServiceAccountsPage(this.driver);
		}
	}
	
	public UserServiceAccountsPage selectAccountDeleteAction() {
		if (this.actionSelectBox.isDisplayed()) {
			Select sc = new Select(this.actionSelectBox);
			sc.selectByValue("delete_selected");
			testReporter.log(LogStatus.INFO, "Selected action 'delete account'");
			return new UserServiceAccountsPage(this.driver);
		}else {
			SysLogger.log("Error: Not able to select 'delete account' action");
			testReporter.log(LogStatus.ERROR, "Error: Not able to select 'delete account' action");
			return new UserServiceAccountsPage(this.driver);
		}
	}
	
	public UserServiceAccountsPage clickOnActionGoButton() {
		if (this.actionGoButton.isDisplayed()) {
			this.actionGoButton.click();
			testReporter.log(LogStatus.INFO, "Clicked on action delete account go button");
			return new UserServiceAccountsPage(this.driver);
		}else {
			SysLogger.log("Error: No able click on action delete account go button");
			testReporter.log(LogStatus.ERROR, "Error: No able click on action delete account go button");
			return new UserServiceAccountsPage(this.driver);
		}
	}
	
	public UserServiceAccountsPage clickOnDeleteYesButton() {
		if (this.deleteYesButton.isDisplayed()) {
			this.deleteYesButton.click();
			testReporter.log(LogStatus.INFO, "Clicked on delete Yes Button");
			return new UserServiceAccountsPage(this.driver);
		}else {
			SysLogger.log("Error: No able to click on delete Yes Button");
			testReporter.log(LogStatus.ERROR, "Error: No able to click on delete Yes Button");
			return new UserServiceAccountsPage(this.driver);
		}
	}
	
	public UserServiceAccountsPage clickOnCheckBoxByAccountName(String accountName) {
		String xpath = "//a[text()='"+accountName+"' and contains(@href, '/admin/account/account/')]/ancestor::th/preceding-sibling::td/input[@type='checkbox']";
		WebElement accountCheckbox = this.driver.findElement(By.xpath(xpath));
		if (accountCheckbox.isDisplayed()) {
			accountCheckbox.click();
			testReporter.log(LogStatus.INFO, "Clicked on check box of account - "+accountName);
			return new UserServiceAccountsPage(this.driver);
		}else {
			SysLogger.log("Error: Check box with account name - "+accountName+" is not present");
			testReporter.log(LogStatus.ERROR, "Error: Check box with account name - "+accountName+" is not present");
			return new UserServiceAccountsPage(this.driver);
		}
	}
	
	public UserServiceEditAccountPage editAccount(String accountName) {
		this.searchAccountByName(accountName);
		String xpath = "//a[text()='"+accountName+"' and contains(@href, '/admin/account/account/')]";
		WebElement accountCheckbox = this.driver.findElement(By.xpath(xpath));
		if (accountCheckbox.isDisplayed()) {
			accountCheckbox.click();
			testReporter.log(LogStatus.INFO, "Clicked on edit link of account - "+accountName);
			return new UserServiceEditAccountPage(this.driver);
		}else {
			SysLogger.log("Error: Account name - "+accountName+" is not present");
			testReporter.log(LogStatus.ERROR, "Error: Account name - "+accountName+" is not present");
			return new UserServiceEditAccountPage(this.driver);
		}
	}
	
	public UserServiceAccountsPage deleteUserAccountByName(String accountName) throws InterruptedException {
		this.searchAccountByName(accountName);
		this.clickOnCheckBoxByAccountName(accountName);
		this.selectAccountDeleteAction();
		this.clickOnActionGoButton();
		Thread.sleep(3000);
		this.clickOnDeleteYesButton();
		return new UserServiceAccountsPage(this.driver);
	}
	
	public UserServiceAddNewAccountPage goToAddNewAccount() {
		if(this.addAccountLink.isDisplayed()) {
			this.addAccountLink.click();
			testReporter.log(LogStatus.INFO, "Clicked on add new Account Link");
			return new UserServiceAddNewAccountPage(this.driver);
		}
		else {
			SysLogger.log("Error: New Account Link not present on page");
			testReporter.log(LogStatus.ERROR, "Error: New Account Link not present on page");
			return new UserServiceAddNewAccountPage(this.driver);
		}
	}
	
	public UserServiceHomePage returnToHomePage() {
		if(this.djangoAdminLogo.isDisplayed()) {
			this.djangoAdminLogo.click();
			testReporter.log(LogStatus.INFO, "Returning to User Service Home page");
			return new UserServiceHomePage(this.driver);
		}
		else {
			SysLogger.log("Error: Django Admin logo is not displayed on page");
			testReporter.log(LogStatus.ERROR, "Error: Django Admin logo is not displayed on page");
			return new UserServiceHomePage(this.driver);
		}
	}
}
