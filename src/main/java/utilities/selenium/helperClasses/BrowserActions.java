package utilities.selenium.helperClasses;

import io.qameta.allure.Step;
import utilities.common.LogsUtils;
import utilities.selenium.driver.WebDriverManager;


public class BrowserActions {

    @Step("Navigating to URL: {url}")
    public static void navigateToURL(String url) {
        WebDriverManager.getDriver().get(url);
        LogsUtils.info("Navigated to URL: " + url);
    }

    @Step("Getting current URL")
    public static String getCurrentURL() {
        LogsUtils.info("Current URL: " + WebDriverManager.getDriver().getCurrentUrl());
        return WebDriverManager.getDriver().getCurrentUrl();
    }

    @Step("Getting page title")
    public static String getCurrentPageTitle() {
        LogsUtils.info("Page title: " + WebDriverManager.getDriver().getTitle());
        return WebDriverManager.getDriver().getTitle();
    }

    @Step("Refreshing the page")
    public static void refreshPage() {
        LogsUtils.info("Refreshing the page");
        WebDriverManager.getDriver().navigate().refresh();
    }

    @Step("Closing the window in focus")
    public static void closeWindow() {
        LogsUtils.info("Closing the window in focus");
        WebDriverManager.getDriver().close();
    }
}

