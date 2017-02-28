package com.dyoon.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.icu.text.IDNA;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dyoon.myapplication.R;
import com.dyoon.myapplication.util.Utils;

/**
 * Created by jun on 2017/2/22.
 */

public class IndexBar extends LinearLayout implements View.OnTouchListener {
    private static final String TAG = "IndexBar";
    public static final String []INDEXLIST=new String[]{"#", "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private IndexChangeListener listener;
    public void setListener(IndexChangeListener listener) {
        this.listener = listener;
    }

    public IndexBar(Context context) {
        this(context,null);
    }

    public IndexBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public IndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i(TAG, "IndexBar: ");
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        Log.i(TAG, "init: ");
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.IndexBar);
        //getDimension获取的默认的单位是px,你设置的sp会自动根据公式被转化成px
        float textSize = typedArray.getDimension(R.styleable.IndexBar_indexTextSize, Utils.sp2px(getContext(),12));
        int textColor= typedArray.getColor(R.styleable.IndexBar_indexTextColor,0xff616161);
        typedArray.recycle();
        setOrientation(VERTICAL);
        setOnTouchListener(this);
        for (String s : INDEXLIST) {
            TextView textView = new TextView(getContext());
            textView.setText(s);
            //也可以 text.setTextSize(TypedValue.COMPLEX_UNIT_PX, indexTextSize);
            textView.setTextSize(Utils.px2sp(getContext(),textSize));//scale pixels表示"缩放像素"即sp

            textView.setTextColor(textColor);
            textView.setGravity(Gravity.CENTER);
            //相应的还有TableRow.LayoutParams,RelativeLayout.LayoutParams
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
            textView.setLayoutParams(params);
            addView(textView);
        }
    }

    /**
     *
     * @param v 表示自定义的这个indexBar
     * @param event 事件
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setBackgroundColor(0x40000000);
                handleEvent(v,event);
                return true;
            case MotionEvent.ACTION_MOVE:
                handleEvent(v,event);
                return true;
            case MotionEvent.ACTION_UP:
                handleEvent(v,event);
                return true;
        }

        return super.onTouchEvent(event);
    }

    private void handleEvent(View v,MotionEvent event) {
        int  y= (int) event.getY();//触点相对于view的左上角的坐标，getRawY()出点相对于view的父节点的坐标
        int height=v.getHeight();
        int position=INDEXLIST.length*y/height;//其实是length*(y/height);
        if (position < 0) {position=0;}
        else if(position>INDEXLIST.length) {position=INDEXLIST.length-1;}
        String index = INDEXLIST[position];
        if(listener!=null){
            listener.onIndexChanged(index, event.getAction() != MotionEvent.ACTION_UP);
        }

    }

    public interface IndexChangeListener{
        void onIndexChanged(String index, boolean showIndicator);
    }
}
