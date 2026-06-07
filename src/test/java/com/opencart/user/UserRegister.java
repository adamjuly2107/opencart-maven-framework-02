package com.opencart.user;

import cores.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;

public class UserRegister extends BaseTest {
    WebDriver driver;

    @BeforeClass
    @Parameters({"userUrl", "adminUrl", "browser"})
    public void beforeClass(String userUrl, String adminUrl, String browserName) {
        this.userUrl = userUrl;
        this.adminUrl = adminUrl;

        userFirstName = "Adam";
        userLastName = "July";
        userEmail = "adamjuly00008@gmail.com";
        userPassword = "Abcd1234!";

        // Open User Home Page
        driver = getWebDriver(browserName, userUrl);
        userHomePage = PageGeneratorGeneric.getPage(UserHomePO.class, driver);
    }

    @Test
    public void TC_01_Register_User_Successfully() {
        userLoginPage = userHomePage.clickToFooterMyAccountLink(driver);

        userRegisterPage = userLoginPage.clickToContinueButton();
        userRegisterPage.enterFirstNameTextbox(userFirstName);
        userRegisterPage.enterLastNameTextbox(userLastName);
        userRegisterPage.enterEmailTextbox(userEmail);
        userRegisterPage.enterPasswordTextbox(userPassword);
        userRegisterPage.acceptPolicy();
        userRegisterPage.clickToContinueButton();

        userHomePage = userRegisterPage.clickToLogoutMenu();
        userLoginPage = userHomePage.clickToLoginMenu();

        userLoginPage.enterEmailTextbox(userEmail);
        userLoginPage.enterPasswordTextbox(userPassword);
        userLoginPage.clickToLoginButton();
    }

//    @AfterClass
//    public void afterClass() {
//        quitDriver();
//    }

    private String userUrl;
    private String adminUrl;
    private String userFirstName, userLastName, userEmail, userPassword;

    private UserHomePO userHomePage;
    private UserLoginPO userLoginPage;
    private UserRegisterPO userRegisterPage;
}
