package com.dyoon.myapplication.ui;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import org.xutils.x;


/**
 * Created by jun on 2016/11/1.
 */

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);

        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int heapSize=manager.getMemoryClass();
        Log.i(TAG, "onCreate: 当前手机允许app的最大内存是："+heapSize+"M");
        Log.i(TAG, "onCreate: "+manager.getDeviceConfigurationInfo().toString());

    }
}
