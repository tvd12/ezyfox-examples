package com.tvd12.ezymq.example.kafka;

import com.tvd12.ezymq.kafka.annotation.EzyKafkaHandler;
import com.tvd12.ezymq.kafka.handler.EzyKafkaMessageHandler;

@EzyKafkaHandler(topic = "hello-world", command = "hello")
public class HelloMessageHandler implements EzyKafkaMessageHandler<String> {
    @Override
    public void process(String message) throws Exception {
        System.out.println("consum message: " + message);
    }
}
