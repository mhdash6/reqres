package PageComponents;

import BasePage.BasePage;
import org.openqa.selenium.By;
import utilities.Waits;



public class SignUpForm<T> extends BasePage<T> {
    private final T currentPage;

    public final By loginBtn = By.cssSelector("#signInModal .modal-footer button.btn-primary");
    public final By closeBtn = By.cssSelector("#signInModal .modal-footer button.btn-secondary");
    public final By userNameField = By.id("sign-username");
    public final By passwordField = By.id("sign-password");
    public final By body = By.id("signInModal");



    public SignUpForm(T currentPage){
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
