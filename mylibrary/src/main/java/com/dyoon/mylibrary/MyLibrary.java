package com.dyoon.mylibrary;

import android.util.Log;

/**
 * Created by jun on 2017/1/10.
 */

public class MyLibrary {
    private static final String TAG = "MyLibrary";
    private String name;
    private int age;

    public void sayHello() {
        Log.i(TAG, "sayHello: hello library");
    }

    public static void test() {
        Log.i(TAG, "test: hello world");
    }
}
