package com.amazon.qa.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.amazon.qa.main.BaseClass;
import com.amazon.qa.pages.HomePage;
import com.amazon.qa.utils.UtilHelper;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class LoginPageFunctionalityTest extends BaseClass
{
	HomePage homepage;
	Logger logger;
	ExtentHtmlReporter htmlReporter;
	ExtentReports report;
	ExtentTest reportlogger;
	
	@BeforeTest
	public void beforetest()
	{
		homepage=new HomePage();
		logger=Logger.getLogger("LogFileGeneration");
		PropertyConfigurator.configure("Log4j.properties");
		htmlReporter=new ExtentHtmlReporter("C:\\Users\\prash\\amazon\\SeleniumPracticeV1JuneAmazon\\test-report1\\new1.html");
		report=new ExtentReports();
		report.attachReporter(htmlReporter);
		
	}
	
	@BeforeMethod
	public void setUpDriver()
	{
		
		reportlogger=report.createTest("Test cases");
		webDriverIntialization();
		logger.info("Starting Application");
	}
	
	@Test(priority=1)
	public void homePageTitlle()
	{
		reportlogger.log(Status.INFO,"Verify title");
		Assert.assertEquals(homepage.getTitle(driver), driver.getTitle());
		reportlogger.log(Status.INFO,"Verified title");
		
	}
	
	@Test(priority=2, dependsOnMethods="homePageTitlle")
	public void verifySignIn()
	{
		reportlogger.log(Status.INFO,"Verify SignIn");
		logger.info("verify signIn functinality testcase");
		homepage.signInOptions(driver);
		String title=homepage.signIn(driver, prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(title, driver.getTitle());
		reportlogger.log(Status.INFO,"Signin title");
		
	}
	
	@Test(priority=3, dependsOnMethods="verifySignIn") //, dependsOnMethods="verifySignIn"
	public void selectItem() throws InterruptedException 
	{
		reportlogger.log(Status.INFO,"Verify Select Item");
		logger.info("Verify items price list");
		
		List<WebElement> list=homepage.productSearch(driver, "office chairs for home below 5000");
		ArrayList<Integer> array=new ArrayList<Integer>();
		for(WebElement temp:list)
		{
			if(temp.getText().contains(","))
			{
				array.add(Integer.parseInt(temp.getText().replace(",", "")));
			}
			else if(temp.getText().isEmpty())
			{
				continue;
			}
			else
			{
				array.add(Integer.parseInt(temp.getText()));
			}
			
		}
		Collections.sort(array);
		String itemPrice=Integer.toString(array.get(0));
		homepage.SelectFilter(driver, "Price: Low to High");
		String title=homepage.ItemSelector(driver, itemPrice);
		Thread.sleep(3000);		
		logger.info("Validation sucessfull");
		Assert.assertEquals(title,driver.getTitle());
		reportlogger.log(Status.INFO,"Product select title");
		
		
		
	}
	@AfterMethod
	public void tearDown(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			try {
				
					String screenshotPath=UtilHelper.takeTestScreenshot(driver, result.getName());				
					reportlogger.fail(result.getThrowable().getMessage());
					reportlogger.fail("Screenshot below :" + reportlogger.addScreenCaptureFromPath(screenshotPath));
					
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		
		
		driver.quit();
	}
	
	@AfterTest
	public void aftertest()
	{
		report.close();
		report.flush();
	}

}
