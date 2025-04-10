package BasePage;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public abstract class BasePage<T> {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    public static void setDriver(WebDriver newDriver) {
        driver.set(newDriver);
    }

    protected static WebDriver getDriver() {
        return driver.get();
    }

    protected WebElement find(By locator) {
        return getDriver().findElement(locator);
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

}

