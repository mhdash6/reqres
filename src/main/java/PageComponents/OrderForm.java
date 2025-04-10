package PageComponents;

import BasePage.BasePage;
import org.openqa.selenium.By;
import utilities.Waits;

public class OrderForm<T> extends BasePage<T> {
    private final T currentPage;

    private final By nameField= By.id("name");
    private final By countryField= By.id("country");
    private final By cityField= By.id("city");
    private final By cardField= By.id("card");
    private final By monthField= By.id("month");
    private final By yearField= By.id("year");
    private final By purchaseBtn= By.cssSelector("#orderModal .btn.btn-primary");
    private final By closeBtn= By.cssSelector("#orderModal .btn.btn-secondary");
    private final By body= By.id("orderModal");
    private final By okBtn = By.cssSelector(".confirm.btn.btn-lg.btn-primary");
    public final By sucessMsg = By.className("sweet-alert");



    public OrderForm(T currentPage){
        this.currentPage = currentPage;
        Waits.waitForVisibility(body,2);
    }

    public void enterName(String name) {
        set(nameField, name);
    }

    public void enterCountry(String country) {
        set(countryField, country);
    }

    public void enterCity(String city) {
        set(cityField, city);
    }

    public void enterCard(String card) {
        set(cardField, card);
    }

    public void enterMonth(String month) {
        set(monthField, month);
    }

    public void enterYear(String year) {
        set(yearField, year);
    }

    public T clickClose(){
        click(closeBtn);
        return currentPage;
    }

    public void clickPurchase(){
        click(purchaseBtn);
    }

    public void clickOk(){
       Waits.waitForVisibility(okBtn,1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        click(okBtn);
    }



    public boolean isSuccesfull(){
        Waits.waitForVisibility(sucessMsg, 3);
        return find(sucessMsg).isDisplayed();

    }
}
