package com.etf.qa.webdriver;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.etf.qa.common.Global;
import com.etf.qa.common.Helper;
import com.etf.qa.testng.TestNGSuiteListener;

public class GridManager {
		
//	final Logger logger = LoggerFactory.getLogger(TestNGSuiteListener.class);
//	private String strGridUrl = Global.getGridUrl();
//	
//	//Start WebGrid 
//	public Process StartGridProcess() throws IOException, InterruptedException
//	{
//		
//		Process gridShellProcess;
//		
//		//Start WebGrid
//		logger.info("Starting Selenium Grid Server");
//		
//		ProcessBuilder processBuilder = new ProcessBuilder();
//		processBuilder.command("java", "-jar", "src/test/resources/selenium-server-standalone-2.39.0.jar", "-role", "hub");
//		
//		gridShellProcess=processBuilder.start();
//		logger.info("Selenium Grid Server Started");
//		logger.info("Wait 5 Seconds for grid to initialize");
//		Helper.Wait(5);		
//		logger.info("Selenium Grid Server browseable at " + strGridUrl +"/grid/console");
//		
//		return gridShellProcess;
//	}
//	
//	public Process StartNodeProcess() throws InterruptedException, IOException
//	{
//		Process gridNodeProcess;
//		
//		logger.info("Starting Selenium Grid Node");
//		//Start the node and attach to grid
//		ProcessBuilder processBuilder = new ProcessBuilder();
//		processBuilder.command("java","-jar", "src/test/resources/selenium-server-standalone-2.39.0.jar", "-role", "node","-hub",  strGridUrl + "/grid/register");
//
//		gridNodeProcess = processBuilder.start();
//		logger.info("Selenium Grid Grid Node Started");
//		logger.info("Wait 5 Seconds for node to initialize");
//		Helper.Wait(5);
//		
//		return gridNodeProcess;
//		
//	}
//	
//	public void EndNodeProcess(Process gridNodeProcess) throws InterruptedException 
//	{
//		if(gridNodeProcess != null)
//		{
//			logger.info("Destroying Selenium Web Grid Object");
//			gridNodeProcess.destroy();
//			logger.info("Wait 2 Seconds for Grid Node to destroy");
//			Helper.Wait(2);
//			logger.info("Selenium Grid Node Destroyed");
//		}
//		
//	}
//	
//
//	
//	public void EndGridProcess(Process gridShellProcess) throws InterruptedException
//	{	
//		if(gridShellProcess != null)
//		{
//			gridShellProcess.destroy();
//			logger.info("Selenium Grid Shell Destroyed");
//		}
//		//manually shut down grid hub by browsing to http://localhost:4444/lifecycle-manager?action=shutdown	
//	}
//	
	
	
}//class
