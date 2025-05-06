package utilities.selenium.helperClasses;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;
import utilities.selenium.driver.WebDriverManager;

import java.time.Duration;

public class Waits {

    @Step("Wait for element visibility located by: {locator} for {seconds} seconds")
    public static void waitForElementVisibility( By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Step("Wait for page to load for {seconds} seconds")
    public static void waitForPageToLoad( int seconds) {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), Duration.ofSeconds(seconds));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
                .equals("complete"));
    }

    @Step("Set implicit wait for {seconds} seconds")
    public static void implicitlyWaitFor( int seconds) {
        WebDriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    @Step("Wait for alert to be present")
    public static void waitForAlert() {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    @Step("Wait for element to be clickable located by: {locator} for {seconds} seconds")
    public static void waitForElementToBeClickable( By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    @Step("Wait for element to be invisible located by: {locator} for {seconds} seconds")
    public static void waitForElementToBeInvisible( By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    @Step("Wait for element to be present located by: {locator} for {seconds} seconds")
    public static void waitForElementToBePresent( By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    @Step("Wait for all elements to be present located by: {locator} for {seconds} seconds")
    public static void waitForAllElementsToBePresent( By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }
    @Step("Wait for element to be selected located by: {locator} for {seconds} seconds")
    public static void waitForElementToBeSelected( By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeSelected(locator));
    }

    public static void waitForAllElementsToBeVisible(By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public static void waitForElementToStale(WebElement element , int seconds) {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.stalenessOf(element));
    }


}
