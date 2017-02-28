package com.dyoon.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.dyoon.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 大灯泡 on 2016/1/21.
 * 简易带有时间轴的linearlayout
 */
public class UnderLineLinearLayout extends LinearLayout {
    //=============================================================元素定义
    private static final String TAG = "UnderLineLinearLayout";
    private Bitmap mIcon;
    //line location
    private int lineMarginSide;
    private int lineDynamicDimen;
    //line property
    private int lineStrokeWidth;
    private int lineColor;
    //point property
    private int pointSize;
    private int pointColor;

    //=============================================================paint
    private Paint linePaint;
    private Paint pointPaint;

    private Paint dkLinePaint;
    private Paint dkPointPaint;
    //=============================================================其他辅助参数
    //第一个点的位置
    private int firstX;
    private int firstY;
    //position点的位置
    private int positionX;
    private int positionY;
    //最后一个图的位置
    private int lastX;
    private int lastY;
    //默认垂直
    private int curOrientation = VERTICAL;
    private Context mContext;

    //开关
    private boolean drawLine = true;
    //位置
    private int position;
    private List<Integer> pointList = new ArrayList<>();

    public UnderLineLinearLayout(Context context) {
        this(context, null);
    }

    public UnderLineLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UnderLineLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray attr = context.obtainStyledAttributes(attrs, R.styleable.UnderLineLinearLayout);
        lineMarginSide = attr.getDimensionPixelOffset(R.styleable.UnderLineLinearLayout_line_margin_side, 10);
        lineDynamicDimen = attr.getDimensionPixelOffset(R.styleable.UnderLineLinearLayout_line_dynamic_dimen, 0);
        lineStrokeWidth = attr.getDimensionPixelOffset(R.styleable.UnderLineLinearLayout_line_stroke_width, 2);
        lineColor = attr.getColor(R.styleable.UnderLineLinearLayout_line_color, 0xff3dd1a5);
        pointSize = attr.getDimensionPixelSize(R.styleable.UnderLineLinearLayout_point_size, 8);
        pointColor = attr.getDimensionPixelOffset(R.styleable.UnderLineLinearLayout_point_color, 0xff3dd1a5);

        int iconRes = attr.getResourceId(R.styleable.UnderLineLinearLayout_icon_src, R.drawable.ic_ok);
        BitmapDrawable temp = (BitmapDrawable) context.getResources().getDrawable(iconRes);
        if (temp != null) mIcon = temp.getBitmap();

        curOrientation = getOrientation();
        attr.recycle();
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setDither(true);
        linePaint.setColor(lineColor);
        linePaint.setStrokeWidth(lineStrokeWidth);
        linePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        pointPaint = new Paint();
        pointPaint.setAntiAlias(true);
        pointPaint.setDither(true);
        pointPaint.setColor(pointColor);
        pointPaint.setStyle(Paint.Style.FILL);

        dkLinePaint = new Paint();
        dkLinePaint.setAntiAlias(true);
        dkLinePaint.setDither(true);
        dkLinePaint.setColor(Color.DKGRAY);
        dkLinePaint.setStrokeWidth(lineStrokeWidth);
        dkLinePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        dkPointPaint = new Paint();
        dkPointPaint.setAntiAlias(true);
        dkPointPaint.setDither(true);
        dkPointPaint.setColor(Color.GRAY);
        dkPointPaint.setStyle(Paint.Style.FILL);

    }

/*    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int childCount=getChildCount();
        int top;

        for (int i = 0; i < childCount; i++) {
            top=getChildAt(i).getTop();
            int y=top+getChildAt(i).getPaddingTop()+lineDynamicDimen;
            pointList.add(y);
        }

    }*/

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(TAG, "onMeasure: ");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.i(TAG, "onSizeChanged: ");
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        initpointList();
    }

    @Override
    protected void onDraw(Canvas canvas) {
     /*   if (pointList.size() != getChildCount()) {//防止重复绘制的时候多次添加了point
            initpointList();
        }*/
        Log.i(TAG, "onDraw: ");
        super.onDraw(canvas);
        if (drawLine) {
//            drawTimeLine(canvas);
            newDraw(canvas);
        }

    }

    private void initpointList() {
        int childCount=getChildCount();
        int top;
        pointList.clear();
        for (int i = 0; i < childCount; i++) {
            top=getChildAt(i).getTop();

            int y=top+getChildAt(i).getPaddingTop()+lineDynamicDimen;
            pointList.add(y);
        }
    }


    private void drawTimeLine(Canvas canvas) {
        int childCount = getChildCount();
        if (position == childCount) {//已完成流程，不需要画灰色的线
            switch (curOrientation) {
                case VERTICAL:
                    drawFirstChildViewVertical(canvas);
                    drawPositionChildViewVertical(canvas);
                    drawBetweenLineVertical(canvas);
                    break;
                case HORIZONTAL:
                    break;
                default:
                    break;
            }
        }else{
            if (childCount > 0) {
                //大于1，证明至少有2个，也就是第一个和第二个之间连成线，第一个和最后一个分别有点/icon
                if (childCount > 1) {
                    switch (curOrientation) {
                        case VERTICAL:
                            drawCanvas(canvas);
                            break;
                        case HORIZONTAL:
                            break;
                        default:
                            break;
                    }
                }
                else if (childCount == 1) {
                    switch (curOrientation) {
                        case VERTICAL:
                            drawFirstChildViewVertical(canvas);
                            break;
                        case HORIZONTAL:
                            break;
                        default:
                            break;
                    }
                }
            }
        }

    }

    private void drawCanvas(Canvas canvas) {
        drawFirstChildViewVertical(canvas);
        drawPositionChildViewVertical(canvas);
        drawBetweenLineVertical(canvas);
        if (position == getChildCount() - 1) {//如果position是倒数第二个
            drawLastChildView(canvas);
            drawLineForPosition(canvas);
        } else {

            drawLastChildView(canvas);
            drawBetweenPotionToLast(canvas);
            drawLineForPosition(canvas);
        }
    }

    private void drawFirstChildViewVertical(Canvas canvas) {
        if (getChildAt(0) != null) {
            int top = getChildAt(0).getTop();
            //记录值
            firstX = lineMarginSide;
            firstY = top + getChildAt(0).getPaddingTop() + lineDynamicDimen;
            //画一个圆
            canvas.drawCircle(firstX, firstY, pointSize, pointPaint);
        }
    }

    private void drawPositionChildViewVertical(Canvas canvas) {
        if (getChildAt(position) != null) {
            int top = getChildAt(position).getTop();
            //记录值
            lastX = lineMarginSide - (mIcon.getWidth() >> 1);
            lastY = top + getChildAt(position).getPaddingTop() + lineDynamicDimen;
            //画一个图
            canvas.drawBitmap(mIcon, lastX, lastY, null);
        }
    }

    private void drawBetweenLineVertical(Canvas canvas) {
        for (int i = 0; i < position; i++) {
            //画剩下的
            canvas.drawLine(lineMarginSide, firstY, lineMarginSide, lastY, linePaint);
            //画了线，就画圆
            if (getChildAt(i) != null && i != 0) {
                int top = getChildAt(i).getTop();
                //记录值
                int Y = top + getChildAt(i).getPaddingTop() + lineDynamicDimen;
                canvas.drawCircle(lineMarginSide, Y, pointSize, pointPaint);
            }
        }
    }

    //画最后一个位置的点
    private void drawLastChildView(Canvas canvas) {
        if (getChildAt(getChildCount()-1) != null) {
            int top = getChildAt(getChildCount()-1).getTop();
            //记录值
            lastX = lineMarginSide;
            lastY = top + getChildAt(getChildCount()-1).getPaddingTop() + lineDynamicDimen;
            //画一个点
            canvas.drawCircle(lastX, lastY, pointSize, dkPointPaint);
        }

    }

    //画position+1到最后位置的线
    private void drawBetweenPotionToLast(Canvas canvas) {
        int positionTop=getChildAt(position+1).getTop();
        int positionY=positionTop+getChildAt(position+1).getPaddingTop()+lineDynamicDimen;
        for (int i = position+1; i < getChildCount(); i++) {
            //画剩下的
            canvas.drawLine(lineMarginSide, positionY, lineMarginSide, lastY, dkLinePaint);
            //画了线，就画圆
            if (getChildAt(i) != null && i != 0) {
                int top = getChildAt(i).getTop();
                //记录值
                int Y = top + getChildAt(i).getPaddingTop() + lineDynamicDimen;
                canvas.drawCircle(lineMarginSide, Y, pointSize, dkPointPaint);
            }
        }
    }

    //画positon到position+1的线
    private void drawLineForPosition(Canvas canvas) {
        int positionY=getChildAt(position).getTop()+getChildAt(position).getPaddingTop()+lineDynamicDimen;
        int positionStartY=positionY+mIcon.getHeight();
        int positionEndY=getChildAt(position+1).getTop()+getChildAt(position+1).getPaddingTop()+lineDynamicDimen;
        canvas.drawLine(lineMarginSide,positionStartY,lineMarginSide,positionEndY,dkLinePaint);

    }


    private void newDraw(Canvas canvas) {
        for (int i = 0; i < pointList.size(); i++) {
            canvas.drawCircle(lineMarginSide,pointList.get(i),pointSize,(i<=getPosition())?pointPaint:dkPointPaint);
        }
        for (int i = 0; i <pointList.size()-1 ; i++) {
            canvas.drawLine(lineMarginSide,pointList.get(i),lineMarginSide,pointList.get(i+1),(i<getPosition())?linePaint:dkLinePaint);
        }
        int positionX=lineMarginSide-(mIcon.getWidth()>>1);
        int positionY=getChildAt(getPosition()).getTop()+getChildAt(getPosition()).getPaddingTop()+lineDynamicDimen
                        -(mIcon.getHeight()>>1);
        canvas.drawBitmap(mIcon,positionX,positionY,null);
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
