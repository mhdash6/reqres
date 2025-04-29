package utilities.selenium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxFactory implements DriverFactory<FirefoxOptions> {
    FirefoxOptions options;

    @Override
    public FirefoxOptions initializeOptions() {
        options = new FirefoxOptions();
        options.setAcceptInsecureCerts(true);
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--window-size=1920,1080");
        return options;
    }

    @Override
    public WebDriver createInstance() {
        options = initializeOptions();
        return new FirefoxDriver(options);
    }
}
