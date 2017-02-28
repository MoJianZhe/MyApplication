package com.dyoon.myapplication.ui;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dyoon.myapplication.R;
import com.dyoon.myapplication.view.ViewWrapper;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by jun on 2017/2/9.
 */

@ContentView(value = R.layout.activity_property_animation)
public class PropertyAnimationActivity extends Activity {
    private static final String TAG = "PropertyAnimationActivi";
    @ViewInject(R.id.btn_test)
    private Button btnTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    @Event(value = R.id.btn_test)
    private void btnTestClick(View view) {
        /**
         * String参数控制了动画的行为
         *//*
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(btnTest, "translationY", 300);
        objectAnimator.setDuration(1000);
        objectAnimator.start();*/

//        PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("translationX", 300);
        //1f表示一倍self，2f表示2倍self
        PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("scaleX", 1f, 0,1f,2f);
        ObjectAnimator objectAnimator=ObjectAnimator.ofPropertyValuesHolder(btnTest, pvh2);
        objectAnimator.setDuration(1000);
        objectAnimator.start();

    }
}
