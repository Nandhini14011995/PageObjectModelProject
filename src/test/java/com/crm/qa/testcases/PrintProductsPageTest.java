package com.crm.qa.testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.PrintProductsPage;
import com.crm.qa.pages.WorkSpacePage;
import com.crm.qa.util.TestUtil;



public class PrintProductsPageTest extends TestBase {

	
	LoginPage loginPage;
	HomePage homePage;
	PrintProductsPage printProductsPage;
	WorkSpacePage workSpacePage;
	TestUtil testUtil;
	

	
	
	public PrintProductsPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		loginPage = new LoginPage();		
		homePage = loginPage.validateLogin(prop.getProperty("username"), prop.getProperty("password"));
		printProductsPage = homePage.validateUploadFile();
	}
	
	@Test	(priority=1)
	public void printProductsPageTitleTest() {
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Product Navigator | Print Online | FedEx Office");
	}
	
	@Test		(priority=2)
	public void  headerTest() {
		boolean flag = printProductsPage.validateHeader();
		Assert.assertTrue(flag);
	}
	
	@Test		(priority=3)
	public void  documentCategoryTest() {
		boolean flag = printProductsPage.validateDocumentCategory();
		Assert.assertTrue(flag);
	}
	
	
	
	@DataProvider
	public Object[][] getProductTestData() throws Exception {
		testUtil = new TestUtil();
		Object[][] tabArray = testUtil.getTableArray(prop.getProperty("sheetName"));
		return (tabArray);
	}
	


	
	@Test	(priority=4, dataProvider="getProductTestData")
	public void UploadFileTest(Hashtable Hasttabledata){
		System.out.println(Hasttabledata.get("Product").toString());
		workSpacePage = printProductsPage.validateSelectFedExProduct(Hasttabledata.get("Product").toString());
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
