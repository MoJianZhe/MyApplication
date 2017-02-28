package com.dyoon.myapplication.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by jun on 2016/12/3.
 */

public class AndroidTestActivity extends Activity {
    private static final String TAG = "AndroidTestActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: " + String.valueOf("hello"));


    }
}
