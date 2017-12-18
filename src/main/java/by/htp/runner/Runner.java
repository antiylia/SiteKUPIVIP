package by.htp.runner;

import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import by.htp.pages.ClockPage;
import by.htp.pages.StartPage;

public class Runner {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "D:\\ChromeDriver\\chromedriver.exe");

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);

		WebDriver driver = new ChromeDriver(caps);

		StartPage page = new StartPage(driver);
		page.setPropertyWindow();
		page.open();

		page.takeScreenShot();
		page.login("antiylia@gmail.com", "0123456");
		page.takeScreenShotAfterAutorization();

		ClockPage clockPage = page.goToClockPage();
		page.takeScreenShot();

		clockPage.choosePriceRangeManually();
		clockPage.takeScreenShotAfterSetUpPrice();

		String handleWindow1 = clockPage.getWindowHandle();
		System.out.println(handleWindow1); // CDwindow-4d17b234-3425-4b9b-8f53-46a2f90f489e
		clockPage.openNewWindowAndSwitchToIt();

		String handleWindow2 = clockPage.getWindowHandle();
		System.out.println(handleWindow2);
		page.open();

		WebElement nessecaryElement = driver.findElement(By.xpath("//h2[contains(text(), 'Почему выбирают нас')]"));
		page.scrollToElementByJavascript(nessecaryElement);
		page.takeScreenShot();

	}
}
