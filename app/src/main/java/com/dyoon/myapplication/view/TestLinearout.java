package com.dyoon.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by jun on 2016/12/5.
 */

public class TestLinearout extends LinearLayout {
    public TestLinearout(Context context) {
        super(context);
    }

    public TestLinearout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestLinearout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
