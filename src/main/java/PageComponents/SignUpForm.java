package PageComponents;

import BasePage.BasePage;
import org.openqa.selenium.By;
import utilities.LogsUtil;
import utilities.Waits;

import static utilities.SimpleElementActions.click;
import static utilities.SimpleElementActions.set;

public class SignUpForm<T> extends BasePage<T> {
    private final T currentPage;

    private final By signUpBtn = By.cssSelector("#signInModal .modal-footer button.btn-primary");
    private final By closeBtn = By.cssSelector("#signInModal .modal-footer button.btn-secondary");
    private final By userNameField = By.id("sign-username");
    private final By passwordField = By.id("sign-password");
    private final By body = By.id("signInModal");

    public SignUpForm(T currentPage) {
        this.currentPage = currentPage;
        LogsUtil.info("Initializing SignUpForm component for the current page.");
        Waits.waitForElementVisibility(getDriver(), body, 1);
        LogsUtil.info("SignUpForm modal is visible.");
    }

    public void enterUserName(String username) {
        LogsUtil.info("Entering username: " + username);
        set(userNameField, getDriver(), username);
        LogsUtil.info("Username entered successfully.");
    }

    public void enterPassword(String password) {
        LogsUtil.info("Entering password.");
        set(passwordField, getDriver(), password);
        LogsUtil.info("Password entered successfully.");
    }

    public T clickSignUp() {
        LogsUtil.info("Clicking the 'Sign Up' button.");
        click(signUpBtn, getDriver());
        LogsUtil.info("'Sign Up' button clicked successfully.");
        return currentPage;
    }

    public T clickClose() {
        LogsUtil.info("Clicking the 'Close' button on the SignUpForm modal.");
        click(closeBtn, getDriver());
        LogsUtil.info("'Close' button clicked successfully.");
        return currentPage;
    }
}
