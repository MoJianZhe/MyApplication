package com.dyoon.myapplication.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * 自动开启线程，自动停止
 * Created by jun on 2016/12/29.
 */

public class MyIntentService extends IntentService {
    private static final String TAG = "MyIntentService";

    public MyIntentService() {
        this("MyIntentService");
    }
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "onHandleIntent: 当前线程的id 是："+Thread.currentThread().getId());

    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: intentService is stopped");
        super.onDestroy();

    }
}
