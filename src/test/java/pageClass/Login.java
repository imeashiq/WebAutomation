package pageClass;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import baseWebElements.BaseWebElement;
import fileReader.CSVReader;
import fileReader.ConfigReader;

public class Login extends BaseWebElement {

	WebDriver driver;
	CSVReader csvReader;

	public Login(WebDriver driver) {
		this.driver = driver;
		csvReader= new CSVReader();
	}

	// Locators used in this page
	public static String login_username = "ID:loginForm:accountId";
	public static String login_password = "ID:loginForm:password";
	public static String login_loginBtn = "ID:loginForm:loginButton";
	public static String homepage_userName = "ID:usernameMenu";
	public static String userMenu = "ID:userMenu";
	public static String logout = "XPATH://*[@id='usernameMenu']/following::a[3]";

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

	/*
	 * Method to login in sailpoint application
	 */
	public void loginApplication(String userType) {
		HashMap<String, String> userDetails = csvReader.readUserDetails(userType);
		getElement(driver, login_username, 20).sendKeys(userDetails.get("UserID"));
		getElement(driver, login_password, 5).sendKeys(userDetails.get("Password"));
		getElement(driver, login_loginBtn, 5).click();
		Assert.assertTrue(getElement(driver, homepage_userName, 15).isDisplayed(),
				"User unable to login to Sailpoint Application");
	}

	/*
	 * Method to logout in sailpoint application
	 */
	public void logoutApplication() {
		getElement(driver, userMenu, 10).click();
		getElement(driver, logout, 10).click();
		Assert.assertTrue(getElement(driver, login_loginBtn, 15).isDisplayed(),
				"User is not logged out of application.");
	}
}
