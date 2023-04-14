package com.academy.techcenture.tests;

import com.academy.techcenture.pages.Login;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

    @Test (priority = 1)
    public void loginTestPositive(){
        Login login = new Login(driver,softAssert);
        login.navigateToLoginPage();
        login.login();
    }

//    @Test(priority = 2)
    public void loginTestNegative() {
        Login login = new Login(driver, softAssert);
        login.navigateToLoginPage();
        login.loginNegative();
    }

}
