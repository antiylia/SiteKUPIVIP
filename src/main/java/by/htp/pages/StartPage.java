package by.htp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StartPage extends Page {

	private static final String URL = "https://www.kupivip.ru/";

	@FindBy(xpath = "//span[contains(text(), 'Вход')]")
	WebElement buttonLogIn;

	@FindBy(xpath = "//div[contains(text(), 'Личный кабинет')]")
	WebElement popUpAutorization;

	@FindBy(id = "user-name")
	WebElement inputEmail;

	@FindBy(id = "password")
	WebElement inputPassword;

	@FindBy(xpath = "//button[@id='login-submit']")
	WebElement buttonLogin;

	@FindBy(xpath = "//a[contains(text(), 'Для дома')]")
	WebElement filrtForHome;

	@FindBy(xpath = "//a[contains(text(), 'Предметы интерьера')]")
	WebElement designGoods;

	@FindBy(xpath = "//a[@href='/catalog/dlya-doma/predmety-interera/chasy']")
	WebElement clocks;

	public StartPage(WebDriver driver) {
		super(driver);
	}

	public void open() {
		super.open(URL);
	}

	public void login(String email, String password) {
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(buttonLogIn));
		buttonLogIn.click();
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(popUpAutorization));
		inputEmail.sendKeys(email);
		inputPassword.sendKeys(password);
		buttonLogin.click();
	}

	public void takeScreenShotAfterAutorization() {
		takeScreenShot(ExpectedConditions.invisibilityOf(popUpAutorization));
	}

	public ClockPage goToClockPage() {
		filrtForHome.click();
		quickLook(designGoods, clocks);
		return new ClockPage(driver);
	}

}
