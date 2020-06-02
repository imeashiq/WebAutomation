package pageClass;

import org.openqa.selenium.WebDriver;

import baseWebElements.BaseWebElement;

public class MyTask extends BaseWebElement {
	WebDriver driver;

	public MyTask(WebDriver driver) {
		this.driver = driver;
	}

	// Locators used in this page
	public static String approveAll = "ID:btnApproveAllApproval0";
	public static String rejectAll = "ID:btnRejectAllApproval0";
	public static String completeBtn = "XPATH://button[text()='Complete']";
	
	/*
	 * Approve all the requests
	 */
	public void approveAllRequests() {
		getElement(driver, approveAll, 10).click();
		getElement(driver, completeBtn, 10).click();
	}

}
