package com.amazon.qa.testcases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazon.qa.main.BaseClass;
import com.amazon.qa.pages.HomePage;

public class LoginPageFunctionalityTest extends BaseClass
{
	HomePage homepage;
	Logger logger;
	
	public  LoginPageFunctionalityTest()
	{
		homepage=new HomePage();
		logger=Logger.getLogger("LogFileGeneration");
		PropertyConfigurator.configure("Log4j.properties");
	}
	
	@BeforeMethod
	public void setUpDriver()
	{
		webDriverIntialization();
		logger.info("Starting Application");
	}
	
	@Test(priority=1)
	public void homePageTitlle()
	{
		Assert.assertEquals(homepage.getTitle(driver), driver.getTitle());
		logger.info("Title verified sucessfully");
	}
	
	@Test(priority=2)
	public void verifySignIn()
	{
		logger.info("verify signIn functinality testcase");
		homepage.signInOptions(driver);
		String title=homepage.signIn(driver, prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(title, driver.getTitle());
		logger.info("Sign in functinality verified ");
	}
	@AfterMethod
	public void tearDown()
	{
		driver.close();
	}

}
