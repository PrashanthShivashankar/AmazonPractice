package com.amazon.qa.pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
	By searchitems=By.xpath("//input[@id='twotabsearchtextbox']");
	By ItemSearchButton=By.xpath("//input[@id='nav-search-submit-button']");
	By ItemPriceTag=By.xpath("//span[@class='a-price-whole']");
	By selectFilerXpath=By.id("s-result-sort-select");
	
	public String getTitle(WebDriver driver)
	{
		return driver.getTitle();
	}
	
	public String signInOptions(WebDriver driver)
	{
		UtilHelper.mouseOverAction(driver, driver.findElement(singUpOptions));
		UtilHelper.exlicitWaitForElement(driver, driver.findElement(signInButton));
		driver.findElement(signInButton).click();
		UtilHelper.exlicitWaitForElement(driver, driver.findElement(userName));
		return driver.getTitle();
		
	}
	
	public String  signIn(WebDriver driver, String username, String password)
	{
		UtilHelper.exlicitWaitForElement(driver, driver.findElement(userName));
		driver.findElement(userName).sendKeys(username);
		driver.findElement(continueButton).click();
		UtilHelper.exlicitWaitForElement(driver, driver.findElement(UserPassword));
		driver.findElement(UserPassword).sendKeys(password);
		return driver.getTitle();
		
	}
	
	public List<WebElement> productSearch(WebDriver driver, String itemName)
	{
		driver.findElement(searchitems).sendKeys(itemName);
		driver.findElement(ItemSearchButton).click();
		List<WebElement> itemsPriceList=driver.findElements(ItemPriceTag);
		return itemsPriceList;
		
	}
	public String ItemSelector(WebDriver driver,String itemPrice)
	{
		String itemPath="//span[text()='"+itemPrice+"']/ancestor::a";
		driver.findElement(By.xpath(itemPath)).click();
		return driver.getTitle();
	}
	
	public void SelectFilter(WebDriver driver, String filter)
	{
		UtilHelper.selectOption(driver.findElement(selectFilerXpath), filter);
	}
	
	
	

}
