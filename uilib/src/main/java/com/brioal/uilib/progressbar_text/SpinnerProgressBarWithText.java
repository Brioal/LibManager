package com.brioal.uilib.progressbar_text;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.brioal.baselib.util.SizeUtil;
import com.brioal.uilib.R;

/**
 * 圆形带文字的进度条
 * Created by Brioal on 2016/7/23.
 */

public class SpinnerProgressBarWithText extends View {
    private int mRadius; //半径
    private int mLineBarWitdh; //圆形框的宽度
    private int mTextSize; //文字大小
    private int mProgress = 0; //进度
    private int mStartAngle; //开始的角度

    private int mTextColor; //文字颜色
    private int mUnReachedColor; //未完成的颜色
    private int mReachColor; //完成的颜色
    private int mCenterColor; //中间的填充颜色

    private int size = 0;

    private Paint mPaint;

    public SpinnerProgressBarWithText(Context context) {
        this(context, null);
    }

    public SpinnerProgressBarWithText(Context context, AttributeSet attrs) {
        super(context, attrs);
        obtainStyledAttrs(attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setTextSize(mTextSize);
    }

    //获取资源文件
    private void obtainStyledAttrs(AttributeSet attrs) {
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.SpinnerProgressBarWithText);
        mRadius = (int) array.getDimension(R.styleable.SpinnerProgressBarWithText_spinner_progress_radicus, SizeUtil.Dp2Px(getContext(), 50));
        mLineBarWitdh = (int) array.getDimension(R.styleable.SpinnerProgressBarWithText_spinner_progress_line_width, SizeUtil.Dp2Px(getContext(), 3));
        mTextSize = (int) array.getDimension(R.styleable.SpinnerProgressBarWithText_spinner_progress_line_text_size, SizeUtil.Sp2Px(getContext(), 15));
        mStartAngle = (int) array.getInt(R.styleable.SpinnerProgressBarWithText_spinner_progress_line_start_angle, 0);

        mTextColor = array.getColor(R.styleable.SpinnerProgressBarWithText_spinner_progress_line_text_color, Color.BLACK);
        mUnReachedColor = array.getColor(R.styleable.SpinnerProgressBarWithText_spinner_progress_line_unreach_color, Color.GRAY);
        mReachColor = array.getColor(R.styleable.SpinnerProgressBarWithText_spinner_progress_line_reached_color, Color.GREEN);
        mCenterColor = array.getColor(R.styleable.SpinnerProgressBarWithText_spinner_progress_line_center_color, Color.WHITE);
        mProgress = array.getInt(R.styleable.SpinnerProgressBarWithText_spinner_progress_line_progress, 0);
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureWidth(widthMeasureSpec);
        int height = mesaureHeight(heightMeasureSpec);

        size = Math.min(width, height);
        mRadius = (size - mLineBarWitdh * 2) / 2;
        //半径设置只在宽度或者高度都为wrap_content,或者radicus小于指定宽或者高的时候起作用
        setMeasuredDimension(size, size);
    }

    //测量高度
    private int mesaureHeight(int heightMeasureSpec) {
        int result = 0;
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int mode = MeasureSpec.getMode(heightMeasureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = mRadius * 2 + mLineBarWitdh * 2;
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }

        return result;
    }

    //测量宽度
    private int measureWidth(int widthMeasureSpec) {
        int result = 0;
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = mRadius * 2 + mLineBarWitdh * 2;
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }

        return result;

    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        String text = mProgress + "%";
        Rect rectBound = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), rectBound);
        int mTextWidth = rectBound.right - rectBound.left;
        int mTextHeight = rectBound.bottom - rectBound.top;

        canvas.save();
        canvas.translate(size / 2, size / 2);
        mPaint.setStyle(Paint.Style.STROKE);
        //绘制填充背景
        mPaint.setColor(mCenterColor);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, mRadius, mPaint);
        //绘制unReach
        mPaint.setColor(mUnReachedColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mLineBarWitdh);
        canvas.drawCircle(0, 0, mRadius, mPaint);

        //绘制Reache
        mPaint.setColor(mReachColor);
        float sweepAngle = mProgress * 1.0f / 100 * 360;
        canvas.drawArc(new RectF(-mRadius, -mRadius, mRadius, mRadius), mStartAngle, sweepAngle, false, mPaint);
        //绘制文字
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mTextColor);
        canvas.drawText(text, 0, text.length(), -mTextWidth / 2, mTextHeight / 2, mPaint);
        canvas.restore();
    }

    //设置进度
    public void setProgress(int progress) {
        mProgress = progress;
        invalidate();
    }

    //设置文字颜色
    public void setTextColor(int textColor) {
        mTextColor = textColor;
    }

    //设置文字大小
    public void setTextSize(int textSize) {
        mTextSize = textSize;
        mPaint.setTextSize(textSize);
    }

    //设置中间填充颜色
    public void setCenterColor(int centerColor) {
        mCenterColor = centerColor;
    }

    //设置未完成的颜色
    public void setUnReachedColor(int unReachedColor) {
        mUnReachedColor = unReachedColor;
    }

    //设置已完成的部分颜色
    public int getReachColor() {
        return mReachColor;
    }
}
