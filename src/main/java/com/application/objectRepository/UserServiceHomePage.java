package com.application.objectRepository;

import org.openqa.selenium.WebDriver;

import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class UserServiceHomePage {
	WebDriver driver;
	ExtentTest testReporter;
	WebDriverWait wait;
	
	@FindBy(xpath = "//*[@id='site-name']/a[@href='/admin/']")
	private WebElement djangoAdminLogo;
	
	@FindBy(xpath = "//a[@href='/admin/account/account/' and contains(text(), 'Accounts')]")
	private WebElement accountsPageLink;
	
	@FindBy(xpath = "//a[@href='/admin/service/service/' and contains(text(), 'Services')]")
	private WebElement servicesPageLink;
	
	@FindBy(xpath = "//a[@href='/admin/user/authuser/' and contains(text(), 'Auth users')]")
	private WebElement authUserPageLink;
	
	@FindBy(xpath = "//a[@href='/admin/role/role/' and contains(text(), 'Roles')]")
	private WebElement rolePageLink;
	
	@FindBy(xpath = "//a[@href='/admin/account/account/add/' and @class='addlink']")
	private WebElement addAccountLink;
	
	@FindBy(xpath = "//a[@href='/admin/service/service/add/' and @class='addlink']")
	private WebElement addServiceLink;
	
	@FindBy(xpath = "//a[@href='/admin/user/authuser/add/' and @class='addlink']")
	private WebElement addAuthUserLink;
	
	@FindBy(xpath = "//a[@href='/admin/role/role/add/' and @class='addlink']")
	private WebElement addRoleLink;
	
	@FindBy(xpath = "//a[@href='/admin/logout/' and contains(text(), 'Log out')]")
	private WebElement logOutLink;
	
	public UserServiceHomePage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
		wait = new WebDriverWait(driver, 120);
	}
	
	public UserServiceAccountsPage goToAccountsPage() {
		if(wait.until(ExpectedConditions.visibilityOf(this.accountsPageLink)).isDisplayed()) {
			this.accountsPageLink.click();
			testReporter.log(LogStatus.INFO, "Clicked on accounts page link");
			return new UserServiceAccountsPage(this.driver);
		} else {
			SysLogger.log("Error: Accounts page link not present");
			testReporter.log(LogStatus.ERROR, "Error: Accounts page link not present");
			return new UserServiceAccountsPage(this.driver);
		}
	}
	
	public UserServiceServicesPage goToServicesPage() {
		if (this.servicesPageLink.isDisplayed()) {
			this.servicesPageLink.click();
			testReporter.log(LogStatus.INFO, "Clicked on Services page link");
			return new UserServiceServicesPage(this.driver);
		} else {
			SysLogger.log("Error: Services page link not present");
			testReporter.log(LogStatus.ERROR, "Error: Services page link not present");
			return new UserServiceServicesPage(this.driver);
		}
	}
	
	public UserServiceAuthUsersPage goToAuthUsersPage() {
		if (this.authUserPageLink.isDisplayed()) {
			this.authUserPageLink.click();
			testReporter.log(LogStatus.INFO, "Clicked on Auth User page link");
			return new UserServiceAuthUsersPage(this.driver);
		} else {
			SysLogger.log("Error: Auth User page link not present");
			testReporter.log(LogStatus.ERROR, "Error: Auth User page link not present");
			return new UserServiceAuthUsersPage(this.driver);
		}
	}
	
	public UserServiceLoginPage logOut() {
		if (this.logOutLink.isDisplayed()) {
			this.logOutLink.click();
			return new UserServiceLoginPage(this.driver);
		} else {
			SysLogger.log("Error: Element 'log out link' not Displayed or not Enabled");
		}
		return new UserServiceLoginPage(this.driver);
	}
	
	public UserServiceAddNewAccountPage goToAddNewAccount() {
		if(this.addAccountLink.isDisplayed()) {
			this.addAccountLink.click();
			testReporter.log(LogStatus.INFO, "Clicked on add new Account Link");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new UserServiceAddNewAccountPage(this.driver);
		}
		else {
			SysLogger.log("Error: New Account Link not present on page");
			testReporter.log(LogStatus.ERROR, "Error: New Account Link not present on page");
			return new UserServiceAddNewAccountPage(this.driver);
		}
	}
	
	public UserServiceAddNewServicePage goToAddNewService() {
		if(this.addServiceLink.isDisplayed()) {
			this.addServiceLink.click();
			testReporter.log(LogStatus.INFO, "Clicked on add new Service Link");
			return new UserServiceAddNewServicePage(this.driver);
		}
		else {
			SysLogger.log("Error: Add new Service Link not present on page");
			testReporter.log(LogStatus.ERROR, "Error: Add new Service Link not present on page");
			return new UserServiceAddNewServicePage(this.driver);
		}
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
}
