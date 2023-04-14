package com.academy.techcenture.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class Base {

//Has the following 3 references---

    protected WebDriver driver;
    protected SoftAssert softAssert;
    protected WebDriverWait wait;
    protected Actions actions; // Action class to hover over

    public Base(WebDriver driver, SoftAssert softAssert) {
        this.driver = driver;
        this.softAssert = softAssert;
        this.wait= new WebDriverWait(this.driver, Duration.ofSeconds(20));
        this.actions = new Actions(this.driver);
        PageFactory.initElements(driver, this);

    }
}
