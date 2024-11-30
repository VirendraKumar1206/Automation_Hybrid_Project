package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import registration_page.HomePage;
import registration_page.UserAccountPage;
import utilities.ExcelReader;
import com.relevantcodes.extentreports.LogStatus;

public class RegistrationTest extends BaseTest {

	@DataProvider(name = "dpCreateUser")
	public Object[][] createUserData() throws Exception {
		String excelFilePath = "D:\\Users\\Avinash\\eclipse-workspace\\Automation_Hybrid_Project\\src\\test\\resources\\Data 1.xlsx";
		ExcelReader excelReader = new ExcelReader();
		return excelReader.getDataFromExcel(excelFilePath, "Sheet1");
	}

	@Test(dataProvider = "dpCreateUser")
	public void PerformExecution(String name, String email, String title, String password, String day, String month,
			String year, String newsletter, String special, String firstName, String lastName, String company,
			String address1, String address2, String country, String state, String city, String zipcode,
			String mobileNumber) throws Exception {

		logTestStatus(LogStatus.INFO, "Starting user registration test");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickOnSignupButton();
			logTestStatus(LogStatus.INFO, "Clicked on Signup button");

			hp.VerifyAssertion();
			logTestStatus(LogStatus.INFO, "'Login to your account' assertion verified");

			hp.enterName(name);
			hp.enterEmail(email);
			hp.clickOnSignup();
			logTestStatus(LogStatus.INFO, "Entered user details and clicked on Signup");

			Thread.sleep(4000);

			UserAccountPage userAccount = new UserAccountPage(driver);
			userAccount.fillAccountInformation(title, password, day, month, year, newsletter, special, firstName,
					lastName, company, address1, address2, country, state, city, zipcode, mobileNumber);
			logTestStatus(LogStatus.INFO, "Filled out account creation form");

			Assert.assertTrue(userAccount.isAccountCreatedSuccessfully(), "Account creation failed!");
			logTestStatus(LogStatus.PASS, "Account created successfully");

			driver.findElement(By.linkText("Continue")).click();
			Assert.assertTrue(userAccount.verifyLogin(name), "Login verification failed!");
			logTestStatus(LogStatus.INFO, "Clicked on 'Continue' and verified login");

			userAccount.logout();
			Assert.assertEquals(driver.getTitle(), "Automation Exercise - Signup / Login");
			logTestStatus(LogStatus.INFO, "Logged out successfully and verified page title");

		} catch (Exception e) {
			logTestStatus(LogStatus.FAIL, "Test failed with exception: " + e.getMessage());
			throw e;
		}
	}
}
