package PageComponents;

import BasePage.BasePage;
import org.openqa.selenium.By;
import utilities.Alert;
import utilities.LogsUtil;
import utilities.Waits;
import static utilities.SimpleElementActions.click;
import static utilities.SimpleElementActions.set;

public class ContactForm<T> extends BasePage<T> {

    private final T currentPage;
    private final By emailField = By.id("recipient-email");
    private final By nameField = By.id("recipient-name");
    private final By messageField = By.id("message-text");
    private final By sendMessageBtn = By.cssSelector("button[onclick='send()']");
    private final By closeBtn = By.cssSelector("div[id='exampleModal'] button[class='btn.btn-secondary']");
    public final By body = By.id("exampleModal");

    public ContactForm(T currentPage) {
        this.currentPage = currentPage;
        LogsUtil.info("Initializing ContactForm component for the current page.");
        Waits.waitForElementVisibility(getDriver(), body, 1);
        LogsUtil.info("ContactForm modal is visible.");
    }

    public void enterEmail(String email) {
        LogsUtil.info("Entering email: " + email);
        set(emailField, getDriver(),email);
        LogsUtil.info("Email entered successfully.");
    }

    public void enterName(String name) {
        LogsUtil.info("Entering name: " + name);
        set(nameField, getDriver(),name);
        LogsUtil.info("Name entered successfully.");
    }

    public void enterMessage(String message) {
        LogsUtil.info("Entering message: " + message);
        set(messageField, getDriver() ,message);
        LogsUtil.info("Message entered successfully.");
    }

    public void fillContactForm(String email, String name, String message) {
        LogsUtil.info("Filling the contact form with email: " + email + ", name: " + name + ", and message: " + message);
        enterEmail(email);
        enterName(name);
        enterMessage(message);
        LogsUtil.info("Contact form filled successfully.");
    }

    public T clickCloseBtn() {
        LogsUtil.info("Clicking the 'Close' button on the ContactForm modal.");
        click(closeBtn, getDriver());
        LogsUtil.info("'Close' button clicked successfully.");
        return currentPage;
    }

    public T clickSendMessageBtn() {
        LogsUtil.info("Clicking the 'Send Message' button on the ContactForm modal.");
        click(sendMessageBtn, getDriver());
        LogsUtil.info("'Send Message' button clicked successfully.");
        return currentPage;
    }

    public boolean successMsgDisplayed() {
        LogsUtil.info("Checking if the success message is displayed.");
        boolean isDisplayed = Alert.getAlertMessage(getDriver()).equals("Thanks for the message!!");
        LogsUtil.info("Success message displayed: " + isDisplayed);
        return isDisplayed;
    }
}

