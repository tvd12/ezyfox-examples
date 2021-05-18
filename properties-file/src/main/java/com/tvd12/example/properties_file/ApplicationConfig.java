package com.tvd12.example.properties_file;

import lombok.Data;

@Data
public class ApplicationConfig {
    private String hello;
    private Database database;
    private Cache cache;

    @Data
    public static class Database {
        private String name;
        private String host;
    }

    @Data
    public static class Cache {
        private String name;
        private String host;
    }
}
