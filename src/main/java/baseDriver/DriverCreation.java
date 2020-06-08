package baseDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import utilities.ConfigReader;

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
		WebDriverManager.chromedriver().setup();
		// Driver Creation
		ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        //For Headless execution uncomment the below line
        //chromeOptions.setHeadless(true);
		WebDriver driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		// Navigating to BaseURL
		driver.get(reader.getBaseURL());
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
				takeScreenshot(getDriver(), result.getClass().getName());
			} catch (Exception e) {
				System.out.println("Exception while taking screenshot " + e.getMessage());
			}
		}
		getDriver().close();
	}

	/*
	 * Method to take screenshot
	 */
	@Attachment
	public byte[] takeScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return ts.getScreenshotAs(OutputType.BYTES);
	}
}
