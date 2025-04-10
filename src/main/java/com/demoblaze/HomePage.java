package com.demoblaze;

import BasePage.BasePage;
import PageComponents.NavBar;
import org.openqa.selenium.By;
import utilities.Waits;

public class HomePage extends BasePage<HomePage>{

    public NavBar<HomePage> navBar;
    private final By phoneLocator = By.linkText("Nokia lumia 1520");
    private final By addToCartButtonLocator = By.cssSelector(".btn.btn-success.btn-lg");
    private final String url = "https://www.demoblaze.com/";
    private final By body = By.id("tbodyid");


    public HomePage(){
        navBar= new NavBar<HomePage>(this);
    }


    public void loadHomePage() {

        getDriver().get(url);
    }

    public ItemPage addProductToCart() {
        Waits.waitForVisibility(phoneLocator,2);
        click(phoneLocator);
        return new ItemPage();
    }
}

