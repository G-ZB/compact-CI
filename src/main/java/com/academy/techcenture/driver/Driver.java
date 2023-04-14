package com.academy.techcenture.driver;

import com.academy.techcenture.config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Driver {

    //Created Class Driver. In this class I will have all elements (proprieties and methods and constructor)


    //Declare a propriety(Variable) called driver of type WebDriver. since this variable is private static it can not be used in a constructor
    private static WebDriver driver;

    //I will also create an empty (because the declared propriety is static) private constructor, so that the class Driver can not be instantiated (Driver driver = new Driver())
    private Driver() {

    }

//Will create Methods to get the Browsers (Chrome, Edge, Safari,...): Since we will be returning a browser, the Method return will be of type WebDriver

    public static WebDriver getDriver() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        String browser = ConfigReader.getProperty("browser");
        String implicitWait = ConfigReader.getProperty("implicitWait");
        String loadPage = ConfigReader.getProperty("pageLoadTime");
        String headless = ConfigReader.getProperty("headless");



        switch(browser){

            case "chrome":
                WebDriverManager.chromedriver().setup();
                WebDriver driver = new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(implicitWait)));
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(loadPage)));
                driver.manage().window().maximize();
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("autofill.profile_enabled", false);
                prefs.put("autofill.password_manage_enabled", false);
                prefs.put("autofill.default_content_setting_values_notifications", 2);

                options.setExperimentalOption("prefs", prefs);
                options.addArguments("--headless");
                options.setHeadless(Boolean.parseBoolean(headless));


                return driver;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
                driver.manage().window().maximize();
                return driver;

            case "firefox":

                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
                driver.manage().window().maximize();
                return driver;

            default:
                throw new RuntimeException("No driver found");
        }

    }

    public static void quitDriver() {

        if (driver != null) {
            driver.quit();

        }
    }
}


//------Not Working with If need to find out why----------------
//
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-allow-origins=*");
//
//
//        String browser = ConfigReader.getProperty("browser");
//        String implicitWait = ConfigReader.getProperty("implicitWait");
//        String loadPage = ConfigReader.getProperty("pageLoadTime");
//
//
//
//        if (browser.equalsIgnoreCase(("chrome"))) {
//            WebDriverManager.chromedriver().setup();
//            WebDriver driver = new ChromeDriver(options);
//             Map<String, Object> prefs = new HashMap<>();
//            prefs.put("autofill.profile_enabled", false);
//            prefs.put("autofill.password_manage_enabled", false);
//            prefs.put("autofill.default_content_setting_values_notifications", 2);
//            options.setExperimentalOption("prefs", prefs);
//
//        } else if (browser.equalsIgnoreCase(("firefox"))) {
//            WebDriverManager.firefoxdriver().setup();
//            driver = new FirefoxDriver();
//
//        } else if (browser.equalsIgnoreCase(("edge"))) {
//            WebDriverManager.edgedriver().setup();
//            driver = new EdgeDriver();
//        } else {
//            throw new RuntimeException("No driver found");
//        }
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(implicitWait)));
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(loadPage)));
//        driver.manage().window().maximize();
//        return driver;
//    }
//        public static void quitDriver() {
//
//            if (driver != null) {
//                driver.quit();
//
//            }
//        }
//    }

