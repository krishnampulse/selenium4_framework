package com.application.testCases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

import com.application.utility.SysLogger;

public class TestNGRunnerTest {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		TestNGRunnerTest testRun = new TestNGRunnerTest();
		testRun.runTestSuite("quick_api_prod_sanity.xml");
	}
	
	
	public void runTestSuite(String runXMLs) throws FileNotFoundException, IOException {
		SysLogger.log("xml files are - " + runXMLs);
		
		List<String> listOfRunXmls = new ArrayList<String>();
		for (String runXML : runXMLs.split(",")) {
			listOfRunXmls.add(System.getProperty("user.dir") + "/src/test/resources/" + runXML.trim());
		}
		
		TestNG testng = new TestNG();
		List<String> suites = new ArrayList<String>();
		for (String suite : listOfRunXmls) {
			suites.add(suite);
		}
		testng.setTestSuites(suites);
		testng.setUseDefaultListeners(false);
		testng.run();
	}
}
