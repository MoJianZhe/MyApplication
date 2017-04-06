package com.dyoon.myapplication.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.dyoon.myapplication.R;

/**
 * Created by jun on 2017/4/1.
 */

public class PxTestActivity extends Activity {
    private static final String TAG = "PxTestActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_px_test);
        Button button = (Button) findViewById(R.id.btn_test);
        float scale=getResources().getDisplayMetrics().density;
        Log.i(TAG, "onCreate: density is : "+scale);
    }
}
