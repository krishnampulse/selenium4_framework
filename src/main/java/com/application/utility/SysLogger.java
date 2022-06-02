package com.application.utility;

public class SysLogger {

	public static synchronized void log(String logString) {
		System.out.println("ThreadId-"+Thread.currentThread().getId()+" "+logString);
	}
	
	public static synchronized void log(Object logString) {
		System.out.println("ThreadId-"+Thread.currentThread().getId()+" "+logString);
	}
}
