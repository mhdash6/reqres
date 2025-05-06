package PageComponents;


import com.demoblaze.CartPage;
import com.demoblaze.HomePage;
import org.openqa.selenium.By;
import utilities.common.LogsUtils;
import utilities.selenium.helperClasses.AjaxWaitUtils;
import utilities.selenium.helperClasses.Waits;
import utilities.uiElements.Link;
import utilities.uiElements.TextContainer;



public class NavBar<T> {

    private final Class<T> currentPage;

    private final Link logo = new Link(By.id("nava"));
    private final Link homeLink = new Link(By.cssSelector("li.nav-item.active a.nav-link"));
    private final Link contactLink = new Link(By.cssSelector("a[data-target='#exampleModal']"));
    private final Link aboutUsLink = new Link(By.cssSelector("a[data-target='#videoModal']"));
    private final Link cartLink = new Link(By.linkText("Cart"));
    private final Link loginLink = new Link(By.id("login2"));
    private final Link logoutLink = new Link(By.id("logout2"));
    private final Link signUpLink = new Link(By.id("signin2"));
    private final TextContainer welcomeMsgLink = new TextContainer(By.id("nameofuser"));

    public NavBar(Class<T> currentPage) {
        this.currentPage = currentPage;
    }

    public HomePage clickHome() {
        homeLink.click();
        LogsUtils.info("'Home' link clicked successfully. Navigating to HomePage.");
        return new HomePage();
    }

    public HomePage clickLogo() {
        logo.click();
        LogsUtils.info("'Logo' link clicked successfully. Navigating to HomePage.");
        return new HomePage();
    }

    public ContactForm<T> clickContact() {
        contactLink.click();
        LogsUtils.info("'Contact Us' link clicked successfully. Opening ContactForm modal.");
        return new ContactForm<>(currentPage);
    }

    public AboutUs<T> clickAboutUs() {
        aboutUsLink.click();
        LogsUtils.info("'About Us' link clicked successfully. Opening AboutUs modal.");
        return new AboutUs<>(currentPage);
    }

    public CartPage clickCart() {
        cartLink.click();
        LogsUtils.info("'Cart' link clicked successfully. Navigating to CartPage.");
        return new CartPage();
    }

    public LoginForm<T> clickLogin() {
        loginLink.click();
        LogsUtils.info("'Login' link clicked successfully. Opening LoginForm modal.");
        return new LoginForm<>(currentPage);
    }

    public HomePage clickLogOut() {
        logoutLink.click();
        LogsUtils.info("'Logout' link clicked successfully. Navigating to HomePage.");
        return new HomePage();
    }

    public SignUpForm<T> clickSignUp() {
        signUpLink.click();
        LogsUtils.info("'Sign Up' link clicked successfully. Opening SignUpForm modal.");
        return new SignUpForm<>(currentPage);
    }

    public String getGreetingMsg() {
        String message = welcomeMsgLink.getText();
        LogsUtils.info("Greeting message fetched: " + message);
        return message;
    }

    public boolean isLoggedIn() {
        LogsUtils.info("Checking if the user is logged in.");
        AjaxWaitUtils.waitForJQuery(2);
        boolean loggedIn = !welcomeMsgLink.isEmpty();
        LogsUtils.info("User is logged in: " + loggedIn);
        return loggedIn;
    }
}


