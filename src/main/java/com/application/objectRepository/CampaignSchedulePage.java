package com.application.objectRepository;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.application.factories.ReporterFactory;
import com.application.utility.DriverMethods;
import com.relevantcodes.extentreports.ExtentTest;

public class CampaignSchedulePage {
	
	WebDriver driver;
	ExtentTest testReporter;
	Actions act;
	JavascriptExecutor js;
	WebDriverWait wait;
	
	@FindBy (xpath = "//a[@href='/home' and contains(text(), 'mPulse Mobile')]")
	private WebElement homePageLink;
	
	@FindBy (xpath = "//div[@id='common_workarea']/div[@class='progress-bar']/div[@class='loadingmessage']/span")
	private WebElement progressBarLoader;
	
	@FindBy (id = "campaign_submit")
	private WebElement launchCampaignButton;
	
	@FindBy (id = "campaign_save")
	private WebElement saveAsDraftButton;
	
	@FindBy (xpath = "//input[@name='endType' and @value='SCHEDULED']")
	private WebElement endDateRadioButton;
	
	@FindBy (id = "sms_endDate")
	private WebElement endDateInputBox;
	
	@FindBy (id = "sms_endTime")
	private WebElement endTimeInputBox;
	
	@FindBy (xpath = "//li[@id='schedule_step']//a[@class='common_form_cancel']")
	private WebElement backButton;
	
	@FindBy (xpath = "//select[@id='timeZone' and @name='timeZone']")
	private WebElement timeZoneSelectBox;
	
	@FindBy (id = "submit_approval")
	private WebElement SubmitForApprovalButton;
	
	@FindBy (id = "comment")
	private WebElement SubmitForApprovalComment;
	
	@FindBy (xpath = "//button[@id = 'button_ok']/span[@class= 'ui-button-text']")
	private WebElement SubmitButton;
	
	public CampaignSchedulePage(WebDriver driver) {
		this.driver = driver;
		act = new Actions(driver);
		js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
		wait = new WebDriverWait(driver, 120);
	}
	
	public boolean isSubmitButtonPresent() {
		if(wait.until(ExpectedConditions.visibilityOf(this.SubmitButton)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isSubmitForApprovalCommentTextBoxPresent() {
		if(wait.until(ExpectedConditions.visibilityOf(this.SubmitForApprovalComment)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isLaunchCampaignButtonPresent() {
		if(wait.until(ExpectedConditions.visibilityOf(this.launchCampaignButton)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isSaveAsDraftButtonPresent() {
		if(wait.until(ExpectedConditions.visibilityOf(this.saveAsDraftButton)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isEndDateRadioButtonPresent() {
		if(this.endDateRadioButton.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isBackButtonPresent() {
		if(this.backButton.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isEndDateInputBoxPresent() {
		if(this.endDateInputBox.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isEndTimeInputBoxPresent() {
		if(this.backButton.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isSubmitForApprovalButtonPresent() {
		if(wait.until(ExpectedConditions.visibilityOf(this.SubmitForApprovalButton)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public CommunicationPage clickOnLaunchCampaignButton() throws InterruptedException {
		Thread.sleep(8000);
		DriverMethods.waitForElementUntillDisappear(this.driver, this.progressBarLoader);
		if(this.isLaunchCampaignButtonPresent()) {
			act.moveToElement(this.launchCampaignButton).build().perform();
			this.launchCampaignButton.click();
			Thread.sleep(18000);
			DriverMethods.waitForElementUntillDisappear(this.driver, this.progressBarLoader);
			DriverMethods.isElementPresent(this.driver, this.homePageLink);
			return new CommunicationPage(this.driver);
		}
		return new CommunicationPage(this.driver);
		
	}
	
	public CommunicationPage clickOnSaveAsDraftButton() throws InterruptedException {
		DriverMethods.waitForElementUntillDisappear(this.driver, this.progressBarLoader);
		if(this.isSaveAsDraftButtonPresent()) {
			act.moveToElement(this.saveAsDraftButton).build().perform();
			this.saveAsDraftButton.click();
			Thread.sleep(3000);
			DriverMethods.waitForElementUntillDisappear(this.driver, this.progressBarLoader);
			DriverMethods.isElementPresent(this.driver, this.homePageLink);
			return new CommunicationPage(this.driver);
		}
		return new CommunicationPage(this.driver);
		
	}
	
	public CampaignComposePage clickOnBackButton() {
		if(this.isLaunchCampaignButtonPresent()) {
			act.moveToElement(this.backButton).build().perform();
			this.backButton.click();
			return new CampaignComposePage(this.driver);
		}
		return new CampaignComposePage(this.driver);
		
	}
	
	public CampaignSchedulePage checkEndDateRadioButton() {
		if(this.isEndDateRadioButtonPresent()) {
			act.moveToElement(this.endDateRadioButton).build().perform();
			this.endDateRadioButton.click();
			return new CampaignSchedulePage(this.driver);
		}
		return new CampaignSchedulePage(this.driver);
		
	}
	
	public CampaignSchedulePage enterEndDate(String date) {
		if(this.isEndDateInputBoxPresent()) {
			act.moveToElement(this.endDateInputBox).build().perform();
			this.endDateInputBox.sendKeys(date);
			return new CampaignSchedulePage(this.driver);
		}
		return new CampaignSchedulePage(this.driver);		
	}
	
	public CampaignSchedulePage enterEndTime(String time) {
		if(this.isEndDateInputBoxPresent()) {
			act.moveToElement(this.endTimeInputBox).build().perform();
			this.endTimeInputBox.sendKeys(time);
			return new CampaignSchedulePage(this.driver);
		}
		return new CampaignSchedulePage(this.driver);		
	}
	
	public CampaignSchedulePage selectTimeZone(String timeZone) {
		if(wait.until(ExpectedConditions.visibilityOf(this.timeZoneSelectBox)).isDisplayed()) {
			Select sc = new Select(this.timeZoneSelectBox);
			sc.selectByVisibleText(timeZone);
			return new CampaignSchedulePage(this.driver);
		}
		return new CampaignSchedulePage(this.driver);
	}
	
	public CampaignSchedulePage selectIstTimeZone() {
		if(wait.until(ExpectedConditions.visibilityOf(this.timeZoneSelectBox)).isDisplayed()) {
			Select sc = new Select(this.timeZoneSelectBox);
			sc.selectByVisibleText("(GMT+05:30) India Standard Time");
			return new CampaignSchedulePage(this.driver);
		}
		return new CampaignSchedulePage(this.driver);
	}	
	
	public CampaignSchedulePage clickOnSubmitForApprovalButton() throws InterruptedException {
		Thread.sleep(8000);
		DriverMethods.waitForElementUntillDisappear(this.driver, this.progressBarLoader);
		if(this.isSubmitForApprovalButtonPresent()) {
			act.moveToElement(this.SubmitForApprovalButton).build().perform();
			this.SubmitForApprovalButton.click();
			Thread.sleep(18000);
			DriverMethods.waitForElementUntillDisappear(this.driver, this.progressBarLoader);
			DriverMethods.isElementPresent(this.driver, this.homePageLink);
			return new CampaignSchedulePage(this.driver);
		}
		return new CampaignSchedulePage(this.driver);
		
	}
	public CampaignSchedulePage enterSubmitForApprovalComment(String comment) throws InterruptedException {
		Thread.sleep(5000);
		if(this.isSubmitForApprovalCommentTextBoxPresent()) {
			act.moveToElement(this.SubmitForApprovalComment).build().perform();
			this.SubmitForApprovalComment.sendKeys(comment);
			return new CampaignSchedulePage(this.driver);
		}
		return new CampaignSchedulePage(this.driver);		
	}
	
	public CommunicationPage clickOnSubmitButton() throws InterruptedException {
		Thread.sleep(4000);
		DriverMethods.waitForElementUntillDisappear(this.driver, this.progressBarLoader);
		if(this.isSubmitForApprovalButtonPresent()) {
			act.moveToElement(this.SubmitButton).build().perform();
			this.SubmitButton.click();
			Thread.sleep(18000);
			DriverMethods.waitForElementUntillDisappear(this.driver, this.progressBarLoader);
			DriverMethods.isElementPresent(this.driver, this.homePageLink);
			return new CommunicationPage(this.driver);
		}
		return new CommunicationPage(this.driver);
		
	}
}
