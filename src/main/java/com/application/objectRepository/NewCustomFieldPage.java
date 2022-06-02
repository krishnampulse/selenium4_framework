package com.application.objectRepository;

import org.openqa.selenium.By;

import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;

public class NewCustomFieldPage {

	WebDriver driver;
	ExtentTest testReporter;
	Actions act;

	@FindBy (xpath = "//a[@href='/home' and contains(text(), 'mPulse Mobile')]")
	private WebElement homePageLink;
	
	@FindBy (xpath = "//div[@class='loader-fullscreen']/div[@class='loader-icon']")
	private WebElement progressBarLoader;
	
	@FindBy(id = "name")
	private WebElement customFieldName;

	@FindBy(id = "fieldType")
	private WebElement customFieldType;
	
	@FindBy(id = "alternativeValue")
	private WebElement alternativeValue;
	
	@FindBy(id = "description")
	private WebElement description;

	@FindBy(xpath = "//button[@id='submit']")
	private WebElement submitButton;
	
	@FindBy(id = "includeInPreferencesUi")
	private WebElement subPreferenceCheckbox;
	
	@FindBy(id = "subPreferencesName")
	private WebElement subPreferencesName;
	
	@FindBy(id = "subPreferencesEditable")
	private WebElement subPreferencesEditable;
	
	@FindBy(className = "deletePickListValue")
	private WebElement deletePicklistButton;
	
	@FindBy(id = "addPicklistValue")
	private WebElement addOptionButton;

	public NewCustomFieldPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
		act = new Actions(driver);
	}

	public boolean isCustomFieldNamePresent() {

		if (this.customFieldName.isDisplayed()) {
			return true;
		} else
			return false;
	}

	public boolean isCustomFieldTypePresent() {

		if (this.customFieldType.isDisplayed()) {
			return true;
		} else
			return false;
	}

	public boolean issubmitButtonButtonPresent() {

		if (this.submitButton.isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public boolean isAlternativeValueTextBoxPresent() {

		if (this.alternativeValue.isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public boolean isDescriptionTextBoxPresent() {

		if (this.description.isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public boolean issubPreferenceCheckboxPresent() {

		if (this.subPreferenceCheckbox.isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public boolean issubPreferencesNameTextBoxPresent() {

		if (this.subPreferencesName.isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public boolean issubPreferencesEditableCheckBoxPresent() {

		if (this.subPreferencesEditable.isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	private void enterCustomFieldName(String fieldName) {
		if (this.isCustomFieldNamePresent()) {
			this.customFieldName.sendKeys(fieldName);
		} else {
			SysLogger.log("Error: Custom field name text box not present");
		}
	}
	
	private void enterAlternativeValue(String altValue) {
		if (this.isAlternativeValueTextBoxPresent()) {
			this.alternativeValue.sendKeys(altValue);
		} else {
			SysLogger.log("Error: Alternative Value text box not present");
		}
	}
	
	private void enterDescription(String description) {
		if (this.isDescriptionTextBoxPresent()) {
			this.description.sendKeys(description);
		} else {
			SysLogger.log("Error: Description text box not present");
		}
	}
	
	private void checkSubPreferenceCheckBox() {
		if (this.issubPreferenceCheckboxPresent()) {
			this.subPreferenceCheckbox.click();
		} else {
			SysLogger.log("Error: Description text box not present");
		}
	}
	
	private void checkSubPreferenceEditableCheckBox() {
		if (this.issubPreferencesEditableCheckBoxPresent()) {
			this.subPreferencesEditable.click();
		} else {
			SysLogger.log("Error: Description text box not present");
		}
	}
	
	private void enterSubPreferenceDisplayName(String displayName) {
		if (this.issubPreferencesNameTextBoxPresent()) {
			this.subPreferencesName.sendKeys(displayName);
		} else {
			SysLogger.log("Error: Description text box not present");
		}
	}
	
	private void enterPicklistOptions(String[] options) {	
		
		if(options.length < 2) {
			SysLogger.log("Minimum two options required");
		}
		else if(options.length == 2) {
			this.deletePicklistButton.click();
			for (int i=0; i<2; i++) {
				WebElement element = this.driver.findElement(By.name("picklistValues["+i+"].value"));
				element.sendKeys(options[i]);
			}
		} else if(options.length >= 3) {
			for (int i=0; i<options.length; i++) {
				if(i < (options.length-3)) {
					this.addOptionButton.click();
				}
				WebElement element = this.driver.findElement(By.name("picklistValues["+i+"].value"));
				element.sendKeys(options[i]);
			}
		}	
	}
	
	private void clickOnSubmitButton() {
		if (this.issubmitButtonButtonPresent()) {
			act.moveToElement(this.submitButton).build().perform();
			act.click(this.submitButton).build().perform();
		} else {
			SysLogger.log("Error: Submit button not present");
		}
	}
	
	private void selectCustomFieldByValue(String value) {
		Select sc = new Select(this.customFieldType);
		sc.selectByValue(value);
	}
	
	private void editCustomFieldName(String fieldName) {
		if (this.isCustomFieldNamePresent()) {
			this.customFieldName.clear();
			this.customFieldName.sendKeys(fieldName);
		} else {
			SysLogger.log("Error: Custom field name text box not present");
		}
	}
	
	private void editAlternativeValue(String altValue) {
		if (this.isAlternativeValueTextBoxPresent()) {
			this.alternativeValue.clear();
			this.alternativeValue.sendKeys(altValue);
		} else {
			SysLogger.log("Error: Alternative Value text box not present");
		}
	}
	
	private void editDescription(String description) {
		if (this.isDescriptionTextBoxPresent()) {
			this.description.clear();
			this.description.sendKeys(description);
		} else {
			SysLogger.log("Error: Description text box not present");
		}
	}
	
	public CustomFieldsPage createNewStringTypeCustomField(String customFieldName, String altValue, String description, String customFieldType) throws InterruptedException {
		this.enterCustomFieldName(customFieldName);
		this.selectCustomFieldByValue(customFieldType);
		this.enterAlternativeValue(altValue);
		this.enterDescription(description);
		this.checkSubPreferenceCheckBox();
		this.enterSubPreferenceDisplayName(customFieldName);
		this.checkSubPreferenceEditableCheckBox();
		Thread.sleep(3000);
		this.clickOnSubmitButton();		
		return new CustomFieldsPage(this.driver);
	}
	
	public CustomFieldsPage createNewPicklistTypeCustomField(String customFieldName, String altValue, String description, String customFieldType, String... options) throws InterruptedException {
		this.enterCustomFieldName(customFieldName);
		this.selectCustomFieldByValue(customFieldType);
		this.enterPicklistOptions(options);
		this.enterAlternativeValue(altValue);
		this.enterDescription(description);
		this.checkSubPreferenceCheckBox();
		this.enterSubPreferenceDisplayName(customFieldName);
		this.checkSubPreferenceEditableCheckBox();
		Thread.sleep(3000);
		this.clickOnSubmitButton();		
		return new CustomFieldsPage(this.driver);
	}
	
	public CustomFieldsPage editStringTypeCustomField(String customFieldName, String altValue, String description) throws InterruptedException {
		this.editCustomFieldName(customFieldName);
		this.editAlternativeValue(altValue);
		this.editDescription(description);
		Thread.sleep(3000);
		this.clickOnSubmitButton();	
		return new CustomFieldsPage(this.driver);
	}
	
	public CustomFieldsPage editPicklistTypeCustomField(String customFieldName, String altValue, String description) throws InterruptedException {
		this.editCustomFieldName(customFieldName);
		this.editAlternativeValue(altValue);
		this.editDescription(description);
		Thread.sleep(3000);
		this.clickOnSubmitButton();		
		return new CustomFieldsPage(this.driver);
	}

	
	


}
