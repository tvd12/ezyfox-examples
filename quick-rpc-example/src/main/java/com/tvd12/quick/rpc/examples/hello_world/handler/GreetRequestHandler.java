package com.tvd12.quick.rpc.examples.hello_world.handler;

import com.tvd12.quick.rpc.examples.hello_world.data.GreetRequest;
import com.tvd12.quick.rpc.examples.hello_world.data.GreetResponse;
import com.tvd12.quick.rpc.server.annotation.RpcRequestHandled;
import com.tvd12.quick.rpc.server.entity.RpcRequest;
import com.tvd12.quick.rpc.server.entity.RpcResponse;
import com.tvd12.quick.rpc.server.handler.RpcAbstractRequestHandler;

@RpcRequestHandled("Greet")
public class GreetRequestHandler extends RpcAbstractRequestHandler<GreetRequest> {

    @Override
    public void handle(RpcRequest<GreetRequest> request, RpcResponse response) {
        response.write(new GreetResponse("Greet " + request.getData().getWho() + "!"));
    }

}
