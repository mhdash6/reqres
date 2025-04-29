package utilities.selenium.helperClasses;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import utilities.common.LogsUtil;

public class Alert {

    @Step("Click 'OK' on the alert")
    public static void clickOK(WebDriver driver) {
        LogsUtil.info("Waiting for alert to be present before clicking 'OK'.");
        Waits.waitForAlert(driver);
        LogsUtil.info("Alert is present. Clicking 'OK'.");
        driver.switchTo().alert().accept();
        LogsUtil.info("'OK' clicked successfully on the alert.");
    }

    @Step("Click 'Cancel' on the alert")
    public static void clickCancel(WebDriver driver) {
        LogsUtil.info("Waiting for alert to be present before clicking 'Cancel'.");
        Waits.waitForAlert(driver);
        LogsUtil.info("Alert is present. Clicking 'Cancel'.");
        driver.switchTo().alert().dismiss();
        LogsUtil.info("'Cancel' clicked successfully on the alert.");
    }

    @Step("Fetch the alert message")
    public static String getAlertMessage(WebDriver driver) {
        LogsUtil.info("Waiting for alert to be present before fetching the message.");
        Waits.waitForAlert(driver);
        String message = driver.switchTo().alert().getText();
        LogsUtil.info("Alert message fetched: " + message);
        return message;
    }

    @Step("Send keys '{input}' to the alert")
    public static void sendKeys(String input, WebDriver driver) {
        LogsUtil.info("Waiting for alert to be present before sending keys.");
        Waits.waitForAlert(driver);
        LogsUtil.info("Alert is present. Sending keys: " + input);
        driver.switchTo().alert().sendKeys(input);
        LogsUtil.info("Keys sent successfully to the alert.");
    }
}
