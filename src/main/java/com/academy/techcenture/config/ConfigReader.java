package com.academy.techcenture.config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    //This class we will have the following proprieties as private static
    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.Properties";


    //Empty private constructor (since the proprieties are static we can not have them in the constructor)
    private ConfigReader() {
    }


    static {
        try {
            FileInputStream inputStream = new FileInputStream(CONFIG_FILE_PATH);
            properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getProperty(String keyName){
        return properties.getProperty(keyName); //will return the value based on the key
    }


    public static void setProperty(String keyName, String value){


    }


}
