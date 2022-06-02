package com.application.testCases;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import com.application.factories.ApplicationDB;
import com.application.factories.ReporterFactory;
import com.application.utility.*;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.expect;

import java.util.Map;

public class TransactionalAPITest extends BaseTest {

	String sc = ConfigReader.getConfig("txn_sc");			
	String TxnAccountId = ConfigReader.getConfig("txn_account_id");
	
		
		@Test (groups = {"sanity","txnapi"})
		public void Verify_user_can_successfully_send_GSM_message_from_send_message_api()
				throws Exception {
			
			ExtentTest testReporter = ReporterFactory.getTest();
			testReporter.log(LogStatus.INFO, "Api test case started");
			testReporter.assignCategory("TRANSACTIONAL API");
			
			String dateTime = ApplicationDB.selectNow();
			String mobilePhone  = RandomeUtility.getRandomPhoneNumber();
			String encoding = "GSM";
			String referenceId = RandomeUtility.getRandomString();
			String message = "This is a test GSM message";
			
			Response r = TransactionalAPIs.sendMessage(mobilePhone, encoding, referenceId, message);
									
			String body = r.getBody().asString();
			SysLogger.log(body);
			Assert.assertEquals(r.statusCode(), 200);
			
			JsonPath jsonPathEvaluator = r.jsonPath();			
			Assert.assertEquals(jsonPathEvaluator.get("httpApiResponse.code").toString(), "100");
			Assert.assertEquals(jsonPathEvaluator.get("httpApiResponse.description").toString(), "Success. Message accepted for delivery to the following recipients.");
			Assert.assertEquals(jsonPathEvaluator.get("httpApiResponse.recipients.recipient.mobileNumber").toString(), mobilePhone );
			
			Map<String, String> smsMtLog = ApplicationDB.getResultMapFromTxnDb("select * from sms_mt_log where platform_account_id = "+TxnAccountId+" and created_on > '"+dateTime+"' order by id desc limit 1");
			Assert.assertEquals(smsMtLog.get("message").toString(), message);
					
		}
		
		@Test (groups = {"txnapi"})
		public void Verify_send_message_api_gives_401_if_basic_auth_is_missing()
				throws Exception {
			
			ExtentTest testReporter = ReporterFactory.getTest();
			testReporter.log(LogStatus.INFO, "Api test case started");
			testReporter.assignCategory("TRANSACTIONAL API");
			
			String mobilePhone  = RandomeUtility.getRandomPhoneNumber();
			String encoding = "GSM";
			String referenceId = RandomeUtility.getRandomString();
			String message = "This is a test GSM message";
			
			String baseURI = ConfigReader.getConfig("send_message_api_url");
			String jsonBody = "{\"mobilephone\": \""+mobilePhone+"\",\"encoding\": \""+encoding+"\",\"reference_id\": \""+referenceId+"\",\"message\": \""+message+"\"}";

			Response r = expect().statusCode(401).given().contentType("application/json")
					.body(jsonBody).log().all().when().post(baseURI);
			
			String body = r.getBody().asString();
			SysLogger.log(body);
			Assert.assertEquals(r.statusCode(), 401);
			
			JsonPath jsonPathEvaluator = r.jsonPath();
			Assert.assertEquals(jsonPathEvaluator.get("errors").toString(), "Unauthorized");
		
					
		}
		
		@Test (groups = {"txnapi"})
		public void Verify_send_message_api_gives_401_in_case_of_invalid_auth()
				throws Exception {
			
			ExtentTest testReporter = ReporterFactory.getTest();
			testReporter.log(LogStatus.INFO, "Api test case started");
			testReporter.assignCategory("TRANSACTIONAL API");
			
			String mobilePhone  = RandomeUtility.getRandomPhoneNumber();
			String encoding = "GSM";
			String referenceId = RandomeUtility.getRandomString();
			String message = "This is a test GSM message";
			
			String baseURI = ConfigReader.getConfig("send_message_api_url");
			String jsonBody = "{\"mobilephone\": \""+mobilePhone+"\",\"encoding\": \""+encoding+"\",\"reference_id\": \""+referenceId+"\",\"message\": \""+message+"\"}";

			Response r = expect().statusCode(401).given().contentType("application/json")
					.header("Authorization", UtilityMethods.getBasicAuth("abc","def"))
					.body(jsonBody).log().all().when().post(baseURI);
			
			String body = r.getBody().asString();
			SysLogger.log(body);
			Assert.assertEquals(r.statusCode(), 401);
			
			JsonPath jsonPathEvaluator = r.jsonPath();
			Assert.assertEquals(jsonPathEvaluator.get("errors").toString(), "Invalid Basic Auth");
		
					
		}
		
		@Test (groups = {"txnapi"})
		public void Verify_send_message_api_gives_400_in_case_of_invalid_request()
				throws Exception {
			
			ExtentTest testReporter = ReporterFactory.getTest();
			testReporter.log(LogStatus.INFO, "Api test case started");
			testReporter.assignCategory("TRANSACTIONAL API");
			
			String mobilePhone  = RandomeUtility.getRandomPhoneNumber();
			String encoding = "GSM";
			String referenceId = RandomeUtility.getRandomString();
			String message = "This is a test GSM message";
			
			String baseURI = ConfigReader.getConfig("send_message_api_url");
			String jsonBody = "{\"mobilephone\": "+mobilePhone+",\"encoding\": \""+encoding+"\",\"reference_id\": \""+referenceId+"\",\"message\": \""+message+"\"}";

			Response r = expect().statusCode(400).given().contentType("application/json")
					.header("Authorization", UtilityMethods.getBasicAuth(ConfigReader.getConfig("txn_username"), ConfigReader.getConfig("txn_password")))
					.body(jsonBody).log().all().when().post(baseURI);
			
			String body = r.getBody().asString();
			SysLogger.log(body);
			Assert.assertEquals(r.statusCode(), 400);
			
			JsonPath jsonPathEvaluator = r.jsonPath();
			Assert.assertEquals(jsonPathEvaluator.get("errors.json.mobilephone[0]").toString(), "Not a valid string.");
		
					
		}
		
		@Test (groups = {"sanity","txnapi"})
		public void Verify_user_can_send_MO_using_MO_API()
				throws Exception {
			
			ExtentTest testReporter = ReporterFactory.getTest();
			testReporter.log(LogStatus.INFO, "Api test case started");
			testReporter.assignCategory("TRANSACTIONAL API");
			
			String dateTime = ApplicationDB.selectNow();
			String mobilePhone  = RandomeUtility.getRandomPhoneNumber();
			String message = "This is a test MO";
			String messageId = RandomeUtility.getRandomString();
			
			Response r = TransactionalAPIs.sendMO(messageId, mobilePhone, this.sc, message, message);
						
			String body = r.getBody().asString();
			SysLogger.log(body);
			Assert.assertEquals(r.statusCode(), 200);
			
			JsonPath jsonPathEvaluator = r.jsonPath();
			Assert.assertEquals(jsonPathEvaluator.get("status").toString(), "Accepted");
		
			Map<String, String> smsMoLog = ApplicationDB.getResultMapFromTxnDb("select * from sms_mo_log where platform_account_id = "+TxnAccountId+" and received_on > '"+dateTime+"' order by id desc limit 1");
			Assert.assertEquals(smsMoLog.get("message").toString(), message);
		}
		
		@Test (groups = {"txnapi"})
		public void Verify_user_receive_MO_callback_on_sending_MO()
				throws Exception {
			
			ExtentTest testReporter = ReporterFactory.getTest();
			testReporter.log(LogStatus.INFO, "Api test case started");
			testReporter.assignCategory("TRANSACTIONAL API");
			
			String dateTime = ApplicationDB.selectNow();
			String mobilePhone  = RandomeUtility.getRandomPhoneNumber();
			String message = "This is MO for testing MO callback";
			String messageId = RandomeUtility.getRandomString();
			
			Response r = TransactionalAPIs.sendMO(messageId, mobilePhone, this.sc, message, message);

			String body = r.getBody().asString();
			SysLogger.log(body);
			Assert.assertEquals(r.statusCode(), 200);
			
			Map<String, String> accountDetail = ApplicationDB.getResultMapFromTxnDb("select id from account where platform_account_id = "+this.TxnAccountId+"");

			Map<String, String> callbackMessageLog = ApplicationDB.getResultMapFromTxnDb("select * from callback_message_log where callback_id = (select id from callback where type = 'MO' and account_id = "+accountDetail.get("id")+") and created_on > '"+dateTime+"' order by id desc limit 1");
			SysLogger.log("Data in callback Message Log is: "+callbackMessageLog);
			
			Assert.assertEquals(callbackMessageLog.get("response_code").toString(), "200");
			Assert.assertEquals(callbackMessageLog.get("response_body").toString(), "{\"success\":true}");
			Assert.assertEquals(callbackMessageLog.get("reason").toString(), "OK");			
					
		}
		
		@Test (groups = {"sanity","txnapi"})
		public void Verify_user_receive_DR_callback_on_hitting_DR_API_and_validate_referenceId()
				throws Exception {
			
			ExtentTest testReporter = ReporterFactory.getTest();
			testReporter.log(LogStatus.INFO, "Api test case started");
			testReporter.assignCategory("TRANSACTIONAL API");
			
			String dateTime = ApplicationDB.selectNow();
			String mobilePhone  = RandomeUtility.getRandomPhoneNumber();
			String encoding = "GSM";
			String referenceId = RandomeUtility.getRandomString();
			String message = "This is a test GSM message";
			
			TransactionalAPIs.sendMessage(mobilePhone, encoding, referenceId, message);
			Map<String, String> smsMtLog = ApplicationDB.getResultMapFromTxnDb("select aggregator_message_id, reference_id from sms_mt_log where mobile_phone = '"+mobilePhone+"'");
			
			Response r = TransactionalAPIs.sendDR(smsMtLog.get("aggregator_message_id"), mobilePhone, this.sc, "200", "Success", "1");
						
			String body = r.getBody().asString();
			SysLogger.log(body);
			Assert.assertEquals(r.statusCode(), 200);
			
			Map<String, String> accountDetail = ApplicationDB.getResultMapFromTxnDb("select id from account where platform_account_id = "+this.TxnAccountId+"");
			
			Map<String, String> callbackMessageLog = ApplicationDB.getResultMapFromTxnDb("select * from callback_message_log where callback_id = (select id from callback where type = 'DR' and account_id = "+accountDetail.get("id")+") and created_on > '"+dateTime+"' order by id desc limit 1");
			SysLogger.log("Data in callback Message Log is: "+callbackMessageLog);
			
			Assert.assertEquals(smsMtLog.get("reference_id").toString(), referenceId);
			Assert.assertEquals(callbackMessageLog.get("response_code").toString(), "200");
			Assert.assertEquals(callbackMessageLog.get("response_body").toString(), "{\"success\":true}");
			Assert.assertEquals(callbackMessageLog.get("reason").toString(), "OK");			
					
		}
		
		
		@Test (groups = {"txnapi"})
		public void Verify_send_message_api_errors_out_on_sending_blank_message()
				throws Exception {
			
			ExtentTest testReporter = ReporterFactory.getTest();
			testReporter.log(LogStatus.INFO, "Api test case started");
			testReporter.assignCategory("TRANSACTIONAL API");
			
			String mobilePhone  = RandomeUtility.getRandomPhoneNumber();
			String encoding = "GSM";
			String referenceId = RandomeUtility.getRandomString();
			String message = "";
			
			Response r = TransactionalAPIs.sendMessage(mobilePhone, encoding, referenceId, message);
			
			String body = r.getBody().asString();
			SysLogger.log(body);
			Assert.assertEquals(r.statusCode(), 200);
			
			JsonPath jsonPathEvaluator = r.jsonPath();
			Assert.assertEquals(jsonPathEvaluator.get("httpApiResponse.code").toString(), "992");
			Assert.assertEquals(jsonPathEvaluator.get("httpApiResponse.description").toString(), "Missing or empty parameter. body is required");
			
								
		}
		
		@Test (groups = {"txnapi"})
		public void Verify_send_message_api_accepts_phonenumber_without_countrycode()
				throws Exception {
						
			ExtentTest testReporter = ReporterFactory.getTest();
			testReporter.log(LogStatus.INFO, "Api test case started");
			testReporter.assignCategory("TRANSACTIONAL API");
			
			String dateTime = ApplicationDB.selectNow();
			String mobilePhone  = RandomeUtility.getRandomPhoneNumber();
			String new_mobilePhone = mobilePhone.substring(1);
			String encoding = "GSM";
			String referenceId = RandomeUtility.getRandomString();
			String message = "This is a test GSM message";
			
			Response r = TransactionalAPIs.sendMessage(new_mobilePhone, encoding, referenceId, message);
						
			String body = r.getBody().asString();
			SysLogger.log(body);
			Assert.assertEquals(r.statusCode(), 200);
			
			JsonPath jsonPathEvaluator = r.jsonPath();
			Assert.assertEquals(jsonPathEvaluator.get("httpApiResponse.code").toString(), "100");
			Assert.assertEquals(jsonPathEvaluator.get("httpApiResponse.description").toString(), "Success. Message accepted for delivery to the following recipients.");
			Assert.assertEquals(jsonPathEvaluator.get("httpApiResponse.recipients.recipient.mobileNumber").toString(), mobilePhone );
		
			Map<String, String> smsMtLog = ApplicationDB.getResultMapFromTxnDb("select * from sms_mt_log where platform_account_id = "+TxnAccountId+" and created_on > '"+dateTime+"' order by id desc limit 1");
			Assert.assertEquals(smsMtLog.get("message").toString(), message);

		}
		
		@Test (groups = {"txnapi"})
		public void Verify_send_message_api_errors_out_with_gsm_encoding_and_message_greater_than_160_chars()
				throws Exception {
			
			ExtentTest testReporter = ReporterFactory.getTest();
			testReporter.log(LogStatus.INFO, "Api test case started");
			testReporter.assignCategory("TRANSACTIONAL API");
			
			String mobilePhone  = RandomeUtility.getRandomPhoneNumber();
			String encoding = "GSM";
			String referenceId = RandomeUtility.getRandomString();
			String message = "This is a test GSM message with length greater than 160 characters. This message will not be sent. Just checking the negative case. API should give an error message.";
			
			Response r = TransactionalAPIs.sendMessage(mobilePhone, encoding, referenceId, message);
			
			String body = r.getBody().asString();
			SysLogger.log(body);
			Assert.assertEquals(r.statusCode(), 400);
			
			JsonPath jsonPathEvaluator = r.jsonPath();
			Assert.assertEquals(jsonPathEvaluator.get("errors.json[0]").toString(), "Message contains 165 characters. Only 160 are allowed for GSM encoding");
		}
		
		@Test (groups = {"txnapi"})
		public void Verify_send_message_api_errors_out_with_UCS2_encoding_and_message_greater_than_70_chars()
				throws Exception {
					
			ExtentTest testReporter = ReporterFactory.getTest();
			testReporter.log(LogStatus.INFO, "Api test case started");
			testReporter.assignCategory("TRANSACTIONAL API");
			
			String mobilePhone  = RandomeUtility.getRandomPhoneNumber();
			String encoding = "UCS-2";
			String referenceId = RandomeUtility.getRandomString();
			String message = "ucs msg â ë ÿƻ Smiling Face with Heart-Shaped Eyes within the Unicode Standard 😍";
			
			Response r = TransactionalAPIs.sendMessage(mobilePhone, encoding, referenceId, message);
			
			String body = r.getBody().asString();
			SysLogger.log(body);
			Assert.assertEquals(r.statusCode(), 400);
			
			JsonPath jsonPathEvaluator = r.jsonPath();
			Assert.assertEquals(jsonPathEvaluator.get("errors.json[0]").toString(), "Message contains 80 characters. Only 70 are allowed for UCS-2 encoding");		
		}
				
		@Test (groups = {"txnapi"})
		public void Verify_user_can_successfully_send_UCS2_message_from_send_message_api()
				throws Exception {
				
			ExtentTest testReporter = ReporterFactory.getTest();
			testReporter.log(LogStatus.INFO, "Api test case started");
			testReporter.assignCategory("TRANSACTIONAL API");
			
			String dateTime = ApplicationDB.selectNow();
			String mobilePhone  = RandomeUtility.getRandomPhoneNumber();
			String encoding = "UCS-2";
			String referenceId = RandomeUtility.getRandomString();
			String message = "âëÿƻ Smiling Face with Heart-Shaped Eyes within the Unicode Standard 😍";
			
			Response r = TransactionalAPIs.sendMessage(mobilePhone, encoding, referenceId, message);
						
			String body = r.getBody().asString();
			SysLogger.log(body);
			Assert.assertEquals(r.statusCode(), 200);
			
			JsonPath jsonPathEvaluator = r.jsonPath();
			Assert.assertEquals(jsonPathEvaluator.get("httpApiResponse.code").toString(), "100");
			Assert.assertEquals(jsonPathEvaluator.get("httpApiResponse.description").toString(), "Success. Message accepted for delivery to the following recipients.");
			Assert.assertEquals(jsonPathEvaluator.get("httpApiResponse.recipients.recipient.mobileNumber").toString(), mobilePhone );
		
			Map<String, String> smsMtLog = ApplicationDB.getResultMapFromTxnDb("select * from sms_mt_log where platform_account_id = "+TxnAccountId+" and created_on > '"+dateTime+"' order by id desc limit 1");
			Assert.assertEquals(smsMtLog.get("message").toString(), message);

		}
		
		
		
}
