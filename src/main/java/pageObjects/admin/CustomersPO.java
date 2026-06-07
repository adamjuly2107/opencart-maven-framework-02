package pageObjects.admin;

import cores.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.admin.CustomersPageUI;

public class CustomersPO extends BasePage {
    private WebDriver driver;

    public CustomersPO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isCustomersPageDisplayed() {
        waitForElementVisible(driver, CustomersPageUI.CUSTOMER_BREADCRUMB);
        return isElementDisplayed(driver, CustomersPageUI.CUSTOMER_BREADCRUMB);
    }
}
