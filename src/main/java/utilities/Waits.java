package utilities;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits extends BaseUtility {

    public static void waitForVisibility(By locator,int seconds){
        Wait<WebDriver> wait = new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(StaleElementReferenceException.class,
                        NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    public static void waitUntilSweetAlertReady( int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.confirm")));
    }



    public static void implicitlyWaitFor(int seconds) {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    public static void waitForAlert() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(1));
        wait.until(ExpectedConditions.alertIsPresent());
    }

}
