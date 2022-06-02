package com.application.utility;

import static io.restassured.RestAssured.expect;

import java.util.Map;

import io.restassured.response.Response;

	public class TransactionalAPIs {
	
	
		public static synchronized Response addAccount(String username, String password, String accountId) {
			
		String baseURI = ConfigReader.getConfig("create_account_url");
		Response r = expect().given()
				  .contentType("multipart/form-data")
		            .multiPart("username", username)
		            .multiPart("password", password)
		            .multiPart("retype_password", password)
		            .multiPart("platform_account_id", accountId)
				.log().all().when().post(baseURI);
		
		return r;
	}
				
		
		public static synchronized Response sendMessage(String mobilephone, String encoding, String referenceId, String message) {
			
			String baseURI = ConfigReader.getConfig("send_message_api_url");
			String jsonBody = "{\"mobilephone\": \""+mobilephone+"\",\"encoding\": \""+encoding+"\",\"reference_id\": \""+referenceId+"\",\"message\": \""+message+"\"}";

			Response r = expect().given().contentType("application/json")
					.header("Authorization", UtilityMethods.getBasicAuth(ConfigReader.getConfig("txn_username"), ConfigReader.getConfig("txn_password")))
					.body(jsonBody).log().all().when().post(baseURI);
			
			return r;
		}
		
		public static synchronized Response sendMO(String message_id, String mobilePhone, String shortcode, String originalMessage, String message) {
			
			String baseURI = ConfigReader.getConfig("mo_api_url");
			Response r = expect().given()
					  .contentType("multipart/form-data")
			            .multiPart("message_id", message_id)
			            .multiPart("device_address", mobilePhone)
			            .multiPart("inbound_address", shortcode)
			            .multiPart("message_orig", originalMessage)
			            .multiPart("message", message)
					.log().all().when().post(baseURI);
			
			return r;
		}
		
		
		public static synchronized Response sendDR(String message_id, String mobilePhone, String shortcode, String status, String statusInfo, String statusCode) {
			
			String baseURI = ConfigReader.getConfig("dr_api_url");
			Response r = expect().given()
					  .contentType("multipart/form-data")
			            .multiPart("device_address", mobilePhone)
			            .multiPart("message_id", message_id)
			            .multiPart("inbound_address", shortcode)
			            .multiPart("status", status)
			            .multiPart("status_info", statusInfo)
			            .multiPart("status_code", statusCode)
					.log().all().when().post(baseURI);
			
			return r;
		}
		
		
	}
