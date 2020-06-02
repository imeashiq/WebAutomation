package pageClass;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import baseWebElements.BaseWebElement;

public class DashBoard extends BaseWebElement {
	WebDriver driver;

	public DashBoard(WebDriver driver) {
		this.driver = driver;
	}

	// Locators used in this page
	public static String quickLinkMenu = "ID:quicklinkButton";
	public static String myTask = "ID:quickLinkCategoryTasks";
	public static String manageAccess = "ID:quickLinkCategoryAccess";
	public static String manageIdentity = "ID:quickLinkCategoryManage";
	public static String approvals = "XPATH://*[@id='quickLinkCategoryTasks_list']/ul/li/a[contains(text(),'Approvals')]";
	public static String approvalsTitle = "XPATH://h1[contains(text(),'Approvals')]";
	public static String manageUserAccess = "XPATH://*[@id='quickLinkCategoryAccess_list']/ul/li/a[contains(text(),'Manage User Access')]";
	public static String manageUserAccessTitle = "XPATH://h1[contains(text(),'Manage User Access')]";
	
	/*
	 * Navigate to Manage User Access Page
	 */
	public void manageUserAccess() {
		getElement(driver, quickLinkMenu, 10).click();
		getElement(driver, manageAccess, 10).click();
		getElement(driver, manageUserAccess, 10).click();
		Assert.assertTrue(getElement(driver, manageUserAccessTitle, 10).isDisplayed(), "User is not navigated to Manage User Access Page.");
	}
	
	/*
	 * Navigate to Approvals Page
	 */
	public void approvals() {
		getElement(driver, quickLinkMenu, 10).click();
		getElement(driver, myTask, 10).click();
		getElement(driver, approvals, 10).click();
		Assert.assertTrue(getElement(driver, approvalsTitle, 10).isDisplayed(), "User is not navigated to Approvals Page.");
	}
}
