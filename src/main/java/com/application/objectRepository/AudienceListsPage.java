package com.application.objectRepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AudienceListsPage {
	WebDriver driver;
	ExtentTest testReporter;
	private boolean flag = false;
	WebDriverWait wait;
	
	@FindBy(xpath = "//a[@class='list_name']")
	private List<WebElement> allList;

	@FindBy(xpath = "//form[@id='paginationForm']/nav/ul/li[@class='next_page']/a")
	private WebElement nextPageNavigationButton;
	
	@FindBy(xpath = "//nav[@id='navigation']//a[@class='segments']")
	private WebElement segmentsLinkButton;
	
	@FindBy(xpath = "//nav[@id='navigation']//a[@class='Email_DNC_List']")
	private WebElement emailDNCListPageLink;

	@FindBy(xpath = "//nav[@id='navigation']//a[@class='members']")
	private WebElement audienceMemberPageLink;
	
	@FindBy(id = "add_list")
	private WebElement createListButton;
	
	@FindBy(xpath = "//a[text()='Create a list' and @href='/audience/lists/new']")
	private WebElement createListButtonForNewAccount;

	public AudienceListsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
		wait = new WebDriverWait(driver, 120);
	}

	public boolean isNextPageNavigationButtonPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.nextPageNavigationButton)).isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public void clickOnNextPageNavigationButton() {
		if (isNextPageNavigationButtonPresent()) {
			this.nextPageNavigationButton.click();
		} else SysLogger.log("Next page button is not present");
	}
	
	public boolean isSegmentPageLinkPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.segmentsLinkButton)).isDisplayed()) {
			return true;
		} else
			return false;
	}
	
	public boolean isAudienceMemberPageLinkPresent() {
		if (this.wait.until(ExpectedConditions.visibilityOf(this.audienceMemberPageLink)).isDisplayed()) {
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
	
	public boolean isCreateListButtonPresent() {
		if(this.createListButton.isDisplayed()) {
			return true;
		}
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
	
	public AudienceMembersPage goToAudienceMemberPage() {	
		if (this.isAudienceMemberPageLinkPresent()) {	
			this.audienceMemberPageLink.click();	
		} else {	
			SysLogger.log("Error: Audience Member page link not present");	
		}	
		return new AudienceMembersPage(driver);	
	}
	

	private boolean clickOnListByName(String listName) {
		for (WebElement element : allList) {
			if (element.getText().equals(listName)) {
				element.click();
				testReporter.log(LogStatus.INFO, "Requested list with name '"+listName+"' found and clicked");
				return true;
			}
		}
		testReporter.log(LogStatus.INFO, "Requested list with name '"+listName+"' not found on current list page");
		return false;
	}
	
	public ListSubscribersPage goToListSubscribersPage(String listName) throws InterruptedException {
		Thread.sleep(5000);
		do {
			flag = this.clickOnListByName(listName);
			if(flag == false)
				this.clickOnNextPageNavigationButton();
		}while(flag == false);
		testReporter.log(LogStatus.INFO, "Navigated to list SubscribersPage of list '"+listName+"'");
		return new ListSubscribersPage(driver);
	}
	
	public CreateUpdateListPage clickOnCreateNewListButton() {
		if(this.isCreateListButtonPresent()) {
			this.createListButton.click();
			testReporter.log(LogStatus.INFO, "Clicked on list create button");
		}
		else {
			SysLogger.log("Error : Create new list button not present");
		}
		return new CreateUpdateListPage(this.driver);
	}
	
	public CreateUpdateListPage clickOnCreateNewListButtonForNewAccount() {
		if(this.createListButtonForNewAccount.isDisplayed()) {
			this.createListButtonForNewAccount.click();
			testReporter.log(LogStatus.INFO, "Clicked on list create button for new account");
		}
		else {
			SysLogger.log("Error : Create new list button for new account is not present");
		}
		return new CreateUpdateListPage(this.driver);
	}
}
