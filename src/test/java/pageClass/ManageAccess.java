package pageClass;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import baseWebElements.BaseWebElement;
import fileReader.CSVReader;

public class ManageAccess extends BaseWebElement {
	WebDriver driver;
	CSVReader csvReader;
	
	public ManageAccess(WebDriver driver) {
		this.driver = driver;
		csvReader= new CSVReader();
	}

	// Locators used in this page
	public static String searchUserTxtBx = "ID:userSearchText";
	public static String userSearchBtn = "ID:userSearchBtn";
	public static String selectUser = "XPATH://*[contains(text(),'<<<>>>')]/preceding::*[@id='selectBtn-1']";
	public static String nextBtn = "accessRequestFooterNextBtn";
	public static String searchHeader = "XPATH:/h3[text()='Search For Access']";
	public static String searchAccessTxtBx = "ID:accessSearchText";
	public static String accessSearchBtn = "ID:accessSearchBtn";
	public static String selectAccess = "XPATH://*[contains(text(),'<<<>>>')]/preceding::button[1]";
	public static String submitBtn = "ID:submitBtn";
	public static String changeRequested = "XPATH://h1[text()='Manual Changes Requested']";
	
	
	/*
	 * Search and Select the User in the User access page.
	 */
	public void searchAndSelectUser(String user) {
		//To read data from user file
		HashMap<String, String> userDetails = csvReader.readUserDetails(user);
		getElement(driver, searchUserTxtBx, 10).sendKeys(userDetails.get("UserID"));
		getElement(driver, userSearchBtn, 10).click();
		getElement(driver, replaceDynamicLocator(selectUser, userDetails.get("UserID")), 10).click();
		getElement(driver, nextBtn, 10).click();
		Assert.assertTrue(getElement(driver, searchHeader, 10).isDisplayed(), "User is not navigated to Search Access Page.");
	}
	
	/*
	 * Search and Select access for the user.
	 */
	public void searchAndSelectAccess(String appAndEntitle) {
		//To read data from user file
		HashMap<String, String> appDetails = csvReader.readEntitleAndApp(appAndEntitle);
		getElement(driver, searchAccessTxtBx, 10).sendKeys(appDetails.get("Application"));
		getElement(driver, accessSearchBtn, 10).click();
		getElement(driver, replaceDynamicLocator(selectAccess, appDetails.get("Entitlement")), 10).click();
		getElement(driver, nextBtn, 10).click();
		Assert.assertTrue(getElement(driver, submitBtn, 10).isDisplayed(), "User is not navigated to Review and Submit Page.");
	}
	
	/*
	 * Submit User Access request for the user.
	 */
	public void reviewAndSubmit() {
		getElement(driver, submitBtn, 10).click();
	}
}
