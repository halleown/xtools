package com.halleown.xtools.converter;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class ByteBufferConverter {
    /**
     * ByteBuffer转文本字符串
     *
     * @param byteBuffer
     * @return
     */
    public static String byteBufferToString(ByteBuffer byteBuffer) {
        byteBuffer.rewind();
        byte[] bytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(bytes);
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 字符串转ByteBuffer
     *
     * @param str
     * @return
     */
    public static ByteBuffer stringToByteBuffer(String str) {
        byte[] bytes;
        try {
            bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        return ByteBuffer.wrap(bytes);
    }

    /**
     * 十六进制字符串转ByteBuffer
     *
     * @param data 十六进制数据，如：5A AA 5C
     * @return ByteBuffer类型数据
     */
    public static ByteBuffer hexToByteBuffer(String data) {
        String[] hex = data.split(" ");
        ByteBuffer buffer = ByteBuffer.allocate(hex.length);
        for (int i = 0; i < hex.length; i++) {
            buffer.put(i, (byte) HexConverter.hexToDecimal(hex[i]));
        }
        return buffer;
    }
}
