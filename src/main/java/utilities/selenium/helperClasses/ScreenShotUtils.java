package utilities.selenium.helperClasses;


import io.qameta.allure.Step;
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

    @Step("Taking screenshot with name: {fileName}")
    public static void takeScreenShot(String fileName) {

        File screenshot = ((TakesScreenshot) WebDriverManager.getDriver())
                .getScreenshotAs(OutputType.FILE);
        File targetFile = new File(SCREEN_SHOTS_PATH +fileName+ DateTime.getDateTime() + ".png");
        try {
            Files.copy(screenshot.toPath(), targetFile.toPath());
            LogsUtils.info("Screenshot taken successfully: " + targetFile.getAbsolutePath());
            attachPng(targetFile);
        } catch (Exception e) {
            LogsUtils.error(
                    "Failed to take screenshot: " + targetFile.getAbsolutePath()
                            + ". Error: " + e.getMessage());
        }
    }
}
