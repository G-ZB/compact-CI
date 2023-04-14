package com.academy.techcenture.tests;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected ExtentReports reports;
    protected ExtentTest extentTest;

    protected SoftAssert softAssert;


    @BeforeTest

    public void beforeTest() {
        reports = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReports.html", true);//will create a folder (tests-output) with an html file
        reports.addSystemInfo("OS NAME", System.getProperty("os.name"));
        reports.addSystemInfo("Engineer", System.getProperty("user.name"));
        reports.addSystemInfo("Environment", "QA");
        reports.addSystemInfo("JAVA Version", System.getProperty("java.version"));
    }

    @AfterTest

    public void afterTest(){
        reports.flush();
        reports.close();

    }
    @BeforeMethod

    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.setHeadless(true);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();


        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();

        String browserName = cap.getBrowserName();
        String browserVersion = (String)cap.getCapability("browserVersion");



        reports.addSystemInfo("BROWSER NAME", browserName);
        reports.addSystemInfo("BROWSER Version", browserVersion);

        softAssert = new SoftAssert();

    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {

        if(result.getStatus()==ITestResult.FAILURE){
            extentTest.log(LogStatus.FAIL,"TEST CASE FAILES IS " + result.getName());
            extentTest.log(LogStatus.FAIL,"TEST CASE FAILES IS " + result.getThrowable());

            String screenshotPath = getScreenshot(driver, result.getName());
            extentTest.log(LogStatus.FAIL,extentTest.addScreenCapture(screenshotPath));
        }

        reports.endTest(extentTest);
        softAssert.assertAll();

        if (driver != null) {
            driver.quit();
        }
    }




    //Method that will take screenshots and save them in a specific folder. this method and screenshots will be used in the after Method to include them in the report
    public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        String destination = ((System.getProperty("user.dir") + "/test-output/" + screenshotName + ".png"));

        //Copy file at destination
        File finalDest = new File(destination);
        FileUtils.copyFile(source, finalDest);
        return destination;

    }


}
