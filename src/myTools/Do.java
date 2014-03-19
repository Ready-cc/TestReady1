package myTools;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import myTools.ParseProperties;
import myTools.Wait;

public class Do {

	private WebDriver driver;
	private ParseProperties xpath = new ParseProperties(System.getProperty("user.dir")+"\\tool\\xpath.properties"); 
//	private ParseProperties sends = new ParseProperties(System.getProperty("user.dir")+"\\tool\\senkkeys.properties");
	
	private Wait waiter;
	
	public Do(WebDriver driver){
		this.driver = driver;	
		waiter = new Wait(driver);
	}
	
	public WebElement what(String locatorname){
		return driver.findElement(By.xpath(xpath.getValue(locatorname)));
	}
	
	public List<WebElement> whats(String locatorname){
		return driver.findElements(By.xpath(xpath.getValue(locatorname)));
	}
	
	public void waitForElementPresent(String locatorname){
		waiter.waitForElementPresent(xpath.getValue(locatorname));
	}
	
	public void waitForElementIsEnable(String locatorname){
		waiter.waitForElementIsEnable(xpath.getValue(locatorname));
	}
	
	public void waitFor(long timeout){
		waiter.waitFor(timeout);
	}

}
