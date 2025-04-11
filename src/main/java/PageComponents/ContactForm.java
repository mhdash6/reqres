package PageComponents;

import BasePage.BasePage;
import org.openqa.selenium.By;
import utilities.Alert;
import utilities.Waits;

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
        logger.get().info("Initializing ContactForm component for the current page.");
        Waits.waitForElementVisibility(body, 1);
        logger.get().info("ContactForm modal is visible.");
    }

    public void enterEmail(String email) {
        logger.get().info("Entering email: {}", email);
        set(emailField, email);
        logger.get().info("Email entered successfully.");
    }

    public void enterName(String name) {
        logger.get().info("Entering name: {}", name);
        set(nameField, name);
        logger.get().info("Name entered successfully.");
    }

    public void enterMessage(String message) {
        logger.get().info("Entering message: {}", message);
        set(messageField, message);
        logger.get().info("Message entered successfully.");
    }

    public void fillContactForm(String email, String name, String message) {
        logger.get().info("Filling the contact form with email: {}, name: {}, and message: {}", email, name, message);
        enterEmail(email);
        enterName(name);
        enterMessage(message);
        logger.get().info("Contact form filled successfully.");
    }

    public T clickCloseBtn() {
        logger.get().info("Clicking the 'Close' button on the ContactForm modal.");
        click(closeBtn);
        logger.get().info("'Close' button clicked successfully.");
        return currentPage;
    }

    public T clickSendMessageBtn() {
        logger.get().info("Clicking the 'Send Message' button on the ContactForm modal.");
        click(sendMessageBtn);
        logger.get().info("'Send Message' button clicked successfully.");
        return currentPage;
    }

    public boolean successMsgDisplayed(){
        return Alert.getAlertMessage().equals("Thanks for the message!!");
    }

}

