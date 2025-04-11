package PageComponents;

import BasePage.BasePage;
import org.openqa.selenium.By;
import utilities.JsExecutor;
import utilities.Waits;
import static utilities.Gets.getInnerText;

public class AboutUs<T> extends BasePage<T> {

    private final T currentPage;
    private final By closeBtn = By.cssSelector("#videoModal .modal-footer > button");
    private final By exitBtn = By.cssSelector("#videoModal button.close");
    private final By errorMsg = By.cssSelector("div[aria-label='Modal Window'] div[role='document']");
    private final By body = By.id("videoModal");

    public AboutUs(T currentPage) {
        this.currentPage = currentPage;
        logger.get().info("Initializing AboutUs component for the current page.");
        Waits.waitForElementVisibility(body, 2);
        logger.get().info("AboutUs modal is visible.");
    }

    public String getErrorMsg() {
        logger.get().info("Fetching error message from the AboutUs modal.");
        String message = getInnerText(errorMsg);
        logger.get().info("Error message fetched: {}", message);
        return message;
    }

    public T clickClose() {
        logger.get().info("Clicking the 'Close' button on the AboutUs modal.");
        Waits.waitForElementToBeClickable(closeBtn,2);
        click(errorMsg);
        Waits.waitForAjaxToComplete();
        click(closeBtn);
        logger.get().info("'Close' button clicked successfully.");
        return currentPage;
    }

    public void clickExit() {
        logger.get().info("Clicking the 'Exit' button on the AboutUs modal.");
        Waits.waitForElementToBeClickable(exitBtn,2);

        click(exitBtn);
        logger.get().info("'Exit' button clicked successfully.");
    }
}
