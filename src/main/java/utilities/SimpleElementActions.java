package utilities;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SimpleElementActions {

    public static WebElement find(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    public static List<WebElement> findAll(WebDriver driver, By... locators) {
        Set<WebElement> uniqueElements = new LinkedHashSet<>();
        for (By loc : locators) {
            uniqueElements.addAll(driver.findElements(loc));
        }
        return new ArrayList<>(uniqueElements);
    }

    public static void set(By locator, WebDriver driver, String text) {
        WebElement field = find(driver, locator);
        field.clear();
        field.sendKeys(text);
    }

    public static void click(By locator, WebDriver driver) {
        WebElement button = find(driver, locator);
        button.click();
    }
}
