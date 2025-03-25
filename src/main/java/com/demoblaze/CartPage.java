package com.demoblaze;
import static  utilities.Tables.*;
import BasePage.BasePage;
import PageComponents.NavBar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {
    public NavBar<CartPage> navBar = new NavBar<>(this);
    private final By placeOrderBtn = By.cssSelector(".btn.btn-success");
    private final By tableHeaders = By.cssSelector("thead th");
    private final By tableRows = By.cssSelector("tbody tr");


    public int getItemsCount(){
        WebElement [] rows = driver.findElements(tableRows).toArray(new WebElement[0]);
        return rows.length;
    }



}
