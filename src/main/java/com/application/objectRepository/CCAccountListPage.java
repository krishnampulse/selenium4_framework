package com.application.objectRepository;

import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CCAccountListPage {
    WebDriver driver;
    ExtentTest testReporter;
    
    @FindBy(xpath = "//a[@href='/cp-account/new' and text()='Add Control Panel Account']")
    private WebElement addNewAccountLink;
    
    @FindBy(xpath = "//section[@class='content']/h1[text()='Account List']")
    private WebElement accountListHeading;
    
    @FindBy(xpath = "//button[@type='button' and text()='Continue']")
    private WebElement dialogContinueButton;

    public CCAccountListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        testReporter = ReporterFactory.getTest();
    }
    
    public String getAccountStatusByAccountName(String accountName) {
    	WebElement element = this.driver.findElement(By.xpath("//td[contains(text(), '"+accountName+"')]/following-sibling::td[3]"));
    	if(element.isDisplayed()) {
    		return element.getText();
    	} return null;
    }
    
    public CCAccountListPage verifyAccountListHeadingPresent() {
    	if(this.accountListHeading.isDisplayed()) {
    		return this;
    	} else {
    		Assert.assertTrue(false, "Error: account list heading not displayed on account list page");
    	}
    	return this;
    }

    public CCEditAccountPage clickOnAccountEditButton(String accountId) throws InterruptedException {
        int counter = 0;
        WebElement editAccountButton = driver.findElement(By.cssSelector("a[href=\"/cp-account/"+accountId+"/edit\"]"));

        if (editAccountButton.isDisplayed()) {
            testReporter.log(LogStatus.INFO, "Edit button found and going to click button");
            editAccountButton.click();
            testReporter.log(LogStatus.INFO, "Edit button clicked");
            String currentUrl = driver.getCurrentUrl();
            while (currentUrl.contains("account/list")){
                 editAccountButton.click();
                 Thread.sleep(1000);
                 currentUrl = driver.getCurrentUrl();
                 counter += 1;
                 if (counter > 3) {
                     break;
                 }
            }
        } else {
            SysLogger.log("Error: Element 'Edit Account link' not Displayed or not Enabled");
        }
        return new CCEditAccountPage(driver);
    }
    
    public CreateControlPanelAccountPage clickOnAddNewAccountLink() throws InterruptedException{
        if (this.addNewAccountLink.isDisplayed()) {
            testReporter.log(LogStatus.INFO, "Add new Account button found and going to click");
            this.addNewAccountLink.click();
            Thread.sleep(1000);
            testReporter.log(LogStatus.INFO, "Add new Account button clicked");
            return new CreateControlPanelAccountPage(this.driver);
        } else {
            SysLogger.log("Error: Element 'Add new Account button' not Displayed or not Enabled");
        }
        return new CreateControlPanelAccountPage(driver);
    }
    
    public CCAccountListPage suspendAccountById(String accountID) throws InterruptedException{
    	WebElement element = this.driver.findElement(By.xpath("//td[@class='actions']/a[@href='/cp-account/"+accountID+"/suspend' and text()='Suspend']"));
        if (element.isDisplayed()) {
            testReporter.log(LogStatus.INFO, "Suspend button found and going to click");
            element.click();
            testReporter.log(LogStatus.INFO, "Suspend button clicked");
            Thread.sleep(1000);
            this.dialogContinueButton.click();
            testReporter.log(LogStatus.INFO, "Suspend Account Continue button clicked");
            Thread.sleep(10000);
            return new CCAccountListPage(driver);
        } else {
            SysLogger.log("Error: Element 'Suspend button' not Displayed or not Enabled");
        }
        return new CCAccountListPage(driver);
    }
    
    public CCAccountListPage activateAccountById(String accountID) throws InterruptedException{
    	WebElement element = this.driver.findElement(By.xpath("//td[@class='actions']/a[@href='/cp-account/"+accountID+"/activate' and text()='Activate']"));
        if (element.isDisplayed()) {
            testReporter.log(LogStatus.INFO, "Activate button found and going to click");
            element.click();
            testReporter.log(LogStatus.INFO, "Activate button clicked");
            Thread.sleep(1000);
            this.dialogContinueButton.click();
            testReporter.log(LogStatus.INFO, "Activate Account Continue button clicked");
            Thread.sleep(10000);
            return new CCAccountListPage(driver);
        } else {
            SysLogger.log("Error: Element 'Activate button' not Displayed or not Enabled");
        }
        return new CCAccountListPage(driver);
    }
    
    public CCAccountListPage deleteAccountById(String accountID) throws InterruptedException{
    	WebElement element = this.driver.findElement(By.xpath("//td[@class='actions']/a[@href='/cp-account/"+accountID+"/delete' and text()='Delete']"));
        if (element.isDisplayed()) {
            testReporter.log(LogStatus.INFO, "Delete button found and going to click");
            element.click();
            testReporter.log(LogStatus.INFO, "Delete button clicked");
            Thread.sleep(1000);
            this.dialogContinueButton.click();
            testReporter.log(LogStatus.INFO, "Suspend Account Continue button clicked");
            Thread.sleep(10000);
            return new CCAccountListPage(driver);
        } else {
            SysLogger.log("Error: Element 'Delete button' not Displayed or not Enabled");
        }
        return new CCAccountListPage(driver);
    }
    
    public CreateControlPanelAccountPage clickOnCloneAccountById(String accountID) throws InterruptedException{
    	WebElement element = this.driver.findElement(By.xpath("//td[@class='actions']/a[@href='/cp-account/"+accountID+"/clone' and text()='Clone']"));
        if (element.isDisplayed()) {
            testReporter.log(LogStatus.INFO, "Clone button found and going to click");
            element.click();
            testReporter.log(LogStatus.INFO, "Clone button clicked");
            Thread.sleep(1000);
            return new CreateControlPanelAccountPage(driver);
        } else {
            SysLogger.log("Error: Element 'Clone button' not Displayed or not Enabled");
        }
        return new CreateControlPanelAccountPage(driver);
    }
}
