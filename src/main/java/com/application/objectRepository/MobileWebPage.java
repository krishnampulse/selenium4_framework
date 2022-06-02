package com.application.objectRepository;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.security.auth.Subject;

import com.application.factories.ReporterFactory;
import com.application.utility.DriverMethods;
import com.application.utility.SysLogger;
import com.application.utility.UtilityMethods;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class MobileWebPage {
    WebDriver driver;
    WebDriverWait wait;
    ExtentTest testReporter;
	Actions act;
	JavascriptExecutor js;
        
    @FindBy(xpath = "//img[@alt='United Health Care' and contains(@src, 'mpulse-morpheus')]")
    WebElement uhcLogo;

    public MobileWebPage(WebDriver driver) {
        this.driver = driver;    
        wait = new WebDriverWait(driver, 120);
        act = new Actions(driver);
        js = (JavascriptExecutor) driver;
        testReporter = ReporterFactory.getTest();            
    }

    public UhcRefillPage goToRefillPage() {
        return new UhcRefillPage();
    }

    public UhcSurveyPage goToSurveyPage() {
        return new UhcSurveyPage();
    }
    
    public boolean isLogoPresent() {
        if (this.uhcLogo.isDisplayed()) {
            return true;
        } else
            return false;
    }

    public class UhcRefillPage{
        @FindBy (xpath = "//div[contains(@class, 'content')]/h3")
        private WebElement pageHeading;

        @FindBy (id = "datepicker")
        private WebElement dobTextField;

        @FindBy (xpath = "//div[@class='input-group-addon datepicker-box']")
        private WebElement calendarIcon;

        @FindBy (xpath = "//select[@class='ui-datepicker-year']")
        private WebElement datePickerYear;

        @FindBy (xpath = "//select[@class='ui-datepicker-month']")
        private WebElement datePickerMonth;

        @FindBy (xpath = "//div[@class='form-check']/input[@id='clickwrap']")
        private WebElement termsAndConditionsCheckbox;

        @FindBy (xpath = "//button[contains(@class, 'validateDOB')]")
        private WebElement validateDOBButton;

        @FindBy (id = "twofa_code")
        private WebElement twoFaCodeTextField;

        @FindBy (xpath = "//button[contains(@class, 'validate2FA')]")
        private WebElement validatetwoFaButton;

        @FindBy (xpath = "//img[contains(@src, 'phone_large.png')]")
        private WebElement callIcon;

        @FindBy (id = "delivery_pharmacy_phone")
        private WebElement pharmacyPhoneOne;

        @FindBy (id = "delivery_pharm_phone_2")
        private WebElement pharmacyPhoneTwo;

        public UhcRefillPage(){
            PageFactory.initElements(driver, this);
        }

        public boolean isPageHeadingPresent() {
            if (this.pageHeading.isDisplayed()) {
                return true;
            } else
                return false;
        }

        public boolean isdobTextFieldPresent() {
            if (this.dobTextField.isDisplayed()) {
                return true;
            } else
                return false;
        }

        public boolean isCalendarIconPresent() {
            if(wait.until(ExpectedConditions.visibilityOf(this.calendarIcon)).isDisplayed()) 
            {
                return true;
            }
            else return false;
        }

        public boolean isDatePickerYearPresent() {
            if (this.datePickerYear.isDisplayed()) {
                return true;
            } else
                return false;
        }

        public boolean isDatePickerMonthPresent() {
            if (this.datePickerMonth.isDisplayed()) {
                return true;
            } else
                return false;
        }

        public boolean isTermsAndConditionsCheckboxPresent() {
            if (this.termsAndConditionsCheckbox.isDisplayed()) {
                return true;
            } else
                return false;
        }

        public boolean isValidateDOBButtonPresent() {
            if (this.validateDOBButton.isDisplayed()) {
                return true;
            } else
                return false;
        }

        public boolean isTwoFaCodeTextFieldPresent() {
            if(wait.until(ExpectedConditions.visibilityOf(this.twoFaCodeTextField)).isDisplayed()) 
            {
                return true;
            }
            else return false;
        }

        public boolean isValidatetwoFaButtonPresent() {
            if (this.validatetwoFaButton.isDisplayed()) {
                return true;
            } else
                return false;
        }

        public boolean isCallIconOnSubmitScreenPresent() {
            if(wait.until(ExpectedConditions.visibilityOf(this.callIcon)).isDisplayed()) 
            {
                return true;
            }
            else return false;
        }

        public String returnPharmacyPhoneOne() {
            if (this.pharmacyPhoneOne.isDisplayed()) {
                return this.pharmacyPhoneOne.getText().trim();
            } else{
                SysLogger.log("Error: Pharmacy phone number 1 not present on UHC survey submission page");
                testReporter.log(LogStatus.INFO, "Error: Pharmacy phone number 1 not present on UHC survey submission page");
                return null;
            }
        }

        public String returnPharmacyPhoneTwo() {
            if (this.pharmacyPhoneTwo.isDisplayed()) {
                return this.pharmacyPhoneTwo.getText().trim();
            } else{
                SysLogger.log("Error: Pharmacy phone number 2 not present on UHC survey submission page");
                testReporter.log(LogStatus.INFO, "Error: Pharmacy phone number 2 not present on UHC survey submission page");
                return null;
            }
        }

        public String returnDrugAndPharmacyDetails(String pos) {
            WebElement element = driver.findElement(By.xpath("//div/p[" + pos + "][contains(@class, 'drug_info')]"));
            if (element.isDisplayed()) {
                return element.getText().trim();
            }
            else{
                SysLogger.log("Error: Drug and Pharmacy details not present on UHC survey submission page");
                testReporter.log(LogStatus.INFO, "Drug and Pharmacy details not present on UHC survey submission page");
                return null;
            }
        }

        public UhcRefillPage clickOnCalendarIcon() {
            if (this.isCalendarIconPresent()) {
                this.calendarIcon.click();
            } else {
                SysLogger.log("Error: Calendar Icon is not Displayed or not Enabled");
                testReporter.log(LogStatus.INFO, "Error: Calendar Icon is not Displayed or not Enabled");
            }
            return new UhcRefillPage();
        }

        public UhcRefillPage selectYearInDatePicker(String year){
            if(this.isDatePickerYearPresent()) {
                Select select = new Select(this.datePickerYear);
			    select.selectByValue(year);
            }
            else {
                SysLogger.log("Error : Year Date picker not present");
                testReporter.log(LogStatus.INFO, "Error : Year Date picker not present");
            }
            return new UhcRefillPage();
        }

        public UhcRefillPage selectMonthInDatePicker(String month){
            if(this.isDatePickerMonthPresent()) {
                Select select = new Select(this.datePickerMonth);
			    select.selectByValue(month);
            }
            else {
                SysLogger.log("Error: Month Date picker not present");
                testReporter.log(LogStatus.INFO, "Error: Month Date picker not present");
            }
            return new UhcRefillPage();
        }

        public UhcRefillPage selectDateInDatePicker(String date){
            WebElement element = driver.findElement(By.linkText(date));
            if (element.isDisplayed()) {
                element.click();
            }
            else{
                SysLogger.log("Error: Expected date not present in calendar view");
                testReporter.log(LogStatus.INFO, "Error: Expected date not present in calendar view");
            }
            return new UhcRefillPage();
        }

        public UhcRefillPage clickOnTermsAndConditionsCheckbox() {
            if (this.isTermsAndConditionsCheckboxPresent()) {
                this.termsAndConditionsCheckbox.click();
            } else {
                SysLogger.log("Error: Selected date in Date picker is not Displayed or not Enabled");
                testReporter.log(LogStatus.INFO, "Error: Selected date in Date picker checkbox is not Displayed or not Enabled");
            }
            return new UhcRefillPage();
        }

        public UhcRefillPage clickOnValidateDOBButton() {
            if (this.isValidateDOBButtonPresent()) {
                this.validateDOBButton.click();
            } else {
                SysLogger.log("Error: Validate DOB button is not Displayed or not Enabled");
                testReporter.log(LogStatus.INFO, "Error: Validate DOB button is not Displayed or not Enabled");
            }
            return new UhcRefillPage();
        }

        public UhcRefillPage enterTwoFaCode(String emailId) throws Exception {
            Thread.sleep(4000);
            if (this.isTwoFaCodeTextFieldPresent()) {
                this.twoFaCodeTextField.click();
                this.twoFaCodeTextField.clear();
                this.twoFaCodeTextField.sendKeys(this.getTwoFaCodeFromEmail(emailId));
            } else {
                SysLogger.log("Error: Text field to enter 2FA code is not present");
                testReporter.log(LogStatus.INFO, "Error: Text field to enter 2FA code is not present");
            }
            return new UhcRefillPage();
        }

        public String getTwoFaCodeFromEmail(String emailId) throws InterruptedException {
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			String emailHTML = "";
			try {
				((JavascriptExecutor)driver).executeScript("window.open()");
				tabs = new ArrayList<String>(driver.getWindowHandles());
				SysLogger.log("Total current running tabs "+tabs.size());
				driver.switchTo().window(tabs.get(1));
				emailHTML = UtilityMethods.getEmailMessageContent(driver, emailId, "Your authorization code");
				driver.close();
			} catch (Exception e) {
				SysLogger.log("Error: Error occurred while switching to new window and getting email message content");
				e.printStackTrace();
			}
            driver.switchTo().window(tabs.get(0));
            Assert.assertTrue(emailHTML.contains("CODE: "), "Error: Email Message content does not contain CODE");
            String twoFaCode = StringUtils.substringBetween(emailHTML, "CODE: ", "</").trim();
            SysLogger.log("2FA code is - " + twoFaCode);
            return twoFaCode;
        }
        
        public UhcRefillPage clickOnValidatetwoFaButton() {
            if (this.isValidatetwoFaButtonPresent()) {
                this.validatetwoFaButton.click();
            } else {
                SysLogger.log("Error: Validate 2FA button is not Displayed or not Enabled");
                testReporter.log(LogStatus.INFO, "Error: Validate 2FA button is not Displayed or not Enabled");
            }
            return new UhcRefillPage();
        } 
        
    }
    
    public class UhcSurveyPage{

        @FindBy (xpath = "//button[contains(@class, 'UHC-button')]")
        private WebElement surveySubmitButton;

        public UhcSurveyPage(){
            PageFactory.initElements(driver, this);
        }

        public boolean isSurveySubmitButtonPresent() {
            if (this.surveySubmitButton.isDisplayed()) {
                return true;
            } else
                return false;
        }

        public boolean verifySurveyPageHeader(String heading){
            WebElement element = driver.findElement(By.xpath("//h3[contains(text(), '" + heading + "')]"));
            if (element.isDisplayed()) {
                return true;
            }
            else
                return false;
        }

        public boolean verifySurveySubmitPageContent(String content){          
            WebElement element = driver.findElement(By.xpath("//h3[contains(text(),\""  + content + "\")]"));
            if (element.isDisplayed()) {
                return true;
            }
            else
                return false;
        }

        public UhcSurveyPage selectStopRefillingReason(String reason){
            WebElement element = driver.findElement(By.xpath("//div[@class='form-check']/label[contains(text(), '" + reason + "')]"));
            act.moveToElement(element).build().perform();
            if (element.isDisplayed()) {
                element.click();
            }
            else{
                SysLogger.log("Error: Refilling reason checkboxes not present in UHC survey landing page");
                testReporter.log(LogStatus.INFO, "Error: Refilling reason checkboxes in UHC survey landing page");
            }
            return new UhcSurveyPage();
        }

        public UhcSurveyPage clickOnSurveySubmitButton()  throws Exception{
            if (this.isSurveySubmitButtonPresent()) {
                this.surveySubmitButton.click();
            } else {
                SysLogger.log("Error: Survey Submit is not Displayed or not Enabled");
                testReporter.log(LogStatus.INFO, "Error: Survey Submit button is not Displayed or not Enabled");
            }
            return new UhcSurveyPage();
        }   
    }	
}
