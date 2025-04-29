package utilities.selenium.helperClasses;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;

public class AjaxWaitUtils {

    @Step("Wait until jQuery AJAX calls complete within {timeoutSeconds} seconds")
    public static void waitUntilAjaxViaJQuery(WebDriver webDriver, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(timeoutSeconds));
        String script =
                "var seleniumCallback = arguments[arguments.length - 1];" +
                        "var originalJqueryAjax = window.jQuery.ajax;" +
                        "window.jQuery.ajax = function() {" +
                        "  var jqXhr = originalJqueryAjax.apply(this, arguments);" +
                        "  jqXhr.always(function() { seleniumCallback(true); });" +
                        "  return jqXhr;" +
                        "};";
        ((JavascriptExecutor) webDriver).executeAsyncScript(script);
    }

    @Step("Wait until fetch API calls complete within {timeoutSeconds} seconds")
    public static void waitUntilAjaxViaFetch(WebDriver webDriver, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(timeoutSeconds));
        String script =
                "var seleniumCallback = arguments[arguments.length - 1];" +
                        "var originalFetch = window.fetch;" +
                        "window.fetch = function() {" +
                        "  var fetchPromise = originalFetch.apply(this, arguments);" +
                        "  fetchPromise.finally(function() { seleniumCallback(true); });" +
                        "  return fetchPromise;" +
                        "};";
        ((JavascriptExecutor) webDriver).executeAsyncScript(script);
    }

    @Step("Wait until native XHR calls complete within {timeoutSeconds} seconds")
    public static void waitUntilAjaxViaXhr(WebDriver webDriver, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(timeoutSeconds));
        String script =
                "var seleniumCallback = arguments[arguments.length - 1];" +
                        "var proto = window.XMLHttpRequest.prototype;" +
                        "var originalOpen = proto.open;" +
                        "proto.open = function() {" +
                        "  this.addEventListener('loadend', function() { seleniumCallback(true); });" +
                        "  originalOpen.apply(this, arguments);" +
                        "};";
        ((JavascriptExecutor) webDriver).executeAsyncScript(script);
    }

    @Step("Wait until all AJAX requests complete within {timeoutSeconds} seconds")
    public static void waitUntilAjaxComplete(WebDriver webDriver, int timeoutSeconds) {
        Boolean isJquery = (Boolean) ((JavascriptExecutor) webDriver)
                .executeScript("return typeof window.jQuery === 'function'");
        Boolean isFetch  = (Boolean) ((JavascriptExecutor) webDriver)
                .executeScript("return typeof window.fetch === 'function'");
        Boolean isXhr    = (Boolean) ((JavascriptExecutor) webDriver)
                .executeScript("return typeof window.XMLHttpRequest !== 'undefined'");

        if (Boolean.TRUE.equals(isJquery)) {
            waitUntilAjaxViaJQuery(webDriver, timeoutSeconds);
        } else if (Boolean.TRUE.equals(isFetch)) {
            waitUntilAjaxViaFetch(webDriver, timeoutSeconds);
        } else if (Boolean.TRUE.equals(isXhr)) {
            waitUntilAjaxViaXhr(webDriver, timeoutSeconds);
        }
    }
}

