package BasePage;
import org.openqa.selenium.WebDriver;



public abstract class BasePage<T> {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    public static void setDriver(WebDriver newDriver) {
        driver.set(newDriver);
    }

    protected static WebDriver getDriver() {
        return driver.get();
    }


}

