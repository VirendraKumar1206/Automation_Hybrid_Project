package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelReader;

public class TestCase_CreateUser {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 30); // Explicit wait for dynamic elements
        driver.get("https://automationexercise.com/");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @DataProvider(name = "dpCreateUser")
    public Object[][] createUserData() throws Exception {
        String excelFilePath = "D:\\Users\\Avinash\\eclipse-workspace\\Automation_Hybrid_Project\\src\\test\\resources\\Data.xlsx";  // Excel file path
        ExcelReader excelReader = new ExcelReader();
        return excelReader.getDataFromExcel(excelFilePath, "Sheet1");  // Excel sheet name
    }

    @Test(dataProvider = "dpCreateUser")
    public void testCreateUser(String name, String email, String title, String password, String day, String month, String year, String newsletter, String special, String firstName, String lastName, String company, String address1, String address2, String country, String state, String city, String zipcode, String mobileNumber) throws InterruptedException {
        // Verify homepage title
        Assert.assertEquals(driver.getTitle(), "Automation Exercise");

        // Click on 'Signup / Login'
        driver.findElement(By.linkText("Signup / Login")).click();

        // Verify 'New User Signup!' text is visible
        waitForElementToBeVisible(By.xpath("//section[@id='form']/div/div/div[3]/div/h2"));
        Assert.assertEquals(driver.findElement(By.xpath("//h2[normalize-space()='Login to your account']")).getText(), "Login to your account");

        // Fill out the Signup form
        driver.findElement(By.name("name")).sendKeys(name);
        driver.findElement(By.xpath("//div[@class='signup-form']//input[@type='email']")).sendKeys(email);
        driver.findElement(By.xpath("//button[text()='Signup']")).click();

        // Verify redirection to 'Enter Account Information' page
        waitForElementToBeVisible(By.xpath("//div[@class='login-form']//h2/b"));
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='login-form']//h2/b")).getText(), "ENTER ACCOUNT INFORMATION");

        // Fill in account details
        fillAccountInformation(title, password, day, month, year, newsletter, special, firstName, lastName, company, address1, address2, country, state, city, zipcode, mobileNumber);

        // Verify account creation success
        waitForElementToBeVisible(By.xpath("//section[@id='form']//h2/b[text()='Account Created!']"));
        Assert.assertTrue(driver.findElement(By.xpath("//section[@id='form']//h2/b[text()='Account Created!']")).isDisplayed());

        // Click Continue and verify login
        driver.findElement(By.linkText("Continue")).click();
        waitForElementToBeVisible(By.xpath("//*[@id='header']//a/b"));
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id='header']//a/b")).getText(), name);

        // Logout
        driver.findElement(By.linkText("Logout")).click();
        Assert.assertEquals(driver.getTitle(), "Automation Exercise - Signup / Login");
    }

    // Reusable method to wait for an element to be visible
    private void waitForElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Reusable method to fill in account information
    private void fillAccountInformation(String title, String password, String day, String month, String year,
                                        String newsletter, String special, String firstName, String lastName, String company,
                                        String address1, String address2, String country, String state, String city, String zipcode, String mobileNumber)
            throws InterruptedException {

        if (title.equalsIgnoreCase("Mr")) {
            driver.findElement(By.id("id_gender1")).click();
        } else if (title.equalsIgnoreCase("Mrs")) {
            driver.findElement(By.id("id_gender2")).click();
        }

        driver.findElement(By.id("password")).sendKeys(password);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement passwordField = driver.findElement(By.xpath("//input[@id='password']"));
        js.executeScript("arguments[0].scrollIntoView(true);", passwordField);

        new Select(driver.findElement(By.id("days"))).selectByVisibleText(day);
        new Select(driver.findElement(By.id("months"))).selectByVisibleText(month);
        new Select(driver.findElement(By.id("years"))).selectByVisibleText(year);

        if (newsletter.equalsIgnoreCase("Yes")) {
            driver.findElement(By.id("newsletter")).click();
        }

        if (special.equalsIgnoreCase("Yes")) {
            driver.findElement(By.id("optin")).click();
        }

        driver.findElement(By.id("first_name")).sendKeys(firstName);
        driver.findElement(By.id("last_name")).sendKeys(lastName);
        driver.findElement(By.id("company")).sendKeys(company);
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//input[@id='company']")));

        driver.findElement(By.id("address1")).sendKeys(address1);
        driver.findElement(By.id("address2")).sendKeys(address2);
        new Select(driver.findElement(By.id("country"))).selectByVisibleText(country);

        driver.findElement(By.id("state")).sendKeys(state);
        driver.findElement(By.id("city")).sendKeys(city);

        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[normalize-space()='Create Account']")));
        driver.findElement(By.id("zipcode")).sendKeys(zipcode);
        driver.findElement(By.id("mobile_number")).sendKeys(mobileNumber);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }
}
