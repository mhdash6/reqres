package utilities;

import org.openqa.selenium.WebDriver;

public abstract class BaseUtility {
    static protected WebDriver driver;
    public static void setDriver(WebDriver newDriver){
        driver=newDriver;
    }
}
