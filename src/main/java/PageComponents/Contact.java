package PageComponents;

import BasePage.BasePage;
import org.openqa.selenium.By;

public class Contact<T> extends BasePage {
    private final T currentPage;
    private final By emailField = By.id("recipient-email");
    private final By nameField = By.id("recipient-name");
    private final By messageField = By.id("message-text");
    private final By sendMessageBtn = By.cssSelector("button[onclick='send()']");
    private final By closeBtn = By.cssSelector("div[id='exampleModal'] button[class='btn btn-secondary']");
    public final  By body = By.cssSelector("div[id='exampleModal'] div[class='modal-content']");
    public Contact(T currentPage) {
        this.currentPage = currentPage;
    }

    public void enterEmail(String email) {
        set(emailField, email);
    }

    public void enterName(String name) {
        set(nameField, name);
    }

    public void enterMessage(String message) {
        set(messageField, message);
    }

    public void fillContactForm(String email, String name, String message) {
        enterEmail(email);
        enterName(name);
        enterMessage(message);
    }

    public T clickCloseBtn() {
        click(closeBtn);
        return currentPage;
    }

    public T clickSendMessageBtn() {
        click(sendMessageBtn);
        return currentPage;
    }
}

