package com.amazon.qa.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



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

}
