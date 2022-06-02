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
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CreateMemberPage {

	WebDriver driver;
	WebDriverWait wait;
	ExtentTest testReporter;
	
	Actions act;
	
	@FindBy(id = "email")
	private WebElement emailTextBox;
	
	@FindBy(id="mobilePhone")
	private WebElement mobilePhoneTextBox;
	
	@FindBy(id="appMemberId")
	private WebElement appMemberIdTextBox;
	
	@FindBy(id="clientMemberId")
	private WebElement clientMemberIdTextBox;
	
	@FindBy(id="firstName")
	private WebElement firstNameTextBox;
	
	@FindBy(id="lastName")
	private WebElement lastNameTextBox;

	@FindBy(id="gender")
	private WebElement genderDropDown;
	
	@FindBy(name="birthDate")
	private WebElement birthDateTextBox;
	
	@FindBy(id="zipCode")
	private WebElement zipCodeTextBox;
	
	@FindBy(id="landLine")
	private WebElement landLineTextBox;
	
	@FindBy(id="country")
	private WebElement countryDropDown;
	
	@FindBy(id="state")
	private WebElement stateDropDown;
	
	@FindBy(id="city")
	private WebElement cityTextBox;
	
	@FindBy(id="address1")
	private WebElement address1TextBox;
	
	@FindBy(id="address2")
	private WebElement address2TextBox;
	
	@FindBy(id="businessPhoneNumber")
	private WebElement businessPhoneNumberTextBox;
	
	@FindBy(id="faxNumber")
	private WebElement faxNumberTextBox;
	
	@FindBy(id="companyName")
	private WebElement companyNameTextBox;
	
	@FindBy(id="occupation")
	private WebElement occupationTextBox;
	
	@FindBy(id="preferredLanguage")
	private WebElement preferredLanguageDropDown;
	
	@FindBy(id="rmEnabled")
	private WebElement rmEnabledCheckBox;
	
	@FindBy(id="addToMessageTestGroup")
	private WebElement addToMessageTestGroupCheckbox;
	
	@FindBy(id="addAsDependentMember")
	private WebElement addAsDependentMemberCheckbox;
	
	@FindBy(id="submit")
	private WebElement memberCreateFormSubmitButton;
	
	@FindBy(xpath="//div[@id='common_workarea']//a[@Class='common_form_cancel']")
	private WebElement memberCreateFormCancelLink;
	
	public CreateMemberPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
		act = new Actions(driver);
	}
	
	public boolean isEmailTextBoxPresent() {
		if(this.emailTextBox.isDisplayed()) {
			return true;
		}
			return false;
	}
	
	public boolean isMobilePhoneTextBoxPresent() {
		if(this.mobilePhoneTextBox.isDisplayed()) {
			return true;
		}
			return false;
	}
	
	public boolean isAppMemberIdTextBoxPresent() {
		if(this.appMemberIdTextBox.isDisplayed()) {
			return true;
		}
			return false;
	}
	
	public boolean isClientMemberIdTextBoxPresent() {
		if(this.clientMemberIdTextBox.isDisplayed()) {
			return true;
		}	
			return false;
	}
	
	public boolean isFirstNameTextBoxPresent() {
		if(this.firstNameTextBox.isDisplayed()) {
			return true;
		}
			return false;
	}
	
	public boolean isLastNameTextBoxPresent() {
		if(this.lastNameTextBox.isDisplayed()) {
			return true;
		}
			return false;
	}
	
	public boolean isGenderDropDownPresent() {
		if(this.genderDropDown.isDisplayed()) {
			return true;
		}
			return false;
	}
	
	public boolean isBirthDateTextBoxPresent() {
		if(this.birthDateTextBox.isDisplayed()) {
			return true;
		}
			return false;
	}
	
	public boolean isZipCodeTextBoxPresent() {
		if(this.zipCodeTextBox.isDisplayed()) {
			return true;
		}
			return false;
	}
	
	public boolean isLandLineTextBoxPresent() {
		if(this.landLineTextBox.isDisplayed()) {
			return true;
		}
			return false;
	}
	
	public boolean isCountryDropDownPresent() {
		if(this.countryDropDown.isDisplayed()) {
			return true;
		}
			return false;
	}
	
	public boolean isStateDropDownEnabled() {
		if(this.stateDropDown.isEnabled()) {
			return true;
		}
			return false;
	}
	
	public boolean isCityTextBoxPresent() {
		if(this.cityTextBox.isDisplayed()) {
			return true;
		}
			return false;
	}
	
	public boolean isAddress1TextBoxPresent() {
		if(this.address1TextBox.isDisplayed()) {
			return true;
		}
			return false;
	}
	
	public boolean isAddress2TextBoxPresent() {
		if(this.address2TextBox.isDisplayed()) {
			return true;
		}
			return false;
	}
	
	public boolean isBusinessPhoneNumberTextBoxPresent() {
		if(this.businessPhoneNumberTextBox.isDisplayed()) {
			return true;
		}
			return false;
	}
	
	public boolean isFaxNumberTextBoxPresent() {
		if(this.faxNumberTextBox.isDisplayed()) {
			return true;
		}
			return false;
	}
	
	public boolean isCompanyNameTextBoxPresent() {
		if(this.companyNameTextBox.isDisplayed()) {
			return true;
		}
			return false;
	}
	
	public boolean isOccupationTextBoxPresent() {
		if(this.occupationTextBox.isDisplayed()) {
			return true;
		}
			return false;
	}
	
	public boolean isPreferredLanguageTextBoxPresent() {
		if(this.preferredLanguageDropDown.isDisplayed()) {
			return true;
		}
			return false;
	}
	
	public boolean isRmEnabledCheckBoxPresent() {
		if(this.rmEnabledCheckBox.isDisplayed()) {
			return true;
		}
			return false;
	}
	
	public boolean isRmEnabledCheckBoxSelected() {
		if(this.rmEnabledCheckBox.isSelected()) {
			return true;
		}
			return false;
	}
	
	public boolean isAddToMessageTestGroupCheckboxPresent() {
		if(this.addToMessageTestGroupCheckbox.isDisplayed()) {
			return true;
		}
			return false;
	}
	
	public boolean isAddAsDependentMemberCheckboxPresent() {
		if(this.addAsDependentMemberCheckbox.isDisplayed()) {
			return true;
		}
			return false;
	}
	
	public boolean isMemberCreateFormSubmitButtonPresent() {
		if(this.memberCreateFormSubmitButton.isDisplayed()) {
			return true;
		}
			return false;
	}
	
	public boolean isMemberCreateCancelLinkPresent() {
		if(this.memberCreateFormCancelLink.isDisplayed()) {
			return true;
		}
			return false;
	}
	
	public CreateMemberPage enterStringTypeCustomFieldValue(String customFieldName, String customFieldValue) {
		WebElement customFieldInputBox = this.driver.findElement(By.xpath("//*[@id=\"customAttributes'"+customFieldName+"'\"]"));

		if (customFieldInputBox.isDisplayed()) {
			customFieldInputBox.click();
			customFieldInputBox.clear();
			customFieldInputBox.sendKeys(customFieldValue);
		}
		else {
			SysLogger.log("Error : Not able to enter email, email text box is not displayed");
		}
		return new CreateMemberPage(this.driver);
	}
	
	public CreateMemberPage enterMobilePhone(String phoneNumber) {
		if (this.isMobilePhoneTextBoxPresent()) {
			this.mobilePhoneTextBox.click();
			this.mobilePhoneTextBox.clear();
			this.mobilePhoneTextBox.sendKeys(phoneNumber);
			this.firstNameTextBox.click();
		}
		else {
			SysLogger.log("Error : Not able to enter phone number, text box is not displayed");
		}
		return new CreateMemberPage(this.driver);
	}
	
	public CreateMemberPage enterEmail(String email) {
		if (this.isEmailTextBoxPresent()) {
			this.emailTextBox.click();
			this.emailTextBox.clear();
			this.emailTextBox.sendKeys(email);
			this.firstNameTextBox.click();
		}
		else {
			SysLogger.log("Error : Not able to enter email, email text box is not displayed");
		}
		return new CreateMemberPage(this.driver);
	}
	
	public CreateMemberPage enterAppMemberId(String appMemberId) {
		if (this.isAppMemberIdTextBoxPresent()) {
			this.appMemberIdTextBox.click();
			this.appMemberIdTextBox.clear();
			this.appMemberIdTextBox.sendKeys(appMemberId);
			this.firstNameTextBox.click();
		}
		else {
			SysLogger.log("Error : Not able to enter app member id, text box is not displayed");
		}
		return new CreateMemberPage(this.driver);
	}
	
	public CreateMemberPage enterClientMemberId(String clientMemberId) {
		if (this.isClientMemberIdTextBoxPresent()) {
			this.clientMemberIdTextBox.click();
			this.clientMemberIdTextBox.clear();
			this.clientMemberIdTextBox.sendKeys(clientMemberId);
			this.firstNameTextBox.click();
		}
		else {
			SysLogger.log("Error : Not able to enter client member id, text box is not displayed");
		}
		return new CreateMemberPage(this.driver);
	}
	
	public CreateMemberPage enterFirstName(String firstName) {
		if (this.isFirstNameTextBoxPresent()) {
			this.firstNameTextBox.clear();
			this.firstNameTextBox.sendKeys(firstName);
		}
		else {
			SysLogger.log("Error : Not able to enter first name, text box is not displayed");
		}
		return new CreateMemberPage(this.driver);
	}
	
	public CreateMemberPage enterlastName(String lastName) {
		if (this.isLastNameTextBoxPresent()) {
			this.lastNameTextBox.clear();
			this.lastNameTextBox.sendKeys(lastName);
		}
		else {
			SysLogger.log("Error : Not able to enter last name, text box is not displayed");
		}
		return new CreateMemberPage(this.driver);
	}
	
	public CreateMemberPage selectGender(String gender) {
		if (this.isGenderDropDownPresent()) {
			Select sc=new Select(genderDropDown);
			sc.selectByVisibleText(gender);
		}
		else {
			SysLogger.log("Error : Not able to select gender, dropdown is not displayed");
		}
		return new CreateMemberPage(this.driver);
	}
	
	public CreateMemberPage enterBirthDate(String birthDate) {
		if (this.isBirthDateTextBoxPresent()) {
			this.birthDateTextBox.clear();
			this.birthDateTextBox.sendKeys(birthDate);
		}
		else {
			SysLogger.log("Error : Not able to enter birth date, text box is not displayed");
		}
		return new CreateMemberPage(this.driver);
	}
	
	public CreateMemberPage enterZipCode(String zipCode) {
		if (this.isZipCodeTextBoxPresent()) {
			this.zipCodeTextBox.clear();
			this.zipCodeTextBox.sendKeys(zipCode);
		}
		else {
			SysLogger.log("Error : Not able to enter zip code, text box is not displayed");
		}
		return new CreateMemberPage(this.driver);
	}
	
	public CreateMemberPage enterLandLineNumber(String landLine) {
		if (this.isLandLineTextBoxPresent()) {
			this.landLineTextBox.clear();
			this.landLineTextBox.sendKeys(landLine);
		}
		else {
			SysLogger.log("Error : Not able to enter landline number, text box is not displayed");
		}
		return new CreateMemberPage(this.driver);
	}
	
	public CreateMemberPage selectCountry(String country) {
		if (this.isCountryDropDownPresent()) {
			Select sc=new Select(countryDropDown);
			sc.selectByVisibleText(country);
		}
		else {
			SysLogger.log("Error : Not able to select country, dropdown is not displayed");
		}
		return new CreateMemberPage(this.driver);
	}
	
	public CreateMemberPage selectState(String state) {
		if (this.isStateDropDownEnabled()) {
			this.stateDropDown.sendKeys(state);
		}
		else {
			SysLogger.log("Error : Not able to select state, dropdown not enabled");
		}
		return new CreateMemberPage(this.driver);
	}
		
	public CreateMemberPage enterAddress1(String address1) {
		if (this.isAddress1TextBoxPresent()) {
			this.address1TextBox.clear();
			this.address1TextBox.sendKeys(address1);
		}
		else {
			SysLogger.log("Error : Not able to enter address1, text box is not displayed");
		}
		return new CreateMemberPage(this.driver);
	}
	
	
	public CreateMemberPage enterAddress2(String address2) {
		if (this.isAddress2TextBoxPresent()) {
			this.address2TextBox.clear();
			this.address2TextBox.sendKeys(address2);
		}
		else {
			SysLogger.log("Error : Not able to enter address2, text box is not displayed");
		}
		return new CreateMemberPage(this.driver);
	}
	
	public CreateMemberPage enterBusinessPhoneNumber(String businessPhoneNumber) {
		if (this.isBusinessPhoneNumberTextBoxPresent()) {
			this.businessPhoneNumberTextBox.clear();
			this.businessPhoneNumberTextBox.sendKeys(businessPhoneNumber);
		}
		else {
			SysLogger.log("Error : Not able to enter business phone number, text box is not displayed");
		}
		return new CreateMemberPage(this.driver);
	}
	
	public CreateMemberPage enterFaxNumber(String faxNumber) {
		if (this.isFaxNumberTextBoxPresent()) {
			this.faxNumberTextBox.clear();
			this.faxNumberTextBox.sendKeys(faxNumber);
		}
		else {
			SysLogger.log("Error : Not able to enter fax number, text box is not displayed");
		}
		return new CreateMemberPage(this.driver);
	}
	
	public CreateMemberPage enterCompanyName(String companyName) {
		if (this.isCompanyNameTextBoxPresent()) {
			this.companyNameTextBox.clear();
			this.companyNameTextBox.sendKeys(companyName);
		}
		else {
			SysLogger.log("Error : Not able to enter company name, text box is not displayed");
		}
		return new CreateMemberPage(this.driver);
	}
	
	public CreateMemberPage enterOccupation(String occupation) {
		if (this.isOccupationTextBoxPresent()) {
			this.occupationTextBox.clear();
			this.occupationTextBox.sendKeys(occupation);
		}
		else {
			SysLogger.log("Error : Not able to enter occupation, text box is not displayed");
		}
		return new CreateMemberPage(this.driver);
	}
	
	public CreateMemberPage selectPreferredLanguage(String preferredLanguage) {
		if (this.isPreferredLanguageTextBoxPresent()) {
			this.preferredLanguageDropDown.sendKeys(preferredLanguage);
		}
		else {
			SysLogger.log("Error : Not able to select preferred language, dropdown is not displayed");
		}
		return new CreateMemberPage(this.driver);
	}
	
	public CreateMemberPage selectRmEnabledCheckBox() {
		if(this.isRmEnabledCheckBoxPresent()) {
			if(this.isRmEnabledCheckBoxSelected()) {
				SysLogger.log("Error : RM Enabled checkbox is already checked");
			}
			else {
				this.rmEnabledCheckBox.click();
			}
		}
		else {
			SysLogger.log("Error : Not able to select RM Enabled, checkbox is not displayed");
		}
		return new CreateMemberPage(this.driver);
	}
	
	public CreateMemberPage selectAddToMessageTestGroupCheckbox() {
		if(this.isAddToMessageTestGroupCheckboxPresent()) {
			this.addToMessageTestGroupCheckbox.click();
		}
		else {
			SysLogger.log("Error : Not able to add member to test group, checkbox is not displayed");
		}
		return new CreateMemberPage(this.driver);
	}
	
	public ListSubscribersPage clickOnSubmitButton() {
		if (this.isMemberCreateFormSubmitButtonPresent()) {
			this.memberCreateFormSubmitButton.click();
		}
		else {
			SysLogger.log("Error : Not able to click Submit, button is not displayed");
		}
		return new ListSubscribersPage(this.driver);
	}
	
	public ListSubscribersPage clickOnCancelLink() {
		if (this.isMemberCreateCancelLinkPresent()) {
			this.memberCreateFormCancelLink.click();
		}
		else {
			SysLogger.log("Error : Not able to click Cancel, link is not displayed");
		}
		return new ListSubscribersPage(this.driver);
	}
	
	public CreateMemberPage deselectSmsCheckBoxByListId(String listId) throws InterruptedException {
		WebElement element = driver.findElement(By.xpath("//input[@value='sms' and contains(@id, '"+listId+"') and not(@disabled)]"));
		if(element.isSelected()) {
			testReporter.log(LogStatus.INFO, "SMS channel checkbox is checked");
			SysLogger.log("SMS channel checkbox is checked");
			this.firstNameTextBox.click();
			element.click();
			testReporter.log(LogStatus.INFO, "unchecked SMS channel checkbox");
		}
		else {
			testReporter.log(LogStatus.INFO, "Error: SMS channel checkbox is not checked");
			SysLogger.log("Error: SMS channel checkbox is not checked");
		}
		return new CreateMemberPage(this.driver);
	}
	
	public CreateMemberPage deselectEmailCheckBoxByListId(String listId) throws InterruptedException {
		String xpath="//input[@value='email' and contains(@id, '"+listId+"')]";
		WebElement element = this.driver.findElement(By.xpath(xpath));
		if(element.isSelected()) {
			testReporter.log(LogStatus.INFO, "Email channel checkbox is checked");
			SysLogger.log("Email channel checkbox is checked");
			this.firstNameTextBox.click();
			element.click();
			testReporter.log(LogStatus.INFO, "unchecked Email channel checkbox");
		}
		else {
			testReporter.log(LogStatus.INFO, "Error: Email channel checkbox is not checked");
			SysLogger.log("Error: Email channel checkbox is not checked");
		}
		return new CreateMemberPage(this.driver);
	}
	
	public CreateMemberPage deselectAppMailCheckBoxByListId(String listId) throws InterruptedException {
		String xpath="//input[@value='appMail' and contains(@id, '"+listId+"')]";
		WebElement element = this.driver.findElement(By.xpath(xpath));
		if(element.isSelected()) {
			testReporter.log(LogStatus.INFO, "Appmail channel checkbox is checked");
			SysLogger.log("Appmail channel checkbox is checked");
			this.firstNameTextBox.click();
			element.click();
			testReporter.log(LogStatus.INFO, "unchecked Appmail channel checkbox");
		}
		else {
			testReporter.log(LogStatus.INFO, "Error: Appmail channel checkbox is not checked");
			SysLogger.log("Error: Appmail channel checkbox is not checked");
		}
		return new CreateMemberPage(this.driver);
	}
	
	public CreateMemberPage deselectPNCheckBoxByListId(String listId) throws InterruptedException {
		String xpath="//input[@value='pushNotification' and contains(@id, '"+listId+"')]";
		WebElement element = this.driver.findElement(By.xpath(xpath));
		if(element.isSelected()) {
			testReporter.log(LogStatus.INFO, "PN channel checkbox is checked");
			SysLogger.log("PN channel checkbox is checked");
			this.firstNameTextBox.click();
			element.click();
			testReporter.log(LogStatus.INFO, "unchecked PN channel checkbox");
		}
		else {
			testReporter.log(LogStatus.INFO, "Error: PN channel checkbox is not checked");
			SysLogger.log("Error: PN channel checkbox is not checked");
		}
		return new CreateMemberPage(this.driver);
	}
	
	public CreateMemberPage checkAppmailCheckBoxByListId(String listId) {
		String xpath="//input[@value='appMail' and contains(@id, '"+listId+"') and not(@disabled)]";
		WebElement element = this.driver.findElement(By.xpath(xpath));
		if(element.isSelected()) {
			testReporter.log(LogStatus.INFO, "Appmail checkbox is already checked");
			SysLogger.log("Appmail checkbox is already checked");
		}
		else {
			this.firstNameTextBox.click();
			element.click();
			testReporter.log(LogStatus.INFO, "Appmail checkbox checked");
			SysLogger.log("Appmail checkbox checked");
		}
		return new CreateMemberPage(this.driver);
	}
	
	public CreateMemberPage checkEmailCheckBoxByListId(String listId) throws InterruptedException {
		String xpath="//input[@value='email' and contains(@id, '"+listId+"')]";
		WebElement element = this.driver.findElement(By.xpath(xpath));
		if(element.isSelected()) {
			testReporter.log(LogStatus.INFO, "Email checkbox is already checked");
			SysLogger.log("Email checkbox is already checked");
		}
		else {
			this.firstNameTextBox.click();
			element.click();
			Thread.sleep(5000);
			testReporter.log(LogStatus.INFO, "Email checkbox checked");
			SysLogger.log("Email checkbox checked");
		}
		return new CreateMemberPage(this.driver);
	}
	
	public CreateMemberPage checkSMSCheckBoxByListId(String listId) {
		String xpath="//input[@value='sms' and contains(@id, '"+listId+"')]";
		WebElement element = this.driver.findElement(By.xpath(xpath));
		if(element.isSelected()) {
			testReporter.log(LogStatus.INFO, "SMS checkbox is already checked");
			SysLogger.log("SMS checkbox is already checked");
		}
		else {
			this.firstNameTextBox.click();
			element.click();
			testReporter.log(LogStatus.INFO, "SMS checkbox checked");
			SysLogger.log("SMS checkbox checked");
		}
		return new CreateMemberPage(this.driver);
	}
	
	public CreateMemberPage checkPushNotificationCheckBoxByListId(String listId) {
		String xpath="//input[@value='pushNotification' and contains(@id, '"+listId+"') and not(@disabled)]";
		WebElement element = this.driver.findElement(By.xpath(xpath));
		if(element.isSelected()) {
			testReporter.log(LogStatus.INFO, "PN checkbox is already checked");
			SysLogger.log("PN checkbox is already checked");
		}
		else {
			this.firstNameTextBox.click();
			element.click();
			testReporter.log(LogStatus.INFO, "PN checkbox checked");
			SysLogger.log("PN checkbox checked");
		}
		return new CreateMemberPage(this.driver);
	}
	
}
