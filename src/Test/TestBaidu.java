package Test;


import java.util.*;
import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;
public class TestBaidu {
@Test
public void test() {
String projectpath = System.getProperty("user.dir");
System.setProperty("webdriver.ie.driver", projectpath+"/tool/IEDriverServer32.exe"); 
WebDriver driver = new InternetExplorerDriver();
driver.get("http://www.baidu.com");
List<WebElement> links1 = driver.findElements(By.xpath("//a"));
List<WebElement> links2 = driver.findElements(By.cssSelector("#nv a"));
//验证链接数量
//assertEquals(10, links.size());
//打印href属性
for (int i = 0; i < links1.size(); i++) {
System.out.println(links1.get(i).getAttribute("href"));
}
for (int i = 0; i < links2.size(); i++) {
System.out.println(links2.get(i).getAttribute("href"));
}
driver.quit();
}
}