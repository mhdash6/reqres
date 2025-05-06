package PageComponents;


import com.demoblaze.CartPage;
import org.openqa.selenium.By;
import utilities.common.LogsUtils;

import utilities.selenium.helperClasses.AjaxWaitUtils;
import utilities.selenium.helperClasses.Alert;
import utilities.uiElements.Button;
import utilities.uiElements.Container;
import utilities.uiElements.TextContainer;
import utilities.uiElements.TextInputField;



public class OrderForm{


    private final TextInputField nameField = new TextInputField(By.id("name"));
    private final TextInputField countryField = new TextInputField(By.id("country"));
    private final TextInputField cityField = new TextInputField(By.id("city"));
    private final TextInputField cardField = new TextInputField(By.id("card"));
    private final TextInputField monthField = new TextInputField(By.id("month"));
    private final TextInputField yearField = new TextInputField(By.id("year"));
    private final Button purchaseBtn = new Button(By.cssSelector("#orderModal .btn.btn-primary"));
    private final Button closeBtn = new Button(By.cssSelector("#orderModal .btn.btn-secondary"));
    private final Button okBtn = new Button(By.cssSelector(".confirm.btn.btn-lg.btn-primary"));
    private final Container body = new Container(By.id("orderModal"));
    private final TextContainer successMsg = new TextContainer(By.className("sweet-alert"));
    private final TextContainer transactionDetails =  new TextContainer(By.className("lead"));

    public OrderForm() {
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


    public void enterName(String name) {
        nameField.write(name);
        LogsUtils.info("Name entered successfully.");
    }

    public void enterCountry(String country) {
        countryField.write(country);
        LogsUtils.info("Country entered successfully.");
    }

    public void enterCity(String city) {
        cityField.write(city);
        LogsUtils.info("City entered successfully.");
    }

    public void enterCard(String card) {
        cardField.write(card);
        LogsUtils.info("Card number entered successfully.");
    }

    public void enterMonth(String month) {
        monthField.write(month);
        LogsUtils.info("Month entered successfully.");
    }

    public void enterYear(String year) {
        yearField.write(year);
        LogsUtils.info("Year entered successfully.");
    }

    public CartPage clickClose() {
        closeBtn.click();
        LogsUtils.info("'Close' button clicked successfully.");
        return new CartPage();
    }

    public void clickPurchase() {
        purchaseBtn.click();
        LogsUtils.info("'Purchase' button clicked successfully.");
    }

    public void clickOk() {
        AjaxWaitUtils.waitForJQuery(2);
        okBtn.click();
        LogsUtils.info("'OK' button clicked successfully.");
    }

    public boolean isSuccessful() {
        boolean isDisplayed = successMsg.isDisplayed() ;
        LogsUtils.info("Success message displayed: " + isDisplayed);
        return isDisplayed;
    }

    public String getSuccessMessage() {
        String message = successMsg.getText();
        LogsUtils.info("Success message fetched: " + message);
        return message;
    }

    public String getErrorMsg(){
      return Alert.getAlertMessage();
    }

    public boolean IsCorrectErrorMsgDisplayed(){
        return "Please fill out Name and Creditcard.".equals(getErrorMsg());
    }

    public void acceptAlert(){
        Alert.clickOK();
    }

    public void fillOrderForm(String name, String country, String city, String card, String month, String year) {
        enterName(name);
        enterCountry(country);
        enterCity(city);
        enterCard(card);
        enterMonth(month);
        enterYear(year);
        LogsUtils.info("Order form filled successfully.");
    }
    public Double getAmount() {
     String text = transactionDetails.getText();
        String total = text.split("Amount:")[1]
                .split("USD")[0]
                .trim();
        return Double.parseDouble(total);
    }
}
