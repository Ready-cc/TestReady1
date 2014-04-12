package Test.xigua.collect;

import static myTools.PrintMain.print;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import myTools.ApiObject;
import myTools.Do;
import myTools.MyException;
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
import org.openqa.selenium.support.ui.Select;
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
//import myTools.APIObject;

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


	/*
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
	*/
	@BeforeClass
	public void startBrowser() {
		BrowserInit browser = new BrowserInit(BrowserType.chrome);
		driver = browser.driver;
		du = new Do(driver);
		wait = new Wait(driver);
		swtichw = new Switch(driver);
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
	public void collect() throws MyException{
		WebElement product1= du.find("//div[@class='mask-layer'   and child::a[contains(@href,'100-2091')]]");
		(new Actions(driver)).moveToElement(product1).build().perform();
		wait.waitFor(2000);
//		product1.click(); 
	}
	@Test
	public void search() throws MyException{
		du.find("//div[@class='search-box']/descendant::input[1]").sendKeys("ccf123");
		du.find("//button[text()='搜索按钮']").click();
	}
	@Test
	public void selectprodcut() throws MyException{
		du.find("//a[text()='100-1472']").click();
		swtichw.toSpecificWindow("100-1472");
		du.find("//button[text()='立即购买']").click();
		du.find("//button[text()='提交订单']").click();
		
	}
	@Test
	public void mySpace() throws MyException{
		this.logOn();
		Actions moveToE = new Actions(driver);
		WebElement user = driver.findElement(By.className("userAvatar"));
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
	@Test
	public void set() throws MyException{
		this.logOn();
		Actions moveToE = new Actions(driver);
		
		WebElement user = driver.findElement(By.className("user"));
		moveToE.moveToElement(user).build().perform();
		driver.findElement(By.xpath("//a[contains(text(),'设置')]")).click();
		WebElement mselect= du.find("//div[@class='form-group'][4]/descendant::a");
/*		mselect.click();
		List<WebElement> xbs = du.finds("//div[@class='bd' and @data-role='body']/descendant::span/a");
		Iterator<WebElement> xb = xbs.iterator();
		wait.waitFor(2000);
		for(WebElement xb1:xbs){
			print(xb1.getText());
		}
		mselect.click();*/
//		swtichw.backToCurrentWindow();
		WebElement proviceselect= du.find("//div[@class='form-group'][5]/descendant::a[1]");
		proviceselect.click();
		List<WebElement> provices = du.finds("//div[@class='bd' and @data-role='body']/descendant::span/a");
		for(WebElement provice:provices){
			print(provice.getText());
		}
		proviceselect.click();
		
		WebElement cityelect= du.find("//div[@class='form-group'][5]/descendant::a[2]");
		cityelect.click();
		List<WebElement> city = du.finds("//div[@class='bd' and @data-role='body']/descendant::span/a");
		for(WebElement city1:city){
			print(city1.getText());
		}
		cityelect.click();
	
		
		WebElement areaselect= du.find("//div[@class='form-group'][5]/descendant::a[3]");
		areaselect.click();
		List<WebElement> area = du.finds("//div[@class='bd' and @data-role='body']/descendant::span/a");
		for(WebElement area1:area){
			print(area1.getText());
		}
		areaselect.click();
		
		
	}
	//div[@class='form-group'][4]/descendant::a
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
