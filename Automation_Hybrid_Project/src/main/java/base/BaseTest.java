package base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeMethod
    public void setup() {
        extent = new ExtentReports("target/ExtentReport.html", true);
        test = extent.startTest(this.getClass().getSimpleName());

        System.setProperty("webdriver.chrome.driver", "D:\\Users\\Avinash\\eclipse-workspace\\Automation_Hybrid_Project\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://automationexercise.com/");
    }
    
    public void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

  
    public void captureScreenshot(String screenshotName) {
        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = "target/screenshots/" + screenshotName + ".png";
            FileUtils.copyFile(screenshotFile, new File(screenshotPath));
            test.log(LogStatus.FAIL, "Test failed", test.addScreenCapture(screenshotPath));
        } catch (IOException e) {
            test.log(LogStatus.ERROR, "Error capturing screenshot: " + e.getMessage());
        }
    }

    public void logTestStatus(LogStatus status, String message) {
        test.log(status, message);
    }
    
    @AfterMethod
    public void teardown() {
        extent.endTest(test);
        extent.flush();
        if (driver != null) {
            driver.quit();
        }
    }
}
