package utilities;

import org.openqa.selenium.WebDriver;

public abstract class BaseUtility {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setDriver(WebDriver newDriver){
        driver.set(newDriver);
    }

    protected static WebDriver getDriver() {
        return driver.get();
    }
}
