package by.htp.pagetest;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import by.htp.pages.StartPage;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

@Title("Test suit - manipulation start page")
public class TestStartPage extends BaseTest {

	private StartPage page;

	@FindBy(xpath = "//iframe[@id='fl-51831']")
	WebElement startIframe;

	@FindBy(xpath = "//div[@class='PushTip-close']")
	WebElement closeIframe;

	@FindBy(id = "search-field")
	WebElement inputForAutoComplete;

	@BeforeClass
	public void start() {
		page = new StartPage(driver);
		page.setPropertyWindow();
		page.open();
	}

	@Title("logIn-test, with taking screenshot")
	@Test(priority = 0)
	public void logIn() {
		page.login("antiylia@gmail.com", "0123456");
		page.takeScreenShotAfterAutorization();
	}

	@Step("Creation nessesary fixture")
	public void fixtureBeforeAutoComplete() {
		new WebDriverWait(driver, 6).until(ExpectedConditions.visibilityOf(startIframe));
		driver.switchTo().frame(startIframe);
		closeIframe.click();
		driver.switchTo().parentFrame();
	}

	@Title("autoComplete-test, which check, the autoSuggest list isn't empty, with taking screenshot")
	@Description("After filling 'ad' into the searching input, it is expected suggesting list of options")
	@Test(priority = 1)
	public void autoComplete() {
		fixtureBeforeAutoComplete();
		inputForAutoComplete.sendKeys("ad");

		new WebDriverWait(driver, 4).until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.xpath("//ul[@class='typeahead dropdown-menu active']/*")));
		List<WebElement> autoSuggest = driver.findElements(By.xpath("//ul[@class='typeahead dropdown-menu active']/*"));

		assertTrue(autoSuggest.size() > 0, "Size of list isn't more than 0");

		page.takeScreenShot();
		autoSuggest.get(autoSuggest.size() - 1).click();
		page.takeScreenShot();
	}

}
