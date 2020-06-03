package baseWebElements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ConfigReader;

public class BaseWebElement {

	boolean isDynamicElement = false;

	/*
	 * Method is to find with WebElement with Expilicit Wait and return it.
	 */
	public WebElement getElement(WebDriver driver, String element, int timeOut) {

		//Wait for page load
		waitForPageLoad(driver);
		
		// Create wait with given wait time passed as argument
		WebDriverWait wait = new WebDriverWait(driver, timeOut);

		// Return Element
		WebElement rElement = null;

		if (!isDynamicElement) {
			// Get the property from the Object Repository
			ConfigReader objectRepo = new ConfigReader();
			element = objectRepo.getObjectProperty(element);
		}

		By locator = null;
		try {
			String[] divider = element.split(":", 2);
			String locatorBy = divider[0];
			String locatorValue = divider[1];

			switch (locatorBy.toUpperCase()) {
			case "XPATH":
				locator = By.xpath(locatorValue);
				break;
			case "ID":
				locator = By.id(locatorValue);
				break;
			case "NAME":
				locator = By.name(locatorValue);
				break;
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			rElement = wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}

		// Create instance of Javascript executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		// now execute query which actually will scroll until that element is not
		// appeared on page.
		je.executeScript("arguments[0].scrollIntoView();", rElement);

		isDynamicElement = false;

		return rElement;
	}

	public void waitForPageLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}

	public String replaceDynamicLocator(String element, String replacementValue) {
		// Get the property from the Object Repository
		ConfigReader objectRepo = new ConfigReader();
		element = objectRepo.getObjectProperty(element);
		isDynamicElement = true;
		return element.replace("<<<>>>", replacementValue);
	}

	public String replaceMultipleDynamicLocator(String element, String replacementValue1, String replacementValue2) {
		// Get the property from the Object Repository
		ConfigReader objectRepo = new ConfigReader();
		element = objectRepo.getObjectProperty(element);
		isDynamicElement = true;
		return element.replaceFirst("<<<>>>", replacementValue1).replaceFirst("<<<>>>", replacementValue2);
	}

	/*
	 * Return Boolean value based on presence of element
	 */
	public boolean isElementPresent(WebDriver driver, String element, int timeOut) {
		try {
			getElement(driver, element, 8).isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
