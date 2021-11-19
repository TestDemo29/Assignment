package info.seleniumcucumber.stepdefinitions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.framework.actions.commonInteface;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import cucumber.runtime.ScenarioImpl;

public class PredefinedStepDefinitions implements commonInteface {

	
	//Step to navigate forward
	@Then("^I navigate forward")
	public void navigate_forward()
	{
		navigateObj.navigate("forward");
	}
	
	//Step to navigate backward
	@Then("^I navigate back")
	public void navigate_back()
	{
		navigateObj.navigate("back");
	}
	
	// steps to refresh page
	@Then("^I refresh page$")
	public void refresh_page()
	{
		navigateObj.RefreshPage();
	}
	
	// Switch between windows
	
	
}