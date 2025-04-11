package com.demoblaze;

import BasePage.BasePage;
import PageComponents.NavBar;
import org.openqa.selenium.By;
import utilities.Alert;
import utilities.Waits;

public class ItemPage extends BasePage<ItemPage> {

    public NavBar<ItemPage> navBar;
    private final By productNameLocator = By.cssSelector(".name");
    private final By addToCartButtonLocator = By.cssSelector(".btn.btn-success.btn-lg");

    public ItemPage() {
        navBar = new NavBar<>(this);
    }

    private String getProductName() {
        Waits.waitForElementVisibility(productNameLocator,2);
        logger.get().info("Fetching the product name.");
        String productName = getDriver().findElement(productNameLocator).getText();
        logger.get().info("Product name fetched: {}", productName);
        return productName;
    }

    public void clickAddToCart() {
        String productName = getProductName();
        logger.get().info("Waiting for the 'Add to Cart' button for product '{}' to be visible.", productName);
        Waits.waitForElementVisibility(addToCartButtonLocator, 2);
        logger.get().info("Clicking the 'Add to Cart' button for product '{}'.", productName);
        click(addToCartButtonLocator);
        logger.get().info("'Add to Cart' button clicked successfully for product '{}'.", productName);
    }

    public void acceptAlert() {
        logger.get().info("Accepting the alert for the product.");
        Alert.clickOK();
        logger.get().info("Alert accepted successfully.");
    }
}
