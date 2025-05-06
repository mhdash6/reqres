package utilities.selenium.helperClasses;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.common.LogsUtils;
import utilities.selenium.driver.WebDriverManager;

import java.time.Duration;

public  class AjaxWaitUtils {

    private AjaxWaitUtils() {
    }

    @Step("Wait for JQuery requests to complete")
    public static void waitForJQuery( int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds));
        wait.pollingEvery(Duration.ofMillis(200));
        wait.until((ExpectedCondition<Boolean>) wd -> {
            JavascriptExecutor js = (JavascriptExecutor) wd;
            return (Boolean) js.executeScript(
                    "return (window.jQuery ? jQuery.active === 0 : true);"
            );
        });
        LogsUtils.info("JQuery requests completed");
    }

    @Step("Inject tracking for fetch API calls")
    public static void injectFetchTracking() {
        String script =
                "if (typeof window.fetch === 'function' && !window._seleniumFetchTracking) {" +
                        "  window._seleniumFetchTracking = true;" +
                        "  window._fetchInProgress = 0;" +
                        "  const origFetch = window.fetch;" +
                        "  window.fetch = function() {" +
                        "    window._fetchInProgress++;" +
                        "    return origFetch.apply(this, arguments).finally(() => window._fetchInProgress--);" +
                        "  };" +
                        "}";
        ((JavascriptExecutor) WebDriverManager.getDriver()).executeScript(script);
        LogsUtils.info("Fetch API tracking injected successfully.");
    }

    @Step("Inject tracking for XHR calls")
    public static void injectXhrTracking() {
        String script =
                "if (typeof XMLHttpRequest !== 'undefined' && !window._seleniumXhrTracking) {" +
                        "  window._seleniumXhrTracking = true;" +
                        "  window._xhrInProgress = 0;" +
                        "  const origSend = XMLHttpRequest.prototype.send;" +
                        "  XMLHttpRequest.prototype.send = function() {" +
                        "    window._xhrInProgress++;" +
                        "    this.addEventListener('loadend', () => window._xhrInProgress--);" +
                        "    origSend.apply(this, arguments);" +
                        "  };" +
                        "}";
        ((JavascriptExecutor) WebDriverManager.getDriver()).executeScript(script);
        LogsUtils.info("XHR API tracking injected successfully.");
    }

    @Step("Wait for fetch requests to complete")
    public static void waitForFetchCompletion( int timeoutSeconds) {
        new WebDriverWait(WebDriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds))
                .until(driver -> (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return (window._fetchInProgress || 0) === 0"));
        LogsUtils.info("Fetch requests completed");
    }

    @Step("Wait for XHR requests to complete")
    public static void waitForXhrCompletion(int timeoutSeconds) {
        new WebDriverWait(WebDriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds))
                .until(driver -> (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return (window._xhrInProgress || 0) === 0"));
        LogsUtils.info("XHR requests completed");
    }


}


