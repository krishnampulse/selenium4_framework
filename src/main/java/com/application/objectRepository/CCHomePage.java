package com.application.objectRepository;

import com.application.factories.ReporterFactory;
import com.application.utility.SysLogger;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CCHomePage {

    WebDriver driver;
    ExtentTest testReporter;

    @FindBy(css = "a[href*='/client/list']")
    public WebElement clientsButton;

    @FindBy(xpath = "a[href*='/logout']")
    public WebElement logoutButton;

    public CCHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        testReporter = ReporterFactory.getTest();
    }

    public boolean isClientsButtonPresent() {
        if (this.clientsButton.isDisplayed()) {
            return true;
        } else
            return false;
    }

    public boolean isLogoutButtonPresent() {
        if (this.logoutButton.isDisplayed()) {
            return true;
        } else
            return false;
    }

    public CCClientsPage clickOnClientsButton() {
        if (this.isClientsButtonPresent()) {
            this.testReporter.log(LogStatus.INFO, "Clients button found and going to click on Clients button");
            this.clientsButton.click();
            this.testReporter.log(LogStatus.INFO, "Clients button clicked");
        }
        else {
            SysLogger.log("Error: Element not Displayed or not Enabled");
        }
        return new CCClientsPage(driver);
    }

    public CCLoginPage ccLogOut() {
        if (isLogoutButtonPresent()) {
            this.logoutButton.click();
        } else {
            SysLogger.log("Error: Element 'log out button' not Displayed or not Enabled");
        }
        return new CCLoginPage(this.driver);
    }
}
