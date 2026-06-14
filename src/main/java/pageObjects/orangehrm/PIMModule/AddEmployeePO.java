package pageObjects.orangehrm.PIMModule;

import org.openqa.selenium.WebDriver;

public class AddEmployeePO extends PIMModulePO{
    private WebDriver driver;

    public AddEmployeePO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
