package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	private WebDriver driver;

	// Constructor to initialize the driver and page factory elements
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[normalize-space()='Signup / Login']")
	private WebElement signInbutton;

	@FindBy(xpath = "//input[@data-qa='login-email']")
	private WebElement emailAddress;

	@FindBy(xpath = "//input[@placeholder='Password']")
	private WebElement enterPassword;

	@FindBy(xpath = "//button[normalize-space()='Login']")
	private WebElement loginButton;

	public void clickOnSign() {
		waitForElementToBeClickable(signInbutton);
		signInbutton.click();
	}

	public void enterEmailId(String email) {
		waitForElementToBeClickable(emailAddress);
		emailAddress.sendKeys(email);
	}

	public void enterPassword(String password) {
		waitForElementToBeClickable(enterPassword);
		enterPassword.sendKeys(password);
	}

	public void clickOnLogin() {
		waitForElementToBeClickable(loginButton);
		loginButton.click();
	}

	private void waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
}
