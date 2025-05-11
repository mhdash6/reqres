package utilities.uiElements;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import utilities.common.LogsUtils;
import utilities.selenium.helperClasses.Waits;

import static utilities.selenium.helperClasses.SimpleElementActions.find;
import static utilities.selenium.helperClasses.SimpleElementActions.findAll;

abstract class UiElement {

    protected final By locator;

    UiElement(By locator) {
        this.locator = locator;
    }

    public UiElement(By parentLocator, By relativeLocator) {
        this.locator= new ByChained(parentLocator, relativeLocator);
    }

    public boolean isDisplayed() {
        try{
            LogsUtils.info("Checking if element is displayed: " + locator.toString());
            Waits.waitForElementVisibility(locator, 5);
            return !findAll( locator ).isEmpty() && find(locator).isDisplayed();
        }catch(Exception e){
            LogsUtils.info("Element is not displayed." );
            return false;
        }
    }

    public By getLocator() {
        return locator;
    }

}
