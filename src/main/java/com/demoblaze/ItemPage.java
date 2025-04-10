package com.demoblaze;

import BasePage.BasePage;
import PageComponents.NavBar;
import org.openqa.selenium.By;
import utilities.Alert;
import utilities.Waits;

public class ItemPage extends BasePage<ItemPage> {
    public NavBar<ItemPage> navBar;
    private final By addToCartButtonLocator = By.cssSelector(".btn.btn-success.btn-lg");

    public ItemPage(){
        navBar= new NavBar<ItemPage>(this);
    }

    public void clickAddToCart(){
        Waits.waitForVisibility(addToCartButtonLocator,2);
        click(addToCartButtonLocator);
    }

    public void acceptAlert(){
        Alert.clickOK();
    }

}
