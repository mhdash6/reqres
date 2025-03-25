package BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage {
    protected static WebDriver driver;

    public static void setDriver(WebDriver newDriver) {
            driver = newDriver;
    }

    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    protected void set(By locator, String text) {
        WebElement field = find(locator);
        field.clear();
        field.sendKeys(text);
    }

    protected void click(By locator) {
        WebElement button = find(locator);
        button.click();
    }

    protected String getInnerText(By locator) {
        return find(locator).getText();
    }
}

