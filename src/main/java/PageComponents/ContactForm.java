package PageComponents;


import org.openqa.selenium.By;
import utilities.selenium.helperClasses.Alert;
import utilities.common.LogsUtils;
import utilities.uiElements.Button;
import utilities.uiElements.Container;
import utilities.uiElements.TextInputField;


public class ContactForm<T>{


    private final Class <T> currentPage;
    private final TextInputField emailField = new TextInputField( By.id("recipient-email"));
    private final TextInputField nameField = new TextInputField( By.id("recipient-name"));
    private final TextInputField messageField = new TextInputField(By.id("message-text"));
    private final Button sendMessageBtn = new Button( By.cssSelector("button[onclick='send()']"));
    private final Button closeBtn = new Button( By.cssSelector("div[id='exampleModal'] button[class='btn.btn-secondary']"));
    private final Container body = new Container(By.id("exampleModal"));

    public ContactForm( Class<T> currentPage ) {
        this.currentPage = currentPage;
        if(isDisplayed()){
            LogsUtils.info("ContactForm modal is visible.");
        }
        else{
            LogsUtils.warn("ContactForm modal is not visible.");
        }
    }
    private boolean isDisplayed() {
        boolean isDisplayed = body.isDisplayed();
        LogsUtils.info("AboutUs modal is displayed: " + isDisplayed);
        return isDisplayed;
    }

    public void enterEmail(String email) {
        emailField.write(email);
        LogsUtils.info("Email entered successfully.");
    }

    public void enterName(String name) {
        nameField.write(name);
        LogsUtils.info("Name entered successfully.");
    }

    public void enterMessage(String message) {
        messageField.write(message);
        LogsUtils.info("Message entered successfully.");
    }

    public void fillContactForm(String email, String name, String message) {
        enterEmail(email);
        enterName(name);
        enterMessage(message);
        LogsUtils.info("Contact form filled successfully.");
    }

    public T clickCloseBtn() {
        closeBtn.click();
        LogsUtils.info("'Close' button clicked successfully.");
        try {
            return currentPage.getDeclaredConstructor().newInstance();
        }catch (Exception e){
            LogsUtils.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }

    public void clickSendMessageBtn() {
        sendMessageBtn.click();
        LogsUtils.info("'Send Message' button clicked successfully.");
    }

    public T acceptSucessAlert(){
        Alert.clickOK();
        try {
            return currentPage.getDeclaredConstructor().newInstance();
        }catch (Exception e){
            LogsUtils.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }


    public boolean successMsgDisplayed() {
        LogsUtils.info("Checking if the success message is displayed.");
        boolean isDisplayed = Alert.getAlertMessage().equals("Thanks for the message!!");
        LogsUtils.info("Success message displayed: " + isDisplayed);
        return isDisplayed;
    }
}

