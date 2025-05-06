package PageComponents;


import org.openqa.selenium.By;
import utilities.common.LogsUtils;
import utilities.uiElements.Button;
import utilities.uiElements.Container;
import utilities.uiElements.TextInputField;



public class SignUpForm<T>  {

    private final Class <T> currentPage;
    private final Button signUpBtn = new Button(By.cssSelector("#signInModal .modal-footer button.btn-primary"));
    private final Button closeBtn = new Button(By.cssSelector("#signInModal .modal-footer button.btn-secondary"));
    private final TextInputField userNameField = new TextInputField( By.id("sign-username"));
    private final TextInputField passwordField = new TextInputField(By.id("sign-password"));
    private final Container body = new Container(By.id("signInModal"));

    public SignUpForm(Class<T> currentPage) {
        this.currentPage = currentPage;
        if(isDisplayed()){
            LogsUtils.info("ContactForm modal is visible.");
        }
        else{
            LogsUtils.warn("ContactForm modal is not visible.");
        }
    }
    public boolean isDisplayed() {
        boolean isDisplayed = body.isDisplayed();
        LogsUtils.info("AboutUs modal is displayed: " + isDisplayed);
        return isDisplayed;
    }

    public void enterUserName(String username) {
        userNameField.write(username);
        LogsUtils.info("Username entered successfully.");
    }

    public void enterPassword(String password) {
        passwordField.write(password);
        LogsUtils.info("Password entered successfully.");
    }

    public T clickSignUp() {
        signUpBtn.click();
        LogsUtils.info("'Sign Up' button clicked successfully.");
        try {
            return currentPage.getDeclaredConstructor().newInstance();
        }catch (Exception e){
            LogsUtils.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }

    public T clickClose() {
        closeBtn.click();
        LogsUtils.info("'Close' button clicked successfully.");
        try {
            return currentPage.getDeclaredConstructor().newInstance();
        }catch (Exception e){
            LogsUtils.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }
}
