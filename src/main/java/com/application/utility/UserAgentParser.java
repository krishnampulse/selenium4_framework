package com.application.utility;

import java.io.IOException;

public class UserAgentParser {

	private UserAgentParser() {}
	
	public static synchronized String getBrowserName(String userAgent) throws IOException {
			
		String  userAgentLower  =  userAgent.toLowerCase();
		
		String browser = "";
		
		if (userAgentLower.contains("chrome"))	{
			if(userAgentLower.contains("headlesschrome"))
				browser=(userAgent.substring(userAgent.indexOf("HeadlessChrome")).split(" ")[0]).replace("/", " ");
			else
				browser=(userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", " ");
            } 
		
		else if (userAgentLower.contains("safari") && userAgentLower.contains("version"))	{
            browser=(userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
            } 
		
		else if (userAgentLower.contains("firefox"))	{
        browser=(userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", " ");
        	}
		
		else if (userAgentLower.contains("msie"))	{
            String substring=userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
            browser=substring.split(" ")[0].replace("MSIE", "IE")+"-"+substring.split(" ")[1];            
            } 
						
		else	{
            browser = "UnKnown browser, More-Info: "+userAgent;
            }
		
		return browser;
		
	}
	
	public static synchronized String getOSName(String userAgent) throws IOException {
		
		String  userAgentLower  =  userAgent.toLowerCase();
		
		String os = "";
		
		if (userAgentLower.indexOf("linux") >= 0 )
        {
            os = "Linux";
        } 
		
		else if(userAgentLower.indexOf("mac") >= 0)	{
            os = "Mac";
        }
		
		else if(userAgentLower.indexOf("windows") >= 0)	{
            os = "Windows";
        }
		
		else if(userAgentLower.indexOf("x11") >= 0)	{
            os = "Unix";
        } 
		
		else if(userAgentLower.indexOf("android") >= 0)	{
            os = "Android";
        }
		
		else if(userAgentLower.indexOf("iphone") >= 0)	{
			os = "IPhone";
        }
		
		else	{
            os = "UnKnown OS, More-Info: "+userAgent;
        }
		
		return os;
	}
		
}
