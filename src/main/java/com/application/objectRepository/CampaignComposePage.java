package com.application.objectRepository;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.application.factories.ReporterFactory;
import com.application.utility.DriverMethods;
import com.application.utility.SysLogger;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CampaignComposePage {
	WebDriver driver;
	ExtentTest testReporter;
	Actions act;
	JavascriptExecutor js;
	WebDriverWait wait;
	
	@FindBy (xpath = "//a[@href='/home' and contains(text(), 'mPulse Mobile')]")
	private WebElement homePageLink;
	
	@FindBy (xpath = "//div[@id='common_workarea']/div[@class='progress-bar']/div[@class='loadingmessage']/span")
	private WebElement progressBarLoader;
	
	@FindBy (xpath = "//div[@id='common_workarea']/div[@class='saving-bar']/div[@class='loadingmessage']/span")
	private WebElement savingBarLoader;
	
	@FindBy(xpath = "//li[@id='email_tab_header' and contains(@style, 'list-item')]")
	private WebElement emailTab;

	@FindBy(xpath = "//li[@id='sms_tab_header' and contains(@style, 'list-item')]")
	private WebElement smsTab;

	@FindBy(xpath = "//li[@id='appmail_tab_header' and contains(@style, 'list-item')]")
	private WebElement appmailTab;

	@FindBy(xpath = "//li[@id='pn_tab_header' and contains(@style, 'list-item')]")
	private WebElement pnTab;
	
	@FindBy (xpath = "//div[@class='common_form_control campaign_details_control']/button/span[contains(text(), 'Next')]/ancestor::button[@id='message_submit']")
	private WebElement campaignNextButton;
	
	@FindBy (xpath = "//div[@class='common_form_control campaign_details_control']/button/span[contains(text(), 'Save')]/ancestor::button[@class='icon_button only_save shift_right']")
	private WebElement campaignSaveButton;
	
	@FindBy (xpath = "//div[@class='common_form_control campaign_details_control']/button/span[contains(text(), 'Next')]/ancestor::button[@id='message_submit']/following-sibling::a[@class='common_form_cancel']")
	private WebElement campaignBackButton;
	
	@FindBy (className = "communication")
	private WebElement communicationPageLink;

	public CampaignComposePage(WebDriver driver) {
		this.driver = driver;
		act = new Actions(driver);
		js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
		wait = new WebDriverWait(driver, 120);
	}
	
	public boolean isEmailTabPresent() {
		if(wait.until(ExpectedConditions.visibilityOf(this.emailTab)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isSmsTabPresent() {
		if(wait.until(ExpectedConditions.visibilityOf(this.smsTab)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isAppmailTabPresent() {
		if(wait.until(ExpectedConditions.visibilityOf(this.appmailTab)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isPnTabPresent() {
		if(wait.until(ExpectedConditions.visibilityOf(this.pnTab)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isCampaignSaveButtonPresent() {
		if(wait.until(ExpectedConditions.visibilityOf(this.campaignSaveButton)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isCampaignBackButtonPresent() {
		if(this.campaignBackButton.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isCampaignNextButtonPresent() {
		if(wait.until(ExpectedConditions.visibilityOf(this.campaignNextButton)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isCommunicationPageLinkPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.communicationPageLink)).isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public CampaignSchedulePage clickOnNextButton() throws InterruptedException {
		if(this.isCampaignNextButtonPresent()) {
			this.campaignNextButton.click();
			Thread.sleep(16000);
			DriverMethods.waitForElementUntillDisappear(this.driver, this.progressBarLoader);
			DriverMethods.isElementPresent(this.driver, this.homePageLink);
		}
		return new CampaignSchedulePage(this.driver);
	}
	
	public CampaignComposePage clickForEmailBug() throws InterruptedException {
		if(this.isCampaignNextButtonPresent()) {
			this.campaignNextButton.click();
			Thread.sleep(10000);
			DriverMethods.waitForElementUntillDisappear(this.driver, this.progressBarLoader);
			DriverMethods.isElementPresent(this.driver, this.homePageLink);
		}
		return new CampaignComposePage(this.driver);
	}
	
	public EmailMessageComposePage goToEmailComposePage() {
		try {
			Thread.sleep(10000);
			DriverMethods.waitForElementUntillDisappear(this.driver, this.progressBarLoader);
			DriverMethods.waitForElementUntillDisappear(this.driver, this.savingBarLoader);
			DriverMethods.isElementPresent(this.driver, homePageLink);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new EmailMessageComposePage(this.driver);
	}
	
	public SmsMessageComposePage goToSmsComposePage() throws InterruptedException {
		try {
			Thread.sleep(1000);
			DriverMethods.waitForElementUntillDisappear(this.driver, this.progressBarLoader);
			DriverMethods.waitForElementUntillDisappear(this.driver, this.savingBarLoader);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new SmsMessageComposePage(this.driver);
	}
	
	public AppmailMessageComposePage goToAppmailComposePage() {
		return new AppmailMessageComposePage(this.driver);
	}
	
	public EmailMessageComposePage clickOnEmailTab() throws InterruptedException {
		Thread.sleep(12000);
		DriverMethods.waitForElementUntillDisappear(this.driver, this.progressBarLoader);
		DriverMethods.waitForElementUntillDisappear(this.driver, this.savingBarLoader);
		DriverMethods.isElementPresent(this.driver, this.homePageLink);
		if(this.isEmailTabPresent()) {
			this.emailTab.click();
			DriverMethods.waitForElementUntillDisappear(this.driver, this.progressBarLoader);
			DriverMethods.waitForElementUntillDisappear(this.driver, this.savingBarLoader);
			DriverMethods.isElementPresent(this.driver, this.homePageLink);
			Thread.sleep(10000);
		}
		else {
			SysLogger.log("Error : Not able to click on Email Tab");
		}
		return new EmailMessageComposePage(this.driver);
	}
	
	public SmsMessageComposePage clickOnSmsTab() throws InterruptedException {
		Thread.sleep(12000);
		DriverMethods.waitForElementUntillDisappear(this.driver, this.progressBarLoader);
		DriverMethods.waitForElementUntillDisappear(this.driver, this.savingBarLoader);
		DriverMethods.isElementPresent(driver, homePageLink);
		if(this.isSmsTabPresent()) {
			act.moveToElement(this.smsTab).build().perform();
			this.smsTab.click();
			Thread.sleep(12000);
			DriverMethods.isElementPresent(driver, homePageLink);
		}
		else {
			SysLogger.log("Error : Not able to click on SMS Tab");
		}
		return new SmsMessageComposePage(this.driver);
	}
	
	public AppmailMessageComposePage clickOnAppmailTab() throws InterruptedException {
		Thread.sleep(12000);
		DriverMethods.waitForElementUntillDisappear(this.driver, this.progressBarLoader);
		DriverMethods.waitForElementUntillDisappear(this.driver, this.savingBarLoader);
		DriverMethods.isElementPresent(driver, homePageLink);
		if(this.isAppmailTabPresent()) {
			act.moveToElement(this.appmailTab).build().perform();
			this.appmailTab.click();
			Thread.sleep(15000);
			DriverMethods.waitForElementUntillDisappear(this.driver, this.progressBarLoader);
			DriverMethods.waitForElementUntillDisappear(this.driver, this.savingBarLoader);
			DriverMethods.isElementPresent(driver, homePageLink);
		}
		else {
			SysLogger.log("Error : Not able to click on appmail Tab");
		}
		return new AppmailMessageComposePage(this.driver);
	}
	
	public PNMessageComposePage clickOnPNTab() throws InterruptedException {
		Thread.sleep(12000);
		DriverMethods.waitForElementUntillDisappear(this.driver, this.progressBarLoader);
		DriverMethods.waitForElementUntillDisappear(this.driver, this.savingBarLoader);
		DriverMethods.isElementPresent(driver, homePageLink);
		if(this.isPnTabPresent()) {
			act.moveToElement(this.pnTab).build().perform();
			this.pnTab.click();
			Thread.sleep(15000);
			DriverMethods.isElementPresent(driver, homePageLink);
		}
		else {
			SysLogger.log("Error : Not able to click on appmail Tab");
		}
		return new PNMessageComposePage(this.driver);
	}
	
	public PNMessageComposePage goToPNComposePage() throws InterruptedException {
		try {
			Thread.sleep(1000);
			DriverMethods.waitForElementUntillDisappear(this.driver, this.progressBarLoader);
			DriverMethods.waitForElementUntillDisappear(this.driver, this.savingBarLoader);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new PNMessageComposePage(this.driver);
	}

	
	
	public CommunicationPage goToCommunicationPage() throws InterruptedException {
		Thread.sleep(3000);
		if (isCommunicationPageLinkPresent()) {
			this.communicationPageLink.click();
			testReporter.log(LogStatus.INFO, "Clicked on communication page link");
		} else {
			SysLogger.log("Error: Communication page link not present");
			testReporter.log(LogStatus.ERROR, "Error: Communication page link not present");
		}
		return new CommunicationPage(this.driver);
	}
}
