package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.framework.actions.BaseAbstract;
import com.framework.actions.BasicActions;
import com.framework.actions.waitUtils;
import com.framework.commonUtils.PropertiesFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class HomePage extends BaseAbstract {

	BasicActions commonActions = new BasicActions();
	waitUtils wait = new waitUtils();
	public static String randomName = null;
	public static String dataPropPath = "\\userData.properties";
	public static String ConfigPropPath = "\\Confguration.properties";

	static Logger log = Logger.getLogger(HomePage.class.getName());

	public HomePage() {

		// This initElements method will create all WebElements

		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "temperature")
	WebElement Temperature_ID;

	@FindBy(xpath = "//*[contains(text(),'Buy moisturizers')]")
	WebElement Category_Moisturizers_XPATH;

	@FindBy(xpath = "//*[contains(text(),'Buy sunscreens')]")
	WebElement Category_Sunscreens_XPATH;

	@FindBy(xpath = "//*[contains(text(),'Pay with Card')]")
	WebElement Pay_with_Card_XPATH;

	@FindBy(xpath = "//*[contains(text(),'PAYMENT SUCCESS')]")
	WebElement PAYMENT_SUCCESS_XPATH;

	@FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/button")
	WebElement temp_XPATH;

	@FindBy(id = "cart")
	WebElement Cart_ID;

	@FindBy(id = "email")
	WebElement Email_ID;

	@FindBy(id = "card_number")
	WebElement Card_number_ID;

	@FindBy(id = "cc-exp")
	WebElement Card_Expiry_ID;

	@FindBy(id = "cc-csc")
	WebElement CVC_ID;

	@FindBy(id = "billing-zip")
	WebElement billing_zip_ID;

	@FindBy(id = "submitButton")
	private WebElement submitButton_BTN;

	@FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/p[1]")
	private WebElement Xpath;

	@FindBy(css = "body > div.container > div:nth-child(1) > span")
	private WebElement infoIcon;

	@FindBy(className = "popover-body")
	private WebElement detailsInfo;



	/**
	 * This Method used to get the current Temperature to decide the shopping items list sunscreen or moisturizer 
	 * @return - returns current Temperature
	 */
	public int getCurrentTemperature() {

		String temp = commonActions.getText(Temperature_ID);
		String[] actTemp = temp.split(" ");
		int tempIntTemperature = Integer.parseInt(actTemp[0]);
		return tempIntTemperature;
	}

	/**
	 * This method is used to select the category  sunscreen or moisturizer depending on the temperature
	 * @param temperature - Provide current temperature as input 
	 * @throws IOException
	 */
	public void selectCategory(int temperature) throws IOException {

		log.info("Current temperature is : " + temperature);

		if (temperature < 19) {
			log.info("Selecting Moisturizers ...... as temperature is " + temperature);
			commonActions.clickElement(Category_Moisturizers_XPATH);
		} else {
			log.info("Selecting Sunscreens ......as temperature is " + temperature);
			commonActions.clickElement(Category_Sunscreens_XPATH);
		}
	}

	
	
	/**
	 * This method is used to select Items and add to cart 
	 * @param itemName
	 */
	public void selectItemsToAddCart(String itemName) {

		int shoppingRows = commonActions.getRowCount("/html/body/div[1]/div");
		String Least_exp_Item = getLeastExpensiveItem(itemName,shoppingRows);

		addLeastExpensiveItemToCart(Least_exp_Item);

		
	}

	/**
	 * This method adds least expensive Item to cart
	 * @param least_exp_Item - porvide the name of the item to add 
	 */
	private void addLeastExpensiveItemToCart(String least_exp_Item) {

		for (int j = 2; j <= 3; j++) {

			for (int i = 1; i <= 3; i++) {

				if (driver.findElement(By.xpath("/html/body/div[1]/div[" + j + "]/div[" + i + "]/p[1]")).getText()
						.contains(least_exp_Item)) {

					driver.findElement(By.xpath("/html/body/div[1]/div[" + j + "]/div[" + i + "]/button")).click();

				}

			}

		}
	}

	/**
	 * This method is used for final payment after checkout using credit card details 
	 * @throws InterruptedException
	 */
	public void payWithCard() throws InterruptedException {

		commonActions.clickElement(Pay_with_Card_XPATH);
		commonActions.SwithToFrame(0);
		commonActions.enterText(Email_ID, PropertiesFile.getPropertyValue(dataPropPath, "Email"));
		Thread.sleep(1000);
		commonActions.enterTextUsingJavaScript(Card_number_ID,
				PropertiesFile.getPropertyValue(dataPropPath, "Card_number"));
		commonActions.enterText(Card_Expiry_ID, PropertiesFile.getPropertyValue(dataPropPath, "Card_Expiry_Month"));
		commonActions.enterText(Card_Expiry_ID, PropertiesFile.getPropertyValue(dataPropPath, "Card_Expiry_Year"));
		commonActions.enterText(CVC_ID, PropertiesFile.getPropertyValue(dataPropPath, "CVC"));
		commonActions.enterText(billing_zip_ID, PropertiesFile.getPropertyValue(dataPropPath, "billing_zip"));
		commonActions.clickElement(submitButton_BTN);
		commonActions.SwithToParent();

	}

	/**
	 * This method is used to identify the condition to fetch items from the info - task bar ( This actually checks list of parameters from the properties
	 *  file and identify the condition to add items to cart
	 * @return - returns the items to add to cart depending on the task
	 */
	public ArrayList<String> getItemsForTask() {

		String[] items = PropertiesFile.getPropertyValue(ConfigPropPath, "ItemsToAddToCart").split(",");

		commonActions.clickElement(infoIcon);

		String temp = commonActions.getText(detailsInfo);

		ArrayList<String> cartItems = new ArrayList<>();

		for (String i : items) {

			if (temp.toLowerCase().contains(i.toLowerCase())) {

				cartItems.add(i);
			}

		}
		commonActions.clickElement(infoIcon);
		return cartItems;

	}

	/**
	 * This method is used to get the least expensive items from the list using the type of item
	 * @param item - provide the condition or items to add to cart
	 * @param rows - provide the number of rows to look for adding items to cart
	 * @return - returns the least expensive item depending on the task condition
	 */
	public String getLeastExpensiveItem(String item,int rows) {

		Map<String, Integer> listOfItems = new HashMap<String, Integer>();

		for (int j = 2; j <= rows; j++) {

			for (int i = 1; i <= rows; i++) {

				if (driver.findElement(By.xpath("/html/body/div[1]/div[" + j + "]/div[" + i + "]/p[1]")).getText()
						.contains(item)) {

					String itemName = driver
							.findElement(By.xpath("/html/body/div[1]/div[" + j + "]/div[" + i + "]/p[1]")).getText();
					String price = driver.findElement(By.xpath("/html/body/div[1]/div[" + j + "]/div[" + i + "]/p[2]"))
							.getText();

					listOfItems.put(itemName, Integer.parseInt(price.replaceAll("[^\\d]", "")));
				}

			}

		}
		System.out.println("+++++++++++++");

		System.out.println("Least Expensive Item Name " + item + " is : "
				+ Collections.min(listOfItems.entrySet(), Map.Entry.comparingByValue()).getKey());
		System.out.println("Least Expensive Item Price " + item + " is : " + Collections.min(listOfItems.values()));

		System.out.println("+++++++++++++");

		return Collections.min(listOfItems.entrySet(), Map.Entry.comparingByValue()).getKey();

	}

	/**
	 * This method is used to verify the payment 
	 * @return - return true if the payment is success or false in case of failure
	 * @throws InterruptedException
	 */
	public boolean verifySucessPayment() throws InterruptedException {

		return commonActions.isElementAvailable(PAYMENT_SUCCESS_XPATH);

	}

	/**
	 * This method used to click on checkout out item button after adding items to cart
	 * 
	 */
	public void checkoutItems() {
		
		commonActions.clickElement(Cart_ID);
	}

}
