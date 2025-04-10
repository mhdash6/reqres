package utilities;

import org.openqa.selenium.By;

public class Gets extends BaseUtility {

    public static String getInnerText(By locator) {
        return getDriver().findElement(locator).getText();
    }

    public static String getAttribute( By locator , String attributeName){
        return getDriver().findElement(locator).getDomAttribute(attributeName);
    }

}
