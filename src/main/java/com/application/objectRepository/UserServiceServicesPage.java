package com.application.objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;
public class UserServiceServicesPage {
	WebDriver driver;
	ExtentTest testReporter;
	
	@FindBy(xpath = "//a[@href='/admin/service/service/add/' and @class='addlink']")
	private WebElement addServiceLink;
	
	@FindBy(name = "action")
	private WebElement actionSelectBox;
	
	@FindBy(name = "index")
	private WebElement actionGoButton;
	
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement deleteYesButton;
	
	@FindBy(xpath = "//*[@id='site-name']/a[@href='/admin/']")
	private WebElement djangoAdminLogo;
	
	public UserServiceServicesPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
	}
	
	public boolean isAddServiceLinkDisplayed() {
		if(this.addServiceLink.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public UserServiceServicesPage selectServiceDeleteAction() {
		if (this.actionSelectBox.isDisplayed()) {
			Select sc = new Select(this.actionSelectBox);
			sc.selectByValue("delete_selected");
			testReporter.log(LogStatus.INFO, "Selected action delete Service");
			return new UserServiceServicesPage(this.driver);
		}else {
			SysLogger.log("Error: No able to selected action delete Service");
			testReporter.log(LogStatus.ERROR, "Error: No able to selected action delete Service");
			return new UserServiceServicesPage(this.driver);
		}
	}
	
	public UserServiceServicesPage clickOnActionGoButton() {
		if (this.actionGoButton.isDisplayed()) {
			this.actionGoButton.click();
			testReporter.log(LogStatus.INFO, "Clicked on action delete Service go button");
			return new UserServiceServicesPage(this.driver);
		}else {
			SysLogger.log("Error: No able click on action delete Service go button");
			testReporter.log(LogStatus.ERROR, "Error: No able click on action delete Service go button");
			return new UserServiceServicesPage(this.driver);
		}
	}
	
	public UserServiceServicesPage clickOnDeleteYesButton() {
		if (this.deleteYesButton.isDisplayed()) {
			this.deleteYesButton.click();
			testReporter.log(LogStatus.INFO, "Clicked on delete Yes Button");
			return new UserServiceServicesPage(this.driver);
		}else {
			SysLogger.log("Error: No able to click on delete Yes Button");
			testReporter.log(LogStatus.ERROR, "Error: No able to click on delete Yes Button");
			return new UserServiceServicesPage(this.driver);
		}
	}
	
	public UserServiceServicesPage clickOnCheckBoxByServiceName(String serviceName) {
		String xpath = "//a[text()='"+serviceName+"' and contains(@href, '/admin/service/service/')]/ancestor::th/preceding-sibling::td/input[@type='checkbox']";
		WebElement serviceCheckbox = this.driver.findElement(By.xpath(xpath));
		if (serviceCheckbox.isDisplayed()) {
			serviceCheckbox.click();
			testReporter.log(LogStatus.INFO, "Clicked on check box of Service - "+serviceName);
			return new UserServiceServicesPage(this.driver);
		}else {
			SysLogger.log("Error: Check box with Service name - "+serviceName+" is not present");
			testReporter.log(LogStatus.ERROR, "Error: Check box with Service name - "+serviceName+" is not present");
			return new UserServiceServicesPage(this.driver);
		}
	}
	
	public UserServiceServicesPage deleteUserServiceByName(String serviceName) throws InterruptedException {
		this.clickOnCheckBoxByServiceName(serviceName);
		this.selectServiceDeleteAction();
		this.clickOnActionGoButton();
		Thread.sleep(3000);
		this.clickOnDeleteYesButton();
		return new UserServiceServicesPage(this.driver);
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
