package utilities.selenium.helperClasses;


import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnhandledAlertException;
import utilities.selenium.driver.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.common.DateTime;
import utilities.common.LogsUtils;
import java.io.File;
import java.nio.file.Files;


import static utilities.common.AllureUtils.attachPng;

public class ScreenShotUtils {
    public static String SCREEN_SHOTS_PATH ="test_outputs/screenshots/";

    private ScreenShotUtils() {
    }


    public static File takeScreenShot(String fileName) {
        File targetFile = null;
        LogsUtils.info("Taking screenshot for: " + fileName);
        try {
            File screenshot = ((TakesScreenshot) WebDriverManager.getDriver().getDelegate()).getScreenshotAs(OutputType.FILE);
            targetFile = new File(SCREEN_SHOTS_PATH + fileName + DateTime.getDateTime() + ".png");
            Files.copy(screenshot.toPath(), targetFile.toPath());
            LogsUtils.info("Screenshot taken successfully: " + targetFile.getAbsolutePath());
            return targetFile;
        } catch (UnhandledAlertException e) {
            LogsUtils.warn("Skipping screenshot due to open alert: " + e.getAlertText());
            return null;
        } catch (Exception e) {
            LogsUtils.error("Failed to take screenshot: " + e.getMessage());
            return null;
        }
    }
}
