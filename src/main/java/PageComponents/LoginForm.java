package PageComponents;

import BasePage.BasePage;
import org.openqa.selenium.By;
import utilities.Waits;



public class LoginForm<T> extends BasePage<T> {
    private final T currentPage;

    public final By loginBtn = By.cssSelector("#logInModal .modal-footer button.btn-primary");
    public final By closeBtn = By.cssSelector("#logInModal .modal-footer button.btn-secondary");
    public final By userNameField = By.id("loginusername");
    public final By passwordField = By.id("loginpassword");
    public final By body = By.id("logInModal");

    public LoginForm(T currentPage){
        this.currentPage=currentPage;
        Waits.waitForVisibility(body,1);
    }

    public void enterUserName(String username){
        set(userNameField,username);
    }

    public void enterPassword(String password){
        set(passwordField,password);
    }

    public T clickLogin(){
        click(loginBtn);
        return currentPage;
    }

    public T clickClose(){
        click(closeBtn);
        return currentPage;
    }

}
