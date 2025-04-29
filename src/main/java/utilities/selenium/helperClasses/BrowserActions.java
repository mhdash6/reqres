package utilities.selenium.helperClasses;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import utilities.common.LogsUtil;


public class BrowserActions {

    @Step("Navigating to URL: {url}")
    public static void navigateToURL(WebDriver driver, String url) {
        driver.get(url);
        LogsUtil.info("Navigated to URL: " + url);
    }

    @Step("Getting current URL")
    public static String getCurrentURL(WebDriver driver) {
        LogsUtil.info("Current URL: " + driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    @Step("Getting page title")
    public static String getCurrentPageTitle(WebDriver driver) {
        LogsUtil.info("Page title: " + driver.getTitle());
        return driver.getTitle();
    }

    @Step("Refreshing the page")
    public static void refreshPage(WebDriver driver) {
        LogsUtil.info("Refreshing the page");
        driver.navigate().refresh();
    }

    @Step("Closing the window in focus")
    public static void closeWindow(WebDriver driver) {
        LogsUtil.info("Closing the window in focus");
        driver.close();
    }
}

