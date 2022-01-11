package infra.util.helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyValueHelper {

    static InputStream inputStream;

    public static String getConfigPropValueFromField(final String field) throws IOException {
        try {
            Properties properties = new Properties();
            String configPropFileName = "config.properties";

            inputStream = PropertyValueHelper.class.getClassLoader().getResourceAsStream(configPropFileName);

            if (inputStream == null)
                throw new FileNotFoundException("Property file: " + configPropFileName + "not found!");

            properties.load(inputStream);
            return properties.getProperty(field);
        } catch (Exception e) {
            System.out.println("Exception when trying to read from config properties file: "
                + e.getMessage());
            return "";
        } finally {
            assert inputStream != null;
            inputStream.close();
        }
    }
}
