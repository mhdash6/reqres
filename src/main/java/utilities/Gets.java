package utilities;

import static utilities.SimpleElementActions.find; 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Gets {

    public static String getInnerText(By locator, WebDriver driver) {
        return find(driver, locator).getText(); 
    }

    public static String getAttribute(By locator, String attributeName, WebDriver driver) {
        return find(driver, locator).getDomAttribute(attributeName); 
    }
}
