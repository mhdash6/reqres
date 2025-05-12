package utilities.common;


import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class PropertiesUtils {
    private static final String PROPERTIES_PATH = "src/main/resources/properties";
    private PropertiesUtils() {}
    private static final Properties properties= new Properties() ;

    public static void loadProperties() {
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(Path.of(PROPERTIES_PATH))) {
            boolean env = false, test = false, secret = false;

            for (Path path : ds) {
                if (env && test && secret) break;

                try (InputStream inputStream = Files.newInputStream(path)) {
                    Properties prop = new Properties();
                    prop.load(inputStream);

                    if ("true".equalsIgnoreCase(prop.getProperty("enabled"))) {
                        String fileName = path.getFileName().toString().toLowerCase();

                        if (fileName.toLowerCase().contains("env") && !env) {
                            properties.putAll(prop);
                            env = true;
                           LogsUtils.info("Loading environment properties from: " + path);
                        } else if (fileName.toLowerCase().contains("config") && !test) {
                            properties.putAll(prop);
                            test = true;
                           LogsUtils.info("Loading test properties from: " + path);

                        } else if (fileName.toLowerCase().contains("secret") && !secret) {
                            properties.putAll(prop);
                            secret = true;
                           LogsUtils.info("Loading secret properties from: " + path);
                        }
                    }
                } catch (Exception e) {
                    LogsUtils.error("Failed to read or parse file: " + path + " — " + e.getMessage());
                }
            }

            LogsUtils.info("Properties loaded successfully ");
        } catch (Exception e) {
            LogsUtils.error("Failed to load properties from directory: " + PROPERTIES_PATH + " — " + e.getMessage());
        }
    }



    public static String getProperty(String key){
        if(properties.isEmpty()){
            loadProperties();
        }
        String value= properties.getProperty(key);
        if(value==null){
            LogsUtils.error("Property not found: " + key);
        }
        return value;
    }
}
