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

public class UserServiceAuthUsersPage {
	WebDriver driver;
	ExtentTest testReporter;
	
	@FindBy(xpath = "//a[@href='/admin/user/authuser/add/' and @class='addlink']")
	private WebElement addAuthUserLink;
	
	@FindBy(name = "action")
	private WebElement actionSelectBox;
	
	@FindBy(name = "index")
	private WebElement actionGoButton;
	
	@FindBy(xpath = "//input[@type='submit' and contains(@value, 'Yes')]")
	private WebElement deleteYesButton;
	
	@FindBy(xpath = "//*[@id='site-name']/a[@href='/admin/']")
	private WebElement djangoAdminLogo;
	
	@FindBy(xpath = "//input[@id='searchbar' and @type='text']")
	private WebElement searchTextBox;
	
	@FindBy(xpath = "//input[@type='submit' and @value='Search']")
	private WebElement searchButton;
	
	public UserServiceAuthUsersPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
	}
	
	public boolean isAddAuthUserLinkDisplayed() {
		if(this.addAuthUserLink.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public UserServiceAuthUsersPage selectAuthUserDeleteAction() {
		if (this.actionSelectBox.isDisplayed()) {
			Select sc = new Select(this.actionSelectBox);
			sc.selectByValue("delete_selected");
			testReporter.log(LogStatus.INFO, "Selected action delete Auth user");
			return new UserServiceAuthUsersPage(this.driver);
		}else {
			SysLogger.log("Error: not able to select action delete Auth user");
			testReporter.log(LogStatus.ERROR, "Error: Not able to select action delete auth user");
			return new UserServiceAuthUsersPage(this.driver);
		}
	}
	
	public UserServiceAuthUsersPage clickOnActionGoButton() {
		if (this.actionGoButton.isDisplayed()) {
			this.actionGoButton.click();
			testReporter.log(LogStatus.INFO, "Clicked on action delete auth user go button");
			return new UserServiceAuthUsersPage(this.driver);
		}else {
			SysLogger.log("Error: Not able to click on action delete auth user go button");
			testReporter.log(LogStatus.ERROR, "Error: Not able to click on auth user delete account go button");
			return new UserServiceAuthUsersPage(this.driver);
		}
	}
	
	public UserServiceAuthUsersPage clickOnDeleteYesButton() {
		if (this.deleteYesButton.isDisplayed()) {
			this.deleteYesButton.click();
			testReporter.log(LogStatus.INFO, "Clicked on delete Yes Button");
			return new UserServiceAuthUsersPage(this.driver);
		}else {
			SysLogger.log("Error: No able to click on delete Yes Button");
			testReporter.log(LogStatus.ERROR, "Error: No able to click on delete Yes Button");
			return new UserServiceAuthUsersPage(this.driver);
		}
	}
	
	public UserServiceAuthUsersPage clickOnCheckBoxByAuthUserEmail(String authUserEmail) {
		String xpath = "//a[text()='"+authUserEmail+"' and contains(@href, '/admin/user/authuser/')]/ancestor::th/preceding-sibling::td/input[@type='checkbox']";
		WebElement authUserCheckbox = this.driver.findElement(By.xpath(xpath));
		if (authUserCheckbox.isDisplayed()) {
			authUserCheckbox.click();
			testReporter.log(LogStatus.INFO, "Clicked on check box of Auth user - "+authUserEmail);
			return new UserServiceAuthUsersPage(this.driver);
		}else {
			SysLogger.log("Error: Check box with auth user email - "+authUserEmail+" is not present");
			testReporter.log(LogStatus.ERROR, "Error: Check box with auth user email - "+authUserEmail+" is not present");
			return new UserServiceAuthUsersPage(this.driver);
		}
	}
	
	public UserServiceAuthUsersPage clickOnChangeAccountLinkByAuthUserEmail(String authUserEmail) {
		String xpath = "//a[text()='"+authUserEmail+"' and contains(@href, '/admin/user/authuser/')]/ancestor::th/following-sibling::td/a[contains(@href,'change-accounts')]";
		WebElement authUserCheckbox = this.driver.findElement(By.xpath(xpath));
		if (authUserCheckbox.isDisplayed()) {
			authUserCheckbox.click();
			testReporter.log(LogStatus.INFO, "Clicked on change account check box of Auth user - "+authUserEmail);
			return new UserServiceAuthUsersPage(this.driver);
		}else {
			SysLogger.log("Error: change account check box with auth user email - "+authUserEmail+" is not present");
			testReporter.log(LogStatus.ERROR, "Error: change account check box with auth user email - "+authUserEmail+" is not present");
			return new UserServiceAuthUsersPage(this.driver);
		}
	}
	
	public UserServiceAuthUsersPage deleteAuthUserAccountByEmail(String authUserEmail) throws InterruptedException {
		this.searchUserByEmail(authUserEmail);
		this.clickOnCheckBoxByAuthUserEmail(authUserEmail);
		this.selectAuthUserDeleteAction();
		this.clickOnActionGoButton();
		this.clickOnDeleteYesButton();
		return new UserServiceAuthUsersPage(this.driver);
	}
	
	public UserServiceAddNewAuthUserPage goToAddNewAuthUser() {
		if(this.addAuthUserLink.isDisplayed()) {
			this.addAuthUserLink.click();
			testReporter.log(LogStatus.INFO, "Clicked on add new Auth user Link");
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
		else {
			SysLogger.log("Error: Add new Auth user Link not present on page");
			testReporter.log(LogStatus.ERROR, "Error: Add new Auth user Link not present on page");
			return new UserServiceAddNewAuthUserPage(this.driver);
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
	
	public UserServiceAuthUsersPage searchUserByEmail(String userEmail) {
		if(this.searchButton.isDisplayed() && this.searchTextBox.isDisplayed()) {
			this.searchTextBox.clear();
			this.searchTextBox.sendKeys(userEmail);
			this.searchButton.click();
			testReporter.log(LogStatus.INFO, "Searching user with given email");
			return new UserServiceAuthUsersPage(this.driver);
		}
		else {
			SysLogger.log("Error: User with given email not found");
			testReporter.log(LogStatus.ERROR, "Error: Not able to search account");
			return new UserServiceAuthUsersPage(this.driver);
		}
	}
	
	public UserServiceAddNewAuthUserPage editUser(String authUserName) {
		this.searchUserByEmail(authUserName);
		WebElement authUserNameLink = this.driver.findElement(By.xpath("//a[text()='"+authUserName+"' and contains(@href, '/admin/user/authuser/')]"));
		if (authUserNameLink.isDisplayed()) {
			authUserNameLink.click();
			testReporter.log(LogStatus.INFO, "Clicked on edit link of authUser - "+authUserName);
			return new UserServiceAddNewAuthUserPage(this.driver);
		}else {
			SysLogger.log("Error: Account name - "+authUserName+" is not present");
			testReporter.log(LogStatus.ERROR, "Error: Account name - "+authUserName+" is not present");
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
	}
}
