package Test;

import prac.browser.*;
import myTools.Do;
import myTools.MyException;
import myTools.Wait;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import prac.browser.BrowserType;

public class Dydt {
	public WebDriver driver;
	public Do du;
	public Wait wait;
	
	@BeforeClass
	public void init(){
		BrowserInit browser = new BrowserInit(BrowserType.chrome);
		driver = browser.driver;
		du = new Do(driver);
		wait = new Wait(driver);
	}
	@Test
	public void logOn() throws MyException{
		driver.get("http://www.dydt.net/forum.php?mod=viewthread&tid=292880&pid=14145398&page=33&extra=#pid14145398");
		du.find("//input[@name='username']").sendKeys("奈落达尔");
		du.find("//input[@name='password']").sendKeys("er15ding");
		du.find("//em[contains(text(),'登录')]").click();
		wait.waitForElementIsEnable("//textarea[@name='message']");
	}
	@Test
	public void reply() throws MyException{
		for(int i =0;i<2;i++){
		du.find("//textarea[@name='message']").sendKeys("RE: 【04.22日截】八戒面膜续团。 [修改]");
		du.find("//button[@value='replysubmit']").click();
		wait.waitFor(15000);
		}
	}
	@AfterClass
	public void logOut(){
		driver.close();
		driver.quit();
	}
	
	

}
