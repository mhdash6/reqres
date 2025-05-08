package utilities.selenium.helperClasses;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Step;
import utilities.common.LogsUtils;
import utilities.selenium.driver.WebDriverManager;

public class SimpleElementActions {

    public static WebElement find( By locator) {
        return WebDriverManager.getDriver().findElement(locator);
    }

    public static List<WebElement> findAll( By... locators) {
        Set<WebElement> uniqueElements = new LinkedHashSet<>();
        for (By loc : locators) {
            LogsUtils.info("Finding elements located by: " + loc);
            uniqueElements.addAll(WebDriverManager.getDriver().findElements(loc));
        }
        return new ArrayList<>(uniqueElements);
    }

    @Step("Input String '{text}' into element located by: {locator}")
    public static void set( By locator, String text) {
        LogsUtils.info("Setting text '" + text + "' for element located by: " + locator);
        WebElement field = find(locator);
        field.clear();
        LogsUtils.info("Cleared existing text in the field.");
        field.sendKeys(text);
        LogsUtils.info("Text '" + text + "' entered successfully.");
    }

    @Step("Click on element located by: {locator}")
    public static void click( By locator) {
        LogsUtils.info("Clicking on element located by: " + locator);
        find(locator).click();;
    }
}
