package com.arkui.fz_tools.utils;


import android.util.Log;


/**
 * This class wraps around Android Log class to add additional thread name/id
 * information is desirable.
 */
public class LogUtil {

    final static String TAG = "FZ";
    public static boolean DEBUG_MODE = true;

    public static String getPrefix() {
        // Show thread info if in debugging mode
        if (DEBUG_MODE)
            return "[" + Thread.currentThread().getName() + "-"
                    + Thread.currentThread().getId() + "] ";
        else
            return "";
    }

    public static void e(String message) {
        if (DEBUG_MODE)
            Log.e(TAG, getPrefix() + message);
    }

    public static void e(String message, Exception e) {
        if (DEBUG_MODE)
            Log.e(TAG, getPrefix() + message, e);
    }

    public static void w(String message) {
        if (DEBUG_MODE)
            Log.w(TAG, getPrefix() + message);
    }

    public static void i(String message) {
        if (DEBUG_MODE)
            Log.i(TAG, getPrefix() + message);
    }

    public static void d(String message) {
        if (DEBUG_MODE)
            Log.d(TAG, getPrefix() + message);
    }

    public static void v(String message) {
        if (DEBUG_MODE)
            Log.v(TAG, getPrefix() + message);
    }
}
