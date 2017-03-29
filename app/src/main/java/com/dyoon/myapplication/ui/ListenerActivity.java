package com.dyoon.myapplication.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.dyoon.myapplication.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


/**
 * Created by jun on 2017/1/22.
 */
@ContentView(R.layout.activity_listener)
public class ListenerActivity extends Activity implements View.OnTouchListener{
    private static final String TAG = "ListenerActivity";
    @ViewInject(R.id.btn_long_press)
    private Button listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            String test = savedInstanceState.getString("name");
            Log.i(TAG, "onCreate: " + test);
        }
        x.view().inject(this);
        listener.setOnTouchListener(this    );
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    Log.i(TAG, "onTouch: ");
                    Log.i(TAG, "onTouch: 手指按下屏幕");
                    break;
                case MotionEvent.ACTION_UP:
                    Log.i(TAG, "onTouch: 手指离开屏幕");
                    break;
            }
        return false;//拦截事件
    }

    @Override//异常情况，保存数据的方法
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name","LaoLi");
    }

    @Override//异常回复数据的方法
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String name = savedInstanceState.getString("name");
        Log.i(TAG, "onRestoreInstanceState: " + name);

    }
}
