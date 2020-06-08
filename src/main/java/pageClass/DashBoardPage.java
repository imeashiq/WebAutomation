package pageClass;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import baseWebElements.BaseWebElement;
import io.qameta.allure.Step;

public class DashBoardPage extends BaseWebElement {
	WebDriver driver;

	public DashBoardPage(WebDriver driver) {
		this.driver = driver;
	}

	/*
	 * Navigate to Manage User Access Page
	 */
	@Step("Navigate to Manage Access page from Dashboard")
	public void manageUserAccess() {
		getElement(driver, "quickLinkMenu", 10).click();
		getElement(driver, "manageAccess", 10).click();
		getElement(driver, "manageUserAccess", 10).click();
		Assert.assertTrue(getElement(driver, "manageUserAccessTitle", 10).isDisplayed(), "User is not navigated to Manage User Access Page.");
	}
	
	/*
	 * Navigate to Approvals Page
	 */
	@Step("Navigate to Approvals page from Dashboard")
	public void approvals() {
		getElement(driver, "quickLinkMenu", 10).click();
		getElement(driver, "myTask", 10).click();
		getElement(driver, "approvals", 10).click();
		Assert.assertTrue(getElement(driver, "approvalsTitle", 10).isDisplayed(), "User is not navigated to Approvals Page.");
	}
	
	/*
	 * Navigate to View Identity Page
	 */
	@Step("Navigate to View Identity page from Dashboard")
	public void viewIdentity() {
		getElement(driver, "quickLinkMenu", 10).click();
		getElement(driver, "manageIdentity", 10).click();
		getElement(driver, "viewIdentity", 10).click();
		Assert.assertTrue(getElement(driver, "viewIdentityTitle", 10).isDisplayed(), "User is not navigated to View Identity Page.");
	}
}
