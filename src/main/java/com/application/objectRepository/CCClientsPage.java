package com.application.objectRepository;

import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CCClientsPage {

    WebDriver driver;
    ExtentTest testReporter;

    @FindBy(xpath = "//a[@href='/account/list']")
    @CacheLookup
    private WebElement accountListButton;

    public CCClientsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        testReporter = ReporterFactory.getTest();
    }

    public boolean isAccountListButtonPresent() {
        if (this.accountListButton.isDisplayed()) {
            return true;
        } else
            return false;
    }

    public CCAccountListPage clickOnAccountListButton() {
        if (isAccountListButtonPresent()) {
            testReporter.log(LogStatus.INFO, "Account List button found and going to click on Clients button");
            accountListButton.click();
            testReporter.log(LogStatus.INFO, "Account List button clicked");
        }
        else {
            SysLogger.log("Error: Element not Displayed or not Enabled");
        }
        return new CCAccountListPage(driver);
    }
}
