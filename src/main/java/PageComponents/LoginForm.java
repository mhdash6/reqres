package PageComponents;

import BasePage.BasePage;
import org.openqa.selenium.By;
import utilities.common.LogsUtil;
import utilities.selenium.helperClasses.Waits;
import static utilities.selenium.helperClasses.SimpleElementActions.click;
import static utilities.selenium.helperClasses.SimpleElementActions.set;

public class LoginForm<T> extends BasePage<T> {
    private final T currentPage;

    private final By loginBtn = By.cssSelector("#logInModal .modal-footer button.btn-primary");
    private final By closeBtn = By.cssSelector("#logInModal .modal-footer button.btn-secondary");
    private final By userNameField = By.id("loginusername");
    private final By passwordField = By.id("loginpassword");
    private final By body = By.id("logInModal");

    public LoginForm(T currentPage) {
        this.currentPage = currentPage;
        LogsUtil.info("Initializing LoginForm component for the current page.");
        Waits.waitForElementVisibility(getDriver(), body, 1);
        LogsUtil.info("LoginForm modal is visible.");
    }

    public void enterUserName(String username) {
        LogsUtil.info("Entering username: " + username);
        set(getDriver(), userNameField, username);
        LogsUtil.info("Username entered successfully.");
    }

    public void enterPassword(String password) {
        LogsUtil.info("Entering password.");
        set(getDriver(), passwordField, password);
        LogsUtil.info("Password entered successfully.");
    }

    public T clickLogin() {
        LogsUtil.info("Clicking the 'Login' button.");
        click(getDriver(), loginBtn);
        LogsUtil.info("'Login' button clicked successfully.");
        return currentPage;
    }

    public T clickClose() {
        LogsUtil.info("Clicking the 'Close' button.");
        click(getDriver(), closeBtn);
        LogsUtil.info("'Close' button clicked successfully.");
        return currentPage;
    }
}
