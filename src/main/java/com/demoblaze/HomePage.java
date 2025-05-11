package com.demoblaze;


import PageComponents.NavBar;
import org.openqa.selenium.By;
import utilities.common.LogsUtils;
import utilities.common.PropertiesUtils;
import utilities.selenium.helperClasses.AjaxWaitUtils;
import utilities.selenium.helperClasses.BrowserActions;
import utilities.selenium.helperClasses.SimpleElementActions;

import utilities.selenium.helperClasses.Waits;
import utilities.uiElements.Button;
import utilities.uiElements.Container;
import utilities.uiElements.Link;



public class HomePage {
    public static NavBar<HomePage> navBar;

    private final Container productList = new Container(By.cssSelector("#tbodyid .card"));
    public final Button nextBtn = new Button(By.id("next2"));
    private final Button previousBtn = new Button(By.id("prev2"));
    private final Link phonesLink = new Link(By.linkText("Phones"));
    private final Link laptopsLink = new Link(By.linkText("Laptops"));
    private final Link monitorsLink = new Link(By.linkText("Monitors"));

    public HomePage() {
        if (navBar == null) {
            navBar = new NavBar<>(HomePage.class);
        }
    }

    public int getProductCount() {
        AjaxWaitUtils.waitForJQuery(10);
        if(isProductListVisible()){
            int count = SimpleElementActions.findAll(productList.getLocator()).size();
            LogsUtils.info("Product count: " + count);
            return count;
        }else {
            return 0;
        }
    }

    public void load() {
        String url=  PropertiesUtils.getProperty("url");
        BrowserActions.navigateToURL(url);
        AjaxWaitUtils.waitForJQuery(10);
        LogsUtils.info("Home page loaded successfully.");
    }

    public ItemPage clickOnProduct(String productName) {
        LogsUtils.info("Adding product '" + productName + "' to the cart.");
        Link productLink = new Link(By.linkText(productName));
        productLink.click();
        LogsUtils.info("Product '" + productName + "' clicked. Navigating to Item Page.");
        return new ItemPage();
    }

    public boolean isProductListVisible() {
        boolean displayed= productList.isDisplayed();
        LogsUtils.info("Product list is " + (displayed?"":"not ") + "visible.");
        return displayed;
    }
    public void clickNextPage() {
        nextBtn.click();
        LogsUtils.info("Clicked 'Next' button successfully.");
        Waits.waitForElementToBeInvisible(nextBtn.getLocator(), 5);
        AjaxWaitUtils.waitForJQuery(10);
    }
    public void clickPreviousPage() {
        AjaxWaitUtils.waitForJQuery(10);
        previousBtn.click();
        LogsUtils.info("Clicked 'Previous' button successfully.");
        AjaxWaitUtils.waitForJQuery(10);
    }
    public void clickPhonesLink() {
        phonesLink.click();
        LogsUtils.info("Clicked 'Phones' link successfully.");
        AjaxWaitUtils.waitForJQuery(10);
    }
    public void clickLaptopsLink() {
        laptopsLink.click();
        LogsUtils.info("Clicked 'Laptops' link successfully.");
        AjaxWaitUtils.waitForJQuery(10);
    }
    public void clickMonitorsLink() {
        monitorsLink.click();
        LogsUtils.info("Clicked 'Monitors' link successfully.");
        AjaxWaitUtils.waitForJQuery(10);
    }

    public boolean isPreviousPageButtonVisible() {
        boolean displayed= previousBtn.isDisplayed();
        LogsUtils.info("Previous button is " + (displayed?"":"not ") + "visible.");
        return displayed;
    }

    public boolean isNextPageButtonVisible() {
        boolean displayed= nextBtn.isDisplayed();
        LogsUtils.info("Next button is " + (displayed?"":"not ") + "visible.");
        return displayed;
    }


    public void addItemsToCart(String... productNames) {
        for (String productName : productNames) {
            ItemPage itemPage = null;
            Link productLink = new Link(By.linkText(productName));
            if (productLink.isDisplayed()) {
                itemPage = clickOnProduct(productName);
            } else {
                clickNextPage();
                productLink = new Link(By.linkText(productName));
                if (productLink.isDisplayed()) {
                    itemPage = clickOnProduct(productName);
                } else {
                    LogsUtils.error("Product '" + productName + "' not found on either page.");
                    throw new RuntimeException("Item "+productName+" not found");
                }
            }
            if (itemPage != null) {
                itemPage.clickAddToCart();
                itemPage.acceptAlert();
                itemPage.navBar.clickHome();
            }
        }
    }


}

