package Test;
import static myTools.PrintMain.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestOne {

    @BeforeSuite
    public void beforeSuite(){
    	print("beforeSuite");
    }
	@BeforeTest
    public void beforeTest(){
    	print("beforeTest");
    }
	@BeforeClass
    public void beforeClass(){
    	print("beforeClass");
    }

    @BeforeMethod
    public void beforeMethod(){
    	print("beforeMethod");
    }
    
    @Test
    public void aFastTest(){
    	print("aFastTest1");
    }
    
    @Test
    public void bSlowTest1(){
    	print("bSlowTest2");
    }
    @Test
    public void bSlowTest2(){
    	print("bSlowTest3");
    }
        
    @AfterMethod
    public void afterMethod(){
    	print("afterMethod");
    }
    
    @AfterClass
    public void afterClass(){
    	print("afterClass");
    }
    @AfterTest
    public void afterTest(){
    	print("afterTest");
    }
    @AfterSuite
    public void afterSuite(){
    	print("AfterSuite");
    }
	
}

