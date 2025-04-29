package utilities.selenium.helperClasses;

import static utilities.selenium.helperClasses.SimpleElementActions.find; // Static import for the 'find' method
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import utilities.common.LogsUtil;

public class JsExecutor {

    public static void scrollToElement(WebDriver driver, By locator) {
        LogsUtil.info("Scrolling to element: " + locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                find(driver, locator)
        );
    }

    @Step("Scrolling down by pixels: {pixels}")
    public static void scrollDownByPixels(WebDriver driver, int pixels) {
        if (pixels < 0) {
            pixels= -pixels;
        }
        LogsUtil.info("Scrolling down by pixels: " + pixels);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "window.scrollBy({top: arguments[0], behavior: 'smooth'});",
                pixels
        );
    }

    @Step("Scrolling up by pixels: {pixels}")
    public static void scrollUpByPixels(WebDriver driver, int pixels) {
        if (pixels < 0) {
            pixels= -pixels;
        }
        LogsUtil.info("Scrolling up by pixels: " + pixels);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "window.scrollBy({top: arguments[0], behavior: 'smooth'});",
                -pixels
        );
    }

    @Step("Scrolling to the top of the page")
    public static void scrollToTop(WebDriver driver) {
        LogsUtil.info("Scrolling to the top of the page");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "window.scrollTo({top: 0, behavior: 'smooth'});"
        );
    }

    @Step("Scrolling to the bottom of the page")
    public static void scrollToBottom(WebDriver driver) {
        LogsUtil.info("Scrolling to the bottom of the page");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "window.scrollTo({top: document.body.scrollHeight, behavior: 'smooth'});"
        );
    }

}
