package com.application.objectRepository;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.application.factories.ApplicationDB;
import com.application.factories.ReporterFactory;
import com.application.utility.ConfigReader;
import com.application.utility.DriverMethods;
import com.application.utility.SysLogger;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CommunicationPage {

	WebDriver driver;
	ExtentTest testReporter;
	Actions act;
	WebDriverWait wait;
	
	@FindBy (xpath = "//a[@href='/home' and contains(text(), 'mPulse Mobile')]")
	private WebElement homePageLink;
	
	@FindBy (id = "list_campaign_new")
	private WebElement newCampaignButton;
	
	@FindBy (xpath = "//form[@id='form']/button")
	private WebElement campaignSearchButton;
	
	@FindBy (id = "list_campaign_name")
	private WebElement campaignNameTextBox;
	
	@FindBy (id = "list_campaign_start_date")
	private WebElement campaignStartDate;
	
	@FindBy (id = "list_campaign_end_date")
	private WebElement campaignEndDate;
	
	@FindBy (xpath = "//div[@class='ui-dialog-buttonset']/button[contains(@class, 'submit')]")
	private WebElement uiDialogYesButton;
	
	@FindBy (xpath = "//div[@class='ui-dialog-buttonset']/button")
	private WebElement campaignCloneOkButton;
	
	@FindBy(xpath = "//a[@href='/campaigns/message-upload']")
	private WebElement messageUploadLink;
	
	@FindBy(xpath = "//a[@class='Dialogues' and @href='/campaigns/dialogues']")
	private WebElement dialogue;
	
	@FindBy(xpath = "//a[@class='audience']")
	private WebElement audienceListsPageLink;
	
	@FindBy(xpath = "//a[@class='campaign_action approve tooltip']")
	private WebElement campaignApproveIcon;
	
	@FindBy (xpath = "//form[@id='approve_comment_form']//textarea[@class='schedule-comment-txt']")
	private WebElement approvalComment;
	
	@FindBy (xpath = "//div[@class='ui-dialog-buttonset']//span[@class='ui-button-text']")
	private WebElement campaignApproveSubmitButton;

	public CommunicationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
	}
	
	public boolean isCampaignApproveSubmitButtonPresent() {
		if(this.campaignApproveSubmitButton.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isCampaignApproveTextBoxPresent() {
		if(wait.until(ExpectedConditions.visibilityOf(this.approvalComment)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isCampaignApproveIconPresent() {
		if(this.campaignApproveIcon.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isCampaignSearchButtonPresent() {
		if(this.campaignSearchButton.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isNewCampaignButtonPresent() {
		if(this.newCampaignButton.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isCampaignNameTextBoxPresent() {
		if(this.campaignNameTextBox.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isCampaignStartDatePresent() {
		if(this.campaignStartDate.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isCampaignEndDatePresent() {
		if(this.campaignEndDate.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isCampaignCloneOkButtonPresent() {
		if(this.campaignCloneOkButton.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isDilogueLinkPresent() {
		if(this.dialogue.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isAudienceListsPageLinkPresent() {
		if (this.audienceListsPageLink.isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public CommunicationPage clickOnCampaignSearchButton() {
		if(this.isCampaignSearchButtonPresent()) {
			this.campaignSearchButton.click();
			testReporter.log(LogStatus.INFO, "Clicked on campaign search button");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			SysLogger.log("Error : Campaign search button not present");
		}
		return new CommunicationPage(this.driver);
	}
	
	public CommunicationPage clickOnCampaignCloneOkButton() {
		if(this.isCampaignCloneOkButtonPresent()) {
			this.campaignCloneOkButton.click();
			testReporter.log(LogStatus.INFO, "Clicked on campaign clone OK button");
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			SysLogger.log("Error : Campaign Clone OK button not present");
		}
		return new CommunicationPage(this.driver);
	}
	
	public CommunicationPage enterCampaignNameInSearchTextBox(String campaignName) {
		if(this.isCampaignNameTextBoxPresent()) {
			this.campaignNameTextBox.clear();
			this.campaignNameTextBox.sendKeys(campaignName);
			testReporter.log(LogStatus.INFO, "campaign name entered in text box name - "+campaignName);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			SysLogger.log("Error : Not able to click on Campaign search button, Campaign search button is not enabled");
		}
		return new CommunicationPage(this.driver);
	}
	
	public CommunicationPage enterCampaignStartDate(String startDate) {
		if(this.isCampaignStartDatePresent()) {
			this.campaignStartDate.clear();
			this.campaignStartDate.sendKeys(startDate);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			SysLogger.log("Error : Not able to enter Campaign Start date");
		}
		return new CommunicationPage(this.driver);
	}
	
	public CommunicationPage enterCampaignEndDate(String endDate) {
		if(this.isCampaignEndDatePresent()) {
			this.campaignEndDate.clear();
			this.campaignEndDate.sendKeys(endDate);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			SysLogger.log("Error : Not able to enter Campaign End date");
		}
		return new CommunicationPage(this.driver);
	}
	
	public boolean isCampaignPresentByName(String campName) {
		String xpath = "//*[@id='list_campaign_data']/tbody//a[contains(text(), '" + campName + "')]";
		try {
			Thread.sleep(12000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DriverMethods.isElementPresent(this.driver, this.homePageLink);
		if (this.driver.findElement(By.xpath(xpath)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isUiDialogYesButton() {
		if (this.uiDialogYesButton.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public CommunicationPage clickOnCampaignRetireLink(String campName) {
		if (this.isCampaignPresentByName(campName)) {
			String campId = this.getCampaignIdByName(campName);
			String xpath = "//*[@id='list_campaign_data']/tbody//a[contains(text(), '"+campName+"')]/following::td/a[@data-id='"+campId+"']";
			this.driver.findElement(By.xpath(xpath)).click();
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return new CommunicationPage(this.driver);
		}
		else {
			SysLogger.log("Error : Not able to click on new Campaign Retire button");
		}
		return new CommunicationPage(this.driver);
	}
	
	public CommunicationPage clickOnCloneLink(String campName) {
		if (this.isCampaignPresentByName(campName)) {
			String campId = this.getCampaignIdByName(campName);
			String xpath = "//*[@id='list_campaign_data']/tbody//a[contains(text(), '"+campName+"')]/following::td/a[@id='"+campId+"' and @class='campaign_action clone tooltip']";
			this.driver.findElement(By.xpath(xpath)).click();
			try {
				Thread.sleep(12000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return new CommunicationPage(this.driver);
		}
		else {
			SysLogger.log("Error : Not able to click on clone Campaign button");
		}
		return new CommunicationPage(this.driver);
	}
	
	public MessageUploadPage clickOnMessageUploadLink() {
		if (this.messageUploadLink.isDisplayed()) {
			this.messageUploadLink.click();
			return new MessageUploadPage(this.driver);
		}
		else {
			SysLogger.log("Error : Not able to click on message upload link");
		}
		return new MessageUploadPage(this.driver);
	}
	
	public CampaignPlanPage clickOnCampaignEditLink(String campName) {
		if (this.isCampaignPresentByName(campName)) {
			String campId = this.getCampaignIdByName(campName);
			String xpath = "//a[@id='"+campId+"' and contains (@class, 'edit')]";
			this.driver.findElement(By.xpath(xpath)).click();
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return new CampaignPlanPage(this.driver);
		}
		else {
			SysLogger.log("Error : Not able to click on new Campaign Retire button");
		}
		return new CampaignPlanPage(this.driver);
	}
	
	public ReportingPage clickOnCampaignReportLink(String campName) {
		String campId = this.getCampaignIdByName(campName);
		if (this.isCampaignPresentByName(campName)) {
			String reportPageLink = ConfigReader.getConfig("ENV")+"/reports/campaign/"+campId+"/performance";
			this.driver.navigate().to(reportPageLink);
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			testReporter.log(LogStatus.INFO, "Navigating to campaign report page");
			return new ReportingPage(this.driver);
		}
		else {
			SysLogger.log("Error : Not able to click on new Campaign Retire button");
		}
		return new ReportingPage(this.driver);
	}
	
	public CommunicationPage clickOnRetireYesButton(String campName) {
		if (this.isUiDialogYesButton()) {
			this.uiDialogYesButton.click();
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			DriverMethods.isElementPresent(this.driver, this.homePageLink);
			return new CommunicationPage(this.driver);
		}
		else {
			SysLogger.log("Error : Not able to click on Campaign Retire yes button");
		}
		return new CommunicationPage(this.driver);
	}
	
	private void waitTillCampaignMarkForDeletePresent(String campName) throws Exception {	
		Map<String, String> stringAttri;
		int maxCount = 10;
		do {
			Thread.sleep(5000);
			stringAttri = ApplicationDB.getResultMap("select count(*) from campaign where account_id ="+ConfigReader.getConfig("account_id")+" and mark_for_delete =true and name ilike '"+campName+"%'");
			maxCount--;
		} while (stringAttri.get("count") != "0" && maxCount != 0);
	}
	
	public CommunicationPage retireCampaignByNameOld(String campName) throws Exception {
		if (this.isCampaignPresentByName(campName)) {
			this.clickOnCampaignRetireLink(campName).clickOnRetireYesButton(campName);
			DriverMethods.isElementPresent(this.driver, this.homePageLink);
			this.waitTillCampaignMarkForDeletePresent(campName);
			return new CommunicationPage(this.driver);
		}
		else {
			SysLogger.log("Error : Not able to Retire Campaign, or campaign not Present");
		}
		return new CommunicationPage(this.driver);
	}
	
	public CommunicationPage pauseCampaignByName(String campName) throws Exception {
		String campId = this.getCampaignIdByName(campName);
		String xpath = "//table[@id='list_campaign_data']/tbody//a[@id='"+campId+"' and contains(@class, 'stop')]";
		WebElement pauseButton = this.driver.findElement(By.xpath(xpath));
		if (pauseButton.isDisplayed()) {
			pauseButton.click();
			Thread.sleep(1000);
			return new CommunicationPage(this.driver);
		}
		else {
			SysLogger.log("Error : Campaign pause button not displayed on Communication page for campaign "+campName);
		}
		return new CommunicationPage(this.driver);
	}
	
	public void retireCampaignByName(String campName) throws Exception {
		// Please do not remove this check. This is to avoid running DB call on Prod and upmc-stage
		if (!ConfigReader.getENVConfigValue().equalsIgnoreCase("prod") && !ConfigReader.getENVConfigValue().equalsIgnoreCase("upmc-stage")) {
		// Older retireCampaignByName method is taking more time, so created new method that will update record in DB
		// status 250 is for retired campaign and message
		String getCampIDbyName = "select id from campaign where name ='"+campName+"' and account_id="+ConfigReader.getConfig("account_id");
		String campId = ApplicationDB.getStringData(getCampIDbyName);
		testReporter.log(LogStatus.INFO, "Retiring campaign having name "+campName+" and id "+campId);
		ApplicationDB.updateData("update message set status =250 where campaign_id="+campId+" and account_id="+ConfigReader.getConfig("account_id"));
		ApplicationDB.updateData("update campaign set status =250 where id="+campId +" and account_id="+ConfigReader.getConfig("account_id"));
		testReporter.log(LogStatus.INFO, "Retired campaign having name "+campName+" and id "+campId);
		}
		else {
			this.pauseCampaignByName(campName);
		}
	}
	
	private String getCampaignIdByName(String campName) {
		String xpath = "//*[@id='list_campaign_data']/tbody//a[contains(text(), '" + campName + "')]";
			String pattern1 = "campaigns/";
			String pattern2 = "/goal";
			String text = this.driver.findElement(By.xpath(xpath)).getAttribute("href");
			Pattern p = Pattern.compile(Pattern.quote(pattern1) + "(.*?)" + Pattern.quote(pattern2));
			Matcher m = p.matcher(text);
			while (m.find()) {
			  return m.group(1);
			}
			return null;
	}
	
	public CampaignPlanPage clickOnCreateNewCampaignButton() {
		if(this.isNewCampaignButtonPresent()) {
			this.newCampaignButton.click();
			testReporter.log(LogStatus.INFO, "Clicked on create new campaign button");
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			SysLogger.log("Error : Not able to click on new Campaign button, new Campaign button is not enabled");
		}
		return new CampaignPlanPage(this.driver);
	}
	
	public DialoguePage clickOnDialogueLink() throws InterruptedException {
		if(this.isDilogueLinkPresent()) {
			this.dialogue.click();
			testReporter.log(LogStatus.INFO, "Clicked on Dialogue button");
			Thread.sleep(5000);
		}
		else {
			SysLogger.log("Error : Not able to click on Dialogue button, Dialogue button is not present on page");
		}
		return new DialoguePage(this.driver);
	}
	
	public AudienceListsPage goToAudienceListPage() throws InterruptedException {
		Thread.sleep(3000);
		if (isAudienceListsPageLinkPresent()) {
			this.audienceListsPageLink.click();
			testReporter.log(LogStatus.INFO, "Clicked on audience page link");
		} else {
			SysLogger.log("Error: Audience List page link not present");
			testReporter.log(LogStatus.ERROR, "Error: Audience List page link not present");
		}
		return new AudienceListsPage(this.driver);
	}
	
	public CommunicationPage clickApproveCampaignIcon(String campName) throws Exception {
		String campId = this.getCampaignIdByName(campName);
		String xpath = "//table[@id='list_campaign_data']/tbody//a[@id='"+campId+"' and contains(@class, 'approve')]";
		WebElement approveIcon = this.driver.findElement(By.xpath(xpath));
		if (approveIcon.isDisplayed()) {
			approveIcon.click();
			Thread.sleep(5000);
			return new CommunicationPage(this.driver);
		}
		else {
			SysLogger.log("Error : Approve Campaign button not displayed on Communication page for campaign "+campName);
		}
		return new CommunicationPage(this.driver);
	}
	
	public CommunicationPage enterApprovalComment(String comment) throws InterruptedException {
		Thread.sleep(5000);
		String xpath = "//form[@id='approve_comment_form']//textarea[@class='schedule-comment-txt']";
		WebElement enterApprovalComment = this.driver.findElement(By.xpath(xpath));
		if (enterApprovalComment.isDisplayed()) {
			enterApprovalComment.sendKeys(comment);
			Thread.sleep(5000);
			return new CommunicationPage(this.driver);
		}
		return new CommunicationPage(this.driver);		
	}
	
	public CommunicationPage clickCampaignApproveSubmitButton() throws InterruptedException {
		if(this.isCampaignApproveSubmitButtonPresent()) {
			campaignApproveSubmitButton.click();
			return new CommunicationPage(this.driver);
		}
		return new CommunicationPage(this.driver);		
	}

}
