package PageComponents;

import BasePage.BasePage;
import org.openqa.selenium.By;
import utilities.Waits;

public class SignUpForm<T> extends BasePage<T> {
    private final T currentPage;

    private final By loginBtn = By.cssSelector("#signInModal .modal-footer button.btn-primary");
    private final By closeBtn = By.cssSelector("#signInModal .modal-footer button.btn-secondary");
    private final By userNameField = By.id("sign-username");
    private final By passwordField = By.id("sign-password");
    private final By body = By.id("signInModal");

    public SignUpForm(T currentPage) {
        this.currentPage = currentPage;
        logger.get().info("Initializing SignUpForm component for the current page.");
        Waits.waitForElementVisibility(body, 1);
        logger.get().info("SignUpForm modal is visible.");
    }

    public void enterUserName(String username) {
        logger.get().info("Entering username: {}", username);
        set(userNameField, username);
        logger.get().info("Username entered successfully.");
    }

    public void enterPassword(String password) {
        logger.get().info("Entering password.");
        set(passwordField, password);
        logger.get().info("Password entered successfully.");
    }

    public T clickSignUp() {
        logger.get().info("Clicking the 'Sign Up' button.");
        click(loginBtn);
        logger.get().info("'Sign Up' button clicked successfully.");
        return currentPage;
    }

    public T clickClose() {
        logger.get().info("Clicking the 'Close' button on the SignUpForm modal.");
        click(closeBtn);
        logger.get().info("'Close' button clicked successfully.");
        return currentPage;
    }
}
