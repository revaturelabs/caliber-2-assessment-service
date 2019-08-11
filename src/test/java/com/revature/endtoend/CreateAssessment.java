package com.revature.endtoend;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class CreateAssessment {

	public WebDriver driver;

	@Test
	public void CREATEASSESSMENT() throws InterruptedException {
		// TODO Auto-generated method stub
		// System.setProperty("webdriver.chrome.driver",
		// "src/test/drivers/chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
	        String OS = System.getProperty("os.name");
			if (OS.startsWith("Windows")) {
				System.setProperty("webdriver.chrome.driver", "src/test/drivers/chromedriver.exe");
			} if(OS.startsWith("Mac"))
				System.setProperty("webdriver.chrome.driver", "src/test/drivers/chromedriver");
			else {
				System.setProperty("webdriver.chrome.driver","src/test/drivers/chromedriverLinux");
			}
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("headless");
		WebDriver	 driver = new ChromeDriver( options );

		
		String baseUrl = "http://localhost:4200/caliber/vp/assess";

		String actualTitle = "";
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		driver.get(baseUrl);

		actualTitle = driver.getTitle();
		System.out.println(actualTitle);
		TimeUnit.SECONDS.sleep(1);

		try {

			// TimeUnit.SECONDS.sleep(1);
			driver.findElement(By.linkText("Create Assessment")).click();

			driver.findElement(By.id("rawScore")).sendKeys("10");

			driver.findElement(By.id("category")).click();

			WebElement dropdown = driver.findElement(By.id("category"));
			dropdown.findElement(By.xpath("//option[. = 'SQL']")).click();
			TimeUnit.SECONDS.sleep(1);
			WebElement dropdown2 = driver.findElement(By.id("selectedType"));
			dropdown2.findElement(By.xpath("//option[. = 'Project']")).click();
			
			// driver.findElement(By.cssSelector(".ng-untouched .btn-default")).click();
			driver.findElement(By.xpath("//input[@value=\'Save\']")).click();
			
			
			//TimeUnit.SECONDS.sleep(10);
		} catch (Exception e) {
			System.out.println("\n\n\nThere is an error. Reason: " + e.toString()+"\n\n\n");
		}
		
		assertTrue(driver.findElement(By.xpath("//th[contains(.,\'SQL Project \')]")).isDisplayed(), "\n\n\nTHe assessment has not been created\n\n\n");
		System.out.println("killed myself");

	}

}
