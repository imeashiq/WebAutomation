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
import utilities.CSVReader;

public class AddEntitlementRequestTest extends DriverCreation {
	String appAndEntitle = "PRISM-VPN";
	String userDetails = "Alan.Bradley";

	@Test(description = "Verify user able to raise access for the other user.", groups = { "sanity" })
	public void raisingAccessForOtherUser() {
		HashMap<String, String> identityDetails = new HashMap<String, String>();
		CSVReader reader = new CSVReader();
		identityDetails = reader.readIdentityDetails(userDetails);

		// Required objects for the Test
		WebDriver driver = getDriver();
		LoginPage login = new LoginPage(driver);
		DashBoardPage dashboard = new DashBoardPage(driver);
		MyTaskPage myTask = new MyTaskPage(driver);
		ManageAccessPage manageAccess = new ManageAccessPage(driver);
		ManageIdentityPage manageIdentity = new ManageIdentityPage(driver);

		// Login as user to raise Access
		login.loginApplication();
		// Navigate to manage user access
		dashboard.manageUserAccess();
		// Raise access request for the user
		manageAccess.searchAndSelectUser(identityDetails.get("requestee"));
		manageAccess.searchAndSelectAccess(appAndEntitle);
		manageAccess.reviewAndSubmit();
		// logout
		login.logoutApplication();

		// Approve as Manager
		login.loginApplication(identityDetails.get("manager"));
		// Navigate to Approvals
		dashboard.approvals();
		myTask.approveAllRequests();
		// logout
		login.logoutApplication();

		// Login as owner to approve request
		login.loginApplication(identityDetails.get("owner"));
		// Navigate to Approvals
		dashboard.approvals();
		myTask.approveAllRequests();
		// logout
		login.logoutApplication();

		// Login as admin
		login.loginApplication();
		// Search for Identity
		dashboard.viewIdentity();
		manageIdentity.searchIdentity(identityDetails.get("requestee"));
		manageIdentity.manageIdentity();
		manageIdentity.identityAccess();
		// Verify the Entitlement
		manageIdentity.verifyEntitlements(appAndEntitle);
	}

}
