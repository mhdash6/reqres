package utilities.common;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.DataFormatter;


import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelUtils implements Closeable {
    private final String filePath;
    private final Workbook workbook;
    private final Sheet sheet;
    private final DataFormatter formatter = new DataFormatter();
    private boolean dirty = false;

    public ExcelUtils(String filePath, String sheetName) {
        this.filePath = filePath;
        try (InputStream inp = new FileInputStream(filePath)) {
            this.workbook = WorkbookFactory.create(inp);
        } catch (IOException e ) {
            LogsUtils.error("Failed to open workbook from " + filePath + ": " + e.getMessage());
            throw new RuntimeException("Unable to open Excel file: " + filePath, e);
        }

        this.sheet = this.workbook.getSheet(sheetName);
        if (this.sheet == null) {
            try {
                this.workbook.close();
            } catch (IOException ex) {
                LogsUtils.error("Failed to close workbook after missing sheet: " + ex.getMessage());
            }
            LogsUtils.error("Sheet '" + sheetName + "' not found in file: " + filePath);
            throw new IllegalArgumentException("Sheet '" + sheetName + "' not found in file: " + filePath);
        }
    }

    public String getCellData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        if (row == null) return "";
        Cell cell = row.getCell(colNum);
        return (cell == null) ? "" : formatter.formatCellValue(cell);
    }

    public List<List<String>> getSheetData() {
        List<List<String>> data = new ArrayList<>();
        for (Row r : sheet) {
            List<String> rowData = new ArrayList<>();
            for (Cell c : r) {
                rowData.add(formatter.formatCellValue(c));
            }
            data.add(rowData);
        }
        return data;
    }


    public void setCellData(int rowNum, int colNum, Object value) {
        Row row = sheet.getRow(rowNum);
        if (row == null) row = sheet.createRow(rowNum);

        Cell cell = row.getCell(colNum);
        if (cell == null) cell = row.createCell(colNum);

        if (value instanceof Number) {
            cell.setCellValue(((Number) value).doubleValue());
        } else if (value instanceof Date) {
            CreationHelper helper = workbook.getCreationHelper();
            CellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(helper.createDataFormat().getFormat("yyyy-MM-dd"));
            cell.setCellValue((Date) value);
            cell.setCellStyle(dateStyle);
        } else {
            cell.setCellValue(String.valueOf(value));
        }

        dirty = true;
    }


    public void save() {
        if (!dirty) return;
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
            dirty = false;
        } catch (IOException e) {
            LogsUtils.error("Failed to save workbook to " + filePath + ": " + e.getMessage());
        }
    }

    @Override
    public void close() {
        try {
            save();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                LogsUtils.error("Failed to close workbook: " + e.getMessage());
            }
        }
    }
}


