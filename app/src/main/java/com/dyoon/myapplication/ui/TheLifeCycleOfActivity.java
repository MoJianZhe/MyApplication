package com.dyoon.myapplication.ui;

import android.app.Activity;
import android.os.Bundle;

/**
 * 一般onCreate和onDestroy只调用一次
 * 当用户回到原来的Activity时 onRestart->onStart->onResume
 *
 * Created by jun on 2017/1/11.
 */

public class TheLifeCycleOfActivity extends Activity {
    @Override//创建
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override//可见(一般是在栈顶),onSTart完成后，页面才可见
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

    @Override//不在栈顶 做一些轻微的数据回收工作
    protected void onStop() {
        super.onStop();
    }

    @Override//活动销毁前调用
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 意外停止，保存数据的方法。正常停止不会调用该方法
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name","LaoLi");

    }
}
