package BasePage;
import utilities.selenium.driver.WebDriverManager;
import org.openqa.selenium.WebDriver;



public abstract class BasePage<T> {

    protected static WebDriver getDriver() {
        return WebDriverManager.getDriver();
    }

}

