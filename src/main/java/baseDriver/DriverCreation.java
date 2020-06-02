package baseDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import fileReader.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.OperatingSystem;

public class DriverCreation {

	private ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/*
	 * Method to create a chrome Driver
	 */
	@BeforeMethod
	public void createDriver() {
		// Object to read properties from config file
		ConfigReader reader = new ConfigReader();
		// Manage ChromeDriver
		WebDriverManager.edgedriver().operatingSystem(OperatingSystem.WIN).setup();
		// Driver Creation
		//WebDriver driver = new ChromeDriver();
		WebDriver driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Navigating to BaseURL
		driver.get(reader.getBaseURL());
		driver.manage().window().maximize();
		setDriver(driver);
	}

	/*
	 * Method is to set the driver to Thread after creation
	 */
	public void setDriver(WebDriver driver) {
		this.tlDriver.set(driver);
	}

	/*
	 * Method returns the driver
	 */
	public WebDriver getDriver() {
		return this.tlDriver.get();
	}

	/*
	 * Close the driver after the completion of a test scenario and take-screenshot
	 * on failure
	 */
	@AfterMethod
	public void closeDriver(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				// Call method to capture screenshot
				File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
				// Move the file to the FailureScreenshots folder
				FileUtils.copyFile(src, new File("FailureScreenshots\\" + result.getClass() + ".png"));
			} catch (Exception e) {
				System.out.println("Exception while taking screenshot " + e.getMessage());
			}
		}
		getDriver().close();
	}

	/*
	 * Method to take screenshot
	 */
	public static String TakeScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
}
