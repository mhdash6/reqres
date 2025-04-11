package PageComponents;

import BasePage.BasePage;
import com.demoblaze.CartPage;
import com.demoblaze.HomePage;
import org.openqa.selenium.By;

import static utilities.Gets.getInnerText;

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
        logger.get().info("Initializing NavBar component for the current page.");
    }

    public HomePage clickHome() {
        logger.get().info("Clicking the 'Home' link in the NavBar.");
        click(homeLink);
        logger.get().info("'Home' link clicked successfully. Navigating to HomePage.");
        return new HomePage();
    }

    public HomePage clickLogo() {
        logger.get().info("Clicking the 'Logo' in the NavBar.");
        click(logo);
        logger.get().info("'Logo' clicked successfully. Navigating to HomePage.");
        return new HomePage();
    }

    public ContactForm<T> clickContact() {
        logger.get().info("Clicking the 'Contact' link in the NavBar.");
        click(contactLink);
        logger.get().info("'Contact' link clicked successfully. Opening ContactForm modal.");
        return new ContactForm<>(currentPage);
    }

    public AboutUs<T> clickAboutUs() {
        logger.get().info("Clicking the 'About Us' link in the NavBar.");
        click(aboutUsLink);
        logger.get().info("'About Us' link clicked successfully. Opening AboutUs modal.");
        return new AboutUs<>(currentPage);
    }

    public CartPage clickCart() {
        logger.get().info("Clicking the 'Cart' link in the NavBar.");
        click(cartLink);
        logger.get().info("'Cart' link clicked successfully. Navigating to CartPage.");
        return new CartPage();
    }

    public LoginForm<T> clickLogin() {
        logger.get().info("Clicking the 'Login' link in the NavBar.");
        click(loginLink);
        logger.get().info("'Login' link clicked successfully. Opening LoginForm modal.");
        return new LoginForm<>(currentPage);
    }

    public HomePage clickLogOut() {
        logger.get().info("Clicking the 'Logout' link in the NavBar.");
        click(logoutLink);
        logger.get().info("'Logout' link clicked successfully. Navigating to HomePage.");
        return new HomePage();
    }

    public SignUpForm<T> clickSignUp() {
        logger.get().info("Clicking the 'Sign Up' link in the NavBar.");
        click(signUpLink);
        logger.get().info("'Sign Up' link clicked successfully. Opening SignUpForm modal.");
        return new SignUpForm<>(currentPage);
    }

    public String getGreatingMsg() {
        logger.get().info("Fetching the greeting message from the NavBar.");
        String message = getInnerText(welcomeMsgLink);
        logger.get().info("Greeting message fetched: {}", message);
        return message;
    }
}

