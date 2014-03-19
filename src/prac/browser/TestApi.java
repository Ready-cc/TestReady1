package prac.browser;

import java.util.List;

import myTools.*;
import static myTools.PrintMain.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Description:practice with seleniumAPI
 * @author ready
 * @since 2014.3.17
 **/
public class TestApi implements Xpath {
	
	private WebDriver driver;
	private Do du;
	private Wait wait;
	private ParseProperties sends = new ParseProperties(System.getProperty("user.dir")+"\\tool\\sendkeys.properties");
	private ParseProperties xp = new ParseProperties(System.getProperty("user.dir")+"\\tool\\xpath.properties");
	
	@BeforeClass
	public void startBrowser(){
		BrowserInit browser = new BrowserInit(BrowserType.chrome);
		driver = browser.driver;
		du = new Do(driver);
		wait = new Wait(driver);
//		driver.manage().window().maximize();
	}	
	
	@Test
	public void iFrameSlider(){	
		driver.get("http://jqueryui.com/slider/");
//		wait.waitForElementPresent(JQUERYHOME);
		driver.switchTo().frame(driver.findElement(By.xpath(SLIDERIFRAME)));
		
		Point initialPoint= driver.findElement(By.xpath(SLIDER)).getLocation();
		System.out.println(initialPoint);
       
        Actions dragger = new Actions(driver);
        dragger.dragAndDropBy(driver.findElement(By.xpath(SILDER)), initialPoint.getX()+80, initialPoint.getY()).build().perform();
//        wait.waitFor(5000);
        
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath(DRAGGABLE)).click();
		wait.waitFor(3000);

	}
	
	@Test
	public void iFrameDraggable(){	
		driver.get("http://jqueryui.com/draggable/");
		Switch swtichw=new Switch(driver);
		swtichw.frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));
//		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));
		Point initPoint = driver.findElement(By.xpath("//div[@id='draggable']")).getLocation();
		print(initPoint);
		Actions dragger = new Actions(driver);
		dragger.dragAndDropBy(driver.findElement(By.xpath("//div[@id='draggable']")), initPoint.getX()+80, initPoint.getY()+80).build().perform();
		
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath(DRAGGABLE)).click();
				
	}
	//隐藏select的如何解决？
	@Test
	public void selectTest(){
		driver.get("http://www.xigua365.com/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//div[@class='ft']/descendant::a[contains(text(),'登录')]")).click();
		driver.findElement(By.name("account_name")).sendKeys("ccf@ym.com");
		driver.findElement(By.name("passwd")).sendKeys("111111");
		driver.findElement(By.name("passwd")).submit();
		Actions moveToE = new Actions(driver);
		
		WebElement user = driver.findElement(By.className("user"));
		moveToE.moveToElement(user).build().perform();
		driver.findElement(By.xpath("//a[contains(text(),'设置')]")).click();

		Select province = new Select(driver.findElement(By.xpath("//select[@depth='0']")));
//		province.selectByValue("130000");
		Select city = new Select(driver.findElement(By.xpath("//select[@depth='1']")));
		List<WebElement> allcitys = city.getOptions();
		for(WebElement eachcity:allcitys)
			print(eachcity.getText());
		
	}
	
	@Test
	public void selectWindow(){
		driver.get("http://www.xigua365.com");
		driver.manage().window().maximize();
		driver.findElement(By.name("q")).sendKeys("测试");
		driver.findElement(By.name("q")).submit();
		driver.findElement(By.xpath("//a[@title='测试6']")).click();
		Switch swtichw=new Switch(driver);
		swtichw.toSpecificWindow("测试6");
		driver.findElement(By.xpath("//li[contains(text(),'商品详情')]")).click();
		List<WebElement> lis = driver.findElements(By.xpath("//div[@class='shop-tab']/ul[@class='tab-cont']/li/P"));
		for(WebElement eachlis:lis){
			print(eachlis.getText());
		}
	}
	@Test
	public void mouseRightClick(){
		driver.get("http://mail.126.com");
		du.what("lgname").clear();
		du.what("lgname").sendKeys(sends.getValue("lgname"));
		du.what("lgpwd").sendKeys(sends.getValue("lgpwd"));
		du.what("lgpwd").submit();
		Actions rclick = new Actions(driver);
		rclick.contextClick(driver.findElement(By.xpath("//div/span[text()='收件箱']"))).perform();
		wait.waitFor(2000);
		driver.findElement(By.xpath("//div[contains(@id,'mail_menu')][last()]/descendant::span[text()='查看未读']")).click();
		wait.waitFor(2000);
		Assert.assertEquals(driver.findElement(By.xpath("//header[@class='frame-main-cont-head']/descendant::span[text()='举 报']")).isDisplayed(), true);
		
	}
	/**HomeWork：易迅滚动到1F
	 * author：ready
	 * date：2014.3.18
	 */
	@Test
	public void dragonefl(){
		driver.get("http://yixun.com");
		Point f1 = driver.findElement(By.xpath("//h2[contains(text(),'1F')]")).getLocation();
		((JavascriptExecutor) driver).executeScript("window.scrollBy("+f1.getX()+","+f1.getY()+")");
		wait.waitFor(2000);
	}
	
//	dragonef2谷歌浏览器下看不到效果
	@Test
	public void dragonef2(){
		driver.get("http://reg.163.com/agreement.shtml");
		 int numberOfPixelsToDragTheScrollbarDown = 1000;
	     Actions dragger = new Actions(driver);
		dragger.moveToElement(driver.findElement(By.xpath("//p[contains(text(),'网易通行证服务条款')]"))).clickAndHold().moveByOffset(0, numberOfPixelsToDragTheScrollbarDown).release().perform();
		wait.waitFor(5000);
	}
	
	@AfterClass
	public void releasBrowser(){
		
		driver.quit();
	}	

	

}
