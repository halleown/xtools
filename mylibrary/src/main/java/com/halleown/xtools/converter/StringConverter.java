package com.halleown.xtools.converter;

import java.io.UnsupportedEncodingException;

/**
 * 文本字符串转换
 */
public class StringConverter {

    /**
     * 文本字符串转byte类型
     *
     * @param str 要转换的字符串
     * @return 转换后的字节数组
     */
    public static byte[] stringToByte(String str) {
        if (str == null) {
            return null;
        }
        try {
            // 使用字符串 "UTF-8" 来代替 StandardCharsets.UTF_8，以兼容 API 19 以下的版本
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            // UTF-8 是 Java 虚拟机必须支持的标准字符集，理论上这个异常永远不会被抛出。
            // 但为了代码的严谨性，还是需要处理它。
            e.printStackTrace();
            // 在异常情况下，可以返回 null 或者一个空数组，取决于你的业务逻辑。
            return null;
        }
    }

}
