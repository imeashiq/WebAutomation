package pageClass;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import baseWebElements.BaseWebElement;

public class ManageIdentity extends BaseWebElement {
	WebDriver driver;

	public ManageIdentity(WebDriver driver) {
		this.driver = driver;
	}

	// Locators used in this page
	public static String quickLinkMenu = "ID:quicklinkButton";
	public static String searchBox = "ID:searchInput";
	public static String searchBtn = "ID:searchBtn";
	public static String manageIdentity = "XPATH://button[contains(text(),'Manage')]";
	public static String identityDetailsTitle = "XPATH://h1[contains(text(),'Identity Details')]";
	public static String identityAccess = "ID:nav-identity-access";
	public static String accessTitle = "XPATH://h2[contains(text(),'Access')]";
	
	/*
	 * Search for an Identity
	 */
	public void searchIdentity(String identityName) {
		getElement(driver, searchBox, 15).sendKeys(identityName);
		getElement(driver, searchBtn, 15).click();
	}
	
	/*
	 * Manage an Identity
	 */
	public void manageIdentity(String identityName) {
		getElement(driver, manageIdentity, 15).click();
		Assert.assertTrue(getElement(driver, identityDetailsTitle, 10).isDisplayed(), "Identity Details is not displayed after clicking Manage Button.");
	}
	
	/*
	 * Navigate to Identity Access
	 */
	public void identityAccess() {
		getElement(driver, identityAccess, 15).click();
		Assert.assertTrue(getElement(driver, accessTitle, 10).isDisplayed(), "Identity Access Details is not displayed.");
	}
	
	/*
	 * Verify the Entitlements
	 */
	public void verifyEntitlements(String application, String entitlement) {
		
	}
}
