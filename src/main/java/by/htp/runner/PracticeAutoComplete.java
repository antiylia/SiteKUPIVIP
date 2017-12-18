package by.htp.runner;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import by.htp.pages.StartPage;

public class PracticeAutoComplete {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "D:\\ChromeDriver\\chromedriver.exe");

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);

		// DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		// capabilities.setCapability("chrome.switches", Arrays.asList("--test-types"));

		WebDriver driver = new ChromeDriver(caps);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// driver.get("http://www.google.com");

		// driver.findElement(By.name("q")).sendKeys("mahatama gandhi");
		// List<WebElement> autoSuggest =
		// driver.findElements(By.xpath("//div[@class='sbqs_c']"));

		// verify the size of the list
		// System.out.println("Size of the AutoSuggets is = " + autoSuggest.size());

		// print the auto suggest
		// for (WebElement a : autoSuggest) {
		// System.out.println("Values are = " + a.getText());
		// }
		// suppose now you want to click on 3rd auto suggest then simply do like this
		// autoSuggest.get(2).click();
		
		StartPage page = new StartPage (driver);
		page.setPropertyWindow();
		page.open();
		
		page.takeScreenShot();
		page.login("antiylia@gmail.com", "0123456");
		
	//	new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='PushTip']"))));
		
		new WebDriverWait(driver, 6).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//iframe[@id='fl-51831']"))));
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='fl-51831']")) );
		
	//	new WebDriverWait(driver, 5).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='fl-51831']")));
		
		driver.findElement(By.xpath("//div[@class='PushTip-close']")).click();
		
	//	driver.switchTo().defaultContent();
		driver.switchTo().parentFrame();
		
	//	Alert alert = (new WebDriverWait(driver, 10)).until(ExpectedConditions.alertIsPresent());
	//	alert.dismiss();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	//	driver.navigate().refresh();
	//	driver.findElement(By.xpath("//a[@href='/campaigns/?showIn=ALL&filter=ALL']")).click();

		driver.findElement(By.id("search-field")).sendKeys("ad");
			
		List<WebElement> autoSuggest = driver.findElements(By.xpath("//ul[@class='typeahead dropdown-menu active']/*"));
		
		
	//	if (isAlertPresent(driver)) {
	//		Alert alert = driver.switchTo().alert();
	//		alert.dismiss();
	//	}
		
		// verify the size of the list
		System.out.println("Size of the AutoSuggets is = " + autoSuggest.size());

			for (WebElement a : autoSuggest) {
				System.out.println("Values are = " + a.getText());
			} 
		page.takeScreenShot();
		autoSuggest.get(autoSuggest.size()-1).click();

		page.takeScreenShot();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}

	public static boolean isAlertPresent(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

}
