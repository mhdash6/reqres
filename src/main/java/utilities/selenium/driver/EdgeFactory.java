package utilities.selenium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeFactory implements DriverFactory<EdgeOptions> {
    EdgeOptions options;

    @Override
    public EdgeOptions initializeOptions() {
        options = new EdgeOptions();
        options.setAcceptInsecureCerts(true);
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--window-size=1920,1080");
//        options.addArguments("--headless");
        return options;
    }


    @Override
    public WebDriver createInstance() {
        options = initializeOptions();
        return new EdgeDriver(options);
    }

}
