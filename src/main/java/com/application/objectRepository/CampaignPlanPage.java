package com.application.objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.application.factories.ReporterFactory;
import com.application.utility.DriverMethods;
import com.application.utility.SysLogger;
import com.relevantcodes.extentreports.ExtentTest;

public class CampaignPlanPage {
	
	WebDriver driver;
	ExtentTest testReporter;
	Actions act;
	
	@FindBy (xpath = "//a[@href='/home' and contains(text(), 'mPulse Mobile')]")
	private WebElement homePageLink;
	
	@FindBy (xpath = "//div[@id='common_workarea']/div[@class='progress-bar']/div[@class='loadingmessage']/span")
	private WebElement progressBarLoader;
	
	@FindBy (id = "name")
	private WebElement campaignNameTextBox;
	
	@FindBy (id= "listId")
	private WebElement listSelectDropDown;
	
	@FindBy (xpath = "//div[@class='sending-sms-list-wrapper']//span/button")
	private WebElement applySegmentsButton;
	
	@FindBy (id = "emailChannels")
	private WebElement emailChannelsCheckBox;
	
	@FindBy (id = "smsChannels")
	private WebElement smsChannelsCheckBox;
	
	@FindBy (id = "appMailChannels")
	private WebElement appMailChannelsCheckBox;
	
	@FindBy (id = "pnChannels")
	private WebElement pnChannelsCheckBox;
	
	@FindBy (id = "goal_submit")
	private WebElement saveAndNextButton;
	
	@FindBy (xpath = "//a[@href='/campaigns' and @class='common_form_cancel back_to_campaign']")
	private WebElement backToCampaignsLink;
	
	@FindBy (xpath = "//button[@type='button' and @role='button' and contains(@class,'submit')]")
	private WebElement selectSegmentOKButton;
	
	@FindBy (xpath = "//button[@type='button' and @role='button' and contains(@class,'cancel')]")
	private WebElement selectSegmentCancelButton;
	
	@FindBy (id = "segmentName")
	private WebElement compositeSegmentNameTextBox;
	
	@FindBy (id = "applied_segment_header")
	private WebElement appliedSegmentHeader;
	
	@FindBy(xpath = "//label[@id='channel_sms']/following-sibling::span")
	private WebElement smsMemberCount;
	
	@FindBy(xpath = "//label[@id='channel_email']/following-sibling::span")
	private WebElement emailMemberCount;
	
	@FindBy(xpath = "//label[@id='channel_appmail']/following-sibling::span")
	private WebElement appmailMemberCount;
	
	@FindBy(xpath = "//label[@id='channel_pn']/following-sibling::span")
	private WebElement pnMemberCount;

	public CampaignPlanPage(WebDriver driver) {
		this.driver = driver;
		act = new Actions(driver);
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
	}
	
	public boolean isCampaignNameTextBoxPresent() {
		if(this.campaignNameTextBox.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isListSelectDropDownPresent() {
		if(this.listSelectDropDown.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isApplySegmentsButtonPresent() {
		if(this.applySegmentsButton.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isEmailChannelsCheckBoxPresent() {
		if(this.emailChannelsCheckBox.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isSmsChannelsCheckBoxPresent() {
		if(this.smsChannelsCheckBox.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isAppMailChannelsCheckBoxPresent() {
		if(this.appMailChannelsCheckBox.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean ispnChannelsCheckBoxPresent() {
		if(this.pnChannelsCheckBox.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isSaveAndNextButtonPresent() {
		if(this.saveAndNextButton.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isBackToCampaignsLinkPresent() {
		if(this.backToCampaignsLink.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isSelectSegmentOKButtonPresent() {
		if(this.selectSegmentOKButton.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isSelectSegmentCancelButtonPresent() {
		if(this.selectSegmentCancelButton.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isCompositeSegmentNameTextBoxPresent() {
		if(this.compositeSegmentNameTextBox.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isHomeButtonPresent() {
		if (this.homePageLink.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public CampaignPlanPage enterCampaignName(String name) {
		if(this.isCampaignNameTextBoxPresent()) {
			this.campaignNameTextBox.clear();
			this.campaignNameTextBox.sendKeys(name);
		}
		else {
			SysLogger.log("Error : Not able to enter Campaign name");
		}
		return new CampaignPlanPage(this.driver);
	}
	
	public CampaignPlanPage checkEmailChannelsCheckBox() {
		if(this.isEmailChannelsCheckBoxPresent()) {
			this.emailChannelsCheckBox.click();
		}
		else {
			SysLogger.log("Error : Not able to select Email check box");
		}
		return new CampaignPlanPage(this.driver);
	}
	
	public CampaignPlanPage checkSmsChannelsCheckBox() {
		if(this.isSmsChannelsCheckBoxPresent()) {
			act.moveToElement(this.smsChannelsCheckBox).build().perform();
			this.smsChannelsCheckBox.click();
		}
		else {
			SysLogger.log("Error : Not able to select SMS check box");
		}
		return new CampaignPlanPage(this.driver);
	}
	
	public CampaignPlanPage checkAppMailChannelsCheckBox() {
		if(this.isAppMailChannelsCheckBoxPresent()) {
			this.appMailChannelsCheckBox.click();
		}
		else {
			SysLogger.log("Error : Not able to select Appmail check box");
		}
		return new CampaignPlanPage(this.driver);
	}
	
	public CampaignPlanPage checkPnChannelsCheckBox() {
		if(this.ispnChannelsCheckBoxPresent()) {
			this.pnChannelsCheckBox.click();
		}
		else {
			SysLogger.log("Error : Not able to select PN check box");
		}
		return new CampaignPlanPage(this.driver);
	}
	
	public CampaignComposePage clickOnSaveAndNextButton() throws InterruptedException {
		if(this.isSaveAndNextButtonPresent()) {
			act.moveToElement(this.saveAndNextButton).build().perform();
			this.saveAndNextButton.click();
			DriverMethods.waitForElementUntillDisappear(this.driver, this.progressBarLoader);
			//Thread.sleep(10000);
			DriverMethods.isElementPresent(this.driver, this.homePageLink);
		}
		else {
			SysLogger.log("Error : Not able to click on save and next button");
		}
		return new CampaignComposePage(this.driver);
	}
	
	private boolean isSegmentApplied(){
		if(this.appliedSegmentHeader.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public CampaignPlanPage clickOnSelectSegmentOKButton() throws InterruptedException {
		if (this.isSelectSegmentOKButtonPresent()) {
			act.moveToElement(this.selectSegmentOKButton).build().perform();
			this.selectSegmentOKButton.click();
		} else {
			SysLogger.log("Error : Not able to click on select segment OK button");
		}
		if (this.isSegmentApplied()) {
			return new CampaignPlanPage(this.driver);
		} else {
			SysLogger.log("Error : Segment not applied on the campaign plan page");
			Assert.assertTrue(false, "Error : Segment not applied on the campaign plan page");
		}
		return new CampaignPlanPage(this.driver);
	}
	
	public CommunicationPage clickOnBackToCampaignsLink() {
		if(this.isBackToCampaignsLinkPresent()) {
			this.backToCampaignsLink.click();
		}
		else {
			SysLogger.log("Error : Not able to click on Back to campaign link");
		}
		return new CommunicationPage(this.driver);
	}
	
	public CampaignPlanPage clickOnSelectSegmentCancelButton() {
		if(this.isSelectSegmentCancelButtonPresent()) {
			this.selectSegmentCancelButton.click();
		}
		else {
			SysLogger.log("Error : Not able to click on select segment Cancel button");
		}
		return new CampaignPlanPage(this.driver);
	}
	
	public CampaignPlanPage clickOnApplySegmentsButton() throws InterruptedException {
		if(this.isApplySegmentsButtonPresent()) {
			this.applySegmentsButton.click();
			Thread.sleep(15000); // wait for segment selection page to open
		}
		else {
			SysLogger.log("Error : Not able to click on apply segment button");
		}
		return new CampaignPlanPage(this.driver);
	}
	
	public CampaignPlanPage enterCompositeSegmentName(String segmentName) {
		if(this.isCompositeSegmentNameTextBoxPresent()) {
			this.compositeSegmentNameTextBox.sendKeys(segmentName);
		}
		else {
			SysLogger.log("Error : Not able to enter composite segment name");
		}
		return new CampaignPlanPage(this.driver);
	}
	
	public CampaignPlanPage selectSegmentByName(String segmentName) throws InterruptedException {
		String xpath = "//label[contains(text(),'"+segmentName+"')]/preceding-sibling::input";
		WebElement element = this.driver.findElement(By.xpath(xpath));
		if(DriverMethods.isElementPresent(this.driver, element)){
			act.moveToElement(element).click().build().perform();
			Thread.sleep(3000); // waiting for segment to properly select
			return new CampaignPlanPage(this.driver);
		} else {
			SysLogger.log("Error : Segment '"+segmentName+"' is not displayed on page");
		}
		return new CampaignPlanPage(this.driver);
	}
	
	public CampaignPlanPage selectSegmentBySegmentID(int segmentID) throws InterruptedException {
		String xpath = "//input[@type='checkbox' and @value='"+segmentID+"']";
		WebElement element = this.driver.findElement(By.xpath(xpath));
		Thread.sleep(3000);
		act.moveToElement(element).build().perform();
		element.click();
		return new CampaignPlanPage(this.driver);
	}
	
	public CampaignPlanPage selectListByListName(String listName) {
		if (this.isListSelectDropDownPresent()) {
			Select sc = new Select(this.listSelectDropDown);
			sc.selectByVisibleText(listName);
		}
		else {
			SysLogger.log("Error : Not able to select List by List name");
		}
		return new CampaignPlanPage(this.driver);
	}
	
	public CampaignPlanPage selectListByListID(int listID) {
		if (this.isListSelectDropDownPresent()) {
			Select sc = new Select(this.listSelectDropDown);
			sc.selectByValue(Integer.toString(listID));
		}
		else {
			SysLogger.log("Error : Not able to select List by ID");
		}
		return new CampaignPlanPage(this.driver);
	}
	
	public HomePage goToHomePage() {
		if (this.isHomeButtonPresent()) {
			this.homePageLink.click();
		} else {
			SysLogger.log("Error: Home page link not present");
		}
		return new HomePage(this.driver);
	}
	
	public int getSMSMemberCount() {
		if (this.smsMemberCount.isDisplayed()) {
			String text = this.smsMemberCount.getText().replace("member", "")
					.replace("s", "").replace("(", "").replace(")", "").replace(",", "").replace(" ", "").trim();
			return Integer.parseInt(text);
		}
		else {
			SysLogger.log("Error : Member count for SMS Channel is not displayed");
		}
		return 0;
	}
	
	public int getEmailMemberCount() {
		if (this.emailMemberCount.isDisplayed()) {
			String text = this.emailMemberCount.getText().replace("member", "")
					.replace("s", "").replace("(", "").replace(")", "").replace(",", "").replace(" ", "").trim();
			return Integer.parseInt(text);
		}
		else {
			SysLogger.log("Error : Member count for Email Channel is not displayed");
		}
		return 0;
	}
	
	public int getAppmailMemberCount() {
		if (this.appmailMemberCount.isDisplayed()) {
			String text = this.appmailMemberCount.getText().replace("member", "")
					.replace("s", "").replace("(", "").replace(")", "").replace(",", "").replace(" ", "").trim();
			return Integer.parseInt(text);
		}
		else {
			SysLogger.log("Error : Member count for Appmail Channel is not displayed");
		}
		return 0;
	}
	
	public int getPNMemberCount() {
		if (this.pnMemberCount.isDisplayed()) {
			String text = this.pnMemberCount.getText().replace("member", "")
					.replace("s", "").replace("(", "").replace(")", "").replace(",", "").replace(" ", "").trim();
			return Integer.parseInt(text);
		}
		else {
			SysLogger.log("Error : Member count for PN Channel is not displayed");
		}
		return 0;
	}

}
