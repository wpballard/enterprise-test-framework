package com.etf.qa.webdriver;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.logging.Level;

import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ThreadGuard;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.etf.qa.common.Global;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class RemoteDriverFactory {
	
//	Q: Is WebDriver thread-safe?
//	A: WebDriver is not thread-safe. Having said that, if you can serialise access to the underlying driver instance, 
//	you can share a reference in more than one thread. This is not advisable. 
//	You /can/ on the other hand instantiate one WebDriver instance for each thread.

//	private RemoteDriverFactory() {}
	
	private static final Logger logger = LoggerFactory.getLogger(RemoteDriverFactory.class);
	
	private static  final RemoteDriverFactory instance = new RemoteDriverFactory();

	public static RemoteDriverFactory getInstance() {
		return instance;
	}

	public CustomEventFiringWebDriver getDriver() {
		return (CustomEventFiringWebDriver) driver.get();
		//return driver.get();
	}

	public void removeDriver() {
		//driver.get().close();
		driver.get().quit();
		driver.remove();
	}
	
///
//	Override for Proxy
///
public void removeDriver(String strProxyPort) {
		
//		//Delete the proxy
//		ProxyApiManager proxyApiManager = new ProxyApiManager(Global.getProxyServerHost(), Global.getProxyServerPort());
//		String response = proxyApiManager.SendRequest("/proxy/"+ strProxyPort, "DELETE");
//		logger.info("Deleted Proxy Node:" + response);
		
		driver.get().quit();
		driver.remove();
	}
	

	// browserName{android|chrome|firefox|htmlunit|internet
	// explorer|iPhone|iPad|opera|safari}
	// platform {WINDOWS|XP|VISTA|MAC|LINUX|UNIX|ANDROID}.

	ThreadLocal<CustomEventFiringWebDriver> driver = new ThreadLocal<CustomEventFiringWebDriver>()
	{
		@Override
		protected CustomEventFiringWebDriver initialValue() {
			
//			ProxyApiManager proxyApiManager = new ProxyApiManager(Global.getProxyServerHost(), Global.getProxyServerPort());			
//			String strProxyPort =  new Gson().fromJson(proxyApiManager.FetchPort(), JsonObject.class).get("port").toString();
//			logger.info("Remote Proxy Port: " + strProxyPort);
//			Proxy proxy = new Proxy();	
//			proxy.setHttpProxy(Global.getProxyServerHost() + ":" + strProxyPort);
//			proxy.setSslProxy(Global.getProxyServerHost() + ":" + strProxyPort);
					
			
			Proxy proxy = new Proxy();	
			proxy.setHttpProxy("");
			proxy.setSslProxy("");
					
			
			String browserName = Global.getBrowser();
			RemoteWebDriver driver = null;

			if (browserName.toLowerCase().contains("firefox")) {
				driver = getFirefoxRemoteWebDriver();	
			}
			if (browserName.toLowerCase().contains("internet")) {	
				driver = getInternetExplorerRemoteWebDriver();
			}
			if (browserName.toLowerCase().contains("chrome")) {	
				driver = getChromeRemoteWebDriver();
			}
			if (browserName.toLowerCase().contains("safari")) {	
				driver = getSafariRemoteWebDriver();
			}
				
			//for remote file upload
			driver.setFileDetector(new LocalFileDetector());
			
			CustomEventFiringWebDriver customEventFiringWebDriver = new CustomEventFiringWebDriver(driver);
			customEventFiringWebDriver.register(new DriverEventListener());
//			customEventFiringWebDriver.setProxyPort("");
			return customEventFiringWebDriver;
			
		}
	};
	
//	 WebDriver driver = ThreadGuard.protect(new FirefoxDriver());

	
private RemoteWebDriver getFirefoxRemoteWebDriver() {
		
		RemoteWebDriver driver = null;		
//		CustomEventFiringWebDriver customEventFiringWebDriver = new CustomEventFiringWebDriver(driver);
		
//		ProxyApiManager proxyApiManager = new ProxyApiManager(Global.getProxyServerHost(), Global.getProxyServerPort());			
//		String strProxyPort =  new Gson().fromJson(proxyApiManager.FetchPort(), JsonObject.class).get("port").toString();
//		logger.info("Remote Proxy Port: " + strProxyPort);
//		Proxy proxy = new Proxy();	
//		proxy.setHttpProxy(Global.getProxyServerHost() + ":" + strProxyPort);
//		proxy.setSslProxy(Global.getProxyServerHost() + ":" + strProxyPort);
		
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		
		firefoxProfile.setAcceptUntrustedCertificates(true);
		
		firefoxProfile.setPreference("signon.autologin.proxy", "true");
		firefoxProfile.setPreference("network.automatic-ntlm-auth.trusted-uris","http,https");
		firefoxProfile.setPreference("network.negotiate-auth.trusted-uris","http,https");
		firefoxProfile.setPreference("network.negotiate-auth.delegation-uris","http,https");
		firefoxProfile.setPreference("network.automatic-ntlm-auth.allow-non-fqdn", "true");
		firefoxProfile.setPreference("network.automatic-ntlm-auth.trusted-uris", "oktapreview.com,okta.com");
		firefoxProfile.setPreference("network.ntlm.send-lm-response", "true");		
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
	
		logger.info("Remote WebDriver Factory - Firefox");	

		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
		capabilities.setBrowserName("firefox");
		capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setPlatform(Platform.valueOf(Global.getPlatform()));// defaults to WINDOWS
		capabilities.setCapability("name", "Remote File Upload using Selenium 2's FileDetectors");
//		capabilities.setCapability(CapabilityType.PROXY, proxy);

		if (Global.getBrowserVersion() != null) {
			capabilities.setVersion(Global.getBrowserVersion());
		}

		try {
			driver = new RemoteWebDriver(new URL(Global.getGridUrl()),capabilities);
		} catch (MalformedURLException e) {
			logger.info("RemoteDriverFactory Malformed URL Exception");
			e.printStackTrace();
		}
		
		return driver;
		
	}
	
private RemoteWebDriver getChromeRemoteWebDriver() {
		
		RemoteWebDriver driver = null;
		
		logger.info("Remote WebDriver Factory - Chrome");
//		logger.info("Empty Proxy Assigned");
		
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--ignore-certificate-errors");
		//chromeOptions.addArguments("--test-type");
		//chromeOptions.addArguments("test-type");

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		//capabilities.setCapability("chrome.switches", Arrays.asList("--incognito"));
		capabilities.setBrowserName("chrome");
		capabilities.setPlatform(Platform.valueOf(Global.getPlatform()));
		capabilities.setCapability("name", "Remote File Upload using Selenium 2's FileDetectors");
//		capabilities.setCapability(CapabilityType.PROXY, proxy);

		if (Global.getBrowserVersion() != null) {
			capabilities.setVersion(Global.getBrowserVersion());
		}

		try {
			driver = new RemoteWebDriver(new URL(Global.getGridUrl()),
					capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return driver;
	}

private RemoteWebDriver getSafariRemoteWebDriver() {
	
	RemoteWebDriver driver = null;	
	
	SafariOptions safariOptions = new SafariOptions();
	safariOptions.setUseCleanSession(true);
	logger.info("Remote WebDriver Factory - Safari");
	
	DesiredCapabilities capabilities = DesiredCapabilities.safari();
	capabilities.setBrowserName("safari");
	capabilities.setPlatform(Platform.valueOf(Global.getPlatform()));
	capabilities.setCapability("name", "Remote File Upload using Selenium 2's FileDetectors");
	if (Global.getBrowserVersion() != null) {
		capabilities.setVersion(Global.getBrowserVersion());
	}
	
	try {
		driver = new RemoteWebDriver(new URL(Global.getGridUrl()),
				capabilities);
	} catch (MalformedURLException e) {
		e.printStackTrace();
	}
	
	
	return driver;
	
}

private RemoteWebDriver getInternetExplorerRemoteWebDriver() {
	
	RemoteWebDriver driver = null;	
	
	DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
	capabilities.setBrowserName("internet explorer");
	capabilities.setPlatform(Platform.valueOf(Global.getPlatform()));
	capabilities.setCapability("name", "Remote File Upload using Selenium 2's FileDetectors");
	capabilities.setCapability("nativeEvents", true);
	capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	
//	capabilities.setCapability(CapabilityType.PROXY, proxy);

	if (Global.getBrowserVersion() != null) {
		capabilities.setVersion(Global.getBrowserVersion());
	}
	
	logger.info("Remote WebDriver Factory - Internet Explorer");
	logger.info("InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR has been set to accept");
	logger.info("Internet Explorer driver screen shot functionality impaired. Screen shots may be black or blank.");

	try {
		driver = new RemoteWebDriver(new URL(Global.getGridUrl()),capabilities);
	} catch (MalformedURLException e) {
		e.printStackTrace();
	}

	return driver;
	
}

public CustomEventFiringWebDriver GetNewDriver(){
	
//	Proxy proxy = new Proxy();	
//	proxy.setHttpProxy("");
//	proxy.setSslProxy("");			
	
	String browserName = Global.getBrowser();

	RemoteWebDriver driver = null;
	//WebDriver driver = ThreadGuard.protect(new FirefoxDriver());
	
	if (browserName.toLowerCase().contains("firefox")) {
		driver = getFirefoxRemoteWebDriver();
		//driver = ThreadGuard.protect(driver);
	}

	if (browserName.toLowerCase().contains("internet")) {
		driver = getInternetExplorerRemoteWebDriver();
	}

	if (browserName.toLowerCase().contains("chrome")) {	
		driver = getChromeRemoteWebDriver();
	}
	
	if (browserName.toLowerCase().contains("safari")) {
		driver = getSafariRemoteWebDriver();	
	}
	
	//for remote file upload
	driver.setFileDetector(new LocalFileDetector());
	
	CustomEventFiringWebDriver customEventFiringWebDriver = new CustomEventFiringWebDriver(driver);
	customEventFiringWebDriver.register(new DriverEventListener());
	//customEventFiringWebDriver.setProxyPort("");
	return customEventFiringWebDriver;
	
}

public CustomEventFiringWebDriver GetNewDriver(String browserName){
	
//	Proxy proxy = new Proxy();	
//	proxy.setHttpProxy("");
//	proxy.setSslProxy("");			

	RemoteWebDriver driver = null;
	//WebDriver driver = ThreadGuard.protect(new FirefoxDriver());
	
	if (browserName.toLowerCase().contains("firefox")) {
		driver = getFirefoxRemoteWebDriver();
		//driver = ThreadGuard.protect(driver);
	}

	if (browserName.toLowerCase().contains("internet")) {
		driver = getInternetExplorerRemoteWebDriver();
	}

	if (browserName.toLowerCase().contains("chrome")) {	
		driver = getChromeRemoteWebDriver();
	}
	
	if (browserName.toLowerCase().contains("safari")) {
		driver = getSafariRemoteWebDriver();	
	}
	
	//for remote file upload
	driver.setFileDetector(new LocalFileDetector());
	
	CustomEventFiringWebDriver customEventFiringWebDriver = new CustomEventFiringWebDriver(driver);
	customEventFiringWebDriver.register(new DriverEventListener());
	//customEventFiringWebDriver.setProxyPort("");
	return customEventFiringWebDriver;
	
}




}// class




