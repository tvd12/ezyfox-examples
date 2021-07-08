package com.tvd12.ezyfox.example.msgpack.test;

public class JsonExample {
    public static void main(String[] args) {
        final String jsonObject =
            "{\"userId\":300,\"transferredMoney\": 100}";
        System.out.println(jsonObject.length());

        final String jsonArray = "[300, 100]";
        System.out.println(jsonArray.length());
    }
}
