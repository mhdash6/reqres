package com.demoblaze;
import static utilities.selenium.helperClasses.SimpleElementActions.click;
import BasePage.BasePage;
import PageComponents.NavBar;
import org.openqa.selenium.By;
import utilities.common.LogsUtil;
import utilities.common.PropertiesUtils;
import utilities.selenium.helperClasses.Waits;

public class HomePage extends BasePage<HomePage> {

    public NavBar<HomePage> navBar;
    private final By productList = By.id("tbodyid");;
    public void loadHomePage() {
        String url=  PropertiesUtils.getProperty("url");
        LogsUtil.info("Loading the home page: " + url);
        getDriver().get(url);
        LogsUtil.info("Home page loaded successfully.");
    }

    public ItemPage addProductToCart(String productName) {
        LogsUtil.info("Adding product '" + productName + "' to the cart.");
        By productLocator = By.linkText(productName);
        Waits.waitForElementVisibility(getDriver(), productLocator, 2);
        click(getDriver(), productLocator);
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

