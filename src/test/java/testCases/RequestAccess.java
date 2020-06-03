package testCases;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import baseDriver.DriverCreation;
import pageClass.DashBoard;
import pageClass.Login;
import pageClass.ManageAccess;
import pageClass.ManageIdentity;
import pageClass.MyTask;
import utilities.JdbcUtil;

public class RequestAccess extends DriverCreation {
	String adminUser = "admin";
	String managerUser = "catherine";
	String userAccess = "tammy";
	String appAndEntitle = "Type1";

	@Test(description = "Verify user able to raise access for the reportee.")
	public void raisingAccessForOtherUser() {
		// Get testData drom the DB
		JdbcUtil testData = new JdbcUtil();
		HashMap<String, String> identityDetails = testData.testData();

		// Required objects for the Test
		WebDriver driver = getDriver();
		Login login = new Login(driver);
		DashBoard dashboard = new DashBoard(driver);
		MyTask myTask = new MyTask(driver);
		ManageAccess manageAccess = new ManageAccess(driver);
		ManageIdentity manageIdentity = new ManageIdentity(driver);

		// Login as Manager to raise Access
		login.loginApplication(identityDetails.get("manager"));
		// Navigate to manage user access
		dashboard.manageUserAccess();
		// Raise access request for the user
		manageAccess.searchAndSelectUser(identityDetails.get("userid"));
		manageAccess.searchAndSelectAccess(appAndEntitle);
		manageAccess.reviewAndSubmit();
		// logout
		login.logoutApplication();

		// Login as admin to approve request
		login.loginApplication(identityDetails.get("sysadmin"));
		// Navigate to Approvals
		dashboard.approvals();
		myTask.approveAllRequests();
		// logout
		login.logoutApplication();
		// Login as admin to approve request
		login.loginApplication(identityDetails.get("manager"));
		// Verify the Entitlement
		dashboard.viewIdentity();
		manageIdentity.searchIdentity(identityDetails.get("userid"));
		manageIdentity.manageIdentity();
		manageIdentity.identityAccess();
		manageIdentity.verifyEntitlements(appAndEntitle);
	}

}
