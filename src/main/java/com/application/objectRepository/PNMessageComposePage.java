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

public class PNMessageComposePage {

	WebDriver driver;
	ExtentTest testReporter;
	Actions act;
	JavascriptExecutor js;
	WebDriverWait wait;

	@FindBy(xpath = "//a[@href='/home' and contains(text(), 'mPulse Mobile')]")
	private WebElement homePageLink;

	@FindBy(xpath = "//div[@id='common_workarea']/div[@class='saving-bar']/div[@class='loadingmessage']/span")
	private WebElement loaderSavingBar;

	@FindBy(xpath = "//div[@id='common_workarea']/div[@class='progress-bar']/div[@class='loadingmessage']/span")
	private WebElement progressBarLoader;

	@FindBy(xpath = "//div[@id='pn-message-accordion']//form[@id='event_pn_compose_0']//textarea[contains(@name, 'message[0].text')]")
	private WebElement pnTextBox;

	@FindBy(xpath = "//div[@id='pn-message-accordion']//form[@id='event_pn_compose_0']//div/div[@id='add_trigger']")
	private WebElement pnAddTrigger;

	@FindBy(xpath = "//div[@id='pn-message-accordion']//form[@id='event_pn_compose_0']//span[@class='pn-check']//label[contains(text(), 'testAppIOS (ios)')]/preceding-sibling::input[@name='message[0].selectedApps'][1]")
	private WebElement selectAppCheckbox;

	@FindBy(xpath = "//div[@class='common_form_control campaign_details_control']/button/span[contains(text(), 'Next')]/ancestor::button[@id='message_submit']")
	private WebElement campaignNextButton;

	@FindBy(xpath = "//div[@class='common_form_control campaign_details_control']/button/span[contains(text(), 'Save')]/ancestor::button[@class='icon_button only_save shift_right']")
	private WebElement campaignSaveButton;

	@FindBy(xpath = "//div[@class='common_form_control campaign_details_control']/button/span[contains(text(), 'Next')]/ancestor::button[@id='message_submit']/following-sibling::a[@class='common_form_cancel']")
	private WebElement campaignBackButton;

	@FindBy(xpath = "//span[@class='ui-button-text' and text()='Ok']")
	private WebElement messageSchedulerAlertOkButton;

	public PNMessageComposePage(WebDriver driver) {
		this.driver = driver;
		act = new Actions(driver);
		js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
		wait = new WebDriverWait(driver, 120);
	}

	public boolean isCampaignSaveButtonPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.campaignSaveButton)).isDisplayed()) {
			return true;
		}
		return false;
	}

	public boolean isCampaignBackButtonPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.campaignBackButton)).isDisplayed()) {
			return true;
		}
		return false;
	}

	public boolean isCampaignNextButtonPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.campaignNextButton)).isDisplayed()) {
			return true;
		}
		return false;
	}

	public CampaignComposePage clickOnSaveButton() throws InterruptedException {
		if (this.isCampaignSaveButtonPresent()) {
			this.campaignSaveButton.click();
			Thread.sleep(12000); // campaign save is taking more time when two message
			DriverMethods.waitForElementUntillDisappear(this.driver, this.loaderSavingBar);
			DriverMethods.isElementPresent(this.driver, this.homePageLink);
		}
		return new CampaignComposePage(this.driver);
	}

	public CampaignPlanPage clickOnBackButton() {
		if (this.isCampaignBackButtonPresent()) {
			this.campaignBackButton.click();
			DriverMethods.isElementPresent(driver, homePageLink);
		}
		return new CampaignPlanPage(driver);
	}

	public boolean isPNTextBoxPresent() {
		DriverMethods.isElementPresent(this.driver, this.homePageLink);
		if (this.wait.until(ExpectedConditions.visibilityOf(this.pnTextBox)).isDisplayed()) {
			return true;
		}
		return false;
	}

	public boolean isPNAddTriggerPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.pnAddTrigger)).isDisplayed()) {
			return true;
		}
		return false;
	}

	public boolean isPNSelectAppCheckboxPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.selectAppCheckbox)).isDisplayed()) {
			return true;
		}
		return false;
	}

	public PNMessageComposePage enterPNText(String pnText) throws InterruptedException {
		Thread.sleep(12000);
		DriverMethods.waitForElementUntillDisappear(this.driver, this.progressBarLoader);
		DriverMethods.waitForElementUntillDisappear(this.driver, this.loaderSavingBar);
		DriverMethods.isElementPresent(this.driver, homePageLink);
		
		try {
			act.moveToElement(this.pnTextBox).build().perform();
			this.pnTextBox.click();
			this.pnTextBox.clear();
			this.pnTextBox.sendKeys(pnText);
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
			Thread.sleep(3000);
		}
		return new PNMessageComposePage(this.driver);
	}

	public PNMessageComposePage selectApp() throws InterruptedException {
		Thread.sleep(12000);
		DriverMethods.waitForElementUntillDisappear(this.driver, this.progressBarLoader);
		DriverMethods.waitForElementUntillDisappear(this.driver, this.loaderSavingBar);
		DriverMethods.isElementPresent(this.driver, homePageLink);
		
		if (this.isPNSelectAppCheckboxPresent()) {
			this.selectAppCheckbox.click();
			Thread.sleep(3000);
		} else {
			SysLogger.log("Error : PN select App checkbox not present");
		}
		return new PNMessageComposePage(this.driver);
	}

	public PNTriggers clickOnAddTriggerButton() throws InterruptedException {
		DriverMethods.waitForElementUntillDisappear(this.driver, this.loaderSavingBar);
		DriverMethods.isElementPresent(this.driver, this.homePageLink);
		if (this.isPNAddTriggerPresent()) {
			act.moveToElement(this.pnAddTrigger).build().perform();
			this.pnAddTrigger.click();
			DriverMethods.isElementPresent(driver, homePageLink);
			Thread.sleep(3000);
		} else {
			SysLogger.log("Error : PN Add Trigger Button not present");
		}
		return new PNTriggers();
	}

	public PNMessageComposePage clickOnOneTimeMessageSchedulerAlertOkButton() throws InterruptedException {
		if (this.messageSchedulerAlertOkButton.isDisplayed()) {
			act.moveToElement(this.messageSchedulerAlertOkButton).build().perform();
			this.messageSchedulerAlertOkButton.click();
			Thread.sleep(1000);
		} else {
			SysLogger.log("Error : message Scheduler Alert OK Button not present");
		}
		return new PNMessageComposePage(this.driver);
	}

	public class PNTriggers {

		@FindBy(xpath = "//div[@id='pn_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='4']")
		private WebElement pnOneTimeTrigger;

		@FindBy(xpath = "//div[@id='pn_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='3']")
		private WebElement pnRecurrenceTrigger;

		@FindBy(xpath = "//div[@id='pn_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='0']")
		private WebElement pnDateTrigger;

		@FindBy(xpath = "//div[@id='pn_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='2']")
		private WebElement pnMemberEngagementTrigger;

		@FindBy(xpath = "//div[@id='pn_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='1']")
		private WebElement pnMemberProfileTrigger;

		@FindBy(xpath = "//div[@id='pn_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='2']")
		private WebElement pnMemberEngagemantTrigger;

		@FindBy(xpath = "//div[@id='pn_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='5']")
		private WebElement pnGetMemberInfoTrigger;

		@FindBy(xpath = "//div[@id='pn_trigger_0']/following-sibling::div/div[@class='ui-dialog-buttonset']/button/span[contains(text(), 'Add Trigger')]/ancestor::button")
		private WebElement addTriggerButton;

		@FindBy(xpath = "//div[@id='pn_trigger_0']/following-sibling::div/div[@class='ui-dialog-buttonset']/button/span[contains(text(), 'Cancel')]/ancestor::button")
		private WebElement cancelTriggerButton;

		public PNTriggers() {
			PageFactory.initElements(driver, this);
			testReporter = ReporterFactory.getTest();
		}

		public boolean isCancelTriggerButtonPresent() {
			if (this.cancelTriggerButton.isDisplayed()) {
				return true;
			}
			return false;
		}

		public boolean isPNMemberProfileTriggerPresent() {
			if (this.pnMemberProfileTrigger.isDisplayed()) {
				return true;
			} else {
				return DriverMethods.isElementPresent(driver, this.pnMemberProfileTrigger);
			}
		}

		public boolean isPNMemberEngagemantTriggerPresent() {
			if (this.pnMemberEngagemantTrigger.isDisplayed()) {
				return true;
			} else {
				return DriverMethods.isElementPresent(driver, this.pnMemberEngagemantTrigger);
			}
		}

		public boolean isPNDateTriggerPresent() {
			if (this.pnDateTrigger.isDisplayed()) {
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
			if (this.pnOneTimeTrigger.isDisplayed()) {
				act.moveToElement(this.pnOneTimeTrigger).build().perform();
				this.pnOneTimeTrigger.click();
				Thread.sleep(3000);
			}
			return new OneTimeTrigger();
		}

		public ProfileTrigger clickOnMemberProfileTrigger() throws InterruptedException {
			if (this.isPNMemberProfileTriggerPresent()) {
				act.moveToElement(this.pnMemberProfileTrigger).build().perform();
				this.pnMemberProfileTrigger.click();
				Thread.sleep(5000);
			}
			return new ProfileTrigger();
		}

		public MemberEngagemantTrigger clickOnMemberEngagemantTrigger() throws InterruptedException {
			if (this.isPNMemberEngagemantTriggerPresent()) {
				act.moveToElement(this.pnMemberEngagemantTrigger).build().perform();
				this.pnMemberEngagemantTrigger.click();
				Thread.sleep(3000);
			}
			return new MemberEngagemantTrigger();
		}

		public DateTrigger clickOnDateTrigger() throws InterruptedException {
			if (this.isPNDateTriggerPresent()) {
				act.moveToElement(this.pnDateTrigger).build().perform();
				this.pnDateTrigger.click();
				Thread.sleep(3000);
			}
			return new DateTrigger();
		}

		public class ProfileTrigger {

			@FindBy(xpath = "//div[@id='pn_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='1']/following-sibling::div//div[@id='profile_trigger_div']//div/input[@class='textbox sm-textbox inlineComponent']")
			private WebElement pnProfileTriggerTimeDelayInlineInputTextBox;

			@FindBy(xpath = "//div[@id='pn_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='1']/following-sibling::div//div[@id='profile_trigger_div']//select[@id='pn_message_0_trigger_profile_fieldName']")
			private WebElement pnProfileTriggerFieldNameDropDown;

			@FindBy(xpath = "//div[@id='pn_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='1']/following-sibling::div//div[@id='profile_trigger_div']//select[@id='pn_message_0_trigger_profile_profileChangeType']")
			private WebElement pnProfileTriggerProfileChangeDropDown;

			@FindBy(xpath = "//div[@id='pn_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='1']/following-sibling::div//div[@id='profile_trigger_div']//div/input[@id='msg_field_value_pn_0']")
			private WebElement pnProfiletriggerFieldValueTextBox;

			public ProfileTrigger() {
				PageFactory.initElements(driver, this);
				testReporter = ReporterFactory.getTest();
			}

			public boolean isPNProfileTriggerTimeDelayInlineInputTextBoxPresent() {
				if (this.pnProfileTriggerTimeDelayInlineInputTextBox.isDisplayed()) {
					return true;
				}
				return false;
			}

			public boolean isPNProfileTriggerFieldNameDropDownPresent() {
				if (this.pnProfileTriggerFieldNameDropDown.isDisplayed()) {
					return true;
				}
				return false;
			}

			public boolean isPNProfileTriggerProfileChangeDropDownPresent() {
				if (this.pnProfileTriggerProfileChangeDropDown.isDisplayed()) {
					return true;
				}
				return false;
			}

			public boolean isPNProfiletriggerFieldValueTextBoxPresent() {
				if (this.pnProfiletriggerFieldValueTextBox.isDisplayed()) {
					return true;
				}
				return false;
			}

			public PNMessageComposePage clickOnSubmitTriggerButton() throws InterruptedException {
				if (isAddTriggerButtonPresent()) {
					Thread.sleep(4000);
					act.moveToElement(addTriggerButton).build().perform();
					addTriggerButton.click();
					Thread.sleep(3000);
				} else {
					SysLogger.log("Error : add trigger button not present");
				}
				return new PNMessageComposePage(driver);
			}

			public ProfileTrigger selectTimeUnitDropDownByVisibleText(String visibleText) throws InterruptedException {
				if (visibleText.equalsIgnoreCase("Minutes")) {
					js.executeScript(
							"$($(\"#dk_container_pn_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='0']\")).trigger('click');");
				} else if (visibleText.equalsIgnoreCase("Hours")) {
					js.executeScript(
							"$($(\"#dk_container_pn_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='1']\")).trigger('click');");
				} else if (visibleText.equalsIgnoreCase("Days")) {
					js.executeScript(
							"$($(\"#dk_container_pn_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='2']\")).trigger('click');");
				} else if (visibleText.equalsIgnoreCase("Weeks")) {
					js.executeScript(
							"$($(\"#dk_container_pn_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='3']\")).trigger('click');");
				} else if (visibleText.equalsIgnoreCase("Months")) {
					js.executeScript(
							"$($(\"#dk_container_pn_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='4']\")).trigger('click');");
				} else if (visibleText.equalsIgnoreCase("Immediately")) {
					js.executeScript(
							"$($(\"#dk_container_pn_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='5']\")).trigger('click');");
				} else if (visibleText.equalsIgnoreCase("Years")) {
					js.executeScript(
							"$($(\"#dk_container_pn_message_0_trigger_profile_timeUnits .dk_options a[data-dk-dropdown-value='6']\")).trigger('click');");
				} else {
					SysLogger.log(
							"Error : Not able to select dropdown, given value '" + visibleText + "' may be incorrect");
				}
				Thread.sleep(3000);
				return new ProfileTrigger();
			}

			public ProfileTrigger selectFieldNameDropDownByValue(String value) throws InterruptedException {
				String javascript = "$($(\"#dk_container_pn_message_0_trigger_profile_fieldName .dk_options a[data-dk-dropdown-value='"
						+ value + "']\")).trigger('click');";
				js.executeScript(javascript);
				Thread.sleep(3000);
				return new ProfileTrigger();
			}

			public ProfileTrigger selectProfileChangeDropDownByValue(String value) throws InterruptedException {
				if (value.equalsIgnoreCase("Any Value")) {
					js.executeScript(
							"$($(\"#dk_container_pn_message_0_trigger_profile_profileChangeType .dk_options a[data-dk-dropdown-value='0']\")).trigger('click');");
				} else if (value.equalsIgnoreCase("Specific Value")) {
					js.executeScript(
							"$($(\"#dk_container_pn_message_0_trigger_profile_profileChangeType .dk_options a[data-dk-dropdown-value='1']\")).trigger('click');");
				} else if (value.equalsIgnoreCase("New Value Only")) {
					js.executeScript(
							"$($(\"#dk_container_pn_message_0_trigger_profile_profileChangeType .dk_options a[data-dk-dropdown-value='2']\")).trigger('click');");
				} else if (value.equalsIgnoreCase("Any Specific Value")) {
					js.executeScript(
							"$($(\"#dk_container_pn_message_0_trigger_profile_profileChangeType .dk_options a[data-dk-dropdown-value='3']\")).trigger('click');");
				} else {
					SysLogger.log(
							"Error : Not able to select dropdown, given value '" + value + "' may be incorrect");
				}
				Thread.sleep(2000);
				return new ProfileTrigger();
			}

		}

		public class MemberEngagemantTrigger {

			@FindBy(xpath = "//div[@id='pn_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='2']/following::div[@id='msg_eventTrigger_external_event']")
			private WebElement pnCustomEventButton;

			@FindBy(xpath = "//div[@id='pn_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='2']/following::div[@id='external_event_div']//input[@id='trigger_event_attributevalue_pn_0']")
			private WebElement pnCustomEventAttributeValueInputBox;

			public MemberEngagemantTrigger() {
				PageFactory.initElements(driver, this);
				testReporter = ReporterFactory.getTest();
			}

			public boolean ispnCustomEventButtonPresent() {
				if (this.pnCustomEventButton.isDisplayed()) {
					return true;
				}
				return false;
			}

			public boolean ispnCustomEventAttributeValueInputBoxPresent() {
				if (this.pnCustomEventAttributeValueInputBox.isDisplayed()) {
					return true;
				}
				return false;
			}

			public PNMessageComposePage clickOnSubmitTriggerButton() throws InterruptedException {
				if (isAddTriggerButtonPresent()) {
					Thread.sleep(4000);
					act.moveToElement(addTriggerButton).build().perform();
					addTriggerButton.click();
					Thread.sleep(3000);
				} else {
					SysLogger.log("Error : add trigger button not present");
				}
				return new PNMessageComposePage(driver);
			}

			public MemberEngagemantTrigger clickOnCustomEventButton() throws InterruptedException {
				if (this.ispnCustomEventButtonPresent()) {
					act.moveToElement(this.pnCustomEventButton).build().perform();
					this.pnCustomEventButton.click();
					Thread.sleep(3000);
				} else {
					SysLogger.log("Error : custom event button not present");
				}
				return new MemberEngagemantTrigger();
			}

			public MemberEngagemantTrigger enterCustomEventAttributeValue(String value) throws InterruptedException {
				if (this.ispnCustomEventAttributeValueInputBoxPresent()) {
					act.moveToElement(this.pnCustomEventAttributeValueInputBox).build().perform();
					this.pnCustomEventAttributeValueInputBox.sendKeys(value);
					Thread.sleep(2000);
				} else {
					SysLogger.log("Error : custom event attribute value inputbox not present");
				}
				return new MemberEngagemantTrigger();
			}

			public MemberEngagemantTrigger selectCustomEventName(String eventName) throws InterruptedException {
				String javascript = "$($(\"#dk_container_pn_message_0_trigger_event_eventId .dk_options a:contains('"
						+ eventName + "')\")).trigger('click');";
				js.executeScript(javascript);
				Thread.sleep(2000);
				return new MemberEngagemantTrigger();
			}

			public MemberEngagemantTrigger selectCustomEventAttributeName(String value) throws InterruptedException {
				String javascript = "$($(\"#dk_container_pn_message_0_trigger_event_eventAttributeId .dk_options a:contains('"
						+ value + "')\")).trigger('click');";
				Thread.sleep(5000); // adding more wait to proper load all attribute name before selecting
				js.executeScript(javascript);
				Thread.sleep(2000);
				return new MemberEngagemantTrigger();
			}

		}

		public class DateTrigger {

			@FindBy(xpath = "//div[@id='pn_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='0']/following-sibling::div//div[@id='date_trigger_div']//input[@class='textbox sm-textbox inlineComponent']")
			private WebElement pnDateTriggerTimeTextBox;

			@FindBy(xpath = "//div[@id='pn_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='0']/following-sibling::div//div[@id='date_trigger_div']/div/input[@class='textbox sm-textbox inlineComponent']")
			private WebElement pnDateTriggerTimePickerTextBox;

			public DateTrigger() {
				PageFactory.initElements(driver, this);
				testReporter = ReporterFactory.getTest();
			}

			public boolean ispnDateTriggerTimeTextBoxPresent() {
				if (this.pnDateTriggerTimeTextBox.isDisplayed()) {
					return true;
				}
				return false;
			}

			public DateTrigger enterDelyAmountOfTime(String number) {
				if (this.ispnDateTriggerTimeTextBoxPresent()) {
					this.pnDateTriggerTimePickerTextBox.sendKeys(number);
				}
				return new DateTrigger();
			}

			public DateTrigger selectTimeUnitDropDownByVisibleText(String visibleText) throws InterruptedException {
				if (visibleText.equalsIgnoreCase("Minutes")) {
					js.executeScript(
							"$($(\"#dk_container_pn_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='0']\")).trigger('click');");
				} else if (visibleText.equalsIgnoreCase("Hours")) {
					js.executeScript(
							"$($(\"#dk_container_pn_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='1']\")).trigger('click');");
				} else if (visibleText.equalsIgnoreCase("Days")) {
					js.executeScript(
							"$($(\"#dk_container_pn_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='2']\")).trigger('click');");
				} else if (visibleText.equalsIgnoreCase("Weeks")) {
					js.executeScript(
							"$($(\"#dk_container_pn_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='3']\")).trigger('click');");
				} else if (visibleText.equalsIgnoreCase("Months")) {
					js.executeScript(
							"$($(\"#dk_container_pn_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='4']\")).trigger('click');");
				} else if (visibleText.equalsIgnoreCase("Immediately")) {
					js.executeScript(
							"$($(\"#dk_container_pn_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='5']\")).trigger('click');");
				} else if (visibleText.equalsIgnoreCase("Years")) {
					js.executeScript(
							"$($(\"#dk_container_pn_message_0_trigger_date_timeUnits .dk_options a[data-dk-dropdown-value='6']\")).trigger('click');");
				} else {
					SysLogger.log(
							"Error : Not able to select dropdown, given value '" + visibleText + "' may be incorrect");
				}
				Thread.sleep(3000);
				return new DateTrigger();
			}

			public DateTrigger selectTriggerDateFieldNameByVisibleText(String visibleText) throws InterruptedException {
				String javascript = "$($(\"#dk_container_pn_message_0_trigger_date_fieldName .dk_options a[data-dk-dropdown-value='"
						+ visibleText + "']\")).trigger('click');";
				js.executeScript(javascript);
				Thread.sleep(3000);
				return new DateTrigger();
			}

			public DateTrigger selectMemberTimeFieldByVisibleText(String visibleText) throws InterruptedException {
				String javascript = "$($(\"#dk_container_pn_message_0_trigger_date_memberTimeField .dk_options a[data-dk-dropdown-value='"
						+ visibleText + "']\")).trigger('click');";
				js.executeScript(javascript);
				Thread.sleep(3000);
				return new DateTrigger();
			}

			public DateTrigger selectTriggerDateCondition(String visibleText) throws InterruptedException {
				if (visibleText.equalsIgnoreCase("Before")) {
					js.executeScript(
							"$($(\"#dk_container_pn_message_0_trigger_date_when .dk_options a[data-dk-dropdown-value='0']\")).trigger('click');");
				} else if (visibleText.equalsIgnoreCase("After")) {
					js.executeScript(
							"$($(\"#dk_container_pn_message_0_trigger_date_when .dk_options a[data-dk-dropdown-value='1']\")).trigger('click');");
				} else {
					SysLogger.log(
							"Error : Not able to select dropdown, given value '" + visibleText + "' may be incorrect");
				}
				Thread.sleep(3000);
				return new DateTrigger();
			}

			public PNMessageComposePage clickOnSubmitTriggerButton() throws InterruptedException {
				if (isAddTriggerButtonPresent()) {
					act.moveToElement(addTriggerButton).build().perform();
					addTriggerButton.click();
					Thread.sleep(3000);
				} else {
					SysLogger.log("Error : add trigger button not present");
				}
				return new PNMessageComposePage(driver);
			}

		}

		public class OneTimeTrigger {

			@FindBy(xpath = "//div[@id='pn_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='4']/following-sibling::div//input[@id='onetime_immediate_radio']")
			private WebElement pnTriggerImmediatelyRadioButton;

			@FindBy(xpath = "//div[@id='pn_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='4']/following-sibling::div//input[@id='onetime_start_time_radio']")
			private WebElement pnTriggerStartTimeRadioButton;

			@FindBy(xpath = "//div[@id='pn_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='4']/following-sibling::div//input[contains(@name, 'oneTime.startDate')]")
			private WebElement pnDateInputBox;

			@FindBy(xpath = "//div[@id='pn_trigger_0' and contains(@style, 'block')]//fieldset/div/h3[@triggertype='4']/following-sibling::div//input[contains(@name, 'oneTime.startTime')]")
			private WebElement pnTimeInputBox;

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
				if (this.pnTriggerImmediatelyRadioButton.isDisplayed()) {
					this.pnTriggerImmediatelyRadioButton.click();
				}
				return new OneTimeTrigger();
			}

			public OneTimeTrigger selectTriggerStartTimeRadioButton() {
				if (this.pnTriggerStartTimeRadioButton.isDisplayed()) {
					this.pnTriggerStartTimeRadioButton.click();
				}
				return new OneTimeTrigger();
			}

			public OneTimeTrigger enterDate(String date) {
				String[] dateObj = date.split("-");
				if (this.pnDateInputBox.isDisplayed()) {
					this.pnDateInputBox.click();
					this.selectMonth(dateObj[0]);
					this.selectYear(dateObj[2]);
					this.selectDate(dateObj[1]);
				}
				return new OneTimeTrigger();
			}

			public OneTimeTrigger enterTime(String time) {
				if (this.pnTimeInputBox.isDisplayed()) {
					this.pnTimeInputBox.click();
					this.pnTimeInputBox.clear();
					this.pnTimeInputBox.sendKeys(time);
				}
				return new OneTimeTrigger();
			}

			private void selectMonth(String month) {
				if (this.monthPicker.isDisplayed()) {
					Select sc = new Select(this.monthPicker);
					sc.selectByVisibleText(month);
				} else {
					SysLogger.log("Error : Not able to select Month name");
				}
			}

			private void selectYear(String year) {
				if (this.yearPicker.isDisplayed()) {
					Select sc = new Select(this.yearPicker);
					sc.selectByVisibleText(year);
				} else {
					SysLogger.log("Error : Not able to select Year name");
				}
			}

			private void selectDate(String date) {
				String dateXpath = "//table[@class='ui-datepicker-calendar']/tbody/tr/td/a[text()='" + date + "']";
				WebElement element = driver.findElement(By.xpath(dateXpath));
				element.click();
			}

			public PNMessageComposePage clickOnSubmitTriggerButton() throws InterruptedException {
				if (isAddTriggerButtonPresent()) {
					act.moveToElement(addTriggerButton).build().perform();
					addTriggerButton.click();
					Thread.sleep(3000);
				} else {
					SysLogger.log("Error : add trigger button not present");
				}
				return new PNMessageComposePage(driver);
			}
		}

	}

}
