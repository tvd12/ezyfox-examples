package com.tvd12.ezymq.example.kafka;

import com.tvd12.ezymq.kafka.EzyKafkaConsumer;
import com.tvd12.ezymq.kafka.EzyKafkaProxy;

public class KafkaConsumer {

    public static void main(String[] args) throws Exception {
        EzyKafkaProxy kafkaProxy = EzyKafkaProxy.builder()
            .scan("com.tvd12.ezymq.example.kafka")
            .build();
        EzyKafkaConsumer consumer = kafkaProxy.getConsumer("hello-world");
        consumer.start();
        while (true) {
            Thread.sleep(1000);
        }
    }

}
