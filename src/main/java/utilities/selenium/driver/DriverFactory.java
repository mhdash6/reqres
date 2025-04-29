package utilities.selenium.driver;

import org.openqa.selenium.WebDriver;

public interface DriverFactory <T> {
        T initializeOptions();
        WebDriver createInstance();
}
