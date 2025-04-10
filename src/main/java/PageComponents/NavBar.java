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
    }

    public HomePage clickHome() {
        click(homeLink);
        return new HomePage();
    }

    public HomePage clickLogo() {
        click(logo);
        return new HomePage();
    }

    public ContactForm<T> clickContact() {
        click(contactLink);
        return new ContactForm<>(currentPage);
    }

    public AboutUs<T> clickAboutUs() {
        click(aboutUsLink);
        return new AboutUs<>(currentPage);
    }

    public CartPage clickCart() {
        click(cartLink);
        return new CartPage();
    }

    public LoginForm<T> clickLogin() {
        click(loginLink);
        return new LoginForm<>(currentPage);
    }

    public HomePage clickLogOut() {
        click(logoutLink);
        return new HomePage();
    }

    public SignUpForm<T> clickSignUp() {
        click(signUpLink);
        return new SignUpForm<>(currentPage);
    }

    public String getGreatingMsg() {
        return getInnerText(welcomeMsgLink);
    }
}

