package by.htp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ClockPage extends Page {

	private static final String URL = "https://www.kupivip.ru/catalog/dlya-doma/predmety-interera/chasy";

	@FindBy(xpath = "//button[@class='btn-dropdown']/span[contains(text(), 'Цена')]")
	WebElement filtrPrice;

	@FindBy(xpath = "//div[@class='noUi-handle noUi-handle-lower']")
	WebElement lowerPrice;

	@FindBy(xpath = "//div[@class='noUi-handle noUi-handle-upper']")
	WebElement higherPrice;

	// refactor!!!
	@FindBy(xpath = "//div[@class='price-slider']//following-sibling::div[@class='dropdown-controls']/button[contains(text(), 'Применить')]")
	WebElement submitPrice;

	@FindBy(xpath = "//h1[contains(text(), 'Часы для дома цена')]")
	WebElement resultFiltrPrice;

	@FindBy(xpath = "//a[@href='/catalog/zhenschinam']//span[contains(text(), 'Женщинам')]")
	WebElement linkForWomen;

	public ClockPage(WebDriver driver) {
		super(driver);
	}

	public void open() {
		super.open(URL);
	}

	public void choosePriceRangeManually() {
		filtrPrice.click();
		moveWithHoldMouseFromElement(lowerPrice, 50, 0);
		moveWithHoldMouseFromElement(higherPrice, -70, 0);
		submitPrice.click();
	}

	public void takeScreenShotAfterSetUpPrice() {
		takeScreenShot(ExpectedConditions.visibilityOf(resultFiltrPrice));
	}

}
