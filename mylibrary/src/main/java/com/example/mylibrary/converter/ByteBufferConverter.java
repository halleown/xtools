package com.example.mylibrary.converter;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ByteBufferConverter {
    /**
     * ByteBuffer转文本字符串
     *
     * @param byteBuffer
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String byteBufferToString(ByteBuffer byteBuffer) {
        byteBuffer.rewind();
        byte[] bytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * 字符串转ByteBuffer
     *
     * @param str
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static ByteBuffer stringToByteBuffer(String str) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
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
