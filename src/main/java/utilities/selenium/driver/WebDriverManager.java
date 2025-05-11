package utilities.selenium.driver;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import utilities.common.LogsUtils;

public class WebDriverManager {
    private WebDriverManager() {
    }


 private static final ThreadLocal<SelfHealingDriver> driver = new ThreadLocal<>();


    @Step("Initializing the Driver for browser: {browser}")
    public static void initializeDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome" ->driver.set(SelfHealingDriver.create( new ChromeFactory().createInstance()));
            case "firefox"-> driver.set(SelfHealingDriver.create (new  FirefoxFactory().createInstance()));
            case "edge"-> driver.set(SelfHealingDriver.create( new EdgeFactory().createInstance()));
        }
    }

    public static SelfHealingDriver getDriver() {
        return driver.get();
    }

    @Step("Closing the Driver")
    public static void closeDriver() {
        LogsUtils.info("Closing the Driver");
        if (getDriver() != null) {
            getDriver().quit();
        }
    }

}

