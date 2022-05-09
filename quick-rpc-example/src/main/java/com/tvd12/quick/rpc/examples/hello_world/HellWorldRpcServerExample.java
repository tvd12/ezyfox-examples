package com.tvd12.quick.rpc.examples.hello_world;

import com.tvd12.quick.rpc.server.QuickRpcServer;
import com.tvd12.quick.rpc.server.asm.RpcRequestHandlerImplementer;
import com.tvd12.quick.rpc.server.setting.QuickRpcSettings;

public class HellWorldRpcServerExample {

    public static void main(String[] args) throws Exception {
        RpcRequestHandlerImplementer.setDebug(true);
        QuickRpcSettings settings = QuickRpcSettings.builder()
            .username("admin")
            .password("admin")
            .build();
        QuickRpcServer server = new QuickRpcServer(settings)
            .scan("com.tvd12.quick.rpc.examples.hello_world");
        server.start();
    }

}
