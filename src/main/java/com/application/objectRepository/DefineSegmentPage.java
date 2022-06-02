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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;

public class DefineSegmentPage {
	
	WebDriver driver;
	ExtentTest testReporter;
	WebDriverWait wait;
	
	@FindBy (id = "name")
	private WebElement segmentNameTextBox;
	
	@FindBy (id = "memberTab")
	private WebElement memberTab;
	
	@FindBy (id= "listTab")
	private WebElement listTab;
	
	@FindBy (name = "account_lists")
	private WebElement listNameSelectBox;
	
	@FindBy (xpath = "//div[@id='segment_rules']//select[@name='field_name']")
	private WebElement fieldName;
	
	@FindBy (xpath = "//div[@id='segment_rules']//div[@class='and_template' and contains(text(), 'AND')]/following-sibling::select[@name='field_name']")
	private WebElement fieldName2nd;
	
	@FindBy (xpath = "//div[@id='segment_rules']//select[@name='field_name']")
	private List<WebElement> allFieldName;
	
	@FindBy (xpath = "//div[@id='segment_rules']//select[@name='field_predicate']")
	private WebElement fieldRuleSelectBox;
	
	@FindBy (xpath = "//div[@id='segment_rules']//div[@class='and_template' and contains(text(), 'AND')]/following-sibling::select[@name='field_predicate']")
	private WebElement fieldRule2ndSelectBox;
	
	@FindBy (xpath = "//div[@id='segment_rules']//select[@name='field_predicate']")
	private List<WebElement> allFieldRuleSelectBox;
	
	@FindBy (xpath = "//div[@id='segment_rules']//input[@data-name='value']")
	private WebElement valueInputTextBox;
	
	@FindBy (xpath = "//div[@id='segment_rules']//select[@data-name='value']")
	private WebElement valueInputSelectBox;
	
	@FindBy (xpath = "//div[@id='segment_rules']//div[@class='and_template' and contains(text(), 'AND')]/following-sibling::input")
	private WebElement valueInput2ndTextBox;
	
	@FindBy (xpath = "//div[@id='segment_rules']//input[@data-name='value']")
	private List<WebElement> allValueInputTextBox;
	
	@FindBy (xpath = "//*[@id='segment_rules']//a[@class='add_criteria']")
	private WebElement addCriteriaLink;
	
	@FindBy (xpath = "//form[@id='segment_form']//a[@class='list_add_criteria']")
	private WebElement listAddCriteriaLink;
	
	@FindBy (xpath = "//*[@id='segment_form']/a[@class='add_additional_rule']")
	private WebElement addAdditionalRuleLink;
	
	@FindBy (id = "segment-form-submit")
	private WebElement segmentFormSubmitButton;
	
	public DefineSegmentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
		wait = new WebDriverWait(driver, 60);
	}
	
	public boolean isSegmentNameTextBoxPresent() {
		if(this.segmentNameTextBox.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isMemberTabPresent() {
		if(this.memberTab.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isListTabPresent() {
		if(this.listTab.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isFieldNamePresent() {
		if(this.fieldName.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isFieldRuleSelectBoxPresent() {
		if(this.fieldRuleSelectBox.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isValueInputTextBoxPresent() {
		if(this.valueInputTextBox.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isValueInputSelectBoxPresent() {
		if(this.valueInputSelectBox.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isFieldName2ndPresent() {
		if(this.fieldName2nd.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isFieldRule2ndSelectBoxPresent() {
		if(this.fieldRule2ndSelectBox.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isValueInput2ndTextBoxPresent() {
		if(this.valueInput2ndTextBox.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isAddCriteriaLinkPresent() {
		if(this.addCriteriaLink.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isListAddCriteriaLinkPresent() {
		if(this.listAddCriteriaLink.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isAddAdditionalRuleLinkPresent() {
		if(this.addAdditionalRuleLink.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isSegmentFormSubmitButtonPresent() {
		if(this.segmentFormSubmitButton.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public DefineSegmentPage enterSegmentName(String name) {
		if (this.isSegmentNameTextBoxPresent()) {
			this.segmentNameTextBox.sendKeys(name);
		}
		else {
			SysLogger.log("Error : Not able to enter segment name, segment text box not displayed");
		}
		return new DefineSegmentPage(this.driver);
	}
	
	public DefineSegmentPage clickOnMemberTab() {
		if (this.isMemberTabPresent()) {
			this.memberTab.click();
		}
		else {
			SysLogger.log("Error : Not able to click on member tab, member tab displayed");
		}
		return new DefineSegmentPage(this.driver);
	}
	
	public DefineSegmentPage clickOnListTab() {
		if (this.isListTabPresent()) {
			this.listTab.click();
		}
		else {
			SysLogger.log("Error : Not able to click on list tab, member tab displayed");
		}
		return new DefineSegmentPage(this.driver);
	}
	
	public DefineSegmentPage selectFieldName(String name) {
		if (this.isFieldNamePresent()) {
			Select sc = new Select(this.fieldName);
			sc.selectByVisibleText(name);
		}
		else {
			SysLogger.log("Error : Not able to select field name");
		}
		return new DefineSegmentPage(this.driver);
	}
	
	public DefineSegmentPage selectListByName(String listName) {
		if (this.listNameSelectBox.isDisplayed()) {
			Select sc = new Select(this.listNameSelectBox);
			sc.selectByVisibleText(listName);
		}
		else {
			SysLogger.log("Error : Not able to select list By name");
		}
		return new DefineSegmentPage(this.driver);
	}
	
	public DefineSegmentPage selectChannelByName(int index, String channel) {
		String xpath = "//form[@id='segment_form']//li[@class='criteria item_list']["+index+"]/select[@data-name='channel']";
		WebElement element = this.driver.findElement(By.xpath(xpath));
		if (this.wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed()) {
			Select sc = new Select(element);
			sc.selectByVisibleText(channel);
		}
		else {
			SysLogger.log("Error : Not able to select channel name at index "+index);
		}
		return new DefineSegmentPage(this.driver);
	}
	
	public DefineSegmentPage selectSubscriptionStatus(int index, String status) {
		String xpath = "//form[@id='segment_form']//li[@class='criteria item_list']["+index+"]/select[@data-name='status']";
		WebElement element = this.driver.findElement(By.xpath(xpath));
		if (this.wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed()) {
			Select sc = new Select(element);
			sc.selectByVisibleText(status);
		}
		else {
			SysLogger.log("Error : Not able to select subscription status by name at index "+index);
		}
		return new DefineSegmentPage(this.driver);
	}
	
	public DefineSegmentPage selectFieldName(int ruleId, int index, String name) {
		String xpath = "//div[@id='segment_rules']//div[@class='rule']["+ruleId+"]//ul[contains(@class, 'rule_defination')]/li["+index+"]//select[@name='field_name']";
		WebElement element = this.driver.findElement(By.xpath(xpath));
		if (this.wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed()) {
			Select sc = new Select(element);
			sc.selectByVisibleText(name);
		}
		else {
			SysLogger.log("Error : Not able to select field name on rule "+ruleId+" at index "+index);
		}
		return new DefineSegmentPage(this.driver);
	}
	
	public DefineSegmentPage selectRuleByVisibleText(String name) {
		if (this.isFieldRuleSelectBoxPresent()) {
			Select sc = new Select(this.fieldRuleSelectBox);
			sc.selectByVisibleText(name);
		}
		else {
			SysLogger.log("Error : Not able to select field Rule");
		}
		return new DefineSegmentPage(this.driver);
	}
	
	public DefineSegmentPage selectRuleByVisibleText(int ruleId, int index, String name) {
		String xpath = "//div[@id='segment_rules']//div[@class='rule']["+ruleId+"]//ul[contains(@class, 'rule_defination')]/li["+index+"]//select[@name='field_predicate']";
		WebElement element = this.driver.findElement(By.xpath(xpath));
		if (this.wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed()) {
			Select sc = new Select(element);
			sc.selectByVisibleText(name);
		}
		else {
			SysLogger.log("Error : Not able to select field on rule "+ruleId+" at index "+index);
		}
		return new DefineSegmentPage(this.driver);
	}
	
	public DefineSegmentPage enterValueInputTextBox(String name) {
		if (this.isValueInputTextBoxPresent()) {
			this.valueInputTextBox.sendKeys(name);
		}
		else {
			SysLogger.log("Error : Not able to enter value in value text box");
		}
		return new DefineSegmentPage(this.driver);
	}
	
	public DefineSegmentPage enterValueInputTextBox(int ruleId, int index, String name) {
		String xpath = "//div[@id='segment_rules']//div[@class='rule']["+ruleId+"]//ul[contains(@class, 'rule_defination')]/li["+index+"]//input[@data-name='value']";
		WebElement element = this.driver.findElement(By.xpath(xpath));
		if (this.wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed()) {
			element.sendKeys(name);
		}
		else {
			SysLogger.log("Error : Not able to enter value in value text box on rule "+ruleId+" at index "+index);
		}
		return new DefineSegmentPage(this.driver);
	}
	
	public DefineSegmentPage checkCompareWithYear(int ruleId, int index) {
		String xpath = "//div[@id='segment_rules']//div[@class='rule']["+ruleId+"]//ul[contains(@class, 'rule_defination')]/li["+index+"]//input[@data-name='comparewithyear']";
		WebElement element = this.driver.findElement(By.xpath(xpath));
		if (this.wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed()) {
			element.click();
		}
		else {
			SysLogger.log("Error : Not able to click on compare with year check box");
		}
		return new DefineSegmentPage(this.driver);
	}
	
	public DefineSegmentPage unCheckCompareWithYear(int ruleId, int index) {
		String xpath = "//div[@id='segment_rules']//div[@class='rule']["+ruleId+"]//ul[contains(@class, 'rule_defination')]/li["+index+"]//input[@data-name='comparewithyear']";
		WebElement element = this.driver.findElement(By.xpath(xpath));
		if (this.wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed()) {
			element.click();
		}
		else {
			SysLogger.log("Error : Not able to click on compare with year check box");
		}
		return new DefineSegmentPage(this.driver);
	}
	
	public DefineSegmentPage selectMonthValueInputByVisibleText(int ruleId, int index, String value) {
		String xpath = "//div[@id='segment_rules']//div[@class='rule']["+ruleId+"]//ul[contains(@class, 'rule_defination')]/li["+index+"]//select[contains(@name, 'month')]";
		WebElement element = this.driver.findElement(By.xpath(xpath));
		if (this.wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed()) {
			Select sc = new Select(element);
			sc.selectByVisibleText(value);
		}
		else {
			SysLogger.log("Error : Not able to enter month value in select field on rule "+ruleId+" at index "+index);
		}
		return new DefineSegmentPage(this.driver);
	}
	
	public DefineSegmentPage selectDayValueInputByVisibleText(int ruleId, int index, String value) {
		String xpath = "//div[@id='segment_rules']//div[@class='rule']["+ruleId+"]//ul[contains(@class, 'rule_defination')]/li["+index+"]//select[contains(@name, 'day')]";
		WebElement element = this.driver.findElement(By.xpath(xpath));
		if (this.wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed()) {
			Select sc = new Select(element);
			sc.selectByVisibleText(value);
		}
		else {
			SysLogger.log("Error : Not able to enter month value in select field on rule "+ruleId+" at index "+index);
		}
		return new DefineSegmentPage(this.driver);
	}
	
	public DefineSegmentPage selectValueInputByVisibleText(String value) {
		if (this.isValueInputSelectBoxPresent()) {
			Select sc = new Select(this.valueInputSelectBox);
			sc.selectByVisibleText(value);
		}
		else {
			SysLogger.log("Error : Not able to enter value in select field");
		}
		return new DefineSegmentPage(this.driver);
	}
	
	public DefineSegmentPage selectValueInputByVisibleText(int ruleId, int index, String value) {
		String xpath = "//div[@id='segment_rules']//div[@class='rule']["+ruleId+"]//ul[contains(@class, 'rule_defination')]/li["+index+"]//select[@data-name='value']";
		WebElement element = this.driver.findElement(By.xpath(xpath));
		if (this.wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed()) {
			Select sc = new Select(element);
			sc.selectByVisibleText(value);
		}
		else {
			SysLogger.log("Error : Not able to enter value in select field on rule "+ruleId+" at index "+index);
		}
		return new DefineSegmentPage(this.driver);
	}
	
	public DefineSegmentPage selectSecondFieldName(String name) {
		if (this.isFieldName2ndPresent()) {
			Select sc = new Select(this.fieldName2nd);
			sc.selectByVisibleText(name);
		}
		else {
			SysLogger.log("Error : Not able to select 2nd field name");
		}
		return new DefineSegmentPage(this.driver);
	}
	
	public DefineSegmentPage selectSecondRuleByVisibleText(String name) {
		if (this.isFieldRule2ndSelectBoxPresent()) {
			Select sc = new Select(this.fieldRule2ndSelectBox);
			sc.selectByVisibleText(name);
		}
		else {
			SysLogger.log("Error : Not able to select 2nd field Rule");
		}
		return new DefineSegmentPage(this.driver);
	}
	
	public DefineSegmentPage enterSecondValueInputTextBox(String name) {
		if (this.isValueInput2ndTextBoxPresent()) {
			this.valueInput2ndTextBox.sendKeys(name);
		}
		else {
			SysLogger.log("Error : Not able to enter 2nd value in value text box");
		}
		return new DefineSegmentPage(this.driver);
	}
	
	public AudienceSegmentsPage clickOnSubmitButton() {
		if(this.isSegmentFormSubmitButtonPresent()) {
			this.segmentFormSubmitButton.click();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			SysLogger.log("Error : Not able to click on submit button");
		}
		return new AudienceSegmentsPage(this.driver);
	}
	
	public DefineSegmentPage clickOnAddCriteriaLink() {
		if(this.isAddCriteriaLinkPresent()) {
			this.addCriteriaLink.click();
		}
		else {
			SysLogger.log("Error : Not able to click on addCriteria button");
		}
		return new DefineSegmentPage(this.driver);
	}
	
	public DefineSegmentPage clickOnListAddCriteriaLink() {
		if(this.isListAddCriteriaLinkPresent()) {
			this.listAddCriteriaLink.click();
		}
		else {
			SysLogger.log("Error : Not able to click on list add Criteria button");
		}
		return new DefineSegmentPage(this.driver);
	}
	
	public DefineSegmentPage clickOnAddCriteriaLink(int ruleId) {
		String xpath = "//div[@id='segment_rules']//div[@class='rule']["+ruleId+"]//a[@class='add_criteria']";
		WebElement element = this.driver.findElement(By.xpath(xpath));
		if (this.wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed()) {
			element.click();
		}
		else {
			SysLogger.log("Error : Not able to click on addCriteria button on rule "+ruleId);
		}
		return new DefineSegmentPage(this.driver);
	}
	
	public DefineSegmentPage clickOnAdditionalRuleLink() {
		if(this.isAddAdditionalRuleLinkPresent()) {
			this.addAdditionalRuleLink.click();
		}
		else {
			SysLogger.log("Error : Not able to click on add Additional Rule Link button");
		}
		return new DefineSegmentPage(this.driver);
	}

}
