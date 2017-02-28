package com.dyoon.myapplication.ui;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by jun on 2017/1/11.
 */

public class TheLifeCycleOfActivity extends Activity {
    @Override//创建
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override//可见(一般是在栈顶)
    protected void onStart() {
        super.onStart();
    }

    @Override//获得焦点，（如用户正在滑动）
    protected void onResume() {
        super.onResume();
    }

    @Override//失去焦点（如：弹出了一个dialog)
    protected void onPause() {
        super.onPause();
    }

    @Override//不在栈顶
    protected void onStop() {
        super.onStop();
    }

    @Override//销毁
    protected void onDestroy() {
        super.onDestroy();
    }
}
