package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import baseDriver.DriverCreation;
import pageClass.DashBoard;
import pageClass.Login;
import pageClass.ManageAccess;
import pageClass.MyTask;

public class RequestAccess extends DriverCreation {
	String adminUser = "admin";
	String managerUser = "catherine";
	String userAccess = "tammy";
	String appAndEntitle = "Type1";	
	@Test
	public void raisingAccessForOtherUser() {
		WebDriver driver = getDriver();
		Login login = new Login(driver);
		DashBoard dashboard = new DashBoard(driver);
		MyTask myTask = new MyTask(driver);
		ManageAccess manageAccess = new ManageAccess(driver);
		
		//Login as Manager to raise Access
		login.loginApplication(managerUser);
		//Navigate to manage user access
		dashboard.manageUserAccess();
		//Raise access request for the user
		manageAccess.searchAndSelectUser(userAccess);
		manageAccess.searchAndSelectAccess(appAndEntitle);
		manageAccess.reviewAndSubmit();
		//logout
		login.logoutApplication();
		
		//Login as admin to approve request
		login.loginApplication(adminUser);
		//Navigate to Approvals
		dashboard.approvals();
		myTask.approveAllRequests();
		
		
	}

}
