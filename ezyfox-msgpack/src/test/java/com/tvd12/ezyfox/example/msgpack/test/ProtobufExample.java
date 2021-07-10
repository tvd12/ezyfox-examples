package com.tvd12.ezyfox.example.msgpack.test;

import java.nio.ByteBuffer;

public class ProtobufExample {
    public static void main(String[] args) {
        final int value = 300;
        final byte[] bytes = serialize(300);
        System.out.println("serialize of " + value + ": " + toString(bytes));
        System.out.println("deserialize: " + deserialize(bytes));
    }

    private static int deserialize(byte[] bytes) {
        int answer = 0;
        for(int i = 0 ; i < bytes.length ; ++i) {
            answer += (bytes[i] & 0x7F) << i * 7;
        }
        return answer;
    }

    private static byte[] serialize(int value) {
        final byte[] valueBytes = ByteBuffer.allocate(4).putInt(value).array();
        int byteCount = 4;
        for (byte valueByte : valueBytes) {
            if ((valueByte & 0xFF) != 0) {
                break;
            }
            --byteCount;
        }
        final ByteBuffer byteBuffer = ByteBuffer.allocate(byteCount);
        int remainValue = value;
        for(int i = 0 ; i < byteCount - 1 ; ++i) {
            // 0x80 = 10000000
            byteBuffer.put((byte)(0x80 | (0x7F & remainValue)));
            remainValue >>= 7;
        }
        byteBuffer.put((byte)remainValue);
        return byteBuffer.array();
    }

    private static String toString(byte[] array) {
        final StringBuilder builder = new StringBuilder("[");
        for(int i = 0 ; i < array.length ; ++i) {
            final ByteBuffer buffer = ByteBuffer.allocate(4).put(new byte[] { 0, 0, 0, array[i]});
            buffer.flip();
            builder.append(buffer.getInt());
            if(i < array.length - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
