package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.internal.EventFiringMouse;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;


import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static Logger log = Logger.getLogger(TestBase.class);
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver edriver;
	public static WebEventListener eventListener;

	

	public TestBase() {
		PropertyConfigurator.configure("log4j.properties");
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					"\\Users\\nandh\\eclipse-workspace\\PageObjectModelProject\\src\\main\\java\\com\\crm"+"\\qa\\config\\config.properties");
			prop.load(ip);
			log.info("Fetching properties file values");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.error("File is not file in given path");
		}catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void initialization() {
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if(browserName.equals("ff")) {
			
		}
		edriver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		edriver.register(eventListener);
		driver = edriver;	
		log.info("edriver initialized");
		
	
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		log.info("URL launched :" + prop.getProperty("url"));
		
	}

}
