package com.freetax.utils;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author zhurui
 * @Date 2017/11/14 14:26
 */
public class PropertiesLoader {

    private static Properties properties = null;

    static {
        try {
            properties = PropertiesLoaderUtils.loadAllProperties("web/config.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        return properties.getProperty(key);
    }
}
