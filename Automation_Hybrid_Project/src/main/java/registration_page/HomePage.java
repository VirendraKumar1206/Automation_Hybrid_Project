package registration_page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage {
	private WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@href='/login']")
	private WebElement Signup_Login_button;

	@FindBy(xpath = "//div[@class='login-form']//h2[text()='Login to your account']")
	private WebElement assertion;

	@FindBy(name = "name")
	private WebElement nameField;

	@FindBy(xpath = "//div[@class='signup-form']//input[@type='email']")
	private WebElement email;

	@FindBy(xpath = "//button[@data-qa='signup-button']")
	private WebElement SignupButton;

	private void waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void clickOnSignupButton() {
		waitForElementToBeClickable(Signup_Login_button);
		Signup_Login_button.click();
	}

	public void VerifyAssertion() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.textToBePresentInElement(assertion, "Login to your account"));
		Assert.assertEquals(assertion.getText(), "Login to your account",
				"Assertion failed: The expected text was not found.");
	}

	public void enterName(String name) {
		waitForElementToBeClickable(nameField);
		nameField.sendKeys(name);
	}

	public void enterEmail(String emailAddress) {
		waitForElementToBeClickable(email);
		email.sendKeys(emailAddress);
	}

	public void clickOnSignup() {
		waitForElementToBeClickable(SignupButton);
		SignupButton.click();
	}
}
