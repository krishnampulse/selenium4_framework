package com.application.objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.application.utility.DriverMethods;
import com.application.utility.SysLogger;

public class ListSubscribersPage {

	WebDriver driver;

	@FindBy(xpath = "//a[@id='import_audience' and contains(@href, 'uploads/new')]")
	private WebElement importSubscribers;
	
	@FindBy (xpath = "//table[@id='members']/tbody[1]/tr[1]/td[@class='phone']/ul/li")
	private WebElement firstMemberWithPhone;
	
	@FindBy (xpath = "//table[@id='members']/tbody[1]/tr[1]/td[@class='email']/ul/li")
	private WebElement firstMemberWithEmail;
	
	@FindBy (id = "export_audience")
	private WebElement exportSubscribers;
	
	@FindBy (id= "dk_container_add_audience_section")
	private WebElement memberAddDropDown;
	
	@FindBy (xpath = "//div[@id='dk_container_add_audience_section']//a[text()='Add Members']")
	private WebElement createANewMemberOption;
	
	@FindBy(id = "new_member")
	private WebElement createNewMemberLink;
	
	@FindBy (xpath = "//a[@id='import_audience' and contains(@href, 'unsubscribe/new')]")
	private WebElement unsubscribeSubscribers;
	
	@FindBy(xpath = "//div[@class='ui-dialog-buttonset']/button[contains(@class, 'ui-button-text') and @type='button' and @role='button']/span[contains(text(),'Yes')]")
	private WebElement deleteMemberAlertButton;

	public ListSubscribersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isImportSubscribersButtonPresent() {
		if (this.importSubscribers.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public UploadAudiencePage clickOnImportSubscribersButton() {
		if (isImportSubscribersButtonPresent()) {
			this.importSubscribers.click();
			return new UploadAudiencePage(driver);
		} else {
			Assert.assertTrue(false, "Error : Import Subscribers button not present");
			return new UploadAudiencePage(driver);
		}
	}	

	public boolean isUnsubscribeSubscribersButtonPresent() {
		if (this.unsubscribeSubscribers.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public UnsubscribeAudiencePage clickOnUnsubscribeSubscribersButton() {
		if (isUnsubscribeSubscribersButtonPresent()) {
			this.unsubscribeSubscribers.click();
		}
		else {
			Assert.assertTrue(false, "Error : Unsubscriber Subscribers button is not present");
		}
		return new UnsubscribeAudiencePage(driver);
	}
	
	public boolean isFirstMemberWithPhonePresent() {
		if (DriverMethods.isElementPresent(driver, firstMemberWithPhone)) {
			return true;
		}
		else return false;
	}
	
	public boolean isFirstMemberWithEmailPresent() {
		if (DriverMethods.isElementPresent(driver, this.firstMemberWithEmail)) {
			return true;
		}
		else return false;
	}
	
	public boolean isMemberAddDropDownPresent() {
		if(this.memberAddDropDown.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isExportSubscribersLinkPresent() {
		if(this.exportSubscribers.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public String getFirstMemberPhoneNumber() {
		if (isFirstMemberWithPhonePresent()) { 
			return "1"+this.firstMemberWithPhone.getText().replace("-", "");
		}
		else return null;
	}
	
	public String getFirstMemberEmail() {
		if (isFirstMemberWithEmailPresent()) {
			return this.firstMemberWithEmail.getAttribute("title");
		}
		else return null;
	}
	
	public CreateMemberPage clickOnCreateNewMemberLink() {
		try {
			if (this.isMemberAddDropDownPresent()) {
				this.memberAddDropDown.click();
				this.createANewMemberOption.click();
				return new CreateMemberPage(this.driver);
			} else {
				SysLogger.log("Error : Member add dropdown is not present");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (this.createNewMemberLink.isDisplayed()) {
				this.createNewMemberLink.click();
				return new CreateMemberPage(this.driver);
			} else {
				SysLogger.log("Error : Member add link is not present");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new CreateMemberPage(this.driver);
	}
	
	public ListSubscribersPage clickOnExportSubscribersLink() throws InterruptedException {
		if (this.isExportSubscribersLinkPresent()) {
			this.exportSubscribers.click();
			Thread.sleep(5000);
			return new ListSubscribersPage(this.driver);
		}
		return new ListSubscribersPage(this.driver);
	}
	
	public CreateMemberPage clickOnMemberEditLink(String memberId) throws InterruptedException {
		Thread.sleep(8000);
		String xpath = "//tr[@id='"+memberId+"']//a[@title='Edit']";
			this.driver.findElement(By.xpath(xpath)).click();
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return new CreateMemberPage(this.driver);
		}
	
	//Added to Delete Member from UI List Page in CP
	
		public ListSubscribersPage clickOnMemberDeleteButton(String memberId) throws InterruptedException{
			WebElement memberDeleteButton = this.driver.findElement(By.xpath("//tr[@id='"+memberId+"']//a[@title='Delete']"));
			
	        if (memberDeleteButton.isDisplayed()) {
	        	memberDeleteButton.click();
	        } 
	        else {
	        	System.out.println("Error: member Delete Button not present on page");
	        }
	        Thread.sleep(5000);
	        this.clickOnDeleteMemberFieldPopUpBox();
			return new ListSubscribersPage(this.driver);
	        
		}
		private void clickOnDeleteMemberFieldPopUpBox() throws InterruptedException{
			
			if (deleteMemberAlertButton.isDisplayed()) {
				deleteMemberAlertButton.click();
			} else {
				System.out.println("Error : Delete Member Alert button is not present");
			}
			Thread.sleep(7000);
			
		}

}
