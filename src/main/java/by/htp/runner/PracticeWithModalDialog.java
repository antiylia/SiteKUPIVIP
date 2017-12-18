package by.htp.runner;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import by.htp.pages.StartPage;

public class PracticeWithModalDialog {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\ChromeDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();		
		
		StartPage page = new StartPage (driver);		
		page.setPropertyWindow();
		
		page.open();
	//	driver.switchTo().activeElement().click();		
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}

}
