package utilities.selenium.helperClasses;

import static utilities.selenium.helperClasses.SimpleElementActions.find;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import utilities.common.LogsUtil;

public class Gets {

    @Step("Fetch inner text form element located by: {locator}")
    public static String getInnerText(WebDriver driver, By locator) {
        LogsUtil.info("Fetching inner text for element located by: " + locator);
        String text = find(driver, locator).getText();
        LogsUtil.info("Inner text fetched: " + text);
        return text;
    }

    @Step("Fetch attribute '{attributeName}' form element located by: {locator}")
    public static String getAttribute(WebDriver driver, By locator, String attributeName) {
        LogsUtil.info("Fetching attribute '" + attributeName + "' for element located by: " + locator);
        String attributeValue = find(driver, locator).getDomAttribute(attributeName);
        LogsUtil.info("Attribute value fetched: " + attributeValue);
        return attributeValue;
    }
}
