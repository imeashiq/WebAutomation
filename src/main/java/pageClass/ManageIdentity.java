package pageClass;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import baseWebElements.BaseWebElement;
import io.qameta.allure.Step;

public class ManageIdentity extends BaseWebElement {
	WebDriver driver;

	public ManageIdentity(WebDriver driver) {
		this.driver = driver;
	}

	/*
	 * Search for an Identity
	 */
	@Step("Search for an Identity")
	public void searchIdentity(String identityName) {
		getElement(driver, "searchBox", 15).sendKeys(identityName);
		getElement(driver, "searchBtn", 15).click();
	}

	/*
	 * Manage an Identity
	 */
	@Step("Manage an Identity")
	public void manageIdentity(String identityName) {
		getElement(driver, "manageIdentity", 15).click();
		Assert.assertTrue(getElement(driver, "identityDetailsTitle", 10).isDisplayed(),
				"Identity Details is not displayed after clicking Manage Button.");
	}

	/*
	 * Navigate to Identity Access
	 */
	@Step("Navigate to Identity Access")
	public void identityAccess() {
		getElement(driver, "identityAccess", 15).click();
		Assert.assertTrue(getElement(driver, "accessTitle", 10).isDisplayed(),
				"Identity Access Details is not displayed.");
	}

	/*
	 * Verify the Entitlements
	 */
	@Step("Verify the Entitlements")
	public void verifyEntitlements(String application, String entitlement) {
		Assert.assertTrue(getElement(driver, replaceMultipleDynamicLocator("appAndEntitle", application, entitlement), 10)
				.isDisplayed(), "Entitlement is not present for the identity.");
	}
}
