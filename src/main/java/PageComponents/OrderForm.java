package PageComponents;

import BasePage.BasePage;
import org.openqa.selenium.By;
import utilities.Waits;

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
        logger.get().info("Initializing OrderForm component for the current page.");
        Waits.waitForElementVisibility(body, 2);
        logger.get().info("OrderForm modal is visible.");
    }

    public void enterName(String name) {
        logger.get().info("Entering name: {}", name);
        set(nameField, name);
        logger.get().info("Name entered successfully.");
    }

    public void enterCountry(String country) {
        logger.get().info("Entering country: {}", country);
        set(countryField, country);
        logger.get().info("Country entered successfully.");
    }

    public void enterCity(String city) {
        logger.get().info("Entering city: {}", city);
        set(cityField, city);
        logger.get().info("City entered successfully.");
    }

    public void enterCard(String card) {
        logger.get().info("Entering card number: {}", card);
        set(cardField, card);
        logger.get().info("Card number entered successfully.");
    }

    public void enterMonth(String month) {
        logger.get().info("Entering month: {}", month);
        set(monthField, month);
        logger.get().info("Month entered successfully.");
    }

    public void enterYear(String year) {
        logger.get().info("Entering year: {}", year);
        set(yearField, year);
        logger.get().info("Year entered successfully.");
    }

    public T clickClose() {
        logger.get().info("Clicking the 'Close' button on the OrderForm modal.");
        click(closeBtn);
        logger.get().info("'Close' button clicked successfully.");
        return currentPage;
    }

    public void clickPurchase() {
        logger.get().info("Clicking the 'Purchase' button on the OrderForm modal.");
        click(purchaseBtn);
        logger.get().info("'Purchase' button clicked successfully.");
    }

    public void clickOk() {
        logger.get().info("Waiting for the 'OK' button to be visible.");
        Waits.waitForElementVisibility(okBtn, 1);
        logger.get().info("Clicking the 'OK' button.");
        Waits.waitForAjaxToComplete();
        click(okBtn);
        logger.get().info("'OK' button clicked successfully.");
    }

    public boolean isSuccessful() {
        logger.get().info("Checking if the success message is displayed.");
        Waits.waitForElementVisibility(successMsg, 3);
        boolean isDisplayed = find(successMsg).isDisplayed();
        logger.get().info("Success message displayed: {}", isDisplayed);
        return isDisplayed;
    }

    public String getSuccessMessage() {
        logger.get().info("Fetching the success message.");
        Waits.waitForElementVisibility(successMsg, 3);
        String message = find(successMsg).getText();
        logger.get().info("Success message fetched: {}", message);
        return message;
    }
}
