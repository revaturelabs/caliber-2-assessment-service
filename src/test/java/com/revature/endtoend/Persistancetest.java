package com.revature.endtoend;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

@Test
public class Persistancetest {
	public void persistancetest() throws InterruptedException 
	

	{
		// TODO Auto-generated method stub
		//System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
        String OS = System.getProperty("os.name");
		if (OS.startsWith("Windows")) {
			System.setProperty("webdriver.chrome.driver", "src/test/drivers/chromedriver.exe");
		}else if(OS.startsWith("Mac"))
			System.setProperty("webdriver.chrome.driver", "src/test/drivers/chromedriver");
		else {
			System.setProperty("webdriver.chrome.driver","src/test/drivers/chromedriverLinux");
		}
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		//options.addArguments("headless");
		WebDriver driver = new ChromeDriver(options);
		String baseUrl = "http://localhost:4200/caliber/vp/assess";
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		


		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		String actualTitle = "";
		// driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

		// launch Fire fox and direct it to the Base URL
		driver.get(baseUrl);

		// get the actual value of the title
		actualTitle = driver.getTitle();
		System.out.println(actualTitle);

        try {

        	TimeUnit.SECONDS.sleep(1);

        	try
        	{driver.findElement(By.linkText("Week 1")).isDisplayed();}
        	catch(Exception e)
        	{wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("li:nth-child(1) .glyphicon"))));
        		driver.findElement(By.cssSelector("li:nth-child(1) .glyphicon")).click();
        		TimeUnit.MILLISECONDS.sleep(600);
        		System.out.println("you got herre1");
        
        	    driver.findElement(By.cssSelector(".modal-content > .modal-footer > .btn-primary")).click();
        	    TimeUnit.SECONDS.sleep(1);
        	    TimeUnit.MILLISECONDS.sleep(600);
        	    System.out.println("you got herre2");
        	}
        	
        	try{driver.findElement(By.linkText("Week 2")).isDisplayed();}
        	catch(Exception e)
        	{System.out.println("you got herre");
        	//TimeUnit.MILLISECONDS.sleep(600);
        	    driver.findElement(By.cssSelector(".col-sm-12 li:nth-child(2) > a")).click();
        	    TimeUnit.SECONDS.sleep(1);
        	    TimeUnit.MILLISECONDS.sleep(600);
        	    driver.findElement(By.cssSelector(".modal-content > .modal-footer > .btn-primary")).click();

        	}
        	try{driver.findElement(By.linkText("Week 3")).isDisplayed();}
        	catch(Exception e){
        		TimeUnit.MILLISECONDS.sleep(1000);
        	    driver.findElement(By.cssSelector(".col-sm-12 li:nth-child(3) > a")).click();
        	    TimeUnit.SECONDS.sleep(1);
        	    TimeUnit.MILLISECONDS.sleep(1000);
        	    driver.findElement(By.cssSelector(".modal-content > .modal-footer > .btn-primary")).click();

        	}
        	
        	//last_element.click();
        	TimeUnit.SECONDS.sleep(1);
	        driver.findElement(By.linkText("Week 3")).click();
	        System.out.println("Got yhere");
	        TimeUnit.SECONDS.sleep(1);
	        driver.findElement(By.id("note-textarea-0")).click();
	        TimeUnit.SECONDS.sleep(1);
	        driver.findElement(By.id("note-textarea-0")).clear();
	        driver.findElement(By.id("note-textarea-0")).sendKeys("qwerty");
	        TimeUnit.SECONDS.sleep(1);
	        driver.findElement(By.linkText("Week 2")).click();
	        TimeUnit.SECONDS.sleep(1);
	        driver.findElement(By.id("note-textarea-0")).click();
	        //assertEquals(driver.findElement(By.id("note-textarea-0")).getAttribute("ng-reflect-model"),"","thetest does not worksfor individual persistance in that they pass on");
	        TimeUnit.SECONDS.sleep(1);
	        driver.findElement(By.linkText("Week 3")).click();
	        TimeUnit.SECONDS.sleep(1);
	        assertEquals(driver.findElement(By.id("note-textarea-0")).getAttribute("ng-reflect-model"),"qwerty","thetest does not worksforpersistance");

	        driver.close();
        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

	}
}
