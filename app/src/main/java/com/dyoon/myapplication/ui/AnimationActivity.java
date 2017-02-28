package com.dyoon.myapplication.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.dyoon.myapplication.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by jun on 2017/1/11.
 */
@ContentView(R.layout.activity_animation)
public class AnimationActivity extends Activity {
    private static final String TAG = "AnimationActivity";
    @ViewInject(R.id.textView4)
    private TextView textView;
    @ViewInject(R.id.button)
    private Button button;
    @ViewInject(R.id.btn_alpha)
    private Button alpha;
    @ViewInject(R.id.btn_translate)
    private Button translate;
    @ViewInject(R.id.btn_scale)
    private Button scale;
    @ViewInject(R.id.btn_animation_set)
    private Button set;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();//不加这一行 会报错
        textView.setText("我是一个兵，来自老百姓");
    }


    @Event(value = R.id.button)
    private void buttonClick(View view) {
        Log.i(TAG, "buttonClick: you click me ");
        RotateAnimation r = new RotateAnimation(0, 360, 100, 100);
        r.setDuration(1000);
        textView.startAnimation(r);

    }

    @Event(value = R.id.btn_alpha)
    private void AlphaClick(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(1000);
        textView.setAnimation(alphaAnimation);
    }

    @Event(value = R.id.btn_translate)
    private void translateClick(View view) {
        Log.i(TAG, "translateClick: ");
        TranslateAnimation t = new TranslateAnimation(0, 100, 0, 300);
        t.setDuration(1000);
        textView.startAnimation(t);
    }

    @Event(value = R.id.btn_scale)
    private void scaleClick(View view) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
        scaleAnimation.setDuration(1000);
        textView.startAnimation(scaleAnimation);
    }

    @Event(value = R.id.btn_animation_set)
    private void setClick(View view) {
        AnimationSet set = new AnimationSet(true);
        set.setDuration(2000);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5F, RotateAnimation.RELATIVE_TO_SELF, 0.5F);
        rotateAnimation.setDuration(1000);


        TranslateAnimation translateAnimation=new TranslateAnimation(0,100,0,300);
        translateAnimation.setDuration(1000);

        set.addAnimation(rotateAnimation);
        set.addAnimation(translateAnimation);
        textView.startAnimation(set);
    }
}

