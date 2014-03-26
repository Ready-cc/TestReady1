package Test.xigua.collect;


import java.util.List;

import myTools.Do;
import myTools.ParseProperties;
import myTools.Switch;
import myTools.Wait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import prac.browser.BrowserInit;
import prac.browser.BrowserType;

public class CheckOut {
	private WebDriver driver;
	private Do du;
	private Wait wait;
	private Switch swtichw;
	String bank;
	/*
	private ParseProperties sends = new ParseProperties(System.getProperty("user.dir")+"\\tool\\sendkeys.properties");
	private ParseProperties xp = new ParseProperties(System.getProperty("user.dir")+"\\tool\\xpath.properties");
	*/
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
//		driver.navigate().back();
//		wait.waitFor(2000);
	}
	@Test
	public void checkout(){
		driver.get("http://www.xigua365.com/shoppingcart/confirmOrderFromCartProductDeailPage?product_id=100-1472&attr_id=6106&product_count=2");
		du.find("//button[@id='J_Order_Submit']").click();
		wait.waitForElementPresent("//button[contains(text(),'下一步')]");
		WebElement nextButton = du.find("//button[contains(text(),'下一步')]");
		Assert.assertTrue(nextButton.isDisplayed());
	}
	@Test
	public void pay(){
		if(du.find("//input[@id='balancePay']").isSelected()){
			du.find("//input[@id='balancePay']").click();
		}
		List<WebElement> cards = du.finds("//div[@class='m-tab']/descendant::span");
		cards.get(0).click();
		List<WebElement> banks = du.finds("//div[@class='pay-option-body']/descendant::a");
//		List<WebElement> banksbtton = du.finds("//div[@class='pay-option-body']/descendant::input");
		//div[@class='pay-option-body']/descendant::input
		for(int i =1;i<=banks.size();i++){
			banks.get(i).click();
			bank=banks.get(i).getAttribute("title");
			du.find("//button[contains(text(),'下一步')]").click();
			
			Assert.assertEquals(bank,du.find("//p[@data-role='title-pay']").getAttribute("title"));
//			wait.waitFor(2000);
			driver.navigate().back();
			
		}
		wait.waitFor(2000);
		/*
		banks.get(0).click();
		bank=banks.get(0).getAttribute("title");
		du.find("//button[contains(text(),'下一步')]").click();
		Assert.assertEquals(bank,du.find("//p[@data-role='title-pay']").getAttribute("title"));
		*/
	}
	@AfterClass
		public void release(){
		driver.close();
		driver.quit();
	}
}
