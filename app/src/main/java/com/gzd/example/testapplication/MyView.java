package com.gzd.example.testapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by gzd on 2019/2/17 0017
 */
public class MyView extends View {
    private int mWeightSize;
    private int mHeightSize;
    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context,AttributeSet attrs) {
        this(context,attrs,0);
    }

    public MyView(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.MyStyle);  //允许用户在xml中指定一些本来要在code里面指定的值
        mWeightSize = a.getDimensionPixelSize(R.styleable.MyStyle_wrap_w_size,100);
        mHeightSize = a.getDimensionPixelSize(R.styleable.MyStyle_wrap_h_size,100);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);

        if (wMode == MeasureSpec.AT_MOST && hMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(mWeightSize,mHeightSize);
        }else if (wMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(mWeightSize,hSize);
        }else if (hMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(wSize,mHeightSize);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int r;
        if (getMeasuredHeight() < getMeasuredWidth()){
            r = getMeasuredHeight() / 2;
        }else {
            r = getMeasuredWidth() / 2;
        }


        int centerX = getLeft() + r;
        int centerY = getTop() + r;

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        //开始绘制
        canvas.drawCircle(centerX, centerY, r, paint);
    }


}
