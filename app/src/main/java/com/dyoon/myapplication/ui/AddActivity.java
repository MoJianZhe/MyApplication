package com.dyoon.myapplication.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.dyoon.myapplication.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

/**
 * Created by jun on 2017/1/12.
 */

@ContentView(R.layout.activity_add)
public class AddActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }


}
