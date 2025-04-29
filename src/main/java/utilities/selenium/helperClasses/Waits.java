package utilities.selenium.helperClasses;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;
import java.time.Duration;

public class Waits {

    @Step("Wait for element visibility located by: {locator} for {seconds} seconds")
    public static void waitForElementVisibility(WebDriver driver, By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Step("Wait for page to load for {seconds} seconds")
    public static void waitForPageToLoad(WebDriver driver, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
                .equals("complete"));
    }

    @Step("Set implicit wait for {seconds} seconds")
    public static void implicitlyWaitFor(WebDriver driver, int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    @Step("Wait for alert to be present")
    public static void waitForAlert(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    @Step("Wait for element to be clickable located by: {locator} for {seconds} seconds")
    public static void waitForElementToBeClickable(WebDriver driver, By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    @Step("Wait for element to be invisible located by: {locator} for {seconds} seconds")
    public static void waitForElementToBeInvisible(WebDriver driver, By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    @Step("Wait for element to be present located by: {locator} for {seconds} seconds")
    public static void waitForElementToBePresent(WebDriver driver, By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    @Step("Wait for all elements to be present located by: {locator} for {seconds} seconds")
    public static void waitForAllElementsToBePresent(WebDriver driver, By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }
}
