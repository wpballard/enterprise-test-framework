package com.etf.qa.tests;


import org.openqa.selenium.By;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.etf.qa.common.Helper;
import com.etf.qa.common.TestBase;
import com.etf.qa.example.pageobjects.GoogleSearchPage;
import com.etf.qa.webdriver.ScreenShotFactory;

public class SearchTest extends TestBase{

	@Test()
	public void GoogleSearchTest() {
		//Take note of Arrange, Act, Assert pattern - http://www.arrangeactassert.com/why-and-what-is-arrange-act-assert
		//Arrange
		
		GoogleSearchPage searchPage = new GoogleSearchPage(driver);
		
		ScreenShotFactory screenShot = new ScreenShotFactory();
		screenShot.createScreenShot(driver, "SearchTest","SearchTest", "Image 1");

		//Act
		searchPage.Search(prop.getProperty("searchTerm"));
		Reporter.log("Searching Google with term: " + prop.getProperty("searchTerm"));
		Helper.Wait(2);
		
		//screenShot.createScreenShot(driver, this.getClass().getName(), this.getClass().getEnclosingMethod().getName(), "Image 2");

		//Assert
		
		//Example negative assertion
		AssertJUnit.assertFalse(driver.getPageSource().contains("did not match any documents"));
		
		//Example positive assertion
		AssertJUnit.assertTrue(driver.getPageSource().contains("resultStats"));
		
		logger.info(driver.findElement(By.id("resultStats")).getText());
		Reporter.log("<br/>" + "Search Results Found: " + driver.findElement(By.id("resultStats")).getText());
	}
	
	
}//Class
