package pageObjects;

import cores.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.UserHomePageUI;

public class UserHomePO extends BasePage {
    private WebDriver driver;

    public UserHomePO (WebDriver driver) {
        this.driver = driver;
    }

    public UserLoginPO clickToLoginMenu() {
        waitForElementClickable(driver, UserHomePageUI.LOGIN_MENU);
        clickToElement(driver, UserHomePageUI.LOGIN_MENU);
        return PageGeneratorGeneric.getPage(UserLoginPO.class, driver);
    }
}
