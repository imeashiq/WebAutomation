package pageClass;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import baseWebElements.BaseWebElement;
import io.qameta.allure.Step;
import utilities.CSVReader;

public class ManageAccess extends BaseWebElement {
	WebDriver driver;
	CSVReader csvReader;

	public ManageAccess(WebDriver driver) {
		this.driver = driver;
		csvReader = new CSVReader();
	}

	/*
	 * Search and Select the User in the User access page.
	 */
	@Step("Search and Select the User in the User access page")
	public void searchAndSelectUser(String user) {
		// To read data from user file
		HashMap<String, String> userDetails = csvReader.readUserDetails(user);
		getElement(driver, "searchUserTxtBx", 10).sendKeys(userDetails.get("UserID"));
		getElement(driver, "userSearchBtn", 10).click();
		getElement(driver, replaceDynamicLocator("selectUser", userDetails.get("UserID")), 10).click();
		getElement(driver, "nextBtn", 10).click();
		Assert.assertTrue(getElement(driver, "searchHeader", 10).isDisplayed(),
				"User is not navigated to Search Access Page.");
	}

	/*
	 * Search and Select access for the user.
	 */
	@Step("Search and Select access for the user")
	public void searchAndSelectAccess(String appAndEntitle) {
		// To read data from user file
		HashMap<String, String> appDetails = csvReader.readEntitleAndApp(appAndEntitle);
		getElement(driver, "searchAccessTxtBx", 10).sendKeys(appDetails.get("Application"));
		getElement(driver, "accessSearchBtn", 10).click();
		getElement(driver, replaceDynamicLocator("selectAccess", appDetails.get("Entitlement")), 10).click();
		getElement(driver, "nextBtn", 10).click();
		Assert.assertTrue(getElement(driver, "submitBtn", 10).isDisplayed(),
				"User is not navigated to Review and Submit Page.");
	}

	/*
	 * Submit User Access request for the user.
	 */
	@Step("Submit User Access request for the user")
	public void reviewAndSubmit() {
		getElement(driver, "submitBtn", 10).click();
	}
}
