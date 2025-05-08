package com.demoblaze;

import static utilities.selenium.helperClasses.Tables.getHeadersNames;
import PageComponents.NavBar;
import PageComponents.OrderForm;
import org.apache.commons.io.build.AbstractOrigin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.common.JsonUtils;
import utilities.common.LogsUtils;
import utilities.common.PropertiesUtils;
import utilities.selenium.helperClasses.AjaxWaitUtils;
import utilities.selenium.helperClasses.BrowserActions;
import utilities.selenium.helperClasses.SimpleElementActions;
import utilities.selenium.helperClasses.Waits;
import utilities.uiElements.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class CartPage  {


    public static NavBar<CartPage> navBar;
    private final Button placeOrderBtn   = new Button( By.cssSelector(".btn.btn-success"));
    private final TextContainer total = new TextContainer(By.id("totalp"));
    private final By tableHeaders    = By.cssSelector("thead th");
    private final By tableRows = By.cssSelector("tbody tr");

    public CartPage() {
        if (navBar == null) {
            navBar = new NavBar<>(CartPage.class);
        }
    }
    public void load() {
        String url=  PropertiesUtils.getProperty("url")+"/cart.html";
        BrowserActions.navigateToURL(url);
        LogsUtils.info("Cart page loaded successfully.");
    }

    private List<TableRow> fetchRows() {
        AjaxWaitUtils.waitForJQuery(5);
        List<TableRow> rows = new ArrayList<>();
        int count = SimpleElementActions.findAll(tableRows).size();
        for (int i = 1; i <= count; i++) {
            By rowLocator = By.cssSelector(
                    String.format("tbody tr:nth-of-type(%d)", i)
            );
            rows.add(new TableRow(rowLocator));
        }
        LogsUtils.info("Fetched " + rows.size() + " rows.");
        return rows;
    }

    public List<String> getItemsNames(){
        List<String> names = new ArrayList<>() {
        };
        List<TableRow> rows = fetchRows();
        for(TableRow row : rows){
            if (row.isDisplayed()){
                TableCell ItemNameCell = new TableCell(row.getLocator(), By.cssSelector("td:nth-of-type(2)") );
                names.add(ItemNameCell.getText());
            }
        }
        return names;
    }

    public List<String> getItemsPrices(){
        List<String> prices = new ArrayList<>() {
        };
        List<TableRow> rows = fetchRows();
        for(TableRow row : rows){
            if (row.isDisplayed()){
                TableCell ItemNameCell = new TableCell(row.getLocator(), By.cssSelector("td:nth-of-type(3)") );
                prices.add(ItemNameCell.getText());
            }
        }
        return prices;
    }

    public boolean isCartEmpty() {
        LogsUtils.info("Checking if the cart is empty.");
        List<TableRow> rows = fetchRows();
        if (rows.isEmpty()) {
            LogsUtils.info("Cart is empty (no rows found).");
            return true;
        }

        for (TableRow row : rows) {
            if (!row.isDeleted()) {
                LogsUtils.info("Cart not empty (found visible row: " + row.getLocator() + ").");
                return false;
            }
        }

        LogsUtils.info("All rows deleted; cart is empty.");
        return true;
    }

    public int getItemsCount() {
        LogsUtils.info("Getting item count in cart.");
        List<TableRow> rows = fetchRows();
        int count = 0;
        for (TableRow row : rows) {
            if (!row.isDeleted()) {
                count++;
            }
        }
        LogsUtils.info("Items in cart: " + count);
        return count;
    }


    public OrderForm clickPlaceOrder() {
        AjaxWaitUtils.waitForJQuery(5);
        LogsUtils.info("Clicking Place Order.");
        placeOrderBtn.click();
        return new OrderForm();
    }


    public void deleteItem(String itemName) {
        LogsUtils.info("Deleting item '" + itemName + "'.");
        List<TableRow> rows = fetchRows();
        if (rows.isEmpty()) {
            LogsUtils.warn("Cart is empty; nothing to delete.");
            return;
        }
        for (TableRow row : rows) {
            if (row.isDeleted()) continue;
            TableCell nameCell = row.getCell(By.cssSelector("td:nth-of-type(2)"));
            if (itemName.equals(nameCell.getText())) {
                WebElement tracker = SimpleElementActions.find(row.getLocator());
                LogsUtils.info("Found '" + itemName + "', clicking delete.");
                TableCell deleteCell = row.getCell(By.cssSelector("td:last-of-type"));
                try {
                    new Link(deleteCell.getLocator(), By.cssSelector("a")).click();
                    Waits.waitForElementToStale(tracker, 5);
                    AjaxWaitUtils.waitForJQuery(2);
                    LogsUtils.info("Item deleted successfully.");
                } catch (Exception e) {
                    LogsUtils.error("Error deleting item: " + e.getMessage());
                    LogsUtils.error("Deletion did not complete.");
                }
                return;
            }
        }

        LogsUtils.warn("Item '" + itemName + "' not found.");
    }

    public double getTotal() {
        String price = total.getText();
        return Double.parseDouble(price);
    }


    public void exportTableToJson(String filePath) {
        LogsUtils.info("Exporting cart to JSON: " + filePath);
        if (isCartEmpty()) {
            LogsUtils.warn("Cart empty; nothing to export.");
            return;
        }
        String[] headers = getHeadersNames( tableHeaders);
        List<Map<String, String>> data = new ArrayList<>();

        for (TableRow row : fetchRows()) {
            if (!row.isDisplayed()) continue;
            Map<String, String> rowMap = new HashMap<>();
            for (int col = 1; col <= headers.length; col++) {
                TableCell cell = row.getCell(By.cssSelector(
                        String.format("td:nth-of-type(%d)", col)
                ));
                rowMap.put(headers[col - 1], cell.getText());
            }
            data.add(rowMap);
        }
        JsonUtils.writeJson(filePath, data);
        LogsUtils.info("JSON export complete.");
    }
}

