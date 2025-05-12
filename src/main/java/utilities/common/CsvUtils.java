package utilities.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import io.qameta.allure.Step;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CsvUtils {


    private static final CsvMapper CSV_MAPPER = new CsvMapper();

    private CsvUtils() {

    }


    public static List<Map<String, String>> readCsv(String filePath, boolean hasHeader) {
        File file = new File(filePath);
        CsvSchema schema;
        try {
            if (hasHeader) {
                schema = CsvSchema.emptySchema().withHeader();
            } else {
                try (BufferedReader br = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
                    String firstLine = br.readLine();
                    if (firstLine == null || firstLine.isEmpty()) {
                        return Collections.emptyList();
                    }
                    String[] cols = firstLine.split(",", -1);
                    CsvSchema.Builder b = CsvSchema.builder().setUseHeader(false);
                    for (int i = 0; i < cols.length; i++) {
                        b.addColumn("col" + (i + 1));
                    }
                    schema = b.build();
                }
            }
            try (MappingIterator<Map<String, String>> reader = CSV_MAPPER
                    .readerFor(new TypeReference<Map<String, String>>() {})
                    .with(schema)
                    .readValues(file)) {
                return reader.readAll();
            }
        } catch (IOException e) {
            LogsUtils.error("Failed to read CSV file: " + filePath + ". Error: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public static <T> List<T> readCsv(String path, boolean hasHeader, Class<T> clazz) {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper
                .schemaFor(clazz)
                .withSkipFirstDataRow(hasHeader);
        try {
            MappingIterator<T> it = mapper
                    .readerFor(clazz)
                    .with(schema)
                    .readValues(new File(path));
            return it.readAll();
        } catch (IOException e) {
            LogsUtils.error("Failed to read CSV file: " + path + ". Error: " + e.getMessage());
            return Collections.emptyList();
        }
    }




    public static void writeCsvFromListOfArrays(String filePath, List<String[]> data, String[] headers) {
        LogsUtils.info("Writing CSV data to path: " + filePath);
        CsvSchema schema = CsvSchema.builder()
                .setUseHeader(true)
                .addColumns(Arrays.asList(headers), CsvSchema.ColumnType.STRING)
                .build();
        try {
            CSV_MAPPER.writer(schema)
                    .writeValues(Paths.get(filePath).toFile())
                    .writeAll(data);
            LogsUtils.info("Successfully wrote CSV data to: " + filePath);
        } catch (IOException e) {
            LogsUtils.error("Failed to write CSV file: " + filePath + ". Error: " + e.getMessage());
        }
    }
}

