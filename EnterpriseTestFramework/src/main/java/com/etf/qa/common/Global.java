package com.etf.qa.common;


import java.io.IOException;
import java.util.Properties;

import org.testng.ISuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Global {
	
	private static String platform = "WINDOWS";
	private static String browser = "firefox";
	private static String browserVersion = null;
	private static String gridUrl = "http://localhost:4444/wd/hub";
	private static Boolean useGrid = true;
	private static Boolean screenshot = false;
	private static String rallyTestSet=null;
	private static String rallyBuild=null;
	private static String rallyBuildNotes=null;
	private static String rallyKey = "_YourRallyKey";
	private static String rallyUrl = "https://rally1.rallydev.com";
	private static String rallyUsername = "yourrallyapiuseremail@whatever.com";
	private static String rallyPassword = "apiuserpassword";
	private static Boolean rallyReport = false;
	private static String pageObjectPropertiesFileName = "PageObject.properties";
	private static String testDataPropertiesFileName = "TestData.properties";
	private static String dbConnectionPropertiesFileName = "DBConnection.properties";
	private static String smtpHost = "YourExchangeHostName";
	private static String smtpUserName = "";
	private static String smtpPassword = "";
	private static Properties globalProp = new Properties();
    
    final static Logger logger = LoggerFactory.getLogger(Global.class);
    

	private static void LoadProperties(){
		
		try {

			globalProp.load(Global.class.getResourceAsStream("/Global.properties"));
			//logger.info("GlobalExecution.properties has been loaded.");
			
		} catch (IOException e) {
			
			logger.info("Unable to load Global.Properties file. IO Exception.");
			e.printStackTrace();
			System.exit(0);
		}
		catch (NullPointerException e)
		{
			logger.info("Unable to load Global.Properties file. File null.");
			e.printStackTrace();
			System.exit(0);
		}			
	}

	
	public static Boolean getUseGrid() {
		
		LoadProperties();
		
		if(globalProp.getProperty("useGrid") != null)
		{
			useGrid = Boolean.parseBoolean(globalProp.getProperty("useGrid"));
		}
		
		if (System.getProperty("useGrid") != null )
		{
			useGrid = Boolean.parseBoolean(System.getProperty("useGrid"));
			logger.info("UseGrid Boolean PARAMETER FOUND: " + useGrid.toString());
		}
		
		return useGrid;
	}
	
	public static Boolean getScreenshot() {
		
		LoadProperties();
		
		if(globalProp.getProperty("screenshot") != null)
		{
			screenshot = Boolean.parseBoolean(globalProp.getProperty("screenshot"));
		}
		
		if (System.getProperty("screenshot") != null )
		{
			screenshot = Boolean.parseBoolean(System.getProperty("screenshot"));
			logger.info("screenshot Boolean PARAMETER FOUND: " + screenshot.toString());
		}
		
		return screenshot;
		
		
	}
	
	
	public static String getPlatform(){
		
		LoadProperties();
		
		if(globalProp.getProperty("platform") != null)
		{
			platform = globalProp.getProperty("platform");
		}	
		
		if (System.getProperty("platform") != null)
		{
			platform = System.getProperty("platform");
			logger.info("Platform (Operating System) PARAMETER FOUND: " + platform );
		}
		return platform;
	}
	
	public static String getBrowser(){
		
		LoadProperties();
		logger.info("Getting Global Browser Selection");
		
		if(globalProp.getProperty("browser") != null)
		{
			browser= globalProp.getProperty("browser");
		}	
		
		if (System.getProperty("browser") != null)
		{
			browser = System.getProperty("browser");
			logger.info("BROWSER PARAMETER FOUND: " + browser );
		}else
		{ logger.info("No System browser property found");}
		return browser;
	}
	
	public static String getBrowserVersion(){
		
		LoadProperties();
		
		if(globalProp.getProperty("browserVersion") != null)
		{
			browserVersion= globalProp.getProperty("browserVersion");
		}
		
		if (System.getProperty("browserVersion") != null)
		{
			browserVersion = System.getProperty("browserVersion");
			logger.info("BROWSER VERSION PARAMETER FOUND: " + browserVersion );
		}
		return browserVersion;
	}
	
	
	public static String getGridUrl(){
		
		LoadProperties();
		
		if(globalProp.getProperty("gridUrl") != null)
		{
			gridUrl= globalProp.getProperty("gridUrl");
		}
		
		
		if (System.getProperty("gridUrl") != null)
		{
			gridUrl = System.getProperty("gridUrl");
			logger.info("GRIDURL PARAMETER FOUND: " + gridUrl );
		}
		return gridUrl;
	}
	
	//rallyTestSet
	public static String getRallyTestSet(){
		
		LoadProperties();
		
		if(globalProp.getProperty("rallyTestSet") != null)
		{
			rallyTestSet= globalProp.getProperty("rallyTestSet");
		}
		
		
		if (System.getProperty("rallyTestSet") != null)
		{
			rallyTestSet = System.getProperty("rallyTestSet");
			logger.info("RallyTestSet PARAMETER FOUND: " + rallyTestSet );
		}
		return rallyTestSet;
	}
	
	public static String getRallyBuild(){
		
		LoadProperties();
		
		if(globalProp.getProperty("rallyBuild") != null)
		{
			rallyBuild= globalProp.getProperty("rallyBuild");
		}
		
		
		if (System.getProperty("rallyBuild") != null)
		{
			rallyBuild = System.getProperty("rallyBuild");
			logger.info("RallyBuild PARAMETER FOUND: " + rallyBuild );
		}
		return rallyBuild;
	}
	
	//rallyBuildNotes
	
	public static String getRallyBuildNotes(){
		
		LoadProperties();
		
		if(globalProp.getProperty("rallyBuildNotes") != null)
		{
			rallyBuildNotes = globalProp.getProperty("rallyBuildNotes");
		}
		
		
		if (System.getProperty("rallyBuildNotes") != null)
		{
			rallyBuildNotes = System.getProperty("rallyBuildNotes");
			logger.info("RallyBuildNotes PARAMETER FOUND: " + rallyBuildNotes );
		}
		return rallyBuildNotes;
	}

	
	public static String getRallyKey(){
		
		LoadProperties();
		
		if(globalProp.getProperty("rallyKey") != null)
		{
			rallyKey= globalProp.getProperty("rallyKey");
		}
		
		
		if (System.getProperty("rallyKey") != null)
		{
			rallyKey = System.getProperty("rallyKey");
			logger.info("RallyKey PARAMETER FOUND: " + rallyKey );
		}
		return rallyKey;
	}
	
public static String getRallyUrl(){
		
		LoadProperties();
		
		if(globalProp.getProperty("rallyUrl") != null)
		{
			rallyUrl= globalProp.getProperty("rallyUrl");
		}
		
		
		if (System.getProperty("rallyUrl") != null)
		{
			rallyUrl = System.getProperty("rallyUrl");
			logger.info("rallyUrl PARAMETER FOUND: " + rallyUrl );
		}
		return rallyUrl;
	}
	
public static String getRallyUsername(){
		
		LoadProperties();
		
		if(globalProp.getProperty("rallyUsername") != null)
		{
			rallyUsername = globalProp.getProperty("rallyUsername");
		}
		
		
		if (System.getProperty("rallyUsername") != null)
		{
			rallyUsername = System.getProperty("rallyUsername");
			logger.info("rallyUsername PARAMETER FOUND: " + rallyUsername );
		}
		return rallyUsername;
	}

public static String getRallyPassword(){
	
	LoadProperties();
	
	if(globalProp.getProperty("rallyPassword") != null)
	{
		rallyPassword = globalProp.getProperty("rallyPassword");
	}
	
	
	if (System.getProperty("rallyPassword") != null)
	{
		rallyPassword = System.getProperty("rallyPassword");
		logger.info("rallyPassword PARAMETER FOUND: " + rallyPassword );
	}
	return rallyPassword;
}

public static Boolean getRallyReport() {
		
		LoadProperties();
		
		if(globalProp.getProperty("rallyReport") != null)
		{
			rallyReport = Boolean.parseBoolean(globalProp.getProperty("rallyReport"));
		}
		
		if (System.getProperty("rallyReport") != null )
		{
			rallyReport = Boolean.parseBoolean(System.getProperty("rallyReport"));
			logger.info("RallyReport Boolean PARAMETER FOUND: " + rallyReport.toString());
		}
		
		return rallyReport;
	}



public static String getPageObjectPropertiesFileName(){
	
	LoadProperties();
	
	if(globalProp.getProperty("pageObjectPropertiesFileName") != null)
	{
		pageObjectPropertiesFileName = globalProp.getProperty("pageObjectPropertiesFileName");
	}	
	
	if (System.getProperty("pageObjectPropertiesFileName") != null)
	{
		pageObjectPropertiesFileName = System.getProperty("baseUrlsPropertiesFileName");
		logger.info("baseUrlsPropertiesFileName PARAMETER FOUND: " + pageObjectPropertiesFileName );
	}
	return pageObjectPropertiesFileName;
}
	
public static String getTestDataPropertiesFileName(){
	
	LoadProperties();
	
	if(globalProp.getProperty("testDataPropertiesFileName") != null)
	{
		testDataPropertiesFileName = globalProp.getProperty("testDataPropertiesFileName");
	}	
	
	if (System.getProperty("testDataPropertiesFileName") != null)
	{
		testDataPropertiesFileName = System.getProperty("testDataPropertiesFileName");
		logger.info("TestDataPropertiesFileName PARAMETER FOUND: " + testDataPropertiesFileName );
	}
	return testDataPropertiesFileName;
}

public static String getDbConnectionPropertiesFileName(){
	
	LoadProperties();
	
	if(globalProp.getProperty("dbConnectionPropertiesFileName") != null)
	{
		dbConnectionPropertiesFileName = globalProp.getProperty("dbConnectionPropertiesFileName");
	}	
	
	if (System.getProperty("dbConnectionPropertiesFileName") != null)
	{
		dbConnectionPropertiesFileName = System.getProperty("dbConnectionPropertiesFileName");
		logger.info("dbConnectionPropertiesFileName PARAMETER FOUND: " + dbConnectionPropertiesFileName );
	}
	return dbConnectionPropertiesFileName;
}

public static String getSmtpHost(){
	
	LoadProperties();
	
	if(globalProp.getProperty("smtpHost") != null)
	{
		smtpHost = globalProp.getProperty("smtpHost");
	}	
	
	if (System.getProperty("smtpHost") != null)
	{
		smtpHost = System.getProperty("smtpHost");
		logger.info("SMTPHost PARAMETER FOUND: " + smtpHost );
	}
	return smtpHost;
}

public static String getSmtpUseName(){
	
	LoadProperties();
	
	if(globalProp.getProperty("smtpUserName") != null)
	{
		smtpUserName = globalProp.getProperty("smtpUserName");
	}	
	
	if (System.getProperty("smtpUserName") != null)
	{
		smtpUserName = System.getProperty("smtpUserName");
		logger.info("SMTPUserName PARAMETER FOUND: " + smtpUserName );
	}
	return smtpUserName;
}

public static String getSmtpPassword(){
	
	LoadProperties();
	
	if(globalProp.getProperty("smtpPassword") != null)
	{
		smtpPassword = globalProp.getProperty("smtpPassword");
	}	
	
	if (System.getProperty("smtpPassword") != null)
	{
		smtpPassword = System.getProperty("smtpPassword");
		logger.info("SMTPPassword PARAMETER FOUND: " + smtpPassword );
	}
	return smtpPassword;
}

	
}//class
