package com.application.objectRepository;

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

public class SmsMessageComposePage {

	WebDriver driver;
	ExtentTest testReporter;
	Actions act;
	JavascriptExecutor js;
	WebDriverWait wait;
	
	@FindBy (xpath = "//a[@href='/home' and contains(text(), 'mPulse Mobile')]")
	private WebElement homePageLink;
	
	@FindBy (xpath = "//div[@id='common_workarea']/div[@class='saving-bar']/div[@class='loadingmessage']/span")
	private WebElement loaderSavingBar;
	
	@FindBy (xpath = "//div[@id='common_workarea']/div[@class='progress-bar']/div[@class='loadingmessage']/span")
	private WebElement progressBarLoader;

	@FindBy(xpath = "//div[@id='sms-message-accordion']//form[@id='event_sms_compose_0']//input[@id='trackClickThrough']")
	private WebElement smsLinkShorteningCheckBox;

	@FindBy(xpath = "//div[@id='sms-message-accordion']//form[@id='event_sms_compose_0']//input[@id='pretendHttp']")
	private WebElement smsPrependHttpCheckBox;

	@FindBy(xpath = "//div[@id='sms-message-accordion']//form[@id='event_sms_compose_0']//input[@id='enableShortcodeVCFDeeplink']")
	private WebElement smsEnableVCFCheckBox;

	@FindBy(xpath = "//div[@id='sms-message-accordion']//form[@id='event_sms_compose_0']//input[@id='enableDeeplinkForSubPreferences']")
	private WebElement smsEnableSubPreferencesCheckBox;

	@FindBy(xpath = "//div[@id='sms-message-accordion']//form[@id='event_sms_compose_0']//input[@id='enableDeeplinkWithSecuremessage' and contains(@name, 'Securemessage')]")
	private WebElement smsSecureMessageCheckBox;
	
	@FindBy(xpath = "//div[@id='sms-message-accordion']//form[@id='event_sms_compose_0']//input[contains(@name, 'allowRedirect')]")
	private WebElement smsAllowRedirectUrlCheckBox;
	
	@FindBy(xpath = "//div[@id='sms-message-accordion']//form[@id='event_sms_compose_0']//input[contains(@name, 'redirectUrl')]")
	private WebElement smsRedirectUrl;

	@FindBy(xpath = "//div[@id='sms-message-accordion']//form[@id='event_sms_compose_0']//input[@id='enableBranding']")
	private WebElement smsEnableBrandingCheckBox;

	@FindBy(xpath = "//div[@id='sms-message-accordion']//form[@id='event_sms_compose_0']//input[@id='brandingCode']")
	private WebElement smsBrandingCodeTextBox;

	@FindBy(xpath = "//div[@id='sms-message-accordion']//form[@id='event_sms_compose_0']//input[@value='prepend']")
	private WebElement smsBrandingPrependCheckBox;

	@FindBy(xpath = "//div[@id='sms-message-accordion']//form[@id='event_sms_compose_0']//input[@value='append']")
	private WebElement smsBrandingAppendCheckBox;

	@FindBy(xpath = "//div[@id='sms-message-accordion']//form[@id='event_sms_compose_0']//input[@id='enableDeeplinkWithSecuremessage' and contains(@name, 'Frequency')]")
	private WebElement smsFrequencyControlCheckBox;
	
	@FindBy(xpath = "//div[@id='sms-message-accordion']//form[@id='event_sms_compose_0']//div[@data-value='8']")
	private WebElement ucs2Tab;

	@FindBy(xpath = "//div[@id='sms-message-accordion']//form[@id='event_sms_compose_0']//textarea[contains(@name, 'message[0].text')]")
	private WebElement smsTextBox;

	@FindBy(xpath = "//div[@id='sms-message-accordion']//form[@id='event_sms_compose_0']//div/div[@id='add_trigger']")
	private WebElement smsAddTrigger;
	
	@FindBy (xpath = "//div[@class='common_form_control campaign_details_control']/button/span[contains(text(), 'Next')]/ancestor::button[@id='message_submit']")
	private WebElement campaignNextButton;
	
	@FindBy (xpath = "//div[@class='common_form_control campaign_details_control']/button/span[contains(text(), 'Save')]/ancestor::button[@class='icon_button only_save shift_right']")
	private WebElement campaignSaveButton;
	
	@FindBy (xpath = "//div[@class='common_form_control campaign_details_control']/button/span[contains(text(), 'Next')]/ancestor::button[@id='message_submit']/following-sibling::a[@class='common_form_cancel']")
	private WebElement campaignBackButton;
	
	@FindBy(xpath = "//span[@class='ui-button-text' and text()='Ok']")
	private WebElement messageSchedulerAlertOkButton;

	public SmsMessageComposePage(WebDriver driver) {
		this.driver = driver;
		act = new Actions(driver);
		js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
		wait = new WebDriverWait(driver, 120);
	}
	
	public boolean isCampaignSaveButtonPresent() {
		if(this.wait.until(ExpectedConditions.visibilityOf(this.campaignSaveButton)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isCampaignBackButtonPresent() {
		if(this.wait.until(ExpectedConditions.visibilityOf(this.campaignBackButton)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isCampaignNextButtonPresent() {
		if(this.wait.until(ExpectedConditions.visibilityOf(this.campaignNextButton)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isAllowRedirectUrlCheckBoxPresent() {
		if(this.wait.until(ExpectedConditions.visibilityOf(this.smsAllowRedirectUrlCheckBox)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isRedirectUrlTextBoxPresent() {
		if(this.wait.until(ExpectedConditions.visibilityOf(this.smsRedirectUrl)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public CampaignComposePage clickOnSaveButton() throws InterruptedException {
		if(this.isCampaignSaveButtonPresent()) {
			this.campaignSaveButton.click();
			Thread.sleep(12000); // campaign save is taking more time when two message
			DriverMethods.waitForElementUntillDisappear(this.driver, this.loaderSavingBar);
			DriverMethods.isElementPresent(this.driver, this.homePageLink);
		}
		return new CampaignComposePage(this.driver);
	}
	
	public CampaignPlanPage clickOnBackButton() {
		if(this.isCampaignBackButtonPresent()) {
			this.campaignBackButton.click();
			DriverMethods.isElementPresent(driver, homePageLink);
		}
		return new CampaignPlanPage(driver);
	}

	public boolean isSmsLinkShorteningCheckBoxPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.smsLinkShorteningCheckBox)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isSmsPretendHttpCheckBoxPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.smsPrependHttpCheckBox)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isSmsEnableVCFCheckBoxPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.smsEnableVCFCheckBox)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isSmsEnableSubPreferencesCheckBoxPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.smsEnableSubPreferencesCheckBox)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isSmsSecureMessageCheckBoxPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.smsSecureMessageCheckBox)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isSmsEnableBrandingCheckBoxPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.smsEnableBrandingCheckBox)).isDisplayed()) {
			return true;
		}
		return false;
	}

	public boolean isSmsTextBoxPresent() {
		DriverMethods.isElementPresent(this.driver, this.homePageLink);
		if (this.wait.until(ExpectedConditions.visibilityOf(this.smsTextBox)).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isUcs2TabPresent() {
		DriverMethods.isElementPresent(this.driver, this.homePageLink);
		if (this.wait.until(ExpectedConditions.visibilityOf(this.ucs2Tab)).isDisplayed()) {
			return true;
		}
		return false;
	}

	public boolean isSmsAddTriggerPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.smsAddTrigger)).isDisplayed()) {
			return true;
		}
		return false;
	}

	public SmsMessageComposePage enterSmsText(String smsText) throws InterruptedException {
		Thread.sleep(12000);
		DriverMethods.waitForElementUntillDisappear(this.driver, this.progressBarLoader);
		DriverMethods.waitForElementUntillDisappear(this.driver, this.loaderSavingBar);
		DriverMethods.isElementPresent(this.driver, homePageLink);
		try {
			act.moveToElement(this.smsTextBox).build().perform();
			this.smsTextBox.click();
			this.smsTextBox.clear();
			this.smsTextBox.sendKeys(smsText);
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
			js.executeScript("$($(\"#text\")).val('"+smsText+"');");
			Thread.sleep(3000);
		}
		return new SmsMessageComposePage(this.driver);
	}

	public SmsTriggers clickOnAddTriggerButton() throws InterruptedException {
		Thread.sleep(10000);
		DriverMethods.waitForElementUntillDisappear(this.driver, this.loaderSavingBar);
		DriverMethods.isElementPresent(this.driver, this.homePageLink);
		if (this.isSmsAddTriggerPresent()) {
			act.moveToElement(this.smsAddTrigger).build().perform();
			this.smsAddTrigger.click();
			DriverMethods.isElementPresent(driver, homePageLink);
			Thread.sleep(3000);
		} else {
			SysLogger.log("Error : Sms Text Box not present");
		}
		return new SmsTriggers();
	}
	
	public SmsMessageComposePage clickOnSmsLinkShorteningCheckBox() throws InterruptedException {
		if (this.isSmsLinkShorteningCheckBoxPresent()) {
			act.moveToElement(this.smsLinkShorteningCheckBox).build().perform();
			this.smsLinkShorteningCheckBox.click();
			Thread.sleep(1000);
		} else {
			SysLogger.log("Error : sms Link Shortening Check Box not present");
		}
		return new SmsMessageComposePage(this.driver);
	}
	
	public SmsMessageComposePage clickOnSmsPrependHttpCheckBox() throws InterruptedException {
		if (this.isSmsPretendHttpCheckBoxPresent()) {
			act.moveToElement(this.smsPrependHttpCheckBox).build().perform();
			this.smsPrependHttpCheckBox.click();
			Thread.sleep(5000);
		} else {
			SysLogger.log("Error : sms Prepend Http Check Box not present");
		}
		return new SmsMessageComposePage(this.driver);
	}
	
	public SmsMessageComposePage clickOnAddBrnadingCodeCheckBox() throws InterruptedException {
		if (this.isSmsEnableBrandingCheckBoxPresent()) {
			act.moveToElement(this.smsEnableBrandingCheckBox).build().perform();
			this.smsEnableBrandingCheckBox.click();
			Thread.sleep(1000);
		} else {
			SysLogger.log("Error : sms branding Check Box not present");
		}
		return new SmsMessageComposePage(this.driver);
	}
	
	public SmsMessageComposePage enterBrandingCode(String code) throws InterruptedException {
		if (this.smsBrandingCodeTextBox.isDisplayed()) {
			act.moveToElement(this.smsBrandingCodeTextBox).build().perform();
			this.smsBrandingCodeTextBox.sendKeys(code);
			Thread.sleep(1000);
		} else {
			SysLogger.log("Error : sms branding text Box not present");
		}
		return new SmsMessageComposePage(this.driver);
	}
	
	public SmsMessageComposePage selectAppendBrandingCheckBox() throws InterruptedException {
		if (this.smsBrandingAppendCheckBox.isDisplayed()) {
			act.moveToElement(this.smsBrandingAppendCheckBox).build().perform();
			this.smsBrandingAppendCheckBox.click();
			Thread.sleep(1000);
		} else {
			SysLogger.log("Error : sms append branding Check Box not present");
		}
		return new SmsMessageComposePage(this.driver);
	}
	
	public SmsMessageComposePage selectUcs2Tab() throws InterruptedException {
		if (this.isUcs2TabPresent()) {
			act.moveToElement(this.ucs2Tab).build().perform();
			this.ucs2Tab.click();
			Thread.sleep(1000);
		} else {
			SysLogger.log("Error : spanish tab is not present");
		}
		return new SmsMessageComposePage(this.driver);
	}
	
	public SmsMessageComposePage clickOnSmsEnableVCFCheckBox() throws InterruptedException {
		if (this.isSmsEnableVCFCheckBoxPresent()) {
			act.moveToElement(this.smsEnableVCFCheckBox).build().perform();
			this.smsEnableVCFCheckBox.click();
			Thread.sleep(1000);
		} else {
			SysLogger.log("Error : sms Enable VCF Check Box not present");
		}
		return new SmsMessageComposePage(this.driver);
	}
	
	public SmsMessageComposePage clickOnSmsEnableSubPreferencesCheckBox() throws InterruptedException {
		if (this.isSmsEnableSubPreferencesCheckBoxPresent()) {
			act.moveToElement(this.smsEnableSubPreferencesCheckBox).build().perform();
			this.smsEnableSubPreferencesCheckBox.click();
			Thread.sleep(1000);
		} else {
			SysLogger.log("Error : sms Enable Sub Preferences Check Box not present");
		}
		return new SmsMessageComposePage(this.driver);
	}
	
	public SmsMessageComposePage clickOnSmsSecureMessageCheckBox() throws InterruptedException {
		if (this.isSmsSecureMessageCheckBoxPresent()) {
			Thread.sleep(5000); // adding more waiting time load sms message compose page
			act.moveToElement(this.smsSecureMessageCheckBox).build().perform();
			this.smsSecureMessageCheckBox.click();
			Thread.sleep(8000);
		} else {
			SysLogger.log("Error : sms Secure Message Check Box not present");
		}
		return new SmsMessageComposePage(this.driver);
	}
	
	public SmsMessageComposePage clickOnAllowRedirectUrlCheckBox() throws InterruptedException {
		if (this.isAllowRedirectUrlCheckBoxPresent()) {
			act.moveToElement(this.smsAllowRedirectUrlCheckBox).build().perform();
			this.smsAllowRedirectUrlCheckBox.click();
			Thread.sleep(2000);
		} else {
			SysLogger.log("Error : sms sms Allow Redirect Url Check Box not present");
		}
		return new SmsMessageComposePage(this.driver);
	}
	
	public SmsMessageComposePage enterRedirectUrl(String URL) throws InterruptedException {
		if (this.isRedirectUrlTextBoxPresent()) {
			act.moveToElement(this.smsRedirectUrl).build().perform();
			this.smsRedirectUrl.sendKeys(URL);
			Thread.sleep(2000);
		} else {
			SysLogger.log("Error : sms sms Redirect Url text Box not present");
		}
		return new SmsMessageComposePage(this.driver);
	}
	
	public SmsMessageComposePage clickOnOneTimeMessageSchedulerAlertOkButton() throws InterruptedException {
		if (this.messageSchedulerAlertOkButton.isDisplayed()) {
			act.moveToElement(this.messageSchedulerAlertOkButton).build().perform();
			this.messageSchedulerAlertOkButton.click();
			Thread.sleep(1000);
		} else {
			SysLogger.log("Error : message Scheduler Alert OK Button not present");
		}
		return new SmsMessageComposePage(this.driver);
	}

	public class SmsTriggers {

		@FindBy(xpath = "//div[@id='sms_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='4']")
		private WebElement smsOneTimeTrigger;

		@FindBy(xpath = "//div[@id='sms_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='3']")
		private WebElement smsRecurrenceTrigger;

		@FindBy(xpath = "//div[@id='sms_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='0']")
		private WebElement smsDateTrigger;

		@FindBy(xpath = "//div[@id='sms_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='2']")
		private WebElement smsMemberEngagementTrigger;

		@FindBy(xpath = "//div[@id='sms_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='1']")
		private WebElement smsMemberProfileTrigger;
		
		@FindBy(xpath = "//div[@id='sms_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='2']")
		private WebElement smsMemberEngagemantTrigger;

		@FindBy(xpath = "//div[@id='sms_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='5']")
		private WebElement smsGetMemberInfoTrigger;

		@FindBy(xpath = "//div[@id='sms_trigger_0']/following-sibling::div/div[@class='ui-dialog-buttonset']/button/span[contains(text(), 'Add Trigger')]/ancestor::button")
		private WebElement addTriggerButton;

		@FindBy(xpath = "//div[@id='sms_trigger_0']/following-sibling::div/div[@class='ui-dialog-buttonset']/button/span[contains(text(), 'Cancel')]/ancestor::button")
		private WebElement cancelTriggerButton;

		public SmsTriggers() {
			PageFactory.initElements(driver, this);
			testReporter = ReporterFactory.getTest();
		}

		public boolean isCancelTriggerButtonPresent() {
			if (this.cancelTriggerButton.isDisplayed()) {
				return true;
			}
			return false;
		}

		public boolean isSmsMemberProfileTriggerPresent() {
			if (this.smsMemberProfileTrigger.isDisplayed()) {
				return true;
			}
			else {
				return DriverMethods.isElementPresent(driver, this.smsMemberProfileTrigger);
			}
		}
		
		public boolean isSmsMemberEngagemantTriggerPresent() {
			if (this.smsMemberEngagemantTrigger.isDisplayed()) {
				return true;
			}
			else {
				return DriverMethods.isElementPresent(driver, this.smsMemberEngagemantTrigger);
			}
		}
		
		public boolean isSmsDateTriggerPresent() {
			if (this.smsDateTrigger.isDisplayed()) {
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
		
		public OneTimeTrigger clickOnOneTimeTrigger() throws InterruptedException {
			if(this.smsOneTimeTrigger.isDisplayed()) {
				act.moveToElement(this.smsOneTimeTrigger).build().perform();
				this.smsOneTimeTrigger.click();
				Thread.sleep(3000);
			}
			return new OneTimeTrigger();
		}
		
		public ProfileTrigger clickOnMemberProfileTrigger() throws InterruptedException {
			if(this.isSmsMemberProfileTriggerPresent()) {
				act.moveToElement(this.smsMemberProfileTrigger).build().perform();
				this.smsMemberProfileTrigger.click();
				Thread.sleep(5000);
			}
			return new ProfileTrigger();
		}
		
		public MemberEngagemantTrigger clickOnMemberEngagemantTrigger() throws InterruptedException {
			if(this.isSmsMemberEngagemantTriggerPresent()) {
				act.moveToElement(this.smsMemberEngagemantTrigger).build().perform();
				this.smsMemberEngagemantTrigger.click();
				Thread.sleep(3000);
			}
			return new MemberEngagemantTrigger();
		}
		
		public DateTrigger clickOnDateTrigger() throws InterruptedException {
			if(this.isSmsDateTriggerPresent()) {
				act.moveToElement(this.smsDateTrigger).build().perform();
				this.smsDateTrigger.click();
				Thread.sleep(3000);
			}
			return new DateTrigger();
		}

		public class ProfileTrigger {

			@FindBy(xpath = "//div[@id='sms_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='1']/following-sibling::div//div[@id='profile_trigger_div']//div/input[@class='textbox sm-textbox inlineComponent']")
			private WebElement smsProfileTriggerTimeDelayInlineInputTextBox;

			@FindBy(xpath = "//div[@id='sms_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='1']/following-sibling::div//div[@id='profile_trigger_div']//select[@id='sms_message_0_trigger_profile_fieldName']")
			private WebElement smsProfileTriggerFieldNameDropDown;

			@FindBy(xpath = "//div[@id='sms_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='1']/following-sibling::div//div[@id='profile_trigger_div']//select[@id='sms_message_0_trigger_profile_profileChangeType']")
			private WebElement smsProfileTriggerProfileChangeDropDown;

			@FindBy(xpath = "//div[@id='sms_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='1']/following-sibling::div//div[@id='profile_trigger_div']//div/input[@id='msg_field_value_sms_0']")
			private WebElement smsProfiletriggerFieldValueTextBox;

			public ProfileTrigger() {
				PageFactory.initElements(driver, this);
				testReporter = ReporterFactory.getTest();
			}

			public boolean isSmsProfileTriggerTimeDelayInlineInputTextBoxPresent() {
				if (this.smsProfileTriggerTimeDelayInlineInputTextBox.isDisplayed()) {
					return true;
				}
				return false;
			}

			public boolean isSmsProfileTriggerFieldNameDropDownPresent() {
				if (this.smsProfileTriggerFieldNameDropDown.isDisplayed()) {
					return true;
				}
				return false;
			}

			public boolean isSmsProfileTriggerProfileChangeDropDownPresent() {
				if (this.smsProfileTriggerProfileChangeDropDown.isDisplayed()) {
					return true;
				}
				return false;
			}

			public boolean isSmsProfiletriggerFieldValueTextBoxPresent() {
				if (this.smsProfiletriggerFieldValueTextBox.isDisplayed()) {
					return true;
				}
				return false;
			}
			
			public SmsMessageComposePage clickOnSubmitTriggerButton() throws InterruptedException {
				if(isAddTriggerButtonPresent()) {
					Thread.sleep(4000);
					act.moveToElement(addTriggerButton).build().perform();
					addTriggerButton.click();
					Thread.sleep(3000);
				}
				else {
					SysLogger.log("Error : add trigger button not present");
				}
				return new SmsMessageComposePage(driver);
			}
			
			public ProfileTrigger selectTimeUnitDropDownByVisibleText(String visibleText) throws InterruptedException {
				if(visibleText.equalsIgnoreCase("Minutes")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='0']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Hours")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='1']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Days")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='2']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Weeks")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='3']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Months")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='4']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Immediately")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='5']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Years")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='6']\")).trigger('click');");
				} else {
					SysLogger.log("Error : Not able to select dropdown, given value '"+visibleText+"' may be incorrect");
				}
				Thread.sleep(3000);
				return new ProfileTrigger();
			}
			
			public ProfileTrigger selectFieldNameDropDownByValue(String value) throws InterruptedException {
				String javascript = "$($(\"#dk_container_sms_message_0_trigger_profile_fieldName .dk_options a[data-dk-dropdown-value='"+value+"']\")).trigger('click');";
				js.executeScript(javascript);
				Thread.sleep(3000);
				return new ProfileTrigger();
			}
			
			public ProfileTrigger addFieldvalue(String value) throws InterruptedException {				
				if(isSmsProfiletriggerFieldValueTextBoxPresent()) {
					this.smsProfiletriggerFieldValueTextBox.sendKeys(value);
				}				
				Thread.sleep(3000);
				return new ProfileTrigger();
			}
			
			public ProfileTrigger addDelayValue(String value) throws InterruptedException {				
				if(isSmsProfileTriggerTimeDelayInlineInputTextBoxPresent()) {
					this.smsProfileTriggerTimeDelayInlineInputTextBox.sendKeys(value);
				}				
				Thread.sleep(3000);
				return new ProfileTrigger();
			}
			
			public ProfileTrigger selectProfileChangeDropDownByValue(String value) throws InterruptedException {
				if(value.equalsIgnoreCase("Any Value")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_profile_profileChangeType .dk_options a[data-dk-dropdown-value='0']\")).trigger('click');");
				} else if(value.equalsIgnoreCase("Specific Value")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_profile_profileChangeType .dk_options a[data-dk-dropdown-value='1']\")).trigger('click');");
				} else if(value.equalsIgnoreCase("New Value Only")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_profile_profileChangeType .dk_options a[data-dk-dropdown-value='2']\")).trigger('click');");
				} else if(value.equalsIgnoreCase("Any Specific Value")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_profile_profileChangeType .dk_options a[data-dk-dropdown-value='3']\")).trigger('click');");
				} else {
					SysLogger.log("Error : Not able to select dropdown, given value '"+value+"' may be incorrect");
				}
				Thread.sleep(2000);
				return new ProfileTrigger();
			}
			
		}
		
		public class MemberEngagemantTrigger {

			@FindBy(xpath = "//div[@id='sms_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='2']/following::div[@id='msg_eventTrigger_external_event']")
			private WebElement smsCustomEventButton;
			
			@FindBy(xpath = "//div[@id='sms_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='2']/following::div[@id='msg_eventTrigger_system_event']")
			private WebElement smsSystemEventButton;

			@FindBy(xpath = "//div[@id='sms_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='2']/following::div[@id='external_event_div']//input[@id='trigger_event_attributevalue_sms_0']")
			private WebElement smsCustomEventAttributeValueInputBox;
			
			@FindBy(xpath = "//div[@id='sms_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='2']/following-sibling::div//div[@id='event_trigger_div']//input[@class='textbox sm-textbox inlineComponent']")
			private WebElement smsSystemTriggerTimeTextBox;

			public MemberEngagemantTrigger() {
				PageFactory.initElements(driver, this);
				testReporter = ReporterFactory.getTest();
			}

			public boolean isSmsCustomEventButtonPresent() {
				if (this.smsCustomEventButton.isDisplayed()) {
					return true;
				}
				return false;
			}
			
			public boolean isSmsSystemEventButtonPresent() {
				if (this.smsSystemEventButton.isDisplayed()) {
					return true;
				}
				return false;
			}

			public boolean isSmsCustomEventAttributeValueInputBoxPresent() {
				if (this.smsCustomEventAttributeValueInputBox.isDisplayed()) {
					return true;
				}
				return false;
			}
			
			public SmsMessageComposePage clickOnSubmitTriggerButton() throws InterruptedException {
				if(isAddTriggerButtonPresent()) {
					Thread.sleep(4000);
					act.moveToElement(addTriggerButton).build().perform();
					addTriggerButton.click();
					Thread.sleep(3000);
				}
				else {
					SysLogger.log("Error : add trigger button not present");
				}
				return new SmsMessageComposePage(driver);
			}
			
			public MemberEngagemantTrigger clickOnCustomEventButton() throws InterruptedException {
				if(this.isSmsCustomEventButtonPresent()) {
					act.moveToElement(this.smsCustomEventButton).build().perform();
					this.smsCustomEventButton.click();
					Thread.sleep(3000);
				}
				else {
					SysLogger.log("Error : custom event button not present");
				}
				return new MemberEngagemantTrigger();
			}
			
			public MemberEngagemantTrigger clickOnSystemEventButton() throws InterruptedException {
				if(this.isSmsCustomEventButtonPresent()) {
					act.moveToElement(this.smsSystemEventButton).build().perform();
					this.smsSystemEventButton.click();
					Thread.sleep(3000);
				}
				else {
					SysLogger.log("Error : System event button not present");
				}
				return new MemberEngagemantTrigger();
			}
			
			public MemberEngagemantTrigger enterCustomEventAttributeValue(String value) throws InterruptedException {
				if(this.isSmsCustomEventAttributeValueInputBoxPresent()) {
					act.moveToElement(this.smsCustomEventAttributeValueInputBox).build().perform();
					this.smsCustomEventAttributeValueInputBox.sendKeys(value);
					Thread.sleep(2000);
				}
				else {
					SysLogger.log("Error : custom event attribute value inputbox not present");
				}
				return new MemberEngagemantTrigger();
			}
			
			public MemberEngagemantTrigger selectCustomEventName(String eventName) throws InterruptedException {
				String javascript = "$($(\"#dk_container_sms_message_0_trigger_event_eventId .dk_options a:contains('"+eventName+"')\")).trigger('click');";
				js.executeScript(javascript);
				Thread.sleep(2000);
				return new MemberEngagemantTrigger();
			}
			
			public MemberEngagemantTrigger selectCustomEventAttributeName(String value) throws InterruptedException {
				String javascript = "$($(\"#dk_container_sms_message_0_trigger_event_eventAttributeId .dk_options a:contains('"+value+"')\")).trigger('click');";
				Thread.sleep(5000); // adding more wait to proper load all attribute name before selecting
				js.executeScript(javascript);
				Thread.sleep(2000);
				return new MemberEngagemantTrigger();
			}
			
			public MemberEngagemantTrigger enterSystemEventDelyAmountOfTime(String number) {
				if(this.smsSystemTriggerTimeTextBox.isDisplayed()) {
					this.smsSystemTriggerTimeTextBox.sendKeys(number);
				}
				return new MemberEngagemantTrigger();
			}
			
			public MemberEngagemantTrigger selectTimeUnitDropDownByVisibleText(String visibleText) throws InterruptedException {
				if(visibleText.equalsIgnoreCase("Minutes")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_event_timeUnits .dk_options a[data-dk-dropdown-value='0']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Hours")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_event_timeUnits .dk_options a[data-dk-dropdown-value='1']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Days")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_event_timeUnits .dk_options a[data-dk-dropdown-value='2']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Weeks")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_event_timeUnits .dk_options a[data-dk-dropdown-value='3']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Months")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_event_timeUnits .dk_options a[data-dk-dropdown-value='4']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Immediately")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_event_timeUnits .dk_options a[data-dk-dropdown-value='5']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Years")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_event_timeUnits .dk_options a[data-dk-dropdown-value='6']\")).trigger('click');");
				} else {
					SysLogger.log("Error : Not able to select dropdown, given value '"+visibleText+"' maybe incorrect");
				}
				Thread.sleep(3000);
				return new MemberEngagemantTrigger();
			}
			
			public MemberEngagemantTrigger selectChannelNameByVisibleText(String visibleText) throws InterruptedException {
				if(visibleText.equalsIgnoreCase("Email")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_event_channel .dk_options a[data-dk-dropdown-value='0']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("SMS")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_event_channel .dk_options a[data-dk-dropdown-value='1']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Secure Messaging")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_event_channel .dk_options a[data-dk-dropdown-value='5']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Push")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_event_channel .dk_options a[data-dk-dropdown-value='9']\")).trigger('click');");
				} else {
					SysLogger.log("Error : Not able to select channel, given value '"+visibleText+"' maybe incorrect");
				}
				Thread.sleep(3000);
				return new MemberEngagemantTrigger();
			}
			
			public MemberEngagemantTrigger selectMessageNameByVisibleText(String visibleText) throws InterruptedException {
				String javascript = "$($(\"#dk_container_sms_message_0_trigger_event_eventMessageId .dk_options a:contains('"+visibleText+"')\")).trigger('click');";
				Thread.sleep(3000); // adding wait to proper load all messages name before selecting
				js.executeScript(javascript);
				Thread.sleep(2000);
				return new MemberEngagemantTrigger();
			}
			
			public MemberEngagemantTrigger selectEventNameByVisibleText(String visibleText) throws InterruptedException {
				String javascript = "$($(\"#dk_container_sms_message_0_trigger_event_systemEventId .dk_options a:contains('"+visibleText+"')\")).trigger('click');";
				Thread.sleep(3000); // adding wait to proper load all Events before selecting
				js.executeScript(javascript);
				Thread.sleep(2000);
				return new MemberEngagemantTrigger();
			}
			
			public MemberEngagemantTrigger selectEventAttributeValueByVisibleText(String visibleText) throws InterruptedException {
				String javascript = "$($(\"#dk_container_sms_message_0_trigger_system_event_eventAttributeValue .dk_options a:contains('"+visibleText+"')\")).trigger('click');";
				Thread.sleep(3000); // adding wait to proper load all Event Attribute Values before selecting
				js.executeScript(javascript);
				Thread.sleep(2000);
				return new MemberEngagemantTrigger();
			}
			
		}

		public class DateTrigger {

			@FindBy(xpath = "//div[@id='sms_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='0']/following-sibling::div//div[@id='date_trigger_div']//input[@class='textbox sm-textbox inlineComponent']")
			private WebElement smsDateTriggerTimeTextBox;

			@FindBy(xpath = "//div[@id='sms_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='0']/following-sibling::div//div[@id='date_trigger_div']/div/input[@class='textbox sm-textbox inlineComponent']")
			private WebElement smsDateTriggerTimePickerTextBox;
			
			public DateTrigger() {
				PageFactory.initElements(driver, this);
				testReporter = ReporterFactory.getTest();
			}
			
			public boolean isSmsDateTriggerTimeTextBoxPresent() {
				if (this.smsDateTriggerTimeTextBox.isDisplayed()) {
					return true;
				}
				return false;
			}
			
			public DateTrigger enterDelyAmountOfTime(String number) {
				if(this.isSmsDateTriggerTimeTextBoxPresent()) {
					this.smsDateTriggerTimePickerTextBox.sendKeys(number);
					//this.smsDateTriggerTimePickerTextBox.sendKeys(Keys.TAB);
				}
				return new DateTrigger();
			}
			
			public DateTrigger selectTimeUnitDropDownByVisibleText(String visibleText) throws InterruptedException {
				if(visibleText.equalsIgnoreCase("Minutes")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='0']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Hours")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='1']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Days")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='2']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Weeks")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='3']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Months")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='4']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Immediately")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='5']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("Years")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='6']\")).trigger('click');");
				} else {
					SysLogger.log("Error : Not able to select dropdown, given value '"+visibleText+"' may be incorrect");
				}
				Thread.sleep(3000);
				return new DateTrigger();
			}
			
			public DateTrigger selectTriggerDateFieldNameByVisibleText(String visibleText) throws InterruptedException {
				String javascript = "$($(\"#dk_container_sms_message_0_trigger_date_fieldName .dk_options a[data-dk-dropdown-value='"+visibleText+"']\")).trigger('click');";
				js.executeScript(javascript);
				Thread.sleep(3000);
				return new DateTrigger();
			}
			
			public DateTrigger selectMemberTimeFieldByVisibleText(String visibleText) throws InterruptedException {
				String javascript = "$($(\"#dk_container_sms_message_0_trigger_date_memberTimeField .dk_options a[data-dk-dropdown-value='"+visibleText+"']\")).trigger('click');";
				js.executeScript(javascript);
				Thread.sleep(3000);
				return new DateTrigger();
			}
			
			public DateTrigger selectTriggerDateCondition(String visibleText) throws InterruptedException {
				if(visibleText.equalsIgnoreCase("Before")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_date_when .dk_options a[data-dk-dropdown-value='0']\")).trigger('click');");
				} else if(visibleText.equalsIgnoreCase("After")) {
					js.executeScript("$($(\"#dk_container_sms_message_0_trigger_date_when .dk_options a[data-dk-dropdown-value='1']\")).trigger('click');");
				} else {
					SysLogger.log("Error : Not able to select dropdown, given value '"+visibleText+"' may be incorrect");
				}
				Thread.sleep(3000);
				return new DateTrigger();
			}
			
			public SmsMessageComposePage clickOnSubmitTriggerButton() throws InterruptedException {
				if(isAddTriggerButtonPresent()) {
					act.moveToElement(addTriggerButton).build().perform();
					addTriggerButton.click();
					Thread.sleep(3000);
				}
				else {
					SysLogger.log("Error : add trigger button not present");
				}
				return new SmsMessageComposePage(driver);
			}
			
		}
		
		public class OneTimeTrigger {

			@FindBy(xpath = "//div[@id='sms_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='4']/following-sibling::div//input[@id='onetime_immediate_radio']")
			private WebElement smsTriggerImmediatelyRadioButton;

			@FindBy(xpath = "//div[@id='sms_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='4']/following-sibling::div//input[@id='onetime_start_time_radio']")
			private WebElement smsTriggerStartTimeRadioButton;
			
			@FindBy(xpath = "//div[@id='sms_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='4']/following-sibling::div//input[contains(@name, 'oneTime.startDate')]")
			private WebElement smsDateInputBox;
			
			@FindBy(xpath = "//div[@id='sms_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='4']/following-sibling::div//input[contains(@name, 'oneTime.startTime')]")
			private WebElement smsTimeInputBox;
			
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
				if(this.smsTriggerImmediatelyRadioButton.isDisplayed()) {
					this.smsTriggerImmediatelyRadioButton.click();
				}
				return new OneTimeTrigger();
			}
			
			public OneTimeTrigger selectTriggerStartTimeRadioButton() {
				if(this.smsTriggerStartTimeRadioButton.isDisplayed()) {
					this.smsTriggerStartTimeRadioButton.click();
				}
				return new OneTimeTrigger();
			}
			
			public OneTimeTrigger enterDate(String date) {
				String[] dateObj = date.split("-");
				if(this.smsDateInputBox.isDisplayed()) {
					this.smsDateInputBox.click();
					this.selectMonth(dateObj[0]);
					this.selectYear(dateObj[2]);
					this.selectDate(dateObj[1]);
				}
				return new OneTimeTrigger();
			}
			
			public OneTimeTrigger enterTime(String time) {
				if(this.smsTimeInputBox.isDisplayed()) {
					this.smsTimeInputBox.click();
					this.smsTimeInputBox.clear();
					this.smsTimeInputBox.sendKeys(time);
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
			
			public SmsMessageComposePage clickOnSubmitTriggerButton() throws InterruptedException {
				if(isAddTriggerButtonPresent()) {
					act.moveToElement(addTriggerButton).build().perform();
					addTriggerButton.click();
					Thread.sleep(3000);
				}
				else {
					SysLogger.log("Error : add trigger button not present");
				}
				return new SmsMessageComposePage(driver);
			}			
		}

	}

}
