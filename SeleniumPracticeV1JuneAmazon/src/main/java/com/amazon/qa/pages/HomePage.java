package com.amazon.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.amazon.qa.utils.UtilHelper;

/*
 * This classs contains function to handel homepage functionalities
 * 
 */
public class HomePage 
{
	By singUpOptions = By.xpath("//span[@id='nav-link-accountList-nav-line-1']");
	By signInButton=By.xpath("//div[@id='nav-flyout-ya-signin']//span[text()='Sign in']");
	By userName=By.xpath("//input[@id='ap_email']");
	By continueButton=By.xpath("//input[@id='continue']");
	By UserPassword=By.xpath("//input[@id='ap_password']");
	By SignInSubmitButton=By.xpath("//input[@id='signInSubmit']");
	
	public String getTitle(WebDriver driver)
	{
		return driver.getTitle();
	}
	
	public String signInOptions(WebDriver driver)
	{
		UtilHelper.mouseOverAction(driver, driver.findElement(singUpOptions));
		driver.findElement(signInButton).click();
		UtilHelper.exlicitWaitForElement(driver, driver.findElement(userName));
		return driver.getTitle();
		
	}
	
	public String  signIn(WebDriver driver, String username, String password)
	{
		driver.findElement(userName).sendKeys(username);
		driver.findElement(continueButton).click();
		UtilHelper.exlicitWaitForElement(driver, driver.findElement(UserPassword));
		driver.findElement(UserPassword).sendKeys(password);
		return driver.getTitle();
		
	}
	
	
	
	

}
