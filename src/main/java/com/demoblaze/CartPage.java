package com.demoblaze;

import static utilities.Tables.getHeadersNames;
import static utilities.Tables.getTableRows;
import BasePage.BasePage;
import PageComponents.NavBar;
import PageComponents.OrderForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.DataUtil;
import utilities.Waits;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartPage extends BasePage<CartPage> {

    public static NavBar<CartPage> navBar;

    private final By placeOrderBtn = By.cssSelector(".btn.btn-success");
    private final By tableHeaders = By.cssSelector("thead th");
    private final By tableRows = By.cssSelector("tbody tr");
    private final By tableCell = By.cssSelector("tbody td");

    public CartPage() {
        navBar = new NavBar<>(this);
    }

    public boolean isCartEmpty() {
        logger.get().info("Checking if the cart is empty.");
        try {
            Waits.waitForElementVisibility(tableRows, 2);
            boolean isEmpty = getDriver().findElements(tableRows).isEmpty();
            logger.get().info("Cart empty status: {}", isEmpty);
            return isEmpty;
        } catch (Exception e) {
            logger.get().warn("Timeout or error while waiting for table rows. Error: {}", e.getMessage());
            return true; 
        }
    }

    public int getItemsCount() {
        logger.get().info("Getting the count of items in the cart.");
        if (isCartEmpty()) {
            logger.get().info("The cart is empty.");
            return 0;
        }
        int itemCount = getDriver().findElements(tableRows).size();
        logger.get().info("Number of items in the cart: {}", itemCount);
        return itemCount;
    }

    public void printItemsInCart() {
        logger.get().info("Printing items in the cart.");
        if (isCartEmpty()) {
            logger.get().info("The cart is empty.");
            System.out.println("Empty Cart");
            return;
        }

        String[][] rows = getTableRows(tableRows, tableCell);
        for (String[] row : rows) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        logger.get().info("Items in the cart printed successfully.");
    }

    public OrderForm<CartPage> clickPlaceOrder() {
        logger.get().info("Clicking the 'Place Order' button.");
        Waits.waitForElementVisibility(placeOrderBtn, 2);
        click(placeOrderBtn);
        logger.get().info("'Place Order' button clicked successfully.");
        return new OrderForm<>(this);
    }

    public void deleteItem(String deleteThis) {
        logger.get().info("Attempting to delete item '{}' from the cart.", deleteThis);

        if (isCartEmpty()) {
            logger.get().warn("The cart is empty. Cannot delete item '{}'.", deleteThis);
            return;
        }

        List<WebElement> rows = getDriver().findElements(tableRows);
        boolean itemDeleted = false;

        for (WebElement row : rows) {
            try {
                String itemName = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
                if (itemName.equals(deleteThis)) {
                    logger.get().info("Item '{}' found in the cart. Attempting to delete it.", deleteThis);
                    By rowLocator = By.xpath("//tr[td[text()='" + deleteThis + "']]");
                    WebElement deleteButton = row.findElement(By.tagName("a"));
                    deleteButton.click();
                    Waits.waitForElementToBeInvisible(rowLocator, 2);
                    logger.get().info("Item '{}' deleted successfully.", deleteThis);
                    itemDeleted = true;
                    break;
                }
            } catch (Exception e) {
                logger.get().error("Error while attempting to delete item '{}'. Error: {}", deleteThis, e.getMessage());
            }
        }

        if (!itemDeleted) {
            logger.get().warn("Item '{}' not found in the cart. Deletion could not be performed.", deleteThis);
        }
    }

    public void exportTableToJson(String filePath) {
        logger.get().info("Exporting table data to JSON file: {}", filePath);
        if (isCartEmpty()) {
            logger.get().warn("The cart is empty. No data to export.");
            return;
        }

        String[] headers = getHeadersNames(tableHeaders);
        String[][] rows = getTableRows(tableRows, tableCell);

        List<Map<String, String>> tableData = new ArrayList<>();

        for (String[] row : rows) {
            Map<String, String> rowMap = new HashMap<>();
            for (int i = 0; i < headers.length; i++) {
                rowMap.put(headers[i], i < row.length ? row[i] : "");
            }
            tableData.add(rowMap);
        }

        DataUtil.writeJson(filePath, tableData);
        logger.get().info("Table data successfully exported to JSON file: {}", filePath);
    }
}
