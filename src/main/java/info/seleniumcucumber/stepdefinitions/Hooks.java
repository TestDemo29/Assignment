package info.seleniumcucumber.stepdefinitions;

import java.io.IOException;
import java.util.HashMap;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.framework.Init.DriverInitalization;
import com.framework.actions.BaseAbstract;
import com.framework.actions.ScreenShotMethods;
import com.framework.commonUtils.PropertiesFile;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends BaseAbstract {

	DriverInitalization driverInitialize = new DriverInitalization();
	public static HashMap<String, String> configDetails = new HashMap<>();
	public static String ConfigPropPath = "\\Confguration.properties";
	ScreenShotMethods takeScreenShotObj = new ScreenShotMethods();

	static {

		configDetails.put("Browser", PropertiesFile.getPropertyValue(ConfigPropPath, "Browser"));

	}

	@Before
	public void beforeScenario(Scenario scenario) {

		
		driver = driverInitialize.getDriver(configDetails);

	}

	@After
	public void AfterScenario(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {

			takeScreenShotObj.takeScreenShot();
		}
		closeDriver();

	}
}
