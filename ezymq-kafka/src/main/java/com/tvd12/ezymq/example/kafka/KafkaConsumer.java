package com.tvd12.ezymq.example.kafka;

import com.tvd12.ezymq.kafka.EzyKafkaProxy;

public class KafkaConsumer {

    public static void main(String[] args) throws Exception {
        EzyKafkaProxy.builder()
            .scan("com.tvd12.ezymq.example.kafka")
            .build();
        while (true) {
            Thread.sleep(1000);
        }
    }

}
