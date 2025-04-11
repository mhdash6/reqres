package utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerFactoryUtility extends BaseUtility {

    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}