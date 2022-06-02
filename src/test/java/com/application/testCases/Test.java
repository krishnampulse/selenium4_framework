package com.application.testCases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.application.utility.ExcelDataProvider;
import com.application.utility.SysLogger;
import com.application.utility.ThreadSafeNumber;

public class Test {

	@DataProvider
	public Object[][] Authentication() throws Exception {

		Object[][] testObjArray = ExcelDataProvider.getCellData("audience_api_test_data.xlsx",
				"Sheet1");
		return (testObjArray);
	}
	
	@org.testng.annotations.Test(dataProvider="Authentication")
	 
    public void Registration_data(String sUserName,String sPassword, String testcases, String i, String xy)throws  Exception{			
 
        SysLogger.log(sUserName);
        SysLogger.log(sPassword);
        SysLogger.log(testcases);
        SysLogger.log(i);
        SysLogger.log(xy);
	}
}