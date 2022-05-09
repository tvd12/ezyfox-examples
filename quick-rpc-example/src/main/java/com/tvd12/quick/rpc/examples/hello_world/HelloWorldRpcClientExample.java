package com.tvd12.quick.rpc.examples.hello_world;

import com.tvd12.quick.rpc.client.QuickRpcClient;
import com.tvd12.quick.rpc.examples.hello_world.data.GreetRequest;
import com.tvd12.quick.rpc.examples.hello_world.data.GreetResponse;

public class HelloWorldRpcClientExample {

    public static void main(String[] args) throws Exception {
        QuickRpcClient client = QuickRpcClient.builder()
            .scan("com.tvd12.quick.rpc.examples.hello_world.data")
            .build();
        GreetResponse response = client.call(new GreetRequest("World"), GreetResponse.class);
        System.out.println(response.getMessage());
    }

}
