package com.demoblaze;

import PageComponents.NavBar;
import org.openqa.selenium.By;
import utilities.selenium.helperClasses.Alert;
import utilities.common.LogsUtils;
import utilities.uiElements.Button;
import utilities.uiElements.TextContainer;


public class ItemPage  {

    public static NavBar<ItemPage> navBar;
    private final TextContainer productName = new TextContainer( By.cssSelector(".name"));
    private final TextContainer productDescription = new TextContainer( By.cssSelector("#more-information p"));
    private final Button addToCartButton = new Button( By.cssSelector(".btn.btn-success.btn-lg"));


    public ItemPage() {
        if (navBar == null) {
            navBar = new NavBar<>(ItemPage.class);
        }
    }

    private String getProductName() {
        LogsUtils.info("Fetching the product name.");
        String name  = this.productName.getText();
        LogsUtils.info("Product name fetched: " + name);
        return  name;
    }
    public String getProductDescription() {
        LogsUtils.info("Fetching the product description.");
        String description  = this.productDescription.getText();
        LogsUtils.info("Product description fetched: " + description);
        return  description;
    }

    public void clickAddToCart() {
        LogsUtils.info("Clicking the 'Add to Cart' button for the product.");
        addToCartButton.click();
    }

    public void acceptAlert() {
        LogsUtils.info("Accepting the alert for the product.");
        Alert.clickOK();
        LogsUtils.info("Alert accepted successfully.");
    }

}
