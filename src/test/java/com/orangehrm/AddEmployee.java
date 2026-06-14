package com.orangehrm;

import cores.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v143.page.Page;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.orangehrm.*;
import pageObjects.orangehrm.PIMModule.AddEmployeePO;
import pageObjects.orangehrm.PIMModule.EmployeeListPO;

public class AddEmployee extends BaseTest {
    @BeforeClass
    @Parameters({"browser", "appUrl"})
    public void beforeClass(String browserName, String appUrl) {
        username = "Admin";
        password = "admin123";
        employeeFirstName = "Adam";
        employeeLastName = "July";

        driver = getWebDriver(browserName, appUrl);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, driver);
    }

    @Test
    public void TC_01_AddEmployeeSuccessfully() {
        adminLoginPage.enterToUsernameTextbox(username);
        adminLoginPage.enterToPasswordTextbox(password);
        dashboardPage = adminLoginPage.clickToLoginButton();

        dashboardPage.waitForAllLoadingSpinnerDisappeared(driver);
        adminLoginPage.clickToMainMenuModuleByName(driver, "PIM");
        employeeListPage = PageGenerator.getPage(EmployeeListPO.class, driver);

        employeeListPage.waitForAllLoadingSpinnerDisappeared(driver);
        addEmployeePage = employeeListPage.clickToAddEmployeeButton();

        addEmployeePage.waitForAllLoadingSpinnerDisappeared(driver);
//        addEmployeePage.enterToFirstNameTextbox(employeeFirstName);
//        addEmployeePage.enterToLastNameTextbox(employeeLastName);
//        employeeId = addEmployeePage.getEmployeeId();
//        addEmployeePage.clickToSaveButton();
    }

    private WebDriver driver;
    private String appUrl, username, password, employeeFirstName, employeeLastName, employeeId;

    private AdminLoginPO adminLoginPage;
    private DashboardPO dashboardPage;
    private EmployeeListPO employeeListPage;
    private AddEmployeePO addEmployeePage;
    private PersonalDetailsPO personalDetailsPage;

}
