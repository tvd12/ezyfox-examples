package com.tvd12.ezymq.example.kafka;

import com.tvd12.ezymq.kafka.EzyKafkaProducer;
import com.tvd12.ezymq.kafka.EzyKafkaProxy;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class KafkaProducer {

    public static void main(String[] args) {
        EzyKafkaProxy kafkaProxy = EzyKafkaProxy.builder()
            .build();
        EzyKafkaProducer producer = kafkaProxy.getProducer("hello-world");
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            producer.send("hello", "Hello World");
        }, 1, 3, TimeUnit.SECONDS);
    }

}
