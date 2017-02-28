package com.dyoon.myapplication.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.dyoon.myapplication.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by jun on 2016/11/4.
 * 看看popwindow
 */

@ContentView(R.layout.popwindow)
public class PopupWindow extends Activity {
    @ViewInject(R.id.menu)
    private Button menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

    }

    @Event(R.id.menu)
    private void menuClick() {
        

    }
}
