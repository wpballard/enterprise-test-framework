package com.etf.qa.webdriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.etf.qa.common.TestBase;

public class ScreenShotFactory {
	
	protected final static Logger logger = LoggerFactory.getLogger(ScreenShotFactory.class);
	
	public String createScreenShot(ITestResult result)
	{
		
		  //get a reference to the test class in use
		  Object currentClass = result.getInstance();
		  //Get driver instance singleton from base class underneath the test class
		  if (((TestBase) currentClass).getDriver() != null)
		  {
			  WebDriver driver = ((TestBase) currentClass).getDriver();
			  
			  if (driver.getClass().getName().equals("org.openqa.selenium.remote.RemoteWebDriver")) {
					 //if remoteDriver, need to augment the class with screenshot functionality
				      driver = new Augmenter().augment(driver);
				    } //if RemoteWebDriver
			  			  
			    File fileScreenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

				try {
					
					//Save file to reporting directories
					SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
			        String date = sdf.format(new Date());
			        
			        File fileSurefireCopy = new File("target/surefire-reports/html/ScreenShot-" + date + "-" + currentClass.getClass().getName() + "." + result.getMethod().getMethodName() + ".png");
					FileUtils.copyFile(fileScreenShot, fileSurefireCopy);
					
					
					String reportLink = "<a href=\"ScreenShot-"+ date + "-" + currentClass.getClass().getName() + "." + result.getMethod().getMethodName() + ".png\">Screen Shot - " + currentClass.getClass().getName() +"</a>" ;
							
					Reporter.log("<br/>" + reportLink, true);
					
					return fileSurefireCopy.getAbsolutePath();
				
				} catch (IOException e) {
					e.printStackTrace();
					Reporter.log("error generating screenshot for " + currentClass.getClass().getName());
				}
				
			  
		  }//if
		  
		return "";

	}//screenShot
	
	public String createScreenShot(WebDriver driver, String className, String methodName, String imageName)
	{
		
		
		logger.info("*********Screenshot Called");
		 
			  if (driver.getClass().getName().equals("org.openqa.selenium.remote.RemoteWebDriver")) {
					 //if remoteDriver, need to augment the class with screenshot functionality
				      driver = new Augmenter().augment(driver);
				    } //if RemoteWebDriver
			  			  
			    File fileScreenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

				try {
					
					
					
					//Save file to reporting directories
					SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
			        String date = sdf.format(new Date());
			        			        
			        logger.info("*********Screenshot - Handling File");
			        
			        File fileSurefireCopy = new File("target/surefire-reports/html/ScreenShot-" + date + "-" + className + "." + methodName  + imageName + ".png");
					FileUtils.copyFile(fileScreenShot, fileSurefireCopy);
					
					
					String reportLink = "<a href=\"ScreenShot-"+ date + "-" + className + "." + methodName  + imageName + ".png\">Screen Shot - " + methodName + imageName +"</a>" ;
							
					logger.info("*********Screenshot- File Copied");
					
					Reporter.log("<br/>" + reportLink + "<br/>", true);
					
					
					logger.info("*********Screenshot - Returning Link: " +  reportLink);
					return reportLink;
				
				} catch (IOException e) {
					e.printStackTrace();
					 
					Reporter.log("error generating screenshot for " + className + "." + methodName + "." + imageName);
				}
				
			  
		  
		return null;

	}//screenShot

}
