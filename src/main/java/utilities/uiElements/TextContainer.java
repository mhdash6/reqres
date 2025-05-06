package utilities.uiElements;

import org.openqa.selenium.By;
import utilities.selenium.helperClasses.Gets;

public class TextContainer extends UiElement {
    public TextContainer(By locator) {
        super(locator);
    }

    public TextContainer(By parentLocator, By relativeLocator) {
        super(parentLocator, relativeLocator);
    }
    public String getText(){
        if(isDisplayed()) {
            return Gets.getInnerText(locator);
        }else {
            return "";
        }
    }
    public boolean isEmpty(){
        return  getText().isEmpty();
    }

}
