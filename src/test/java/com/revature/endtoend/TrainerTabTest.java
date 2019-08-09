package com.revature.endtoend;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class TrainerTabTest {
private String OS;
private static WebDriver driver;
private String URL = System.getenv("CALIBER_BASE_URL") + "/caliber/vp/assess";

	@BeforeClass
	public void setup() {
		OS = System.getProperty("os.name");
		if (OS.startsWith("Windows")) {
			System.setProperty("webdriver.chrome.driver", "src/test/drivers/chromedriver.exe");
		} if(OS.startsWith("Mac"))
			System.setProperty("webdriver.chrome.driver", "src/test/drivers/chromedriver");
		else {
			System.setProperty("webdriver.chrome.driver","src/test/drivers/chromedriverLinux");
		}
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		driver.get(URL);
	}
	@AfterClass
	public void teardown() {
		driver.quit();
	}
	
	@Test(priority = 1)
	public void testPageDirect() {
		AssessBatchPOM assessBatchPOM = new AssessBatchPOM(driver);
		assertEquals(driver.getTitle(), assessBatchPOM.title);
	}
	//Tests to make sure that Dan Pickles shows up when "Dan" is entered.
	@Test(priority = 2)
	public void TestTrainerTabByFirstName() {
		AssessBatchPOM assessBatchPOM = new AssessBatchPOM(driver);
		assessBatchPOM.pressTrainerTab();
		WebDriverWait wait = new WebDriverWait(driver, 7);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"myInput\"]")));
		WebElement searchbar = driver.findElement(By.xpath("//*[@id=\"myInput\"]"));
		searchbar.click();
		searchbar.sendKeys("Dan");
		List<WebElement> dropdownItems = driver.findElements(By.xpath("/html/body/app-root/div/app-assess-batch/app-toolbar/div[1]/div/div/div[1]/ul/li[3]/ul/li[2]/a"));
		boolean found = false;
		for(WebElement element : dropdownItems) {
			if(element.getText().equals("Dan Pickles - 7/10/18"))
			found = true;
		}
		searchbar.clear();
		assertTrue(found);
	}
	//Test to make sure Dan Pickles shows up when "pickles" is entered.
	@Test(priority = 3)
	public void TestTrainerTabByLastName() {
		AssessBatchPOM assessBatchPOM = new AssessBatchPOM(driver);
		assessBatchPOM.pressTrainerTab();
		WebDriverWait wait = new WebDriverWait(driver, 7);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"myInput\"]")));
		WebElement searchbar = driver.findElement(By.xpath("//*[@id=\"myInput\"]"));
		searchbar.click();
		searchbar.sendKeys("Pickles");
		List<WebElement> dropdownItems = driver.findElements(By.xpath("/html/body/app-root/div/app-assess-batch/app-toolbar/div[1]/div/div/div[1]/ul/li[3]/ul/li[2]/a"));
		boolean found = false;
		for(WebElement element : dropdownItems) {
			if(element.getText().equals("Dan Pickles - 7/10/18"))
			found = true;
		}
		searchbar.clear();
		assertTrue(found);
	}
	//Tests to make sure Dan Pickles shows up when "Dan Pickles" is entered.
	@Test(priority = 4)
	public void TestTrainerTabByFullName() {
		AssessBatchPOM assessBatchPOM = new AssessBatchPOM(driver);
		assessBatchPOM.pressTrainerTab();
		WebDriverWait wait = new WebDriverWait(driver, 7);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"myInput\"]")));
		WebElement searchbar = driver.findElement(By.xpath("//*[@id=\"myInput\"]"));
		searchbar.click();
		searchbar.sendKeys("Dan Pickles");
		List<WebElement> dropdownItems = driver.findElements(By.xpath("/html/body/app-root/div/app-assess-batch/app-toolbar/div[1]/div/div/div[1]/ul/li[3]/ul/li[2]/a"));
		boolean found = false;
		for(WebElement element : dropdownItems) {
			if(element.getText().equals("Dan Pickles - 7/10/18"))
			found = true;
		}
		searchbar.clear();
		assertTrue(found);
	}
	//Tests to make sure Dan Pickes does NOT show up when "Jonathan Wolfe" is entered.
	@Test(priority = 5)
	public void TestTrainerTabByWronglName() {
		AssessBatchPOM assessBatchPOM = new AssessBatchPOM(driver);
		assessBatchPOM.pressTrainerTab();
		WebDriverWait wait = new WebDriverWait(driver, 7);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"myInput\"]")));
		WebElement searchbar = driver.findElement(By.xpath("//*[@id=\"myInput\"]"));
		searchbar.click();
		searchbar.sendKeys("Jonathan Wolfe");
		List<WebElement> dropdownItems = driver.findElements(By.xpath("/html/body/app-root/div/app-assess-batch/app-toolbar/div[1]/div/div/div[1]/ul/li[3]/ul/li[2]/a"));
		boolean found = false;
		for(WebElement element : dropdownItems) {
			if(element.getText().equals("Dan Pickles - 7/10/18"))
			found = true;
		}
		searchbar.clear();
		assertFalse(found);
	}
	@Test(priority = 6)
	public void TestTrainerTabByLowerCaseFullName() {
		AssessBatchPOM assessBatchPOM = new AssessBatchPOM(driver);
		assessBatchPOM.pressTrainerTab();
		WebDriverWait wait = new WebDriverWait(driver, 7);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"myInput\"]")));
		WebElement searchbar = driver.findElement(By.xpath("//*[@id=\"myInput\"]"));
		searchbar.click();
		searchbar.sendKeys("dan pickles");
		List<WebElement> dropdownItems = driver.findElements(By.xpath("/html/body/app-root/div/app-assess-batch/app-toolbar/div[1]/div/div/div[1]/ul/li[3]/ul/li[2]/a"));
		boolean found = false;
		for(WebElement element : dropdownItems) {
			if(element.getText().equals("Dan Pickles - 7/10/18"))
			found = true;
		}
		searchbar.clear();
		assertTrue(found);
	}
	//This test checks to see if Dan Pickles shows up with his full name entered in mixed case
	@Test(priority = 7)
	public void TestTrainerTabByMixedCaseFullName() {
		AssessBatchPOM assessBatchPOM = new AssessBatchPOM(driver);
		assessBatchPOM.pressTrainerTab();
		WebDriverWait wait = new WebDriverWait(driver, 7);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"myInput\"]")));
		WebElement searchbar = driver.findElement(By.xpath("//*[@id=\"myInput\"]"));
		searchbar.click();
		searchbar.sendKeys("dAn pIcKlEs");
		List<WebElement> dropdownItems = driver.findElements(By.xpath("/html/body/app-root/div/app-assess-batch/app-toolbar/div[1]/div/div/div[1]/ul/li[3]/ul/li[2]/a"));
		boolean found = false;
		for(WebElement element : dropdownItems) {
			if(element.getText().equals("Dan Pickles - 7/10/18"))
			found = true;
		}
		searchbar.clear();
		assertTrue(found);
	}
	
	//REMOVED TEST due to not being a search parameter.
	/*
	@Test(priority = 8)
	public void testSelectTrainerTabByDate() {
		AssessBatchPOM assessBatchPOM = new AssessBatchPOM(driver);
		assessBatchPOM.pressTrainerTab();
		WebDriverWait wait = new WebDriverWait(driver, 7);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"myInput\"]")));
		WebElement searchbar = driver.findElement(By.xpath("//*[@id=\"myInput\"]"));
		searchbar.click();
		searchbar.sendKeys("7/10/18");
		boolean found = false;
		if(driver.findElement(By.linkText("Dan Pickles - 7/10/18")) != null)
			found = true;
		searchbar.clear();
		assertTrue(found);
	}
	*/
}
