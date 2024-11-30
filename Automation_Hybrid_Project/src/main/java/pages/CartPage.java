package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	private WebDriver driver;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[normalize-space()='Home']")
	private WebElement scrollup;

	@FindBy(xpath = "//ul[@class='nav navbar-nav']//li[a[@href='/view_cart' and contains(text(), 'Cart')]]")
	private WebElement clickOnCart;

	@FindBy(xpath = "//a[@class='btn btn-default check_out']")
	private WebElement clickOnProceedToCheckout;

	@FindBy(xpath = "//b[text()='Total Amount']")
	private WebElement scrolldown;

	@FindBy(xpath = "//textarea[@name='message']")
	private WebElement orderTestField;

	@FindBy(xpath = "//a[@class='btn btn-default check_out']")
	private WebElement clickOnPlaceOrder;

	public void scrollUpToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void clickOnCartIcon() {
		clickOnCart.click();
	}

	public void clickOnProceedToCheckOut() {
		clickOnProceedToCheckout.click();
	}

	public void scrollDownToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void enterText(String text) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(orderTestField)).sendKeys(text);
	}

	public void clickOnPlaceOrder() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(clickOnPlaceOrder)).click();
	}

	public void scrollUp() {
		scrollUpToElement(scrollup);
	}

	public void scrollDown() {
		scrollDownToElement(scrolldown);
	}

}
