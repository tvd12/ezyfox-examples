package com.tvd12.ezyfox.example.msgpack;

import com.tvd12.ezyfox.binding.EzyBindingContext;
import com.tvd12.ezyfox.binding.codec.EzyBindingEntityCodec;
import com.tvd12.ezyfox.codec.EzyEntityCodec;
import com.tvd12.ezyfox.codec.MsgPackSimpleDeserializer;
import com.tvd12.ezyfox.codec.MsgPackSimpleSerializer;
import com.tvd12.ezyfox.io.EzyPrints;

import java.util.Arrays;

public class MsgpackExample {
    public static void main(String[] args) {
        final EzyBindingContext bindingContext = EzyBindingContext.builder()
            .scan("com.tvd12.ezyfox.example.msgpack")
            .build();
        final EzyEntityCodec codec = EzyBindingEntityCodec.builder()
            .marshaller(bindingContext.newMarshaller())
            .unmarshaller(bindingContext.newUnmarshaller())
            .messageSerializer(new MsgPackSimpleSerializer())
            .messageDeserializer(new MsgPackSimpleDeserializer())
            .build();
        final Transfer transfer = new Transfer(300, 100);

        final byte[] serializedBytes = codec.serialize(transfer);

        System.out.println(serializedBytes.length);
        System.out.println(Arrays.toString(serializedBytes));
        System.out.println(EzyPrints.printHex(serializedBytes));

        final Transfer deserializedObj = codec.deserialize(serializedBytes, Transfer.class);
        System.out.println(deserializedObj);
    }
}
