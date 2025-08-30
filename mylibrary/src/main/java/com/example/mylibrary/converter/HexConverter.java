package com.example.mylibrary.converter;

/**
 * 十六进制转换
 */
public class HexConverter {

    /**
     * 十六进制转二进制
     */
    public static String hexToBinary(String hex) {
        if (hex.startsWith("0x") || hex.startsWith("0X")) {
            hex = hex.substring(2);
        }

        try {
            // 将十六进制字符串转换为整数
            int decimal = Integer.parseInt(hex, 16);
            // 将整数转换为二进制字符串
            return Integer.toBinaryString(decimal);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("无效的十六进制字符串: " + hex);
        }
    }

    /**
     * 十六进制转八进制
     */
    public static void hexToOctal(String hex) {

    }

    /**
     * 十六进制转十进制
     *
     * @param hex
     */
    public static int hexToDecimal(String hex) {
        return Integer.parseInt(hex.replace("0x", ""), 16);
    }

}
