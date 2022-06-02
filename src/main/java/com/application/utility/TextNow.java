package com.application.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TextNow {
	
	WebDriver driver;
	
	@FindBy (name = "username")
	private WebElement username;
	
	@FindBy (name = "password")
	private WebElement password;
	
	@FindBy (id = "btn-login")
	private WebElement loginButton;
	
	@FindBy (xpath = "//div[@id='messageView']//li[@class='chat-item incoming'][last()]//span[@class='message-text ']/span")
	private WebElement lastMessage;
	
	@FindBy (id = "text-input")
	private WebElement textInput;
	
	@FindBy (id = "send_button")
	private WebElement sendButton;
	
	public TextNow(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		driver.navigate().to("https://www.textnow.com");
	}
	
	private TextNow enterUsername(String user_name) {
		if(this.username.isDisplayed()) {
			this.username.click();
			this.username.sendKeys(user_name);
		}
		else {
			SysLogger.log("Error : username is not displayed on page");
		}
		return new TextNow(this.driver);
	}
	
	private TextNow enterPassword(String password) {
		if(this.password.isDisplayed()) {
			this.password.click();
			this.password.sendKeys(password);
		}
		else {
			SysLogger.log("Error : password is not displayed on page");
		}
		return new TextNow(this.driver);
	}
	
	private TextNow clickOnLoginButton() {
		if(this.loginButton.isDisplayed()) {
			this.loginButton.click();
		}
		else {
			SysLogger.log("Error : login button is not displayed on page");
		}
		return new TextNow(this.driver);
	}
	
	private TextNow goToShortcode(String short_code) {
		WebElement shortcode = this.driver.findElement(By.xpath("//*[@id='sidebar']//li[contains(@class, 'uikit-summary-list__cell')]//div[@class = 'chat-preview__contact-name']/span/div[text()='"+short_code+"']"));
		if(shortcode.isDisplayed()) {
			shortcode.click();
		}
		else {
			SysLogger.log("Error : shortcode is not displayed on page");
		}
		return new TextNow(this.driver);
	}
	
	private String getLastMessage() {
		if(this.lastMessage.isDisplayed()) {
			return this.lastMessage.getText();
		}
		else {
			SysLogger.log("Error : last message element is not displayed on page");
		}
		return null;
	}
	
	public String readLastMT(String username, String password, String shortcode) throws InterruptedException {
		Thread.sleep(15000);
		this.enterUsername(username);
		this.enterPassword(password);
		this.clickOnLoginButton();
		Thread.sleep(15000);
		this.goToShortcode(shortcode);
		return this.getLastMessage();
	}
}
