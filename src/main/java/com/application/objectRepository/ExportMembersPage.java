package com.application.objectRepository;

import org.openqa.selenium.By;

import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

public class ExportMembersPage {
	
	WebDriver driver;
	ExtentTest testReporter;
	
	@FindBy(xpath = "//input[@name='channels' and @value='email']")
	private WebElement emailChannelCheckbox;
	
	@FindBy(xpath = "//input[@name='channels' and @value='sms']")
	private WebElement smsChannelCheckbox;
	
	@FindBy(xpath = "//li[contains(text(),'Subscribed')]/input")
	private WebElement subscribedRadioButton;
	
	@FindBy(xpath = "//li[contains(text(),'Unsubscribed')]/input")
	private WebElement unsubscribedRadioButton;
	
	@FindBy(xpath = "//input[@type='checkbox' and @value='id']")
	private WebElement audienceIdCheckbox;
	
	@FindBy(xpath = "//input[@type='checkbox' and @value='email' and @name='standardFields']")
	private WebElement emailIdCheckbox;
	
	@FindBy(xpath = "//input[@type='checkbox' and @value='mobile_phone']")
	private WebElement mobilePhoneCheckbox;
	
	@FindBy(xpath = "//input[@type='checkbox' and @value='first_name']")
	private WebElement firstNameCheckbox;
	
	@FindBy(xpath = "//input[@type='checkbox' and @value='last_name']")
	private WebElement lastNameCheckbox;
	
	@FindBy(name = "emailAddress")
	private WebElement emailAddressInputbox;
	
	@FindBy(xpath = "//input[@value='EXPORT']")
	private WebElement exportButton;
	
	@FindBy(xpath = "//button[@type='button' and @role='button']")
	private WebElement successOKButton;
	
	public ExportMembersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
	}
	
	public boolean isEmailChannelCheckboxPresent() {
		if (this.emailChannelCheckbox.isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public boolean isSmsChannelCheckboxPresent() {
		if (this.smsChannelCheckbox.isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public boolean isSubscribedRadioButtonPresent() {
		if (this.subscribedRadioButton.isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public boolean isUnsubscribedRadioButtonPresent() {
		if (this.unsubscribedRadioButton.isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public boolean isAudienceIdCheckboxPresent() {
		if (this.audienceIdCheckbox.isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public boolean isEmailIdCheckboxPresent() {
		if (this.emailIdCheckbox.isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public boolean isMobilePhoneCheckboxPresent() {
		if (this.unsubscribedRadioButton.isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public ExportMembersPage selectEmailChannelCheckbox() throws InterruptedException {
		if (this.isEmailChannelCheckboxPresent()) {
			this.emailChannelCheckbox.click();
			return new ExportMembersPage(this.driver);
		} else {
			SysLogger.log("Error: Email channel checkbox not present on page");
		}
		return new ExportMembersPage(this.driver);
	}
	
	public ExportMembersPage selectSmsChannelCheckbox() throws InterruptedException {
		if (this.isSmsChannelCheckboxPresent()) {
			this.smsChannelCheckbox.click();
			return new ExportMembersPage(this.driver);
		} else {
			SysLogger.log("Error: SMS channel checkbox not present on page");
		}
		return new ExportMembersPage(this.driver);
	}
	
	public ExportMembersPage selectSubscribedRadioButton() {
		if (this.isSubscribedRadioButtonPresent()) {
			this.subscribedRadioButton.click();
			return new ExportMembersPage(this.driver);
		} else {
			SysLogger.log("Error: subscribed Radio Button not present on page");
		}
		return new ExportMembersPage(this.driver);
	}
	
	public ExportMembersPage selectUnsubscribedRadioButton() {
		if (this.isUnsubscribedRadioButtonPresent()) {
			this.unsubscribedRadioButton.click();
			return new ExportMembersPage(this.driver);
		} else {
			SysLogger.log("Error: Unsubscribed Radio Button not present on page");
		}
		return new ExportMembersPage(this.driver);
	}
	
	public ExportMembersPage selectAudienceIdCheckbox() {
		if (this.isAudienceIdCheckboxPresent()) {
			this.audienceIdCheckbox.click();
			return new ExportMembersPage(this.driver);
		} else {
			SysLogger.log("Error: audience Id Checkbox not present on page");
		}
		return new ExportMembersPage(this.driver);
	}
	
	public ExportMembersPage selectEmailIdCheckbox() {
		if (this.isEmailIdCheckboxPresent()) {
			this.emailIdCheckbox.click();
			return new ExportMembersPage(this.driver);
		} else {
			SysLogger.log("Error: emailId Checkbox not present on page");
		}
		return new ExportMembersPage(this.driver);
	}
	
	public ExportMembersPage selectMobilePhoneCheckbox() {
		if (this.isMobilePhoneCheckboxPresent()) {
			this.mobilePhoneCheckbox.click();
			return new ExportMembersPage(this.driver);
		} else {
			SysLogger.log("Error: mobilePhone Checkbox not present on page");
		}
		return new ExportMembersPage(this.driver);
	}
	
	public ExportMembersPage selectFirstNameCheckbox() {
		if (this.firstNameCheckbox.isDisplayed()) {
			this.firstNameCheckbox.click();
			return new ExportMembersPage(this.driver);
		} else {
			SysLogger.log("Error: first Name Checkbox not present on page");
		}
		return new ExportMembersPage(this.driver);
	}
	
	public ExportMembersPage selectLastNameCheckbox() {
		if (this.lastNameCheckbox.isDisplayed()) {
			this.lastNameCheckbox.click();
			return new ExportMembersPage(this.driver);
		} else {
			SysLogger.log("Error: lastName Checkbox not present on page");
		}
		return new ExportMembersPage(this.driver);
	}
	
	public ExportMembersPage enterEmailAddress(String email) {
		if (this.emailAddressInputbox.isDisplayed()) {
			this.emailAddressInputbox.sendKeys(email);
			return new ExportMembersPage(this.driver);
		} else {
			SysLogger.log("Error: email Address Inputbox not present on page");
		}
		return new ExportMembersPage(this.driver);
	}
	
	public ExportMembersPage clickOnExportButton() {
		if (this.exportButton.isDisplayed()) {
			this.exportButton.click();
			return new ExportMembersPage(this.driver);
		} else {
			SysLogger.log("Error: export Button not present on page");
		}
		return new ExportMembersPage(this.driver);
	}
	
	public AudienceMembersPage clickOnSuccessOKButton() throws InterruptedException {
		Thread.sleep(8000); // waiting for success dialogue to appear
		if (this.successOKButton.isDisplayed()) {
			this.successOKButton.click();
			return new AudienceMembersPage(this.driver);
		} else {
			SysLogger.log("Error: success OK Button not present on page");
		}
		return new AudienceMembersPage(this.driver);
	}
	
	public ExportMembersPage selectListByListId(String listId) {
		WebElement element = this.driver.findElement(By.xpath("//input[@value='"+listId+"' and @name='listIds']"));
		if (element.isDisplayed()) {
			element.click();
			return new ExportMembersPage(this.driver);
		} else {
			SysLogger.log("Error: list Name having id "+listId+" not present on page");
		}
		return new ExportMembersPage(this.driver);
	}
}
