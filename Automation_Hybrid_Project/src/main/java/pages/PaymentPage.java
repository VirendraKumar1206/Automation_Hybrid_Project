package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentPage {
	private WebDriver driver;

	public PaymentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "name_on_card")
	public WebElement name;

	@FindBy(xpath = "//input[@name='card_number' and @data-qa='card-number']")
	public WebElement cardNumber;

	@FindBy(name = "cvc")
	public WebElement CVC;

	@FindBy(name = "expiry_month")
	public WebElement expiryMonth;

	@FindBy(name = "expiry_year")
	public WebElement expiryYear;

	@FindBy(id = "submit")
	public WebElement submit;

	@FindBy(xpath = "//a[@class='btn btn-primary']")
	public WebElement clickOnContinue;

	public void fillName(String name) {
		waitForElementToBeClickable(this.name);
		this.name.sendKeys(name);
	}

	public void fillCard(String cardNumber) {
		waitForElementToBeClickable(this.cardNumber);
		this.cardNumber.sendKeys(cardNumber);
	}

	public void fillCVC(String cvc) {
		waitForElementToBeClickable(this.CVC);
		this.CVC.sendKeys(cvc);
	}

	public void fillExpiryMonth(String month) {
		waitForElementToBeClickable(this.expiryMonth);
		this.expiryMonth.sendKeys(month);
	}

	public void fillExpiryYear(String year) {
		waitForElementToBeClickable(this.expiryYear);
		this.expiryYear.sendKeys(year);
	}

	public void submitPayment() {
		waitForElementToBeClickable(this.submit);
		submit.click();
	}

	public void clickContinue() {
		waitForElementToBeClickable(this.clickOnContinue);
		clickOnContinue.click();
	}

	private void waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void fillPaymentDetailsAndSubmit(String name, String cardNumber, String cvc, String expiryMonth,
			String expiryYear) {
		fillName(name);
		fillCard(cardNumber);
		fillCVC(cvc);
		fillExpiryMonth(expiryMonth);
		fillExpiryYear(expiryYear);
		submitPayment();
	}
}
