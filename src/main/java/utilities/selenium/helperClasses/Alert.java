package utilities.selenium.helperClasses;

import io.qameta.allure.Step;
import utilities.common.LogsUtils;
import utilities.selenium.driver.WebDriverManager;

public class Alert {

    @Step("Click 'OK' on the alert")
    public static void clickOK() {
        LogsUtils.info("Waiting for alert to be present before clicking 'OK'.");
        Waits.waitForAlert();
        LogsUtils.info("Alert is present. Clicking 'OK'.");
        WebDriverManager.getDriver().switchTo().alert().accept();
        LogsUtils.info("'OK' clicked successfully on the alert.");
    }

    @Step("Click 'Cancel' on the alert")
    public static void clickCancel() {
        LogsUtils.info("Waiting for alert to be present before clicking 'Cancel'.");
        Waits.waitForAlert();
        LogsUtils.info("Alert is present. Clicking 'Cancel'.");
        WebDriverManager.getDriver().switchTo().alert().dismiss();
        LogsUtils.info("'Cancel' clicked successfully on the alert.");
    }

    @Step("Fetch the alert message")
    public static String getAlertMessage() {
        LogsUtils.info("Waiting for alert to be present before fetching the message.");
        Waits.waitForAlert();
        String message = WebDriverManager.getDriver().switchTo().alert().getText();
        LogsUtils.info("Alert message fetched: " + message);
        return message;
    }

    @Step("Send keys '{input}' to the alert")
    public static void sendKeys(String input) {
        LogsUtils.info("Waiting for alert to be present before sending keys.");
        Waits.waitForAlert();
        LogsUtils.info("Alert is present. Sending keys: " + input);
        WebDriverManager.getDriver().switchTo().alert().sendKeys(input);
        LogsUtils.info("Keys sent successfully to the alert.");
    }
}
