package com.application.objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.application.factories.ApplicationDB;
import com.application.factories.ReporterFactory;
import com.application.utility.ConfigReader;
import com.application.utility.DriverMethods;
import com.application.utility.SysLogger;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DialoguePage {
	WebDriver driver;
	ExtentTest testReporter;
	
	@FindBy (xpath = "//a[@href='/home' and contains(text(), 'mPulse Mobile')]")
	private WebElement homePageLink;
	
	@FindBy(xpath = "//a[@href='/campaigns/dialogues/create']")
	private WebElement createNewDialogueLink;
	
	@FindBy(xpath = "//div[@class='ui-dialog-buttonset']/button[contains(@class, 'submit')]")
	private WebElement submitYesButton;
	
	@FindBy(xpath = "//button[@class='cancel ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']")
	private WebElement submitNoButton;
	
	public DialoguePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
	}
	
	public boolean isCreateNewDialogueLinkPresent() {
		if(this.createNewDialogueLink.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public DialogueLaunchPage clickOnCreateNewDialogueButton() throws InterruptedException {
		if(this.isCreateNewDialogueLinkPresent()) {
			this.createNewDialogueLink.click();
			testReporter.log(LogStatus.INFO, "Clicked on create new Dialogue button");
			SysLogger.log("Clicked on create new Dialogue button");
		}
		else {
			SysLogger.log("Error : Not able to click on create new Dialogue button, Dialogue button is not present on page");
		}
		return new DialogueLaunchPage(this.driver);
	}
	
	public DialoguePage clickOnRetireYesButton() {
		if (this.submitYesButton.isDisplayed()) {
			this.submitYesButton.click();
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			DriverMethods.isElementPresent(this.driver, this.homePageLink);
			return new DialoguePage(this.driver);
		}
		else {
			SysLogger.log("Error : Not able to click on Dialogue Retire yes button");
		}
		return new DialoguePage(this.driver);
	}
	
	public DialoguePage retireDialogueByName(String dialogueName) throws Exception {
		String getDialogueIdByName = "select id from dialogue where name ='"+dialogueName+"' and account_id="+ConfigReader.getConfig("account_id");
		String dialogueId = ApplicationDB.getStringData(getDialogueIdByName);
		WebElement retireLink = this.driver.findElement(By.xpath("//a[@data-id='"+dialogueId+"' and @title='Retire Dialogue']"));
		if(retireLink.isDisplayed()) {
			retireLink.click();
			testReporter.log(LogStatus.INFO, "Clicked on Dialogue retire link");
			SysLogger.log("Clicked on Dialogue retire link");
		}
		else {
			SysLogger.log("Error : Not able to click on Dialogue retire link, Dialogue retire link is not present on page");
		}
		Thread.sleep(1000);
		this.clickOnRetireYesButton();
		return new DialoguePage(this.driver);
	}
}
