package utilities.selenium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import utilities.common.PropertiesUtils;

public class ChromeFactory implements DriverFactory<ChromeOptions> {
    ChromeOptions options;

    @Override
    public ChromeOptions initializeOptions() {
        options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.addArguments(
                "--disable-notifications",
                "--disable-infobars",
                "--start-maximized",
                "--disable-blink-features=AutomationControlled"
        );
        if ("true".equalsIgnoreCase(PropertiesUtils.getProperty("headless"))|| "jenkins".equalsIgnoreCase(PropertiesUtils.getProperty("executionType"))) {
            options.addArguments("--headless");
        }
        return options;
    }

    @Override
    public WebDriver createInstance() {
        options = initializeOptions();
        return new ChromeDriver(options);
    }
}
