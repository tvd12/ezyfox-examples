package com.tvd12.example.properties_file;

import com.tvd12.properties.file.mapping.PropertiesMapper;
import com.tvd12.properties.file.reader.BaseFileReader;
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

        ApplicationConfig applicationConfig = new PropertiesMapper()
            .reader(new MultiFileReader("alpha"))
            .file("application.properties")
            .map(ApplicationConfig.class);
        System.out.println("applicationConfig alpha: " + applicationConfig);

        Properties yamlProperties = new BaseFileReader()
            .read("application.yaml");
        System.out.println("yaml properties: " + yamlProperties);

        Properties yamlPropertiesAlpha = new MultiFileReader("alpha")
            .read("application.yaml");
        System.out.println("yaml properties alpha: " + yamlPropertiesAlpha);

        ApplicationConfig applicationConfigYaml = new PropertiesMapper()
            .reader(new MultiFileReader("alpha"))
            .file("application.yaml")
            .map(ApplicationConfig.class);
        System.out.println("applicationConfigYaml alpha: " + applicationConfigYaml);
    }

}
