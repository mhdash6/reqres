package utilities.selenium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeFactory implements DriverFactory<ChromeOptions> {
    ChromeOptions options;

    @Override
    public ChromeOptions initializeOptions() {
        options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-blink-features=AutomationControlled");
//        options.addArguments("--headless");
        return options;
    }

    @Override
    public WebDriver createInstance() {
        options = initializeOptions();
        return new ChromeDriver(options);
    }
}
