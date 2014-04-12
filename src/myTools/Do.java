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
	
	public WebElement what(String locatorname) throws MyException {
		try{
			return driver.findElement(By.xpath(xpath.getValue(locatorname)));
		}catch(Exception e){
			throw new MyException("请查看xpath数据是否存在？");
		}
	}

	public WebElement find(String webelement)throws MyException{
		try{
		return driver.findElement(By.xpath(webelement));
		}catch(Exception e){
			throw new MyException("元素不存在");
		}
	}
	public List<WebElement> finds(String webelement) throws MyException{
		try{
			return driver.findElements(By.xpath(webelement));
		}catch(Exception e){
			throw new MyException("元素不存在");
		}

	}
	public List<WebElement> whats(String locatorname) throws MyException{
		try{
			return driver.findElements(By.xpath(xpath.getValue(locatorname)));
		}catch(Exception e){
			throw new MyException("请查看xpath数据是否存在？");
		}

	}
	
	public void waitForElementPresent(String locatorname) throws MyException{
		try{
			waiter.waitForElementPresent(xpath.getValue(locatorname));
		}catch(Exception e){
			throw new MyException("元素咋还不出现呢？！");
		}

	}
	
	public void waitForElementIsEnable(String locatorname) throws MyException{
		try{
			waiter.waitForElementIsEnable(xpath.getValue(locatorname));
		}
		catch(Exception e){
			throw new MyException("元素咋一直不能用呢？！");
		}
}
	
	public void waitFor(long timeout) throws MyException{
		try{
			waiter.waitFor(timeout);
		}
		catch(Exception e){
			throw new MyException("我等太久了。。。");
		}

	}

}
