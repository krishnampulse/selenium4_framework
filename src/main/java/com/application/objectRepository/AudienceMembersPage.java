package com.application.objectRepository;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;
import com.relevantcodes.extentreports.ExtentTest;

public class AudienceMembersPage {
	
	WebDriver driver;
	ExtentTest testReporter;
	WebDriverWait wait;
	
	@FindBy(id = "export_audience")
	private WebElement exportMemberLink;

	@FindBy(css = "[placeholder='Search Members']")
	private WebElement memberSearchField;

	@FindBy(css = "[alt='search']")
	private WebElement searchIcon;

	@FindBy(xpath = "//button[text()='Mobile Phone']/parent::div/span/button[@class='icon-triangle-down']")
	private WebElement searchTriangleDown;

	@FindBy(xpath = "//div[text()='FIRST NAME']")
	private WebElement contentTableHeader;

	@FindBy(xpath = "//button[text()='LAST']")
	private WebElement buttonLast;
	
	@FindBy(id = "searchAttribute")
	private WebElement identifierSelectBox;
	
	@FindBy(id = "fieldKeywords")
	private WebElement fieldKeywordsInputBox;
	
	@FindBy(xpath = "//button[@type='submit' and @title='Search']")
	private WebElement searchButton;
	
	@FindBy(xpath = "//nav[@id='navigation']//a[@class='segments']")
	private WebElement segmentsLinkButton;
	
	@FindBy(xpath = "//nav[@id='navigation']//a[@class='Email_DNC_List']")
	private WebElement emailDNCListPageLink;
	
	@FindBy(xpath = "//nav[@id='navigation']//a[@class='lists']")
	private WebElement audienceListsPageLink;
	
	@FindBy(id="enc number")
	private WebElement clickToDecryptButtonforEncNumberValue;
	
	@FindBy(id="customAttributes'enc number'")
	private WebElement encNumberCustomFieldValueTextBox;
	
	@FindBy(id="enc single line")
	private WebElement clickToDecryptButtonforEncSingleLineValue;
	
	@FindBy(id="customAttributes'enc single line'")
	private WebElement encSingleLineCustomFieldValueTextBox;

    @FindBy(id="enc date")
   	private WebElement clickToDecryptButtonforEncDateValue;
    
    @FindBy(id="customAttributes'enc date'")
    private WebElement encDateCustomFieldValueTextBox;
    
    @FindBy(id="enc time")
   	private WebElement clickToDecryptButtonforEncTimeValue;
    
    @FindBy(id="customAttributes'enc time'")
    private WebElement encTimeCustomFieldValueTextBox;
    
    @FindBy(id="enc picklist")
	private WebElement clickToDecryptButtonforEncPicklistValue;
	
    @FindBy(xpath = "//input[@type='radio' and @value='bike']")
    private WebElement encPicklistCustomFieldValue;
    
    @FindBy(id="enc paragraph")
	private WebElement clickToDecryptButtonforEncParagraphValue;
	
    @FindBy(id="customAttributes'enc paragraph'")
    private WebElement encParagraphCustomFieldValueTextBox;
    
    @FindBy(id="enc multi picklist")
	private WebElement clickToDecryptButtonforEncMultiPicklistValue;
	
	 
	public AudienceMembersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
		wait = new WebDriverWait(driver, 120);
	}
		
	public String getEncNumberCustomFieldValue() {
		return encNumberCustomFieldValueTextBox.getAttribute("value");
	}
	
	public String getEncSingleLineCustomFieldValue() {
		return encSingleLineCustomFieldValueTextBox.getAttribute("value");
	}
	
	public String getEncDateCustomFieldValue() {
		return encDateCustomFieldValueTextBox.getAttribute("value");
	}
	
	public String getEncTimeCustomFieldValue() {
		return encTimeCustomFieldValueTextBox.getAttribute("value");
	}
	
	public String getEncPicklistCustomFieldValue() {
		return encPicklistCustomFieldValue.getAttribute("value");
	}
	
	public String getEncParagraphCustomFieldValue() {
		return encParagraphCustomFieldValueTextBox.getAttribute("value");
	}
	
	public String  getEncMutliPicklistCustomFieldValue() { 
    	List<WebElement>  value = driver.findElements(By.xpath("//div[@class='options-container']/li/input[@name=\"customAttributes['enc multi picklist']\" and @checked=\"checked\"]"));  		
    	String finalValue="";
    	for (WebElement element:value)
    	{
    		finalValue = finalValue+element.getAttribute("value") +" | " ;
    	}
    	int length = finalValue.length();
    	return finalValue.substring(0, length-3);
}

	public boolean isEncNumberCustomFieldPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.clickToDecryptButtonforEncNumberValue)).isDisplayed()) {
			return true;
		} else
			return false;	
	}
	
	public boolean isEncSingleLineCustomFieldPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.clickToDecryptButtonforEncSingleLineValue)).isDisplayed()) {
			return true;
		} else
			return false;	
	}
	
	public boolean isEncDateCustomFieldPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.clickToDecryptButtonforEncDateValue)).isDisplayed()) {
			return true;
		} else
			return false;	
	}
	
	public boolean isEncTimeCustomFieldPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.clickToDecryptButtonforEncTimeValue)).isDisplayed()) {
			return true;
		} else
			return false;	
	}
	
	public boolean isEncPicklistCustomFieldPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.clickToDecryptButtonforEncPicklistValue)).isDisplayed()) {
			return true;
		} else
			return false;	
	}
	
	public boolean isEncParagraphCustomFieldPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.clickToDecryptButtonforEncParagraphValue)).isDisplayed()) {
			return true;
		} else
			return false;	
	}
	
	public boolean isEncMultiPicklistCustomFieldPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.clickToDecryptButtonforEncMultiPicklistValue)).isDisplayed()) {
			return true;
		} else
			return false;	
	}
	
	public boolean isExportMemberLinkPresent() {
		if (this.exportMemberLink.isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public boolean isSegmentPageLinkPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.segmentsLinkButton)).isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public boolean isEmailDNCLPageLinkPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.emailDNCListPageLink)).isDisplayed()) {
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
	
	public AudienceSegmentsPage goToSegmentsPage() {
		if (this.isSegmentPageLinkPresent()) {
			this.segmentsLinkButton.click();
		} else {
			SysLogger.log("Error: Audience Segment page link not present");
		}
		return new AudienceSegmentsPage(driver);
	}
	
	public DoNotContactEmailPage goToDoNotContactEmailPage() {
		if (this.isEmailDNCLPageLinkPresent()) {
			this.emailDNCListPageLink.click();
		} else {
			SysLogger.log("Error: Do not contact email page link not present");
		}
		return new DoNotContactEmailPage(driver);
	}
	
	public AudienceListsPage goToAudienceListsPage() {
		if (this.isAudienceListsPageLinkPresent()) {
			this.audienceListsPageLink.click();
		} else {
			SysLogger.log("Error: Audience Lists Page Link not present");
		}
		return new AudienceListsPage(driver);
	}
	
	public ExportMembersPage clickOnExportMemberLink() {
		if (this.isExportMemberLinkPresent()) {
			this.exportMemberLink.click();
			return new ExportMembersPage(this.driver);
		} else {
			SysLogger.log("Error: Export Audience member page link not present on page");
		}
		return new ExportMembersPage(this.driver);
	}

	public AudienceMembersPage selectIdentifierByValue(String identifierValue) {
		if (this.identifierSelectBox.isDisplayed()) {
			Select sc = new Select(this.identifierSelectBox);
			sc.selectByValue(identifierValue);
		}
		else {
			SysLogger.log("Error : Not able to select Identifier by value");
		}
		return new AudienceMembersPage(this.driver);
	}
	
	public AudienceMembersPage enterSearchKeyword(String keyword) {
		if (this.fieldKeywordsInputBox.isDisplayed()) {
			this.fieldKeywordsInputBox.click();
			this.fieldKeywordsInputBox.clear();
			this.fieldKeywordsInputBox.sendKeys(keyword);
		}
		else {
			SysLogger.log("Error : field Keywords Input Box not displayed");
		}
		return new AudienceMembersPage(this.driver);
	}
	
	public AudienceMembersPage clickOnSearchButton() {
		if (this.searchButton.isDisplayed()) {
			this.searchButton.click();
			return new AudienceMembersPage(this.driver);
		} else {
			SysLogger.log("Error: search Button not present on page");
		}
		return new AudienceMembersPage(this.driver);
	}
	
	public AudienceMembersPage clickOnAddDNCLButton(String memberId) throws InterruptedException {
		WebElement addDNCLButton = this.driver.findElement(By.xpath("//tr[@id='"+memberId+"']/td[@class='select']/ul/li/a[@title='Do Not Contact Email']"));
		if (addDNCLButton.isDisplayed()) {
			addDNCLButton.click();
			Thread.sleep(5000);
			return new AudienceMembersPage(this.driver);
		} else {
			SysLogger.log("Error: add DNCL Button not present on page");
		}
		return new AudienceMembersPage(this.driver);
	}
	
	
	public AudienceMembersPage clickOnRemoveDNCLButton(String memberId) {
		WebElement removeDNCLButton = this.driver.findElement(By.xpath("//tr[@id='"+memberId+"']/td[@class='select']/ul/li/a[@title='No More Block Email']"));
		if (removeDNCLButton.isDisplayed()) {
			removeDNCLButton.click();
			return new AudienceMembersPage(this.driver);
		} else {
			SysLogger.log("Error: remove DNCL Button not present on page");
		}
		return new AudienceMembersPage(this.driver);
	}
	
	public EditAudienceMemberPage clickOnMemberEditButton(String memberId) {
		WebElement memberEditButton = this.driver.findElement(By.xpath("//tr[@id='"+memberId+"']/td[@class='select']/ul/li/a[@title='Edit']"));
		if (memberEditButton.isDisplayed()) {
			memberEditButton.click();
			return new EditAudienceMemberPage(this.driver);
		} else {
			SysLogger.log("Error: member Edit Button not present on page");
		}
		return new EditAudienceMemberPage(this.driver);
	}
	
	public AudienceMembersPage checkEncNumberField() {
		if (isEncNumberCustomFieldPresent()) {
			clickToDecryptButtonforEncNumberValue.click();		
		}
		else {
			SysLogger.log("Error: Enc Number Custom Field not found on page");
		}
		return new AudienceMembersPage(this.driver);
	}
	
	public AudienceMembersPage checkEncSingleLineField() {
		if(isEncSingleLineCustomFieldPresent()) {
			clickToDecryptButtonforEncSingleLineValue.click();
		}
		else {
			SysLogger.log("Error: Enc Single Line Custom Field not found on page");
		}
		return new AudienceMembersPage(this.driver);
	}
	
	public AudienceMembersPage checkEncDateField() {
		if(isEncDateCustomFieldPresent()) {
			clickToDecryptButtonforEncDateValue.click();
		}
		else {
			SysLogger.log("Error: Enc Date Custom Field not found on page");
		}
		return new AudienceMembersPage(this.driver);
	}
	
	public AudienceMembersPage checkEncTimeField() {
		if(isEncTimeCustomFieldPresent()) {
			clickToDecryptButtonforEncTimeValue.click();
		}
		else {
			SysLogger.log("Error: Enc Time Custom Field not found on page");
		}
		return new AudienceMembersPage(this.driver);
	}
	
	public AudienceMembersPage checkEncPicklistField() {
		if(isEncPicklistCustomFieldPresent()) {
			clickToDecryptButtonforEncPicklistValue.click();
		}
		else {
			SysLogger.log("Error: Enc Picklist Custom Field not found on page");
		}
		return new AudienceMembersPage(this.driver);
	}
	
	public AudienceMembersPage checkEncParagraphField() {
		if(isEncParagraphCustomFieldPresent()) {
			clickToDecryptButtonforEncParagraphValue.click();
		}
		else {
			SysLogger.log("Error: Enc Paragraph Custom Field not found on page");
		}
		return new AudienceMembersPage(this.driver);
	}	
	
	  public AudienceMembersPage checkEncMultiPicklistField() {
			if(isEncMultiPicklistCustomFieldPresent()) {
				clickToDecryptButtonforEncMultiPicklistValue.click();
			}
			else {
				SysLogger.log("Error: Enc Multi Picklist Custom Field not found on page");
			}
			return new AudienceMembersPage(this.driver);
		}
	
}

