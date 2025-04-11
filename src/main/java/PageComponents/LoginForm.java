package PageComponents;

import BasePage.BasePage;
import org.openqa.selenium.By;
import utilities.Waits;

public class LoginForm<T> extends BasePage<T> {
    private final T currentPage;

    private final By loginBtn = By.cssSelector("#logInModal .modal-footer button.btn-primary");
    private final By closeBtn = By.cssSelector("#logInModal .modal-footer button.btn-secondary");
    private final By userNameField = By.id("loginusername");
    private final By passwordField = By.id("loginpassword");
    private final By body = By.id("logInModal");

    public LoginForm(T currentPage) {
        this.currentPage = currentPage;
        logger.get().info("Initializing LoginForm component for the current page.");
        Waits.waitForElementVisibility(body, 1);
        logger.get().info("LoginForm modal is visible.");
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

    public T clickLogin() {
        logger.get().info("Clicking the 'Login' button.");
        click(loginBtn);
        logger.get().info("'Login' button clicked successfully.");
        return currentPage;
    }

    public T clickClose() {
        logger.get().info("Clicking the 'Close' button.");
        click(closeBtn);
        logger.get().info("'Close' button clicked successfully.");
        return currentPage;
    }
}
