package utilities.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlServerUtils implements AutoCloseable {
    private Connection connection;
    private final String url;

    private static final String URL_TEMPLATE =
            "jdbc:sqlserver://%s:1433;"
                    + "database=%s;"
                    + "user=%s;"
                    + "password=%s;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;";

    public SqlServerUtils() {
        this.url = String.format(
                URL_TEMPLATE,
                PropertiesUtils.getProperty("SqlServerName"),
                PropertiesUtils.getProperty("database"),
                PropertiesUtils.getProperty("SqlUserName"),
                PropertiesUtils.getProperty("SqlPassword")
        );

        try {
            this.connection = DriverManager.getConnection(this.url);
            LogsUtils.info("Connected to SQL Server: " + this.url);
        } catch (SQLException e) {
            LogsUtils.error("Failed to connect to SQL Server: " + e.getMessage());
        }
    }


    public List<Map<String, String>> readQuery(String sql) {
        List<Map<String, String>> results = new ArrayList<>();
        try (
                ResultSet resultSet = connection.createStatement().executeQuery(sql);
        ) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, String> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String value = resultSet.getString(i);
                    row.put(columnName, value);
                }
                results.add(row);
            }
        } catch (SQLException e) {
            LogsUtils.error("Failed to execute read query: " + sql + ". Error: " + e.getMessage());
        }
        return results;
    }


    public int writeQuery(String sql) {
        try (Statement stmt = connection.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql);
            LogsUtils.info("Write query executed: " + sql + ". Rows affected: " + rowsAffected);
            return rowsAffected;
        } catch (SQLException e) {
            LogsUtils.error("Failed to execute write query: " + sql + ". Error: " + e.getMessage());
            return -1;
        }
    }


    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
                LogsUtils.info("SQL Server connection closed.");
            } catch (SQLException e) {
                LogsUtils.error("Failed to close SQL Server connection: " + e.getMessage());
            }
        }
    }
}


