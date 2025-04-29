package utilities.selenium.helperClasses;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Step;
import utilities.common.LogsUtil;

public class SimpleElementActions {

    public static WebElement find(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    public static List<WebElement> findAll(WebDriver driver, By... locators) {
        LogsUtil.info("Finding all elements for the provided locators.");
        Set<WebElement> uniqueElements = new LinkedHashSet<>();
        for (By loc : locators) {
            LogsUtil.info("Finding elements located by: " + loc);
            uniqueElements.addAll(driver.findElements(loc));
        }
        LogsUtil.info("Total unique elements found: " + uniqueElements.size());
        return new ArrayList<>(uniqueElements);
    }

    @Step("Input String '{text}' into element located by: {locator}")
    public static void set(WebDriver driver, By locator, String text) {
        LogsUtil.info("Setting text '" + text + "' for element located by: " + locator);
        WebElement field = find(driver, locator);
        field.clear();
        LogsUtil.info("Cleared existing text in the field.");
        field.sendKeys(text);
        LogsUtil.info("Text '" + text + "' entered successfully.");
    }

    @Step("Click on element located by: {locator}")
    public static void click(WebDriver driver, By locator) {
        LogsUtil.info("Clicking on element located by: " + locator);
        WebElement button = find(driver, locator);
        button.click();
        LogsUtil.info("Element clicked successfully.");
    }
}
