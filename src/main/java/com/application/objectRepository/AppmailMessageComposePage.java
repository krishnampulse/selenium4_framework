package com.application.objectRepository;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
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
import com.application.utility.SysLogger;
import com.relevantcodes.extentreports.ExtentTest;

public class AppmailMessageComposePage {
	WebDriver driver;
	ExtentTest testReporter;
	Actions act;
	JavascriptExecutor js;
	WebDriverWait wait;
	
	@FindBy (xpath = "//a[@href='/home' and contains(text(), 'mPulse Mobile')]")
	private WebElement homePageLink;
	
	@FindBy(xpath = "//div[@id='appmail-message-accordion']//form[@id='event_appmail_compose_0']//textarea[contains(@placeholder, 'enter your Secure Messaging subject')]")
	private WebElement secureMessageSubject;
	
	@FindBy(xpath = "//div[@id='appmail-message-accordion']//div[@class='notification_row']/div[@data-value='APPMAIL']/span")
	private WebElement secureMessageType;
	
	@FindBy(xpath = "//div[@id='appmail-message-accordion']//div[@class='notification_row']/div[@data-value='APPMAIL_SURVEY']/span")
	private WebElement secureMessageSurveyType;
	
	@FindBy (xpath = "//div[@id='common_workarea']/div[@class='saving-bar']/div[@class='loadingmessage']/span")
	private WebElement loaderSavingBar;
	
	@FindBy (xpath = "html/body")
	private WebElement secureMessageTextBody;
	
	@FindBy(xpath = "//div[@id='appmail-message-accordion']//form[@id='event_appmail_compose_0']//input[@name='add_survey_item']")
	private WebElement addSurveyItemButton;
	
	@FindBy(xpath = "//input[contains(@name, 'message[0].saveSubmitted') and @type='checkbox']")
	private WebElement sendSurveySummaryCheckBox;
	
	@FindBy (xpath = "//div[@id='appmail-message-accordion']//form[@id='event_appmail_compose_0']//div[@id='add_trigger']")
	private WebElement appmailAddTriggerButton;
	
	@FindBy (xpath = "//div[@class='common_form_control campaign_details_control']/button/span[contains(text(), 'Next')]/ancestor::button[@id='message_submit']")
	private WebElement campaignNextButton;
	
	@FindBy (xpath = "//div[@class='common_form_control campaign_details_control']/button/span[contains(text(), 'Save')]/ancestor::button[@class='icon_button only_save shift_right']")
	private WebElement campaignSaveButton;
	
	@FindBy (xpath = "//div[@class='common_form_control campaign_details_control']/button/span[contains(text(), 'Next')]/ancestor::button[@id='message_submit']/following-sibling::a[@class='common_form_cancel']")
	private WebElement campaignBackButton;
	
	@FindBy(xpath = "//span[@class='ui-button-text' and text()='Ok']")
	private WebElement messageSchedulerAlertOkButton;

	public AppmailMessageComposePage(WebDriver driver) {
		this.driver = driver;
		act = new Actions(driver);
		js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
		wait = new WebDriverWait(driver, 120);
	}
	
	public boolean isSecureMessageSubjectTextBoxPresent() {
		if(this.wait.until(ExpectedConditions.visibilityOf(this.secureMessageSubject)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isAppmailAddTriggerButtonPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.appmailAddTriggerButton)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isCampaignSaveButtonPresent() {
		if(this.wait.until(ExpectedConditions.visibilityOf(this.campaignSaveButton)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public AppmailMessageComposePage enterSecureMessageSubject(String subject) throws Exception {
		Thread.sleep(10000);
		DriverMethods.waitForElementUntillDisappear(this.driver, this.loaderSavingBar);
		act.moveToElement(this.secureMessageSubject).build().perform();
		Thread.sleep(8000); // adding more waiting because it is failing on qa
		this.wait.until(ExpectedConditions.visibilityOf(this.secureMessageSubject));
		this.secureMessageSubject.click();
		this.secureMessageSubject.clear();
		this.secureMessageSubject.sendKeys(subject);
		Thread.sleep(3000);
		SysLogger.log("Info : secure message subject text box is filled with value "+subject);
		return new AppmailMessageComposePage(this.driver);
	}
	
	public AppmailMessageComposePage selectMessagePriority(String visibleText) throws InterruptedException {
		if(visibleText.equalsIgnoreCase("Standard")) {
			js.executeScript("$($(\"#dk_container_message_priority_0 .dk_options a[data-dk-dropdown-value='0']\")).trigger('click');");
		} else if(visibleText.equalsIgnoreCase("Urgent")) {
			js.executeScript("$($(\"#dk_container_message_priority_0 .dk_options a[data-dk-dropdown-value='1']\")).trigger('click');");
		} else {
			SysLogger.log("Error : Not able to select dropdown, given value '"+visibleText+"' may be incorrect");
		}
		Thread.sleep(3000);
		return new AppmailMessageComposePage(this.driver);
	}
	
	public CampaignComposePage clickOnSaveButton() throws InterruptedException {
		if(this.isCampaignSaveButtonPresent()) {
			this.campaignSaveButton.click();
			Thread.sleep(12000); // campaign save is taking more time when two message
			DriverMethods.waitForElementUntillDisappear(this.driver, this.loaderSavingBar);
			DriverMethods.isElementPresent(driver, homePageLink);
		}
		return new CampaignComposePage(driver);
	}
	
	public AppmailMessageComposePage clickOnSecureMessageSurveyType() throws InterruptedException {
		if(this.secureMessageSurveyType.isDisplayed()) {
			this.secureMessageSurveyType.click();
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			SysLogger.log("Alert Text - "+alertText);
			alert.accept();
		}
		return new AppmailMessageComposePage(driver);
	}
	
	public NewSurveyItemPage clickOnAddSurveyItemButton() throws InterruptedException {
		if(this.addSurveyItemButton.isDisplayed()) {
			this.addSurveyItemButton.click();
			Thread.sleep(5000);
		} else {
			SysLogger.log("Error - Add Survey Item Button is not Displayed on Page");
		}
		return new NewSurveyItemPage(driver);
	}
	
	public AppmailMessageComposePage clickOnSendSurveySummaryCheckBox() throws InterruptedException {
		if(this.sendSurveySummaryCheckBox.isDisplayed()) {
			this.sendSurveySummaryCheckBox.click();
			Thread.sleep(2000);
		} else {
			SysLogger.log("Error - send Survey Summary CheckBox is not Displayed on Page");
		}
		return new AppmailMessageComposePage(driver);
	}
	
	public AppmailMessageComposePage enterSecureMessageText(String text) {
		int size = driver.findElements(By.tagName("iframe")).size();
		SysLogger.log("total number of iframe - "+size);
		this.driver.switchTo().frame(0);
		this.secureMessageTextBody.sendKeys(text);
		this.driver.switchTo().defaultContent();
		return new AppmailMessageComposePage(this.driver);
	}
	
	public AppmailMessageComposePage enterSecureSurveyIntroductionText(String text) {
		WebElement element = this.driver.findElement(By.xpath("//iframe[contains(@title, 'message[0].introductionText')]"));
		this.driver.switchTo().frame(element);
		this.secureMessageTextBody.sendKeys(text);
		this.driver.switchTo().defaultContent();
		return new AppmailMessageComposePage(this.driver);
	}
	
	public AppmailMessageComposePage enterSecureSurveySubmitConfirmationText(String text) {
		WebElement element = this.driver.findElement(By.xpath("//iframe[contains(@title, 'message[0].confirmationPopUpText')]"));
		this.driver.switchTo().frame(element);
		this.secureMessageTextBody.sendKeys(text);
		this.driver.switchTo().defaultContent();
		return new AppmailMessageComposePage(this.driver);
	}
	
	public AppmailTriggers clickOnAddTriggerButton() throws InterruptedException {
		Thread.sleep(10000);
		DriverMethods.waitForElementUntillDisappear(this.driver, this.loaderSavingBar);
		DriverMethods.isElementPresent(this.driver, this.homePageLink);
		if (this.isAppmailAddTriggerButtonPresent()) {
			act.moveToElement(this.appmailAddTriggerButton).build().perform();
			this.appmailAddTriggerButton.click();
			DriverMethods.isElementPresent(driver, homePageLink);
			Thread.sleep(3000);
		} else {
			SysLogger.log("Error : Appmail Trigger are not present");
		}
		return new AppmailTriggers();
	}
	
	public AppmailMessageComposePage clickOnOneTimeMessageSchedulerAlertOkButton() throws InterruptedException {
		if (this.messageSchedulerAlertOkButton.isDisplayed()) {
			act.moveToElement(this.messageSchedulerAlertOkButton).build().perform();
			this.messageSchedulerAlertOkButton.click();
			Thread.sleep(1000);
		} else {
			SysLogger.log("Error : message Scheduler Alert OK Button not present");
		}
		return new AppmailMessageComposePage(this.driver);
	}
	
	public class NewSurveyItemPage {
		WebDriver driver;
		ExtentTest testReporter;
		Actions act;
		JavascriptExecutor js;
		WebDriverWait wait;
		
		public NewSurveyItemPage(WebDriver driver) {
			this.driver = driver;
			act = new Actions(driver);
			js = (JavascriptExecutor) driver;
			PageFactory.initElements(driver, this);
			testReporter = ReporterFactory.getTest();
			wait = new WebDriverWait(driver, 120);
		}
		
		public NewSurveyItemPage selectProfileFieldToUpdate(int questionNumber, String fieldName) throws InterruptedException {
			String javascript = "$($(\"#dk_container_fieldName_0_"+(questionNumber-1)+" .dk_options a:contains('"+fieldName+"')\")).trigger('click');";
			Thread.sleep(2000); // adding wait to proper load all fields name before selecting
			this.js.executeScript(javascript);
			Thread.sleep(2000);
			return new NewSurveyItemPage(this.driver);
		}
		
		public NewSurveyItemPage enterValueInQuestionTextBox(int questionNumber, String questionText) throws InterruptedException {
			WebElement element = this.driver.findElement(By.xpath("//iframe[contains(@title, 'appmailQuestionForm["+(questionNumber-1)+"]')]"));
			this.act.moveToElement(element).build().perform();
			if (element.isDisplayed()) {
				this.driver.switchTo().frame(element);
				this.driver.findElement(By.xpath("html/body")).sendKeys(questionText);
				this.driver.switchTo().defaultContent();
				Thread.sleep(1000);
			} else {
				SysLogger.log("Error : Question value field not present");
			}
			return new NewSurveyItemPage(this.driver);
		}
		
		public NewSurveyItemPage clickOnMultipleSelectCheckBox(int questionNumber) throws InterruptedException {
			WebElement element = this.driver.findElement(By.xpath("//div[@aria-labelledby='ui-dialog-title-"+(questionNumber+1)+"']//div[@name='survey_dialog_appmail_0' and @id='survey_dialog_appmail_0_"+(questionNumber-1)+"']//input[contains(@name, 'appmailQuestionForm["+(questionNumber-1)+"].responseType') and @type='checkbox']"));
			this.act.moveToElement(element).build().perform();
			if (element.isDisplayed()) {
				this.act.click(element).build().perform();
				Thread.sleep(1000);
			} else {
				SysLogger.log("Error : Multiple Select CheckBox not present");
			}
			return new NewSurveyItemPage(this.driver);
		}
		
		public NewSurveyItemPage clickOnFreeTextCheckBox(int questionNumber) throws InterruptedException {
			WebElement element = this.driver.findElement(By.xpath("//div[@aria-labelledby='ui-dialog-title-"+(questionNumber+1)+"']//div[@name='survey_dialog_appmail_0' and @id='survey_dialog_appmail_0_"+(questionNumber-1)+"']//input[contains(@name, 'appmailQuestionForm["+(questionNumber-1)+"].isFreeText') and @type='checkbox']"));
			this.act.moveToElement(element).build().perform();
			if (element.isDisplayed()) {
				this.act.click(element).build().perform();
				Thread.sleep(1000);
			} else {
				SysLogger.log("Error : Free Text CheckBox not present");
			}
			return new NewSurveyItemPage(this.driver);
		}
		
		public NewSurveyItemPage enterValueInOptionTextBox(int questionNumber, int optionNumber, String value) throws InterruptedException {
			WebElement element = this.driver.findElement(By.xpath("//div[@aria-labelledby='ui-dialog-title-"+(questionNumber+1)+"']//div[@name='survey_dialog_appmail_0' and @id='survey_dialog_appmail_0_"+(questionNumber-1)+"']//div[contains(@class, 'appmail-survey-option-div')]/textarea[contains(@name, 'appmailQuestionForm["+(questionNumber-1)+"].surveyOptionsForm["+(optionNumber-1)+"].option')]"));
			this.act.moveToElement(element).build().perform();
			if (element.isDisplayed()) {
				this.act.click(element).build().perform();
				this.act.sendKeys(value).build().perform();
				Thread.sleep(1000);
			} else {
				SysLogger.log("Error : Option value field not present");
			}
			return new NewSurveyItemPage(this.driver);
		}
		
		public NewSurveyItemPage clickOnAddOptionButton(int questionNumber) throws InterruptedException {
			WebElement element = this.driver.findElement(By.xpath("//div[@aria-labelledby='ui-dialog-title-"+(questionNumber+1)+"']//div[@name='survey_dialog_appmail_0' and @id='survey_dialog_appmail_0_"+(questionNumber-1)+"']//li/input[@name='add_survey_option']"));
			this.act.moveToElement(element).build().perform();
			if (element.isDisplayed()) {
				this.act.click(element).build().perform();
				Thread.sleep(1000);
			} else {
				SysLogger.log("Error : Add Option Button not present");
			}
			return new NewSurveyItemPage(this.driver);
		}
		
		public AppmailMessageComposePage clickOnOkButton(int questionNumber) throws InterruptedException {
			WebElement element = this.driver.findElement(By.xpath("//div[@aria-labelledby='ui-dialog-title-"+(questionNumber+1)+"']//button/span[text()='OK']//parent::button"));
			this.act.moveToElement(element).build().perform();
			if (element.isDisplayed()) {
				this.act.click(element).build().perform();
				Thread.sleep(5000);
			} else {
				SysLogger.log("Error : Survey Item OK Button not present");
			}
			return new AppmailMessageComposePage(this.driver);
		}		
	}
	
	public class AppmailTriggers {
		@FindBy(xpath = "//div[@id='appmail_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='4']")
		private WebElement appmailOneTimeTrigger;

		@FindBy(xpath = "//div[@id='appmail_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='3']")
		private WebElement appmailRecurrenceTrigger;

		@FindBy(xpath = "//div[@id='appmail_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='0']")
		private WebElement appmailDateTrigger;

		@FindBy(xpath = "//div[@id='appmail_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='2']")
		private WebElement appmailMemberEngagementTrigger;

		@FindBy(xpath = "//div[@id='appmail_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='1']")
		private WebElement appmailMemberProfileTrigger;

		@FindBy(xpath = "//div[@id='appmail_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='5']")
		private WebElement appmailGetMemberInfoTrigger;

		@FindBy(xpath = "//div[@id='appmail_trigger_0']/following-sibling::div/div[@class='ui-dialog-buttonset']/button/span[contains(text(), 'Add Trigger')]/ancestor::button")
		private WebElement addAppmailTriggerButton;

		@FindBy(xpath = "//div[@id='appmail_trigger_0']/following-sibling::div/div[@class='ui-dialog-buttonset']/button/span[contains(text(), 'Cancel')]/ancestor::button")
		private WebElement cancelTriggerButton;

		public AppmailTriggers() {
			PageFactory.initElements(driver, this);
			testReporter = ReporterFactory.getTest();
		}

		public boolean isCancelTriggerButtonPresent() {
			if (this.cancelTriggerButton.isDisplayed()) {
				return true;
			}
			return false;
		}

		public boolean isAppmailMemberProfileTriggerPresent() {
			if (this.appmailMemberProfileTrigger.isDisplayed()) {
				return true;
			}
			else {
				return DriverMethods.isElementPresent(driver, this.appmailMemberProfileTrigger);
			}
		}
		
		public boolean isAppmailDateTriggerPresent() {
			if (this.appmailDateTrigger.isDisplayed()) {
				return true;
			}
			return false;
		}

		public boolean isAddTriggerButtonPresent() {
			if (this.addAppmailTriggerButton.isDisplayed()) {
				return true;
			}
			return false;
		}
		
		public boolean isappmailDateTriggerPresent() {
			if (this.appmailDateTrigger.isDisplayed()) {
				return true;
			}
			return false;
		}
		
		public boolean isAppmailMemberEngagemantTriggerPresent() {
			if (this.appmailMemberEngagementTrigger.isDisplayed()) {
				return true;
			}
			else {
				return DriverMethods.isElementPresent(driver, this.appmailMemberEngagementTrigger);
			}
		}
		
		public ProfileTrigger clickOnMemberProfileTrigger() throws InterruptedException {
			if(this.isAppmailMemberProfileTriggerPresent()) {
				act.moveToElement(this.appmailMemberProfileTrigger).build().perform();
				this.appmailMemberProfileTrigger.click();
				Thread.sleep(3000);
			}
			return new ProfileTrigger();
		}
		
		public DateTrigger clickOnDateTrigger() throws InterruptedException {
			if(this.isappmailDateTriggerPresent()) {
				act.moveToElement(this.appmailDateTrigger).build().perform();
				this.appmailDateTrigger.click();
				Thread.sleep(3000);
			}
			return new DateTrigger();
		}
		
		public MemberEngagemantTrigger clickOnMemberEngagemantTrigger() throws InterruptedException {
			if(this.isAppmailMemberEngagemantTriggerPresent()) {
				act.moveToElement(this.appmailMemberEngagementTrigger).build().perform();
				this.appmailMemberEngagementTrigger.click();
				Thread.sleep(3000);
			}
			return new MemberEngagemantTrigger();
		}
		
		public OneTimeTrigger clickOnOneTimeTrigger() throws InterruptedException {
			if(this.appmailOneTimeTrigger.isDisplayed()) {
				act.moveToElement(this.appmailOneTimeTrigger).build().perform();
				this.appmailOneTimeTrigger.click();
				Thread.sleep(3000);
			}
			return new OneTimeTrigger();
		}
		
		public class ProfileTrigger {

			@FindBy(xpath = "//div[@id='appmail_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='1']/following-sibling::div//div[@id='profile_trigger_div']//div/input[@class='textbox sm-textbox inlineComponent']")
			private WebElement appmailProfileTriggerTimeDelayInlineInputTextBox;

			@FindBy(xpath = "//div[@id='appmail_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='1']/following-sibling::div//div[@id='profile_trigger_div']//div/input[@id='msg_field_value_appmail_0']")
			private WebElement appmailProfiletriggerFieldValueTextBox;

			public ProfileTrigger() {
				PageFactory.initElements(driver, this);
				testReporter = ReporterFactory.getTest();
			}

			public boolean isAppmailProfileTriggerTimeDelayInlineInputTextBoxPresent() {
				if (this.appmailProfileTriggerTimeDelayInlineInputTextBox.isDisplayed()) {
					return true;
				}
				return false;
			}

			public boolean isAppmailProfiletriggerFieldValueTextBoxPresent() {
				if (this.appmailProfiletriggerFieldValueTextBox.isDisplayed()) {
					return true;
				}
				return false;
			}
			
			public AppmailMessageComposePage clickOnSubmitTriggerButton() throws InterruptedException {
				SysLogger.log("Inside method appmail clickOnSubmitTriggerButton");
				if(isAddTriggerButtonPresent()) {
					SysLogger.log("Inside if condition is addtrigger button ");
					act.moveToElement(addAppmailTriggerButton).build().perform();
					SysLogger.log("moved to element clickOnSubmitTriggerButton");
					addAppmailTriggerButton.click();
					SysLogger.log("clicked on add trigger button");
					Thread.sleep(10000);
				}
				else {
					SysLogger.log("Error : add trigger button not present");
				}
				return new AppmailMessageComposePage(driver);
			}
			
			public ProfileTrigger selectTimeUnitDropDownByVisibleText(String visibleText) throws InterruptedException {
				if(visibleText.equalsIgnoreCase("Minutes")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='0']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Hours")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='1']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Days")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='2']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Weeks")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='3']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Months")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='4']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Immediately")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='5']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Years")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='6']\")).trigger('click');");
				} else {
					SysLogger.log("Error : Not able to select dropdown, given value '"+visibleText+"' may be incorrect");
				}
				Thread.sleep(3000);
				return new ProfileTrigger();
			}
			
			public ProfileTrigger selectFieldNameDropDownByValue(String value) throws InterruptedException {
				String javascript = "$($(\"#dk_container_appmail_message_0_trigger_profile_fieldName .dk_options a[data-dk-dropdown-value='"+value+"']\")).trigger('click');";
				js.executeScript(javascript);
				Thread.sleep(3000);
				return new ProfileTrigger();
			}
			
			public ProfileTrigger addFieldvalue(String value) throws InterruptedException {				
				if(isAppmailProfiletriggerFieldValueTextBoxPresent()) {
					this.appmailProfiletriggerFieldValueTextBox.sendKeys(value);
				}				
				Thread.sleep(3000);
				return new ProfileTrigger();
			}
			
			public ProfileTrigger addDelayValue(String value) throws InterruptedException {				
				if(isAppmailProfileTriggerTimeDelayInlineInputTextBoxPresent()) {
					this.appmailProfileTriggerTimeDelayInlineInputTextBox.sendKeys(value);
				}				
				Thread.sleep(3000);
				return new ProfileTrigger();
			}
			
			public ProfileTrigger selectProfileChangeDropDownByValue(String value) throws InterruptedException {
				if(value.equalsIgnoreCase("Any Value")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_profile_profileChangeType .dk_options a[data-dk-dropdown-value='0']\")).trigger('click');");
				} else if(value.equalsIgnoreCase("Specific Value")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_profile_profileChangeType .dk_options a[data-dk-dropdown-value='1']\")).trigger('click');");
				} else if(value.equalsIgnoreCase("New Value Only")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_profile_profileChangeType .dk_options a[data-dk-dropdown-value='2']\")).trigger('click');");
				} else if(value.equalsIgnoreCase("Any Specific Value")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_profile_profileChangeType .dk_options a[data-dk-dropdown-value='3']\")).trigger('click');");
				} else {
					SysLogger.log("Error : Not able to select dropdown, given value '"+value+"' may be incorrect");
				}
				Thread.sleep(2000);
				return new ProfileTrigger();
			}
			
		}
		
		public class DateTrigger {

			@FindBy(xpath = "//div[@id='appmail_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='0']/following-sibling::div//div[@id='date_trigger_div']//input[@class='textbox sm-textbox inlineComponent']")
			private WebElement appmailDateTriggerTimeTextBox;

			@FindBy(xpath = "//div[@id='appmail_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='0']/following-sibling::div//div[@id='date_trigger_div']/div/input[@class='textbox sm-textbox inlineComponent']")
			private WebElement appmailDateTriggerTimePickerTextBox;

			public DateTrigger() {
				PageFactory.initElements(driver, this);
				testReporter = ReporterFactory.getTest();
			}
			
			public boolean isAppmailDateTriggerTimeTextBoxPresent() {
				if (this.appmailDateTriggerTimeTextBox.isDisplayed()) {
					return true;
				}
				return false;
			}
			
			public DateTrigger enterDelyAmountOfTime(String number) {
				if(this.isAppmailDateTriggerTimeTextBoxPresent()) {
					this.appmailDateTriggerTimePickerTextBox.sendKeys(number);
				}
				return new DateTrigger();
			}
			
			public DateTrigger selectTimeUnitDropDownByVisibleText(String visibleText) throws InterruptedException {
				if(visibleText.equalsIgnoreCase("Minutes")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='0']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Hours")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='1']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Days")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='2']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Weeks")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='3']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Months")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='4']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Immediately")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='5']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Years")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='6']\")).trigger('click');");
				} else {
					SysLogger.log("Error : Not able to select dropdown, given value '"+visibleText+"' may be incorrect");
				}
				Thread.sleep(3000);
				return new DateTrigger();
			}
			
			public DateTrigger selectTriggerDateFieldNameByVisibleText(String visibleText) throws InterruptedException {
				String javascript = "$($(\"#dk_container_appmail_message_0_trigger_date_fieldName .dk_options a[data-dk-dropdown-value='"+visibleText+"']\")).trigger('click');";
				js.executeScript(javascript);
				Thread.sleep(3000);
				return new DateTrigger();
			}
			
			public DateTrigger selectMemberTimeFieldByVisibleText(String visibleText) throws InterruptedException {
				String javascript = "$($(\"#dk_container_appmail_message_0_trigger_date_memberTimeField .dk_options a[data-dk-dropdown-value='"+visibleText+"']\")).trigger('click');";
				js.executeScript(javascript);
				Thread.sleep(3000);
				return new DateTrigger();
			}
			
			public DateTrigger selectTriggerDateCondition(String visibleText) throws InterruptedException {
				if(visibleText.equalsIgnoreCase("Before")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_date_when .dk_options a[data-dk-dropdown-value='0']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("After")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_date_when .dk_options a[data-dk-dropdown-value='1']\")).trigger('click');");
				} else {
					SysLogger.log("Error : Not able to select dropdown, given value '"+visibleText+"' may be incorrect");
				}
				Thread.sleep(3000);
				return new DateTrigger();
			}
			
			public AppmailMessageComposePage clickOnSubmitTriggerButton() throws InterruptedException {
				if(isAddTriggerButtonPresent()) {
					act.moveToElement(addAppmailTriggerButton).build().perform();
					addAppmailTriggerButton.click();
					Thread.sleep(3000);
				}
				else {
					SysLogger.log("Error : add trigger button not present");
				}
				return new AppmailMessageComposePage(driver);
			}
			
		}
		
		public class MemberEngagemantTrigger {

			@FindBy(xpath = "//div[@id='appmail_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='2']/following::div[@id='msg_eventTrigger_external_event']")
			private WebElement appmailCustomEventButton;
			
			@FindBy(xpath = "//div[@id='appmail_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='2']/following::div[@id='msg_eventTrigger_system_event']")
			private WebElement appmailSystemEventButton;

			@FindBy(xpath = "//div[@id='appmail_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='2']/following::div[@id='external_event_div']//input[@id='trigger_event_attributevalue_appmail_0']")
			private WebElement appmailCustomEventAttributeValueInputBox;
			
			@FindBy(xpath = "//div[@id='appmail_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='2']/following-sibling::div//div[@id='event_trigger_div']//input[@class='textbox sm-textbox inlineComponent']")
			private WebElement appmailSystemTriggerTimeTextBox;

			public MemberEngagemantTrigger() {
				PageFactory.initElements(driver, this);
				testReporter = ReporterFactory.getTest();
			}

			public boolean isAppmailCustomEventButtonPresent() {
				if (this.appmailCustomEventButton.isDisplayed()) {
					return true;
				}
				return false;
			}

			public boolean isAppmailCustomEventAttributeValueInputBoxPresent() {
				if (this.appmailCustomEventAttributeValueInputBox.isDisplayed()) {
					return true;
				}
				return false;
			}
			
			public boolean isAppmailSystemEventButtonPresent() {
				if (this.appmailSystemEventButton.isDisplayed()) {
					return true;
				}
				return false;
			}
			
			public AppmailMessageComposePage clickOnSubmitTriggerButton() throws InterruptedException {
				if(isAddTriggerButtonPresent()) {
					Thread.sleep(4000);
					act.moveToElement(addAppmailTriggerButton).build().perform();
					addAppmailTriggerButton.click();
					Thread.sleep(3000);
				}
				else {
					SysLogger.log("Error : add trigger button not present");
				}
				return new AppmailMessageComposePage(driver);
			}
			
			public MemberEngagemantTrigger clickOnCustomEventButton() throws InterruptedException {
				if(this.isAppmailCustomEventButtonPresent()) {
					act.moveToElement(this.appmailCustomEventButton).build().perform();
					this.appmailCustomEventButton.click();
					Thread.sleep(3000);
				}
				else {
					SysLogger.log("Error : custom event button not present");
				}
				return new MemberEngagemantTrigger();
			}
			
			public MemberEngagemantTrigger clickOnSystemEventButton() throws InterruptedException {
				if(this.isAppmailCustomEventButtonPresent()) {
					act.moveToElement(this.appmailSystemEventButton).build().perform();
					this.appmailSystemEventButton.click();
					Thread.sleep(3000);
				}
				else {
					SysLogger.log("Error : System event button not present");
				}
				return new MemberEngagemantTrigger();
			}
			
			public MemberEngagemantTrigger enterCustomEventAttributeValue(String value) throws InterruptedException {
				if(this.isAppmailCustomEventAttributeValueInputBoxPresent()) {
					act.moveToElement(this.appmailCustomEventAttributeValueInputBox).build().perform();
					this.appmailCustomEventAttributeValueInputBox.sendKeys(value);
					Thread.sleep(2000);
				}
				else {
					SysLogger.log("Error : custom event attribute value inputbox not present");
				}
				return new MemberEngagemantTrigger();
			}
			
			public MemberEngagemantTrigger selectCustomEventName(String eventName) throws InterruptedException {
				String javascript = "$($(\"#dk_container_appmail_message_0_trigger_event_eventId .dk_options a:contains('"+eventName+"')\")).trigger('click');";
				js.executeScript(javascript);
				Thread.sleep(2000);
				return new MemberEngagemantTrigger();
			}
			
			public MemberEngagemantTrigger selectCustomEventAttributeName(String value) throws InterruptedException {
				String javascript = "$($(\"#dk_container_appmail_message_0_trigger_event_eventAttributeId .dk_options a:contains('"+value+"')\")).trigger('click');";
				Thread.sleep(5000); // adding more wait to proper load all attribute name before selecting
				js.executeScript(javascript);
				Thread.sleep(2000);
				return new MemberEngagemantTrigger();
			}
			
			public MemberEngagemantTrigger enterSystemEventDelyAmountOfTime(String number) {
				if(this.appmailSystemTriggerTimeTextBox.isDisplayed()) {
					this.appmailSystemTriggerTimeTextBox.sendKeys(number);
				}
				return new MemberEngagemantTrigger();
			}

			public MemberEngagemantTrigger selectTimeUnitDropDownByVisibleText(String visibleText) throws InterruptedException {
				if(visibleText.equalsIgnoreCase("Minutes")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_event_timeUnits .dk_options a[data-dk-dropdown-value='0']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Hours")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_event_timeUnits .dk_options a[data-dk-dropdown-value='1']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Days")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_event_timeUnits .dk_options a[data-dk-dropdown-value='2']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Weeks")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_event_timeUnits .dk_options a[data-dk-dropdown-value='3']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Months")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_event_timeUnits .dk_options a[data-dk-dropdown-value='4']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Immediately")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_event_timeUnits .dk_options a[data-dk-dropdown-value='5']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Years")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_event_timeUnits .dk_options a[data-dk-dropdown-value='6']\")).trigger('click');");
				} else {
					SysLogger.log("Error : Not able to select dropdown, given value '"+visibleText+"' maybe incorrect");
				}
				Thread.sleep(3000);
				return new MemberEngagemantTrigger();
			}

			public MemberEngagemantTrigger selectChannelNameByVisibleText(String visibleText) throws InterruptedException {
				if(visibleText.equalsIgnoreCase("Email")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_event_channel .dk_options a[data-dk-dropdown-value='0']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("SMS")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_event_channel .dk_options a[data-dk-dropdown-value='1']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Secure Messaging")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_event_channel .dk_options a[data-dk-dropdown-value='5']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Push")) {
					js.executeScript("$($(\"#dk_container_appmail_message_0_trigger_event_channel .dk_options a[data-dk-dropdown-value='9']\")).trigger('click');");
				} else {
					SysLogger.log("Error : Not able to select channel, given value '"+visibleText+"' maybe incorrect");
				}
				Thread.sleep(3000);
				return new MemberEngagemantTrigger();
			}
			
			public MemberEngagemantTrigger selectMessageNameByVisibleText(String visibleText) throws InterruptedException {
				String javascript = "$($(\"#dk_container_appmail_message_0_trigger_event_eventMessageId .dk_options a:contains('"+visibleText+"')\")).trigger('click');";
				Thread.sleep(3000); // adding wait to proper load all messages name before selecting
				js.executeScript(javascript);
				Thread.sleep(2000);
				return new MemberEngagemantTrigger();
			}
			
			public MemberEngagemantTrigger selectEventNameByVisibleText(String visibleText) throws InterruptedException {
				String javascript = "$($(\"#dk_container_appmail_message_0_trigger_event_systemEventId .dk_options a:contains('"+visibleText+"')\")).trigger('click');";
				Thread.sleep(3000); // adding wait to proper load all Events before selecting
				js.executeScript(javascript);
				Thread.sleep(2000);
				return new MemberEngagemantTrigger();
			}
			
			public MemberEngagemantTrigger selectEventAttributeValueByVisibleText(String visibleText) throws InterruptedException {
				String javascript = "$($(\"#dk_container_appmail_message_0_trigger_system_event_eventAttributeValue .dk_options a:contains('"+visibleText+"')\")).trigger('click');";
				Thread.sleep(3000); // adding wait to proper load all Event Attribute Values before selecting
				js.executeScript(javascript);
				Thread.sleep(2000);
				return new MemberEngagemantTrigger();
			}
			
		}
		
		public class OneTimeTrigger {

			@FindBy(xpath = "//div[@id='appmail_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='4']/following-sibling::div//input[@id='onetime_immediate_radio']")
			private WebElement appmailTriggerImmediatelyRadioButton;

			@FindBy(xpath = "//div[@id='appmail_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='4']/following-sibling::div//input[@id='onetime_start_time_radio']")
			private WebElement appmailTriggerStartTimeRadioButton;
			
			@FindBy(xpath = "//div[@id='appmail_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='4']/following-sibling::div//input[contains(@name, 'oneTime.startDate')]")
			private WebElement appmailDateInputBox;
			
			@FindBy(xpath = "//div[@id='appmail_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='4']/following-sibling::div//input[contains(@name, 'oneTime.startTime')]")
			private WebElement appmailTimeInputBox;
			
			@FindBy(xpath = "//a[contains(@class, 'ui-state-highlight') and @href='#']")
			private WebElement highlightedDate;
			
			@FindBy(className = "ui-datepicker-month")
			private WebElement monthPicker;
			
			@FindBy(className = "ui-datepicker-year")
			private WebElement yearPicker;

			public OneTimeTrigger() {
				PageFactory.initElements(driver, this);
				testReporter = ReporterFactory.getTest();
			}
			
			public OneTimeTrigger selectTriggerImmediatelyRadioButton() {
				if(this.appmailTriggerImmediatelyRadioButton.isDisplayed()) {
					this.appmailTriggerImmediatelyRadioButton.click();
				}
				return new OneTimeTrigger();
			}
			
			public OneTimeTrigger selectTriggerStartTimeRadioButton() {
				if(this.appmailTriggerStartTimeRadioButton.isDisplayed()) {
					this.appmailTriggerStartTimeRadioButton.click();
				}
				return new OneTimeTrigger();
			}
			
			public OneTimeTrigger enterDate(String date) {
				String[] dateObj = date.split("-");
				if(this.appmailDateInputBox.isDisplayed()) {
					this.appmailDateInputBox.click();
					this.selectMonth(dateObj[0]);
					this.selectYear(dateObj[2]);
					this.selectDate(dateObj[1]);
				}
				return new OneTimeTrigger();
			}
			
			public OneTimeTrigger enterTime(String time) {
				if(this.appmailTimeInputBox.isDisplayed()) {
					this.appmailTimeInputBox.click();
					this.appmailTimeInputBox.clear();
					this.appmailTimeInputBox.sendKeys(time);
				}
				return new OneTimeTrigger();
			}
			
			private void selectMonth(String month) {
				if (this.monthPicker.isDisplayed()) {
					Select sc = new Select(this.monthPicker);
					sc.selectByVisibleText(month);
				}
				else {
					SysLogger.log("Error : Not able to select Month name");
				}
			}
			
			private void selectYear(String year) {
				if (this.yearPicker.isDisplayed()) {
					Select sc = new Select(this.yearPicker);
					sc.selectByVisibleText(year);
				}
				else {
					SysLogger.log("Error : Not able to select Year name");
				}
			}
			
			private void selectDate(String date) {
				String dateXpath = "//table[@class='ui-datepicker-calendar']/tbody/tr/td/a[text()='"+date+"']";
				WebElement element = driver.findElement(By.xpath(dateXpath));
				element.click();
			}
			
			public AppmailMessageComposePage clickOnSubmitTriggerButton() throws InterruptedException {
				if(isAddTriggerButtonPresent()) {
					act.moveToElement(addAppmailTriggerButton).build().perform();
					addAppmailTriggerButton.click();
					Thread.sleep(3000);
				}
				else {
					SysLogger.log("Error : add trigger button not present");
				}
				return new AppmailMessageComposePage(driver);
			}			
		}
	}
}
