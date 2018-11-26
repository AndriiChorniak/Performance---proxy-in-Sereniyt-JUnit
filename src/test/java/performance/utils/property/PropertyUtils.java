package performance.utils.property;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by andriy.chornyak on 11/21/2018.
 */
public class PropertyUtils {
    public static final Properties ENDPOINT_PROPERTY;

    static {
        FileInputStream fileInputStream;
        ENDPOINT_PROPERTY = new Properties();

        try {
            fileInputStream = new FileInputStream("src/test/resources/endpoints.properties");
            ENDPOINT_PROPERTY.load(fileInputStream);
        } catch (IOException e) {
            System.err.println("Property file is missing");
        }
    }
}
