package com.revature.endtoend;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

/*
 * Navigation from Assess Batch tab to Quality Audit Batch. 
 */

public class NavigationAtoQ {
    
	protected WebDriver driver; 
	
	@Test
	public void assessBatch() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\doaak\\Desktop\\Project3\\caliber-2-meta\\quality-audit-service\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
		String url = "http://localhost:4200/caliber/vp/assess";
		String url1 = "";
		driver.get("http://localhost:4200/caliber/vp/assess");

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
				"C:\\Users\\doaak\\Desktop\\Project3\\caliber-2-meta\\quality-audit-service\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://localhost:4200/caliber/vp/assess");
		String url = "http://localhost:4200/caliber/vp/assess";
		String url1 = "";
		driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
		driver.findElement(By.id("quality-link")).click();
		assertEquals(driver.getCurrentUrl(), url);

		if (url1.equals(url)) {
			System.out.println("Failed");
		} else {
			System.out.println("Right");
		}
	}
    
}
