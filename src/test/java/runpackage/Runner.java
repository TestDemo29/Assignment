package runpackage;

import org.junit.runner.RunWith;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@ExtendedCucumberOptions(jsonReport = "target/cucumber.json", retryCount = 0, detailedReport = true, detailedAggregatedReport = true, overviewReport = true,
//coverageReport = true,
		jsonUsageReport = "target/cucumber-usage.json", usageReport = true, toPDF = true, excludeCoverageTags = {
				"@flaky" }, includeCoverageTags = { "@passed" }, outputFolder = "target/results")

@CucumberOptions(plugin = { "html:target/cucumberHtmlReport", "json:target/cucumber.json",
		"pretty:target/cucumber-pretty.txt", "usage:target/cucumber-usage.json" },
		// pretty:target/cucumber-json-report.json
		features = "classpath:features", glue = { "info.seleniumcucumber.stepdefinitions" }, tags = { "@shop" })

public class Runner {
	
	
}
