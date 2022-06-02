package com.application.objectRepository;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;

import com.application.factories.ReporterFactory;
import com.application.utility.ConfigReader;
import com.application.utility.SysLogger;
import com.application.utility.UtilityMethods;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AuthServiceLoginPage {
	WebDriver driver;
	String url;
	ExtentTest testReporter;
	WebDriverWait wait;
	
	@FindBy(id = "email")
	private WebElement userEmail;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(linkText = "Forgot Password?")
	private WebElement forgotPwdLink;
	
	@FindBy(xpath = "//button[@type='submit' and contains(text(), 'NEXT')]")
	private WebElement nextButton;
	
	@FindBy(id = "acc-tool-tip")
	private WebElement accountToolTip;
	
	@FindBy(id = "acc-dropdown")
	private WebElement accountDropdown;
	
	@FindBy(id = "dd-close")
	private WebElement clearAccountSelection;
	
	@FindBy(xpath = "//div[@class='inner-header' and text()='Sign In']")
	private WebElement signInText;
	
	@FindBy(xpath = "//button[@type='button' and contains(text(), 'CANCEL')]")
	private WebElement cancelButton;

	@FindBy(xpath = "//*[@class='mpulse-round-logo']")
	private WebElement mPulseRoundLogo;
	
	@FindBy(xpath = "//*[@class='mpulse-footer']")
	private WebElement mPulseFooter;
	
	@FindBy(className = "emailContainer")
	private WebElement email2faLink;
	
	@FindBy(id = "enter-code")
	private WebElement codeTextBox;
	
	@FindBy(xpath = "//button[@class='loggedInContainer']")
	private WebElement loggedInUser;
	
	@FindBy(xpath = "//button[@class='loggedInContainer']/div")
	private WebElement loggedInUserMessage;
	
	@FindBy(xpath = "//div[contains(text(),\"Oops, we couldn't find a phone number for your account.Please contact your supervisor to add a number to your account.\")]")
	private WebElement twofaErrorText;
	
	@FindBy(xpath = "//div[@role='alert']/div[text()='Please reset your password to continue using the service.']")
	private WebElement resetPasswordText;
	
	@FindBy(xpath = "//div[@role='alert']/div[text()='Your email and/or password was incorrect. Please try again']")
	private WebElement incorrectPasswordText;
	
	public AuthServiceLoginPage (String loginUrl, WebDriver driver) {
		this.url = loginUrl;
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
		wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	}
	
	public AuthServiceLoginPage (WebDriver driver) {
		this.url = ConfigReader.getConfig("ENV");
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
		wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	}
	
	public boolean isMpulseRoundLogoImageDisplay() {
		if(this.wait.until(ExpectedConditions.visibilityOf(this.mPulseRoundLogo)).isDisplayed()) {
			return true;
		} else {
			SysLogger.log("Error: mPulse Logo Image and Service Name is not displayed on Auth service login page");
			return false;
		}
	}
	
	public boolean isMpulseFooterDisplayed() {
		if(this.wait.until(ExpectedConditions.visibilityOf(this.mPulseFooter)).isDisplayed()) {
			return true;
		} else {
			SysLogger.log("Error: mPulse Footer Image is not displayed on Auth service login page");
			return false;
		}
	}
	
	public boolean isSignInTextDisplayed() {
		try {
			if(this.signInText.isDisplayed()) {
				return true;
			} else {
				SysLogger.log("Error: Sign In Text is not displayed on Auth service login page");
				return false;
			}
		} catch (Exception e) {
			SysLogger.log("Error: Sign In Text is not displayed on Auth service login page");
			return false;
		}
	}
	
	public boolean isResetPasswordTextDisplay() {
		try {
			if(this.resetPasswordText.isDisplayed()) {
				return true;
			} else {
				SysLogger.log("Error: Reset password Text is not displayed on Auth service login page");
				return false;
			}
		} catch (Exception e) {
			SysLogger.log("Error: Reset password Text is not displayed on Auth service login page");
			return false;
		}
	}
	
	public boolean isIncorrectPasswordTextDisplay() {
		try {
			if(this.incorrectPasswordText.isDisplayed()) {
				return true;
			} else {
				SysLogger.log("Error: Incorrect password Text is not displayed on Auth service login page");
				return false;
			}
		} catch (Exception e) {
			SysLogger.log("Error: Incorrect password Text is not displayed on Auth service login page");
			return false;
		}
	}
	
	public boolean isErrorTextPresent() throws InterruptedException {
		if(this.twofaErrorText.isDisplayed()) {
			return true;
		} else {
			SysLogger.log("Error: Error text not present");
			return false;
		}	
	}
	
	public void enterUserEmail(String userEmail) {
		if(this.wait.until(ExpectedConditions.visibilityOf(this.userEmail)).isDisplayed()) {
			this.userEmail.clear();
			this.userEmail.sendKeys(userEmail);
		} else {
			SysLogger.log("Error: Username text box is not displayed on Auth service login page");
		}
	}
	
	public void enterPassword(String password) {
		if(this.wait.until(ExpectedConditions.visibilityOf(this.password)).isDisplayed()) {
			this.password.clear();
			this.password.sendKeys(password);
		} else {
			SysLogger.log("Error: Password text box is not displayed on Auth service login page");
		}
	}
	
	public void enterToken(String token) {
		if(this.wait.until(ExpectedConditions.visibilityOf(this.codeTextBox)).isDisplayed()) {
			this.codeTextBox.clear();
			this.codeTextBox.sendKeys(token);
		} else {
			SysLogger.log("Error: Token text box is not displayed on Auth service login page");
		}
	}
	
	public void clickOnNextButtonForLogin() {
		if(this.wait.until(ExpectedConditions.visibilityOf(this.nextButton)).isDisplayed()) {
			this.nextButton.click();
			try {
				Thread.sleep(3000); // waiting for successfully login to Auth Service
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			SysLogger.log("Error: Next button is not displayed on Auth service login page");
		}
	}
	
	public void selectAccountFromDropDown(String accountName) throws InterruptedException {
		try {
			if(this.accountDropdown.isDisplayed()) {
				Select accountDrp = new Select(accountDropdown);
				accountDrp.selectByVisibleText(accountName);
			}
		} catch (Exception e) {
			SysLogger.log("Account dropdown page is not present, user is assigned to only one account");
		}
	}
	
	public void clickOnNextButtonOnAccountSelect() throws InterruptedException {
		if(this.wait.until(ExpectedConditions.visibilityOf(this.nextButton)).isDisplayed()) {
			this.nextButton.click();
			Thread.sleep(8000); // waiting for successfully login to CP
		} else {
			SysLogger.log("Error: Next button is not displayed on Auth service account selection page");
		}
	}
	
	public void clickOnFinalLoginButton() throws InterruptedException {
		if(this.wait.until(ExpectedConditions.visibilityOf(this.nextButton)).isDisplayed()) {
			this.nextButton.click();
			Thread.sleep(8000); // waiting for successfully login to CP
		} else {
			SysLogger.log("Error: Final Login button is not displayed on Auth service login page");
		}
	}
	
	public HomePage loginToCpByIPWhitelisting(String authUserEmail, String authPassword) throws InterruptedException {
		this.driver.navigate().to(this.url);
		try {
			Thread.sleep(5000); // waiting for redirect to Auth service
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String authUrl = ConfigReader.getConfig("auth_service_login_url");
		if (this.driver.getCurrentUrl().contains(this.url)) {
			HomePage homePage = new HomePage(this.driver);
			homePage.logOut();
			AuthServiceLogoutPage authLogOut = new AuthServiceLogoutPage(driver);
			if(driver.getTitle().equalsIgnoreCase("Logout Page") && driver.getCurrentUrl().contains("/logout")) {
				authLogOut.clickOnBackToLoginButton();
			}
			this.driver.navigate().to(this.url);
		}
		String currentUrl = this.driver.getCurrentUrl();
		SysLogger.log("current url is:"+currentUrl);
		SysLogger.log("auth url is:"+ authUrl);
		if (this.isMpulseRoundLogoImageDisplay() && currentUrl.equalsIgnoreCase(authUrl) && this.isMpulseFooterDisplayed()) {
			this.enterUserEmail(authUserEmail);
			this.enterPassword(authPassword);
			this.clickOnNextButtonForLogin();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (this.driver.getTitle().equalsIgnoreCase("Single SignOn")) {
				if (this.isResetPasswordTextDisplay() && this.isSignInTextDisplayed()) {
					testReporter.log(LogStatus.WARNING, "Your password is older than 90 days, Please reset it before login. Username - "+authUserEmail+", expired Password - "+authPassword);
					Assert.assertFalse(true, "Login Error: Your password is older than 90 days, Please reset it before login. Username - "+authUserEmail+", expired Password - "+authPassword);
				}
				
				if (this.isIncorrectPasswordTextDisplay() && this.isSignInTextDisplayed()) {
					testReporter.log(LogStatus.WARNING, "Your email and/or password was incorrect. Please try again. Username - "+authUserEmail+", expired Password - "+authPassword);
					Assert.assertFalse(true, "Login Error: Your email and/or password was incorrect. Please try again. Username - "+authUserEmail+", expired Password - "+authPassword);
				}
			}
			return new HomePage(this.driver);
		} else {
			return new HomePage(this.driver);
		}
	}
	
	public HomePage loginToCP(String authUserEmail, String authPassword) {
		String currentUrl = this.driver.getCurrentUrl();
		SysLogger.log("Current url is:"+currentUrl);
		if (this.isMpulseRoundLogoImageDisplay() && this.isSignInTextDisplayed() && this.isMpulseFooterDisplayed()) {
			this.enterUserEmail(authUserEmail);
			this.enterPassword(authPassword);
			this.clickOnNextButtonForLogin();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (this.driver.getTitle().equalsIgnoreCase("Single SignOn")) {
				if (this.isResetPasswordTextDisplay() && this.isSignInTextDisplayed()) {
					testReporter.log(LogStatus.WARNING, "Your password is older than 90 days, Please reset it before login. Username - "+authUserEmail+", expired Password - "+authPassword);
					Assert.assertFalse(true, "Login Error: Your password is older than 90 days, Please reset it before login. Username - "+authUserEmail+", expired Password - "+authPassword);
				}
				if (this.isIncorrectPasswordTextDisplay() && this.isSignInTextDisplayed()) {
					testReporter.log(LogStatus.WARNING, "Your email and/or password was incorrect. Please try again. Username - "+authUserEmail+", expired Password - "+authPassword);
					Assert.assertFalse(true, "Login Error: Your email and/or password was incorrect. Please try again. Username - "+authUserEmail+", expired Password - "+authPassword);
				}
			}
			return new HomePage(this.driver);
		} else {
			return new HomePage(this.driver);
		}
	}
	
	public HomePage loginToCpByEmail2fa(String authUserEmail, String authPassword) throws InterruptedException {
		this.driver.navigate().to(this.url);
		Thread.sleep(5000); // waiting for redirect to Auth service
		String authUrl = ConfigReader.getConfig("auth_service_login_url");
		if (this.driver.getCurrentUrl().contains(this.url)) {
			HomePage homePage = new HomePage(this.driver);
			homePage.logOut();
			AuthServiceLogoutPage authLogOut = new AuthServiceLogoutPage(driver);
			if(driver.getTitle().equalsIgnoreCase("Logout Page") && driver.getCurrentUrl().contains("/logout")) {
				authLogOut.clickOnBackToLoginButton();
			}
			this.driver.navigate().to(this.url);
		}
		String currentUrl = this.driver.getCurrentUrl();
		if (this.isMpulseRoundLogoImageDisplay() && currentUrl.equalsIgnoreCase(authUrl) && this.isMpulseFooterDisplayed()) {
			this.enterUserEmail(authUserEmail);
			this.enterPassword(authPassword);
			this.clickOnNextButtonForLogin();
			Thread.sleep(8000); // waiting for token to send to the email
			if (this.driver.getTitle().equalsIgnoreCase("Single SignOn") && this.isSignInTextDisplayed()) {
				if (this.isResetPasswordTextDisplay() && this.isSignInTextDisplayed()) {
					testReporter.log(LogStatus.WARNING, "Your password is older than 90 days, Please reset it before login. Username - "+authUserEmail+", expired Password - "+authPassword);
					Assert.assertFalse(true, "Login Error: Your password is older than 90 days, Please reset it before login. Username - "+authUserEmail+", expired Password - "+authPassword);
				}
				if (this.isIncorrectPasswordTextDisplay() && this.isSignInTextDisplayed()) {
					testReporter.log(LogStatus.WARNING, "Your email and/or password was incorrect. Please try again. Username - "+authUserEmail+", expired Password - "+authPassword);
					Assert.assertFalse(true, "Login Error: Your email and/or password was incorrect. Please try again. Username - "+authUserEmail+", expired Password - "+authPassword);
				}
			}
			this.enterToken(this.getTokenFromEmail(authUserEmail));
			this.clickOnFinalLoginButton();
			Thread.sleep(5000);
			return new HomePage(this.driver);
		} else {
			return new HomePage(this.driver);
		}
	}
	
	public String getTokenFromEmail(String emailId) throws InterruptedException {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		String token = "";
		try {
			((JavascriptExecutor)this.driver).executeScript("window.open()");
			tabs = new ArrayList<String>(driver.getWindowHandles());
			SysLogger.log("Total current running tabs "+tabs.size());
			this.driver.switchTo().window(tabs.get(1));
			token = UtilityMethods.getAuthToken(this.driver, emailId);
			this.driver.close();
		} catch (Exception e) {
			SysLogger.log("Error: Error occurred while switching to new window and getting auth token from email message");
			e.printStackTrace();
		}
		
		this.driver.switchTo().window(tabs.get(0));
		return token;
	}
	
	public AuthServiceLoginPage negativeLoginToCp2faMobile(String authUserEmail, String authPassword) throws InterruptedException {
		this.driver.navigate().to(this.url);
		Thread.sleep(5000); // waiting for redirect to Auth service
		String authUrl = ConfigReader.getConfig("auth_service_login_url");
		if (this.driver.getCurrentUrl().contains(this.url)) {
			HomePage homePage = new HomePage(this.driver);
			homePage.logOut();
			AuthServiceLogoutPage authLogOut = new AuthServiceLogoutPage(driver);
			if(driver.getTitle().equalsIgnoreCase("Logout Page") && driver.getCurrentUrl().contains("/logout")) {
				authLogOut.clickOnBackToLoginButton();
			}
			this.driver.navigate().to(this.url);
		}
		String currentUrl = this.driver.getCurrentUrl();
		if (this.isMpulseRoundLogoImageDisplay() && currentUrl.equalsIgnoreCase(authUrl) && this.isMpulseFooterDisplayed()) {
			this.enterUserEmail(authUserEmail);
			this.enterPassword(authPassword);
			this.clickOnNextButtonForLogin();
			
			return new AuthServiceLoginPage(this.driver);
		} else {
			return new AuthServiceLoginPage(this.driver);
		}
	}
}
