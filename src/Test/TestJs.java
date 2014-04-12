package Test;

import java.io.File;

import myTools.Do;
import myTools.Switch;
import myTools.Wait;
import myTools.WriteAndReadtxt;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import prac.browser.BrowserInit;
import prac.browser.BrowserType;

public class TestJs {

	/**
	 * @param args
	 */
	private WebDriver driver;
	String filepath;
	String Alertpath;
	String uploadpath;
	Wait wait;
	
	@BeforeClass
	public void startBrowser(){
		BrowserInit browser = new BrowserInit(BrowserType.chrome);
		File file = new File("F:/webdriver/src/js.html");
		File Alert = new File("F:/webdriver/src/alert.html");
		File upload = new File("F:/webdriver/src/upload_file.html");
		filepath = "file:///"+file.getAbsolutePath();
		Alertpath = "file:///"+Alert.getAbsolutePath();
		uploadpath = "file:///"+upload.getAbsolutePath();
		System.out.printf("now accesss %s \n", filepath);
		driver = browser.driver;
//		driver.manage().window().maximize();
		wait = new Wait(driver);
	}	
	@Test
	public void testJs(){
		driver.get(filepath);
		wait.waitFor(1000);
		 ((JavascriptExecutor)driver).executeScript("$('#tooltip').fadeOut();");
		 wait.waitFor(1000);
		 WebElement button = driver.findElement(By.className("btn"));
		 wait.waitFor(1000);
         ((JavascriptExecutor)driver).executeScript("$(arguments[0]).fadeOut();", button);
         wait.waitFor(1000);
	}
	@Test
	public void testAlert(){
		driver.get(Alertpath);
	}
	@Test
	public void testUpload(){
		driver.get(uploadpath);
		driver.findElement(By.cssSelector("input[type=file]")).sendKeys("F:/webdriver/src/upload_file.html");
		 wait.waitFor(3000);
	}
	
	@Test
	public void testCookie(){
		driver.get("http://www.baidu.com");
		wait.waitFor(1000);
		System.out.println("登录前"+driver.manage().getCookies());
		driver.findElement(By.xpath("//a[@id='lb' and @name = 'tj_login']")).click();
		wait.waitFor(3000);
		driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("erdinghao");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("erding15");
		driver.findElement(By.xpath("//input[@name='password']")).submit();
		wait.waitFor(1000);
		System.out.println("登录后"+driver.manage().getCookies());
		driver.manage().deleteAllCookies();
		Cookie c1 = new Cookie("BAIDUID","FC5FBF5CA9B6531BD7B5083A94B4EAFC:FG=1");
		Cookie c2 = new Cookie("BDUSS","1PUVJ4cFdvRkpvZFRlSnlHSWQ1V1Y2aVI0V1phTG4wcUVYNUQ1UE1-TjA5V1ZUQVFBQUFBJCQAAAAAAAAAAAEAAADB4DwCZXJkaW5naGFvAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHRoPlN0aD5TdE=1");
		Cookie c3 =  new Cookie("H_PS_PSSID","5221_5229_1437_5225_5723_5823_5848_5831_4760_5856_5898");
		Cookie c4 =  new Cookie("H_PS_TIPFLAG","0");
		driver.manage().addCookie(c1);
		driver.manage().addCookie(c2);
		driver.manage().addCookie(c3);
		driver.manage().addCookie(c4);
		System.out.println("添加cookie"+driver.manage().getCookies());
		driver.get("http://www.baidu.com");
		driver.navigate().refresh();
		wait.waitFor(3000);
		System.out.println("browser will be close");
	}
	
	@AfterClass
	public void release(){
	driver.close();
	driver.quit();
}
}
