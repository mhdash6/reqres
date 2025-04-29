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
        Properties prop;
        try(DirectoryStream<Path> ds = Files.newDirectoryStream(Path.of(PROPERTIES_PATH))){
            boolean env=false;
            boolean test=false;
            for (Path path : ds) {
                if(env && test){
                    break;
                }
                try (InputStream inputStream = Files.newInputStream(path)) {
                    prop= new Properties();
                    prop.load(inputStream);
                    if ("true".equalsIgnoreCase(prop.getProperty("enabled"))) {
                        if (path.getFileName().toString().contains("env") && !env) {
                            properties.putAll(prop);
                            env = true;
                        } else if (path.getFileName().toString().contains("test") && !test) {
                            properties.putAll(prop);
                            test = true;
                        }
                    }
                }
            }
            LogsUtil.info("Properties loaded successfully");
        }
        catch(Exception e){
            LogsUtil.error("Failed to load properties file: " + e.getMessage());
        }

    }


    public static String getProperty(String key){
        if(properties.isEmpty()){
            loadProperties();
        }
        String value= properties.getProperty(key);
        if(value==null){
            LogsUtil.error("Property not found: " + key);
        }
        return value;
    }
}
