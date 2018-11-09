package com.neusoft.ehr.utils;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClassPropertiesUtils {
    private static Properties properties = null;

    public ClassPropertiesUtils() {
    }

    static {
        properties = new Properties();
        InputStream in = ClassPropertiesUtils.class.getClassLoader().getResourceAsStream("class.properties");
        try {
            properties.load(in);
        } catch (IOException var2) {
            var2.printStackTrace();
        }
    }
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }


}
