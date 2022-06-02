package com.application.utility;


public class Authorization {

	private String auth;
	
	public Authorization(String auth) {
		
		this.auth=auth;
		
	}
	
	public String getHeader() {
		
		if((this.auth).equalsIgnoreCase("Basic")) {
			return "Authorization";
		}
		if((this.auth).equalsIgnoreCase("oAuth")) {
			return "Access-Token";
		}
		if((this.auth).equalsIgnoreCase("SSO")) {
			return "sso-access-token";
		}
		
		else {
			SysLogger.log("No valid header match found");
			return null;
		}
	}
	
	public String getAuth() {
		
		if(this.auth.equalsIgnoreCase("Basic")){
			return UtilityMethods.getBasicAuth(ConfigReader.getConfig("api_username"),
					ConfigReader.getConfig("api_password"));
		}
		
		if(this.auth.equalsIgnoreCase("oAuth")){
			
			return UtilityMethods.getoAuth();
		}
		
		if(this.auth.equalsIgnoreCase("SSO")){
			return UtilityMethods.authTokenIpWhiteList();
		}
		else {
			SysLogger.log("No valid auth token found");
			return null;
		}	
	}	
	
}