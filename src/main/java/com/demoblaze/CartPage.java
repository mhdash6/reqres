package com.demoblaze;
import static utilities.SimpleElementActions.click;
import static utilities.SimpleElementActions.findAll;
import static utilities.Tables.getHeadersNames;
import static utilities.Tables.getTableRows;
import BasePage.BasePage;
import PageComponents.NavBar;
import PageComponents.OrderForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.DataUtil;
import utilities.LogsUtil;
import utilities.Waits;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartPage extends BasePage<CartPage> {

    public NavBar<CartPage> navBar;

    private final By placeOrderBtn = By.cssSelector(".btn.btn-success");
    private final By tableHeaders = By.cssSelector("thead th");
    private final By tableRows = By.cssSelector("tbody tr");
    private final By tableCell = By.cssSelector("tbody td");

    public CartPage() {
        navBar = new NavBar<>(this);
    }

    public boolean isCartEmpty() {
        LogsUtil.info("Checking if the cart is empty.");
        try {
            Waits.waitForElementVisibility(getDriver(), tableRows, 2);
            boolean isEmpty = getDriver().findElements(tableRows).isEmpty();
            LogsUtil.info("Cart empty status: " + isEmpty);
            return isEmpty;
        } catch (Exception e) {
            LogsUtil.info("Cart empty status: " + true);
            return true;
        }
    }

    public int getItemsCount() {
        LogsUtil.info("Getting the count of items in the cart.");
        if (isCartEmpty()) {
            LogsUtil.info("The cart is empty.");
            return 0;
        }
        int itemCount = getDriver().findElements(tableRows).size();
        LogsUtil.info("Number of items in the cart: " + itemCount);
        return itemCount;
    }

    public void printItemsInCart() {
        LogsUtil.info("Printing items in the cart.");
        if (isCartEmpty()) {
            LogsUtil.info("The cart is empty.");
            System.out.println("Empty Cart");
            return;
        }

        String[][] rows = getTableRows(tableRows, tableCell, getDriver());
        for (String[] row : rows) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        LogsUtil.info("Items in the cart printed successfully.");
    }

    public OrderForm<CartPage> clickPlaceOrder() {
        LogsUtil.info("Clicking the 'Place Order' button.");
        Waits.waitForElementVisibility(getDriver(), placeOrderBtn, 2);
        click(placeOrderBtn, getDriver());
        LogsUtil.info("'Place Order' button clicked successfully.");
        return new OrderForm<>(this);
    }

    public void deleteItem(String deleteThis) {
        LogsUtil.info("Attempting to delete item '" + deleteThis + "' from the cart.");

        if (isCartEmpty()) {
            LogsUtil.warn("The cart is empty. Cannot delete item '" + deleteThis + "'.");
            return;
        }

        List<WebElement> rows = findAll(getDriver(), tableRows);
        boolean itemDeleted = false;

        for (WebElement row : rows) {
            try {
                String itemName = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
                if (itemName.equals(deleteThis)) {
                    LogsUtil.info("Item '" + deleteThis + "' found in the cart. Attempting to delete it.");
                    WebElement deleteButton = row.findElement(By.cssSelector("td a"));
                    deleteButton.click();
                    By rowLocator = By.xpath("//tr[td[text()='" + deleteThis + "']]");
                    Waits.waitForElementToBeInvisible(getDriver(), rowLocator, 2);
                    LogsUtil.info("Item '" + deleteThis + "' deleted successfully.");
                    itemDeleted = true;
                    break;
                }
            } catch (Exception e) {
                LogsUtil.error("Error while attempting to delete item '" + deleteThis + "'. Error: " + e.getMessage());
            }
        }

        if (!itemDeleted) {
            LogsUtil.warn("Item '" + deleteThis + "' not found in the cart. Deletion could not be performed.");
        }
    }


    public void exportTableToJson(String filePath) {
        LogsUtil.info("Exporting table data to JSON file: " + filePath);
        if (isCartEmpty()) {
            LogsUtil.warn("The cart is empty. No data to export.");
            return;
        }

        String[] headers = getHeadersNames(getDriver(), tableHeaders);
        String[][] rows = getTableRows(tableRows, tableCell, getDriver());

        List<Map<String, String>> tableData = new ArrayList<>();

        for (String[] row : rows) {
            Map<String, String> rowMap = new HashMap<>();
            for (int i = 0; i < headers.length; i++) {
                rowMap.put(headers[i], i < row.length ? row[i] : "");
            }
            tableData.add(rowMap);
        }

        DataUtil.writeJson(filePath, tableData);
        LogsUtil.info("Table data successfully exported to JSON file: " + filePath);
    }
}
