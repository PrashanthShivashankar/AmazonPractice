package com.amazon.qa.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
 * read property file
 * Initiate webDriver
 */
public class BaseClass 
{
public WebDriver driver;
public Properties prop;
public FileInputStream file;

/*
 * Read the property files when class is loaded is created 
 */
public BaseClass()
{
	try 
	{
		file=new FileInputStream("F:\\Python-Programs\\SeleniumPracticeV1JuneAmazon\\src\\main\\java\\com\\amazon\\qa\\config\\config.properties");
		prop=new Properties();
		prop.load(file);
	
	} 
	catch (FileNotFoundException e) 
	{
		
		e.printStackTrace();
	} 
	catch (IOException e) 
	{
		
		e.printStackTrace();
	}
	
}

/*
 * webDriverIntialization function is used to intialize the webdriver 
 * Initialize
 */
public void webDriverIntialization()
{
	ChromeOptions options=new ChromeOptions();
	options.addArguments("enable-automation");
	options.addArguments("start-maximized");
	//options.addArguments("--headless");
	//options.addArguments("--window-size=1920,1080"); 
	if(prop.getProperty("browser").equalsIgnoreCase("chrome"))
	{
		System.setProperty("webdriver.chrome.driver", "F:\\Salenium- Leaaring-Software\\chromedriver.exe");
		driver=new ChromeDriver(options);
		
	}
	else if(prop.getProperty("browser").equalsIgnoreCase("firefox"))
	{
		System.setProperty("webdriver.gecko.driver","SeleniumPracticeV1JuneAmazon/Drivers/geckodriver.exe");
		driver=new FirefoxDriver();
		
	}

	driver.manage().deleteAllCookies();
	driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.get(prop.getProperty("url"));
}
	
}
