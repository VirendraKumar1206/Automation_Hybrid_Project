package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.PaymentPage;
import com.relevantcodes.extentreports.LogStatus;

public class Tests extends BaseTest {

	@Test
	public void testing() throws InterruptedException {
		LoginPage lg = new LoginPage(driver);
		HomePage hp = new HomePage(driver);
		CartPage cp = new CartPage(driver);
		PaymentPage pp = new PaymentPage(driver);

		WebDriverWait wait = new WebDriverWait(driver, 20);

		try {
			// Log in
			lg.clickOnSign();
			lg.enterEmailId("penny.jones@example.com");
			lg.enterPassword("Pass@123");
			lg.clickOnLogin();
			test.log(LogStatus.INFO, "Logged in successfully.");

			wait.until(ExpectedConditions.visibilityOf(hp.scrolldown));

			// Add product to cart
			Thread.sleep(2000); // Just to wait a moment
			hp.scrollAndPerformAction(hp.bluetop, hp.clickOnAddTocart);
			test.log(LogStatus.INFO, "Product added to cart.");

			// Navigate to Cart and Checkout
			cp.scrollUp();
			cp.clickOnCartIcon();
			cp.clickOnProceedToCheckOut();
			test.log(LogStatus.INFO, "Proceeded to checkout.");

			cp.enterText("Order Successfully Created");
			cp.clickOnPlaceOrder();
			test.log(LogStatus.INFO, "Order placed.");

			// Payment Process
			wait.until(ExpectedConditions.visibilityOf(pp.name));
			pp.fillName("Penny Jones");
			test.log(LogStatus.INFO, "Entered payment name.");
		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Test failed: " + e.getMessage());
			captureScreenshot("error_screenshot");
		}
	}
}
