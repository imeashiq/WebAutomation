package pageClass;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import baseWebElements.BaseWebElement;
import fileReader.ConfigReader;

public class Login extends BaseWebElement {

	WebDriver driver;

	public Login(WebDriver driver) {
		this.driver = driver;
	}

	// Locators used in this page
	public static String login_username = "ID:loginForm:accountId";
	public static String login_password = "ID:loginForm:password";
	public static String login_loginBtn = "ID:loginForm:loginButton";
	public static String homepage_userName = "ID:usernameMenu";

	// Functionalities to be defined as methods

	/*
	 * Method to login in sailpoint application
	 */
	public void loginApplication() {
		ConfigReader reader = new ConfigReader();
		String userName = reader.getUsername();
		String password = reader.getPassword();
		getElement(driver, login_username, 20).sendKeys(userName);
		getElement(driver, login_password, 5).sendKeys(password);
		getElement(driver, login_loginBtn, 5).click();
		Assert.assertTrue(getElement(driver, homepage_userName, 15).isDisplayed(),
				"User unable to login to Sailpoint Application");
	}
}
