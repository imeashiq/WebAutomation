package pageClass;

import org.openqa.selenium.WebDriver;

import baseWebElements.BaseWebElement;

public class ManageIdentity extends BaseWebElement {
	WebDriver driver;

	public ManageIdentity(WebDriver driver) {
		this.driver = driver;
	}

	// Locators used in this page
	public static String quickLinkMenu = "ID:quicklinkButton";

}
