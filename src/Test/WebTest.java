package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebTest {
	private WebDriver driver;
	public void webmentTest(){
		WebElement select = driver.findElement(By.xpath("//select"));

	}
	public void spilt(){
		String ulr = "http://testpaycb.xigua365.com/cashier.jhtml?tradeIdentity=1&orderid=100-20140328-1679-002333&spm=undefined.undefined.0.0.kjGSct";
		String aa = ulr.substring(68,92);
		System.out.print(aa);
		
	}
	public static void main(String[] args){
		WebTest aa = new WebTest();
		aa.spilt();
	}
}
