package BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage {
    protected static WebDriver driver;

    public void setDriver(WebDriver newDriver) {
            driver = newDriver;
    }

   public WebElement find(By locator) {
        return driver.findElement(locator);
    }

    public void set(By locator, String text) {
        WebElement field = find(locator);
        field.clear();
        field.sendKeys(text);
    }

    public void click(By locator) {
        WebElement button = find(locator);
        button.click();
    }

    public String getText(By locator) {
        return find(locator).getText();
    }
}

