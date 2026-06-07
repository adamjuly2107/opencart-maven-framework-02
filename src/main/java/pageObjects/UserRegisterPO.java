package pageObjects;

import cores.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.UserHomePageUI;
import pageUIs.UserRegisterPageUI;

public class UserRegisterPO extends BasePage {
    private WebDriver driver;

    public UserRegisterPO (WebDriver driver) {
        this.driver = driver;
    }

    public void enterFirstNameTextbox(String userFirstName) {
        waitForElementVisible(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX, userFirstName);
    }

    public void enterLastNameTextbox(String userLastName) {
        waitForElementVisible(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX, userLastName);
    }

    public void enterEmailTextbox(String userEmail) {
        waitForElementVisible(driver, UserRegisterPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.EMAIL_TEXTBOX, userEmail);
    }

    public void enterPasswordTextbox(String userPassword) {
        waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, userPassword);
    }

    public void acceptPolicy() {
        waitForElementClickable(driver, UserRegisterPageUI.AGREE_CHECKBOX);
        checkTheCheckboxOrRadio(driver, UserRegisterPageUI.AGREE_CHECKBOX);
    }

    public UserHomePO clickToContinueButton() {
        waitForElementClickable(driver, UserRegisterPageUI.CONTINUE_BUTTON);
        clickToElement(driver, UserRegisterPageUI.CONTINUE_BUTTON);
        return PageGeneratorGeneric.getPage(UserHomePO.class, driver);
    }

    public UserHomePO clickToLogoutMenu() {
        waitForElementClickable(driver, UserHomePageUI.LOGOUT_MENU);
        clickToElement(driver, UserHomePageUI.LOGOUT_MENU);
        return PageGeneratorGeneric.getPage(UserHomePO.class, driver);
    }
}
