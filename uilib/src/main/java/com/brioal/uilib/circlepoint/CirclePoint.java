package com.brioal.uilib.circlepoint;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.brioal.uilib.R;

import java.util.Random;

/**
 * 圆点
 * Created by Brioal on 2016/5/18.
 */
public class CirclePoint extends View {
    private int mColor;
    private Paint mPaint;
    private int radicus;
    private int mWidth;
    private int mHeight;

    private Random mRandom;
    private boolean isRandom = false; //是否随机颜色

    public CirclePoint(Context context) {
        this(context, null);
        init();
    }

    public CirclePoint(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.CirclePoint);
        int color = array.getColor(R.styleable.CirclePoint_pointcolor, getResources().getColor(R.color.colorPrimary));
        boolean israndom = array.getBoolean(R.styleable.CirclePoint_israndom, false);
        mColor = color;
        isRandom = israndom;
        init();
    }

    public void randomColor() {
        int r = mRandom.nextInt(255);
        int g = mRandom.nextInt(255);
        int b = mRandom.nextInt(255);
        mColor = Color.rgb(r, g, b);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        mWidth = widthSize;
        mHeight = heightSize;
        radicus = Math.min(widthSize, heightSize) / 2;
    }


    private void init() {
        mRandom = new Random();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
        if (isRandom) {
            randomColor(); //产生随机颜色
        }
        mPaint.setColor(mColor);
    }

    public void setColor(int mColor) {
        this.mColor = mColor;
        mPaint.setColor(mColor);
        invalidate();
    }

    public void setRandom(boolean isRandom) {
        this.isRandom = isRandom;
        randomColor();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mWidth / 2, mHeight / 2, radicus - 10, mPaint);
    }
}
