package com.tvd12.ezyfox.example.msgpack.test;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class BytesPrinter {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(ByteBuffer.allocate(4).putInt(300).array()));
        System.out.println(Arrays.toString(ByteBuffer.allocate(4).putInt(100).array()));

        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[] {0, 1, 44, 100});
        System.out.println(byteBuffer.getInt());
    }

}
