package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;


public class HomePage extends TestBase{
	
	//Page Factory - OR:
	
	@FindBy(xpath="//div[@class='avatar initials ng-star-inserted']")
	WebElement avatar;
	
	//Logo, Title should be inherited from login page
	
	@FindBy(xpath="//h3[text()='Upload a Print-Ready File']")
	WebElement printReady;
	
	@FindBy(xpath="//h3[text()='Custom Design Templates']")
	WebElement canva;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean validateAvatar() {
		return avatar.isDisplayed();
	}
	
	public boolean validatePrintReady() {
		return printReady.isDisplayed();
	}
	
	public boolean validateCanva() {
		return canva.isDisplayed();
	}
	
	public PrintProductsPage validateUploadFile() throws InterruptedException{
		Thread.sleep(3000);	
			printReady.click();				
		return new PrintProductsPage();
	}
}
