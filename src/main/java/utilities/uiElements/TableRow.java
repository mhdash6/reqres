package utilities.uiElements;

import org.openqa.selenium.By;

import utilities.selenium.helperClasses.Waits;

public class TableRow  extends UiElement{
    public TableRow(org.openqa.selenium.By locator) {
        super(locator);
    }

    public TableRow(By parentLocator, By relativeLocator) {
        super(parentLocator, relativeLocator);
    }

    public TableCell getCell(By locator){
        Waits.waitForElementVisibility(locator, 3);
        return new TableCell(this.locator, locator);
    }

    public boolean isDeleted(){
        try{
            Waits.waitForElementToBeInvisible(locator, 3);
            return true;
        }catch(Exception e){
            return false;
        }
    }

}
