package pageClass;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import baseWebElements.BaseWebElement;
import io.qameta.allure.Step;
import utilities.CSVReader;
import utilities.ConfigReader;

public class LoginPage extends BaseWebElement {

	WebDriver driver;
	CSVReader csvReader;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		csvReader = new CSVReader();
	}

	/*
	 * Method to login in sailpoint application
	 */
	@Step("Login into the Application")
	public void loginApplication() {
		ConfigReader reader = new ConfigReader();
		String userName = reader.getUsername();
		String password = reader.getPassword();
		getElement(driver, "login_username", 20).sendKeys(userName);
		getElement(driver, "login_password", 5).sendKeys(password);
		getElement(driver, "login_loginBtn", 5).click();
		Assert.assertTrue(getElement(driver, "homepage_userName", 15).isDisplayed(),
				"User unable to login to Sailpoint Application");
	}

	/*
	 * Method to login in sailpoint application
	 */
	@Step("Login into the application with given user")
	public void loginApplication(String userType) {
		//HashMap<String, String> userDetails = csvReader.readUserDetails(userType);
		getElement(driver, "login_username", 20).sendKeys(userType);
		getElement(driver, "login_password", 5).sendKeys("xyzzy");
		getElement(driver, "login_loginBtn", 5).click();
		Assert.assertTrue(getElement(driver, "homepage_userName", 15).isDisplayed(),
				"User unable to login to Sailpoint Application");
	}

	/*
	 * Method to logout in sailpoint application
	 */
	@Step("Logout of the application")
	public void logoutApplication() {
		getElement(driver, "userMenu", 10).click();
		getElement(driver, "logout", 10).click();
		Assert.assertTrue(getElement(driver, "login_loginBtn", 15).isDisplayed(),
				"User is not logged out of application.");
	}
}
