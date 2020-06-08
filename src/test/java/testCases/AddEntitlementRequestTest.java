package testCases;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import baseDriver.DriverCreation;
import pageClass.DashBoardPage;
import pageClass.LoginPage;
import pageClass.ManageAccessPage;
import pageClass.ManageIdentityPage;
import pageClass.MyTaskPage;
import utilities.JdbcUtil;

public class AddEntitlementRequestTest extends DriverCreation {
	String appAndEntitle = "Type1";

	@Test(description = "Verify user able to raise access for the reportee.")
	public void raisingAccessForOtherUser() {
		// Get testData drom the DB
		JdbcUtil testData = new JdbcUtil();
		HashMap<String, String> identityDetails = new HashMap<String, String>();

		// Required objects for the Test
		WebDriver driver = getDriver();
		LoginPage login = new LoginPage(driver);
		DashBoardPage dashboard = new DashBoardPage(driver);
		MyTaskPage myTask = new MyTaskPage(driver);
		ManageAccessPage manageAccess = new ManageAccessPage(driver);
		ManageIdentityPage manageIdentity = new ManageIdentityPage(driver);

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
