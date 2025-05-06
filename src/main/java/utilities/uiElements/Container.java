package utilities.uiElements;

import org.openqa.selenium.By;

public class Container extends UiElement {
    public Container(By locator) {
        super(locator);
    }
    public Container(By parentLocator, By relativeLocator) {
        super(parentLocator, relativeLocator);
    }

}
