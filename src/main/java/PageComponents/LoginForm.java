package PageComponents;


import org.openqa.selenium.By;
import utilities.common.LogsUtils;
import utilities.selenium.helperClasses.AjaxWaitUtils;
import utilities.selenium.helperClasses.Alert;
import utilities.uiElements.Button;
import utilities.uiElements.Container;
import utilities.uiElements.TextInputField;



public class LoginForm<T>  {

    private final Class <T> currentPage;
    private final Button loginBtn = new Button(By.cssSelector("#logInModal .modal-footer button.btn-primary"));
    private final Button closeBtn = new Button( By.cssSelector("#logInModal .modal-footer button.btn-secondary"));
    private final TextInputField userNameField = new TextInputField( By.id("loginusername"));
    private final TextInputField passwordField = new TextInputField( By.id("loginpassword"));
    private final Container body = new Container(By.id("logInModal"));

    public LoginForm(Class<T> currentPage) {
        this.currentPage = currentPage;
        AjaxWaitUtils.waitForJQuery(5);
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

    public T clickLogin() {
        loginBtn.click();
        LogsUtils.info("'Login' button clicked successfully.");
        try {
            return currentPage.getDeclaredConstructor().newInstance();
        }catch (Exception e){
            LogsUtils.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }

    public T login(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        return clickLogin();
    }

    public String getErrorMsg(){
      return Alert.getAlertMessage();
    }

    public boolean isInvalidUsername(){
        return "User does not exist.".equals(getErrorMsg());
    }

    public boolean isEmptyCredentials(){
        return "Please fill out Username and Password.".equals(getErrorMsg());
    }

    public boolean isInvalidPassword(){
        return "Wrong password.".equals(getErrorMsg());
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
