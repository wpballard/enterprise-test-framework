package com.etf.qa.testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IExecutionListener;

import com.etf.qa.common.Global;
import com.etf.qa.webdriver.GridManager;

public class TestNGExecutionListener implements IExecutionListener {

	public WebDriver driver;
	private GridManager gridManager = new GridManager();

	Process gridShellProcess;
	Process gridNodeProcess;

	final Logger logger = LoggerFactory.getLogger(TestNGSuiteListener.class);

	@Override
	public void onExecutionStart() {

		logger.info("Test Execution Listener Started");

		// for debugging
		logger.info("Global Browser: " + Global.getBrowser());
		logger.info("Screenshot Functionality Enabled: " + Global.getScreenshot().toString());

		// this is for removing the log4j:WARN messages
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
		System.setProperty("org.uncommons.reportng.escape-output", "false");

		if (Global.getRallyReport()) {
			logger.info("Rally results reporting enabled in Global Execution Properties");
		} else {
			logger.info("Rally results reporting has been disabled or is missing in Global Execution Properties - Can be enabled by setting RallyReport=true");
		}

		logger.info("Testing Started");

	}// onExecutionStart

	@Override
	public void onExecutionFinish() {
		logger.info("All Tests Completed");

	}

}// Class
