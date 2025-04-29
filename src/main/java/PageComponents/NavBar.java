package PageComponents;

import BasePage.BasePage;
import com.demoblaze.CartPage;
import com.demoblaze.HomePage;
import org.openqa.selenium.By;
import utilities.common.LogsUtil;

import static utilities.selenium.helperClasses.Gets.getInnerText;
import static utilities.selenium.helperClasses.SimpleElementActions.click;

public class NavBar<T> extends BasePage<T> {
    private final T currentPage;

    private final By logo = By.id("nava");
    private final By homeLink = By.cssSelector("li.nav-item.active a.nav-link");
    private final By contactLink = By.cssSelector("a[data-target='#exampleModal']");
    private final By aboutUsLink = By.cssSelector("a[data-target='#videoModal']");
    private final By cartLink = By.linkText("Cart");
    private final By loginLink = By.id("login2");
    private final By logoutLink = By.id("logout2");
    private final By welcomeMsgLink = By.id("nameofuser");
    private final By signUpLink = By.id("signin2");

    public NavBar(T currentPage) {
        this.currentPage = currentPage;
        LogsUtil.info("Initializing NavBar component for the current page.");
    }

    public HomePage clickHome() {
        LogsUtil.info("Clicking the 'Home' link in the NavBar.");
        click(getDriver(), homeLink);
        LogsUtil.info("'Home' link clicked successfully. Navigating to HomePage.");
        return new HomePage();
    }

    public HomePage clickLogo() {
        LogsUtil.info("Clicking the 'Logo' in the NavBar.");
        click(getDriver(), logo);
        LogsUtil.info("'Logo' clicked successfully. Navigating to HomePage.");
        return new HomePage();
    }

    public ContactForm<T> clickContact() {
        LogsUtil.info("Clicking the 'Contact' link in the NavBar.");
        click(getDriver(), contactLink);
        LogsUtil.info("'Contact' link clicked successfully. Opening ContactForm modal.");
        return new ContactForm<>(currentPage);
    }

    public AboutUs<T> clickAboutUs() {
        LogsUtil.info("Clicking the 'About Us' link in the NavBar.");
        click(getDriver(), aboutUsLink);
        LogsUtil.info("'About Us' link clicked successfully. Opening AboutUs modal.");
        return new AboutUs<>(currentPage);
    }

    public CartPage clickCart() {
        LogsUtil.info("Clicking the 'Cart' link in the NavBar.");
        click(getDriver(), cartLink);
        LogsUtil.info("'Cart' link clicked successfully. Navigating to CartPage.");
        return new CartPage();
    }

    public LoginForm<T> clickLogin() {
        LogsUtil.info("Clicking the 'Login' link in the NavBar.");
        click(getDriver(), loginLink);
        LogsUtil.info("'Login' link clicked successfully. Opening LoginForm modal.");
        return new LoginForm<>(currentPage);
    }

    public HomePage clickLogOut() {
        LogsUtil.info("Clicking the 'Logout' link in the NavBar.");
        click(getDriver(), logoutLink);
        LogsUtil.info("'Logout' link clicked successfully. Navigating to HomePage.");
        return new HomePage();
    }

    public SignUpForm<T> clickSignUp() {
        LogsUtil.info("Clicking the 'Sign Up' link in the NavBar.");
        click(getDriver(), signUpLink);
        LogsUtil.info("'Sign Up' link clicked successfully. Opening SignUpForm modal.");
        return new SignUpForm<>(currentPage);
    }

    public String getGreatingMsg() {
        LogsUtil.info("Fetching the greeting message from the NavBar.");
        String message = getInnerText( getDriver(),welcomeMsgLink);
        LogsUtil.info("Greeting message fetched: " + message);
        return message;
    }

    public boolean isLoggedIn() {
        LogsUtil.info("Checking if the user is logged in.");
        boolean loggedIn = !getDriver().findElements(welcomeMsgLink).isEmpty();
        LogsUtil.info("User is logged in: " + loggedIn);
        return loggedIn;
    }
}

