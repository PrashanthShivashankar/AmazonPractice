package com.amazon.qa.utils;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;



public class UtilHelper 
{
	
	

	public static void mouseOverAction(WebDriver driver,WebElement element)	
	{
		 Actions action=new Actions(driver);
		action.moveToElement(element).perform();
				
	}
	
	public static void exlicitWaitForElement(WebDriver driver, WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void selectOption( WebElement element, String filter)
	{
		Select option=new Select(element);
		option.selectByVisibleText(filter);
	}
	
	public static String  takeTestScreenshot(WebDriver driver, String screenshotName) 
	{
		try
		{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		String  destinatio="F:\\Python-Programs\\ExtentReport\\Verision2 Reports\\" + screenshotName +".png";
		File dest=new File(destinatio);
		Files.copy(source, dest); 
		System.out.println("Screenshot taken");
		return destinatio;
		}
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
		
	}

}
