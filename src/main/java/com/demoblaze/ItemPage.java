package com.demoblaze;

import static utilities.selenium.helperClasses.SimpleElementActions.click;
import static utilities.selenium.helperClasses.SimpleElementActions.find;
import BasePage.BasePage;
import PageComponents.NavBar;
import org.openqa.selenium.By;
import utilities.selenium.helperClasses.Alert;
import utilities.common.LogsUtil;
import utilities.selenium.helperClasses.Waits;

public class ItemPage extends BasePage<ItemPage> {

    public NavBar<ItemPage> navBar;
    private final By productNameLocator = By.cssSelector(".name");
    private final By addToCartButtonLocator = By.cssSelector(".btn.btn-success.btn-lg");

    public ItemPage() {
        navBar = new NavBar<>(this);
    }

    private String getProductName() {
        Waits.waitForElementVisibility(getDriver(), productNameLocator, 2);
        LogsUtil.info("Fetching the product name.");
        String productName = find(getDriver(), productNameLocator).getText();
        LogsUtil.info("Product name fetched: " + productName);
        return productName;
    }

    public void clickAddToCart() {
        String productName = getProductName();
        LogsUtil.info("Waiting for the 'Add to Cart' button for product '" + productName + "' to be visible.");
        Waits.waitForElementVisibility(getDriver(), addToCartButtonLocator, 2);
        LogsUtil.info("Clicking the 'Add to Cart' button for product '" + productName + "'.");
        click(getDriver(), addToCartButtonLocator);
        LogsUtil.info("'Add to Cart' button clicked successfully for product '" + productName + "'.");
    }

    public void acceptAlert() {
        LogsUtil.info("Accepting the alert for the product.");
        Alert.clickOK(getDriver());
        LogsUtil.info("Alert accepted successfully.");
    }
}
