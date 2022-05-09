package com.tvd12.quick.rpc.examples.hello_world.data;

import com.tvd12.ezyfox.binding.annotation.EzyObjectBinding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EzyObjectBinding
public class GreetResponse {

    protected String message;

}
