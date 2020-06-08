package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import baseDriver.DriverCreation;
import pageClass.LoginPage;

public class LoginTest extends DriverCreation {
	
	@Test
	public void test1() {
		
		WebDriver driver = getDriver();
		LoginPage login = new LoginPage(driver);
		
		//Login to the Application as admin
		login.loginApplication();
	}
}
