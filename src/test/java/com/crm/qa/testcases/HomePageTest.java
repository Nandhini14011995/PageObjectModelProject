package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.PrintProductsPage;



public class HomePageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	PrintProductsPage printProductsPage;
	
	public HomePageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		loginPage = new LoginPage();
		homePage = loginPage.validateLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test	(priority=1)
	public void homePageTitleTest() {
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Retail Home | Print Online | FedEx Office");
	}
	
	@Test	(priority=2)
	public void  homePageLogoTest() {
		boolean flag = loginPage.validateLoginPageLogo();
		Assert.assertTrue(flag);
	}
	
	@Test (priority=3)
	public void avatarTest() {
		boolean flag = homePage.validateAvatar();
		Assert.assertTrue(flag);
	}
	
	@Test (priority=4)
	public void printReadyTest() {
		boolean flag = homePage.validatePrintReady();
		Assert.assertTrue(flag);
	}
	
	@Test (priority=5)
	public void canvaTest() {
		boolean flag = homePage.validateCanva();
		Assert.assertTrue(flag);
	}
	
	@Test	(priority=6)
	public void UploadFileTest() throws InterruptedException{
		printProductsPage = homePage.validateUploadFile();
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
