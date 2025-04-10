package utilities;


public class Alert extends BaseUtility {

    public static void clickOK(){
        Waits.waitForAlert();
        getDriver().switchTo().alert().accept();
    }

    public static void clickCancel(){
        Waits.waitForAlert();
        getDriver().switchTo().alert().dismiss();
    }

    public static String getAlertMessage(){
        Waits.waitForAlert();
        return getDriver().switchTo().alert().getText();
    }

    public static void sendKeys(String input){
        Waits.waitForAlert();
        getDriver().switchTo().alert().sendKeys(input);
    }

}
