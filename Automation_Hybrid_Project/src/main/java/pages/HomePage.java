package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	private WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[normalize-space()='Women']")
	public WebElement scrolldown;

	@FindBy(xpath = "(//div[@class='product-image-wrapper'])[1]")
	public WebElement bluetop;

	@FindBy(xpath = "(//a[@data-product-id='1'])[1]")
	public WebElement clickOnAddTocart;

	@FindBy(xpath = "//button[@class='btn btn-success close-modal btn-block']")
	private WebElement continueshoping;

	// Method to scroll to an element and perform actions
	public void scrollAndPerformAction(WebElement elementToScroll, WebElement elementToInteract)
			throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 20);

		js.executeScript("arguments[0].scrollIntoView(true);", elementToScroll);

		wait.until(ExpectedConditions.elementToBeClickable(elementToInteract));

		Actions act = new Actions(driver);
		act.moveToElement(elementToInteract).perform();

		// Use JavascriptExecutor to ensure the click happens even if there are overlay
		// issues
		js.executeScript("arguments[0].click();", elementToInteract);

		// Optionally, wait for and click on the "Continue Shopping" button after adding
		// item to the cart
		wait.until(ExpectedConditions.elementToBeClickable(continueshoping)).click();
	}

	// Use the scrollAndPerformAction method for the first product
	public void firstProduct() throws InterruptedException {
		scrollAndPerformAction(bluetop, clickOnAddTocart);
	}
}
