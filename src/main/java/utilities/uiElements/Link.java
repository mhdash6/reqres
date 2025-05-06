package utilities.uiElements;

import org.openqa.selenium.By;
import utilities.common.LogsUtils;
import utilities.selenium.helperClasses.Gets;
import utilities.selenium.helperClasses.JsExecutor;
import utilities.selenium.helperClasses.SimpleElementActions;
import utilities.selenium.helperClasses.Waits;

public class Link extends UiElement {


    public Link(By locator) {
        super(locator);
    }

    public Link(By parentLocator, By relativeLocator) {
        super(parentLocator, relativeLocator);
    }

    public void click(){
        Waits.waitForElementToBeClickable(locator, 3);
        JsExecutor.scrollToElement(locator);
        SimpleElementActions.click(locator);
    }

    public String getHref(){
        try {
            Waits.waitForElementToBePresent( locator, 3);
            return Gets.getAttribute(locator, "href");
        }catch (Exception e){
            LogsUtils.error("Couldn't get href for ", locator.toString()," Error ", e.getMessage() );
            return "";
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
