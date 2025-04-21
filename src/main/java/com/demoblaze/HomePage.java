package com.demoblaze;
import static utilities.SimpleElementActions.click;
import BasePage.BasePage;
import PageComponents.NavBar;
import org.openqa.selenium.By;
import utilities.LogsUtil;
import utilities.Waits;

public class HomePage extends BasePage<HomePage> {

    public NavBar<HomePage> navBar;

    private final By productList = By.id("tbodyid");

    private static final String URL = "https://www.demoblaze.com/";

    public HomePage() {
        navBar = new NavBar<>(this);
    }

    public void loadHomePage() {
        LogsUtil.info("Loading the home page: " + URL);
        getDriver().get(URL);
        LogsUtil.info("Home page loaded successfully.");
    }

    public ItemPage addProductToCart(String productName) {
        LogsUtil.info("Adding product '" + productName + "' to the cart.");
        By productLocator = By.linkText(productName);
        Waits.waitForElementVisibility(getDriver(), productLocator, 2);
        click(productLocator,getDriver());
        LogsUtil.info("Product '" + productName + "' clicked. Navigating to Item Page.");
        return new ItemPage();
    }

    public boolean isProductListVisible() {
        LogsUtil.info("Checking if the product list is visible.");
        try {
            Waits.waitForElementVisibility(getDriver(), productList, 2);
            boolean isVisible = getDriver().findElement(productList).isDisplayed();
            LogsUtil.info("Product list visibility status: " + isVisible);
            return isVisible;
        } catch (Exception e) {
            LogsUtil.error("Product list is not visible. Error: " + e.getMessage());
            return false;
        }
    }
}

