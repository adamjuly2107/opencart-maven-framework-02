package pageObjects;

import cores.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Constructor;

public class PageGeneratorGeneric {
    public static <T extends BasePage> T getPage(Class<T> pageClass, WebDriver driver) {
        try {
            Constructor<T> constructor = pageClass.getConstructor(WebDriver.class);
            return constructor.newInstance(driver);
        } catch (Exception e) {
            throw new RuntimeException("Cannot init page object: " + pageClass.getSimpleName(), e);
        }
    }
}
