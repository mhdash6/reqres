package utilities.uiElements;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import utilities.common.LogsUtils;
import utilities.selenium.helperClasses.Gets;
import utilities.selenium.helperClasses.JsExecutor;
import utilities.selenium.helperClasses.SimpleElementActions;
import utilities.selenium.helperClasses.Waits;

public class Button extends UiElement {
    public Button(By locator) {
        super(locator);
    }

    public Button(By parentLocator, By relativeLocator) {
        super(parentLocator, relativeLocator);
    }

    public void click(){
        if (isEnabled()){
            JsExecutor.scrollToElement(locator);
            SimpleElementActions.click(locator);
        }else {
            LogsUtils.info("Button is not enabled. Skipping click action.");
        }

    }

    public boolean isEnabled(){
        try {
            Waits.waitForElementToBeClickable(locator, 3);
            return true;
        }catch (TimeoutException e){
            LogsUtils.info("Button is not enabled.");
            return false;
        }
    }
    public String getText(){
        if(isDisplayed()) {
            return Gets.getInnerText(locator);
        }else {
            return "";
        }
    }

}

