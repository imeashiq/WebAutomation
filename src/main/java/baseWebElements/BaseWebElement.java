package baseWebElements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseWebElement {

	/*
	 * Method is to find with WebElement with Expilicit Wait and return it.
	 */
	public WebElement getElement(WebDriver driver, String element, int timeOut) {

		// Create wait with given wait time passed as argument
		WebDriverWait wait = new WebDriverWait(driver, timeOut);

		// Return Element
		WebElement rElement = null;

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
			rElement = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		
		// Create instance of Javascript executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		// now execute query which actually will scroll until that element is not appeared on page.
		je.executeScript("arguments[0].scrollIntoView(true);", rElement);
		
		return rElement;
	}

	public String replaceDynamicLocator(String element, String replacementValue) {
		return element.replace("<<<>>>", replacementValue);
	}
	
	public String replaceMultipleDynamicLocator(String element, String replacementValue1, String replacementValue2) {
		return element.replaceFirst("<<<>>>", replacementValue1).replaceFirst("<<<>>>", replacementValue2);
	}
}
