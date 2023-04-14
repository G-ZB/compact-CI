package com.academy.techcenture.pages;

import com.academy.techcenture.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

public class Login extends Base {

    public Login(WebDriver driver, SoftAssert softAssert) {
        super(driver, softAssert);
    }

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//div[text()='Login']")
    private WebElement loginButton;


    @FindBy(xpath = "//div[contains(@class,'negative message')]")
    private WebElement errorMessage;

    public void navigateToLoginPage(){

        driver.get(ConfigReader.getProperty("url"));
        softAssert.assertTrue(driver.getTitle().equals("Cogmento CRM"));
    }

    public void login(){
        emailInput.sendKeys(ConfigReader.getProperty("email"));
        passwordInput.sendKeys(ConfigReader.getProperty("password"));
        softAssert.assertTrue(loginButton.isEnabled(),"Login button is not enabled");
        loginButton.click();
    }

    public void loginNegative(){
        emailInput.sendKeys(ConfigReader.getProperty("invalid@gmail.com"));
        passwordInput.sendKeys(ConfigReader.getProperty("invalidpassword"));
        softAssert.assertTrue(loginButton.isEnabled(),"Login button is not enabled");
        loginButton.click();
        softAssert.assertTrue(errorMessage.isDisplayed(),"Error Message is not displayed");

    }

}
