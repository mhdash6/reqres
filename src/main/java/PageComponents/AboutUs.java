package PageComponents;

import BasePage.BasePage;
import org.openqa.selenium.By;
import utilities.Waits;
import utilities.LogsUtil;
import static utilities.Gets.getInnerText;
import static utilities.SimpleElementActions.click;

public class AboutUs<T> extends BasePage<T> {

    private final T currentPage;
    private final By closeBtn = By.cssSelector("#videoModal .modal-footer > button");
    private final By exitBtn = By.cssSelector("#videoModal button.close");
    private final By errorMsg = By.cssSelector("div[aria-label='Modal Window'] div[role='document']");
    private final By body = By.id("videoModal");

    public AboutUs(T currentPage) {
        this.currentPage = currentPage;
        LogsUtil.info("Initializing AboutUs component for the current page.");
        Waits.waitForElementVisibility(getDriver(), body, 2);
        LogsUtil.info("AboutUs modal is visible.");
    }

    public String getErrorMsg() {
        LogsUtil.info("Fetching error message from the AboutUs modal.");
        String message = getInnerText(errorMsg, getDriver());
        LogsUtil.info("Error message fetched: " + message);
        return message;
    }

    public T clickClose() {
        LogsUtil.info("Clicking the 'Close' button on the AboutUs modal.");
        Waits.waitForElementToBeClickable(getDriver(), closeBtn, 2);
        click(closeBtn, getDriver());
        LogsUtil.info("'Close' button clicked successfully.");
        return currentPage;
    }

    public void clickExit() {
        LogsUtil.info("Clicking the 'Exit' button on the AboutUs modal.");
        Waits.waitForElementToBeClickable(getDriver(), exitBtn, 2);
        click(exitBtn, getDriver());
        LogsUtil.info("'Exit' button clicked successfully.");
    }
}
