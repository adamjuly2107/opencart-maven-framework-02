package pageObjects.user;

import cores.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.user.UserHomePageUI;

public class UserHomePO extends BasePage {
    private WebDriver driver;

    public UserHomePO (WebDriver driver) {
        this.driver = driver;
    }

    public boolean isMyAccountPageDisplayed() {
        waitForElementVisible(driver, UserHomePageUI.MY_ACCOUNT_BREADCRUMB);
        return isElementDisplayed(driver, UserHomePageUI.MY_ACCOUNT_BREADCRUMB);
    }
}
