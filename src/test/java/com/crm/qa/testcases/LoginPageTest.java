package com.crm.qa.testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;



public class LoginPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	
	public LoginPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
	}
	
	@Test	(priority=1)
	public void loginPageTitleTest() {
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Retail Home | Print Online | FedEx Office");
	}
	
	@Test		(priority=2)
	public void  loginPageLogoTest() {
		boolean flag = loginPage.validateLoginPageLogo();
		Assert.assertTrue(flag);
	}
	
	@DataProvider
	public Object[][] getProductTestData() throws Exception {
		testUtil = new TestUtil();
		Object[][] tabArray = testUtil.getTableArray(prop.getProperty("sheetName"));
		return (tabArray);
	}
	
	@Test	(priority=3, dataProvider="getProductTestData")
	public void loginTest(Hashtable data) throws InterruptedException{
	homePage = 	loginPage.validateLogin(data.get("Username").toString(), data.get("Password").toString());
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
