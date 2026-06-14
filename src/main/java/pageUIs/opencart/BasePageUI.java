package pageUIs.opencart;

public class BasePageUI {
    // User Site
    public static final String FOOTER_MY_ACCOUNT_LINK = "//footer//a[text()='My Account']";
    public static final String MY_ACCOUNT_DROPDOWN_MENU = "//span[text()='My Account']/parent::a";
    public static final String MY_ACCOUNT_DROPDOWN_ITEM = "//span[text()='My Account']/parent::a/following-sibling::ul//a[text()='Logout']/parent::li";
    public static final String LOGIN_DROPDOWN_ITEM = "//span[text()='My Account']/parent::a/following-sibling::ul//a[text()='Login']/parent::li";

    // Admin Site
    public static final String CUSTOMERS_DROPDOWN_MENU = "//li[@id='menu-customer']";
    public static final String CUSTOMERS_DROPDOWN_ITEM = "//li[@id='menu-customer']/ul//a[text()='Customers']/parent::li";
    public static final String LOGOUT_BUTTON = "//li[@id='nav-logout']";
}
