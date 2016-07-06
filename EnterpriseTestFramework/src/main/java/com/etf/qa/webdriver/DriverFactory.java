package com.etf.qa.webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.etf.qa.common.Global;
import com.etf.qa.common.Helper;

public class DriverFactory {
	
final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

public DriverFactory() {}

public WebDriver getNewDriver() {
	
	WebDriver webDriver = null;

	try {

		if (Global.getUseGrid()) {
			logger.info("Selenium Grid Test");
			logger.info("Initializing RemoteWebDriver");
			RemoteDriverFactory remoteDriverFactory = new RemoteDriverFactory();
			webDriver = remoteDriverFactory.GetNewDriver();
		}

		else {
			logger.info("Selenium Local Webdriver Test");
			logger.info("Initializing Local WebDriver");
			LocalDriverFactory localDriverFactory = new LocalDriverFactory();
			webDriver = localDriverFactory.GetNewDriver();
		}

		// proxyPort = ((CustomEventFiringWebDriver)driver).getProxyPort();
		webDriver.manage().timeouts().setScriptTimeout(180, TimeUnit.SECONDS);
		webDriver.manage().window().maximize();

		Helper.Wait(2);
	} catch (Exception ex) {
		logger.info(ex.getMessage());
	}

	return webDriver;
}
		
public WebDriver getNewDriver(String strBrowserName) {
		
		WebDriver webDriver = null;

		try {

			if (Global.getUseGrid()) {
				logger.info("Selenium Grid Test");
				logger.info("Initializing RemoteWebDriver");
				RemoteDriverFactory remoteDriverFactory = new RemoteDriverFactory();
				webDriver = remoteDriverFactory.GetNewDriver(strBrowserName);
			}

			else {
				logger.info("Selenium Local Webdriver Test");
				logger.info("Initializing Local WebDriver");
				LocalDriverFactory localDriverFactory = new LocalDriverFactory();
				webDriver = localDriverFactory.GetNewDriver(strBrowserName);
			}

			// proxyPort = ((CustomEventFiringWebDriver)driver).getProxyPort();
			webDriver.manage().timeouts().setScriptTimeout(180, TimeUnit.SECONDS);
			webDriver.manage().window().maximize();

			Helper.Wait(2);
		} catch (Exception ex) {
			logger.info(ex.getMessage());
		}

		return webDriver;
	}

}
