package utilities.uiElements;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriverException;
import utilities.common.LogsUtils;
import utilities.selenium.helperClasses.Gets;
import utilities.selenium.helperClasses.JsExecutor;
import utilities.selenium.helperClasses.SimpleElementActions;
import utilities.selenium.helperClasses.Waits;

import static utilities.selenium.helperClasses.SimpleElementActions.find;

public class TextInputField extends UiElement {

    public TextInputField(By locator) {
        super(locator);
    }

    public TextInputField(By parentLocator, By relativeLocator) {
        super(parentLocator, relativeLocator);
    }

    public String getText(){
        if(isDisplayed()){
            return Gets.getAttribute(locator, "value");
        }else {
            LogsUtils.error("Couldn't get text for ", locator.toString());
            return "";
        }
    }

    public void write(String text) {
        try {
            if (text == null || text.isEmpty() || text.equals("null")) {
                text = "";
            }
            Waits.waitForElementToBeClickable(locator, 3);
            SimpleElementActions.set(locator, text);
        } catch (WebDriverException ex) {
            try {
                JsExecutor.writeUsingJs(text, locator);
                LogsUtils.warn("Selenium SendKeys Failed, JsExecutor used");
            } catch (Exception e) {
                LogsUtils.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }


}
