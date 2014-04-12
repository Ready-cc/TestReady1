package Test.xigua.collect;


import java.util.List;
import java.util.Set;


import myTools.*;
import Test.xigua.collect.Prodcut;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
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
	JDBCTest test;
	WriteAndReadtxt fio =null;
	String sql = "select send_body from sms_queue where to_phone = 18610066360 ORDER BY create_time desc limit 1";
	private String caseName;
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
		fio = new WriteAndReadtxt();
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
	public void checkout() throws MyException{
		String url = null;
		Prodcut p = new Prodcut();
		driver.get(p.prod("100-1472",2));
		du.find("//textarea").sendKeys("测试备注");
		wait.waitForElementPresent("//button[@id='J_Order_Submit']");
		du.find("//button[@id='J_Order_Submit']").click();
		wait.waitForElementPresent("//button[contains(text(),'下一步')]");
//		保存订单号
		url= driver.getCurrentUrl();
		String orderid = url.substring(68,92);
		System.out.println(url.substring(68,92));
		fio.writeFile(orderid, "D:/orderid.txt");
		fio.writeline(orderid, "D:/temporderid.txt");
		WebElement nextButton = du.find("//button[contains(text(),'下一步')]");
		Assert.assertTrue(nextButton.isDisplayed());
	}
	@Test
	public void pay() throws MyException{
		caseName ="pay";
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
	/**
	 * 使用余额支付
	 * @throws MyException 
	 */
	@Test
	public void banlancepay() throws MyException{
		du.find("//button[contains(text(),'下一步')]").click();
		wait.waitFor(1000);
		test = new JDBCTest("sms_queue");
		test.connection("smp", "writeuser", "parkland100");
		String ss = test.exesql(sql);
		du.find("//input[@name='check_code']").sendKeys(ss.substring(0,6));
		wait.waitForElementPresent("//button[text()='去支付']");
		du.find("//button[text()='去支付']").click();
		wait.waitForElementPresent("//a[text()='查看我的订单']");
		
	}
	@Test
	public void shouhuo() throws MyException{
		caseName ="shouhuo";
		Actions moveToE = new Actions(driver);
		WebElement user = driver.findElement(By.className("userAvatar"));
		moveToE.moveToElement(user).build().perform();
		wait.waitForElementPresent("//a[contains(text(),'我的订单')]");
		du.find("//a[contains(text(),'我的订单')]").click();
		String orderid = fio.readTxtFile();
		du.find("//input[@name='order_id']").sendKeys(orderid);
//		du.find("//button[text()='搜索']").click();
		wait.waitForElementPresent("//button[text()='确认收货']");
		du.find("//button[text()='确认收货']").click();
		wait.waitForElementPresent("//button[text()='确定']");
		du.find("//button[text()='确定']").click();
		wait.waitFor(1000);
	}
/*	@AfterMethod
	public void updatereport(){  
		ScreenHot.screentest(driver, caseName);
	}*/
	@AfterClass
		public void release(){
		driver.close();
		driver.quit();
	}
}
