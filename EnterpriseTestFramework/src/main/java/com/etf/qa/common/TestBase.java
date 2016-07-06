package com.etf.qa.common;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.sql.DriverPropertyInfo;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.support.ThreadGuard;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;


import com.etf.qa.testng.TestNGEventListener;
import com.etf.qa.testng.TestNGExecutionListener;
import com.etf.qa.testng.TestNGSuiteListener;
import com.etf.qa.webdriver.CustomEventFiringWebDriver;
import com.etf.qa.webdriver.LocalDriverFactory;
import com.etf.qa.webdriver.RemoteDriverFactory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;




@Listeners({ TestNGExecutionListener.class, TestNGSuiteListener.class, TestNGEventListener.class, org.uncommons.reportng.HTMLReporter.class})
public class TestBase {


	protected final static Logger logger = LoggerFactory.getLogger(TestBase.class);
	public WebDriver driver;
	public Helper helper;
	public Properties prop;

	public WebDriver getDriver() {
		return driver;
	}

	public TestBase() {}
	
	@AfterClass
	public void afterClass() {
		
		try {
			
			driver.close();
			
			if(Global.getUseGrid())
			{
				driver.quit();
//				RemoteDriverFactory.getInstance().removeDriver();			
			}
			else
			{
////				driver.quit();
				LocalDriverFactory.getInstance().removeDriver();
			}
			
		} catch(Exception ex) {
			
			logger.info("Error Closing Browser: " + ex.getMessage());
		}
		
		
		logger.info("Driver Object Disposed");
		
	}

	@BeforeClass
	public void beforeClass() {
			
		try {
			
			if(Global.getUseGrid())
			{
				logger.info("Selenium Grid Test");
				logger.info("Initializing RemoteWebDriver");

				RemoteDriverFactory remoteDriverFactory = new RemoteDriverFactory();
				driver = remoteDriverFactory.GetNewDriver();
				
				if(driver == null) {
					throw new Exception("Webdriver Not Properly Initialized");
				}
				
				driver.manage().window().setSize(new Dimension(1920, 1080));
			}
			
			else
			{				
				logger.info("Selenium Local Webdriver Test");
				logger.info("Initializing Local WebDriver");		
				driver = LocalDriverFactory.getInstance().getDriver();
				if(driver == null) {
					throw new Exception("Webdriver Not Properly Initialized");
				}
				
				driver.manage().window().maximize();
			}
			
			driver.manage().timeouts().setScriptTimeout(180, TimeUnit.SECONDS);
			
			//driver.manage().window().setSize(new Dimension(1366, 768));
			//driver.manage().window().setSize(new Dimension(1920, 1080));
			//driver.manage().window().maximize();
			

			Helper.Wait(2);					
			}
			catch (Exception ex)
			{
				logger.info(ex.getMessage());				
			}
		
											
		// Load the properties file
		
		prop = Helper.loadTestDataProperties();
		
	}
	
	
	@BeforeMethod()
	public void beforeMethod(Method method) 
	{
	
	}
	
	
	@AfterMethod()
	public void afterMethod()
	{


	}
	


}// Class

