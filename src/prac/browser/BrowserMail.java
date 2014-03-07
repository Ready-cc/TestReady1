package prac.browser;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class BrowserMail {
	private WebDriver driver;
	
	@BeforeClass
	public void start(){
		BrowserInit browser = new  BrowserInit(BrowserType.ie);
		driver =  browser.driver;
	}
	@Test
	public void logOn(){
		driver.get("http://mail.126.com");
		driver.findElement(By.id("idInput")).sendKeys("erdinghao");
//		driver.findElement(By.xpath("//input[@id='idInput']")).sendKeys("erdinghao");
		driver.findElement(By.id("pwdInput")).sendKeys("er15ding");
//		driver.findElement(By.xpath("//input[@id='pwdInput']")).sendKeys("er15ding");
		driver.findElement(By.id("loginBtn"));
		driver.findElement(By.xpath("//button[@id='loginBtn']")).click();	
//		System.out.println("登陆成功");
	}

	@Test
	public void writeLetter(){

		driver.findElement(By.xpath("//div[@id='dvNavTop']/descendant::span[contains(text(),'写 信')]")).click();
		driver.findElement(By.xpath("//div[@id='divComposeto']/descendant::input[2]")).sendKeys("erdinghao@126.com");
		driver.findElement(By.xpath("//div[@id='divComposeSubject']/descendant::input")).sendKeys("test-ready");
		driver.findElement(By.xpath("//header[@id='divComposeToolbar']/descendant::span[1]")).click();
		System.out.println("test写信1");	
	}

	@Test
	public void inBox(){
		//收件箱有时会定位不到
		driver.findElement(By.xpath("//div[@id='navtree']/descendant::span[@title='收件箱']")).click();
	}
	@Test
	@Parameters("index")
	public void checkMail(int index){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> bt = driver.findElements(By.xpath("//div[contains(@id,'_ContentDiv')]/descendant::div[contains(text(),'test')]"));
//		driver.findElements(By.cssSelector(".cq")).get(index).click();
		driver.findElements(By.xpath("//div[contains(@id,'_ContentDiv')]/descendant::div[contains(text(),'test')]")).get(index).click();
		for (int i = 0; i < bt.size(); i++) {
			System.out.println(bt.get(i).getText());
			}
	}
	
	@Test
	public void outBox(){
		driver.findElement(By.xpath("//div[@id='navtree']/descendant::span[(text()='已发送')]")).click();
		System.out.println("test已发送4");
	}
		
	@AfterClass
	public void releaseie(){
		driver.quit();
	}


}

