package pageObjects.opencart.admin;

import cores.BasePage;
import org.openqa.selenium.WebDriver;

public class DashboardPO extends BasePage {
    private WebDriver driver;

    public DashboardPO(WebDriver driver) {
        this.driver = driver;
    }
}
