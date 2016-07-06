package com.etf.qa.tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.etf.qa.common.Global;
import com.etf.qa.common.Helper;


public class MultithreadTest {
	
//		
//	@Test(invocationCount = 5, threadPoolSize=2)
//	@RallyTestCase(TestCase = "TC35837")
//	public void SampleTest(){
//		
//		//Driver Start
//		
//		WebDriver driver = null;
//		
//		if(Global.getUseGrid())
//		{
//			//driver = RemoteDriverFactory.getInstance().getDriver();
//			RemoteDriverFactory remoteDriverFactory = new RemoteDriverFactory();
//			driver = remoteDriverFactory.GetNewDriver();
//		}
//		else
//		{
//			driver = LocalDriverFactory.getInstance().getDriver();
//		}
//		
//		//Test Start
//		
//		driver.navigate().to("http://www.google.com");
//		WebElement txtSearchTerm = driver.findElement(By.name("q"));
//		txtSearchTerm.sendKeys("Star Wars");
//		WebElement btnSearch = driver.findElement(By.name("btnG"));
//		
//		Actions actions = new Actions(driver);
//		actions.moveToElement(btnSearch).click().perform();
//
//		Helper.Wait(20);
//		
//		//Driver cleanup
//
//		driver.quit();
//		
//		if(Global.getUseGrid())
//		{
//			//RemoteDriverFactory.getInstance().removeDriver();
//		}
//		else
//		{
//			LocalDriverFactory.getInstance().removeDriver();
//		}
//		
//		
//	
//	}
	
	

}
