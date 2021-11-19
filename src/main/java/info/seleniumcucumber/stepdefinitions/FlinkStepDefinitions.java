package info.seleniumcucumber.stepdefinitions;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import com.framework.actions.commonInteface;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FlinkStepDefinitions implements commonInteface {

	public static int temperature;

	@Given("^User launch \"([^\"]*)\"$")
	public void user_launch(String url) throws Throwable {

		navigateObj.launchUrl(url);

	}

	@Given("^User verifies the temperature for shopping$")
	public void user_verifies_the_temperature_for_shopping() throws Throwable {

		temperature = homePageObj.getCurrentTemperature();

	}

	@When("^User Shop for the Moisturizers \\(if the temperature is bellow (\\d+) degree\\) or Sunscreens \\(if the temperature is above (\\d+) degree$")
	public void user_Shop_for_the_Moisturizers_if_the_temperature_is_bellow_degree_or_Sunscreens_if_the_temperature_is_above_degree(
			int arg1, int arg2) throws Throwable {

		homePageObj.selectCategory(temperature);

	}

	@When("^User adds items to carts$")
	public void user_adds_items_to_carts() throws Throwable {

		ArrayList<String> shoppingItemsForTask = homePageObj.getItemsForTask();
		for (String items : shoppingItemsForTask) {

			homePageObj.selectItemsToAddCart(items);
		}
		homePageObj.checkoutItems();

	}

	@Then("^User completes the payment$")
	public void user_completes_the_payment() throws Throwable {

		homePageObj.payWithCard();

	}

	@Then("^verifies user placed order sucessfully$")
	public void verifies_user_placed_order_sucessfully() throws Throwable {

		assertTrue(
				"Payment was not sucessfull ..... Please check screen shot for more details under Screenshots folder !!!!!",
				homePageObj.verifySucessPayment());

	}

}
