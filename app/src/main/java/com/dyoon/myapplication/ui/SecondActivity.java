package com.dyoon.myapplication.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.dyoon.myapplication.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by jun on 2016/11/1.
 */
@ContentView(R.layout.seconde_activity)
public class SecondActivity extends Activity {
   /* @ViewInject(R.id.second)
    private TextView textView;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }
}
