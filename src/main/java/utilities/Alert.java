package utilities;

import org.openqa.selenium.WebDriver;

public class Alert {

    public static void clickOK(WebDriver driver){
        Waits.waitForAlert(driver);
        driver.switchTo().alert().accept();
    }

    public static void clickCancel(WebDriver driver){
        Waits.waitForAlert(driver);
        driver.switchTo().alert().dismiss();
    }

    public static String getAlertMessage(WebDriver driver){
        Waits.waitForAlert(driver);
        return  driver.switchTo().alert().getText();
    }

    public static void sendKeys(String input,WebDriver driver){
        Waits.waitForAlert(driver);
        driver.switchTo().alert().sendKeys(input);
    }

}
