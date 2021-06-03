package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase{

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;
	public static String SHEET_PATH = "C:\\\\Users\\\\nandh\\\\eclipse-workspace\\\\SeleniumInterviewPreparation\\\\DataProvider.xlsx";
	
	
	private XSSFWorkbook wb;
	private XSSFSheet sh;
	private XSSFCell Cell;
	private XSSFRow Row;
	
	
	// Read the Excel data
		public Object[][] getTableArray(String SheetName) throws Exception {
			Object[][] tabArray = null;
			try {
				// we will convert the path to java readable format
				FileInputStream ExcelFile = new FileInputStream(SHEET_PATH);
				// Access the required test data from the respective sheet
				wb = new XSSFWorkbook(ExcelFile);
				sh = wb.getSheet(SheetName);
				// wb.getSheetAt(0);
				// Optional Temporary Variable which is going to store the cell
				// values
				int ci, cj;
				
				// Total number of rows
				int totalRows = sh.getLastRowNum();
				// From the rows how many columns/cells
				int noOfColumns = sh.getRow(totalRows).getLastCellNum();
				int col = noOfColumns - 1;
				System.out.println("The total cells(column) : " + col);
				tabArray = new Object[totalRows][1];
				ci = 0;
				Hashtable<Object, Object> table = null;
				// Row values
				for (int i = 0; i < totalRows; i++, ci++) {		
					
					table = new Hashtable<Object,Object>();					
					cj = 0;
					// Column values
					for (int j = 0; j <= col; j++, cj++) {
						
						//tabArray[ci][cj] = getCellData(i, j);// data from the excel
						table.put(getCellData(0, j), getCellData(i+1, j));	
						
						//System.out.println("The values for "+i+"th row"+" and "+j+"th col" +": " + tabArray[ci][cj]);
					}
					
					tabArray[i][0]=table;
				}
			} catch (FileNotFoundException e) {
				System.out.println("Could not read the Excel sheet");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Could not read the Excel sheet");
				e.printStackTrace();
			}
			return (tabArray);
		}

		public String getCellData(int RowNum, int ColNum) throws Exception {
			try {
				Cell = sh.getRow(RowNum).getCell(ColNum);
				String CellData = Cell.getStringCellValue();
				System.out.println(RowNum+" And "+ColNum+"Cell data:"+CellData);
				return CellData;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw (e);
			}
		}
	
	
		public  void takeScreenshotAtEndOfTest() throws IOException {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String currentDir = System.getProperty("user.dir");
			FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
		}
	
	
	
}

	

