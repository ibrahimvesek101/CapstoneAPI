package ibrahim_API.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * reads the properties file configuration.properties
 */
public class ConfigurationReader {

    private static Properties properties;
    private static FileInputStream input;
    private static FileOutputStream output;
    private static String path = "config.properties";

    static {

        try {

            input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static String get(String keyName) {
        return properties.getProperty(keyName);
    }

    public static void set(String keyName, String value) {
        properties.setProperty(keyName, value);

        try {
            output = new FileOutputStream(path);
            properties.store(output, null);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
