package com.etf.qa.common;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author wpballard
 *
 */
public class PageBase { 
	
	protected final WebDriver driver;
	public Helper helper;
	protected final static  Logger logger = LoggerFactory.getLogger(PageBase.class);
	public Properties baseUrls;
	public Actions actions;

	
	public PageBase(WebDriver driver) {
		this.driver = driver;
	
		baseUrls = Helper.loadPageObjectProperties();
		actions = new Actions(driver);
	}
	
	
	public void ConfirmTitle(String strTitle)
	{
		if(!driver.getTitle().contentEquals(strTitle))
		{
			logger.error("Should be on page ${strTitle} but I am actually on page ${driver.getTitle()}");
			throw new IllegalStateException("Should be on page ${strTitle} but I am actually on page ${driver.getTitle()}");
		}
		else
		{
			logger.info("Page Title matched page object title: ${strTitle}");
		}
	}
	
	
	public void ConfirmUrl(String strUrl)
	{
		
		//This method similar to "Loadable Component" model native to selenium.
		//https://code.google.com/p/selenium/wiki/LoadableComponent		
		if(!driver.getCurrentUrl().contains(strUrl))
		{
			logger.info("Page Url string not found - navigating to URL");
			driver.navigate().to(strUrl);
			logger.info("After navigation URL is " + driver.getCurrentUrl());
			
		}else
		{
			logger.info("Current URL: " + driver.getCurrentUrl() );	
		}		
	}//ConfirmUrl
	

}//class
