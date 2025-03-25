package utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;

public class Tables  extends BaseUtility {

    public static String[] getHeadersNames(By... thLocators) {
        List<String> headerNames = new ArrayList<>();
        for (By locator : thLocators) {
            for (WebElement element : driver.findElements(locator)) {
                headerNames.add(element.getText());
            }
        }
        return headerNames.toArray(new String[0]);
    }

    public static String[][] getTableRows(By rowLocator, By cellLocator) {
        List<WebElement> rows = driver.findElements(rowLocator);
        if (rows.isEmpty()) return new String[0][];

        List<Object[]> activeRowSpans = new ArrayList<>();
        List<List<String>> tableData = new ArrayList<>();
        int maxColumns = 0;

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(cellLocator);
            List<String> currentRow = new ArrayList<>();

            List<Object[]> nextRowSpans = new ArrayList<>();
            for (Object[] span : activeRowSpans) {
                int col = (int) span[0];
                String text = (String) span[1];
                int remaining = (int) span[2];


                while (currentRow.size() <= col) currentRow.add(null);

                currentRow.set(col, text);
                if (remaining > 1) {
                    nextRowSpans.add(new Object[]{col, text, remaining - 1});
                }
            }
            activeRowSpans = nextRowSpans;

            int col = 0;
            for (WebElement cell : cells) {

                while (col < currentRow.size() && currentRow.get(col) != null) col++;

                String text = cell.getText();
                int colspan = parseSpan(cell.getAttribute("colspan"), 1);
                int rowspan = parseSpan(cell.getAttribute("rowspan"), 1);


                while (currentRow.size() < col + colspan) currentRow.add(null);
                for (int i = 0; i < colspan; i++) {
                    currentRow.set(col + i, text);
                }


                if (rowspan > 1) {
                    activeRowSpans.add(new Object[]{col, text, rowspan - 1});
                }
                col += colspan;
            }

            maxColumns = Math.max(maxColumns, currentRow.size());
            tableData.add(currentRow);
        }


        return normalizeRows(tableData, maxColumns);
    }

    private static int parseSpan(String value, int defaultValue) {
        if (value == null || value.isEmpty()) return defaultValue;
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private static String[][] normalizeRows(List<List<String>> tableData, int maxColumns) {
        String[][] result = new String[tableData.size()][];
        for (int i = 0; i < tableData.size(); i++) {
            List<String> row = tableData.get(i);
            result[i] = new String[maxColumns];
            for (int j = 0; j < maxColumns; j++) {
                result[i][j] = (j < row.size()) ? row.get(j) : "";
            }
        }
        return result;
    }

}
