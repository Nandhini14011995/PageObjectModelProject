package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;


public class PrintProductsPage extends TestBase{
	
	//Page Factory - OR:
	
	@FindBy(xpath="//h2[text()='Print Products']")
	WebElement header;
	
	//Title, header should be inherited
	
	@FindBy(xpath="//h2[text()='Documents']")
	WebElement documentCategory;
	
	
	public PrintProductsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean validateHeader() {
		return header.isDisplayed();
	}
	
	public boolean validateDocumentCategory() {
		return documentCategory.isDisplayed();
	}
	
	public WorkSpacePage validateSelectFedExProduct(String productName) {
		driver.findElement(By.xpath("//div[contains(text(), \""+productName+"\")]")).click();
		return new WorkSpacePage();
	}
	
}
