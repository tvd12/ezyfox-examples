package com.tvd12.quick.rpc.examples.hello_world.controller;

import com.tvd12.quick.rpc.examples.hello_world.data.GreetRequest;
import com.tvd12.quick.rpc.examples.hello_world.data.GreetResponse;
import com.tvd12.quick.rpc.server.annotation.Rpc;
import com.tvd12.quick.rpc.server.annotation.RpcController;
import com.tvd12.quick.rpc.server.entity.RpcRequest;
import com.tvd12.quick.rpc.server.entity.RpcResponse;
import com.tvd12.quick.rpc.server.entity.RpcSession;

@RpcController
public class HelloController {

    @Rpc("greet")
    public GreetResponse greet(GreetRequest request, RpcRequest<?> r, RpcResponse response, RpcSession session) {
        return new GreetResponse("Hello " + request.getWho() + "!");
    }

}
