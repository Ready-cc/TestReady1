package Test.xigua.collect;

import myTools.Do;
import myTools.MyException;
import myTools.ParseProperties;
import myTools.ScreenHot;
import myTools.Switch;
import myTools.Wait;
import myTools.WriteAndReadtxt;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import prac.browser.BrowserInit;
import prac.browser.BrowserType;

public class Merchant {


	private WebDriver driver;
	private Do du;
	private Wait wait;
	private Switch swtichw;
	private ParseProperties sends = new ParseProperties(System.getProperty("user.dir")+"\\tool\\sendkeys.properties");
	private ParseProperties xp = new ParseProperties(System.getProperty("user.dir")+"\\tool\\xpath.properties");
	WriteAndReadtxt fio =null;
	private String caseName;
	
	@BeforeClass
	public void startBrowser(){
		BrowserInit browser = new BrowserInit(BrowserType.chrome);
		driver = browser.driver;
		du = new Do(driver);
		wait = new Wait(driver);
		swtichw=new Switch(driver);
		fio = new WriteAndReadtxt();
		driver.manage().window().maximize();
	}	
	@Test
	public void logOn(){
		driver.get("http://merchant.xigua365.com/");
//		driver.findElement(By.xpath("//div[@class='ft']/descendant::a[contains(text(),'登录')]")).click();
		driver.findElement(By.name("account_name")).sendKeys("chenchunfeng@yourmall.com");
		driver.findElement(By.name("passwd")).sendKeys("111111");
		driver.findElement(By.name("passwd")).submit();
	}

	@Test
	public void oderManage() throws MyException{
		caseName="fahuo";
		du.find("//span[text()='订单管理']").click();
		String orderid = fio.readTxtFile();
		du.find("//input[@id='order_id']").sendKeys(orderid);
		du.find("//button").click();
		wait.waitForElementPresent("//span[text()='发货']");
		du.find("//span[text()='发货']").click();
		du.find("//input[@id='wlcompany']").sendKeys("城市100");
		du.find("//input[@id='sendgoodsorders']").sendKeys("111111111111");
		du.find("//span[text()='确定']").click();
	}
/*	@AfterMethod
	public void updatereport(){  
		ScreenHot.screentest(driver, caseName);

	}*/
	@AfterClass
	public void releasBrowser(){
		driver.quit();
	}	

}
