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

	@Test
	public void assessBatch() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\doaak\\Desktop\\Project3\\caliber-2-meta\\assessment-service\\src\\test\\drivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("window-size=1200x600");
		WebDriver driver = new ChromeDriver(options);
		driver.get("http://localhost:4200/caliber/vp/assess");

		System.out.println("title is: " + driver.getTitle());
		AssessBatchPOM assessBatchPOM = new AssessBatchPOM(driver);
		driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
		String url = "http://localhost:4200/caliber/vp/assess";
		String url1 = "";

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
		// driver.get("https://www.google.co.in/");
		System.out.println("title is: " + driver.getTitle());
		AssessBatchPOM assessBatchPOM = new AssessBatchPOM(driver);
		String url = "http://localhost:4200/caliber/vp/assess";
		driver.findElement(By.id("quality-link")).click();
		assertEquals(driver.getCurrentUrl(), url);

	}
}
