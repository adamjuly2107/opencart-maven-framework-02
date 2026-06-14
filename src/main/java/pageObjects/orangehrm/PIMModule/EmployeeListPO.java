package pageObjects.orangehrm.PIMModule;

import cores.BasePage;
import org.openqa.selenium.WebDriver;

public class EmployeeListPO extends PIMModulePO {
    private WebDriver driver;

    public EmployeeListPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
