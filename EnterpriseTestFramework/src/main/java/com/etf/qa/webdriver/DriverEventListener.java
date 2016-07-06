package com.etf.qa.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import org.joda.time.DateTime;

public class DriverEventListener implements WebDriverEventListener {
	
	protected final static  Logger logger = LoggerFactory.getLogger(DriverEventListener.class);
	private By lastFindBy;
	private String originalValue;

	public DriverEventListener() {}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver) {		
		 logger.info("WebDriver changed value in element found "+lastFindBy+" from '"+originalValue+"' to '" + element.getText() +"'");
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		logger.info("Button Clicked: " + element.getText()  + " at " + DateTime.now().toLocalTime());
	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		logger.info("Found Element " + by + " at " + DateTime.now().toLocalTime());
	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
		logger.info("Webdriver navigated back to: " + driver.getCurrentUrl() + " at " + DateTime.now().toLocalTime());
	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
		logger.info("Webdriver navigated forward to: " + driver.getCurrentUrl() + " at " + DateTime.now().toLocalTime());
	}

	@Override
	public void afterNavigateTo(String strUrl, WebDriver driver) {
		logger.info("Webdriver navigated to: " + strUrl + " at " + DateTime.now().toLocalTime());
	}

	@Override
	public void afterScript(String strUrl, WebDriver driver) {
		logger.info("Webdriver executed script: " + driver.getCurrentUrl() + " at " + DateTime.now().toLocalTime());
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		originalValue = element.getText();
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		logger.info("Clicking Element: " + element.getText() + " at " + DateTime.now().toLocalTime());	
	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		lastFindBy = by;
		logger.info("Finding Element " + by + " at " + DateTime.now().toLocalTime());
	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {
		logger.info("Navigating Back at " + DateTime.now().toLocalTime());
		
	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {
		logger.info("Navigating Forward at " + DateTime.now().toLocalTime());	
	}

	@Override
	public void beforeNavigateTo(String strUrl, WebDriver driver) {
		logger.info("WebDriver navigating to: "+strUrl + " at " + DateTime.now().toLocalTime());
	}

	@Override
	public void beforeScript(String strScript, WebDriver driver) {
		logger.info("Webdriver executing script: " + driver.getCurrentUrl() + " at " + DateTime.now().toLocalTime());
	}

	@Override
	public void onException(Throwable error, WebDriver driver) {
		
		if (error.getClass().equals(NoSuchElementException.class)){
            logger.error("WebDriver error: Element not found "+ lastFindBy);
        } else {
            logger.error("WebDriver error:", error);
        }
		
	}

	@Override
	public void afterNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

}

