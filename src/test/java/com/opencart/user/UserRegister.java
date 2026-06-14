package com.opencart.user;

import cores.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;
import pageObjects.opencart.admin.AdminLoginPO;
import pageObjects.opencart.admin.CustomersPO;
import pageObjects.opencart.admin.DashboardPO;
import pageObjects.opencart.user.UserHomePO;
import pageObjects.opencart.user.UserLoginPO;
import pageObjects.opencart.user.UserRegisterPO;

public class UserRegister extends BaseTest {
    WebDriver driver;

    @BeforeClass
    @Parameters({"userUrl", "adminUrl", "browser"})
    public void beforeClass(String userUrl, String adminUrl, String browserName) {
        this.userUrl = userUrl;
        this.adminUrl = adminUrl;

        userFirstName = "Adam";
        userLastName = "July";
        userEmail = "adamjuly00014@gmail.com";
        userPassword = "Abcd1234!";
        adminUsername = "adamjuly";
        adminPassword = "Zhugeliang2107!";

        driver = getWebDriver(browserName, userUrl);
        userHomePage = PageGenerator.getPage(UserHomePO.class, driver);
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
        Assert.assertTrue(userRegisterPage.isSuccessfulMessageDisplayed());
        userRegisterPage.clickToLogoutAtUserSite(driver);
        userRegisterPage.openAdminSiteUrl(driver, adminUrl);

        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, driver);
        adminLoginPage.enterAdminUsername(adminUsername);
        adminLoginPage.enterAdminPassword(adminPassword);

        dashboardPage = adminLoginPage.clickToLoginButton();
        customersPage = dashboardPage.openCustomersPage(driver);
        Assert.assertTrue(customersPage.isCustomersPageDisplayed());

        adminLoginPage = customersPage.clickToLogoutAtAdminSite(driver);
        adminLoginPage.openUserSiteUrl(driver, userUrl);

        userHomePage = PageGenerator.getPage(UserHomePO.class, driver);

        userLoginPage = userHomePage.clickToLoginAtUserSite(driver);
        userLoginPage.enterEmailTextbox(userEmail);
        userLoginPage.enterPasswordTextbox(userPassword);

        userHomePage = userLoginPage.clickToLoginButton();
        Assert.assertTrue(userHomePage.isMyAccountPageDisplayed());
    }

//    @AfterClass
//    public void afterClass() {
//        quitDriver();
//    }

    private String userUrl;
    private String adminUrl;
    private String userFirstName, userLastName, userEmail, userPassword, adminUsername, adminPassword;

    private UserHomePO userHomePage;
    private UserLoginPO userLoginPage;
    private UserRegisterPO userRegisterPage;
    private AdminLoginPO adminLoginPage;
    private DashboardPO dashboardPage;
    private CustomersPO customersPage;
}
