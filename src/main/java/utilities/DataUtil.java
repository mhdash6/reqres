package utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.csv.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class DataUtil  {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    private static final CsvMapper CSV_MAPPER = new CsvMapper();

    public static Map<String, Object> readJson(String path) {
        try {
            return JSON_MAPPER.readValue(new File(path), new TypeReference<Map<String, Object>>() {});
        } catch (IOException e) {
            System.err.println("Failed to read JSON: " + e.getMessage());
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
        try {
            JSON_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File(path), data);
        } catch (IOException e) {
            System.out.println("Failed to write JSON: " + e.getMessage());
        }
    }

//
//    public static void writeCsvFromListOfArrays(String filePath, List<String[]> data, String[] headers) {
//        CsvSchema schema = CsvSchema.builder()
//                .addColumns(Arrays.asList(headers), CsvSchema.ColumnType.STRING)
//                .setUseHeader(true)
//                .build();
//        try {
//            CSV_MAPPER.writer(schema).writeValues(new File(filePath)).writeAll(data);
//        } catch (IOException e) {
//            System.out.println("Failed to write CSV: " + e.getMessage());
//        }
//    }

}



