package by.htp.runner;

import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy;

import by.htp.pages.StartPage;

public class ForSearchingSelect {

    private static WebDriver driver;

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver", "D:\\ChromeDriver\\chromedriver.exe");

		
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);

		driver = new ChromeDriver(capabilities);

		StartPage page = new StartPage(driver);
		page.setPropertyWindow();
		driver.get("http://antiylia@gmail.com:0123456@kupivip.ru/"); // deprecated
		
		// WebElement select = driver.findElement(By.xpath("//*[@id='birth-day']"));
		// String text = getTextByJavascript(select);
		// System.out.println(text);

		// makeElementVisibleByJavascript(select);
		
	}

}
