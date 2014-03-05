package prac.browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BrowserMail {
	private WebDriver ie;
	private static DesiredCapabilities caps = null;
	private String projectpath = System.getProperty("user.dir");

	@BeforeClass
	public void start(){
    	System.setProperty("webdriver.ie.driver", projectpath+"/tool/IEDriverServer32.exe"); 
		caps = DesiredCapabilities.internetExplorer();
		caps.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, false);
		caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);   
		caps.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");		
        caps.setCapability("ignoreZoomSetting", true);
        ie = new InternetExplorerDriver(caps);
	}
	@Test
	public void logOn(){
		ie.get("http://mail.126.com");
		ie.findElement(By.xpath("//input[@id='idInput']")).sendKeys("erdinghao");
		ie.findElement(By.xpath("//input[@id='pwdInput']")).sendKeys("er15ding");
		ie.findElement(By.xpath("//button[@id='loginBtn']")).click();	
		System.out.println("登陆成功");
	}

	@Test
	public void writeLetter(){

		ie.findElement(By.xpath("//div[@id='dvNavTop']/descendant::span[contains(text(),'写 信')]")).click();
		ie.findElement(By.xpath("//div[@id='divComposeto']/descendant::input[2]")).sendKeys("erdinghao@126.com");
		ie.findElement(By.xpath("//div[@id='divComposeSubject']/descendant::input")).sendKeys("test-ready");
		ie.findElement(By.xpath("//header[@id='divComposeToolbar']/descendant::span[1]")).click();
		System.out.println("test写信1");	
	}

	@Test
	public void inBox(){
		ie.findElement(By.xpath("//div[@id='navtree']/descendant::span[@title='收件箱']")).click();
		ie.findElement(By.xpath("//div[contains(@id,'_ContentDiv')]/descendant::div[contains(text(),'test')][1]")).click();
		System.out.println("test收件箱2");
	}
	
	@Test
	public void outBox(){
		ie.findElement(By.xpath("//div[@id='navtree']/descendant::span[(text()='已发送')]")).click();
		System.out.println("test已发送4");
	}
	
	@AfterClass
	public void releaseie(){
		ie.quit();
	}
	

}

