package utilities.selenium.driver;

import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import utilities.common.LogsUtil;

public class WebDriverManager {
    private WebDriverManager() {
    }
 private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    @Step("Initializing the Driver for browser: {browser}")
    public static void initializeDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome" ->driver.set(new ChromeFactory().createInstance());
            case "firefox"-> driver.set(new FirefoxFactory().createInstance());
            case "edge"-> driver.set(new EdgeFactory().createInstance());
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    @Step("Closing the Driver")
    public static void closeDriver() {

        LogsUtil.info("Closing the Driver");
        if (getDriver() != null) {
            getDriver().quit();
        }
    }

}

