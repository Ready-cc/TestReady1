package Test.xigua.collect;

import java.io.File;
import java.util.Set;

import myTools.ApiObject;
import myTools.Do;
import myTools.ParseProperties;
import myTools.ScreenHot;
import myTools.Switch;
import myTools.Wait;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Command;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.eti.kinoshita.testlinkjavaapi.model.TestCase;

import prac.browser.BrowserInit;
import prac.browser.BrowserType;
import practiceeight.APIObject;

public class HomePage {

	private WebDriver driver;
	private Do du;
	private Wait wait;
	private Switch swtichw;


	private ParseProperties sends = new ParseProperties(System.getProperty("user.dir")+"\\tool\\sendkeys.properties");
	private ParseProperties xp = new ParseProperties(System.getProperty("user.dir")+"\\tool\\xpath.properties");
	private ParseProperties testlink = new ParseProperties(System.getProperty("user.dir")+"\\tool\\xpath.properties");
	   //testlink parameters
    private String url;
    private String devKey;
    private String projectName;
    private String tl;
    private String buildName;
    private String platform;
	private String caseName;
	 private ApiObject testlinkapi;


	
	@BeforeClass
	@Parameters({"url","devkey","project","plan","build","platform"})
	public void startBrowser(@Optional("aaa") String url,String devkey,String project,String plan,String build,String platform){
		this.url = url;
    	this.devKey = devkey;
    	this.projectName = project;
    	this.tl = plan;
    	this.buildName = build;
    	this.platform = platform;
    	
		BrowserInit browser = new BrowserInit(BrowserType.chrome);
		testlinkapi = new ApiObject(url,devKey,projectName,tl,buildName,platform);
		driver = browser.driver;
		du = new Do(driver);
		wait = new Wait(driver);
		swtichw=new Switch(driver);
		driver.manage().window().maximize();
	}	
	@Test
	public void logOn(){
		caseName="tc1";
		driver.get("http://www.xigua365.com/");
		driver.findElement(By.xpath("//div[@class='ft']/descendant::a[contains(text(),'登录')]")).click();
		driver.findElement(By.name("account_name")).sendKeys("ccf@ym.com");
		driver.findElement(By.name("passwd")).sendKeys("111111");
		driver.findElement(By.name("passwd")).submit();
		WebElement user = driver.findElement(By.className("user"));
		Assert.assertTrue(user.isDisplayed());

	}
	
	@Test
	public void collect(){
		WebElement product1= du.find("//div[@class='mask-layer'   and child::a[contains(@href,'100-2091')]]");
		(new Actions(driver)).moveToElement(product1).build().perform();
		wait.waitFor(2000);
//		product1.click(); 
	}
	@Test
	public void mySpace(){
		
		Actions moveToE = new Actions(driver);
		WebElement user = driver.findElement(By.className("user"));
		moveToE.moveToElement(user).build().perform();
		du.find("//a[contains(text(),'我的空间')]").click();
		wait.waitFor(1000);
		du.find("//button[@class='btn-r']").click();
		wait.waitFor(5000);
		du.find("//div[object[@type='application/x-shockwave-flash']]").click();
		wait.waitFor(5000);
		Set<String> handles = driver.getWindowHandles();
		String titlename;
		for(String handle:handles){
			titlename = driver.switchTo().window(handle).getTitle();
				System.out.print(titlename);
				}
	}
	
	@AfterMethod
	public void updatereport(ITestResult result) throws Exception {  
		testlinkapi.getAPI();
    	testlinkapi.getTestCases();
    	TestCase tc = testlinkapi.getTestCase(caseName);
    	testlinkapi.executeTestCase(caseName,result.getStatus(),"aas");
		//调用screenShot方法，屏幕截图，该方法返回文件类型，然后取该文件路径。
		String filepath = ScreenHot.screenShot(driver, caseName).getAbsolutePath();
		System.out.print(filepath);
		testlinkapi.testUpload(tc,filepath,caseName, "desc","Autor:ccf","fileContent");

	}
	@AfterClass
	public void releasBrowser(){
		driver.quit();
	}	
}
