package com.application.objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

public class EmailMessageComposePage {
	
	WebDriver driver;
	ExtentTest testReporter;
	Actions act;
	JavascriptExecutor js;
	WebDriverWait wait;
	
	@FindBy (xpath = "//a[@href='/home' and contains(text(), 'mPulse Mobile')]")
	private WebElement homePageLink;
	
	@FindBy (xpath = "//div[@id='common_workarea']/div[@class='saving-bar']/div[@class='loadingmessage']/span")
	private WebElement loaderSavingBar;
	
	@FindBy (xpath = "//div[@id='email-message-accordion']//form[@id='event_email_compose_0']//textarea[contains(@placeholder, 'enter your email subject')]")
	private WebElement emailSubjectTextBox;

	@FindBy(xpath = "//div[@id='email-message-accordion']//form[@id='event_email_compose_0']//input[@id='trackClickThrough']")
	private WebElement emailLinkShorteningCheckBox;

	@FindBy(xpath = "//div[@id='email-message-accordion']//form[@id='event_email_compose_0']//input[@id='enableDeeplinkForSubPreferences']")
	private WebElement emailEnableSubPreferencesCheckBox;

	@FindBy(xpath = "//div[@id='email-message-accordion']//form[@id='event_email_compose_0']//input[@id='enableDeeplinkWithSecuremessage' and contains(@name, 'Securemessage')]")
	private WebElement emailSecureMessageCheckBox;

	@FindBy(xpath = "//div[@id='email-message-accordion']//form[@id='event_email_compose_0']//div[@class='allow-redirect']/preceding-sibling::div/span/select[@id='selectedAppmailExpire_0']")
	private WebElement emailDeeplinkValidSelectBox;

	@FindBy(xpath = "//div[@id='email-message-accordion']//form[@id='event_email_compose_0']//div[@class='allow-redirect']/input[@type='checkbox']")
	private WebElement redirectUrlCheckBox;

	@FindBy(xpath = "//div[@id='email-message-accordion']//form[@id='event_email_compose_0']//div[@class='allow-redirect']/input[@type='text']")
	private WebElement redirectUrlTextBox;
	
	@FindBy (xpath = "//div[@id='email-message-accordion']//form[@id='event_email_compose_0']//div[@class='nav-pill-item email_design_btn']")
	private WebElement designEmailButton;
	
	@FindBy (xpath = "//div[@id='email-message-accordion']//form[@id='event_email_compose_0']//div[@id='email_tabs']//a[contains(text (), 'Edit Email')]")
	private WebElement editEmailButton;

	@FindBy(xpath = "//div[@id='email-message-accordion']//form[@id='event_email_compose_0']//div[@class='template-view new_template_view']//div[@class='template-type']/input[@class='btn plainEmail']")
	private WebElement plainEmailSelectButton;
	
	@FindBy(xpath = "//div[@id='email-message-accordion']//form[@id='event_email_compose_0']//div[@class='template-view new_template_view']//div[@class='template-type']/input[@class='btn htmlEmail']")
	private WebElement htmlEmailSelectButton;

	@FindBy(xpath = "//div[@id='email-message-accordion']//form[@id='event_email_compose_0']//input[@id='enableDeeplinkWithSecuremessage' and contains(@name, 'Frequency')]")
	private WebElement emailFrequencyControlCheckBox;

	@FindBy(xpath = "//div[@id='email-message-accordion']//form[@id='event_email_compose_0']//div[@id='plainTextEditor_0']//div[@class='ace_layer ace_text-layer']/div[1]/div")
	private WebElement emailTextBoxFirstLine;
	
	@FindBy(xpath = "//form[@id='event_email_compose_0']//div[@id='email_tabs']//div[@class='left-button-wrap']/span[contains(text(), 'Back to Templates')]")
	private WebElement emailBackToTemplates;
	
	@FindBy (xpath = "//div[@id='email-message-accordion']//form[@id='event_email_compose_0']//div[@class='right-button-wrap']/a[contains(@class, 'design-save')]")
	private WebElement emailComposeSaveButton;
	
	@FindBy (xpath = "//div[@id='email-message-accordion']//form[@id='event_email_compose_0']/div/div/div[@class='popup container-popup']/div/div[@class='popup-header clearfix']/span")
	private WebElement closeComposeEmailButton;

	@FindBy(xpath = "//div[@id='email-message-accordion']//form[@id='event_email_compose_0']//div/div[@id='add_trigger']")
	private WebElement emailAddTrigger;
	
	@FindBy (xpath = "//div[@class='common_form_control campaign_details_control']/button/span[contains(text(), 'Next')]/ancestor::button[@id='message_submit']")
	private WebElement campaignNextButton;
	
	@FindBy (xpath = "//div[@class='common_form_control campaign_details_control']/button/span[contains(text(), 'Save')]/ancestor::button[@class='icon_button only_save shift_right']")
	private WebElement campaignSaveButton;
	
	@FindBy (xpath = "//div[@class='common_form_control campaign_details_control']/button/span[contains(text(), 'Next')]/ancestor::button[@id='message_submit']/following-sibling::a[@class='common_form_cancel']")
	private WebElement campaignBackButton;
	
	@FindBy(xpath = "//span[@class='ui-button-text' and text()='Ok']")
	private WebElement messageSchedulerAlertOkButton;

	public EmailMessageComposePage(WebDriver driver) {
		this.driver = driver;
		act = new Actions(driver);
		js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
		wait = new WebDriverWait(driver, 120);
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
		if(this.campaignBackButton.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isEmailSubjectTextBoxPresent() {
		if(this.emailSubjectTextBox.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isEmailLinkShorteningCheckBoxPresent() {
		if(this.emailLinkShorteningCheckBox.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isEmailEnableSubPreferencesCheckBoxPresent() {
		if(this.emailEnableSubPreferencesCheckBox.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isEmailSecureMessageCheckBoxPresent() {
		if(this.emailSecureMessageCheckBox.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isemailDeeplinkValidSelectBoxPresent() {
		if (this.emailDeeplinkValidSelectBox.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isDesignEmailButtonPresent() {
		if(this.designEmailButton.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isEditEmailButtonPresent() {
		if(this.editEmailButton.isDisplayed()) {
			return true;
		}
		return false;
	}

	public boolean isPlainEmailSelectButtonPresent() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		if (this.plainEmailSelectButton.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isEmailComposeSaveButtonPresent() {
		if (this.emailComposeSaveButton.isDisplayed()) {
			return true;
		}
		return false;
	}

	public boolean isEmailAddTriggerPresent() {
		if (wait.until(ExpectedConditions.visibilityOf(this.emailAddTrigger)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isComposeEmailCloseButtonPresent() {
		if (this.closeComposeEmailButton.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public EmailMessageComposePage enterEmailSubject(String emailSubject) throws InterruptedException {
		DriverMethods.waitForElementUntillDisappear(this.driver, this.loaderSavingBar);
		act.moveToElement(this.emailSubjectTextBox).build().perform();
		this.emailSubjectTextBox.click();
		this.emailSubjectTextBox.clear();
		this.emailSubjectTextBox.sendKeys(emailSubject);
		Thread.sleep(3000);
		SysLogger.log("Info : Email subject text box is filled with value "+emailSubject);
		return new EmailMessageComposePage(this.driver);
	}
	
	public EmailMessageComposePage selectLinkTrackingCheckBox() throws InterruptedException {
		if(this.isEmailLinkShorteningCheckBoxPresent()) {
			act.moveToElement(this.emailLinkShorteningCheckBox).click().build().perform();
			//this.emailLinkShorteningCheckBox.click();
			DriverMethods.isElementPresent(driver, homePageLink);
		}
		return new EmailMessageComposePage(driver);
	}
	
	public EmailMessageComposePage clickOnEmailEnableSubPreferencesCheckBox() throws InterruptedException {
		if (this.isEmailEnableSubPreferencesCheckBoxPresent()) {
			act.moveToElement(this.emailEnableSubPreferencesCheckBox).build().perform();
			this.emailEnableSubPreferencesCheckBox.click();
			Thread.sleep(1000);
		} else {
			SysLogger.log("Error : Email Enable Sub Preferences Check Box not present");
		}
		return new EmailMessageComposePage(this.driver);
	}
	
	public EmailMessageComposePage selectEmailSecureMessageCheckBox() throws InterruptedException {
		if(this.isEmailSecureMessageCheckBoxPresent()) {
			act.moveToElement(this.emailSecureMessageCheckBox).click().build().perform();
			DriverMethods.isElementPresent(driver, homePageLink);
		}
		return new EmailMessageComposePage(driver);
	}
	
	public EmailMessageComposePage clickOnAllowRedirectUrlCheckBox() throws InterruptedException {
		if (this.redirectUrlCheckBox.isDisplayed()) {
			act.moveToElement(this.redirectUrlCheckBox).build().perform();
			this.redirectUrlCheckBox.click();
			Thread.sleep(2000);
		} else {
			SysLogger.log("Error : Email Allow Redirect Url Check Box not present");
		}
		return new EmailMessageComposePage(this.driver);
	}
	
	public EmailMessageComposePage enterRedirectUrl(String URL) throws InterruptedException {
		if (this.redirectUrlTextBox.isDisplayed()) {
			act.moveToElement(this.redirectUrlTextBox).build().perform();
			this.redirectUrlTextBox.sendKeys(URL);
			Thread.sleep(2000);
		} else {
			SysLogger.log("Error : Email Redirect Url text Box not present");
		}
		return new EmailMessageComposePage(this.driver);
	}

	public EmailMessageComposePage clickOnDesignEmail() throws InterruptedException {
		if(this.isDesignEmailButtonPresent()) {
			act.moveToElement(this.designEmailButton).click(this.designEmailButton).build().perform();
			Thread.sleep(5000);
		} else {
			SysLogger.log("Error : Design Email button is not present or not displayed");
		}
		return new EmailMessageComposePage(this.driver);
	}
	
	public EmailMessageComposePage clickOnEditEmail() throws InterruptedException {
		if(this.isEditEmailButtonPresent()) {
			act.moveToElement(this.editEmailButton).click(this.editEmailButton).build().perform();
			Thread.sleep(5000);
		} else {
			SysLogger.log("Error : Edit Email button is not present or not displayed");
		}
		return new EmailMessageComposePage(this.driver);
	}
	
	public EmailMessageComposePage clickOnPlainEmailSelectButton() throws InterruptedException {
		if(this.isPlainEmailSelectButtonPresent()) {
			act.moveToElement(this.plainEmailSelectButton).click(this.plainEmailSelectButton).build().perform();
			Thread.sleep(5000);
		} else {
			SysLogger.log("Error : Plain Email button is not present or not displayed");
		}
		return new EmailMessageComposePage(this.driver);
	}
	
	public EmailMessageComposePage clickOnHtmlEmailSelectButton() throws InterruptedException {
		if(this.htmlEmailSelectButton.isDisplayed()) {
			act.moveToElement(this.htmlEmailSelectButton).click(this.htmlEmailSelectButton).build().perform();
			Thread.sleep(5000);
		} else {
			SysLogger.log("Error : HTML Email button is not present or not displayed");
		}
		return new EmailMessageComposePage(this.driver);
	}
	
	public CampaignComposePage clickOnSaveButton() throws InterruptedException {
		Thread.sleep(5000);
		if(this.isCampaignSaveButtonPresent()) {
			act.moveToElement(this.campaignSaveButton).build().perform();
			this.campaignSaveButton.click();
			Thread.sleep(10000);
			DriverMethods.waitForElementUntillDisappear(this.driver, this.loaderSavingBar);
			DriverMethods.isElementPresent(driver, homePageLink);
		}
		return new CampaignComposePage(driver);
	}
	
	public EmailMessageComposePage clickOnEmailComposeSaveButton() throws InterruptedException {
		if(this.isEmailComposeSaveButtonPresent()) {
			this.emailComposeSaveButton.click();
			Thread.sleep(10000);
			DriverMethods.waitForElementUntillDisappear(this.driver, this.loaderSavingBar);
			DriverMethods.isElementPresent(driver, this.closeComposeEmailButton);
		}
		return new EmailMessageComposePage(driver);
	}
	
//	public EmailMessageComposePage enterEmailText(String emailText) throws InterruptedException {
//		js.executeScript("jQuery('div.ace_content div.ace_text-layer div.ace_line_group div.ace_line').first().text('Hi, ##FIRSTNAME## ! " + emailText +
//				" ##LIST.PERMISSION_REMINDER##');");
//		Thread.sleep(3000);
//		return new EmailMessageComposePage(driver);
//	}
	
	public EmailMessageComposePage enterEmailText(String emailText) throws InterruptedException {
		WebElement element = this.driver.findElement(By.xpath("//div[@id='plainTextEditor_0']/div[@class='ace_scroller']/div[@class='ace_content']/div[contains(@class, 'ace_text-layer')]/div[@class='ace_line_group'][1]/div[contains(@class, 'ui-droppable')]"));	
		act.moveToElement(element).click(element).sendKeys(" "+emailText+" ").build().perform();
		Thread.sleep(3000);
		return new EmailMessageComposePage(driver);
	}	
	
	public EmailMessageComposePage enterEmailHtmlText(String emailText) throws InterruptedException {
		WebElement element = this.driver.findElement(By.xpath("//div[@id='htmlEditor_0']/div[@class='ace_scroller']/div/div[3]/div[7]/div/span[2]"));	
		act.moveToElement(element).click(element).sendKeys(emailText).build().perform();	
		return new EmailMessageComposePage(driver);
	}
	
	public EmailMessageComposePage clickOnEmailComposeCloseButton() throws InterruptedException {
		if(this.isComposeEmailCloseButtonPresent()) {
			this.closeComposeEmailButton.click();
//			act.moveToElement(this.closeComposeEmailButton).build().perform();
//			act.doubleClick().build().perform();
			//js.executeAsyncScript("jQuery('span.close_bee_design').first().click();");
			Thread.sleep(10000);
			act.keyDown(Keys.CONTROL).sendKeys("-").keyUp(Keys.CONTROL).perform();
			act.keyDown(Keys.CONTROL).sendKeys("+").keyUp(Keys.CONTROL).perform();
			DriverMethods.isElementPresent(driver, homePageLink);
		}
		return new EmailMessageComposePage(driver);
	}
	
	public CampaignPlanPage clickOnBackButton() {
		if(this.isCampaignBackButtonPresent()) {
			this.campaignBackButton.click();
			DriverMethods.isElementPresent(driver, homePageLink);
		}
		return new CampaignPlanPage(driver);
	}

	public EmailTriggers clickOnAddTriggerButton() throws InterruptedException {
		if (this.isEmailAddTriggerPresent()) {
			act.moveToElement(this.emailAddTrigger).build().perform();
			this.emailAddTrigger.click();
			Thread.sleep(8000);
			DriverMethods.isElementPresent(driver, homePageLink);
		} else {
			SysLogger.log("Error : Email Trigger are not present");
		}
		return new EmailTriggers();
	}
	
	public EmailMessageComposePage clickOnOneTimeMessageSchedulerAlertOkButton() throws InterruptedException {
		if (this.messageSchedulerAlertOkButton.isDisplayed()) {
			act.moveToElement(this.messageSchedulerAlertOkButton).build().perform();
			this.messageSchedulerAlertOkButton.click();
			Thread.sleep(1000);
		} else {
			SysLogger.log("Error : message Scheduler Alert OK Button not present");
		}
		return new EmailMessageComposePage(this.driver);
	}
	
	public class EmailTriggers {
		
		WebDriverWait wait;

		@FindBy(xpath = "//div[@id='email_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='4']")
		private WebElement emailOneTimeTrigger;

		@FindBy(xpath = "//div[@id='email_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='3']")
		private WebElement emailRecurrenceTrigger;

		@FindBy(xpath = "//div[@id='email_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='0']")
		private WebElement emailDateTrigger;

		@FindBy(xpath = "//div[@id='email_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='2']")
		private WebElement emailMemberEngagementTrigger;

		@FindBy(xpath = "//div[@id='email_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='1']")
		private WebElement emailMemberProfileTrigger;

		@FindBy(xpath = "//div[@id='email_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='5']")
		private WebElement emailGetMemberInfoTrigger;

		@FindBy(xpath = "//div[@id='email_trigger_0']/following-sibling::div/div[@class='ui-dialog-buttonset']/button/span[contains(text(), 'Add Trigger')]/ancestor::button")
		private WebElement addTriggerButton;

		@FindBy(xpath = "//div[@id='email_trigger_0']/following-sibling::div/div[@class='ui-dialog-buttonset']/button/span[contains(text(), 'Cancel')]/ancestor::button")
		private WebElement cancelTriggerButton;

		public EmailTriggers() {
			PageFactory.initElements(driver, this);
			testReporter = ReporterFactory.getTest();
			wait = new WebDriverWait(driver, 60);
		}

		public boolean isCancelTriggerButtonPresent() {
			if (this.cancelTriggerButton.isDisplayed()) {
				return true;
			}
			return false;
		}

		public boolean isEmailMemberProfileTriggerPresent() {
			if (wait.until(ExpectedConditions.visibilityOf(this.emailMemberProfileTrigger)).isDisplayed()) {
				return true;
			}
			else {
				return DriverMethods.isElementPresent(driver, this.emailMemberProfileTrigger);
			}
		}
		
		public boolean isEmailDateTriggerPresent() {
			if (this.emailDateTrigger.isDisplayed()) {
				return true;
			}
			return false;
		}

		public boolean isAddTriggerButtonPresent() {
			if (this.addTriggerButton.isDisplayed()) {
				return true;
			}
			return false;
		}
		
		public boolean isEmailMemberEngagemantTriggerPresent() {
			if (this.emailMemberEngagementTrigger.isDisplayed()) {
				return true;
			}
			else {
				return DriverMethods.isElementPresent(driver, this.emailMemberEngagementTrigger);
			}
		}
		
		public ProfileTrigger clickOnMemberProfileTrigger() throws InterruptedException {
			if(this.isEmailMemberProfileTriggerPresent()) {
				act.moveToElement(this.emailMemberProfileTrigger).build().perform();
				this.emailMemberProfileTrigger.click();
				Thread.sleep(3000);
			}
			return new ProfileTrigger();
		}
		
		public DateTrigger clickOnDateTrigger() throws InterruptedException {
			if(this.isEmailDateTriggerPresent()) {
				act.moveToElement(this.emailDateTrigger).build().perform();
				this.emailDateTrigger.click();
				Thread.sleep(3000);
			}
			return new DateTrigger();
		}
		
		public MemberEngagemantTrigger clickOnMemberEngagemantTrigger() throws InterruptedException {
			if(this.isEmailMemberEngagemantTriggerPresent()) {
				act.moveToElement(this.emailMemberEngagementTrigger).build().perform();
				this.emailMemberEngagementTrigger.click();
				Thread.sleep(3000);
			}
			return new MemberEngagemantTrigger();
		}
		
		public OneTimeTrigger clickOnOneTimeTrigger() throws InterruptedException {
			if(this.emailOneTimeTrigger.isDisplayed()) {
				act.moveToElement(this.emailOneTimeTrigger).build().perform();
				this.emailOneTimeTrigger.click();
				Thread.sleep(3000);
			}
			return new OneTimeTrigger();
		}

		public class ProfileTrigger {

			@FindBy(xpath = "//div[@id='email_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='1']/following-sibling::div//div[@id='profile_trigger_div']//div/input[@class='textbox sm-textbox inlineComponent']")
			private WebElement emailProfileTriggerTimeDelayInlineInputTextBox;

			@FindBy(xpath = "//div[@id='email_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='1']/following-sibling::div//div[@id='profile_trigger_div']//div/input[@id='msg_field_value_email_0']")
			private WebElement emailProfiletriggerFieldValueTextBox;

			public ProfileTrigger() {
				PageFactory.initElements(driver, this);
				testReporter = ReporterFactory.getTest();
			}

			public boolean isEmailProfileTriggerTimeDelayInlineInputTextBoxPresent() {
				if (this.emailProfileTriggerTimeDelayInlineInputTextBox.isDisplayed()) {
					return true;
				}
				return false;
			}

			public boolean isEmailProfiletriggerFieldValueTextBoxPresent() {
				if (this.emailProfiletriggerFieldValueTextBox.isDisplayed()) {
					return true;
				}
				return false;
			}
			
			public EmailMessageComposePage clickOnSubmitTriggerButton() throws InterruptedException {
				if(isAddTriggerButtonPresent()) {
					act.moveToElement(addTriggerButton).build().perform();
					addTriggerButton.click();
					Thread.sleep(3000);
				}
				else {
					SysLogger.log("Error : add trigger button not present");
				}
				return new EmailMessageComposePage(driver);
			}
			
			public ProfileTrigger selectTimeUnitDropDownByVisibleText(String visibleText) throws InterruptedException {
				if(visibleText.equalsIgnoreCase("Minutes")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='0']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Hours")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='1']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Days")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='2']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Weeks")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='3']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Months")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='4']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Immediately")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='5']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Years")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='6']\")).trigger('click');");
				} else {
					SysLogger.log("Error : Not able to select dropdown, given value '"+visibleText+"' may be incorrect");
				}
				Thread.sleep(3000);
				return new ProfileTrigger();
			}
			
			public ProfileTrigger selectFieldNameDropDownByValue(String value) throws InterruptedException {
				String javascript = "$($(\"#dk_container_email_message_0_trigger_profile_fieldName .dk_options a[data-dk-dropdown-value='"+value+"']\")).trigger('click');";
				js.executeScript(javascript);
				Thread.sleep(3000);
				return new ProfileTrigger();
			}
			
			public ProfileTrigger selectProfileChangeDropDownByValue(String value) throws InterruptedException {
				if(value.equalsIgnoreCase("Any Value")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_profile_profileChangeType .dk_options a[data-dk-dropdown-value='0']\")).trigger('click');");
				} else if(value.equalsIgnoreCase("Specific Value")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_profile_profileChangeType .dk_options a[data-dk-dropdown-value='1']\")).trigger('click');");
				} else if(value.equalsIgnoreCase("New Value Only")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_profile_profileChangeType .dk_options a[data-dk-dropdown-value='2']\")).trigger('click');");
				} else if(value.equalsIgnoreCase("Any Specific Value")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_profile_profileChangeType .dk_options a[data-dk-dropdown-value='3']\")).trigger('click');");
				} else {
					SysLogger.log("Error : Not able to select dropdown, given value '"+value+"' may be incorrect");
				}
				Thread.sleep(2000);
				return new ProfileTrigger();
			}

			public ProfileTrigger addFieldvalue(String value) throws InterruptedException {				
				if(isEmailProfiletriggerFieldValueTextBoxPresent()) {
					this.emailProfiletriggerFieldValueTextBox.sendKeys(value);
				}				
				Thread.sleep(3000);
				return new ProfileTrigger();
			}
			
			public ProfileTrigger addDelayValue(String value) throws InterruptedException {				
				if(isEmailProfileTriggerTimeDelayInlineInputTextBoxPresent()) {
					this.emailProfileTriggerTimeDelayInlineInputTextBox.sendKeys(value);
				}				
				Thread.sleep(3000);
				return new ProfileTrigger();
			}
			
		}

		public class DateTrigger {

			@FindBy(xpath = "//div[@id='email_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='0']/following-sibling::div//div[@id='date_trigger_div']//input[@class='textbox sm-textbox inlineComponent']")
			private WebElement emailDateTriggerTimeTextBox;

			@FindBy(xpath = "//div[@id='email_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='0']/following-sibling::div//div[@id='date_trigger_div']/div/input[@class='textbox sm-textbox inlineComponent']")
			private WebElement emailDateTriggerTimePickerTextBox;

			public DateTrigger() {
				PageFactory.initElements(driver, this);
				testReporter = ReporterFactory.getTest();
			}
			
			public boolean isEmailDateTriggerTimeTextBoxPresent() {
				if (this.emailDateTriggerTimeTextBox.isDisplayed()) {
					return true;
				}
				return false;
			}
			
			public DateTrigger enterDelyAmountOfTime(String number) {
				if(this.isEmailDateTriggerTimeTextBoxPresent()) {
					this.emailDateTriggerTimePickerTextBox.sendKeys(number);
				}
				return new DateTrigger();
			}
			
			public DateTrigger selectTimeUnitDropDownByVisibleText(String visibleText) throws InterruptedException {
				if(visibleText.equalsIgnoreCase("Minutes")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='0']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Hours")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='1']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Days")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='2']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Weeks")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='3']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Months")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='4']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Immediately")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='5']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Years")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='6']\")).trigger('click');");
				} else {
					SysLogger.log("Error : Not able to select dropdown, given value '"+visibleText+"' may be incorrect");
				}
				Thread.sleep(3000);
				return new DateTrigger();
			}
			
			public DateTrigger selectTriggerDateFieldNameByVisibleText(String visibleText) throws InterruptedException {
				String javascript = "$($(\"#dk_container_email_message_0_trigger_date_fieldName .dk_options a[data-dk-dropdown-value='"+visibleText+"']\")).trigger('click');";
				js.executeScript(javascript);
				Thread.sleep(3000);
				return new DateTrigger();
			}
			
			public DateTrigger selectMemberTimeFieldByVisibleText(String visibleText) throws InterruptedException {
				String javascript = "$($(\"#dk_container_email_message_0_trigger_date_memberTimeField .dk_options a[data-dk-dropdown-value='"+visibleText+"']\")).trigger('click');";
				js.executeScript(javascript);
				Thread.sleep(3000);
				return new DateTrigger();
			}
			
			public DateTrigger selectTriggerDateCondition(String visibleText) throws InterruptedException {
				if(visibleText.equalsIgnoreCase("Before")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_date_when .dk_options a[data-dk-dropdown-value='0']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("After")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_date_when .dk_options a[data-dk-dropdown-value='1']\")).trigger('click');");
				} else {
					SysLogger.log("Error : Not able to select dropdown, given value '"+visibleText+"' may be incorrect");
				}
				Thread.sleep(3000);
				return new DateTrigger();
			}
			
			public EmailMessageComposePage clickOnSubmitTriggerButton() throws InterruptedException {
				if(isAddTriggerButtonPresent()) {
					act.moveToElement(addTriggerButton).build().perform();
					addTriggerButton.click();
					Thread.sleep(3000);
				}
				else {
					SysLogger.log("Error : add trigger button not present");
				}
				return new EmailMessageComposePage(driver);
			}
			
		}
		
		public class MemberEngagemantTrigger {

			@FindBy(xpath = "//div[@id='email_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='2']/following::div[@id='msg_eventTrigger_external_event']")
			private WebElement emailCustomEventButton;
			
			@FindBy(xpath = "//div[@id='email_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='2']/following::div[@id='msg_eventTrigger_system_event']")
			private WebElement emailSystemEventButton;

			@FindBy(xpath = "//div[@id='email_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='2']/following::div[@id='external_event_div']//input[@id='trigger_event_attributevalue_email_0']")
			private WebElement emailCustomEventAttributeValueInputBox;
			
			@FindBy(xpath = "//div[@id='email_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='2']/following-sibling::div//div[@id='event_trigger_div']//input[@class='textbox sm-textbox inlineComponent']")
			private WebElement emailSystemTriggerTimeTextBox;

			public MemberEngagemantTrigger() {
				PageFactory.initElements(driver, this);
				testReporter = ReporterFactory.getTest();
			}

			public boolean isEmailCustomEventButtonPresent() {
				if (this.emailCustomEventButton.isDisplayed()) {
					return true;
				}
				return false;
			}

			public boolean isEmailCustomEventAttributeValueInputBoxPresent() {
				if (this.emailCustomEventAttributeValueInputBox.isDisplayed()) {
					return true;
				}
				return false;
			}
			
			public boolean isEmailSystemEventButtonPresent() {
				if (this.emailSystemEventButton.isDisplayed()) {
					return true;
				}
				return false;
			}
			
			public EmailMessageComposePage clickOnSubmitTriggerButton() throws InterruptedException {
				if(isAddTriggerButtonPresent()) {
					act.moveToElement(addTriggerButton).build().perform();
					Thread.sleep(3000);
					addTriggerButton.click();
					Thread.sleep(5000);
				}
				else {
					SysLogger.log("Error : add trigger button not present");
				}
				return new EmailMessageComposePage(driver);
			}
			
			public MemberEngagemantTrigger clickOnCustomEventButton() throws InterruptedException {
				if(this.isEmailCustomEventButtonPresent()) {
					act.moveToElement(this.emailCustomEventButton).build().perform();
					this.emailCustomEventButton.click();
					Thread.sleep(3000);
				}
				else {
					SysLogger.log("Error : custom event button not present");
				}
				return new MemberEngagemantTrigger();
			}
			
			public MemberEngagemantTrigger clickOnSystemEventButton() throws InterruptedException {
				if(this.isEmailCustomEventButtonPresent()) {
					act.moveToElement(this.emailSystemEventButton).build().perform();
					this.emailSystemEventButton.click();
					Thread.sleep(3000);
				}
				else {
					SysLogger.log("Error : System event button not present");
				}
				return new MemberEngagemantTrigger();
			}
			
			public MemberEngagemantTrigger enterCustomEventAttributeValue(String value) throws InterruptedException {
				if(this.isEmailCustomEventAttributeValueInputBoxPresent()) {
					act.moveToElement(this.emailCustomEventAttributeValueInputBox).build().perform();
					this.emailCustomEventAttributeValueInputBox.sendKeys(value);
					Thread.sleep(2000);
				}
				else {
					SysLogger.log("Error : custom event attribute value inputbox not present");
				}
				return new MemberEngagemantTrigger();
			}
			
			public MemberEngagemantTrigger selectCustomEventName(String eventName) throws InterruptedException {
				String javascript = "$($(\"#dk_container_email_message_0_trigger_event_eventId .dk_options a:contains('"+eventName+"')\")).trigger('click');";
				js.executeScript(javascript);
				Thread.sleep(2000);
				return new MemberEngagemantTrigger();
			}
			
			public MemberEngagemantTrigger selectCustomEventAttributeName(String value) throws InterruptedException {
				String javascript = "$($(\"#dk_container_email_message_0_trigger_event_eventAttributeId .dk_options a:contains('"+value+"')\")).trigger('click');";
				Thread.sleep(5000); // adding more wait to proper load all attribute name before selecting
				js.executeScript(javascript);
				Thread.sleep(2000);
				return new MemberEngagemantTrigger();
			}
			
			public MemberEngagemantTrigger enterSystemEventDelyAmountOfTime(String number) {
				if(this.emailSystemTriggerTimeTextBox.isDisplayed()) {
					this.emailSystemTriggerTimeTextBox.sendKeys(number);
				}
				return new MemberEngagemantTrigger();
			}

			public MemberEngagemantTrigger selectTimeUnitDropDownByVisibleText(String visibleText) throws InterruptedException {
				if(visibleText.equalsIgnoreCase("Minutes")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_event_timeUnits .dk_options a[data-dk-dropdown-value='0']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Hours")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_event_timeUnits .dk_options a[data-dk-dropdown-value='1']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Days")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_event_timeUnits .dk_options a[data-dk-dropdown-value='2']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Weeks")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_event_timeUnits .dk_options a[data-dk-dropdown-value='3']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Months")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_event_timeUnits .dk_options a[data-dk-dropdown-value='4']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Immediately")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_event_timeUnits .dk_options a[data-dk-dropdown-value='5']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Years")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_event_timeUnits .dk_options a[data-dk-dropdown-value='6']\")).trigger('click');");
				} else {
					SysLogger.log("Error : Not able to select dropdown, given value '"+visibleText+"' maybe incorrect");
				}
				Thread.sleep(3000);
				return new MemberEngagemantTrigger();
			}

			public MemberEngagemantTrigger selectChannelNameByVisibleText(String visibleText) throws InterruptedException {
				if(visibleText.equalsIgnoreCase("Email")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_event_channel .dk_options a[data-dk-dropdown-value='0']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("SMS")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_event_channel .dk_options a[data-dk-dropdown-value='1']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Secure Messaging")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_event_channel .dk_options a[data-dk-dropdown-value='5']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Push")) {
					js.executeScript("$($(\"#dk_container_email_message_0_trigger_event_channel .dk_options a[data-dk-dropdown-value='9']\")).trigger('click');");
				} else {
					SysLogger.log("Error : Not able to select channel, given value '"+visibleText+"' maybe incorrect");
				}
				Thread.sleep(3000);
				return new MemberEngagemantTrigger();
			}
			
			public MemberEngagemantTrigger selectMessageNameByVisibleText(String visibleText) throws InterruptedException {
				String javascript = "$($(\"#dk_container_email_message_0_trigger_event_eventMessageId .dk_options a:contains('"+visibleText+"')\")).trigger('click');";
				Thread.sleep(3000); // adding wait to proper load all messages name before selecting
				js.executeScript(javascript);
				Thread.sleep(3000);
				return new MemberEngagemantTrigger();
			}
			
			public MemberEngagemantTrigger selectEventNameByVisibleText(String visibleText) throws InterruptedException {
				String javascript = "$($(\"#dk_container_email_message_0_trigger_event_systemEventId .dk_options a:contains('"+visibleText+"')\")).trigger('click');";
				Thread.sleep(5000); // adding wait to proper load all Events before selecting
				js.executeScript(javascript);
				Thread.sleep(5000);
				return new MemberEngagemantTrigger();
			}
			
			public MemberEngagemantTrigger selectEventAttributeValueByVisibleText(String visibleText) throws InterruptedException {
				String javascript = "$($(\"#dk_container_email_message_0_trigger_system_event_eventAttributeValue .dk_options a:contains('"+visibleText+"')\")).trigger('click');";
				Thread.sleep(3000); // adding wait to proper load all Event Attribute Values before selecting
				js.executeScript(javascript);
				Thread.sleep(2000);
				return new MemberEngagemantTrigger();
			}
			
		}
		
		public class OneTimeTrigger {

			@FindBy(xpath = "//div[@id='email_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='4']/following-sibling::div//input[@id='onetime_immediate_radio']")
			private WebElement emailTriggerImmediatelyRadioButton;

			@FindBy(xpath = "//div[@id='email_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='4']/following-sibling::div//input[@id='onetime_start_time_radio']")
			private WebElement emailTriggerStartTimeRadioButton;
			
			@FindBy(xpath = "//div[@id='email_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='4']/following-sibling::div//input[contains(@name, 'oneTime.startDate')]")
			private WebElement emailDateInputBox;
			
			@FindBy(xpath = "//div[@id='email_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='4']/following-sibling::div//input[contains(@name, 'oneTime.startTime')]")
			private WebElement emailTimeInputBox;
			
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
				if(this.emailTriggerImmediatelyRadioButton.isDisplayed()) {
					this.emailTriggerImmediatelyRadioButton.click();
				}
				return new OneTimeTrigger();
			}
			
			public OneTimeTrigger selectTriggerStartTimeRadioButton() {
				if(this.emailTriggerStartTimeRadioButton.isDisplayed()) {
					this.emailTriggerStartTimeRadioButton.click();
				}
				return new OneTimeTrigger();
			}
			
			public OneTimeTrigger enterDate(String date) {
				String[] dateObj = date.split("-");
				if(this.emailDateInputBox.isDisplayed()) {
					this.emailDateInputBox.click();
					this.selectMonth(dateObj[0]);
					this.selectYear(dateObj[2]);
					this.selectDate(dateObj[1]);
				}
				return new OneTimeTrigger();
			}
			
			public OneTimeTrigger enterTime(String time) {
				if(this.emailTimeInputBox.isDisplayed()) {
					this.emailTimeInputBox.click();
					this.emailTimeInputBox.clear();
					this.emailTimeInputBox.sendKeys(time);
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
			
			public EmailMessageComposePage clickOnSubmitTriggerButton() throws InterruptedException {
				if(isAddTriggerButtonPresent()) {
					act.moveToElement(addTriggerButton).build().perform();
					addTriggerButton.click();
					Thread.sleep(3000);
				}
				else {
					SysLogger.log("Error : add trigger button not present");
				}
				return new EmailMessageComposePage(driver);
			}			
		}

	}

}
