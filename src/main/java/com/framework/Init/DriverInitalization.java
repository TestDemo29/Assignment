package com.framework.Init;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.map.HashedMap;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.ErrorHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.framework.commonUtils.PropertiesFile;

/**
 * Created by tom on 24/02/17.
 */
public class DriverInitalization {
	public static long DEFAULT_WAIT = 20;
	protected static WebDriver driver;

	private static String OS = System.getProperty("os.name");

	protected static String currentDir = System.getProperty("user.dir");

	public static WebDriver getLatestDriver() {

		return driver;
	}

	public static WebDriver getDriver(HashMap<String, String> DriverDetails) {
		if (driver != null) {
			return driver;
		}

		System.out.println("Running on OS : " + OS);
		if (OS.contains("Windows")) {

			System.out.println("Windows >>> " + DriverDetails.get("Browser"));

			switch (DriverDetails.get("Browser")) {

			case "Firefox":
				System.out.println("Launch Firefox");
				System.setProperty("webdriver.gecko.driver",
						currentDir + "\\src\\main\\resources\\Windows\\geckodriver.exe");
				FirefoxOptions options = new FirefoxOptions();
				DesiredCapabilities capabilities = null;
				capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
				driver = new FirefoxDriver(capabilities);

				break;

			case "Chrome":
				System.out.println("Launch Chrome");
				System.setProperty("webdriver.chrome.driver",
						currentDir + "\\src\\main\\resources\\Windows\\chromedriver.exe");
				driver = new ChromeDriver();
				break;
			default:
				System.out.println("Browser execution not supported !! ");
				break;
			}

		} else if (OS.contains("mac")) {

			switch (DriverDetails.get("Browser")) {

			case "Firefox":
				System.out.println("Launching Firefox");
				System.setProperty("webdriver.gecko.driver", currentDir + "/src/main/resources/Mac/geckodriver");
				FirefoxOptions options = new FirefoxOptions();
				DesiredCapabilities capabilities = null;
				capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
				driver = new FirefoxDriver(capabilities);
				break;

			case "Chrome":
				System.out.println("Launching Chrome");
				System.setProperty("webdriver.chrome.driver", currentDir + "/src/main/resources/Mac/chromedriver");
				driver = new ChromeDriver();
				break;
			default:
				System.out.println("Browser execution not supported !! ");
				break;
			}
		}

		else {

			switch (DriverDetails.get("Browser")) {

			case "Firefox":
				System.out.println("Launching Firefox");
				System.setProperty("webdriver.gecko.driver", currentDir + "/src/main/resources/Linux/geckodriver");
				FirefoxOptions options = new FirefoxOptions();
				DesiredCapabilities capabilities = null;
				capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
				driver = new FirefoxDriver(capabilities);
				break;

			case "Chrome":
				System.out.println("Launching Chrome");
				System.setProperty("webdriver.chrome.driver", currentDir + "/src/main/resources/Linux/chromedriver");
				driver = new ChromeDriver();
				break;
			default:
				System.out.println("Browser execution not supported !! ");
				break;
			}

		}
		driver.manage().timeouts().setScriptTimeout(DEFAULT_WAIT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;

	}

	public static void closeDriver() {
		if (driver != null) {
			try {
				driver.close();
				driver.quit(); // fails in current geckodriver! TODO: Fixme
			} catch (NoSuchMethodError nsme) { // in case quit fails
			} catch (NoSuchSessionException nsse) { // in case close fails
			} catch (SessionNotCreatedException snce) {
			} // in case close fails
			driver = null;
		}
	}
}
