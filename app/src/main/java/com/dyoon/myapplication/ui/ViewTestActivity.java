package com.dyoon.myapplication.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.dyoon.myapplication.R;
import com.dyoon.myapplication.view.TestViewMearsure;

/**
 * Created by jun on 2016/12/1.
 */

public class ViewTestActivity extends Activity{
    private final static String TAG = "ViewTestActivity";
    private TestViewMearsure testViewMearsure;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textviewmearsure);
//        setContentView(new TestViewMearsure(this));
        testViewMearsure = (TestViewMearsure) findViewById(R.id.testview_mearsure);
        Log.e(TAG, "onCreate method");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart method");
    }
}
