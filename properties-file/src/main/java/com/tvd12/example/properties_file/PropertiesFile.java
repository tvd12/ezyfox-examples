package com.tvd12.example.properties_file;

import com.tvd12.properties.file.reader.MultiFileReader;

import java.util.Properties;

public class PropertiesFile {

    public static void main(String[] args) {
        Properties properties = new MultiFileReader()
            .read("application.properties");
        System.out.println("properties: " + properties);

        Properties propertiesAlpha = new MultiFileReader("alpha")
            .read("application.properties");
        System.out.println("properties alpha: " + propertiesAlpha);

        Properties yamlProperties = new MultiFileReader()
            .read("application.yaml");
        System.out.println("yaml properties: " + yamlProperties);

        Properties yamlPropertiesAlpha = new MultiFileReader("alpha")
            .read("application.yaml");
        System.out.println("yaml properties alpha: " + yamlPropertiesAlpha);
    }

}
