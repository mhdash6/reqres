package com.demoblaze;

import BasePage.BasePage;
import PageComponents.NavBar;
import org.openqa.selenium.By;
import utilities.Waits;

public class HomePage extends BasePage<HomePage> {

    public NavBar<HomePage> navBar;

    private final By productList = By.id("tbodyid");
    

    private static final String URL = "https://www.demoblaze.com/";

    public HomePage() {
        navBar = new NavBar<>(this);
    }

    public void loadHomePage() {
        logger.get().info("Loading the home page: {}", URL);
        getDriver().get(URL);
        logger.get().info("Home page loaded successfully.");
    }

    public ItemPage addProductToCart(String productName) {
        logger.get().info("Adding product '{}' to the cart.", productName);
        By productLocator = By.linkText(productName);
        Waits.waitForElementVisibility(productLocator, 2);
        click(productLocator);
        logger.get().info("Product '{}' clicked. Navigating to Item Page.", productName);
        return new ItemPage();
    }

    public boolean isProductListVisible() {
        logger.get().info("Checking if the product list is visible.");
       try {
        Waits.waitForElementVisibility(productList, 2);
        return getDriver().findElement(productList).isDisplayed();
       } catch (Exception e) {
              logger.get().error("Product list is not visible: {}", e.getMessage());
              return false;
       }

    }
}

