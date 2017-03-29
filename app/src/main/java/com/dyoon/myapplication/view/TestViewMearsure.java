package com.dyoon.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.dyoon.myapplication.R;

import java.util.ArrayList;
import java.util.List;


/**步骤：
 * 创建veiw，处理veiw的布局(onMeasure,onSizeChage)
 * 绘制view
 * 处理用户交互(android的时间分发机制touchEvent)
 *
 *
 *
 * 自定义的单位是px
 * Created by jun on 2016/12/2.
 */

public class TestViewMearsure extends View {
    private static final String TAG = "TestViewMearsure";
    private Paint mpaint;
    private String title;//文本
    private int textColor;//文本的颜色
    private float textSize;//文本的大小
    private Rect bound;//文本的范围
    private List<Float> pointList = new ArrayList<>();
    public TestViewMearsure(Context context) {
        this(context, null);
    }

    public TestViewMearsure(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public TestViewMearsure(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TestViewMearsure);
        title=typedArray.getString(R.styleable.TestViewMearsure_title);
        textColor = typedArray.getColor(R.styleable.TestViewMearsure_textColor, 0xff00ff00);
        textSize = typedArray.getDimension(R.styleable.TestViewMearsure_textSize, 36);
        typedArray.recycle();
        init();
    }

    public void init() {
        Log.i(TAG, "height的大小是： "+getHeight());//这个时候还没有开始测量，所以getHeight()的大小是0；

        for (int i = 1; i < 6; i++) {
            pointList.add(100F*i);
        }

    }

/*    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.i(TAG, "onLayout: ");
        super.onLayout(changed, left, top, right, bottom);


    }*/

/*    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.i(TAG, "onFinishInflate: ");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent: ");
        return super.onTouchEvent(event);

    }*/

    /**
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
           Log.i(TAG, "onMeasure: ");
           int width = measureSize(widthMeasureSpec);
           int height = measureSize(heightMeasureSpec);
           setMeasuredDimension(width, height);
    }

    //通过模式，给出不同的测量值,wrap_content的时候起作用
    public static int measureSize( int measureSpec) {
        int result=1440;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case MeasureSpec.AT_MOST://wrap_content
                result = Math.min(result, specSize);
                break;
            case MeasureSpec.EXACTLY://写死dp或者match_parent
                result=specSize;
                break;
            case MeasureSpec.UNSPECIFIED://未指定，一般用不上，系统内容
                result=1440;
                break;
            default:
                break;
        }
        return result;
    }

/*    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG, "onSizeChanged: ");
    }*/

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int position=3;


        /*super.onDraw(canvas);*/
//        canvas.drawColor(Color.WHITE);
        //canvas的坐标源点就是该view的左上角，canvas的宽度和高度就是该veiw的宽度和高度
        Log.i(TAG, "onDraw: cavas的宽度是"+canvas.getWidth()+", canvas的高度是"+canvas.getHeight());
        Paint paint = new Paint();
//        paint.setTextSize(textSize);
        paint.setColor(Color.parseColor("#00ff00"));


        Paint paint2 = new Paint();
        paint2.setColor(Color.parseColor("#ff0000"));


        Float leftX=100F;
//        canvas.drawLine(20,20,200,200,paint2);//两点间的坐标
        for (int i = 0; i < pointList.size(); i++) {
            canvas.drawCircle(leftX,pointList.get(i),30F,(i<=position) ? paint : paint2);
        }
        for (int i = 0; i < pointList.size()-1; i++) {
            canvas.drawLine(leftX,pointList.get(i),leftX,pointList.get(i+1),(i<position) ? paint : paint2);
        }


    }




    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        //属性改变后，控件的样子也可能发生改变，需要调用invalidate去重新绘制，
        invalidate();
        //同样，属性改变 控件的大小和形状也可能改变，需要调用请求测量获取一个新的布局位置
        requestLayout();
    }
}
