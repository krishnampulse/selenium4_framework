package com.application.utility;

import org.apache.commons.lang3.RandomStringUtils;

import com.application.factories.ReporterFactory;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class RandomeUtility {
	
	public static String getRandomEmails(int length) {
		ExtentTest testReporter = ReporterFactory.getTest();
		String email = RandomStringUtils.randomAlphanumeric(length).toLowerCase()+"@mailinator.com";
		testReporter.log(LogStatus.INFO, "Using Email - "+email);
		return email;
	}
	
	public static String getRandomString() {
		return "kn"+RandomStringUtils.randomAlphanumeric(6).toLowerCase();
	}
	
	public static String getRandomEventName() {
		return "event_"+RandomStringUtils.randomAlphanumeric(6).toLowerCase();
	}
	
	public static String getRandomEventAttributeName() {
		return "attri_"+RandomStringUtils.randomAlphanumeric(6).toLowerCase();
	}
	
	public static synchronized String getRandomPhoneNumber() {
		ExtentTest testReporter = ReporterFactory.getTest();
		String phone;
		if (ConfigReader.getConfig("allowRandomPhone").equalsIgnoreCase("true")) {
			Random rand = new Random();
			int num = rand.nextInt(90000000) + 10000000;
			phone = "199"+num;
			testReporter.log(LogStatus.INFO, "Using Phone number - "+phone);
			return phone;
		}
		else {
			phone = ThreadSafeNumber.getRandomPhone();
			testReporter.log(LogStatus.INFO, "Using Phone number - "+phone);
			return phone;
		}
	}
	
	public static String getRandomNumber() {
		Random rand = new Random();
		int num = rand.nextInt(900) + 100;
		String number = "1"+num;
		return number;
	}
	
	public static String getRandomAppMemberID() {
		ExtentTest testReporter = ReporterFactory.getTest();
		String appMemberId = RandomStringUtils.randomAlphanumeric(6).toLowerCase()+"app";
		testReporter.log(LogStatus.INFO, "Using appMemberId - "+appMemberId);
		return appMemberId;
	}
	
	public static String getRandomClientMemberID() {
		return RandomStringUtils.randomAlphanumeric(6).toLowerCase()+"client";
	}
	
	public static String getNewAccountName() {
		return "Test_Automation_account_"+getRandomString();
	}
}
