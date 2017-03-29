package com.dyoon.myapplication.ui.animal;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dyoon.myapplication.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jun on 2017/3/10.
 */

public class OverParentActivity extends Activity{
    private static final String TAG = "OverParentActivity";
    private Button button;
    private TextView textView;
    Map<String, Integer> map = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_parent);
        this.button = (Button) findViewById(R.id.btn_move);
        textView= ((TextView) findViewById(R.id.text));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: ");
                Animation animation = AnimationUtils.loadAnimation(OverParentActivity.this, R.anim.move);
                animation.setFillAfter(true);//不会回到原来的位置，但view的布局还在原来的位置
                textView.startAnimation(animation);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OverParentActivity.this, "Hello World!", Toast.LENGTH_SHORT).show();
            }
        });
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action=event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        Log.i(TAG, "onTouch: "+"hello button down");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.i(TAG, "onTouch: "+"hello button move");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.i(TAG, "onTouch: "+"hello action up");
                        break;
                    default:
                }
                return true;
            }
        });

    }
}
