package com.mindful;

import android.util.Log;

/**
 * Created by kyle on 7/7/14.
 */
public class Logger {

    public static void i(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        Log.d(tag, msg);
    }
}
