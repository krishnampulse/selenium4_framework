package com.application.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.application.utility.SysLogger;

public class CP403ErrorPage {
	
	WebDriver driver;
	
	@FindBy(id = "userLogout")
    private WebElement clickHereToLogoutLink;
	
    public CP403ErrorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public AuthServiceLoginPage clickOnLogout() {
    	if (this.clickHereToLogoutLink.isDisplayed()) {
            this.clickHereToLogoutLink.click();
        } else {
            SysLogger.log("Error: Element 'click here' to logout is not Displayed or not Enabled");
        }
        return new AuthServiceLoginPage(this.driver);
    }
}
