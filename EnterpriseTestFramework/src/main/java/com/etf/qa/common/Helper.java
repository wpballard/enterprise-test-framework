package com.etf.qa.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.internal.thread.TestNGThread;

public class Helper {

	final static Logger logger = LoggerFactory.getLogger(Helper.class);

	/***
	 * The dreaded static wait that no one is supposed to use (but we all do
	 * sometimes) Not the preferred wait method - but implemented ThreadSafe for
	 * TestNG
	 * 
	 * @param intSeconds
	 */
	public static void Wait(Integer intSeconds) {
		logger.info("Waiting " + intSeconds + " Seconds");
		try {
			TestNGThread.sleep(intSeconds * 1000);
		} catch (InterruptedException e) {
			logger.info("Threading Exception during sleep");
			logger.info(e.getMessage());
		}

		logger.info(intSeconds + " Second Wait Complete");
	}

	/***
	 * Switches from one window handle to the next
	 * 
	 * @param driver
	 */
	public static void SwitchWindow(WebDriver driver) {

		String strCurrentHandle = driver.getWindowHandle().toString();

		logger.info("Switching Windows. Current Window Handle: " + driver.getWindowHandle().toString());
		for (String winHandle : driver.getWindowHandles()) {
			if (winHandle.equals(strCurrentHandle)) {
				logger.info("Same Handle Found - not switching yet");
			} else {
				logger.info("Switching");
				driver.switchTo().window(winHandle);
			}

		}
		logger.info("Windows Switched. Current Window Handle: " + driver.getWindowHandle().toString());

	}

	/***
	 * Accepts WebElement that should be an HTML select tag (dropdown) on the
	 * page Accepts text string to search for in the dropdown values Performs
	 * the select action by using actions and arrow keys. Survives DOM Stale
	 * Element exceptions
	 * 
	 * @param webelement
	 * @param text
	 */
	public static void SelectDropDownOptionByArrowDown(WebElement webelement, int optionIndex, WebDriver driver) {

		Select select = new Select(webelement);
		new Actions(driver).moveToElement(webelement).perform();
		int i = 0;

		do {
			new Actions(driver).sendKeys(Keys.ARROW_DOWN).perform();
			i++;
		} while (i < optionIndex);

		new Actions(driver).sendKeys(Keys.TAB).perform();// Tab out of field

	}

	/***
	 * Accepts WebElement that should be an HTML select tag (dropdown) on the
	 * page Accepts text string to search for in the dropdown values Performs
	 * the select action by using actions and arrow keys. Survives DOM Stale
	 * Element exceptions
	 * 
	 * @param webelement
	 * @param text
	 */
	public static void SelectDropDownOptionByArrowDown(WebElement webelement, String OptionText, WebDriver driver) {

		Select select = new Select(webelement);

		// int intSelectedIndex = select.getFirstSelectedOption().

		List<WebElement> lsOptionItems = select.getOptions();
		int i = 0;
		// identify the webElement
		for (WebElement webElement : lsOptionItems) {
			if (webElement.getText().trim().equals(OptionText.trim())) {
				logger.info("Found " + OptionText + " in " + webelement.getTagName());
				break;// exit the loop
			}
			i++;
		}
		// tab off and tab back to secure focus
		webelement.sendKeys(Keys.TAB);
		String selectAll = Keys.chord(Keys.SHIFT, Keys.TAB);
		new Actions(driver).sendKeys(selectAll).perform();

		// loop through to arrow to the selection
		int ii = 0;

		do {
			new Actions(driver).sendKeys(Keys.ARROW_DOWN).perform();
			ii++;
		} while (ii < i);

		new Actions(driver).sendKeys(Keys.TAB).perform();// Tab out of field

	}

	/***
	 * Accepts WebElement that should be an HTML select tag (dropdown) on the
	 * page Accepts text string to search for in the dropdown values Performs
	 * the select action; no return value
	 * 
	 * @param webelement
	 * @param text
	 */
	public static void SelectDropDown(WebElement webelement, String OptionText) {
		try {
			logger.info("Selecting " + OptionText + " from " + webelement.getTagName());
			Select select = new Select(webelement);
			select.selectByVisibleText(OptionText);
		} catch (Exception e) {
			logger.info("There was a problem making a selection in the dropdown.");
			logger.info("Error Message: " + e.getMessage());
		}

	}

	public static String GetDropdownSelectedItem(WebElement webElement) {

		Select select = new Select(webElement);

		if (select.getFirstSelectedOption() != null) {
			return select.getFirstSelectedOption().getText();
		}

		return "";

	}

	/***
	 * 
	 * @param webelement
	 * @param OptionText
	 * @return
	 */
	public static boolean isDropdownOptionElementPresent(WebElement webelement, String OptionText) {

		logger.info("Looking for " + OptionText + " from " + webelement.getTagName());

		Select select = new Select(webelement);

		List<WebElement> lsOptionItems = select.getOptions();

		for (WebElement webElement : lsOptionItems) {
			if (webElement.getText().equals(OptionText)) {

				logger.info("Found " + OptionText + " in " + webelement.getTagName());
				return true;
			}
		}

		logger.info("Did not find " + OptionText + " in " + webelement.getTagName());
		return false;

	}

	/***
	 * 
	 * @param seachText
	 * @param by
	 * @param driver
	 * @return
	 */
	public static boolean doesElementContainText(String seachText, By by, WebDriver driver) {

		return driver.findElement(by).getText().contains(seachText);
	}

	/***
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public static void handleAlert(WebDriver driver) throws Exception {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (NoAlertPresentException e) {
			System.out.println("Alert not caputured");
		}
	}


	/***
	 * 
	 * @param by
	 * @param driver
	 * @return
	 */
	public static boolean isElementPresent(By by, WebDriver driver) {
		try {
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

			if (driver.findElement(by).isEnabled()) {
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				return true;
			}

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return false;

		} catch (NoSuchElementException e) {
			logger.info("Element was not present");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return false;
		}
	}

	/***
	 * 
	 * @param webElement
	 * @param driver
	 * @return
	 */
	public static boolean isElementPresent(WebElement webElement, WebDriver driver) {

		try {

			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

			if (webElement.isEnabled()) {
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				return true;
			}

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			return false;

		} catch (NoSuchElementException e) {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			logger.info("Element was not present");

			return false;
		}
	}

	/***
	 * 
	 * @param strFrom
	 * @param strTo
	 * @param strSubject
	 * @param strBody
	 */
	public static void SendEmail(String strFrom, String strTo, String strSubject, String strBody) {

		Transport transport;
		try {
			Properties properties = new Properties();
			properties.put("mail.smtp.host", Global.getSmtpHost());
			properties.put("mail.transport.protocol", "smtp");
			// properties.setProperty("mail.smtp.auth", "true"); // not
			// necessary for my server
			Session session = Session.getInstance(properties, null);
			transport = session.getTransport("smtp");
			transport.connect();

			Message message = new MimeMessage(session);
			message.setSubject(strSubject);
			message.setText(strBody);
			message.setFrom(new InternetAddress(strFrom));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(strTo));
			transport.sendMessage(message, message.getAllRecipients());

		} catch (MessagingException e) {
			logger.info("Error in Helper SendEmail.");
			e.printStackTrace();
		}

	}

	/***
	 * 
	 * @return
	 */
	public static Properties loadPageObjectProperties() {
		Properties prop = new Properties();

		try {
			prop.load(PageBase.class.getResourceAsStream("/" + Global.getPageObjectPropertiesFileName()));

		} catch (IOException e) {
			e.printStackTrace();
			logger.info("Unable to load EnvironmentUrls.Properties file");
			System.exit(0);
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.info("Unable to load EnvironmentUrls. EnvironmentUrls.properties file. File null.");
			System.exit(0);

		}

		return prop;

	}

	/***
	 * 
	 * @return
	 */
	public static Properties loadTestDataProperties() {

		// Load the properties file
		Properties prop = new Properties();

		try {
			logger.info("Loading TestData.properties");
			prop.load(TestBase.class.getResourceAsStream("/" + Global.getTestDataPropertiesFileName()));

		} catch (IOException e) {
			e.printStackTrace();
			logger.info("Unable to load TestData.Properties file");
			System.exit(0);
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.info("Unable to load TestData.Properties file. File null.");
			System.exit(0);

		}

		return prop;
	}

}// class
