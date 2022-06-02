package com.application.objectRepository;

import org.openqa.selenium.By;

import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

public class CustomFieldsPage {
	
	WebDriver driver;
	ExtentTest testReporter;
	
	@FindBy(id="add_custom_field")
	private WebElement addCustomFieldButton;
	
	@FindBy(xpath="//div[@class='ui-dialog-buttonset']/button[contains(@class, 'submit') and @type='button' and @role='button']")
	private WebElement deleteCustomFieldAlertButton;
	
	public CustomFieldsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
	}
	
	public boolean isAddCustomFieldButtonPresent() {
		
		if (this.addCustomFieldButton.isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public NewCustomFieldPage goToNewCustomFieldPage() {
		
		if (this.isAddCustomFieldButtonPresent()) {
			this.addCustomFieldButton.click();
		} else {
			SysLogger.log("Error: New Custom Field page link not present");
		}
		return new NewCustomFieldPage(this.driver);
	}
	
	public NewCustomFieldPage clickOnEditCustomFieldButton(String customFieldId) {
		WebElement editCustomFieldButton = this.driver.findElement(By.xpath("//td[@class=\"custom_action_col\"]/a[@href='/account/custom-fields/"+customFieldId+"/edit']"));
			if (editCustomFieldButton.isDisplayed()) {
				editCustomFieldButton.click();
			} else {
				SysLogger.log("Error: Edit Custom Field button not present");
			}
			return new NewCustomFieldPage(this.driver);
		}
	
	public CustomFieldsPage clickOnDeleteCustomFieldButton(String customFieldId) throws InterruptedException {
		WebElement deleteCustomFieldButton = this.driver.findElement(By.xpath("//td[@class=\"custom_action_col\"]/a[@href='/account/custom-fields/"+customFieldId+"/deleteValidate']"));

		if (deleteCustomFieldButton.isDisplayed()) {
			deleteCustomFieldButton.click();
		} else {
			SysLogger.log("Error: Delete Custom Field button not present");
		}
		this.clickOnDeleteCustomFieldPopUpBox();
		return new CustomFieldsPage(this.driver);
	}
	private void clickOnDeleteCustomFieldPopUpBox() throws InterruptedException {
	    
		if (deleteCustomFieldAlertButton.isDisplayed()) {
			deleteCustomFieldAlertButton.click();
		} else {
			SysLogger.log("Error: Delete Custom Field Alert button not present");
		}
        Thread.sleep(7000);	
	}
	
	

}
