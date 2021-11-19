package com.framework.actions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.framework.Init.DriverInitalization;
import com.itextpdf.text.log.SysoCounter;

public class BasicActions extends DriverInitalization {

	waitUtils wait = new waitUtils();

	/**
	 * This Method is to enter text
	 * 
	 * @param ele  - Webelement to insert text
	 * @param text - text to enter
	 */
	public void enterText(WebElement ele, String text) {
		wait.waitForVisibility(ele);
		ele.sendKeys(text);
	}

	/**
	 * Method to click on web element
	 * 
	 * @param ele - provide Element to click
	 */
	public void clickElement(WebElement ele) {

		wait.waitForVisibility(ele);
		ele.click();
	}

	/**
	 * Method to click on web element without wait
	 * 
	 * @param ele - provide element to click
	 */
	public void clickElementWithoutWait(WebElement ele) {

		// wait.waitForVisibility(ele);
		ele.click();
	}

	/**
	 * Method to prepare webelement
	 * 
	 * @param locaterType
	 * @param replaceValue
	 * @return - retrun webelement
	 */
	public By prepareWebElement(String locaterType, String replaceValue) {

		By locator = null;

		switch (locaterType.toLowerCase()) {

		case "xapth":
			locator = By.xpath(replaceValue);
			break;
		case "id":
			locator = By.id(replaceValue);
			break;

		}
		return locator;

	}

	/**
	 * Method to get Text
	 * 
	 * @param ele - web element to fetch text
	 * @return - text from web element
	 */
	public String getText(WebElement ele) {
		return ele.getText();
	}

	/**
	 * This method is used to check if element is available or not
	 * 
	 * @param ele - Web Element to fetch status
	 * @return - return true if found else false
	 */
	public boolean isElementAvailable(WebElement ele) {

		boolean flag = false;
		try {
			wait.waitForVisibility(ele);
			flag = ele.isDisplayed();
		} catch (Exception e) {
			return flag;
		}

		return flag;
	}

	/**
	 * This method used to enter Text using the javascript executor
	 * 
	 * @param ele       - Web Element to enter text
	 * @param inputText - input text to enter
	 */
	public void enterTextUsingJavaScript(WebElement ele, String inputText) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].value='" + inputText + "'", ele);

	}

	/**
	 * This method is used to switch to frame depending on index
	 * 
	 * @param index - provide index to switch frame for
	 */
	public void SwithToFrame(int index) {

		driver.switchTo().frame(index);

	}

	/**
	 * This method is used to switch to Parent frame
	 */
	public void SwithToParent() {

		driver.switchTo().parentFrame();

	}

	/**
	 * This method used to get the count for a row depending on the xpath
	 * 
	 * @param xpath - xpath to fetch the row count
	 * @return - returns row count
	 */
	public int getRowCount(String xpath) {
		List<WebElement> rows = driver.findElements(By.xpath(xpath));
		return rows.size();

	}

}
