package com.application.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class EventHandler implements WebDriverEventListener {

	public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

		SysLogger.log("inside method afterChangeValueOf on " + arg0.toString());
	}

	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		SysLogger.log("inside method afterClickOn on " + arg0.toString());
	}

	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		// TODO Auto-generated method stub
		SysLogger.log("Find happened on " + arg1.toString() + " Using method " + arg0.toString());
	}

	public void afterNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub

		SysLogger.log("Inside the after navigateback to " + arg0.getCurrentUrl());
	}

	public void afterNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub
		SysLogger.log("Inside the afterNavigateForward to " + arg0.getCurrentUrl());
	}

	public void afterNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		SysLogger.log("Inside the afterNavigateTo to " + arg0);
	}

	public void afterScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		SysLogger.log("Inside the afterScript to, Script is " + arg0);
	}

	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

		SysLogger.log("Inside the beforeChangeValueOf method");
	}

	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		SysLogger.log("About to click on the " + arg0.toString());

	}

	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		// TODO Auto-generated method stub
		SysLogger.log("Just before finding element " + arg1.toString());

	}

	public void beforeNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub
		SysLogger.log("Just before beforeNavigateBack " + arg0.getCurrentUrl());

	}

	public void beforeNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub
		SysLogger.log("Just before beforeNavigateForward " + arg0.getCurrentUrl());

	}

	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		SysLogger.log("Just before beforeNavigateTo " + arg0);
	}

	public void beforeScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		SysLogger.log("Just before beforeScript " + arg0);
	}

	public void onException(Throwable arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		SysLogger.log("Exception occured at " + arg0.getMessage());

	}

	public void afterNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	public void beforeNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeGetText(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text) {
		// TODO Auto-generated method stub
		
	}

}
