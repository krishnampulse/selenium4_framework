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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CCLoginPage {

    WebDriver driver;
    ExtentTest testReporter;
    WebDriverWait wait;

    @FindBy(id = "email")
    @CacheLookup
    private WebElement userName;

    @FindBy(id = "password")
    @CacheLookup
    private WebElement password;

    @FindBy(name= "form.login.submitted")
    @CacheLookup
    public WebElement loginButton;
    
    @FindBy(xpath = "//section[@class='content']/h1")
    private WebElement accessDeniedHeadingText;
    
    @FindBy(xpath = "//section[@class='content']")
    private WebElement accessDeniedFullTextMessage;
    
    @FindBy(xpath = "//section[@class='content']/h1")
    private WebElement resetPasswordHeadingText;
    
    @FindBy(xpath = "//form[@action='/reset']/fieldset/ul/li[1]")
    private WebElement resetPasswordFullTextMessage;

    public CCLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        testReporter = ReporterFactory.getTest();
        wait = new WebDriverWait(driver, 60);
    }
    
	public boolean isAccessDeniedHeadingTextPresent() {
		String text = "Access Denied";
		if (wait.until(ExpectedConditions.textToBePresentInElement(this.accessDeniedHeadingText, text))) {
			return true;
		} else
			return false;
	}
	
	public boolean isResetPasswordHeadingTextPresent() {
		String text = "Reset Password";
		if (wait.until(ExpectedConditions.textToBePresentInElement(this.resetPasswordHeadingText, text))) {
			return true;
		} else
			return false;
	}
	
	public String getFullAccessDeniedTextMessage() {
		return this.accessDeniedFullTextMessage.getText();
	}
	
	public String getFullResetPasswordTextMessage() {
		return this.resetPasswordFullTextMessage.getText();
	}

    public void enterUserName(String userName) {
        if (this.userName.isDisplayed() && this.userName.isEnabled()) {
            testReporter.log(LogStatus.INFO, "UserName textbox found and going to fill user name");
            this.userName.clear();
            this.userName.sendKeys(userName);
            testReporter.log(LogStatus.INFO, "user name '"+userName+"' entered in username textbox");
        } else
            SysLogger.log("Error: Element 'user name' not Displayed or not Enabled");
    }

    public void enterPassword(String password) {
        if (this.password.isDisplayed() && this.password.isEnabled()) {
            testReporter.log(LogStatus.INFO, "password textbox found and going to fill password");
            this.password.clear();
            this.password.sendKeys(password);
            testReporter.log(LogStatus.INFO, "password '"+password+"' entered in password textbox");
        } else {
            SysLogger.log("Error: Element 'password' not Displayed or not Enabled");
        }
    }

    public void clickOnLoginButton() {
        if (this.loginButton.isDisplayed() && this.loginButton.isEnabled()) {
            testReporter.log(LogStatus.INFO, "Login button found and going to click login button");
            loginButton.click();
            testReporter.log(LogStatus.INFO, "Login button clicked");
        } else {
            SysLogger.log("Error: Element 'login button' not Displayed or not Enabled");
        }
    }

    public CCHomePage login(String userName, String password) throws InterruptedException {
    	String currentUrl = driver.getCurrentUrl();
		String title = driver.getTitle();
		SysLogger.log("Current CC url is "+currentUrl+" and title is "+title);
		if (title.equalsIgnoreCase("Access Denied - Command Center")) {
			Assert.assertTrue(this.isAccessDeniedHeadingTextPresent());
			String message = this.getFullAccessDeniedTextMessage();
			testReporter.log(LogStatus.ERROR, message);
			Assert.assertTrue(false, message);
		}
        enterUserName(userName);
        enterPassword(password);
        clickOnLoginButton();
        title = driver.getTitle();
		if (title.equalsIgnoreCase("Welcome - Command Center")) {
			testReporter.log(LogStatus.INFO, "Login successfully with user name '"+userName+"' and password '"+password+"'");
	        Thread.sleep(6000);
			return new CCHomePage(driver);
		}
		if (title.equalsIgnoreCase("Reset Password - Command Center")) {
			Assert.assertTrue(this.isResetPasswordHeadingTextPresent());
			String message = this.getFullResetPasswordTextMessage();
			testReporter.log(LogStatus.ERROR, "Password Expired: "+message);
			Assert.assertTrue(false, "Password Expired: "+message);
		}
		return new CCHomePage(driver);
    }
}
