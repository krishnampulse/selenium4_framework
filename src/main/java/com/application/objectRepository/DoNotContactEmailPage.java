package com.application.objectRepository;

import java.util.List;

import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DoNotContactEmailPage {
	
	WebDriver driver;
	ExtentTest testReporter;
	WebDriverWait wait;
	private boolean flag = false;
	
	@FindBy(id = "unblock")
	private WebElement unblockButton;
	
	@FindBy(xpath = "//span[text()='Continue']/parent::button[@type='button' and @role='button']")
	private WebElement continueButton;
	
	@FindBy(xpath = "//nav[@id='navigation']//a[@class='segments']")
	private WebElement segmentsLinkButton;
	
	@FindBy (xpath = "//nav[@id='navigation']//a[@class='members']")
	private WebElement audienceMemberPageLink;
	
	@FindBy(xpath = "//nav[@id='navigation']//a[@class='lists']")
	private WebElement audienceListsPageLink;
	
	@FindBy (className = "communication")
	private WebElement communicationPageLink;
	
	@FindBy(xpath = "//table[@id='block_list']/tbody/tr/td[2]")
	private List<WebElement> allBlockedEmails;
	
	@FindBy(xpath = "//li[@class='next_page']/a[@title='Next Page']")
	private WebElement nextPageNavigationLink;
	
	public DoNotContactEmailPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
		wait = new WebDriverWait(driver, 120);
	}
	
	public boolean isSegmentPageLinkPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.segmentsLinkButton)).isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public boolean isAudienceListsPageLinkPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.audienceListsPageLink)).isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public boolean isAudienceMemberPageLinkPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.audienceMemberPageLink)).isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public boolean isCommunicationPageLinkPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.communicationPageLink)).isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public AudienceSegmentsPage goToSegmentsPage() {
		if (this.isSegmentPageLinkPresent()) {
			this.segmentsLinkButton.click();
		} else {
			SysLogger.log("Error: Audience Segment page link not present");
		}
		return new AudienceSegmentsPage(driver);
	}
	
	public AudienceMembersPage goToAudienceMemberPage() {	
		if (this.isAudienceMemberPageLinkPresent()) {	
			this.audienceMemberPageLink.click();	
		} else {	
			SysLogger.log("Error: Audience Member page link not present");	
		}	
		return new AudienceMembersPage(driver);	
	}
	
	public AudienceListsPage goToAudienceListsPage() {
		if (this.isAudienceListsPageLinkPresent()) {
			this.audienceListsPageLink.click();
		} else {
			SysLogger.log("Error: Audience Lists Page Link not present");
		}
		return new AudienceListsPage(driver);
	}
	
	private boolean isBlockedEmailPresentOnPage(String email) {
		for (WebElement element : allBlockedEmails) {
			if (element.getText().equals(email)) {
				testReporter.log(LogStatus.INFO, "Blocked email '"+email+"' found on Page");
				return true;
			}
		}
		testReporter.log(LogStatus.INFO, "Blocked email '"+email+"' not found on Page");
		return false;
	}
	
	public void clickOnNextPageNavigationButton() {
		if (this.nextPageNavigationLink.isDisplayed()) {
			this.nextPageNavigationLink.click();
		} else SysLogger.log("Next page Navigation Link is not present");
	}
	
	public DoNotContactEmailPage verifyThatBlockedEmailIsPresentOnPage(String email) {
		do {
			flag = this.isBlockedEmailPresentOnPage(email);
			if(flag == false) {
				this.clickOnNextPageNavigationButton();
			} else {
				WebElement blockedEmail = this.driver.findElement(By.xpath("//table[@id='block_list']/tbody/tr/td[text()='"+email+"']"));
				if (blockedEmail.isDisplayed()) {
					testReporter.log(LogStatus.INFO, "Blocked email is present on DNCL Page");
					return new DoNotContactEmailPage(this.driver);
				}
				else {
					SysLogger.log("Error : Blocked email is not present on DNCL Page");
					Assert.assertTrue(false, "Error: Blocked email is not present on DNCL Page");
				}
			}
		} while(flag == false);
		testReporter.log(LogStatus.INFO, "Navigated to list SubscribersPage of list '"+email+"'");
		return new DoNotContactEmailPage(this.driver);
	}
	
	public DoNotContactEmailPage selectCheckboxCorrespondingToEmail(String email) {
		WebElement checkBox = this.driver.findElement(By.xpath("//td[text()='"+email+"']/preceding-sibling::td[@class='check']/input[@type='checkbox']"));
		if (checkBox.isDisplayed()) {
			checkBox.click();
		}
		else {
			SysLogger.log("Error : check box for given email not present");
		}
		return new DoNotContactEmailPage(this.driver);
	}
	
	public DoNotContactEmailPage clickOnUnblockButton() {
		if (this.unblockButton.isDisplayed()) {
			this.unblockButton.click();
			return new DoNotContactEmailPage(this.driver);
		} else {
			SysLogger.log("Error: search Button not present on page");
		}
		return new DoNotContactEmailPage(this.driver);
	}
	
	public DoNotContactEmailPage clickOnContinueButton() {
		if (this.continueButton.isDisplayed()) {
			this.continueButton.click();
			return new DoNotContactEmailPage(this.driver);
		} else {
			SysLogger.log("Error: Continue Button not present on page");
		}
		return new DoNotContactEmailPage(this.driver);
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
