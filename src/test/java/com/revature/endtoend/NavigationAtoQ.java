package com.revature.endtoend;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/*
 * Navigation from Assess Batch tab to Quality Audit Batch. 
 */

public class NavigationAtoQ {
    
	protected WebDriver driver; 
	
	//public final String URL = System.getenv("CALIBER_BASE_URL") + "/caliber/vp/audit";

	//private String URL = System.getenv("CALIBER_BASE_URL") + "/caliber/vp/assess";

//	@BeforeClass
//	public void setup() {
//		String OS;
//		OS = System.getProperty("os.name");
//		if (OS.startsWith("Windows")) {
//			System.setProperty("webdriver.chrome.driver", "src/test/drivers/chromedriver.exe");
//		}else { if(OS.startsWith("Mac"))
//			System.setProperty("webdriver.chrome.driver", "src/test/drivers/chromedriver");
//		else {
//			System.setProperty("webdriver.chrome.driver","src/test/drivers/chromedriverLinux");
//		}
//		}
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--headless");
//		driver = new ChromeDriver(options);
//		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
//		driver.get(URL);
//	}
	
	@Test 
	public void assessBatch() throws InterruptedException {
		//System.setProperty("webdriver.chrome.driver",
				//"C:\\Users\\doaak\\Desktop\\Project3\\caliber-2-meta\\quality-audit-service\\Drivers\\chromedriver.exe");
		//driver = new ChromeDriver();
	     System.setProperty("webdriver.chrome.driver",
                 "C:\\Users\\doaak\\Desktop\\Project3\\caliber-2-meta\\assessment-service\\src\\test\\drivers\\chromedriver.exe");
         ChromeOptions options = new ChromeOptions();
         options.addArguments("headless");
         options.addArguments("window-size=1200x600");
         WebDriver driver = new ChromeDriver(options);
         driver.get("http://localhost:4200/caliber/vp/assess");
         //driver.get("https://www.google.co.in/");
         System.out.println("title is: " + driver.getTitle());
		AssessBatchPOM assessBatchPOM = new AssessBatchPOM(driver);
		driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
		String url = "http://localhost:4200/caliber/vp/assess";
		String url1 = "";
		//driver.get("http://localhost:4200/caliber/vp/assess");

		url1 = driver.getCurrentUrl();

		if (url1.equals(url)) {
			System.out.println("Test Passed");
		} else {
			System.out.println("Test Failed");
		}

	}
	
	@Test
	public void assessBatchNav() throws InterruptedException {
	
		 System.setProperty("webdriver.chrome.driver",
                 "C:\\Users\\doaak\\Desktop\\Project3\\caliber-2-meta\\assessment-service\\src\\test\\drivers\\chromedriver.exe");
         ChromeOptions options = new ChromeOptions();
         options.addArguments("headless");
         options.addArguments("window-size=1200x600");
         WebDriver driver = new ChromeDriver(options);
         driver.get("http://localhost:4200/caliber/vp/assess");
         //driver.get("https://www.google.co.in/");
         System.out.println("title is: " + driver.getTitle());
		AssessBatchPOM assessBatchPOM = new AssessBatchPOM(driver);
		String url = "http://localhost:4200/caliber/vp/audit";
//		String url1 = "";
		driver.findElement(By.id("quality-link")).click();
		assertEquals(driver.getCurrentUrl(),url);

    
}
}

