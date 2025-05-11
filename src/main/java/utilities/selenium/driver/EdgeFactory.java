package utilities.selenium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import utilities.common.PropertiesUtils;

public class EdgeFactory implements DriverFactory<EdgeOptions> {
    EdgeOptions options;

    @Override
    public EdgeOptions initializeOptions() {
        options = new EdgeOptions();
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
        return new EdgeDriver(options);
    }

}
