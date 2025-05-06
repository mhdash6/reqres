package utilities.selenium.helperClasses;

import static utilities.selenium.helperClasses.SimpleElementActions.find; // Static import for the 'find' method
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import io.qameta.allure.Step;
import utilities.common.LogsUtils;
import utilities.selenium.driver.WebDriverManager;

public class JsExecutor {

    private JsExecutor() {
    }
    @Step("Scrolling to element: {locator}")
    public static void scrollToElement( By locator) {
        LogsUtils.info("Scrolling to element: " + locator);
        JavascriptExecutor js = (JavascriptExecutor) WebDriverManager.getDriver();
        js.executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                find( locator)
        );
    }

    @Step("Scrolling down by pixels: {pixels}")
    public static void scrollDownByPixels( int pixels) {
        if (pixels < 0) {
            pixels= -pixels;
        }
        LogsUtils.info("Scrolling down by pixels: " + pixels);
        JavascriptExecutor js = (JavascriptExecutor) WebDriverManager.getDriver();
        js.executeScript(
                "window.scrollBy({top: arguments[0], behavior: 'smooth'});",
                pixels
        );
    }

    @Step("Scrolling up by pixels: {pixels}")
    public static void scrollUpByPixels( int pixels) {
        if (pixels < 0) {
            pixels= -pixels;
        }
        LogsUtils.info("Scrolling up by pixels: " + pixels);
        JavascriptExecutor js = (JavascriptExecutor) WebDriverManager.getDriver();
        js.executeScript(
                "window.scrollBy({top: arguments[0], behavior: 'smooth'});",
                -pixels
        );
    }

    @Step("Scrolling to the top of the page")
    public static void scrollToTop() {
        LogsUtils.info("Scrolling to the top of the page");
        JavascriptExecutor js = (JavascriptExecutor) WebDriverManager.getDriver();
        js.executeScript(
                "window.scrollTo({top: 0, behavior: 'smooth'});"
        );
    }

    @Step("Scrolling to the bottom of the page")
    public static void scrollToBottom() {
        LogsUtils.info("Scrolling to the bottom of the page");
        JavascriptExecutor js = (JavascriptExecutor) WebDriverManager.getDriver();
        js.executeScript(
                "window.scrollTo({top: document.body.scrollHeight, behavior: 'smooth'});"
        );
    }

    @Step
    public static void writeUsingJs(String text,By locator){
        JavascriptExecutor js = (JavascriptExecutor) WebDriverManager.getDriver();
        js.executeScript("arguments[0].value = arguments[1];",find(locator), text);
    }
}
