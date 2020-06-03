package pageClass;

import org.openqa.selenium.WebDriver;

import baseWebElements.BaseWebElement;
import io.qameta.allure.Step;

public class MyTask extends BaseWebElement {
	WebDriver driver;

	public MyTask(WebDriver driver) {
		this.driver = driver;
	}

	/*
	 * Approve all the requests
	 */
	@Step("Approve all the requests")
	public void approveAllRequests() {
		getElement(driver, "approveAll", 10).click();
		getElement(driver, "completeBtn", 10).click();
		if (isElementPresent(driver, "cancelBtn", 8)) {
			getElement(driver, "cancelBtn", 10).click();
		}
	}

}
