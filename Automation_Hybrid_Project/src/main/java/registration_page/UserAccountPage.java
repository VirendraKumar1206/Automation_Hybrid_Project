package registration_page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserAccountPage {

	private WebDriver driver;
	private WebDriverWait wait;

	public UserAccountPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 20);
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "name")
	private WebElement nameField;

	@FindBy(xpath = "//div[@class='signup-form']//input[@type='email']")
	private WebElement emailField;

	@FindBy(xpath = "//button[@data-qa='signup-button']")
	private WebElement signupButton;

	@FindBy(id = "id_gender1")
	private WebElement mrRadioButton;

	@FindBy(id = "id_gender2")
	private WebElement mrsRadioButton;

	@FindBy(id = "password")
	private WebElement passwordField;

	@FindBy(id = "days")
	private WebElement dayDropdown;

	@FindBy(id = "months")
	private WebElement monthDropdown;

	@FindBy(id = "years")
	private WebElement yearDropdown;

	@FindBy(id = "newsletter")
	private WebElement newsletterCheckbox;

	@FindBy(id = "optin")
	private WebElement specialCheckbox;

	@FindBy(id = "first_name")
	private WebElement firstNameField;

	@FindBy(id = "last_name")
	private WebElement lastNameField;

	@FindBy(id = "company")
	private WebElement companyField;

	@FindBy(id = "address1")
	private WebElement address1Field;

	@FindBy(id = "address2")
	private WebElement address2Field;

	@FindBy(id = "country")
	private WebElement countryDropdown;

	@FindBy(id = "state")
	private WebElement stateField;

	@FindBy(id = "city")
	private WebElement cityField;

	@FindBy(id = "zipcode")
	private WebElement zipcodeField;

	@FindBy(id = "mobile_number")
	private WebElement mobileNumberField;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submitButton;

	@FindBy(xpath = "//section[@id='form']//h2/b[text()='Account Created!']")
	private WebElement accountCreatedMessage;

	@FindBy(xpath = "//*[@id='header']//a/b")
	private WebElement loggedInName;

	@FindBy(linkText = "Logout")
	private WebElement logoutButton;

	private void waitForElementToBeClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void fillSignupForm(String name, String email) {
		waitForElementToBeClickable(nameField);
		nameField.sendKeys(name);
		emailField.sendKeys(email);
		signupButton.click();
	}

	public void fillAccountInformation(String title, String password, String day, String month, String year,
			String newsletter, String special, String firstName, String lastName, String company, String address1,
			String address2, String country, String state, String city, String zipcode, String mobileNumber) {

		if (title.equalsIgnoreCase("Mr")) {
			mrRadioButton.click();
		} else if (title.equalsIgnoreCase("Mrs")) {
			mrsRadioButton.click();
		}

		passwordField.sendKeys(password);

		waitForElementToBeClickable(passwordField);
		new Select(dayDropdown).selectByVisibleText(day);
		new Select(monthDropdown).selectByVisibleText(month);
		new Select(yearDropdown).selectByVisibleText(year);

		if (newsletter.equalsIgnoreCase("Yes")) {
			newsletterCheckbox.click();
		}

		if (special.equalsIgnoreCase("Yes")) {
			specialCheckbox.click();
		}

		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
		companyField.sendKeys(company);
		address1Field.sendKeys(address1);
		address2Field.sendKeys(address2);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", address2Field);
		new Select(countryDropdown).selectByVisibleText(country);

		stateField.sendKeys(state);
		cityField.sendKeys(city);
		zipcodeField.sendKeys(zipcode);
		mobileNumberField.sendKeys(mobileNumber);

		submitButton.click();
	}

	public boolean isAccountCreatedSuccessfully() {
		return accountCreatedMessage.isDisplayed();
	}

	public boolean verifyLogin(String name) {
		return loggedInName.getText().equals(name);
	}

	public void logout() {
		logoutButton.click();
	}
}
