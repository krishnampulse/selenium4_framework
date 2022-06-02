package com.application.factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.application.utility.ConfigReader;
import com.application.utility.SysLogger;

public class DatabaseFactory {
	
	private DatabaseFactory() {}
    public static final ThreadLocal<Connection> threadConnection = new ThreadLocal<Connection>();
    public static final ThreadLocal<Connection> threadConnectionAmg = new ThreadLocal<Connection>();
    public static final ThreadLocal<Connection> threadConnectionTxn = new ThreadLocal<Connection>();
	
	public static synchronized Connection getConnection() throws SQLException {

		if (threadConnection.get() == null) {
			Connection connection = createConnection();
			threadConnection.set(connection);
			return threadConnection.get();
		}
		if (threadConnection.get().isClosed()) {
			SysLogger.log("********* [DB] Re-initializing database connection for threadId - "+Thread.currentThread().getId());
			Connection connection = createConnection();
			threadConnection.set(connection);
			return threadConnection.get();
		}
		else {
			return threadConnection.get();
		}
	}
	
	public static synchronized void closeConnection() {
		try {
			if (threadConnection.get() != null) {
				threadConnection.get().close();
				threadConnection.remove();
				SysLogger.log("Closing DB Connection for threadId - "+Thread.currentThread().getId());
			}
		} catch (SQLException e) {
			throw new RuntimeException("Unable to close the connection", e);
		}
	}
	
	private static synchronized Connection createConnection() {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
		} catch (Exception e) {
			SysLogger.log("postgresql Driver is not found , Error : - " +e.getMessage());
			e.printStackTrace();
		}
		try {
			String jdbcURL = ConfigReader.getConfig("jdbcURL");
			String jdbcUser = ConfigReader.getConfig("jdbcUser");
			String jdbcPassword = ConfigReader.getConfig("jdbcPassword");
			Connection con = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPassword);
			SysLogger.log("Sucessfully Connected to DB");
			SysLogger.log("Starting DB Connection for threadId - "+Thread.currentThread().getId());
			return con;
		} catch (SQLException e) {
			SysLogger.log("not able to connect to postgresql , Error : - " +e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	private static synchronized Connection createConnectionToAmgDb() {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
		} catch (Exception e) {
			SysLogger.log("postgresql Driver is not found , Error : - " +e.getMessage());
			e.printStackTrace();
		}
		try {
			String amgJdbcURL = ConfigReader.getConfig("amgjdbcURL");
			String amgJdbcUser = ConfigReader.getConfig("amgjdbcUser");
			String amgJdbcPassword = ConfigReader.getConfig("amgjdbcPassword");
			Connection con = DriverManager.getConnection(amgJdbcURL, amgJdbcUser, amgJdbcPassword);
			SysLogger.log("Sucessfully Connected to AMG DB");
			SysLogger.log("Starting AMG DB Connection for threadId - "+Thread.currentThread().getId());
			return con;
		} catch (SQLException e) {
			SysLogger.log("not able to connect to AMG DB, Error : - " +e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public static synchronized Connection getAmgDbConnection() throws SQLException {
		if (threadConnectionAmg.get() == null) {
			Connection connection = createConnectionToAmgDb();
			threadConnectionAmg.set(connection);
			return threadConnectionAmg.get();
		}
		if (threadConnectionAmg.get().isClosed()) {
			SysLogger.log("********* [DB] Re-initializing database connection for threadId - "+Thread.currentThread().getId());
			Connection connection = createConnectionToAmgDb();
			threadConnectionAmg.set(connection);
			return threadConnectionAmg.get();
		}
		else {
			return threadConnectionAmg.get();
		}
	}
	
	public static synchronized void closeAmgDBConnection() {
		try {
			if (threadConnectionAmg.get() != null) {
				threadConnectionAmg.get().close();
				threadConnectionAmg.remove();
				SysLogger.log("Closing AMG DB Connection for threadId - "+Thread.currentThread().getId());
			}
		} catch (SQLException e) {
			throw new RuntimeException("Unable to close the AMG DB connection", e);
		}
	}
	
	private static synchronized Connection createConnectionToTxnDB() {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
		} catch (Exception e) {
			SysLogger.log("postgresql Driver is not found , Error : - " +e.getMessage());
			e.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection(ConfigReader.getConfig("txnjdbcURL"), ConfigReader.getConfig("txnjdbcUser"), ConfigReader.getConfig("txnjdbcPassword"));
			SysLogger.log("Sucessfully Connected to TRANSACTIONAL DB");
			SysLogger.log("Starting TRANSACTIONAL DB Connection for threadId - "+Thread.currentThread().getId());
			return con;
		} catch (SQLException e) {
			SysLogger.log("not able to connect to TRANSACTIONAL DB, Error : - " +e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public static synchronized Connection getTxnDBConnection() throws SQLException {
		if (threadConnectionTxn.get() == null) {
			Connection connection = createConnectionToTxnDB();
			threadConnectionTxn.set(connection);
			return threadConnectionTxn.get();
		}
		if (threadConnectionTxn.get().isClosed()) {
			SysLogger.log("********* [DB] Re-initializing database connection for threadId - "+Thread.currentThread().getId());
			Connection connection = createConnectionToTxnDB();
			threadConnectionTxn.set(connection);
			return threadConnectionTxn.get();
		}
		else {
			return threadConnectionTxn.get();
		}
	}
	
	public static synchronized void closeTxnDBConnection() {
		try {
			if (threadConnectionTxn.get() != null) {
				threadConnectionTxn.get().close();
				threadConnectionTxn.remove();
				SysLogger.log("Closing TRANSACTIONAL DB Connection for threadId - "+Thread.currentThread().getId());
			}
		} catch (SQLException e) {
			throw new RuntimeException("Unable to close the TRANSACTIONAL DB connection", e);
		}
	}
}

