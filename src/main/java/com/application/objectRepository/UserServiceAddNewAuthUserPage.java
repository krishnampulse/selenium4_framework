package com.application.objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.application.factories.ReporterFactory;
import com.relevantcodes.extentreports.ExtentTest;

public class UserServiceAddNewAuthUserPage {
	WebDriver driver;
	ExtentTest testReporter;
	
	@FindBy(id = "id_first_name")
	private WebElement firstNameInputBox;
	
	@FindBy(id = "id_last_name")
	private WebElement lastNameInputBox;
	
	@FindBy(id = "id_email")
	private WebElement emailInputBox;
	
	@FindBy(id = "id_password1")
	private WebElement passwordInputBox;
	
	@FindBy(id = "id_password2")
	private WebElement confirmPasswordInputBox;
	
	@FindBy(id = "id_phone_number")
	private WebElement phoneNumberInputBox;
	
	@FindBy(id = "id_accounts_from")
	private WebElement accountSelectDropDown;
	
	@FindBy(id = "id_accounts_add_link")
	private WebElement accountsAddLink;
	
	@FindBy(xpath = "//a[text()='Add another User roles']")
	private WebElement addAnotherUserRole;
	
	@FindBy(name = "_save")
	private WebElement saveButton;
	
	@FindBy(name = "_addanother")
	private WebElement saveAndAddAnotherButton;
	
	@FindBy(name = "_continue")
	private WebElement saveAndContinueEditingButton;
	
	@FindBy(xpath = "//input[@type='submit' and @value='Submit']")
	private WebElement submitButton;
	
	@FindBy(xpath = "//a[@class='collapse-toggle' and text()='Show']")
	private WebElement passwordShowLink;
	
	@FindBy(xpath = "//a[@class='collapse-toggle' and text()='Hide']")
	private WebElement passwordHideLink;
	
	public UserServiceAddNewAuthUserPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
	}
	
	public UserServiceAddNewAuthUserPage enterFirstName(String firstName) {
		if (this.firstNameInputBox.isDisplayed()) {
			this.firstNameInputBox.clear();
			this.firstNameInputBox.sendKeys(firstName);
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
		else {
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
	}
	
	public UserServiceAddNewAuthUserPage enterLastName(String lastName) {
		if (this.lastNameInputBox.isDisplayed()) {
			this.lastNameInputBox.clear();
			this.lastNameInputBox.sendKeys(lastName);
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
		else {
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
	}
	
	public UserServiceAddNewAuthUserPage enterEmail(String email) {
		if (this.emailInputBox.isDisplayed()) {
			this.emailInputBox.clear();
			this.emailInputBox.sendKeys(email);
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
		else {
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
	}
	
	public UserServiceAddNewAuthUserPage enterPassword(String password) {
		if (this.passwordInputBox.isDisplayed()) {
			this.passwordInputBox.clear();
			this.passwordInputBox.sendKeys(password);
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
		else {
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
	}
	
	public UserServiceAddNewAuthUserPage enterConfirmPassword(String confirmPassword) {
		if (this.confirmPasswordInputBox.isDisplayed()) {
			this.confirmPasswordInputBox.clear();
			this.confirmPasswordInputBox.sendKeys(confirmPassword);
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
		else {
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
	}
	
	public UserServiceAddNewAuthUserPage enterPhoneNumber(String phoneNumber) {
		if (this.phoneNumberInputBox.isDisplayed()) {
			this.phoneNumberInputBox.clear();
			this.phoneNumberInputBox.sendKeys(phoneNumber);
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
		else {
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
	}
	
	public UserServiceAddNewAuthUserPage selectAccountByName(String accountName) {
		if (this.accountSelectDropDown.isDisplayed()) {
			Select sc = new Select(this.accountSelectDropDown);
			sc.selectByVisibleText(accountName);
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
		else {
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
	}
	
	public UserServiceAddNewAuthUserPage clickOnAccountsAddLink() {
		if (this.accountsAddLink.isDisplayed()) {
			this.accountsAddLink.click();
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
		else {
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
	}
	
	public UserServiceAddNewAuthUserPage clickOnAddAnotherUserRole() {
		if (this.addAnotherUserRole.isDisplayed()) {
			this.addAnotherUserRole.click();
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
		else {
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
	}
	
	public UserServiceAddNewAuthUserPage selectAccountServiceByName(String accountName, String serviceName, int set) {
		WebElement element = this.driver.findElement(By.id("id_roles-"+set+"-account_services"));
		if (element.isDisplayed()) {
			Select sc = new Select(element);
			sc.selectByVisibleText(accountName+" -- "+serviceName);
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
		else {
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
	}
	
	public UserServiceAddNewAuthUserPage enterRoleName(String roleName, String serviceName, int set) {
		WebElement element = this.driver.findElement(By.id("id_roles-"+set+"-role"));
		if (element.isDisplayed()) {
			Select sc = new Select(element);
			sc.selectByVisibleText(serviceName+" -- "+roleName);
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
		else {
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
	}
	
	public UserServiceAddNewAuthUserPage addServiceAndRoleName(String accountName, String serviceName, String roleName, int set) {
		this.clickOnAddAnotherUserRole();
		this.selectAccountServiceByName(accountName, serviceName, set);
		this.enterRoleName(roleName, serviceName, set);
		return new UserServiceAddNewAuthUserPage(this.driver);
	}
	
	public UserServiceAuthUsersPage clickOnSaveButton() {
		if (this.saveButton.isDisplayed()) {
			this.saveButton.click();
			return new UserServiceAuthUsersPage(this.driver);
		}
		else {
			return new UserServiceAuthUsersPage(this.driver);
		}
	}
	
	public UserServiceAddNewAuthUserPage clickOnSaveAndAddAnotherButton() {
		if (this.saveAndAddAnotherButton.isDisplayed()) {
			this.saveAndAddAnotherButton.click();
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
		else {
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
	}
	
	public UserServiceAddNewAuthUserPage clickOnSaveAndContinueEditingButton() {
		if (this.saveAndContinueEditingButton.isDisplayed()) {
			this.saveAndContinueEditingButton.click();
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
		else {
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
	}
	
	public UserServiceAddNewAuthUserPage clickOnChangeAccountMappingSubmitButton() {
		if (this.submitButton.isDisplayed()) {
			this.submitButton.click();
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
		else {
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
	}
	
	public UserServiceAddNewAuthUserPage clickOnPasswordShowLink() {
		if (this.passwordShowLink.isDisplayed()) {
			this.passwordShowLink.click();
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
		else {
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
	}
	
	public UserServiceAddNewAuthUserPage clickOnPasswordHideLink() {
		if (this.passwordHideLink.isDisplayed()) {
			this.passwordHideLink.click();
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
		else {
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
	}
	
	public UserServiceAddNewAuthUserPage clearPhoneNumberField() {
		if (this.phoneNumberInputBox.isDisplayed()) {
			this.phoneNumberInputBox.click();
			this.phoneNumberInputBox.clear();
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
		else {
			return new UserServiceAddNewAuthUserPage(this.driver);
		}
	}
}
