package com.etf.qa.testng;

import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class TestNGSuiteListener implements ISuiteListener {
	
	
	final Logger logger = LoggerFactory.getLogger(TestNGSuiteListener.class);

	public void onStart(ISuite suite) {
		
		logger.info("TestNG Suite " + suite.getName() +" Started" );
		 
	}
	

	public void onFinish(ISuite suite) {
	
		logger.info("TestNG Suite completed");
	}
	
	
	

}//Class
