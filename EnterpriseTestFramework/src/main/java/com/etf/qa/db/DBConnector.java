package com.etf.qa.db;

//import oracle.jdbc.pool.OracleDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.etf.qa.common.Global;
import com.etf.qa.common.TestBase;

public class DBConnector {

	protected final static Logger logger = LoggerFactory.getLogger(TestBase.class);
	public Properties dbProp;

	public DBConnector() {
		LoadDBProps();
	}

	/***
	 * 
	 * @param connection
	 * @param QueryString
	 * @return
	 */
	public ResultSet queryDB(Connection connection, String QueryString) {
		
		try {
			Statement s = connection.createStatement();
			return s.executeQuery(QueryString);
		} catch (Exception e) {
			logger.info("There was an error executing a SQL Query");
			logger.info("Query Details: " + QueryString);
			e.printStackTrace();
		}
		return null;
	}
	
	/***
	 * 
	 * @param userID
	 * @param password
	 * @param dbServer
	 * @param sDBPort
	 * @param dbName
	 * @return
	 */
	public Connection getMySQLConnection(String userID, String password, String dbServer, String sDBPort, String dbName) {
				
		
		String dbConnectString = "jdbc:mysql://" + dbServer + ":" + sDBPort + "/" + dbName;
		logger.info(dbConnectString);
		Connection connection = null;
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dbConnectString, userID, password);
			logger.info(dbConnectString + " - " + userID + " - " + password);
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			return connection;
		}
	}

//	public  Connection getOracleConnection(String userID, String password, String dbServer, String sDBPort, String dbName) {
//
//		
//		//String dbConnectString = "jdbc:oracle:thin:" + userID + "/" + password + dbServer + ":" + sDBPort + ":" + dbName;
//		String dbConnectString = "jdbc:oracle:thin:@" + dbServer + ":" + sDBPort + ":" + dbName;
//
//		try {
//
//			OracleDataSource ods = new OracleDataSource();
//			ods.setURL(dbConnectString);
//			ods.setUser(userID);
//			ods.setPassword(password);
//			ods.setDatabaseName(dbName);
//			ods.setServerName(dbServer);
//			ods.setPortNumber(Integer.parseInt(sDBPort));
//			
//			Connection conn = ods.getConnection();
//			return conn;
//		} catch (Exception e) {
//			logger.info("Database Connection Error");
//			logger.info("Database Connection String: " + dbConnectString);
//			e.printStackTrace();
//			return null;
//		}
//	}

	public Connection getMSSQLConnection(String userID, String password, String dbServer, String sDBPort, String dbName) {
		
		String dbConnectString = "jdbc:jtds:sqlserver://" + dbServer + ":" + sDBPort + "/" + dbName;
		logger.info(dbConnectString);
		Connection connection = null;
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			connection = DriverManager.getConnection(dbConnectString, userID, password);
			logger.info(dbConnectString + " - " + userID + " - " + password);
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			return connection;
		}
	}

	/***
	 * 
	 */
	private void LoadDBProps() {
		// Load the properties file
		dbProp = new Properties();

		try {
			logger.info("Loading DB.properties");
			dbProp.load(TestBase.class.getResourceAsStream("/" + Global.getDbConnectionPropertiesFileName()));

		} catch (IOException e) {
			e.printStackTrace();
			logger.info("Unable to load DBConnection.Properties file");
			System.exit(0);
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.info("Unable to load DBConnection.Properties file. File null.");
			System.exit(0);

		}
	}

}// class
