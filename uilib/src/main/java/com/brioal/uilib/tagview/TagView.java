package com.brioal.uilib.tagview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.TextView;

import com.brioal.uilib.R;


/**
 * 一个带边框的TextView
 * Created by Brioal on 2016/5/19.
 */

public class TagView extends TextView {
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private int mStrikeWidth;
    private int mColor;
    private boolean isChecked = false;

    public TagView(Context context) {
        this(context, null);
    }

    public TagView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mStrikeWidth = 2;
        mColor = getContext().getResources().getColor(R.color.colorPrimary);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(mColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrikeWidth);

    }

    public void setChecked(boolean checked) {
        isChecked = checked;
        if (checked) {
            setTextColor(Color.WHITE);
            setBackgroundColor(getContext().getResources().getColor(R.color.colorPrimary));
        } else {
            setTextColor(Color.BLACK);
            setBackgroundColor(Color.WHITE);
        }
    }

    public boolean isChecked() {
        return isChecked;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        canvas.drawRoundRect(new RectF(mStrikeWidth, mStrikeWidth, mWidth - mStrikeWidth, mHeight - mStrikeWidth), 5, 5, mPaint);
    }
}
