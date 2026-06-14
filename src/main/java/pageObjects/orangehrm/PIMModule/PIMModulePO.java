package pageObjects.orangehrm.PIMModule;

import cores.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.orangehrm.PIMModule.PIMModulePageUI;

public class PIMModulePO extends BasePage {
    private WebDriver driver;

    public PIMModulePO(WebDriver driver) {
        this.driver = driver;
    }

    public AddEmployeePO clickToAddEmployeeButton() {
        waitForElementClickable(driver, PIMModulePageUI.DYNAMIC_TOPBAR_TAB_ITEM_BY_NAME, "Add Employee");
        clickToElement(driver, PIMModulePageUI.DYNAMIC_TOPBAR_TAB_ITEM_BY_NAME, "Add Employee");
        return PageGenerator.getPage(AddEmployeePO.class, driver);
    }
}
