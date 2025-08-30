package com.example.mylibrary.converter;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.nio.charset.StandardCharsets;

public class ByteConverter {

    /**
     * byte类型转文本字符串
     *
     * @param data
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String byteToString(byte[] data) {
        return new String(data, StandardCharsets.UTF_8);
    }

    /**
     * byte转十六进制字符串
     *
     * @param binary
     * @return
     */
    public static String byteToHex(byte[] binary) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < binary.length; i++) {
            sb.append(String.format("%02X", binary[i] & 0xFF));
        }
        return sb.toString();
    }


    /**
     * 二进制转八进制
     *
     * @param binary
     */
    public static int binaryToOctal(byte[] binary) {
        return 0;
    }

    /**
     * 二进制转十进制
     *
     * @param binary
     */
    public static int binaryToDecimal(byte[] binary) {
        return 0;
    }

    /**
     * 二进制转十六进制
     *
     * @param binary
     */
    public static int binaryToHex(byte[] binary) {
        return 0;
    }
}
