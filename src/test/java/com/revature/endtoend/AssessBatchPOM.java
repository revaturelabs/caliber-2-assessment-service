package com.revature.endtoend;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AssessBatchPOM {
	public final String title = "Caliber | Performance Management";
	
	@FindBy(how = How.XPATH, using = "/html/body/app-root/div/app-assess-batch/app-toolbar/div[1]/div/div/div[1]/ul/li[3]/a")
	public WebElement dropdown;
	
	
	public AssessBatchPOM(WebDriver driver) {
	PageFactory.initElements(driver, this);
	}
	
	public void pressTrainerTab() {
		this.dropdown.click();
	}
}
