package com.etf.qa.webdriver;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.etf.qa.common.Global;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class LocalDriverFactory {

//	private LocalDriverFactory() {}

	final Logger logger = LoggerFactory.getLogger(LocalDriverFactory.class);
	private static LocalDriverFactory instance = new LocalDriverFactory();

	public static LocalDriverFactory getInstance() {
		return instance;
	}

	public CustomEventFiringWebDriver getDriver() {
		//return (CustomEventFiringWebDriver) driver.get();
		return driver.get();
	}

	public void removeDriver() {

		//driver.get().quit();
		driver.remove();
	}

	
public void removeDriver(String strProxyPort) {
		
//		//Delete the proxy
//		ProxyApiManager proxyApiManager = new ProxyApiManager(Global.getProxyServerHost(), Global.getProxyServerPort());
//		String response = proxyApiManager.SendRequest("/proxy/"+ strProxyPort, "DELETE");
//		logger.info("Deleted Proxy Node:" + response);
		
		driver.get().quit();
		driver.remove();
	}
	
	// browserName{android|chrome|firefox|htmlunit|internetexplorer|iPhone|iPad|opera|safari}
	// platform {WINDOWS|XP|VISTA|MAC|LINUX|UNIX|ANDROID}.

	ThreadLocal<CustomEventFiringWebDriver> driver = new ThreadLocal<CustomEventFiringWebDriver>() {
		
		@Override
		protected CustomEventFiringWebDriver initialValue() {
			
//			ProxyApiManager proxyApiManager = new ProxyApiManager(Global.getProxyServerHost(), Global.getProxyServerPort());			
//			String strProxyPort =  new Gson().fromJson(proxyApiManager.FetchPort(), JsonObject.class).get("port").toString();
//			
//			Proxy proxy = new Proxy();	
//			proxy.setHttpProxy(Global.getProxyServerHost() + ":" + strProxyPort);
//			proxy.setSslProxy(Global.getProxyServerHost() + ":" + strProxyPort);
		     

			String browserName = Global.getBrowser();

			WebDriver driver = null;
			if (browserName.toLowerCase().contains("firefox")) {
				driver = getFirefoxLocalWebDriver();
			}
			if (browserName.toLowerCase().contains("internet")) {
				driver = getInternetExplorerLocalWebDriver();
			}
			
			if (browserName.toLowerCase().contains("chrome")) {
				driver = getChromeLocalWebDriver();
			}
		
			if (browserName.toLowerCase().contains("safari")) {
				driver = getSafariLocalWebDriver();
			}


			CustomEventFiringWebDriver customEventFiringWebDriver = new CustomEventFiringWebDriver(driver);
			customEventFiringWebDriver.register(new DriverEventListener());
//			customEventFiringWebDriver.setProxyPort(strProxyPort);
			return customEventFiringWebDriver;

		}// override
	};// threadlocal
	
private WebDriver getFirefoxLocalWebDriver() {
	
	WebDriver driver = null;
	
	FirefoxProfile firefoxProfile = new FirefoxProfile();
	//firefoxProfile.setPreference("browser.safebrowsing.malware.enabled", "true");
	firefoxProfile.setPreference("signon.autologin.proxy", "true");
	//firefoxProfile.setPreference("network.http.phishy-userpass-length", 255);
	firefoxProfile.setPreference("network.automatic-ntlm-auth.trusted-uris","http,https");
	firefoxProfile.setPreference("network.negotiate-auth.trusted-uris","http,https");
	firefoxProfile.setPreference("network.negotiate-auth.delegation-uris","http,https");
	firefoxProfile.setPreference("network.automatic-ntlm-auth.trusted-uris", "oktapreview.com,okta.com");
	firefoxProfile.setPreference("network.automatic-ntlm-auth.allow-non-fqdn", "true");
	firefoxProfile.setPreference("network.ntlm.send-lm-response", "true");				
	
	//network.negotiate-auth.allow-insecure-ntlm-v1
	firefoxProfile.setPreference("security.mixed_content.block_active_content", false);
	firefoxProfile.setPreference("security.mixed_content.block_display_content", true);
	firefoxProfile.setPreference("security.warn_leaving_secure",false);
	firefoxProfile.setPreference("security.warn_submit_insecure",false);
	firefoxProfile.setPreference("security.warn_leaving_secure.show_once", false);
	firefoxProfile.setPreference("security.warn_viewing_mixed",false);
	firefoxProfile.setPreference("security.warn_viewing_mixed.show_once", false);
	// Pre-populate the current URL and pre-fetch the certificate
	firefoxProfile.setPreference("browser.ssl_override_behavior", 2);
	firefoxProfile.setPreference("browser.tabs.warnOnOpen", false);
	firefoxProfile.setPreference("browser.tabs.warnOnClose", false);
	firefoxProfile.setPreference("browser.warnOnQuit", false);
	firefoxProfile.setEnableNativeEvents(true);

	DesiredCapabilities capabilities = DesiredCapabilities.firefox();	
 
		capabilities.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
		capabilities.setBrowserName("firefox");
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
//		capabilities.setCapability(CapabilityType.PROXY, proxy);
 
	logger.info("Local WebDriver Factory - Firefox");
	driver = new FirefoxDriver(capabilities);
		
		return driver;
	}

private WebDriver getChromeLocalWebDriver() {
	
	WebDriver driver = null;
	
	ChromeOptions chromeOptions = new ChromeOptions();
	chromeOptions.addArguments("test-type");
	//chromeOptions.add_experimental_option("excludeSwitches", ["ignore-certificate-errors"]);
	
	//excludeSwitche
	
	
	logger.info("Local WebDriver Factory - Chrome");
	System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
	DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
//	capabilities.setCapability(CapabilityType.PROXY, proxy);
	driver = new ChromeDriver(capabilities);
	
	return driver;
}
	
private WebDriver getSafariLocalWebDriver() {
		
		WebDriver driver = null;
		
		SafariOptions safariOptions = new SafariOptions();
		
		safariOptions.setUseCleanSession(true);
		logger.info("Local WebDriver Factory - Safari");
		
		DesiredCapabilities capabilities = DesiredCapabilities.safari();
		
		driver = new SafariDriver(capabilities);
		
		return driver;
	}
	
private WebDriver getInternetExplorerLocalWebDriver() {
	
	WebDriver driver = null;
	
	DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
	//capabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR,org.openqa.selenium.UnexpectedAlertBehaviour.DISMISS);
	capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	capabilities.setCapability("nativeEvents", true);
//	capabilities.setCapability(CapabilityType.PROXY, proxy);
	// capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);

	logger.info("Local WebDriver Factory - Internet Explorer");
	logger.info("InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR has been set to accept");

	System.setProperty("webdriver.ie.driver","src/test/resources/IEDriverServer.exe");
	driver = new InternetExplorerDriver(capabilities);
		
	return driver;
	}	

public CustomEventFiringWebDriver GetNewDriver() {
	
	String browserName = Global.getBrowser();

	WebDriver driver = null;
	if (browserName.toLowerCase().contains("firefox")) {
		driver = getFirefoxLocalWebDriver();
	}
	if (browserName.toLowerCase().contains("internet")) {
		driver = getInternetExplorerLocalWebDriver();
	}
	
	if (browserName.toLowerCase().contains("chrome")) {
		driver = getChromeLocalWebDriver();
	}

	if (browserName.toLowerCase().contains("safari")) {
		driver = getSafariLocalWebDriver();
	}
	
	CustomEventFiringWebDriver customEventFiringWebDriver = new CustomEventFiringWebDriver(driver);
	customEventFiringWebDriver.register(new DriverEventListener());
//	customEventFiringWebDriver.setProxyPort(strProxyPort);
	return customEventFiringWebDriver;
}

public CustomEventFiringWebDriver GetNewDriver(String browserName) {
	
	WebDriver driver = null;
	if (browserName.toLowerCase().contains("firefox")) {
		driver = getFirefoxLocalWebDriver();
	}
	if (browserName.toLowerCase().contains("internet")) {
		driver = getInternetExplorerLocalWebDriver();
	}
	
	if (browserName.toLowerCase().contains("chrome")) {
		driver = getChromeLocalWebDriver();
	}

	if (browserName.toLowerCase().contains("safari")) {
		driver = getSafariLocalWebDriver();
	}
	
	CustomEventFiringWebDriver customEventFiringWebDriver = new CustomEventFiringWebDriver(driver);
	customEventFiringWebDriver.register(new DriverEventListener());
//	customEventFiringWebDriver.setProxyPort(strProxyPort);
	return customEventFiringWebDriver;
}

}//Class


