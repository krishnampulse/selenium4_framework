package com.application.objectRepository;

import org.openqa.selenium.By;

import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SecureMessageWebInboxPage {
	
	WebDriver driver;
	ExtentTest testReporter;
	WebDriverWait wait;
	String url;
	
	@FindBy(xpath = "//img[@alt='mpulse' and contains(@src, 'favicon_mpulse')]")
	WebElement favicon;
	
	@FindBy(xpath = "//label/strong[text()='Secure Message']")
	WebElement secureMessageLabel;
	
	@FindBy(id = "messageCountText")
	WebElement messageCountText;
	
	@FindBy(xpath ="//section[contains(@class, 'fullmessage')]//img[@alt='appmail_logo']")
	WebElement appmailLogoSingleMessage;
	
	@FindBy(xpath = "//div[@class='innermsg select-message']")
	WebElement appmailInboxSingleMessage;
	
	@FindBy(xpath = "//div[@class='innermsg select-message']//div[@class='substrMsg msgconten']")
	WebElement appmailSubjectSingleMessage;
	
	@FindBy(xpath = "//div[@class='innermsg select-message']//span[@class='priority']")
	WebElement priorityLabelSingleMessage;
	
	@FindBy(xpath = "//section[contains(@class, 'fullmessage')]/div[@class='complete-message']/p[1]")
	WebElement messageContentSingleMessage;
	
	@FindBy(xpath = "//section[contains(@class, 'fullmessage')]/div[@class='complete-message']/p/a[@class='unsub-link']")
	WebElement unsubLinkSingleMessage;
	
	@FindBy(xpath = "//section[contains(@class, 'fullmessage')]/div[@class='complete-message']//div[@id='intro-text']/p")
	WebElement surveyIntroTextSingleMessage;
	
	@FindBy(xpath = "//section[contains(@class, 'fullmessage')]/button[text()='Start']")
	WebElement surveyStartButtonSingleMessage;
	
	@FindBy(xpath = "//section[contains(@class, 'fullmessage')]/button[text()='Next']")
	WebElement surveyNextButtonSingleMessage;
	
	@FindBy(xpath = "//section[contains(@class, 'fullmessage')]/form/label/button[@type='button']")
	WebElement surveySubmitButtonSingleMessage;
	
	@FindBy(xpath = "//section[contains(@class, 'fullmessage')]/div[@role='dialog']//button[text()='Done']")
	WebElement surveyDonButtonInPopUpSingleMessage;
	
	@FindBy(xpath = "//section[contains(@class, 'fullmessage')]/div[@role='dialog']/div[contains(@class, 'option-margin js-option-popup')]/p[@role='text']")
	WebElement surveySubmitConfirmationTextSingleMessage;
	
	@FindBy(xpath = "//section[contains(@class, 'fullmessage')]/div[@class='complete-message']/h3[text()='Survey Summary']")
	WebElement surveySummaryLabelText;
	
	public SecureMessageWebInboxPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
		wait = new WebDriverWait(driver, 60);
	}
	
	public boolean isMpulseFaviconPresent() {
		if(this.favicon.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isSecureMessageLabelPresent() {
		if(this.secureMessageLabel.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isAppmailLogoPresent() {
		if(this.appmailLogoSingleMessage.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isPriorityLabelPresent() {
		if(this.priorityLabelSingleMessage.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isSurveySummaryLabelTextPresent() {
		if(this.surveySummaryLabelText.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public String getMessageCountText() {
		if(this.messageCountText.isDisplayed()) {
			return this.messageCountText.getText();
		}
		else {
			SysLogger.log("Error : Message Count Text is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Message Count Text is not displayed");
		}
		return null;
	}
	
    public SecureMessageWebInboxPage clickOnAppmailInbox() {
        if (this.appmailInboxSingleMessage.isDisplayed()) {
            this.appmailInboxSingleMessage.click();
        } else {
            SysLogger.log("Error: appmail Inbox is not Displayed or not Enabled");
        }
        return new SecureMessageWebInboxPage(this.driver);
    }
    
    public SecureMessageWebInboxPage clickOnSurveyStartButton() {
        if (this.surveyStartButtonSingleMessage.isDisplayed()) {
            this.surveyStartButtonSingleMessage.click();
        } else {
            SysLogger.log("Error: survey Start Button is not Displayed or not Enabled");
            testReporter.log(LogStatus.INFO, "Error: survey Start Button is not Displayed or not Enabled");
        }
        return new SecureMessageWebInboxPage(this.driver);
    }
    
    public SecureMessageWebInboxPage clickSurveyNextButton() {
        if (this.surveyNextButtonSingleMessage.isDisplayed()) {
            this.surveyNextButtonSingleMessage.click();
        } else {
            SysLogger.log("Error: survey Next Button is not Displayed or not Enabled");
            testReporter.log(LogStatus.INFO, "Error: survey Next Button is not Displayed or not Enabled");
        }
        return new SecureMessageWebInboxPage(this.driver);
    }
    
    public SecureMessageWebInboxPage clickOnSurveySubmitButton() {
        if (this.surveySubmitButtonSingleMessage.isDisplayed()) {
            this.surveySubmitButtonSingleMessage.click();
        } else {
            SysLogger.log("Error: survey Submit Button is not Displayed or not Enabled");
            testReporter.log(LogStatus.INFO, "Error: survey Submit Button is not Displayed or not Enabled");
        }
        return new SecureMessageWebInboxPage(this.driver);
    }
    
    public SecureMessageWebInboxPage clickOnSurveyDonButtonInPopUp() throws InterruptedException {
    	Thread.sleep(10000);
        if (this.surveyDonButtonInPopUpSingleMessage.isDisplayed()) {
            this.surveyDonButtonInPopUpSingleMessage.click();
        } else {
            SysLogger.log("Error: survey Done Button is not Displayed or not Enabled");
            testReporter.log(LogStatus.INFO, "Error: survey Done Button is not Displayed or not Enabled");
        }
        return new SecureMessageWebInboxPage(this.driver);
    }
	
	public SecureMessageWebInboxPage clickOnOptionByName(String optionName) {
		WebElement element = this.driver.findElement(By.xpath("//span[@class='survey-input' and text()='"+optionName+"']"));
		if(element.isDisplayed()) {
			element.click();
			return new SecureMessageWebInboxPage(this.driver);
		}
		else {
			SysLogger.log("Error : Option with text "+optionName+" not displayed");
			testReporter.log(LogStatus.INFO, "Error : Option with text "+optionName+" not displayed");
		}
		return new SecureMessageWebInboxPage(this.driver);
	}
	
	public SecureMessageWebInboxPage enterValueInFreeTextOption(int questionNumber, String optionValue) {
		WebElement element = this.driver.findElement(By.xpath("//div[@class='complete-message']/div[contains(@class, 'onload-hide questions hide')]["+questionNumber+"]//span[contains(@class, 'survey-input')]/input[@class='survey-free-text-input']"));
		if(element.isDisplayed()) {
			element.click();
			element.sendKeys(optionValue);
			return new SecureMessageWebInboxPage(this.driver);
		}
		else {
			SysLogger.log("Error : Free text Option not displayed");
			testReporter.log(LogStatus.INFO, "Error : Free text Option not displayed");
		}
		return new SecureMessageWebInboxPage(this.driver);
	}
	
	public String getSurveySubmitConfirmationText() {
		if(this.surveySubmitConfirmationTextSingleMessage.isDisplayed()) {
			return this.surveySubmitConfirmationTextSingleMessage.getText();
		}
		else {
			SysLogger.log("Error : survey Submit Confirmation Text is not displayed");
			testReporter.log(LogStatus.INFO, "Error : survey Submit Confirmation Text is not displayed");
		}
		return null;
	}
	
	public String getSurveyIntroText() {
		if(this.surveyIntroTextSingleMessage.isDisplayed()) {
			return this.surveyIntroTextSingleMessage.getText();
		}
		else {
			SysLogger.log("Error : survey Intro Text is not displayed");
			testReporter.log(LogStatus.INFO, "Error : survey Intro Text is not displayed");
		}
		return null;
	}
	
	public String getAppmailSubject() {
		if(this.appmailSubjectSingleMessage.isDisplayed()) {
			return this.appmailSubjectSingleMessage.getText();
		}
		else {
			SysLogger.log("Error : appmail Subject Text is not displayed");
			testReporter.log(LogStatus.INFO, "Error : appmail Subject Text is not displayed");
		}
		return null;
	}
	
	public String getMessageContent() {
		if(this.messageContentSingleMessage.isDisplayed()) {
			return this.messageContentSingleMessage.getText();
		}
		else {
			SysLogger.log("Error : message Content Text is not displayed");
			testReporter.log(LogStatus.INFO, "Error : message Content Text is not displayed");
		}
		return null;
	}
	
	public String getQuestionTextInSurveySummary(int questionNumber) {
		String xpath = "//section[contains(@class, 'fullmessage')]/div[@class='complete-message']/div[@class='question_block']["+questionNumber+"]/p";
		WebElement element = this.driver.findElement(By.xpath(xpath));
		if(element.isDisplayed()) {
			return element.getText();
		}
		else {
			SysLogger.log("Error : Question Text In Survey Summary is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Question Text In Survey Summary is not displayed");
		}
		return null;
	}
	
	public String getSelectedOptionTextInSurveySummary(int questionNumber) {
		String xpath = "//section[contains(@class, 'fullmessage')]/div[@class='complete-message']/div[@class='question_block']["+questionNumber+"]/ul/li";
		WebElement element = this.driver.findElement(By.xpath(xpath));
		if(element.isDisplayed()) {
			return element.getAttribute("innerHTML");
		}
		else {
			SysLogger.log("Error : Question Text In Survey Summary is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Question Text In Survey Summary is not displayed");
		}
		return null;
	}
	
	public String getSelectedOptionTextInSurveySummary(int questionNumber, int replyOption) {
		String xpath = "//section[contains(@class, 'fullmessage')]/div[@class='complete-message']/div[@class='question_block']["+questionNumber+"]/ul/li["+replyOption+"]";
		WebElement element = this.driver.findElement(By.xpath(xpath));
		if(element.isDisplayed()) {
			return element.getAttribute("innerHTML");
		}
		else {
			SysLogger.log("Error : Question Text In Survey Summary is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Question Text In Survey Summary is not displayed");
		}
		return null;
	}
	
	public String getUnsubLink() {
		if(this.unsubLinkSingleMessage.isDisplayed()) {
			return this.unsubLinkSingleMessage.getAttribute("href");
		}
		else {
			SysLogger.log("Error : Un sub Link is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Un sub Link is not displayed");
		}
		return null;
	}
}
