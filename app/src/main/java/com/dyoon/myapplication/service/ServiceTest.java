package com.dyoon.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by jun on 2016/12/29.
 */

public class ServiceTest extends Service {
    private static final String TAG = "ServiceTest";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }

    //第一次创建的时候调用
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: myservice is create");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: Myservice is start");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "run: 调用完后，就停止服务");
                stopSelf();
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: mySevice is stoped");
    }

    public void stop() {
        stopSelf();
    }
}
