package com.application.utility;

import static io.restassured.RestAssured.expect;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import com.application.factories.ApplicationDB;
import com.application.factories.BrowserFactory;
import com.application.objectRepository.HomePage;

public class UtilityMethods {

	public static synchronized String getScreenShot(String ScreenShotName) {
		WebDriver driver = BrowserFactory.getBrowser();
		String fileName = (new Date()).toString().replace(" ", "_").replace(":", "-").trim() + ".png";
		String destinationFilePath = "./ScreenShots/" + ScreenShotName + "_" + fileName;
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File(destinationFilePath));
		} catch (Exception e) {
			SysLogger.log("Exception while taking screen shot " + e.getMessage());
		}
		SysLogger.log("Screen shot taken");
		return destinationFilePath;
	}

	public static synchronized String getCsvFilePath(String fileName) {
		return System.getProperty("user.dir") + "/src/test/resources/testData" + "/" + fileName + ".csv";
	}

	public static synchronized Double getPercentage(int count, int total) {
		Double toBeTruncated = ((count * 100.0) / (total * 1.0));
		SysLogger.log("un truncated " + toBeTruncated);
		@SuppressWarnings("deprecation")
		Double truncatedDouble = new BigDecimal(toBeTruncated).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
		return truncatedDouble;
	}

	public static HomePage createSegment(WebDriver driver, String segmentName) throws InterruptedException {

		HomePage home = new HomePage(driver);
		home.goToAudienceListPage().goToSegmentsPage().goToDefineSegmentPage().enterSegmentName(segmentName)
				.clickOnMemberTab().selectFieldName("Address2").selectRuleByVisibleText("Equal To")
				.enterValueInputTextBox("kanpur").clickOnAddCriteriaLink().selectSecondFieldName("custom number")
				.selectSecondRuleByVisibleText("Equal To").enterSecondValueInputTextBox("789").clickOnSubmitButton()
				.goToHomePage();
		return new HomePage(driver);
	}

	public static synchronized String getNewSegmentName() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		return (new Date()).toString().replace(" ", "").replace(":", "").trim().substring(0, 14) + "_seg";
	}

	public static synchronized String getDate_YYYY_MM_DD(int delaydate) {
		Date dNow = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(dNow);
		cal.add(Calendar.DAY_OF_MONTH, delaydate);
		dNow = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sdf.format(dNow);
		return strDate;
	}

	public static synchronized String getISTDate_YYYY_MM_DD(int delaydate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("IST"));
		Date dNow = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(dNow);
		cal.add(Calendar.DAY_OF_MONTH, delaydate);
		dNow = cal.getTime();
		String strDate = sdf.format(dNow);
		return strDate;
	}

	public static synchronized String getTimeWithDelay(int delayTimeInMinutes) {
		Date dNow = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(dNow);
		cal.add(Calendar.MINUTE, delayTimeInMinutes);
		dNow = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String strDate = sdf.format(dNow);
		return strDate;
	}

	public static synchronized String getISTTimeWithDelay_HH_mm(int delayTimeInMinutes) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		sdf.setTimeZone(TimeZone.getTimeZone("IST"));
		Date dNow = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(dNow);
		cal.add(Calendar.MINUTE, delayTimeInMinutes);
		dNow = cal.getTime();
		String strDate = sdf.format(dNow);
		return strDate;
	}

	public static synchronized String getISTTimeWithDelay(int delayTimeInMinutes) {
		SimpleDateFormat sd = new SimpleDateFormat("hh:mma");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, delayTimeInMinutes);
		date = cal.getTime();
		sd.setTimeZone(TimeZone.getTimeZone("IST"));
		SysLogger.log("Time is - " + sd.format(date));
		return sd.format(date);
	}

	public static synchronized String getISTDateWithDelay(int delaydate) {
		SimpleDateFormat sd = new SimpleDateFormat("MMM-d-yyyy");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, delaydate);
		date = cal.getTime();
		sd.setTimeZone(TimeZone.getTimeZone("IST"));
		return sd.format(date);
	}

	public static synchronized String getCurrentTime() {
		SimpleDateFormat sd = new SimpleDateFormat("hh:mm:ss");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return sd.format(date);
	}

	public static synchronized long getTimeDifferenceInSeconds(String startTime, String endTime) throws ParseException {
		SimpleDateFormat sd = new SimpleDateFormat("hh:mm:ss");
		Date d1 = sd.parse(startTime);
		Date d2 = sd.parse(endTime);
		return TimeUnit.MILLISECONDS.toSeconds(d2.getTime() - d1.getTime());
	}

	public static synchronized String getNewCampaignName() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		return "Camp_" + (new Date()).toString().replace(" ", "_").replace(":", "").trim().substring(0, 17);
	}

	public static synchronized String getNewDialogueName() {
		return "Dialogue_" + (new Date()).toString().replace(" ", "_").replace(":", "").trim().substring(0, 17);
	}

	public static synchronized String getNewMessageName() {
		return "Message_" + (new Date()).toString().replace(" ", "_").replace(":", "").trim().substring(0, 17);
	}

	public static synchronized void openEmailMessageInMailinator(WebDriver driver, String email)
			throws InterruptedException {

		email = email.substring(0, email.indexOf('@'));
		SysLogger.log("Email is -- " + email);
		driver.navigate().to("https://www.mailinator.com/v3/index.jsp?zone=public&query=" + email + "#/#inboxpane");
		Thread.sleep(5000);
		driver.findElement(By.xpath(
				"//div[@id='inboxpane']//table[@class='table table-striped jambo_table']//td/a[contains(text(), 'Welcome')]"))
				.click();
		driver.switchTo().frame("msg_body");
	}

	public static synchronized void clickOnConfirmLinkInEmailMailinator(WebDriver driver, String email)
			throws InterruptedException {

		email = email.substring(0, email.indexOf('@'));
		SysLogger.log("Email is -- " + email);
		driver.navigate().to("https://www.mailinator.com/v3/index.jsp?zone=public&query=" + email + "#/#inboxpane");
		Thread.sleep(5000);
		driver.findElement(By.xpath(
				"//div[@id='inboxpane']//table[@class='table table-striped jambo_table']//td/a[contains(text(), 'Your confirmation')]"))
				.click();
		driver.switchTo().frame("msg_body");
		Thread.sleep(5000);
		String text = driver.findElement(By.xpath("html/body/a[1]")).getAttribute("href");
		SysLogger.log("The url is - " + text);
		driver.navigate().to(text);

	}

	public static synchronized boolean is_CsvUploadSuccessMessage_InEmailMailinator(WebDriver driver, String email)
			throws InterruptedException {
		email = email.substring(0, email.indexOf('@'));
		SysLogger.log("Email is -- " + email);
		driver.navigate().to("https://www.mailinator.com/v3/index.jsp?zone=public&query=" + email + "#/#inboxpane");
		Thread.sleep(5000);
		driver.findElement(By.xpath(
				"//div[@id='inboxpane']//table[@class='table table-striped jambo_table']//td/a[contains(text(), 'Import result of list')]"))
				.click();
		driver.switchTo().frame("msg_body");
		Thread.sleep(5000);

		if (driver.getPageSource().contains("Import csv succeed")) {
			return true;
		} else {
			return false;
		}
	}

	public static synchronized void clickOnUnsubscribeLinkInEmailMailinator(WebDriver driver, String email)
			throws InterruptedException {

		email = email.substring(0, email.indexOf('@'));
		SysLogger.log("Email is -- " + email);
		driver.navigate().to("https://www.mailinator.com/v3/index.jsp?zone=public&query=" + email + "#/#inboxpane");
		Thread.sleep(5000);
		driver.findElement(By.xpath(
				"//div[@id='inboxpane']//table[@class='table table-striped jambo_table']//td/a[contains(text(), 'Welcome')]"))
				.click();
		driver.switchTo().frame("msg_body");
		Thread.sleep(5000);
		String text = driver.findElement(By.xpath("html/body/a[contains(text(), 'Unsubscribe')]")).getAttribute("href");
		SysLogger.log("The url is - " + text);
		driver.navigate().to(text);

	}

	public static synchronized String getSecureMessageLinkFromMailinatorEmail(WebDriver driver, String email)
			throws InterruptedException {

		email = email.substring(0, email.indexOf('@'));
		SysLogger.log("Email is -- " + email);
		driver.navigate().to("https://www.mailinator.com/v3/index.jsp?zone=public&query=" + email + "#/#inboxpane");
		Thread.sleep(5000);
		driver.findElement(By.xpath(
				"//div[@id='inboxpane']//table[@class='table table-striped jambo_table']//td/a[contains(text(), 'email with secure message link')]"))
				.click();
		driver.switchTo().frame("msg_body");
		Thread.sleep(5000);
		String text = driver.findElement(By.xpath("html/body/a[1]")).getAttribute("href").trim();
		SysLogger.log("The secure message url is - " + text);
		return text;
	}

	public static synchronized String getSubPrefLinkFromMailinatorEmail(WebDriver driver, String email)
			throws InterruptedException {

		email = email.substring(0, email.indexOf('@'));
		SysLogger.log("Email is -- " + email);
		driver.navigate().to("https://www.mailinator.com/v3/index.jsp?zone=public&query=" + email + "#/#inboxpane");
		Thread.sleep(5000);
		driver.findElement(By.xpath(
				"//div[@id='inboxpane']//table[@class='table table-striped jambo_table']//td/a[contains(text(), 'default email message with sub pref link')]"))
				.click();
		driver.switchTo().frame("msg_body");
		Thread.sleep(5000);
		String text = driver.findElement(By.xpath("html/body/a[1]")).getAttribute("href").trim();
		SysLogger.log("The sub preference url is - " + text);
		return text;
	}

	public static synchronized String getBasicAuth(String username, String password) {
		return "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
	}

	public static synchronized String getNewMemberFirstName() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		return (new Date()).toString().replace(" ", "").replace(":", "").trim().substring(0, 14) + "_fname";
	}

	public static synchronized File getTheNewestFile(String filePath, String ext) {
		File theNewestFile = null;
		File dir = new File(filePath);
		FileFilter fileFilter = new WildcardFileFilter("*." + ext);
		File[] files = dir.listFiles(fileFilter);

		if (files.length > 0) {
			/** The newest file comes first **/
			Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			theNewestFile = files[0];
		}
		return theNewestFile;
	}

	public static synchronized List<String> getAllCSVColumnsValueByHeaderName(File file, String headerName)
			throws Exception {
		List<String> values = new ArrayList<String>();
		try (Reader reader = new FileReader(file);
				CSVParser csvParser = new CSVParser(reader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
			for (CSVRecord csvRecord : csvParser) {
				// Accessing values by Header names
				String value = csvRecord.get(headerName).trim().toString();
				if (!value.isEmpty()) {
					values.add(value);
				}
			}
		}
		return values;
	}

	public static synchronized void generateCsvFileByGivenHeaderName(String csvFilePath, String campaign_name,
			String message_name, String message_body, String is_track_click_through, String schedule_on)
			throws Exception {

		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(csvFilePath));

				CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("campaign_name",
						"message_name", "message_body", "is_track_click_through", "schedule_on"));) {
			csvPrinter.printRecord(campaign_name, message_name, message_body, is_track_click_through, schedule_on);
			csvPrinter.flush();
		}
	}

	public static synchronized boolean isCsvDownloadedFromExportCsvLinkInmailinatorValid(WebDriver driver, String email)
			throws InterruptedException {
        
		email = email.substring(0, email.indexOf('@'));
		SysLogger.log("Email is -- " + email);
		String fileDownloadpath = System.getProperty("user.dir") + "/downloads";
		driver.navigate().to("https://www.mailinator.com/v3/index.jsp?zone=public&query=" + email + "#/#inboxpane");
		Thread.sleep(5000);
		driver.findElement(By.xpath(
				"//div[@id='inboxpane']//table[@class='table table-striped jambo_table']//td/a[contains(text(), 'Members Export CSV ready to be downloaded')]"))
				.click();
		driver.switchTo().frame("msg_body");
		Thread.sleep(5000);
		WebElement csvFileLink = driver.findElement(By.xpath("html/body/a[1]"));
		csvFileLink.click();
		Thread.sleep(5000);
		String fileName = csvFileLink.getText();
		String month = fileName.substring(17, 19);
		String date = fileName.substring(20, 22);
		String year = fileName.substring(23, 27);
		String hour = fileName.substring(28, 30);
		String minute = fileName.substring(31, 33);
		fileName = "mPulseAutomation_" + year + '-' + month + '-' + date + ' ' + hour + '_' + minute;
		boolean flag = false;

		File directory = new File(fileDownloadpath);
		File[] content = directory.listFiles();
		for (int i = 0; i < content.length; i++) {
			if (content[i].getName().contains(fileName))
				return flag = true;
		}

		return flag;
	}

	public static synchronized boolean isUserGetValidFromAddressActivationLink(WebDriver driver, String email)
			throws InterruptedException {

		email = email.substring(0, email.indexOf('@'));
		SysLogger.log("Email is -- " + email);
		driver.navigate().to("https://www.mailinator.com/v3/index.jsp?zone=public&query=" + email + "#/#inboxpane");
		Thread.sleep(5000);
		driver.findElement(By.xpath(
				"//div[@id='inboxpane']//table[@class='table table-striped jambo_table']//td/a[contains(text(), 'Please confirm this email address as a \"from address\"')]"))
				.click();
		driver.switchTo().frame("msg_body");
		Thread.sleep(5000);
		String text = driver.findElement(By.xpath("html/body/a[1]")).getAttribute("href");
		SysLogger.log("Address activation link is - " + text);
		driver.navigate().to(text);
		Thread.sleep(5000);
		if (driver.getPageSource().contains("added as a \"from\" email address")) {
			return true;
		} else {
			return false;
		}
	    
	}
	public static synchronized String getAuthToken(WebDriver driver, String email) throws InterruptedException {
		email = email.substring(0, email.indexOf('@'));
		SysLogger.log("Email is -- " + email);
		driver.navigate().to("https://www.mailinator.com/v3/index.jsp?zone=public&query=" + email + "#/#inboxpane");
		Thread.sleep(3000);
		driver.findElement(By.xpath(
				"//div[@id='inboxpane']//table[@class='table table-striped jambo_table']/tbody/tr[1]/td[4]/a[contains(text(), 'Your authorization code')]"))
				.click();
		driver.switchTo().frame("msg_body");
		Thread.sleep(3000);
		String emailText = driver.findElement(By.xpath("/html/body")).getText().trim();
		SysLogger.log("Email content - " + emailText);
		String token = emailText.substring(emailText.lastIndexOf(" ") + 1);
		SysLogger.log("Member token is - " + token);
		return token;
	}

	public static synchronized String getEmailMessageContent(WebDriver driver, String email, String subject)
			throws InterruptedException {
		email = email.substring(0, email.indexOf('@'));
		SysLogger.log("Email is -- " + email);
		driver.navigate().to("https://www.mailinator.com/v3/index.jsp?zone=public&query=" + email + "#/#inboxpane");
		Thread.sleep(3000);
		driver.findElement(By.xpath(
				"//div[@id='inboxpane']//table[@class='table table-striped jambo_table']//td/a[contains(text(), '"
						+ subject + "')]"))
				.click();
		driver.switchTo().frame("msg_body");
		Thread.sleep(3000);
		return driver.findElement(By.xpath("/html/body[@marginheight]")).getAttribute("innerHTML").trim();
	}

	public static synchronized ArrayList<String> getEmailMessageContentAndLink(WebDriver driver, String email,
			String subject) throws InterruptedException {
		email = email.substring(0, email.indexOf('@'));
		SysLogger.log("Email is -- " + email);
		driver.navigate().to("https://www.mailinator.com/v3/index.jsp?zone=public&query=" + email + "#/#inboxpane");
		Thread.sleep(3000);
		driver.findElement(By.xpath(
				"//div[@id='inboxpane']//table[@class='table table-striped jambo_table']//td/a[contains(text(), '"
						+ subject + "')]"))
				.click();
		driver.switchTo().frame("msg_body");
		Thread.sleep(3000);
		String emailText = driver.findElement(By.xpath("/html/body/p")).getText().trim();
		ArrayList<String> result = new ArrayList<String>();
		result.add(emailText);
		String text = driver.findElement(By.xpath("/html/body/p/a[1]")).getAttribute("href").trim();
		result.add(text);
		return result;
	}

	public static synchronized Map<String, String> getEmailMessage(WebDriver driver, String email, String subject)
			throws InterruptedException {
		email = email.substring(0, email.indexOf('@'));
		SysLogger.log("Email is -- " + email);
		driver.navigate().to("https://www.mailinator.com/v3/index.jsp?zone=public&query=" + email + "#/#inboxpane");
		Thread.sleep(3000);
		String fromName = driver.findElement(By.xpath(
				"//div[@id='inboxpane']//table[@class='table table-striped jambo_table']//td[@class='ng-binding'][2]"))
				.getText();
		driver.findElement(By.xpath(
				"//div[@id='inboxpane']//table[@class='table table-striped jambo_table']//td/a[contains(text(), '"
						+ subject + "')]"))
				.click();
		Thread.sleep(3000);
		String toAddress = driver.findElement(By.xpath("//div[@id='msgpane']//table/tbody/tr[2]/td/b")).getText()
				.trim();
		String fromAddress = driver.findElement(By.xpath("//div[@id='msgpane']//table/tbody/tr[3]/td/b")).getText()
				.trim();
		String ipAddress = driver.findElement(By.xpath("//div[@id='msgpane']//table/tbody/tr[5]/td/b")).getText()
				.trim();
		driver.switchTo().frame("msg_body");
		Thread.sleep(3000);
		String emailText = "";
		try {
			emailText = driver.findElement(By.xpath("/html/body/p")).getText().trim();
		} catch (Exception e1) {
		}
		if (emailText == "") {
			emailText = driver.findElement(By.xpath("/html/body[@marginheight]")).getAttribute("innerHTML").trim()
					.replace("<br>", "").trim();
		}
		String link = "";
		try {
			link = driver.findElement(By.xpath("/html/body/a[1]")).getAttribute("href").trim();
		} catch (Exception e) {
		}
		Map<String, String> data = new HashMap<String, String>();
		data.put("to_address", toAddress + "@mailinator.com");
		data.put("from_address", fromAddress);
		data.put("ip_address", ipAddress);
		data.put("from_name", fromName);
		data.put("email_text", emailText);
		data.put("link", link);
		return data;
	}

	public static synchronized String geFirstLinkFromEmailMessage(WebDriver driver, String email, String subject)
			throws InterruptedException {

		email = email.substring(0, email.indexOf('@'));
		SysLogger.log("Email is -- " + email);
		driver.navigate().to("https://www.mailinator.com/v3/index.jsp?zone=public&query=" + email + "#/#inboxpane");
		Thread.sleep(3000);
		driver.findElement(By.xpath(
				"//div[@id='inboxpane']//table[@class='table table-striped jambo_table']//td/a[contains(text(), '"
						+ subject + "')]"))
				.click();
		driver.switchTo().frame("msg_body");
		Thread.sleep(3000);
		String link = driver.findElement(By.xpath("/html/body/a[1]")).getAttribute("href").trim();
		return link;
	}

	public static synchronized boolean isEmailMessagePresentWithSubjectNameInInbox(WebDriver driver, String email,
			String subject) throws InterruptedException {
		email = email.substring(0, email.indexOf('@'));
		SysLogger.log("Email is -- " + email);
		driver.navigate().to("https://www.mailinator.com/v3/index.jsp?zone=public&query=" + email + "#/#inboxpane");
		Thread.sleep(3000);
		WebElement emailSubject = driver.findElement(By.xpath(
				"//div[@id='inboxpane']//table[@class='table table-striped jambo_table']//td/a[contains(text(), '"
						+ subject + "')]"));
		if (emailSubject.isDisplayed() && emailSubject.isEnabled()) {
			return true;
		}
		return false;
	}

	public static synchronized ArrayList<String> getAuthUserPasswordSetupLinkAndContentFromEmail(WebDriver driver,
			String email, String subject) throws InterruptedException {
		email = email.substring(0, email.indexOf('@'));
		SysLogger.log("Email is -- " + email);
		driver.navigate().to("https://www.mailinator.com/v3/index.jsp?zone=public&query=" + email + "#/#inboxpane");
		Thread.sleep(3000);
		driver.findElement(By.xpath(
				"//div[@id='inboxpane']//table[@class='table table-striped jambo_table']//td/a[contains(text(), '"
						+ subject + "')]"))
				.click();
		driver.switchTo().frame("msg_body");
		Thread.sleep(3000);
		String emailText = driver.findElement(By.xpath("/html/body")).getText().trim();
		ArrayList<String> result = new ArrayList<String>();
		result.add(emailText);
		String text = driver.findElement(By.xpath("/html/body/a[1]")).getAttribute("href").trim();
		result.add(text);
		return result;
	}

	public static synchronized void retireAllActiveCampaignByDBUpdate(String accountId) throws Exception {
		// Please do not remove this check. This is to avoid running DB call on Prod

		String droppedCampaignId = ConfigReader.getConfig("maas_api_dropped_message_campaign_id");
		String messageSentCampaignId = ConfigReader.getConfig("maas_api_message_sent_activity_campaign_id");

		if (!ConfigReader.getENVConfigValue().equalsIgnoreCase("prod")
				&& !ConfigReader.getENVConfigValue().equalsIgnoreCase("upmc-stage")
				&& !ConfigReader.getENVConfigValue().equalsIgnoreCase("stage")) {

			ApplicationDB.updateData("update message set status=250 where account_id=" + accountId
					+ " and campaign_id in (select id from campaign where status=300 and account_id=" + accountId
					+ " and id not in(" + droppedCampaignId + "," + messageSentCampaignId + "))");
			ApplicationDB.updateData("update campaign set status=250 where status=300 and account_id=" + accountId
					+ " and id not in(" + droppedCampaignId + "," + messageSentCampaignId + ")");
		}
	}

	public static synchronized String getoAuth() {
		String url = ConfigReader.getConfig("oauth_url");
		Response r = expect().given().contentType("application/x-www-form-urlencoded")
				.formParam("grant_type", "password").formParam("client_id", ConfigReader.getConfig("client_id"))
				.formParam("client_secret", ConfigReader.getConfig("client_secret"))
				.formParam("username", ConfigReader.getConfig("oauth_username"))
				.formParam("password", ConfigReader.getConfig("oauth_password")).log().all().when().post(url);
		String body = r.getBody().asString();
		SysLogger.log("Response body " + body);
		Assert.assertEquals(r.statusCode(), 200);
		JsonPath jsonPathEvaluator = r.jsonPath();
		return jsonPathEvaluator.get("access_token").toString();
	}

	public static synchronized String authTokenIpWhiteList() {

		String url = ConfigReader.getConfig("ssoAuthTokenUrl");
		Response r = expect().given().contentType("application/x-www-form-urlencoded")
				.formParam("email", ConfigReader.getConfig("ssoAuthTokenUsername"))
				.formParam("password", ConfigReader.getConfig("ssoAuthTokenPassword"))
				.formParam("platform_account_id", ConfigReader.getConfig("account_id")).log().all().when().post(url);
		String body = r.getBody().asString();
		SysLogger.log("Response body " + body);
		Assert.assertEquals(r.statusCode(), 200);
		JsonPath jsonPathEvaluator = r.jsonPath();

		return jsonPathEvaluator.get("token").toString();
	}

	public static synchronized String authTokenIpWhiteList(String username, String password, String platformAccountId) {

		String url = ConfigReader.getConfig("ssoAuthTokenUrl");
		Response r = expect().given().contentType("application/x-www-form-urlencoded").formParam("email", username)
				.formParam("password", password).formParam("platform_account_id", platformAccountId).log().all().when()
				.post(url);
		String body = r.getBody().asString();
		SysLogger.log("Response body " + body);
		Assert.assertEquals(r.statusCode(), 200);
		JsonPath jsonPathEvaluator = r.jsonPath();

		return jsonPathEvaluator.get("token").toString();
	}

	public static synchronized Response authTokenVerify(String jwtToken) {

		String url = ConfigReader.getConfig("ssoAuthTokenVerifyUrl");
		Response r = expect().given().contentType("application/x-www-form-urlencoded").formParam("token", jwtToken)
				.log().all().when().post(url);
		String body = r.getBody().asString();
		SysLogger.log("Response body " + body);
		Assert.assertEquals(r.statusCode(), 200);
		return r;
	}

	public static synchronized String authTokenRefresh(String jwtToken) {

		String url = ConfigReader.getConfig("ssoAuthTokenRefreshUrl");
		Response r = expect().given().contentType("application/x-www-form-urlencoded").formParam("token", jwtToken)
				.log().all().when().post(url);
		String body = r.getBody().asString();
		SysLogger.log("Response body " + body);
		Assert.assertEquals(r.statusCode(), 200);
		JsonPath jsonPathEvaluator = r.jsonPath();

		return jsonPathEvaluator.get("token").toString();
	}

	public static synchronized void addMemberInDNCL(String email, String accountId) {

		String baseURI = ConfigReader.getConfig("dncl_url");
		String jsonBody = "{ \"email\": \"" + email + "\" }";
		Response r = expect().statusCode(200).given().contentType("application/json")
				.header("Authorization",
						getBasicAuth(ConfigReader.getConfig("api_username"), ConfigReader.getConfig("api_password")))
				.header("X-Ms-Format", "json").header("X-Ms-Source", "api").body(jsonBody).log().all().when()
				.post(baseURI);

		Assert.assertEquals(r.statusCode(), 200);

	}

	public static synchronized String getUserAgentOfCurrentBrowser(WebDriver driver) {
		driver.navigate().to("https://www.google.com/search?q=google");
		WebElement googleSearch = driver.findElement(By.xpath("//*[@name='q' and @type='text']"));
		googleSearch.clear();
		googleSearch.sendKeys("get user agent");
		driver.findElement(By.xpath("//button[@aria-label='Google Search' and @type='submit']")).click();
		return driver.findElement(By.xpath("//div[@id='rso']//span[text()='Your user agent']/following-sibling::div"))
				.getText();
	}
}
