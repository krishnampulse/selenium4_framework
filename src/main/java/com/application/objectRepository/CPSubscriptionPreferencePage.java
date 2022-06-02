package com.application.objectRepository;

import org.openqa.selenium.By;

import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CPSubscriptionPreferencePage {
	
	WebDriver driver;
	ExtentTest testReporter;
	WebDriverWait wait;
	Actions act;
	JavascriptExecutor jsEx;
	
	@FindBy(xpath ="//header[@class='common_form_header']/child::h1[ contains(text(), 'Subscription preferences Page Editor')]")
	private WebElement subPrefPageHeadingText;
	
	@FindBy(xpath="//img[@alt='logo' and contains(text(), 'data-dz-thumbnail')]")
	private WebElement logoImage;
	
	@FindBy(id="myForm")
	private WebElement addImageLink;
	
	@FindBy(xpath = "//input[@type='file' and @class='dz-hidden-input' and @accept='image/jpeg,image/png,image/jpg']")
	private WebElement subPrefCustomLogoImageInput;
	
	@FindBy(xpath ="//a[@class='dz-remove' and contains(text(), 'Remove file')]")
	private WebElement removeImageLink;
	
	@FindBy(id="sortable2")
	private WebElement memberFieldsForm;
	
	@FindBy(id ="listInputSortable")
	private WebElement listsForm; 
		
	@FindBy(id="introHeader")
	private WebElement introHeaderTextBox;
	
	@FindBy(id="introMessage")
	private WebElement introMsgTextBox;
	
	@FindBy(id="channelHeader")
	private WebElement channelHeaderTextBox;
	
	@FindBy(id="channelMessage")
	private WebElement channelMsgTextBox;
	
	@FindBy(id="channelFooter")
	private WebElement channelFooterTextBox;
	
	@FindBy(id="informationHeader")
	private WebElement customFieldHeaderTextBox;
	
	@FindBy(id="informationMessage")
	private WebElement customFieldMsgTextBox;
	
	@FindBy(id="sortable4")
	private WebElement picklistCustomFieldForm;
	
	@FindBy(id="unsubscribeMessage")
	private WebElement unsubMsgTextBox;
	
	@FindBy(xpath="//ul[@id='footer_links_ul']//child::input[@class='textbox url-Label']")
	private WebElement footerLinkLabelTextBox;

	@FindBy(xpath="//ul[@id='footer_links_ul']//child::input[@class='textbox url-URL']")
	private WebElement footerLinkURLTextBox;
	
	@FindBy(xpath ="//ul[@id='footer_links_ul' and @class='common_form_group_body']//descendant::a[@id=6 and @title='Delete' and @class='delete_event delete_footer_link']")
	private WebElement footerDeleteLinkButton;
	
	@FindBy(id="add_footer_link")
	private WebElement addLinkButton;
	
	@FindBy(id="submit")
	private WebElement saveButton;
	
	@FindBy(xpath ="//a[@class='common_form_cancel' and @href='/account/sub-preferences' and contains(text(), 'Cancel')]")
	private WebElement cancelButton;
	
	@FindBy(id="thanksHeader")
	private WebElement thankYouHeaderTextBox;
	
	@FindBy(id="thanksMessage")
	private WebElement thankYouMsgTextBox;		
	
	@FindBy(id="expiredLinkHeader")
	private WebElement expiredLinkHeaderTextBox;
	
	@FindBy(id="expiredLinkMessage")
	private WebElement expiredLinkMsgTextBox;
	
	public CPSubscriptionPreferencePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
		wait = new WebDriverWait(driver, 120);
		act = new Actions(driver);
		jsEx = (JavascriptExecutor) driver;
		}

	public boolean isLogoImageDisplayed() {
		if(this.wait.until(ExpectedConditions.visibilityOf(this.logoImage)).isDisplayed()) {
			return true;
		}
		return false;		
	}

	public boolean isAddImageLinkDisplayed() {
		if(this.wait.until(ExpectedConditions.visibilityOf(this.addImageLink)).isDisplayed()) {
			return true;
		}
		return false;		
	}
	
	public boolean isRemoveImageLinkDisplayed() {
		try {
			if (this.removeImageLink.isDisplayed()) {
				return true;
			}
		} catch (NoSuchElementException e) {
		}
		return false;
	}
	
	public boolean isCustomLogoDisplayed(String imageName) {
		try {
			WebElement customLogo = this.driver.findElement(
					By.xpath("//div[@class='dz-image']/img[@alt='logo' and contains(@src, '" + imageName + "')]"));
			if (customLogo.isDisplayed()) {
				return true;
			}
		} catch (NoSuchElementException e) {
		}
		return false;
	}
	
	public CPSubscriptionPreferencePage clickOnRemoveImageLink() {
		if(this.isRemoveImageLinkDisplayed()) {
			this.removeImageLink.click();
		}
		else {
			SysLogger.log("Error : Remove Image Link is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Remove Image Link  is not displayed");
		}
		return new CPSubscriptionPreferencePage(this.driver);		
	} 
	
	public CPSubscriptionPreferencePage uploadLogoImage(String imageName) throws InterruptedException {
		if(isRemoveImageLinkDisplayed())
			this.removeImageLink.click();
		if(isAddImageLinkDisplayed() && this.subPrefCustomLogoImageInput.isEnabled()) {
			String imagePath = System.getProperty("user.dir") + "/src/test/resources/"+ imageName;
			this.act.moveToElement(this.addImageLink).build().perform();
			this.subPrefCustomLogoImageInput.sendKeys(imagePath);
			Thread.sleep(10000); // waiting for image to upload
		}
		else {
			SysLogger.log("Error : Add image Link is not displayed or custom logo image input field is not enabled");
			testReporter.log(LogStatus.INFO, "Error : Add image Link is not displayed or custom logo image input field is not enabled");
		}
		return new CPSubscriptionPreferencePage(this.driver);		
	}
	
	public CPSubscriptionPreferencePage verifyCustomLogoImageIsPresent(String imageName) throws InterruptedException {
		if(this.isCustomLogoDisplayed(imageName)) {
			return this;
		}
		else {
			SysLogger.log("Error : custom logo image with Image name '"+imageName+"' is not displayed");
			testReporter.log(LogStatus.INFO, "Error : custom logo image with Image name '"+imageName+"' is not displayed");
			Assert.assertTrue(false, "Error : custom logo image with Image name '"+imageName+"' is not displayed");
		}
		return new CPSubscriptionPreferencePage(this.driver);		
	}
	
	public boolean isEditorHeadingTextDisplayed() {
		if(this.wait.until(ExpectedConditions.visibilityOf(this.subPrefPageHeadingText)).isDisplayed()) {
			return true;
		}
		return false;		
	}
	
	public boolean isRemoveMemberFieldIconDisplayed(String memberFieldId) {
		try {
			WebElement removeAddressFieldIcon = driver.findElement(By.xpath("//ul[@id='sortable2']/child::li[@data-id='"+memberFieldId+"']/child::img"));
			if(removeAddressFieldIcon.isDisplayed()) {
				return true;
				}
			}
			catch(NoSuchElementException e) {	
				SysLogger.log("Error : Remove member field icon is not displayed");
				testReporter.log(LogStatus.INFO, "Error : Remove member field icon is not displayed");
			}
			return false;
		}
	
	public boolean isRemovePicklistCustomFieldIconDisplayed(String picklistTrueFalseId) {
		try {
			WebElement removePicklistCustomFieldIcon = driver.findElement(By.xpath("//ul[@id='sortable4']/child::li[@data-id='"+picklistTrueFalseId+"']/child::img"));
			if(removePicklistCustomFieldIcon.isDisplayed()) {
				return true;
				}
			}
			catch(NoSuchElementException e) {	
				SysLogger.log("Error : Remove picklist field icon is not displayed");
				testReporter.log(LogStatus.INFO, "Error : Remove picklist field icon is not displayed");
			}
			return false;
		}
	
	public boolean isRemoveListIconDisplayed(String listId) {
		try {
			WebElement removeListIcon = driver.findElement(By.xpath("//ul[@id='listInputSortable']/child::li[@data-id='"+listId+"']/child::img"));
			if(removeListIcon.isDisplayed()) {
			return true;
			}
		}
		catch(NoSuchElementException e) {	
		}
		return false;
	}
	
	public CPSubscriptionPreferencePage clickOnAddLinkButton() {
		if(this.addLinkButton.isDisplayed()) {
			this.addLinkButton.click();
		}
		else {
			SysLogger.log("Error : Add Link Button is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Add Link Button is not displayed");
		}	
		return new CPSubscriptionPreferencePage(driver);
	}
	
	public CPSubscriptionPreferencePage clickOnCancelButton() {
		if(this.cancelButton.isDisplayed()) {
			this.cancelButton.click();
		}
		else {
			SysLogger.log("Error : Cancel Button is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Cancel Button is not displayed");
		}
		return new CPSubscriptionPreferencePage(this.driver);		
	}
	
	public String getChannelFooterText() {
		if(this.channelFooterTextBox.isDisplayed()) {
			return channelFooterTextBox.getAttribute("value");
		}
		else {
			SysLogger.log("Error : Channel Footer TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Channel Footer TextBox is not displayed");
			return null;
		}		
	}
	
	public CPSubscriptionPreferencePage enterChannelFooterText(String text) {
		if(this.channelFooterTextBox.isDisplayed()) {
			this.channelFooterTextBox.click();
			this.channelFooterTextBox.clear();
			this.channelFooterTextBox.sendKeys(text);
		}
		else {
			SysLogger.log("Error : Channel Footer TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Channel Footer TextBox is not displayed");
		}		
		return new CPSubscriptionPreferencePage(driver);
	}
	
	public String getChannelHeaderText() {
		if(this.channelHeaderTextBox.isDisplayed()) {
			return this.channelHeaderTextBox.getAttribute("value");
		}
		else {
			SysLogger.log("Error : Channel Header TextBox is not displayed");	
			testReporter.log(LogStatus.INFO, "Error : Channel Header TextBox is not displayed");
		}
		return null;
	}
	
	public CPSubscriptionPreferencePage enterChannelHeaderText(String text) {
		if(this.channelHeaderTextBox.isDisplayed()) { 
			this.channelHeaderTextBox.click();
			this.channelHeaderTextBox.clear();
			this.channelHeaderTextBox.sendKeys(text);
		}
		else {
			SysLogger.log("Error : Channel Header TextBox is not displayed");	
			testReporter.log(LogStatus.INFO, "Error : Channel Header TextBox is not displayed");
		}
		return new CPSubscriptionPreferencePage(driver);
	}
	
	public String getChannelMsgText() {
		if(this.channelMsgTextBox.isDisplayed()) { 
			return this.channelMsgTextBox.getAttribute("value");
		}
		else {
			SysLogger.log("Error : Channel Message TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Channel Message TextBox is not displayed");
		}
		return null;		
	}
	
	public CPSubscriptionPreferencePage enterChannelMsgText(String text) {
		if(this.channelMsgTextBox.isDisplayed()) {
			this.channelMsgTextBox.click();
			this.channelMsgTextBox.clear();
			this.channelMsgTextBox.sendKeys(text);
		}
		else {
			SysLogger.log("Error : Channel Message TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Channel Message TextBox is not displayed");
		}
		return new CPSubscriptionPreferencePage(driver);
	}
	
	public String getCustomFieldHeaderText() {
		if(this.customFieldHeaderTextBox.isDisplayed()) {
			return this.customFieldHeaderTextBox.getAttribute("value");
		}
		else {
			SysLogger.log("Error : Custom Field Header TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Custom Field Header TextBox is not displayed");
			return null;
		}
	}
	
	public CPSubscriptionPreferencePage enterCustomFieldHeaderText(String text) {
		if(this.customFieldHeaderTextBox.isDisplayed()) {
			this.customFieldHeaderTextBox.click();
			this.customFieldHeaderTextBox.clear();
			this.customFieldHeaderTextBox.sendKeys(text);
		}
		else {
			SysLogger.log("Error : Custom Field Header TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Custom Field Header TextBox is not displayed");
		}
		return new CPSubscriptionPreferencePage(driver);
	}
		
	public String getCustomFieldMsgText() {
		if(this.customFieldMsgTextBox.isDisplayed()) {
			return this.customFieldMsgTextBox.getAttribute("value");
		}
		else {
			SysLogger.log("Error : Custom Field Message TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Custom Field Message TextBox is not displayed");
			return null;
		}
	}
	
	public CPSubscriptionPreferencePage enterCustomFieldMsgText(String text) {
		if(this.customFieldMsgTextBox.isDisplayed()) {
			this.customFieldMsgTextBox.click();
			this.customFieldMsgTextBox.clear();
			this.customFieldMsgTextBox.sendKeys(text);
		}
		else {
			SysLogger.log("Error : Custom Field Message TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Custom Field Message TextBox is not displayed");
		}
		return new CPSubscriptionPreferencePage(driver);
	}
	
	public String getExpiredLinkHeaderText() {
		if(this.expiredLinkHeaderTextBox.isDisplayed()) {
			return this.expiredLinkHeaderTextBox.getAttribute("value");
		}
		else {
			SysLogger.log("Error : Expired Link Header TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Expired Link Header TextBox is not displayed");
			return null;
		}
	}
	
	public CPSubscriptionPreferencePage enterExpiredLinkHeaderText(String text) {
		if(this.expiredLinkHeaderTextBox.isDisplayed()) {
			this.expiredLinkHeaderTextBox.click();
			this.expiredLinkHeaderTextBox.clear();
			this.expiredLinkHeaderTextBox.sendKeys(text);
		}
		else {
			SysLogger.log("Error : Expired Link Header TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Expired Link Header TextBox is not displayed");
		}
		return new CPSubscriptionPreferencePage(driver);
	}
		
	public String getExpiredLinkMsgText() {
		if(this.expiredLinkMsgTextBox.isDisplayed()) {
			return this.expiredLinkMsgTextBox.getAttribute("value");
		}
		else {
			SysLogger.log("Error : Expired Link Message TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Expired Link Message TextBox is not displayed");
			return null;
		}
	}
	
	public CPSubscriptionPreferencePage enterExpiredLinkMsgText(String text) {
		if(this.expiredLinkMsgTextBox.isDisplayed()) {
			this.expiredLinkMsgTextBox.click();
			this.expiredLinkMsgTextBox.clear();
			this.expiredLinkMsgTextBox.sendKeys(text);
		}
		else {
			SysLogger.log("Error : Expired Link Message TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Expired Link Message TextBox is not displayed");
		}
		return new CPSubscriptionPreferencePage(driver);
	}
	
	public CPSubscriptionPreferencePage enterFooterLinkLabelText(String text) {
		if(this.footerLinkLabelTextBox.isDisplayed()) {
			this.footerLinkLabelTextBox.click();
			this.footerLinkLabelTextBox.clear();
			this.footerLinkLabelTextBox.sendKeys(text);
		}
		else {
			SysLogger.log("Error : Footer Link Label TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Footer Link Label TextBox is not displayed");
		}
		return new CPSubscriptionPreferencePage(driver);
	}
	
	public String getFooterLinkLabelText() {
		if(this.footerLinkLabelTextBox.isDisplayed()) {
			return this.footerLinkLabelTextBox.getAttribute("value");
		}
		else {
			SysLogger.log("Error : Footer Link Label TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Footer Link Label TextBox is not displayed");
			return null;
		}
	}
	
	public CPSubscriptionPreferencePage enterFooterLinkURLText(String text) {
		if(this.footerLinkURLTextBox.isDisplayed()) {
			this.footerLinkURLTextBox.click();
			this.footerLinkURLTextBox.clear();
			this.footerLinkURLTextBox.sendKeys(text);
		}
		else {
			SysLogger.log("Error : Footer Link URL TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Footer Link URL TextBox is not displayed");
		}
		return new CPSubscriptionPreferencePage(driver);
	}
	
	public String getFooterLinkURLText() {
		if(this.footerLinkURLTextBox.isDisplayed()) {
			return this.footerLinkURLTextBox.getAttribute("value");
		}
		else {
			SysLogger.log("Error : Footer Link URL TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Footer Link URL TextBox is not displayed");
			return null;
		}
	}
	
	public String getIntroHeaderText() {
		if(this.introHeaderTextBox.isDisplayed()) {
			return this.introHeaderTextBox.getAttribute("value");
		}
		else {
			SysLogger.log("Error : Intro Header TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Intro Header TextBox is not displayed");
			return null;
		}
	}
	
	public CPSubscriptionPreferencePage enterIntroHeaderText(String text) {
		if(this.introHeaderTextBox.isDisplayed()) {
			this.introHeaderTextBox.click();
			this.introHeaderTextBox.clear();
			this.introHeaderTextBox.sendKeys(text);
		}
		else {
			SysLogger.log("Error : Intro Header TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Intro Header TextBox is not displayed");
		}
		return new CPSubscriptionPreferencePage(driver);
	}
		
	public String getIntroMsgText() {
		if(this.introMsgTextBox.isDisplayed()) {
			return this.introMsgTextBox.getAttribute("value");
		}
		else {
			SysLogger.log("Error : Intro Message TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Intro Message TextBox is not displayed");
			return null;
		}
	}
	
	public CPSubscriptionPreferencePage enterIntroMsgText(String text) {
		if(this.introMsgTextBox.isDisplayed()) {
			this.introMsgTextBox.click();
			this.introMsgTextBox.clear();
			this.introMsgTextBox.sendKeys(text);
		}
		else {
			SysLogger.log("Error : Intro Message TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Intro Message TextBox is not displayed");
		}
		return new CPSubscriptionPreferencePage(driver);
	}
	
	public CPSubscriptionPreferencePage clickOnRemovePicklistCustomFieldIcon(String picklistTrueFalseId) {
		if(this.isRemovePicklistCustomFieldIconDisplayed(picklistTrueFalseId)) {
			WebElement removePicklistCustomFieldIcon = driver.findElement(By.xpath("//ul[@id='sortable4']/child::li[@data-id='"+picklistTrueFalseId+"']/child::img"));
			removePicklistCustomFieldIcon.click();
		}
		else {
			SysLogger.log("Error : Remove Custom Field Icon is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Remove Custom Field Icon is not displayed");
		}
		return new CPSubscriptionPreferencePage(driver);
	}
	
	public CPSubscriptionPreferencePage clickOnRemoveMemberField(String memberFieldId) {
		if(this.isRemoveMemberFieldIconDisplayed(memberFieldId)) {
			WebElement removeMemberFieldIcon = driver.findElement(By.xpath("//ul[@id='sortable2']/child::li[@data-id='"+memberFieldId+"']/child::img"));
			removeMemberFieldIcon.click();	
		}
		else {
			SysLogger.log("Error : Remove Member Field Icon is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Remove Member Field Icon is not displayed");
		}
		return new CPSubscriptionPreferencePage(driver);
	}
	
	public CPSubscriptionPreferencePage clickOnRemoveListIcon(String listId) {
		if(this.isRemoveListIconDisplayed(listId)) {
			WebElement removeListIcon = driver.findElement(By.xpath("//ul[@id='listInputSortable']/child::li[@data-id='"+listId+"']/child::img"));
			removeListIcon.click();	
		}
		else {
			SysLogger.log("Error : Remove List Icon is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Remove List Icon is not displayed");
		}
		return new CPSubscriptionPreferencePage(driver);
	}
		
	public CPSubscriptionPreferencePage dragAndDropMemberFieldToForm(String memberFieldId) {
		if(this.memberFieldsForm.isDisplayed()) {
			if(this.isRemoveMemberFieldIconDisplayed(memberFieldId)) {
				SysLogger.log("INFO : Member Field is already added in form");
				testReporter.log(LogStatus.INFO, "INFO : Member Field is already added in form");
				return new CPSubscriptionPreferencePage(driver); 
			}
			WebElement memberFieldInTable = driver.findElement(By.xpath("//ul[@id='sortable1']/child::li[@data-id='"+memberFieldId+"']"));
			this.act.moveToElement(memberFieldInTable).build().perform();;
			this.act.dragAndDrop(memberFieldInTable, this.memberFieldsForm).build().perform();
			}
		else {
			SysLogger.log("Error : Member Field or form is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Member Field or form is not displayed");
		}
		return new CPSubscriptionPreferencePage(driver);
	}
	
	public CPSubscriptionPreferencePage dragAndDropPicklistCustomFieldToForm(String picklistTrueFalseId) {
		if(this.picklistCustomFieldForm.isDisplayed()) {
			if(this.isRemovePicklistCustomFieldIconDisplayed(picklistTrueFalseId)) {
				SysLogger.log("INFO : Custom Picklist Field is already added in form");
				testReporter.log(LogStatus.INFO, "INFO : Custom Picklist Field is already added in form");
				return new CPSubscriptionPreferencePage(driver);
			}
			WebElement picklistCustomFieldInTable = driver.findElement(By.xpath("//ul[@id='sortable3']/child::li[@data-id='"+picklistTrueFalseId+"']"));
			this.act.dragAndDrop(picklistCustomFieldInTable, this.picklistCustomFieldForm).build().perform();
			}
		else {
			SysLogger.log("Error : Picklist Custom Field or form is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Picklist Custom Field or form is not displayed");
		}
		return new CPSubscriptionPreferencePage(driver);
	}
	
	public CPSubscriptionPreferencePage dragAndDropListToForm(String listId) {
		if(this.listsForm.isDisplayed()) {
			if(this.isRemoveListIconDisplayed(listId)) {
				SysLogger.log("INFO : List is already added in form");
				testReporter.log(LogStatus.INFO, "INFO : List is already added in form");
				return new CPSubscriptionPreferencePage(driver);
			}
			WebElement listNameInTable = driver.findElement(By.xpath("//ul[@id='listLibrarySortable']/child::li[@data-id='"+listId+"']"));
			this.act.dragAndDrop(listNameInTable, this.listsForm).build().perform();
			}	
		else {
			SysLogger.log("Error : List or form is not displayed");
			testReporter.log(LogStatus.INFO, "Error : List or form is not displayed");
		}
		return new CPSubscriptionPreferencePage(driver);
	}

	public CPSubscriptionPreferencePage clickOnFooterDeleteLinkButton() {
		if(this.footerDeleteLinkButton.isDisplayed()) {
			this.footerDeleteLinkButton.click();
		}
		else {
			SysLogger.log("Error : Footer Delete Link button is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Footer Delete Link button is not displayed");
		}
		return new CPSubscriptionPreferencePage(driver);
	}
	
	public CPSubscriptionPreferencePage clickOnSaveButton() {
		if(this.saveButton.isDisplayed()) {
			this.saveButton.click();
		}
		else 
			SysLogger.log("Error : Save Button is not displayed");
		return new CPSubscriptionPreferencePage(this.driver);		
	}

	public String getSubPrefHeadingText() {
		if(this.subPrefPageHeadingText.isDisplayed()) {
			return this.subPrefPageHeadingText.getAttribute("value");
		}
		else {
			SysLogger.log("Error : Sub Pref. Heading TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Sub Pref. Heading TextBox is not displayed");
			return null;
		}
	}
	
	public CPSubscriptionPreferencePage enterSubPrefHeadingText(String text) {
		if(this.subPrefPageHeadingText.isDisplayed()) {
			this.subPrefPageHeadingText.click();
			this.subPrefPageHeadingText.clear();
			this.subPrefPageHeadingText.sendKeys(text);
		}
		else {
			SysLogger.log("Error : Sub Pref. Heading TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Sub Pref. Heading TextBox is not displayed");
		}
		return new CPSubscriptionPreferencePage(driver);
	}

	public String getThankYouHeaderText() {
		if(this.thankYouHeaderTextBox.isDisplayed()) {
			return this.thankYouHeaderTextBox.getAttribute("value");
		}
		else {
			SysLogger.log("Error : Thank You Header TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Thank You Header TextBox is not displayed");
			return null;
		}
	}
	
	public CPSubscriptionPreferencePage enterThankYouHeaderText(String text) {
		if(this.thankYouHeaderTextBox.isDisplayed()) {
			this.thankYouHeaderTextBox.click();
			this.thankYouHeaderTextBox.clear();
			this.thankYouHeaderTextBox.sendKeys(text);
		}
		else {
			SysLogger.log("Error : Thank You Header TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Thank You Header TextBox is not displayed");
		}
		return new CPSubscriptionPreferencePage(driver);
	}

	public String getThankYouMsgText() {
		if(this.thankYouMsgTextBox.isDisplayed()) {
			return this.thankYouMsgTextBox.getAttribute("value");
		}
		else {
			SysLogger.log("Error : Thank You Message TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Thank You Message TextBox is not displayed");
			return null;
		}
	}
	
	public CPSubscriptionPreferencePage enterThankYouMsgText(String text) {
		if(this.thankYouMsgTextBox.isDisplayed()) {
			this.thankYouMsgTextBox.click();
			this.thankYouMsgTextBox.clear();
			this.thankYouMsgTextBox.sendKeys(text);
		}
		else {
			SysLogger.log("Error : Thank You Message TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Thank You Message TextBox is not displayed");
		}
		return new CPSubscriptionPreferencePage(driver);
	}
	
	public String getUnsubMsgText() {
		if(this.unsubMsgTextBox.isDisplayed()) {
			return this.unsubMsgTextBox.getAttribute("value");
		}
		else {
			SysLogger.log("Error : Unsub Message TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Unsub Message TextBox is not displayed");
			return null;
		}
	}
	
	public CPSubscriptionPreferencePage enterUnsubMsgText(String text) {
		if(this.unsubMsgTextBox.isDisplayed()) {
			this.unsubMsgTextBox.click();
			this.unsubMsgTextBox.clear();
			this.unsubMsgTextBox.sendKeys(text);
		}
		else {
			SysLogger.log("Error : Unsub Message TextBox is not displayed");
			testReporter.log(LogStatus.INFO, "Error : Unsub Message TextBox is not displayed");
		}
		return new CPSubscriptionPreferencePage(driver);
	}		
}
