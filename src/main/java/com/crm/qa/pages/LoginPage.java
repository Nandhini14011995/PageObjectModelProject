package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;


public class LoginPage extends TestBase{
	
	//Page Factory - OR:
	
	/*
	 * CacheLookup will store the memory of webelement.
	 * it will not interact with findBy to find the webelement overtime as it has already stored the webelement in memory
	 * So this approach will improve the performance of the code.
	 */
	
	
	@FindBy(xpath="//span[text()='Sign Up or Log In']")
	@CacheLookup
	WebElement login;
	
	@FindBy(xpath="//div[@class='logo office-logo layout horizontal center']")
	WebElement logo;
	
	@FindBy(xpath="//input[@id='custom-input-0']")
	WebElement username_Input;
	
	@FindBy(xpath="//input[@id='custom-input-1']")
	WebElement password_Input;
	
	@FindBy(xpath="//span[text()='LOG IN']")
	WebElement login_Btn;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	public boolean validateLoginPageLogo() {
		return logo.isDisplayed();
	}
	
	public HomePage validateLogin(String us, String Pwd) throws InterruptedException{
		login.click();
		username_Input.sendKeys(us);
		password_Input.sendKeys(Pwd);
		login_Btn.click();
		Thread.sleep(5000);
		return new HomePage();
	}
}
