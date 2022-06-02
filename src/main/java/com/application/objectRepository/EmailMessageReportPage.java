package com.application.objectRepository;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.application.factories.ReporterFactory;
import com.relevantcodes.extentreports.ExtentTest;

public class EmailMessageReportPage {
	WebDriver driver;
	ExtentTest testReporter;
	Actions act;
	JavascriptExecutor js;
	
	@FindBy(xpath = "//div[@id='email_engagement_trends']/div//li[3]/div/div/input")
	private WebElement totalClickCount;
	
	@FindBy(xpath = "//div[@id='email_link_trends']/div/ul/li")
	private List<WebElement> totalTrackedLink;
	
	@FindBy(xpath = "//div[@id='email_link_trends']/div/ul/li/p/input")
	private List<WebElement> totalTrackedDomain;

	public EmailMessageReportPage(WebDriver driver) {
		this.driver = driver;
		act = new Actions(driver);
		js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
		testReporter = ReporterFactory.getTest();
	}
	
	public int getTotalLinkClickCount() {
		return Integer.parseInt(totalClickCount.getAttribute("value"));
	}
	
	public int getTotalTrackedLinkCount() {
		return totalTrackedLink.size();
	}
	
	public List<String> getAllTrackedDomain() {
		List<String> allDomains = new ArrayList<String>();
		for (WebElement domain: totalTrackedDomain) {
			allDomains.add(domain.getAttribute("value"));
		}
		return allDomains;
	}
	
	public int getClickCountDomainWise(String domain) {
		WebElement element = this.driver.findElement(By.xpath("//div[@id='email_link_trends']/div/ul/li/p/input[@value='"+domain+"']/parent::p/parent::li/div/div/p/b"));
		return Integer.parseInt(element.getText());
	}
}
