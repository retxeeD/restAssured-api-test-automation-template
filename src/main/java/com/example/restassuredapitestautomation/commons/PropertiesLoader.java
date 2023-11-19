package com.example.restassuredapitestautomation.commons;

import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
    private static final String APPLICATION_PROPERTIES ="/application.properties";
    private static PropertiesLoader instance = null;
    private Properties properties;

    private PropertiesLoader() throws IOException {}

    public static PropertiesLoader getInstance(String path){
        if (instance == null){
            try {
                instance = new PropertiesLoader();
                instance.load(path);
            } catch (IOException e){
                throw new IllegalArgumentException("Error to process application.properties");
            }
        }
        return instance;
    }

    public static PropertiesLoader getInstance(){
        if (instance == null){
            try {
                instance = new PropertiesLoader();
                instance.load(APPLICATION_PROPERTIES);
            } catch (IOException e) {
                throw new IllegalArgumentException("Error to process application.properties");
            }
        }
        return instance;
    }

    public String getValue(String path, String key){
        try {
            load(path);
            return properties.getProperty(key);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public String getValue(String key){
        try {
            return properties.getProperty(key);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private void load(String path) {
        try {
            properties = new Properties();
            properties.load(getClass().getResourceAsStream(path));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public String getValueWithEnvironment(String key){
        try {
            String environment = getValueEnvironment(Constant.ENVIRONMENT).toLowerCase();
            return properties.getProperty(environment.concat(".").concat(key));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public String getValueEnvironment(String key){
        try {
            String value = System.getenv(key);
            if (null != value){
                return value;
            }
            return properties.getProperty(key);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
