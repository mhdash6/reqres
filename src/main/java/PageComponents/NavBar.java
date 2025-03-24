package PageComponents;

import BasePage.BasePage;
import com.demoblaze.CartPage;
import com.demoblaze.HomePage;
import org.openqa.selenium.By;

public class NavBar extends BasePage {
    By logo = By.id("nava");
    By homeLink = By.cssSelector("li[class='nav-item active'] a[class='nav-link']");
    By contactLink = By.cssSelector("a[data-target='#exampleModal']");
    By aboutUsLink= By.cssSelector("a[data-target='#videoModal']");
    By cartLink = By.cssSelector("a[onclick='showcart()']");
    By loginLink = By.id("#login2");
    By LogoutLink = By.id("logout2");
    By welcomeMsgLink = By.id("nameofuser");
    By signUpLink = By.id("signin2");

    public HomePage clickHome(){
        click(homeLink);
        return new HomePage();
    }
    public HomePage clickLogo(){
        click(logo);
        return new HomePage();
    }
    public Contact clickContact(){
        click(contactLink);
        return new Contact();
    }
    public AboutUs clickAboutUs(){
        click(aboutUsLink);
        return new AboutUs();
    }
    public CartPage clickCart(){
        click(cartLink);
        return new CartPage();
    }

    public Login clickLogin(){
        click(loginLink);
        return new Login();
    }
    public HomePage clickLogOut(){
        click(LogoutLink);
        return new  HomePage();
    }
    public SignUp clickSignUp(){
        click(signUpLink);
        return new SignUp();
    }

    public String getCreatingMsg(){
        return getText(welcomeMsgLink);
    }

}
