package by.htp.pages;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

import by.htp.utils.TransformData;

public abstract class Page {

	protected WebDriver driver;

	protected Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	protected void open(String url) {
		getDriver().get(url);
	}

	public void finish() {
		driver.quit();
		driver = null;
	}

	public WebDriver setPropertyWindow() {
		driver.manage().window().maximize();
		return driver;
	}

	public WebDriver setPropertyTimeOut() {
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		return driver;
	}

	public void takeScreenShot() {
		File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File screen = new File("screen " + TransformData.transformDateIntoString() + ".png");
		try {
			Files.copy(tmp, screen);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public <T> void takeScreenShot(ExpectedCondition<T> condition) {
		new WebDriverWait(driver, 5).until(condition);
		takeScreenShot();
	}

	public void quickLook(WebElement elementMoveTo, WebElement elementClick) {

		Actions a = new Actions(driver);
		a.moveToElement(elementMoveTo).pause(5).moveToElement(elementClick).click().perform();
	}

	public void moveWithHoldMouseFromElement(WebElement element, int xOffset, int yOffset) {
		Actions builder = new Actions(driver);
		builder.clickAndHold(element).moveByOffset(xOffset, yOffset).release().perform();
	}

	public String getWindowHandle() {
		String handleHost = driver.getWindowHandle();
		return handleHost;
	}

	public void openNewWindowAndSwitchToIt() {
		JavascriptExecutor driverJS = (JavascriptExecutor) driver;
		driverJS.executeScript("window.open()");

		ArrayList<String> listWindows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(listWindows.get(1));
	}

	public void closeWindow(String handle) {
		driver.switchTo().window(handle);
		driver.close();
	}

	// To handle unexpected alert on page load.
	public void handleUnexpectedAlert() {
		try {
			driver.switchTo().alert().dismiss();
		} catch (NoAlertPresentException e) {
			System.out.println("unexpected alert not present");
		}
	}

	public boolean isElementPresent(By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (InvalidSelectorException ex) {
			// !!! it is necessary to realize existing mistakes in Xpath-locator,
			throw ex;
			// re-throw out such exception from method-frame
		} catch (NoSuchElementException ex) {
			return false;
		}
	}

	public boolean areElementsPresent(By locator) {
		return driver.findElements(locator).size() > 0;
	}

	public void scrollToElementByJavascript(WebElement element) {
		String script = "arguments[0].scrollIntoView();";
		((JavascriptExecutor) driver).executeScript(script, element);
	}

	public String getTextByJavascript(WebElement element) {
		String script = "var element = arguments[0];" + "return element.textContent;";
		return (String) ((JavascriptExecutor) driver).executeScript(script, element);
	}

	public void makeElementVisibleByJavascript(WebElement element) {
		String script = "var element = arguments[0];" + "element.style.display='block';";
		((JavascriptExecutor) driver).executeScript(script, element);
	}

}
