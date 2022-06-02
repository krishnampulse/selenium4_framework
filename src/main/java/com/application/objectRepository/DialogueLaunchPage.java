package com.application.objectRepository;

import org.openqa.selenium.By;

import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DialogueLaunchPage {
	WebDriver driver;
	ExtentTest testReporter;
	
	@FindBy(id = "name")
	private WebElement dialogueInputBox;
	
	@FindBy(id ="listId")
	private WebElement selectListDropDown;
	
	@FindBy(className = "add_action")
	private WebElement addActionButton;
	
	@FindBy(xpath = "//input[@triggertype='4' and @name='trigger.triggeredType']")
	private WebElement triggerOnSpecificDateRadioButton;
	
	@FindBy(id = "onetime_immediate_radio")
	private WebElement onetimeImmediateRadioButton;
	
	@FindBy(id = "onetime_start_time_radio")
	private WebElement onetimeStartTimeRadioButton;
	
	@FindBy(name = "trigger.oneTime.startDate")
	private WebElement oneTimeStartDateInputBox;
	
	@FindBy(name = "trigger.oneTime.startTime")
	private WebElement triggerOneTimeStartTimeInputBox;
	
	@FindBy(xpath = "//input[@name='trigger.triggeredType' and @triggertype='2']")
	private WebElement triggerOnMemberEngagementRadioButton;
	
	@FindBy(id = "trigger_event_eventId")
	private WebElement eventNameDropDown;
	
	@FindBy(id = "trigger_event_eventAttributeId")
	private WebElement eventAttributeDropDown;
	
	@FindBy(name = "trigger.event.eventAttributeValue")
	private WebElement eventAttributeValueInputBox;
	
	@FindBy(id = "dialogue_save")
	private WebElement dialogueSaveAsDraftButton;
	
	@FindBy(id = "dialogue_submit")
	private WebElement launchDialogueButton;
	
	public DialogueLaunchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
	}
	
	public boolean isDialogueInputBoxPresent() {
		if(this.dialogueInputBox.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isSelectListDropDownPresent() {
		if(this.selectListDropDown.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public DialogueLaunchPage enterDialogueName(String dialogueName) throws InterruptedException {
		if(this.isDialogueInputBoxPresent()) {
			this.dialogueInputBox.clear();
			this.dialogueInputBox.sendKeys(dialogueName);
			testReporter.log(LogStatus.INFO, "Entered Dialogue name "+dialogueName);
			SysLogger.log("Entered Dialogue name "+dialogueName);
		}
		else {
			SysLogger.log("Error: Not able to click enter value in Dialogue input box, Dialogue input box is not present on page");
		}
		return new DialogueLaunchPage(this.driver);
	}
	
	public DialogueLaunchPage selectListByListId(String listId) throws InterruptedException {
		if(this.isSelectListDropDownPresent()) {
			Select sc = new Select(this.selectListDropDown);
			sc.selectByValue(listId);
			testReporter.log(LogStatus.INFO, "Selected list in dropdown, list id "+listId);
			SysLogger.log("Selected list in dropdown, list id "+listId);
		}
		else {
			SysLogger.log("Error: Not able to select list by id, list select drop down is not present on page");
		}
		return new DialogueLaunchPage(this.driver);
	}
	
	public DialogueLaunchPage clickOnAddActionButton() throws InterruptedException {
		if(this.addActionButton.isDisplayed()) {
			this.addActionButton.click();
			testReporter.log(LogStatus.INFO, "Clicked on add action button");
			SysLogger.log("Clicked on add action button");
		}
		else {
			SysLogger.log("Error: Add action button is not present on page");
		}
		return new DialogueLaunchPage(this.driver);
	}
	
	public DialogueLaunchPage selectWorkflowID(String workflowID, int workflowSelectBoxCounterStratsWithZero) {
		WebElement selectWorkflowIdDropDown = this.driver.findElement(By.name("workflow["+workflowSelectBoxCounterStratsWithZero+"].workflowId"));
		if(selectWorkflowIdDropDown.isDisplayed()) {
			Select sc = new Select(selectWorkflowIdDropDown);
			sc.selectByValue(workflowID);
			testReporter.log(LogStatus.INFO, "Selected workflow id in dropdown, workflow id "+workflowID);
			SysLogger.log("Selected workflow id in dropdown, workflow id "+workflowID);
		}
		else {
			SysLogger.log("Error: Not able to select workflow by id, workflow select drop down is not present on page");
		}
		return new DialogueLaunchPage(this.driver);
	}
	
	public DialogueLaunchPage selectWorkflowStartAction(int actionSelectBoxCounterStratsWithZero) {
		WebElement selectStartActionDropDown = this.driver.findElement(By.name("workflow["+actionSelectBoxCounterStratsWithZero+"].action"));
		if(selectStartActionDropDown.isDisplayed()) {
			Select sc = new Select(selectStartActionDropDown);
			sc.selectByVisibleText("START");
			testReporter.log(LogStatus.INFO, "Selected workflow start action in dropdown");
			SysLogger.log("Selected workflow start action in dropdown");
		}
		else {
			SysLogger.log("Error: Not able to select workflow action, workflow action select drop down is not present on page");
		}
		return new DialogueLaunchPage(this.driver);
	}
	
	public DialogueLaunchPage selectWorkflowStopAction(int actionSelectBoxCounterStratsWithZero) {
		WebElement selectStopActionDropDown = this.driver.findElement(By.name("workflow["+actionSelectBoxCounterStratsWithZero+"].action"));
		if(selectStopActionDropDown.isDisplayed()) {
			Select sc = new Select(selectStopActionDropDown);
			sc.selectByVisibleText("STOP");
			testReporter.log(LogStatus.INFO, "Selected workflow stop action in dropdown");
			SysLogger.log("Selected workflow stop action in dropdown");
		}
		else {
			SysLogger.log("Error: Not able to select workflow action, workflow action select drop down is not present on page");
		}
		return new DialogueLaunchPage(this.driver);
	}
	
	public DialogueLaunchPage clickOnTriggerOnSpecificDateRadioButton() throws InterruptedException {
		if(this.triggerOnSpecificDateRadioButton.isDisplayed()) {
			this.triggerOnSpecificDateRadioButton.click();
			testReporter.log(LogStatus.INFO, "Clicked on trigger on Specific Date Radio button");
			SysLogger.log("Clicked on trigger on Specific Date Radio button");
		}
		else {
			SysLogger.log("Error: trigger on Specific Date Radio button is not present on page");
		}
		return new DialogueLaunchPage(this.driver);
	}
	
	public DialogueLaunchPage clickOnOneTimeImmediateRadioButton() throws InterruptedException {
		if(this.onetimeImmediateRadioButton.isDisplayed()) {
			this.onetimeImmediateRadioButton.click();
			testReporter.log(LogStatus.INFO, "Clicked on onetime Immediate Radio button");
			SysLogger.log("Clicked on onetime Immediate Radio button");
		}
		else {
			SysLogger.log("Error: One time Immediate Radio button is not present on page");
		}
		return new DialogueLaunchPage(this.driver);
	}
	
	public DialogueLaunchPage clickOnOneTimeStartTimeRadioButton() throws InterruptedException {
		if(this.onetimeStartTimeRadioButton.isDisplayed()) {
			this.onetimeStartTimeRadioButton.click();
			testReporter.log(LogStatus.INFO, "Clicked on onetime start time Radio button");
			SysLogger.log("Clicked on onetime start time Radio button");
		}
		else {
			SysLogger.log("Error: One time start time Radio button is not present on page");
		}
		return new DialogueLaunchPage(this.driver);
	}
	
	public DialogueLaunchPage enterOneTimeStartDate(String startDateInUTC) throws InterruptedException {
		if(this.oneTimeStartDateInputBox.isDisplayed()) {
			this.oneTimeStartDateInputBox.clear();
			this.oneTimeStartDateInputBox.sendKeys(startDateInUTC);;
			testReporter.log(LogStatus.INFO, "Entered one time start date in text box");
			SysLogger.log("Entered one time start date in text box");
		}
		else {
			SysLogger.log("Error: One time start date text box is not present on page");
		}
		return new DialogueLaunchPage(this.driver);
	}
	
	public DialogueLaunchPage enterOneTimeStartTime(String startTimeInUTC) throws InterruptedException {
		if(this.triggerOneTimeStartTimeInputBox.isDisplayed()) {
			this.triggerOneTimeStartTimeInputBox.clear();
			this.triggerOneTimeStartTimeInputBox.sendKeys(startTimeInUTC);;
			testReporter.log(LogStatus.INFO, "Entered one time start time in text box");
			SysLogger.log("Entered one time start time in text box");
		}
		else {
			SysLogger.log("Error: One time start time text box is not present on page");
		}
		return new DialogueLaunchPage(this.driver);
	}
	
	public DialogueLaunchPage clickOnTriggerOnMemberEngagementRadioButton() throws InterruptedException {
		if(this.triggerOnMemberEngagementRadioButton.isDisplayed()) {
			this.triggerOnMemberEngagementRadioButton.click();
			testReporter.log(LogStatus.INFO, "Clicked on trigger on Member Engagement Radio button");
			SysLogger.log("Clicked on trigger on Member Engagement Radio button");
		}
		else {
			SysLogger.log("Error: trigger on Member Engagement Radio button is not present on page");
		}
		return new DialogueLaunchPage(this.driver);
	}
	
	public DialogueLaunchPage selectEventNameFromDropDown(String eventName) throws InterruptedException {
		if(this.eventNameDropDown.isDisplayed()) {
			Select sc = new Select(this.eventNameDropDown);
			sc.selectByVisibleText(eventName);
			testReporter.log(LogStatus.INFO, "Selected event name in dropdown, event name "+eventName);
			SysLogger.log("Selected event name in dropdown, event name "+eventName);
		}
		else {
			SysLogger.log("Error: Not able to select event name, event name select drop down is not present on page");
		}
		return new DialogueLaunchPage(this.driver);
	}
	
	public DialogueLaunchPage selectEventAttributeDropDown(String attributeName) throws InterruptedException {
		if(this.eventAttributeDropDown.isDisplayed()) {
			Select sc = new Select(this.eventAttributeDropDown);
			sc.selectByVisibleText(attributeName);
			testReporter.log(LogStatus.INFO, "Selected event attribute name in dropdown, event name "+attributeName);
			SysLogger.log("Selected event attribute name in dropdown, event name "+attributeName);
		}
		else {
			SysLogger.log("Error: Not able to select event attribute name, event attribute name select drop down is not present on page");
		}
		return new DialogueLaunchPage(this.driver);
	}
	
	public DialogueLaunchPage enterEventAttributeValue(String eventAttributeValue) throws InterruptedException {
		if(this.eventAttributeValueInputBox.isDisplayed()) {
			this.eventAttributeValueInputBox.clear();
			this.eventAttributeValueInputBox.sendKeys(eventAttributeValue);;
			testReporter.log(LogStatus.INFO, "Entered event Attribute Value in text box, value "+eventAttributeValue);
			SysLogger.log("Entered event Attribute Value in text box, value "+eventAttributeValue);
		}
		else {
			SysLogger.log("Error: Event Attribute Value text box is not present on page");
		}
		return new DialogueLaunchPage(this.driver);
	}
	
	public DialoguePage clickOnDialogueSaveAsDraftButton() throws InterruptedException {
		if(this.dialogueSaveAsDraftButton.isDisplayed()) {
			this.dialogueSaveAsDraftButton.click();
			testReporter.log(LogStatus.INFO, "Clicked on dialogue save as draft button");
			SysLogger.log("Clicked on dialogue save as draft button");
		}
		else {
			SysLogger.log("Error: dialogue save as draft button is not present on page");
		}
		return new DialoguePage(this.driver);
	}
	
	public DialoguePage clickOnLaunchDialogueButton() throws InterruptedException {
		if(this.launchDialogueButton.isDisplayed()) {
			this.launchDialogueButton.click();
			testReporter.log(LogStatus.INFO, "Clicked on launch Dialogue button");
			SysLogger.log("Clicked on launch Dialogue button");
		}
		else {
			SysLogger.log("Error: Launch Dialogue button is not present on page");
		}
		return new DialoguePage(this.driver);
	}
	
}
