package PageComponents;

import BasePage.BasePage;
import org.openqa.selenium.By;
import utilities.LogsUtil;
import utilities.Waits;

import static utilities.SimpleElementActions.click;
import static utilities.SimpleElementActions.set;
import static utilities.SimpleElementActions.find;

public class OrderForm<T> extends BasePage<T> {
    private final T currentPage;

    private final By nameField = By.id("name");
    private final By countryField = By.id("country");
    private final By cityField = By.id("city");
    private final By cardField = By.id("card");
    private final By monthField = By.id("month");
    private final By yearField = By.id("year");
    private final By purchaseBtn = By.cssSelector("#orderModal .btn.btn-primary");
    private final By closeBtn = By.cssSelector("#orderModal .btn.btn-secondary");
    private final By body = By.id("orderModal");
    private final By okBtn = By.cssSelector(".confirm.btn.btn-lg.btn-primary");
    public final By successMsg = By.className("sweet-alert");

    public OrderForm(T currentPage) {
        this.currentPage = currentPage;
        LogsUtil.info("Initializing OrderForm component for the current page.");
        Waits.waitForElementVisibility(getDriver(), body, 2);
        LogsUtil.info("OrderForm modal is visible.");
    }

    public void enterName(String name) {
        LogsUtil.info("Entering name: " + name);
        set(nameField, getDriver(), name);
        LogsUtil.info("Name entered successfully.");
    }

    public void enterCountry(String country) {
        LogsUtil.info("Entering country: " + country);
        set(countryField, getDriver(), country);
        LogsUtil.info("Country entered successfully.");
    }

    public void enterCity(String city) {
        LogsUtil.info("Entering city: " + city);
        set(cityField, getDriver(), city);
        LogsUtil.info("City entered successfully.");
    }

    public void enterCard(String card) {
        LogsUtil.info("Entering card number: " + card);
        set(cardField, getDriver(), card);
        LogsUtil.info("Card number entered successfully.");
    }

    public void enterMonth(String month) {
        LogsUtil.info("Entering month: " + month);
        set(monthField, getDriver(), month);
        LogsUtil.info("Month entered successfully.");
    }

    public void enterYear(String year) {
        LogsUtil.info("Entering year: " + year);
        set(yearField, getDriver(), year);
        LogsUtil.info("Year entered successfully.");
    }

    public T clickClose() {
        LogsUtil.info("Clicking the 'Close' button on the OrderForm modal.");
        click(closeBtn, getDriver());
        LogsUtil.info("'Close' button clicked successfully.");
        return currentPage;
    }

    public void clickPurchase() {
        LogsUtil.info("Clicking the 'Purchase' button on the OrderForm modal.");
        click(purchaseBtn, getDriver());
        LogsUtil.info("'Purchase' button clicked successfully.");
    }

    public void clickOk() {
        LogsUtil.info("Waiting for the 'OK' button to be visible.");
        Waits.waitForElementVisibility(getDriver(), okBtn, 1);
        LogsUtil.info("Clicking the 'OK' button.");
        click(okBtn, getDriver());
        LogsUtil.info("'OK' button clicked successfully.");
    }

    public boolean isSuccessful() {
        LogsUtil.info("Checking if the success message is displayed.");
        Waits.waitForElementVisibility(getDriver(), successMsg, 3);
        boolean isDisplayed = find(getDriver(), successMsg).isDisplayed();
        LogsUtil.info("Success message displayed: " + isDisplayed);
        return isDisplayed;
    }

    public String getSuccessMessage() {
        LogsUtil.info("Fetching the success message.");
        Waits.waitForElementVisibility(getDriver(), successMsg, 3);
        String message = find(getDriver(), successMsg).getText();
        LogsUtil.info("Success message fetched: " + message);
        return message;
    }
}
