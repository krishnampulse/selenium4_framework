package com.application.objectRepository;

import java.util.ArrayList;

import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class MemberSubscriptionPreferencePage {

	WebDriver driver;
	ExtentTest testReporter;
	WebDriverWait wait;
	Actions act;
	
	@FindBy(xpath="//img[contains(@src, 'mpulse-logo.png')]")
	private WebElement mPulseLogoImage;
							
	@FindBy(className="headline")
	private WebElement introHeadingText;
	
	@FindBy(xpath=("//div[@class='smallfont col-md-12']"))
	private WebElement introMsgText;
	
	@FindBy(xpath="//h4[@class='subscription-preference' and contains(text(), 'Choose your communication preferences')]")
	private WebElement channelHeaderText;
	
	@FindBy(xpath="//p[@class='subtext']")
	private WebElement channelMsgText;
	
	@FindBy(xpath="//div[@class='channelcheck col-md-12']//following-sibling::p[@class='subtext']")
	private WebElement channelFooterText;
	
	@FindBy(xpath="//h4[@class='content-preference']")
	private WebElement customFieldHeaderText;
	
	@FindBy(xpath="//h4[@class='content-preference']//following-sibling::p[@class='subtext']")
	private WebElement customFieldMsgText;
	
	@FindBy(xpath ="//div[@class='typeofInfo custom_picklist checkbox-div']/child::label[@class='label-item']")
	private WebElement picklistCustomFieldName;
	
	@FindBy(xpath ="//div[@class='typeofInfo custom_picklist checkbox-div']/child::label[@class='label-item']/child::input[@type='checkbox']")
	private WebElement picklistCustomFieldCheckbox;
	
	@FindBy(id="unsub_all")
	private WebElement unsubAllCheckbox;
	
	@FindBy(xpath="//label[@class='label-item' and @for='unsub_all']")
	private WebElement unsubMsgText;
	
	@FindBy(xpath="//a[contains(@href, 'https')]")
	private WebElement footerLinkLabelText;
	
	@FindBy(className="submit_btn")
	private WebElement saveButton;
		
	@FindBy(className="headline")
	private WebElement thankYouHeaderText;
	
	@FindBy(xpath ="//div[@class='smallfont col-md-12']")
	private WebElement thankYouMsgText;		
	
	public MemberSubscriptionPreferencePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
		wait = new WebDriverWait(driver, 120);
		act = new Actions(driver);
		}
	
	public boolean isMpulseLogoImageDisplayed() {
		if(this.wait.until(ExpectedConditions.visibilityOf(this.mPulseLogoImage)).isDisplayed()) {
			return true;
		}
		return false;		
	}
	
	public boolean isCustomLogoImageDisplayed(String imageName) {
		WebElement customLogo = this.driver.findElement(By.xpath("//img[contains(@src, '"+imageName+"')]"));
		if(this.wait.until(ExpectedConditions.visibilityOf(customLogo)).isDisplayed()) {
			return true;
		}
		return false;		
	}
		
	public boolean isMemberFieldTextBoxDisplayed(String memberFieldMappedName) {
		WebElement memberFieldTextBox = driver.findElement(By.id(memberFieldMappedName));
		if(this.wait.until(ExpectedConditions.visibilityOf(memberFieldTextBox)).isDisplayed()) {
			return true;
		}
		SysLogger.log("Error : "+memberFieldMappedName+" is not displayed");
		testReporter.log(LogStatus.INFO, "Error : "+memberFieldMappedName+" is not displayed");
		return false;		
	}
		
	public boolean isPicklistCustomFieldDisplayed() {
		if(this.wait.until(ExpectedConditions.visibilityOf(this.picklistCustomFieldName)).isDisplayed()) {
			return true;
		}
		SysLogger.log("Error : "+picklistCustomFieldName+" is not displayed");
		testReporter.log(LogStatus.INFO, "Error : "+picklistCustomFieldName+" is not displayed");
		return false;		
	}
	
	public boolean isListNameDisplayed(String listName) {
		WebElement listNameLabel = driver.findElement(By.xpath("//label[@title='"+listName+"']"));
		if(this.wait.until(ExpectedConditions.visibilityOf(listNameLabel)).isDisplayed()) {
			return true;
		}
		SysLogger.log("Error : "+listName+" is not displayed");
		testReporter.log(LogStatus.INFO, "Error : "+listName+" is not displayed");
		return false;		
	}
	
	public MemberSubscriptionPreferencePage enterTextInMemberField(String text, String memberFieldMappedName) {
		WebElement memberFieldTextBox = driver.findElement(By.id(memberFieldMappedName));
		if(this.isMemberFieldTextBoxDisplayed(memberFieldMappedName)) {
			memberFieldTextBox.sendKeys(text);
		}
		else {
			SysLogger.log("Error : "+memberFieldMappedName+" is not displayed");
			testReporter.log(LogStatus.INFO, "Error : "+memberFieldMappedName+" is not displayed");
		}	
		return new MemberSubscriptionPreferencePage(driver);
	}
	
	public String getValueFromMemberField(String memberFieldMappedName) {
		WebElement memberFieldTextBox = driver.findElement(By.id(memberFieldMappedName));
		if(this.isMemberFieldTextBoxDisplayed(memberFieldMappedName)) {
			return memberFieldTextBox.getAttribute("value");
		}
		else {
			SysLogger.log("Error : "+memberFieldMappedName+" field is not displayed");
			testReporter.log(LogStatus.INFO, "Error : "+memberFieldMappedName+" field is not displayed");
			return null;
		}	
	}
		
	public MemberSubscriptionPreferencePage selectMultiPicklistCustomFieldCheckbox(String multiPicklistTagName, String multiPicklistValue) {
		WebElement multiPicklistCheckbox = driver.findElement(By.xpath("//input[@name='"+multiPicklistTagName+"' and @value='"+multiPicklistValue+"']"));
		if(multiPicklistCheckbox.isDisplayed()) {
			try {
				if(!multiPicklistCheckbox.isSelected())
					multiPicklistCheckbox.click();
			}
			catch(Exception e) {
				SysLogger.log("Multi Picklist with value "+multiPicklistValue+" is already checked");
			}
		}
		else {
			SysLogger.log("Error : Multi Picklist Custom Field Checkbox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Multi Picklist Custom Field Checkbox is not displayed");
		}	
		return new MemberSubscriptionPreferencePage(driver);
	}
	
	public boolean isMultiPicklistCustomFieldCheckboxSelected(String multiPicklistTagName, String multiPicklistValue) {
		WebElement multiPicklistCheckbox = driver.findElement(By.xpath("//input[@name='"+multiPicklistTagName+"' and @value='"+multiPicklistValue+"']"));
		if(multiPicklistCheckbox.isDisplayed()) {
				if(multiPicklistCheckbox.isSelected()) {
					return true;
			}
		}
		else {
			SysLogger.log("Error : Multi Picklist Custom Field Checkbox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Multi Picklist Custom Field Checkbox is not displayed");
		}	
		return false;
	}
	
	public MemberSubscriptionPreferencePage selectPicklistCustomFieldCheckbox() {
		if(this.picklistCustomFieldCheckbox.isDisplayed()) {
			try {
				if(!this.picklistCustomFieldCheckbox.isSelected())
					this.picklistCustomFieldCheckbox.click();
			}
			catch(Exception e) {
				SysLogger.log("Picklist Custom field checkbox is already checked");
			}
		}
		else {
			SysLogger.log("Error : Picklist Custom Field Checkbox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Picklist Custom Field Checkbox is not displayed");
		}	
		return new MemberSubscriptionPreferencePage(driver);
	}
	
	public boolean isPicklistCustomFieldCheckboxSelected() {
		if(this.picklistCustomFieldCheckbox.isDisplayed()) {
			if(this.picklistCustomFieldCheckbox.isSelected()) {
				return true;
			}
		}
		else {
			SysLogger.log("Error : Picklist Custom Field Checkbox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Picklist Custom Field Checkbox is not displayed");
		}
		return false;	
	}
	
	public MemberSubscriptionPreferencePage selectListSmsCheckbox(String listName) {
		WebElement listSmsCheckbox = driver.findElement(By.xpath("//label[@title='"+listName+"']//following-sibling::div[@class='channelcheckinput']//descendant::input[contains(@name, 'sms_subscription')]"));
		if(listSmsCheckbox.isDisplayed()) {
			try {
				if(!listSmsCheckbox.isSelected())
					listSmsCheckbox.click();
			}
			catch(Exception e) {
				SysLogger.log("List SMS checkbox is already checked");
			}
		}
		else {
			SysLogger.log("Error : List SMS Checkbox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : List SMS Checkbox is not displayed");
		}	
		return new MemberSubscriptionPreferencePage(driver);
	}
	
	public MemberSubscriptionPreferencePage selectListEmailCheckbox(String listName) {
		WebElement listEmailCheckbox = driver.findElement(By.xpath("//label[@title='"+listName+"']//following-sibling::div[@class='channelcheckinput']//descendant::input[contains(@name, 'email_subscription')]"));
		if(listEmailCheckbox.isDisplayed()) {
			try {
				if(!listEmailCheckbox.isSelected())
					listEmailCheckbox.click();
			}
			catch(Exception e) {
				SysLogger.log("List Email checkbox is already checked");
			}
		}
		else {
			SysLogger.log("Error : List Email Checkbox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : List Email Checkbox is not displayed");
		}	
		return new MemberSubscriptionPreferencePage(driver);
	}
	
	public MemberSubscriptionPreferencePage deselectListSmsCheckbox(String listName) {
		WebElement listSmsCheckbox = driver.findElement(By.xpath("//label[@title='"+listName+"']//following-sibling::div[@class='channelcheckinput']//descendant::input[contains(@name, 'sms_subscription')]"));
		if(listSmsCheckbox.isDisplayed()) {
			try {
				if(listSmsCheckbox.isSelected())
					listSmsCheckbox.click();
			}
			catch(Exception e) {
				SysLogger.log("List SMS checkbox is already checked");
			}
		}
		else {
			SysLogger.log("Error : List SMS Checkbox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : List SMS Checkbox is not displayed");
		}	
		return new MemberSubscriptionPreferencePage(driver);
	}
	
	public MemberSubscriptionPreferencePage deselectListEmailCheckbox(String listName) {
		WebElement listEmailCheckbox = driver.findElement(By.xpath("//label[@title='"+listName+"']//following-sibling::div[@class='channelcheckinput']//descendant::input[contains(@name, 'email_subscription')]"));
		if(listEmailCheckbox.isDisplayed()) {
			try {
				if(listEmailCheckbox.isSelected())
					listEmailCheckbox.click();
			}
			catch(Exception e) {
				SysLogger.log("List Email checkbox is already checked");
			}
		}
		else {
			SysLogger.log("Error : List Email Checkbox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : List Email Checkbox is not displayed");
		}	
		return new MemberSubscriptionPreferencePage(driver);
	}
	
	public String getChannelFooterText() {
		if(this.channelFooterText.isDisplayed()) {
			return this.channelFooterText.getText();
		}
		else {
			SysLogger.log("Error : Channel Footer Text is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Channel Footer Text is not displayed");
			return null;
		}		
	}
	
	public String getChannelHeaderText() {
		if(this.channelHeaderText.isDisplayed()) {
			return this.channelHeaderText.getText();
		}
		else {
			SysLogger.log("Error : Channel Header Text is not displayed");	
			testReporter.log(LogStatus.INFO, "Error : Channel Header Text is not displayed");
		}
		return null;
	}
	
	public String getChannelMsgText() {
		if(this.channelMsgText.isDisplayed()) {
			return this.channelMsgText.getText();
		}
		else {
			SysLogger.log("Error : Channel Message Text is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Channel Message Text is not displayed");
		}
		return null;		
	}
	
	public String getCustomFieldHeaderText() {
		if(this.customFieldHeaderText.isDisplayed()) {
			return this.customFieldHeaderText.getText();
		}
		else {
			SysLogger.log("Error : Custom Field Header Text is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Custom Field Header Text is not displayed");
			return null;
		}
	}
	
	public String getCustomFieldMsgText() {
		if(this.customFieldMsgText.isDisplayed()) {
			return this.customFieldMsgText.getText();
		}
		else {
			SysLogger.log("Error : Custom Field Message Text is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Custom Field Message Text is not displayed");
			return null;
		}
	}
	
	public String getFooterLinkLabelText() {
		if(this.footerLinkLabelText.isDisplayed()) {
			return this.footerLinkLabelText.getText();
		}
		else {
			SysLogger.log("Error : Footer Link Label Text is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Footer Link Label Text is not displayed");
			return null;
		}
	}
	
	public String getFooterLinkURL() {
		if(this.footerLinkLabelText.isDisplayed()) {
			this.footerLinkLabelText.click();
			ArrayList<String> totalTabs = new ArrayList<>(this.driver.getWindowHandles());
			String footerLinkURL = "";
			try {
				totalTabs = new ArrayList<>(this.driver.getWindowHandles());
				this.driver.switchTo().window(totalTabs.get(1));
				footerLinkURL = this.driver.getCurrentUrl();
				driver.close();
			} catch (Exception e) {
				SysLogger.log("Error: Error occurred while switching to new window and getting Current URL");
				e.printStackTrace();
			}
			this.driver.switchTo().window(totalTabs.get(0));
			return footerLinkURL;
		}
		else {
			SysLogger.log("Error : Footer Link is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Footer Link is not displayed");
			return null;
		}
	}
	
	public String getIntroHeadingText() {
		if(this.introHeadingText.isDisplayed()) {
			return this.introHeadingText.getText();
		}
		else {
			SysLogger.log("Error : Intro Header Text is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Intro Header Text is not displayed");
			return null;
		}
	}
	
	public String getIntroMsgText() {
		if(this.introMsgText.isDisplayed()) {
			return this.introMsgText.getText();
		}
		else {
			SysLogger.log("Error : Intro Message Text is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Intro Message Text is not displayed");
			return null;
		}
	}
	
	public MemberSubscriptionPreferencePage clickOnSaveButton() {
		if(this.saveButton.isDisplayed()) {
			this.saveButton.click();
		}
		else 
			SysLogger.log("Error : Save Button is not displayed");
		return new MemberSubscriptionPreferencePage(this.driver);		
	}

	public String getThankYouHeaderText() {
		if(this.thankYouHeaderText.isDisplayed()) {
			return this.thankYouHeaderText.getText();
		}
		else {
			SysLogger.log("Error : Thank You Header Text is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Thank You Header Text is not displayed");
			return null;
		}
	}
	
	public String getThankYouMsgText() {
		if(this.thankYouMsgText.isDisplayed()) {
			return this.thankYouMsgText.getText();
		}
		else {
			SysLogger.log("Error : Thank You Message Text is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Thank You Message Text is not displayed");
			return null;
		}
	}
	
	public MemberSubscriptionPreferencePage selectUnsubAllCheckbox() {
		if(this.unsubAllCheckbox.isDisplayed()) {
			try {
				if(!this.unsubAllCheckbox.isSelected())
					this.unsubAllCheckbox.click();
			}
			catch(Exception e) {
				SysLogger.log("Unsub All checkbox is already checked");
			}
		}
		else {
			SysLogger.log("Error : Unsub All Checkbox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Unsub All Checkbox is not displayed");
		}	
		return new MemberSubscriptionPreferencePage(driver);
	}
	
	public String getUnsubMsgText() {
		if(this.unsubMsgText.isDisplayed()) {
			return this.unsubMsgText.getText();
		}
		else {
			SysLogger.log("Error : Unsub Message Text is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Unsub Message Text is not displayed");
			return null;
		}
	}
	
}
