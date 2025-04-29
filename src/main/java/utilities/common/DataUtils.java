package utilities.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import io.qameta.allure.Step;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DataUtils {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final CsvMapper CSV_MAPPER = new CsvMapper();

    private DataUtils() {
        // Prevent instantiation
    }


    public static Object readJson(String path) {
        LogsUtil.info("Reading JSON file from path: " + path);
        try {
            Path filePath = Paths.get(path);
            JsonNode root = JSON_MAPPER.readTree(filePath.toFile());
            if (root.isArray()) {
                if (root.get(0).isArray()) {
                    List<List<Object>> data = JSON_MAPPER.convertValue(root, new TypeReference<List<List<Object>>>() {});
                    LogsUtil.info("Successfully read JSON Array of Arrays from: " + path);
                    return data;
                }
                else {
                    List<Map<String, Object>> data = JSON_MAPPER.convertValue(root, new TypeReference<List<Map<String, Object>>>() {});
                    LogsUtil.info("Successfully read JSON Array from: " + path);
                    return data;
                }
            }
            else if (root.isObject()) {
                Map<String, Object> data = JSON_MAPPER.convertValue(root, new TypeReference<Map<String, Object>>() {});
                LogsUtil.info("Successfully read JSON Object from: " + path);
                return data;
            }
            else {
                LogsUtil.error("Unknown JSON structure at: " + path);
                return Collections.emptyMap();
            }
        } catch (IOException e) {
            LogsUtil.error("Failed to read JSON file: " + path + ". Error: " + e.getMessage());
            return Collections.emptyMap();
        }
    }



    public static List<Map<String, String>> readCsv(String filePath, boolean hasHeader) {
        File file = new File(filePath);
        try {
            CsvSchema schema = hasHeader
                    ? CsvSchema.emptySchema().withHeader()
                    : CsvSchema.emptySchema().withoutHeader();

            try (MappingIterator<Map<String, String>> reader  =
                         CSV_MAPPER.readerFor(new TypeReference<Map<String, String>>() {})
                                 .with(schema)
                                 .readValues(file)) {
                return reader.readAll();
            }
        } catch (IOException e) {
            System.err.println("Failed to read CSV: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public static void writeJson(String path, Object data) {
        LogsUtil.info("Writing JSON data to path: " + path);
        try {
            JSON_MAPPER.writerWithDefaultPrettyPrinter()
                    .writeValue(Paths.get(path).toFile(), data);
            LogsUtil.info("Successfully wrote JSON data to: " + path);
        } catch (IOException e) {
            LogsUtil.error("Failed to write JSON file: " + path + ". Error: " + e.getMessage());
        }
    }

    @Step("Write CSV data to path: {filePath}")
    public static void writeCsvFromListOfArrays(String filePath, List<String[]> data, String[] headers) {
        LogsUtil.info("Writing CSV data to path: " + filePath);
        CsvSchema schema = CsvSchema.builder()
                .setUseHeader(true)
                .addColumns(Arrays.asList(headers), CsvSchema.ColumnType.STRING)
                .build();

        try {
            CSV_MAPPER.writer(schema)
                    .writeValues(Paths.get(filePath).toFile())
                    .writeAll(data);
            LogsUtil.info("Successfully wrote CSV data to: " + filePath);
        } catch (IOException e) {
            LogsUtil.error("Failed to write CSV file: " + filePath + ". Error: " + e.getMessage());
        }
    }
}

