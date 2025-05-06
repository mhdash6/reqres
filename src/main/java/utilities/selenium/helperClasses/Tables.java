package utilities.selenium.helperClasses;

import static utilities.selenium.helperClasses.SimpleElementActions.findAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;
import utilities.common.LogsUtils;

import java.util.ArrayList;
import java.util.List;

public class Tables {

    @Step("Fetch table headers using provided locators")
    public static String[] getHeadersNames( By... thLocators) {
        LogsUtils.info("Fetching table headers using provided locators.");
        List<String> headers = new ArrayList<>();
        for (By locator : thLocators) {
            LogsUtils.info("Fetching headers for locator: " + locator);
            for (WebElement el : findAll(locator)) {
                headers.add(el.getText().trim());
            }
        }
        LogsUtils.info("Headers fetched: " + headers);
        return headers.toArray(new String[0]);
    }

    @Step("Fetch table rows using row locator: {rowLocator} and cell locator: {cellLocator}")
    public static String[][] getTableRows( By rowLocator, By cellLocator) {
        LogsUtils.info("Fetching table rows using row locator: " + rowLocator + " and cell locator: " + cellLocator);
        List<WebElement> rows = findAll(rowLocator);
        if (rows.isEmpty()) {
            LogsUtils.warn("No rows found for the provided row locator: " + rowLocator);
            return new String[0][];
        }

        List<List<String>> table = new ArrayList<>();
        List<String> activeValues = new ArrayList<>();
        List<Integer> activeSpans = new ArrayList<>();
        int maxCols = 0;

        for (WebElement row : rows) {
            List<String> currentRow = new ArrayList<>();
            for (int i = 0; i < activeValues.size(); i++) {
                while (currentRow.size() <= i) currentRow.add("");
                currentRow.set(i, activeValues.get(i));
                activeSpans.set(i, activeSpans.get(i) - 1);
                if (activeSpans.get(i) < 1) {
                    activeValues.set(i, "");
                    activeSpans.set(i, 0);
                }
            }

            List<WebElement> cells = row.findElements(cellLocator);
            int col = 0;
            for (WebElement cell : cells) {
                while (col < currentRow.size() && !currentRow.get(col).equals("")) col++;
                String text = cell.getText().trim();
                LogsUtils.info("Cell text fetched: " + text);
                int colspan = parseSpan(cell.getDomAttribute("colspan"));
                int rowspan = parseSpan(cell.getDomAttribute("rowspan"));

                for (int i = 0; i < colspan; i++) {
                    int index = col + i;
                    while (currentRow.size() <= index) currentRow.add("");
                    currentRow.set(index, text);

                    while (activeValues.size() <= index) {
                        activeValues.add("");
                        activeSpans.add(0);
                    }
                    if (rowspan > 1) {
                        activeValues.set(index, text);
                        activeSpans.set(index, rowspan - 1);
                    }
                }
                col += colspan;
            }
            maxCols = Math.max(maxCols, currentRow.size());
            table.add(currentRow);
        }

        LogsUtils.info("Table rows processed. Total rows: " + table.size() + ", Max columns: " + maxCols);

        String[][] result = new String[table.size()][maxCols];
        for (int i = 0; i < table.size(); i++) {
            List<String> row = table.get(i);
            for (int j = 0; j < maxCols; j++) {
                result[i][j] = j < row.size() ? row.get(j) : "";
            }
        }
        LogsUtils.info("Table rows successfully converted to a 2D array.");
        return result;
    }

    @Step("Parse span value: {value}")
    private static int parseSpan(String value) {
        if (value == null || value.trim().isEmpty()) return 1;
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            LogsUtils.warn("Failed to parse span value: " + value + ". Defaulting to 1.");
            return 1;
        }
    }
}

