package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;

public class JsExecutor extends BaseUtility {

    private static final Logger logger = LoggerFactoryUtility.getLogger(JsExecutor.class);

    public static void scrollToElement(WebElement element) {
        logger.info("Scrolling to element: {}", element);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        logger.info("Successfully scrolled to element.");
    }

    public static void clickElementUsingJS(WebElement element) {
        logger.info("Clicking on element using JavaScript: {}", element);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", element);
        logger.info("Successfully clicked on element using JavaScript.");
    }
}
