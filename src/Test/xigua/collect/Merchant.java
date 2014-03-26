package Test.xigua.collect;

import myTools.Do;
import myTools.ParseProperties;
import myTools.Switch;
import myTools.Wait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
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
	
	@BeforeClass
	public void startBrowser(){
		BrowserInit browser = new BrowserInit(BrowserType.chrome);
		driver = browser.driver;
		du = new Do(driver);
		wait = new Wait(driver);
		swtichw=new Switch(driver);
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
	public void ProductManage(){
		this.logOn();
		du.find("//span[text()='商品管理']").click();
	}
	
	@AfterClass
	public void releasBrowser(){
		driver.quit();
	}	

}
