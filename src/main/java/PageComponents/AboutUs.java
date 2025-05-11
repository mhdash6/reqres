package PageComponents;


import org.openqa.selenium.By;
import utilities.common.LogsUtils;
import utilities.selenium.helperClasses.AjaxWaitUtils;
import utilities.selenium.helperClasses.Waits;
import utilities.uiElements.Button;
import utilities.uiElements.Container;
import utilities.uiElements.TextContainer;


public class AboutUs<T> {

    private final Class<T> currentPage;
    private final Button closeBtn = new Button(By.cssSelector("#videoModal .modal-footer > button"));
    private final Button exitBtn = new Button(By.cssSelector("#videoModal button.close"));
    private final TextContainer errorMsg = new TextContainer(By.cssSelector("div[aria-label='Modal Window'] div[role='document']"));
    private final Container body = new Container(By.id("videoModal"));

    public AboutUs(Class<T> currentPage) {
        this.currentPage = currentPage;
        if(isDisplayed()){
            LogsUtils.info("AboutUs modal is visible.");
        }else {
            LogsUtils.warn("AboutUs modal is not visible.");
        }
    }

    public boolean isDisplayed() {
        boolean isDisplayed = body.isDisplayed();
        LogsUtils.info("AboutUs modal is displayed: " + isDisplayed);
        return isDisplayed;
    }


    public String getErrorMsg() {
        AjaxWaitUtils.waitForJQuery(3);
        Waits.waitForElementVisibility(errorMsg.getLocator(),3);
        String message = errorMsg.getText();
        LogsUtils.info("Error message fetched: " + message);
        return message;
    }

    public T clickClose() {
        closeBtn.click();
        Waits.waitForElementToBeInvisible(body.getLocator(),3);
        LogsUtils.info("'Close' button clicked successfully.");
        try {
            return currentPage.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            LogsUtils.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }

    public T clickExit() {
        exitBtn.click();
        Waits.waitForElementToBeInvisible(body.getLocator(),3);
        try {
            return currentPage.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            LogsUtils.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }
}