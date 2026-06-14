package pageObjects.opencart.admin;

import cores.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.opencart.admin.AdminLoginPageUI;

public class AdminLoginPO extends BasePage {
    private WebDriver driver;

    public AdminLoginPO(WebDriver driver) {
        this.driver = driver;
    }

    public void enterAdminUsername(String adminUsername) {
        waitForElementVisible(driver, AdminLoginPageUI.USER_NAME_TEXTBOX);
        sendKeyToElement(driver, AdminLoginPageUI.USER_NAME_TEXTBOX, adminUsername);
    }

    public void enterAdminPassword(String adminPassword) {
        waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, adminPassword);
    }

    public DashboardPO clickToLoginButton() {
        waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getPage(DashboardPO.class, driver);
    }
}
