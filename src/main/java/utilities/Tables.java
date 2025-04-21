package utilities;

import static utilities.SimpleElementActions.findAll; 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Tables {

    public static String[] getHeadersNames(WebDriver driver, By... thLocators) {
        List<String> headers = new ArrayList<>();
        for (By locator : thLocators) {
            for (WebElement el : findAll(driver, locator)) { 
                headers.add(el.getText().trim());
            }
        }
        return headers.toArray(new String[0]);
    }

    public static String[][] getTableRows(By rowLocator, By cellLocator, WebDriver driver) {
        List<WebElement> rows = findAll(driver, rowLocator);
        if (rows.isEmpty()) return new String[0][];

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

        String[][] result = new String[table.size()][maxCols];
        for (int i = 0; i < table.size(); i++) {
            List<String> row = table.get(i);
            for (int j = 0; j < maxCols; j++) {
                result[i][j] = j < row.size() ? row.get(j) : "";
            }
        }
        return result;
    }

    private static int parseSpan(String value) {
        if (value == null || value.trim().isEmpty()) return 1;
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return 1;
        }
    }
}

