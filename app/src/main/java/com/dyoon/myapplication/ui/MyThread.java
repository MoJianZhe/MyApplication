package com.dyoon.myapplication.ui;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import org.xutils.x;

/**
 * Created by jun on 2016/11/3.
 */

public class MyThread extends Thread {
    public Handler handler;
    @Override
    public void run() {
        Looper.prepare();//初始化Looper
         handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {

                switch (msg.what) {//如果传过来的是setEmptyMessage(),那么所有的case都会执行
                    case 1:
                        Toast.makeText(x.app(),"子线程收到消息",Toast.LENGTH_SHORT).show();
                        break;


                    case 2:
                        String name= (String) msg.obj;
                        Toast.makeText(x.app(),"名称是"+name,Toast.LENGTH_SHORT).show();
                        Log.e("number",String.valueOf(Thread.activeCount()));
                        break;
                    default:
                        break;
                }
            }
        };
        Looper.loop();//启动消息循环,监听消息，代码不会执行完所以线程也不会死亡。


    }
}
