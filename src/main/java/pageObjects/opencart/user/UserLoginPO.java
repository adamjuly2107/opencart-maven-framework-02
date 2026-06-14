package pageObjects.opencart.user;

import cores.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.opencart.user.UserLoginPageUI;

public class UserLoginPO extends BasePage {
    WebDriver driver;

    public UserLoginPO(WebDriver driver) {
        this.driver = driver;
    }

    public UserRegisterPO clickToContinueButton() {
        waitForElementClickable(driver, UserLoginPageUI.CONTINUE_BUTTON);
        clickToElement(driver, UserLoginPageUI.CONTINUE_BUTTON);
        return PageGenerator.getPage(UserRegisterPO.class, driver);
    }

    public void enterEmailTextbox(String userEmail) {
        waitForElementVisible(driver, UserLoginPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, userEmail);
    }

    public void enterPasswordTextbox(String userPassword) {
        waitForElementVisible(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, userPassword);
    }

    public UserHomePO clickToLoginButton() {
        waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getPage(UserHomePO.class, driver);
    }
}
