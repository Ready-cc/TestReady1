package Test.xigua.collect;

import java.util.Set;

import myTools.ApiObject;
import myTools.Do;
import myTools.MyException;
import myTools.ParseProperties;
import myTools.Switch;
import myTools.Wait;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import prac.browser.BrowserInit;
import prac.browser.BrowserType;

public class BuyProduct {


	private WebDriver driver;
	private Do du;
	private Wait wait;
	private Switch swtichw;


	
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
		driver.get("http://www.xigua365.com/");
		driver.findElement(By.xpath("//div[@class='ft']/descendant::a[contains(text(),'登录')]")).click();
		driver.findElement(By.name("account_name")).sendKeys("ccf@ym.com");
		driver.findElement(By.name("passwd")).sendKeys("111111");
		driver.findElement(By.name("passwd")).submit();
		WebElement user = driver.findElement(By.className("user"));
		Assert.assertTrue(user.isDisplayed());

	}

	@Test
	public void search() throws MyException{
		du.find("//div[@class='search-box']/descendant::input[1]").sendKeys("ccf123");
		du.find("//button[text()='搜索按钮']").click();
	}
	@Test
	public void selectprodcut() throws MyException{
		du.find("//a[text()='100-1472']").click();
		swtichw.toSpecificWindow("100");
		du.find("//button[text()='立即购买']").click();
		swtichw.toSpecificWindow("填写订单信息");
		du.find("//button[text()='提交订单']").click();
		
	}

	@AfterClass
	public void releasBrowser(){
		driver.quit();
	}	
}