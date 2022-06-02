package com.application.utility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ThreadSafeNumber {
	
	private static int counter;
	
	private static Map<Long, String[]> runningThreadsMap = new HashMap<Long, String[]>();
	private static Map<Long, Integer> runningNumberMap = new HashMap<Long, Integer>();
	
	public synchronized static String getRandomPhone() {
		Long threadID = Thread.currentThread().getId();
		if (ConfigReader.getENVConfigValue().equalsIgnoreCase("prod") || ConfigReader.getENVConfigValue().equalsIgnoreCase("upmc-stage")
				|| ConfigReader.getENVConfigValue().equalsIgnoreCase("upmc-prod")) {
			return getRandomPhoneProd();
		}
		if (!runningThreadsMap.containsKey(threadID)) {
			String[] tmp= ThreadSafeNumber.getnextPhoneArray();
			runningThreadsMap.put(threadID, tmp);
			Random r=new Random();
	        return (String) tmp[r.nextInt(tmp.length)];
		}
		else {
			String[] tmp= runningThreadsMap.get(threadID);
			Random r=new Random();
	        return tmp[r.nextInt(tmp.length)];
		}
	}
	
	private synchronized static String getRandomPhoneProd() {
		Long threadID = Thread.currentThread().getId();
		
		if (!runningThreadsMap.containsKey(threadID)) {
			String[] tmp= ThreadSafeNumber.getnextPhoneArray();
			runningThreadsMap.put(threadID, tmp);
			List<String> stringList = Arrays.asList(tmp);
			runningNumberMap.put(threadID, 0);
	        return stringList.get(0);
		}
		else {
			String[] tmp= runningThreadsMap.get(threadID);
			List<String> stringList = Arrays.asList(tmp);
			if(!runningNumberMap.containsKey(threadID)) {
				runningNumberMap.put(threadID, 0);
		        return stringList.get(0);
			}
			else {
				int nextNum = runningNumberMap.get(threadID)+1;
				runningNumberMap.put(threadID, nextNum);
				return stringList.get(nextNum);
			}
		}
	}
	
	public synchronized static String[] getAllPhone() {
		Long threadID = Thread.currentThread().getId();
		
		if (!runningThreadsMap.containsKey(threadID)) {
			String[] tmp= ThreadSafeNumber.getnextPhoneArray();
			runningThreadsMap.put(threadID, tmp);
	        return tmp;
		}
		else {
			String[] tmp= runningThreadsMap.get(threadID);
	        return tmp;
		}
	}
	
	private synchronized static String[] getnextPhoneArray() {
		if (ConfigReader.getENVConfigValue().equalsIgnoreCase("prod") || ConfigReader.getENVConfigValue().equalsIgnoreCase("upmc-stage")
				|| ConfigReader.getENVConfigValue().equalsIgnoreCase("upmc-prod")) {
			return getnextPhoneArrayProd();
		}
		if (counter == 0) {
			counter = counter+1;
			String[] arr1 = {"16504211192", "16508635091", "19899162563", "19899162564", "19899162565", 
					"19899162566", "19899162567", "19899162568", "19899162569", "19899162570", "19899162571",
					"19899162572", "19899162573", "19899162574", "19899162575", "19899162576", "19899162577",
					"19899162578", "19899162579", "19899162580", "19899162581", "19899162582", "19899162583", 
					"19899162584", "19899162585", "19899162586", "19899162587", "19899162588", "19899162589", 
					"19899162590", "19899162591", "19899162592", "19899162593", "19899162594", "19899162595", 
					"19899162596", "19899162597", "19899162598", "19899162599", "19899162600", "19899162601", 
					"19899162602", "19899162603", "19899162604", "19899162605", "19899162606"};
			return arr1;
		}
		if (counter == 1) {
			counter = counter+1;
			String[] arr2 = {"16509188040", "16509188041", "16509188042", "16509188043", "16509188044", "16509188045",
					"16509188046", "16509188047", "16509188048", "16509188049", "16509188050", "16509188051", "16509188052",
					"16509188053", "16509188054", "16509188055", "16509188056", "16509188057", "16509188058", "16509188059", 
					"16509188060", "16509188061", "16509188062", "16509188063", "16509188064", "16509188065", "16509188066", 
					"16509188067", "16509188068", "16509188069", "16509188070", "16509188071", "16509188072", "16509188073", 
					"16509188074", "16509188075", "16509188076", "16509188077", "16509188078", "16509188079", "16509188080", 
					"16509188081", "16509188082", "16509188083", "16509188084", "16509188085", "16509188086", "16509188087"};
			return arr2;
		}
		if (counter == 2) {
			counter = counter+1;
			String[] arr3 = {"16502063891", "16502063893", "16502063895", "16502063896", "16502063897", "16502063898",
					"16502063899", "16502063900", "16502063901", "16502063902", "16502063903", "16502063904", "16502063905",
					"16502063906", "16502063907", "16502063908", "16502063909", "16502063910", "16502063911", "16502063912",
					"16502063913", "16502063914", "16502063915", "16502063916", "16502063917", "16502063918", "16502063919",
					"16502063920", "16502063921", "16502063922", "16502063923", "16502063924", "16502063925", "16502063926", 
					"16502063927", "16502063928", "16502063929", "16502063930", "16502063931", "16502063932", "16502063933", 
					"16502063934", "16502063935", "16502063936", "16502063937", "16502063938", "16502063939", "16502063940", 
					"16502063941", "16502063942", "16502063943", "16502063944", "16502063945", "16502063946"};
			return arr3;
		}
		else {
			counter = counter+1;
			String[] arr = {"16504551001", "16504551002", "16504551003", "16504551004", "16504551005", "16504551006", 
					"16504551007", "16504551008", "16504551009", "16504551010", "16504551011", "16504551012", "16504551013", 
					"16504551014", "16504551015", "16504551016", "16504551017", "16504551018", "16504551019", "16504551020", 
					"16504551021", "16504551022", "16504551023", "16504551024", "16504551025", "16504551026", "16504551027", 
					"16504551028", "16504551029", "16504551030", "16504551031", "16504551032", "16504551033", "16504551034", 
					"16504551035", "16504551036", "16504551037", "16504551038", "16504551039", "16504551040", "16504551041", 
					"16504551042", "16504551043", "16504551044", "16504551045", "16504551046", "16504551047", "16504551048", 
					"16504551049", "16504551050", "16504551051", "16504551052", "16504551053", "16504551054", "16504551055", 
					"16504551056", "16504551057", "16504551058", "16504551059", "16504551060", "16504551061", "16504551062", 
					"16504551063", "16504551064", "16504551065", "16504551066", "16504551067", "16504551068", "16504551069", 
					"16504551070", "16504551071", "16504551072", "16504551073", "16504551074", "16504551075"};
			return arr;
		}
	 }
	
	private synchronized static String[] getnextPhoneArrayProd() {
		if (counter == 0) {
			counter = counter+1;
			String[] arr1 = {"16507038468", "16502813109", "16476290093"};
			return arr1;
		}
		if (counter == 1) {
			counter = counter+1;
			String[] arr2 = {"16502766052", "16507733045", "16505548289"};
			return arr2;
		}
		if (counter == 2) {
			counter = counter+1;
			String[] arr3 = {"16507662774", "16502406841", "16502882800"};
			return arr3;
		}
		if (counter == 3) {
			counter = counter+1;
			String[] arr4 = {"16505762142", "16504396369", "16502813641"};
			return arr4;
		}
		if (counter == 4) {
			counter = counter+1;
			String[] arr5 = {"16504778612", "16504381903", "16507733073"};
			return arr5;
		}
		if (counter == 5) {
			counter = counter+1;
			String[] arr6 = {"16505548381", "16504849392", "14168857628"};
			return arr6;
		}
		if (counter == 6) {
			counter = counter+1;
			String[] arr7 = {"16504396362", "16504300589", "16504303897"};
			return arr7;
		}
		if (counter == 7) {
			counter = counter+1;
			String[] arr8 = {"16503389773", "16504777862", "16504849387"};
			return arr8;
		}
		else {
			counter = counter+1;
			String[] arr = {"16508676181", "16509189652", "16508635091"};
			return arr;
		}
	 }
	
	public synchronized static void resetNextPhoneCounter() {
		if (ConfigReader.getENVConfigValue().equalsIgnoreCase("prod") || ConfigReader.getENVConfigValue().equalsIgnoreCase("upmc-stage")
				|| ConfigReader.getENVConfigValue().equalsIgnoreCase("upmc-prod")) {
			Long threadID = Thread.currentThread().getId();
			runningNumberMap.remove(threadID);
		}
	}
	
}
