package prac.browser;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

import myTools.*;
import static myTools.PrintMain.*;

	public class BrowserMail {
	private Wait wait;	
	private WebDriver driver;
	private ParseProperties sends = new ParseProperties(System.getProperty("user.dir")+"\\tool\\sendkeys.properties");
	private ParseProperties xp = new ParseProperties(System.getProperty("user.dir")+"\\tool\\xpath.properties");
	
	
	@BeforeClass
	public void start(){
		BrowserInit browser = new  BrowserInit(BrowserType.ie);
		driver =  browser.driver;
		wait = new Wait(driver);
	}
	@Test
	public void logOn(){
		driver.get(sends.getValue("url"));
		driver.findElement(By.xpath(xp.getValue("lgname"))).clear();
		driver.findElement(By.xpath(xp.getValue("lgname"))).sendKeys(sends.getValue("lgname"));
		driver.findElement(By.xpath(xp.getValue("lgpwd"))).sendKeys(sends.getValue("lgpwd"));
		driver.findElement(By.xpath(xp.getValue("lgpwd"))).submit();
		System.out.println("登陆成功");
	}
	@Test
	public void writeLetter(){
		driver.findElement(By.xpath(xp.getValue("writebutton"))).click();
		driver.findElement(By.xpath(xp.getValue("addres"))).sendKeys(sends.getValue("addres"));
		driver.findElement(By.xpath(xp.getValue("title"))).sendKeys(sends.getValue("title"));
		driver.findElement(By.xpath(xp.getValue("sendbutton"))).click();
		System.out.println("test写信1");	
	}
	@Test
	public void inBox(){
		driver.findElement(By.xpath(xp.getValue("inboxbutton"))).click();
		wait.waitForElementPresent(xp.getValue("inboxtitle"));
		driver.findElement(By.xpath(xp.getValue("inboxtitle"))).click();
	}
	@Test
	public void outBox(){
		driver.findElement(By.xpath(xp.getValue("sentbutton"))).click();
		System.out.println("test已发送4");
	}

	
	@Test
	public void draft(){
		int lth=0;
		int number=0;
		int page=0;
		int count=0;
		driver.findElement(By.xpath(xp.getValue("dratftbutton1"))).click();
		WebElement dratfm1 = driver.findElement(By.xpath(xp.getValue("dratftbutton2")));
		lth = dratfm1.getText().length();
		number = Integer.parseInt(dratfm1.getText().substring(1, lth-1));
	 	page=Integer.valueOf(driver.findElement(By.xpath("//span[@class='nui-select-text']")).getText().split("/")[1]);
	 	while(page>1){
	 		wait.waitFor(5000);
	 		List<WebElement> pagebtns = driver.findElements(By.xpath("//div[@title='下一页']/span[2]"));
	 		try{
//	 			翻页操作后，翻页按钮会累加，通过get(int index)方法按索引取元素
	 			WebElement pagebtn = pagebtns.get(pagebtns.size()-1);
	 			pagebtn.click();
		 	}
		 	catch(Exception e){
	 			break;
	 		}
	 		page--;
	 		count = driver.findElements(By.xpath("//b[contains(@title,'草稿')]")).size();

	 	}
	 	System.out.println("当前草稿箱数量 " +count);
	 	System.out.println("当前草稿页数 " +page);
	 	Assert.assertEquals(number, count);

	}
	
	@AfterClass
	public void releaseie(){
		driver.quit();
	}


}

