package cores;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.PageGenerator;
import pageObjects.admin.AdminLoginPO;
import pageObjects.admin.CustomersPO;
import pageObjects.user.UserLoginPO;
import pageUIs.BasePageUI;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    private void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Selenium Web Browser Function
    protected void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    protected String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    protected String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    protected String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    protected void backToPreviousPage(WebDriver driver) {
        driver.navigate().back();
    }

    protected void forwardToFollowingPage(WebDriver driver) {
        driver.navigate().forward();
    }

    protected void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    protected void acceptAlert(WebDriver driver) {
        waitAlertPresence(driver).accept();
    }

    protected void cancelAlert(WebDriver driver) {
        waitAlertPresence(driver).dismiss();
    }

    protected String getTextAlert(WebDriver driver) {
        return waitAlertPresence(driver).getText();
    }

    protected void sendKeyToAlert(WebDriver driver, String keyToSend) {
        waitAlertPresence(driver).sendKeys(keyToSend);
    }

    protected String getWindowHandle(WebDriver driver) {
        return driver.getWindowHandle();
    }

    protected void switchWindowByID(WebDriver driver, String windowId) {
        Set<String> allWindowHandle = driver.getWindowHandles();
        for (String window : allWindowHandle) {
            if (!window.equals(windowId)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    protected void switchWindowByTitle(WebDriver driver, String expectedTitle) {
        Set<String> allWindowHandle = driver.getWindowHandles();
        for (String window : allWindowHandle) {
            driver.switchTo().window(window);
            if (getPageTitle(driver).equals(expectedTitle)) {
                break;
            }
        }
        sleepInSecond(2);
    }

    protected void switchWindowByContainTitle(WebDriver driver, String expectedTitle) {
        Set<String> allWindowHandle = driver.getWindowHandles();
        for (String window : allWindowHandle) {
            driver.switchTo().window(window);
            if (getPageTitle(driver).contains(expectedTitle)) {
                break;
            }
        }
        sleepInSecond(2);
    }

    protected void closeAllWindowExceptParent(WebDriver driver, String ParentWindowId) {
        Set<String> allWindowHandle = driver.getWindowHandles();
        for (String window : allWindowHandle) {
            driver.switchTo().window(window);
            if (!window.equals(ParentWindowId)) {
                driver.close();
            }
        }
        sleepInSecond(2);
        driver.switchTo().window(ParentWindowId);
    }

    // Selenium Web Element Function
    private By getByXpath(String locator) {
        return By.xpath(locator);
    }

    private By getByLocator(String locator) {
        if(locator == null || locator.trim().isEmpty()) {
            throw new IllegalArgumentException("Locator cannot be null or empty.");
        }

        String[] locatorArr = locator.split("=", 2);
        String locatorType = locatorArr[0].trim();
        String locatorValue = locatorArr[1].trim();

        switch (locatorType.toUpperCase()) {
            case "ID":
                return By.id(locatorValue);
            case "CLASS":
                return By.className(locatorValue);
            case "NAME":
                return By.name(locatorValue);
            case "CSS":
                return By.cssSelector(locatorValue);
            case "XPATH":
                return By.xpath(locatorValue);
            default:
                throw new IllegalArgumentException("Locator type is not supported: " + locatorType);
        }
    }

    private String castParameter(String locator, String... values) {
        return String.format(locator, (Object[]) values);
    }

    private WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(getByXpath(locator));
    }

    private List<WebElement> getListElement(WebDriver driver, String locator) {
        return driver.findElements(getByXpath(locator));
    }

    protected void clickToElement(WebDriver driver, String locator) {
        getElement(driver, locator).click();
    }

    protected void sendKeyToElement(WebDriver driver, String locator, String keyToSend) {
        getElement(driver, locator).sendKeys(keyToSend);
    }

    private Select getDropdown(WebDriver driver, String locator) {
        return new Select(getElement(driver, locator));
    }

    protected void selectItemInDropdown(WebDriver driver, String locator, String expectedItem) {
        getDropdown(driver, locator).selectByVisibleText(expectedItem);
    }

    protected String getSelectedItemInDropdown(WebDriver driver, String locator) {
        return getDropdown(driver, locator).getFirstSelectedOption().getText();
    }

    protected boolean isDropdownMultiple(WebDriver driver, String locator) {
        return getDropdown(driver, locator).isMultiple();
    }

    protected void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String expectedItem) {
        clickToElement(driver, parentLocator);
        sleepInSecond(SHORT_TIMEOUT);

        waitForListElementPresence(driver, childLocator);

        for (WebElement item : getListElement(driver, childLocator)) {
            if (item.getText().trim().equals(expectedItem)) {
                item.click();
                sleepInSecond(SHORT_TIMEOUT);
                break;
            }
        }
    }

    protected String getDOMAttributeValue(WebDriver driver, String locator, String attributeName) {
        return getElement(driver, locator).getDomAttribute(attributeName);
    }

    protected String getDOMPropertyValue(WebDriver driver, String locator, String propertyName) {
        return getElement(driver, locator).getDomProperty(propertyName);
    }

    protected String getTextElement(WebDriver driver, String locator) {
        return getElement(driver, locator).getText();
    }

    protected String getCssValue(WebDriver driver, String locator, String propertyName) {
        return getElement(driver, locator).getCssValue(propertyName);
    }

    protected String getHexaColorFromRGBA(String rgbaColor) {
        return Color.fromString(rgbaColor).asHex().toUpperCase();
    }

    protected int getListElementNumber(WebDriver driver, String locator) {
        return getListElement(driver, locator).size();
    }

    protected boolean isElementDisplayed(WebDriver driver, String locator) {
        return getElement(driver, locator).isDisplayed();
    }

    protected boolean isControlSelected(WebDriver driver, String locator) {
        return getElement(driver, locator).isSelected();
    }

    protected boolean isControlEnabled(WebDriver driver, String locator) {
        return getElement(driver, locator).isEnabled();
    }

    protected void checkTheCheckboxOrRadio(WebDriver driver, String locator) {
        if (!isControlSelected(driver, locator)) {
            clickToElement(driver, locator);
        }
    }

    protected void uncheckTheCheckbox(WebDriver driver, String locator) {
        if (isControlSelected(driver, locator)) {
            clickToElement(driver, locator);
        }
    }

    protected void switchToFrame(WebDriver driver, String locator) {
        driver.switchTo().frame(getElement(driver, locator));
    }

    protected void switchToDefaultContent(WebDriver driver, String locator) {
        driver.switchTo().defaultContent();
    }

    // Action
    protected void doubleClickToElement(WebDriver driver, String locator) {
        new Actions(driver).doubleClick(getElement(driver, locator)).perform();
    }

    protected void hoverMouseToElement(WebDriver driver, String locator) {
        new Actions(driver).moveToElement(getElement(driver, locator)).perform();
    }

    protected void rightClickToElement(WebDriver driver, String locator) {
        new Actions(driver).contextClick(getElement(driver, locator)).perform();
    }

    protected void dragAndDrop(WebDriver driver, String sourceLocator, String targetLocator) {
        new Actions(driver).dragAndDrop(getElement(driver, sourceLocator),
                getElement(driver, targetLocator)).perform();
    }

    protected void sendKeyboardToElement(WebDriver driver, String locator, String key) {
        new Actions(driver).sendKeys(getElement(driver, locator), key).perform();
    }

    // JS Executor
    protected Object executeJavascriptForBrowser(WebDriver driver, String javascript) {
        return ((JavascriptExecutor) driver).executeScript(javascript);
    }

    protected void scrollToBottomPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    protected void clickToElementByJavascript(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click;", getElement(driver, locator));
        sleepInSecond(SHORT_TIMEOUT);
    }

    protected void scrollToElementOnTop(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
    }

    // Wait
    protected void waitForElementVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
    }

    protected void waitForListElementVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
    }

    protected void waitForElementInvisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
    }

    protected void waitForListElementInvisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver, locator)));
    }

    protected void waitForElementPresence(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(getByXpath(locator)));
    }

    protected void waitForListElementPresence(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(locator)));
    }

    protected void waitForElementSelected(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeSelected(getByXpath(locator)));
    }

    protected void waitForElementClickable(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
    }

    private Alert waitAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.alertIsPresent());
    }

    // User Site
    public UserLoginPO clickToFooterMyAccountLink(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.FOOTER_MY_ACCOUNT_LINK);
        clickToElement(driver, BasePageUI.FOOTER_MY_ACCOUNT_LINK);
        return PageGenerator.getPage(UserLoginPO.class, driver);
    }

    public void clickToLogoutAtUserSite(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.MY_ACCOUNT_DROPDOWN_MENU);
        clickToElement(driver, BasePageUI.MY_ACCOUNT_DROPDOWN_MENU);
        waitForElementClickable(driver, BasePageUI.MY_ACCOUNT_DROPDOWN_ITEM);
        clickToElement(driver, BasePageUI.MY_ACCOUNT_DROPDOWN_ITEM);
    }

    public UserLoginPO clickToLoginAtUserSite(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.MY_ACCOUNT_DROPDOWN_MENU);
        clickToElement(driver, BasePageUI.MY_ACCOUNT_DROPDOWN_MENU);
        waitForElementClickable(driver, BasePageUI.LOGIN_DROPDOWN_ITEM);
        clickToElement(driver, BasePageUI.LOGIN_DROPDOWN_ITEM);
        return PageGenerator.getPage(UserLoginPO.class, driver);
    }

    // Admin Site
    public CustomersPO openCustomersPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.CUSTOMERS_DROPDOWN_MENU);
        clickToElement(driver, BasePageUI.CUSTOMERS_DROPDOWN_MENU);
        waitForElementClickable(driver, BasePageUI.CUSTOMERS_DROPDOWN_ITEM);
        clickToElement(driver, BasePageUI.CUSTOMERS_DROPDOWN_ITEM);
        return PageGenerator.getPage(CustomersPO.class, driver);
    }

    public AdminLoginPO clickToLogoutAtAdminSite(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.LOGOUT_BUTTON);
        clickToElement(driver, BasePageUI.LOGOUT_BUTTON);
        return PageGenerator.getPage(AdminLoginPO.class, driver);
    }

    // Common
    public void openAdminSiteUrl(WebDriver driver, String adminUrl) {
        openPageUrl(driver, adminUrl);
    }

    public void openUserSiteUrl(WebDriver driver, String userUrl) {
        openPageUrl(driver, userUrl);
    }

    private static final int LONG_TIMEOUT = 30;
    private static final int SHORT_TIMEOUT = 2;



}
