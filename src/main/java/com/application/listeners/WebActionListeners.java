package com.application.listeners;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import com.application.utility.SysLogger;

public class WebActionListeners implements WebDriverListener {
	public void beforeClick(WebElement element) {
		SysLogger.log("Before clicking to Elemnet: '" + element.toString() + "'");
	}
	public void afterClick(WebElement element) {
		SysLogger.log("After clicking to Elemnet: '" + element.toString() + "'");
	}
	
	public void beforeFindElement(WebElement element, By locator) {
		SysLogger.log("Trying to find Element: '" + element.toString() + "' with locater - "+locator.toString());
	}
	public void afterFindElement(WebElement element, By locator, WebElement result) {
		SysLogger.log("Found Element: '" + element.toString() + "' with locater - '"+locator.toString());
	}
	
	public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
		SysLogger.log("Got error on Method - "+method.getName()+" with Exception - "+e.getMessage());
	}
}
