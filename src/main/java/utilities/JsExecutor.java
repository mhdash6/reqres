package utilities;

import static utilities.SimpleElementActions.find; // Static import for the 'find' method
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JsExecutor {

    public static void scrollToElement(By locator, WebDriver driver) {
        LogsUtil.info("Scrolling to element:", locator.toString());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", find(driver, locator));
        LogsUtil.info("Successfully scrolled to element.");
    }

    public static void clickElementUsingJS(By locator, WebDriver driver) {
        LogsUtil.info("Clicking element using JS:", locator.toString());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", find(driver, locator));
        LogsUtil.info("Successfully clicked element using JS.");
    }
}
