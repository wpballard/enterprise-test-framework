package com.etf.qa.tests;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.UnknownHostException;


import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.AssertJUnit;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.etf.qa.common.Global;
import com.etf.qa.rally.RallyTestCase;
import com.etf.qa.webdriver.ScreenShotFactory;
import com.etf.qa.common.Helper;
import com.etf.qa.common.TestBase;
import com.google.gson.*;



public class DebugTest extends TestBase {
   
//	@Test(dependsOnMethods = "LoadContractTab")
//	@Test(dependsOnMethods = "DependencyTest")
	//Test Case = TC42436 //TC35837
//	@RallyTestCase(TestCase = "TC42436", TestSet="TS2410")
	
//	@RallyTestCase(TestCaseTestSet={"TC42436,TS2410","TC42436,TS2410"})
//	@RallyTestCase(TestCaseTestSet={"TC42436,TS2410"})
	@Test()
	@RallyTestCase(TestCases = "TC42436,TC35837")
	public void SampleTest(){
//		public void SampleTest(ITestContext testContext){
		
		//This is for framework debugging only
		//Page objects not used in this case 
		//Not a suitable model for production test code
		
		//ProxyApiManager proxyApiManager = new ProxyApiManager(Global.getProxyServerHost(), Global.getProxyServerPort());
		//proxyApiManager.AddHeader(proxyPort, "Authorization", "Basic YmFsbGF3MDE6YlJleGVAVDMz");
		
		//Helper.basicAuthenticate(proxyPort, domain, username, password)
		//Helper.basicAuthenticate(proxyPort, "rhdroot.com", "ballaw01", "bRexe@T33");
		
		//Helper.ntlmAuthenticate(proxyPort, "rhdroot.com", "ballaw01", "bRexe@T33");
				
		
		driver.navigate().to("http://www.google.com");
		
		WebElement txtSearchTerm = driver.findElement(By.name("q"));
		
//		DexElement dexElementInterface = driver.findElement(By.name("q"));
//		DexElement dexElement =  driver.findElement(By.name("q"));	
//		DexElement dexElement =  (DexElement)driver.findElement(By.name("q"));	
//		DexWebElementImpl dElement = new DexWebElementImpl(driver.findElement(By.name("q")));	
//		String strTagName = dElement.getTagName();	
//		ScreenShotFactory screenShot = new ScreenShotFactory();
//		screenShot.createScreenShot(driver, this.getClass().getName(), this.getClass().getEnclosingMethod().getName(), "MyImageName");	
		//Reporter.log("SOMETHING HERE: " + screenShot.createScreenShot(driver, this.getClass().getName(), "SampleTest", "MyImageName"));	
//		testContext.getCurrentXmlTest().addParameter(key, value);	
		//testContext.setAttribute("MYTESTATTRIBUTE", "SOME STRING IN REPORT");	
		//ITestResult testResult = testContext.getCurrentXmlTest().addParameter(key, value);	
		//ITestResult result
//		Looks like we need to access the ITestContext and 
//		get the ITestResult and add the screenshot attribute to it from the actual test method.
		
		Helper.isElementPresent(txtSearchTerm, driver);
		txtSearchTerm.sendKeys("Star Wars");
		
//		screenShot.createScreenShot(driver, this.getClass().getName(), "SampleTest", "BeforeSearch");
		//Reporter.log("SOMETHING HERE: " + screenShot.createScreenShot(driver, this.getClass().getName(), "SampleTest", "BeforeSearch"));
		
		WebElement btnSearch = driver.findElement(By.name("btnG"));	
		btnSearch.click();	
		Helper.Wait(5);
		
//		screenShot.createScreenShot(driver, this.getClass().getName(), "SampleTest", "After");
//		Actions actions = new Actions(driver);
//		actions.moveToElement(btnSearch).click().perform();
//		Helper.Wait(5);	
	
	}
	
//	@Test
//	@RallyTestCase(TestCase = "TC42436", TestSet="TS2410")
//	public void SampleTestTwo(){
//		//This is for framework debugging only
//		//Page objects not used in this case 
//		//Not a suitable model for production test code
//		
//		driver.navigate().to("http://www.google.com");
//		WebElement txtSearchTerm = driver.findElement(By.name("q"));
//		txtSearchTerm.sendKeys("Star Wars");
//		WebElement btnSearch = driver.findElement(By.name("btnG"));
//		btnSearch.click();
//		
//	}
//	
//	
//	
//	@Test
//	@RallyTestCase(TestCaseTestSet={"TC42436,TS2410","TC42436,TS2410"})
//	public void SampleTestThree(){
//		//This is for framework debugging only
//		//Page objects not used in this case 
//		//Not a suitable model for production test code
//		
//		driver.navigate().to("http://www.google.com");
//		WebElement txtSearchTerm = driver.findElement(By.name("q"));
//		txtSearchTerm.sendKeys("Star Wars");
//		WebElement btnSearch = driver.findElement(By.name("btnG"));
//		btnSearch.click();
//		
//	}

}//Class
