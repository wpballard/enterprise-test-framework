package com.etf.qa.example.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.etf.qa.common.PageBase;

public class GoogleSearchPage extends PageBase {

	public GoogleSearchPage(WebDriver driver) {
		super(driver);
		
		this.ConfirmUrl(baseUrls.getProperty("GoogleUrl"));
		
		PageFactory.initElements(driver, this);
	}
	
	
	//declare page elements
	@FindBy(how=How.NAME, using="q") //@FindBy(name="pw") - shorthand
	private WebElement txtSearchTerm;
	private WebElement btnG;
	
	//page methods
	 public GoogleSearchPage Search(String strSearchTerm) {
		 		 
		 txtSearchTerm.clear();
		 txtSearchTerm.sendKeys(strSearchTerm);
		 
		 btnG.click();
		 return this;
	 }

}
