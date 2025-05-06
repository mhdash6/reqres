package utilities.uiElements;

import org.openqa.selenium.By;
import utilities.selenium.helperClasses.Gets;
import utilities.selenium.helperClasses.SimpleElementActions;
import utilities.selenium.helperClasses.Waits;


public class TableCell extends UiElement {

    public TableCell(By rowLocator, By cellLocator) {
        super(rowLocator, cellLocator);
    }

    public TableCell(By locator) {
        super(locator);
    }
    public String getText(){
        if(isDisplayed()){
            return Gets.getInnerText(locator);
        }else {
            return "";
        }
    }
    public void click(){
        Waits.waitForElementVisibility(locator,3);
        SimpleElementActions.click(locator);
    }
    public String getText(By locator){
        if(isDisplayed()){
            return SimpleElementActions.find(this.locator).findElement(locator).getText();
        }
        else{
            return "";
        }
    }
}
