package com.joykade.xtools.utils;

import android.util.Log;

import com.joykade.xtools.BuildConfig;

public class LogUtil {
    public static boolean isDebug = BuildConfig.DEBUG;
    private static final String TAG = "xtools";
    /**
     * 获取相关数据:类名,方法名,行号等.用来定位行
     */
    private static String getFunctionName() {

        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts != null) {
            for (StackTraceElement st : sts) {
                if (st.isNativeMethod()) {
                    continue;
                }
                if (st.getClassName().equals(Thread.class.getName())) {
                    continue;
                }
                if (st.getClassName().equals(TAG)) {
                    continue;
                }
                return "[ Thread:" + Thread.currentThread().getName() + ", at " + st.getClassName() + "." + st.getMethodName()
                        + "(" + st.getFileName() + ":" + st.getLineNumber() + ")" + " ]";
            }
        }
        return null;
    }


    public static void v(String msg) {
        if (isDebug) {
            Log.v(TAG, getMsgFormat(msg));
        }
    }

    public static void v(String tag, String msg) {
        if (isDebug) {
            Log.v(tag, getMsgFormat(msg));
        }
    }


    public static void d(String msg) {
        if (isDebug) {
            Log.d(TAG, getMsgFormat(msg));
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            int strLength = msg.length();
            int start = 0;
            int end = 4048;
            for (int i = 0; i < 100; i++) {
                //剩下的文本还是大于规定长度则继续重复截取并输出
                if (strLength > end) {
                    Log.d(tag + i, msg.substring(start, end));
                    start = end;
                    end = end + 4048;
                } else {
                    Log.d(tag, msg.substring(start, strLength));
                    break;
                }
            }
        }
    }


    public static void i(String msg) {
        if (isDebug) {
            Log.i(TAG, getMsgFormat(msg));
        }
    }

    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, getMsgFormat(msg));
        }
    }


    public static void w(String msg) {
        if (isDebug) {
            Log.w(TAG, getMsgFormat(msg));
        }
    }

    public static void w(String tag, String msg) {
        if (isDebug) {
            Log.w(tag, getMsgFormat(msg));
        }
    }


    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, getMsgFormat(msg));
        }
    }

    public static void e(String msg, Throwable e) {
        if (isDebug) {
            Log.e(TAG,  msg, e);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, getMsgFormat(msg));
        }
    }

    /**
     * 输出格式定义
     */
    private static String getMsgFormat(String msg) {
        return msg + " ;" + getFunctionName();
    }
}
