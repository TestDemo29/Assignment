package com.framework.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.framework.Init.DriverInitalization;

public class waitUtils extends BaseAbstract {
//	WebDriver driver;
//	DriverInitalization test = new DriverInitalization();
//	WebDriverWait wait;
//
//	public waitUtils() {
//
//		this.driver = test.getLatestDriver();
//		wait = new WebDriverWait(this.driver, 5);
//	}

	public void waitForVisibility(WebElement element) throws Error {
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementClickable(WebElement element) throws Error {
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	

}
