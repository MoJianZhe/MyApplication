package com.dyoon.myapplication.view;

import android.util.Log;
import android.view.View;

/**
 * Created by jun on 2017/2/9.
 */

public class ViewWrapper  {
    private static final String TAG = "ViewWrapper";
    private View mTarget;

    public ViewWrapper(View view) {
        this.mTarget=view;
    }

    public int getWidth() {
        Log.i(TAG, "getWidth: view.layoutParams.width :"+mTarget.getLayoutParams().width);
        Log.i(TAG, "getWidth: view.getWidth() :"+mTarget.getWidth());
        /**
         *
         * 如果设置的是wrap_content返回的是-2，Match_parent返回的是-1
         * 返回view向父view请求的最大宽度
         * 这是设置的常量
         * public static final int MATCH_PARENT=-1;
         * public static final int WRAP_CONTENT=-2;
         */
        return mTarget.getLayoutParams().width;
    }

    public void setWidth(int width) {
        mTarget.getLayoutParams().width=width;
        mTarget.requestLayout();
    }


}
