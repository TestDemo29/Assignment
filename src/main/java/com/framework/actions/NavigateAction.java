package com.framework.actions;

public class NavigateAction extends BaseAbstract {

	/**
	 * Method to launch url
	 * 
	 * @param url
	 */
	public void launchUrl(String url) {

		driver.get(url);

	}

	/**
	 * Method to refresh the page
	 */
	public void RefreshPage() {

		driver.navigate().refresh();
	}

	/**
	 * Method to navigate back & forward
	 * 
	 * @param direction : String : Navigate to forward or backward
	 */
	public void navigate(String direction) {
		if (direction.equals("back"))
			driver.navigate().back();
		else
			driver.navigate().forward();
	}

	/** Method to maximize browser */
	public void maximizeBrowser() {
		driver.manage().window().maximize();
	}

}
