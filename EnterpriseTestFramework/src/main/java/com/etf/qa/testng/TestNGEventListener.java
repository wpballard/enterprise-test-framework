package com.etf.qa.testng;

import java.lang.annotation.Annotation;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dexmedia.qa.common.Global;
import com.dexmedia.qa.common.rally.RallyReporter;
import com.dexmedia.qa.common.rally.RallyTestCase;
import com.dexmedia.qa.common.webdriver.ScreenShotFactory;



public class TestNGEventListener implements ITestListener {
	
	final Logger logger = LoggerFactory.getLogger(TestNGEventListener.class);
	ScreenShotFactory screenShot = new ScreenShotFactory();
	private RallyReporter rallyReporter = null;
	private String strScreenShot = "";
	RallyTestCase rallyTestCase = null;
	
	public TestNGEventListener()
	{
		try {
			rallyReporter = new RallyReporter();
		} catch (URISyntaxException e) {
			logger.error(e.toString());
		}
	}


	@Override
	public void onTestStart(ITestResult result) {
		try {
			rallyReporter = new RallyReporter();
		} catch (URISyntaxException e) {
			logger.error(e.toString());
		}
		rallyTestCase = null;
		logger.info("Test Started: "+ result.getMethod().getMethodName().toString());	
		Annotation annotation = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(RallyTestCase.class);

		if (annotation != null) {
			rallyTestCase = (RallyTestCase) annotation;
			
			if(rallyTestCase.TestCaseTestSet()!=null) {
				
				ArrayList<String> al = new ArrayList<String>();
				Collections.addAll(al, rallyTestCase.TestCaseTestSet());
				
				 String[] arrRallyTestCaseTestSet = new String[rallyTestCase.TestCaseTestSet().length];
				 arrRallyTestCaseTestSet = rallyTestCase.TestCaseTestSet();
				
				//Reporter.log("Rally Test Case Number: " + arrRallyTestCaseTestSet);//may not show properly in report from onTestStart
				logger.info("Rally Test Case Number: " + rallyTestCase.TestCaseTestSet().toString());
			}
			
			if(rallyTestCase.TestCases()!=null) {
				
				ArrayList<String> al = new ArrayList<String>();
				Collections.addAll(al, rallyTestCase.TestCaseTestSet());
				
				 String[] arrRallyTestCases = new String[rallyTestCase.TestCases().length];
				 arrRallyTestCases= rallyTestCase.TestCases();
				 for(int i=0; i<arrRallyTestCases.length;i++) {
					 logger.info("Rally Test Case Number: " + arrRallyTestCases[i].toString());
				 }
				
			}
			
			if(!rallyTestCase.TestCase().isEmpty()) {
				Reporter.log("Rally Test Case Number: " + rallyTestCase.TestCase(), true);//may not show properly in report from onTestStart
				logger.info("Rally Test Case Number: " + rallyTestCase.TestCase());
			}
			
			
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		try {
			rallyReporter = new RallyReporter();
		} catch (URISyntaxException e) {
			logger.error(e.toString());
		}
		rallyTestCase = null;
		logger.info("Test Passed: "+ result.getMethod().getMethodName().toString());

		if(Global.getScreenshot())
		{
			result.setAttribute("screenshot", screenShot.createScreenShot(result));
		}
		
		
		Annotation annotation = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(RallyTestCase.class);
		rallyTestCase = (RallyTestCase) annotation;
		
		if (annotation != null) 
		{		
			
			 String[] arrRallyTestCaseTestSet = new String[rallyTestCase.TestCaseTestSet().length];
			 arrRallyTestCaseTestSet = rallyTestCase.TestCaseTestSet();
			 
			
			 if(!arrRallyTestCaseTestSet[0].isEmpty()) {
				//@RallyTestCase(TestCaseTestSet={"tc1,ts1","tc2,ts2","tc3,ts3"})
				 
				for(int i=0; i<arrRallyTestCaseTestSet.length;i++) {
					logger.info("");//force a new line
					logger.info("Rally Annotation: " + arrRallyTestCaseTestSet[i]);
					
					
					String[] arrRallyValues = arrRallyTestCaseTestSet[i].split(",");
					if (!result.getAttribute("screenshot").toString().isEmpty())
					{
						strScreenShot = result.getAttribute("screenshot").toString();
						if (Global.getRallyReport()) {rallyReporter.Report(arrRallyValues[0],arrRallyValues[1], strScreenShot, "Pass");}
						Reporter.log("<br/>"+"Rally Test Case Number: " + arrRallyValues[0], true);
						Reporter.log("<br/>"+"Rally Test Set Number: " + arrRallyValues[1], true);
						
					} else {
						if (Global.getRallyReport()) {rallyReporter.Report(arrRallyValues[0],arrRallyValues[1], "Pass");}
						Reporter.log("<br/>"+"Rally Test Case Number: " + arrRallyValues[0], true);
						Reporter.log("<br/>"+"Rally Test Set Number: " + arrRallyValues[1], true);
					}
					
					Reporter.log("<br/>"+"Rally Test Case Number: " + arrRallyValues[0], true);
					Reporter.log("<br/>"+"Rally Test Set Number: " + arrRallyValues[1], true);
					
				}//for	
			}//if TestCaseTestSet
			 
			 
			 String[] arrRallyTestCases = new String[rallyTestCase.TestCases().length];
			 arrRallyTestCases = rallyTestCase.TestCases();
			 
			 if(!arrRallyTestCases[0].isEmpty()) {
				 
				 for(int i=0; i<arrRallyTestCases.length;i++) {
					 
					 if (!result.getAttribute("screenshot").toString().isEmpty()) {
						 strScreenShot = result.getAttribute("screenshot").toString();
						 if (Global.getRallyReport()) {rallyReporter.Report(arrRallyTestCases[i],"", strScreenShot, "Pass");}
					 } else {
						 if (Global.getRallyReport()) {rallyReporter.Report(arrRallyTestCases[i],"", "Pass");}
					 }//screenshot else
					 Reporter.log("<br/>"+"Rally Test Case Number: " + arrRallyTestCases[i], true);
				}//for
			}//if TestCases
				 
				
			
			
			if(!rallyTestCase.TestCase().isEmpty()) {
				//@RallyTestCase(TestCase = "TC42436", TestSet="TS2410")
				//(Global.getScreenshot()
				//!result.getAttribute("screenshot").
				if (Global.getScreenshot() )
				{
					strScreenShot = result.getAttribute("screenshot").toString();
					if (Global.getRallyReport()) {rallyReporter.Report(rallyTestCase.TestCase().toString(), rallyTestCase.TestSet().toString(), strScreenShot, "Pass");}
					Reporter.log("<br/>"+"Rally Test Case Number: " + rallyTestCase.TestCase(), true);
					Reporter.log("<br/>"+"Rally Test Case Number: " + rallyTestCase.TestSet(), true);
				}
				else
				{
					logger.info("Screen shot not available in time for Rally Reporter on test pass");
					if (Global.getRallyReport()) {rallyReporter.Report(rallyTestCase.TestCase(), rallyTestCase.TestSet(), "Pass");}
					Reporter.log("<br/>"+"Rally Test Case Number: " + rallyTestCase.TestCase(), true);
					Reporter.log("<br/>"+"Rally Test Case Number: " + rallyTestCase.TestSet(), true);
				}//if
				
				Reporter.log("<br/>"+"Rally Test Case Number: " + rallyTestCase.TestCase(), true);
				Reporter.log("<br/>"+"Rally Test Case Number: " + rallyTestCase.TestSet(), true);
				
			}//if TestCase

		}//if RallyReport

		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			rallyReporter = new RallyReporter();
		} catch (URISyntaxException e) {
			logger.error(e.toString());
		}
		rallyTestCase = null;
		logger.info("Test Failed: "+ result.getMethod().getMethodName().toString());
		
		if(Global.getScreenshot())
		{
			result.setAttribute("screenshot", screenShot.createScreenShot(result));
		}
		
		Annotation annotation = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(RallyTestCase.class);
		rallyTestCase = (RallyTestCase) annotation;
		
		if (annotation != null) 
		{		
			 String[] arrRallyTestCaseTestSet = new String[rallyTestCase.TestCaseTestSet().length];
			 arrRallyTestCaseTestSet = rallyTestCase.TestCaseTestSet();
			
			 if(!arrRallyTestCaseTestSet[0].isEmpty()) {	
				//@RallyTestCase(TestCaseTestSet={"tc1,ts1","tc2,ts2","tc3,ts3"})
				 
				for(int i=0; i<arrRallyTestCaseTestSet.length;i++) {
					
					logger.info("Rally Annotation: " + arrRallyTestCaseTestSet[i]);
					String[] arrRallyValues = arrRallyTestCaseTestSet[i].split(",");
					if (!result.getAttribute("screenshot").toString().isEmpty())
					{
						strScreenShot = result.getAttribute("screenshot").toString();
						if (Global.getRallyReport()) {rallyReporter.Report(arrRallyValues[0],arrRallyValues[1], strScreenShot, "Fail");}

					} else {
						if (Global.getRallyReport()) {rallyReporter.Report(arrRallyValues[0],arrRallyValues[1], "Fail");}

					}
					
					Reporter.log("<br/>"+"Rally Test Case Number: " + arrRallyValues[0], true);
					Reporter.log("<br/>"+"Rally Test Set Number: " + arrRallyValues[1], true);
										
				}//for	
			}//if TestCaseTestSet
			 
			 
			 String[] arrRallyTestCases = new String[rallyTestCase.TestCases().length];
			 arrRallyTestCases = rallyTestCase.TestCases();
			 
			 if(!arrRallyTestCases[0].isEmpty()) {
				 
				 for(int i=0; i<arrRallyTestCases.length;i++) {
					 
					 if (!result.getAttribute("screenshot").toString().isEmpty()) {
						 strScreenShot = result.getAttribute("screenshot").toString();
						 if (Global.getRallyReport()) {rallyReporter.Report(arrRallyTestCases[i],"", strScreenShot, "Fail");}
					 } else {
						 if (Global.getRallyReport()) {rallyReporter.Report(arrRallyTestCases[i],"", "Fail");}
					 }//screenshot else
					 Reporter.log("<br/>"+"Rally Test Case Number: " + arrRallyTestCases[i], true);
				}//for
			}//if TestCases
			
			
			if(!rallyTestCase.TestCase().isEmpty()) {
				//@RallyTestCase(TestCase = "TC42436", TestSet="TS2410")
				if (!result.getAttribute("screenshot").toString().isEmpty())
				{
					strScreenShot = result.getAttribute("screenshot").toString();
					if (Global.getRallyReport()) {rallyReporter.Report(rallyTestCase.TestCase().toString(), rallyTestCase.TestSet().toString(), strScreenShot, "Fail");
					}
				}
				else
				{
					logger.info("Screen shot not available in time for Rally Reporter on test pass");
					if (Global.getRallyReport()) {rallyReporter.Report(rallyTestCase.TestCase(), rallyTestCase.TestSet(), "Fail");}
				}//if
				
				Reporter.log("<br/>"+"Rally Test Case Number: " + rallyTestCase.TestCase(), true);
				Reporter.log("<br/>"+"Rally Test Case Number: " + rallyTestCase.TestSet(), true);
				
			}//if TestCase

		}//if RallyReport
		
//		if (annotation != null) 
//		{
//			
//			//Reporting to ReportNG here so it shows in individual test notes
//			Reporter.log("<br/>"+"Rally Test Case Number: " + rallyTestCase.TestCase());
//			if (Global.getRallyReport() && result.getAttribute("screenshot") != null)
//			{				
//				strScreenShot = result.getAttribute("screenshot").toString();
//				rallyReporter.Report(rallyTestCase.TestCase(), rallyTestCase.TestSet(), strScreenShot, "Fail");
//			}	
//			else if (Global.getRallyReport())
//			{
//				logger.info("Screen shot not available in time for Rally Reporter on test fail");
//				rallyReporter.Report(rallyTestCase.TestCase(), rallyTestCase.TestSet(), "Fail");
//			}
//		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		try {
			rallyReporter = new RallyReporter();
		} catch (URISyntaxException e) {
			logger.error(e.toString());
		}
		rallyTestCase = null;
		logger.info("Test Skipped: " + result.getMethod().getMethodName().toString());
		//result.setAttribute("screenshot", screenShot.createScreenShot(result));
		
		Annotation annotation = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(RallyTestCase.class);
		rallyTestCase = (RallyTestCase) annotation;
		
//		if (annotation != null) 
//		{
//			//Reporting to ReportNG here so it shows in individual test notes
//			Reporter.log("<br/>"+"Rally Test Case Number: " + rallyTestCase.TestCase());
//			if (Global.getRallyReport())
//			{
//				rallyReporter.Report(rallyTestCase.TestCase(), rallyTestCase.TestSet(), "Blocked");
//			}
//		}
		
		if (annotation != null) 
		{		
			 String[] arrRallyTestCaseTestSet = new String[rallyTestCase.TestCaseTestSet().length];
			 arrRallyTestCaseTestSet = rallyTestCase.TestCaseTestSet();
			
			 if(!arrRallyTestCaseTestSet[0].isEmpty()) {	
				//@RallyTestCase(TestCaseTestSet={"tc1,ts1","tc2,ts2","tc3,ts3"})
				 
				for(int i=0; i<arrRallyTestCaseTestSet.length;i++) {
					
					logger.info("Rally Annotation: " + arrRallyTestCaseTestSet[i]);
					String[] arrRallyValues = arrRallyTestCaseTestSet[i].split(",");

					if (Global.getRallyReport()) {rallyReporter.Report(arrRallyValues[0],arrRallyValues[1], "Blocked");}
					Reporter.log("<br/>"+"Rally Test Case Number: " + arrRallyValues[0], true);
					Reporter.log("<br/>"+"Rally Test Set Number: " + arrRallyValues[1], true);
										
				}//for	
	
			}//if TestCaseTestSet
			 
			 
			 String[] arrRallyTestCases = new String[rallyTestCase.TestCases().length];
			 arrRallyTestCases = rallyTestCase.TestCases();
			 
			 if(!arrRallyTestCases[0].isEmpty()) {
				 
				 for(int i=0; i<arrRallyTestCases.length;i++) {
					 if (Global.getRallyReport()) {rallyReporter.Report(arrRallyTestCases[i],"", "Blocked");}
					 Reporter.log("<br/>"+"Rally Test Case Number: " + arrRallyTestCases[i], true);					 
				}//for
			}//if TestCases
			
			
			if(!rallyTestCase.TestCase().isEmpty() && rallyTestCase.TestCase() !=null ) {
				//@RallyTestCase(TestCase = "TC42436", TestSet="TS2410")
		
					logger.info("Screen shot not available in time for Rally Reporter on test pass");
					if (Global.getRallyReport()) {rallyReporter.Report(rallyTestCase.TestCase(), rallyTestCase.TestSet(), "Blocked");}
					
				
			}//if TestCase
			
			Reporter.log("<br/>"+"Rally Test Case Number: " + rallyTestCase.TestCase(), true);
			Reporter.log("<br/>"+"Rally Test Case Number: " + rallyTestCase.TestSet(), true);

		}//if RallyReport
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		logger.info("Test Failed: "+ result.getMethod().getMethodName().toString());
		if(Global.getScreenshot())
		{
			///NOT FULLY IMPLEMENTED
			//result.setAttribute("screenshot", screenShot.createScreenShot(result));		
		}
	}

	@Override
	public void onStart(ITestContext context) {

		logger.info("Test Class Started: " + context.getClass().getName().toString());
		
	}

	@Override
	public void onFinish(ITestContext context) {

		logger.info("Test Class Finished: " + context.getClass().getName().toString());
		
	}
	
}
