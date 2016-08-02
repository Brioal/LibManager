package com.brioal.uilib.progressbar_text;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.brioal.baselib.util.SizeUtil;
import com.brioal.uilib.R;

/**
 * 水平带文字的进度条
 * Created by Brioal on 2016/7/21.
 */

public class LineProgressWithText extends ProgressBar {

    private static final int DEFAULT_TEXT_SIZE = 15; //字体大小 sp
    private static final int DEFAULT_TEXT_COLOR = Color.BLACK; //文字颜色
    private static final int DEFAULT_UNREACH_COLOR = Color.BLUE; //未完成的颜色
    private static final int DEFUALT_REACH_COLOR = Color.GREEN; //已完成的颜色
    private static final int DEFAULT_BAR_HEIGHT = 2; //线条的高度 dpi
    private static final int DEFAULT_TEXT_OFFSET = 10; //文字的边距 dpi


    private int mTextSize; //文字大小
    private int mTextColor; //文字颜色
    private int mUnreachColor; //未完成的颜色
    private int mReachColor; //已完成的颜色
    private int mBarHeight; //线条的高度
    private int mRealWidth; //最终绘制的线条宽度
    private float mTextOffset = SizeUtil.Dp2Px(getContext(), DEFAULT_TEXT_OFFSET); //文字的边距

    private Paint mPaint = new Paint();
    private int progress;

    public LineProgressWithText(Context context) {
        this(context, null);
    }

    public LineProgressWithText(Context context, AttributeSet attrs) {
        super(context, attrs);
        obtainStyledAttrs(attrs);
        mPaint.setTextSize(mTextSize);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    //获取属性
    private void obtainStyledAttrs(AttributeSet attrs) {
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.LineProgressWithText);
        mTextSize = (int) array.getDimension(R.styleable.LineProgressWithText_line_progress_text_size, SizeUtil.Sp2Px(getContext(), DEFAULT_TEXT_SIZE));
        mTextColor = array.getColor(R.styleable.LineProgressWithText_line_progress_text_color, DEFAULT_TEXT_COLOR);
        mUnreachColor = array.getColor(R.styleable.LineProgressWithText_line_progress_unreach_color, DEFAULT_UNREACH_COLOR);
        mReachColor = array.getColor(R.styleable.LineProgressWithText_line_progress_reach_color, DEFUALT_REACH_COLOR);
        mBarHeight = (int) array.getDimension(R.styleable.LineProgressWithText_line_progress_bar_height, SizeUtil.Dp2Px(getContext(), DEFAULT_BAR_HEIGHT));
        array.recycle();
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widtgVal = MeasureSpec.getSize(widthMeasureSpec);
        int heightVal = measureHeight(heightMeasureSpec);
        setMeasuredDimension(widtgVal, heightVal);

        mRealWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
    }

    @Override
    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
        this.progress = progress;
        invalidate();
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(getPaddingLeft(), getHeight() / 2); //坐标中心移动到开始的店
        String text = progress + "%";
        int textwidth = (int) mPaint.measureText(text);
        //绘制达到的进度
        float radio = progress * 1.0f / getMax(); //进度百分比
        float endX = 0;
        if (radio > 0) {
            endX = radio * mRealWidth - mTextOffset / 2 - textwidth / 2; //绘制完成的坐标点
            mPaint.setColor(mReachColor);
            mPaint.setStrokeWidth(mBarHeight);
            canvas.drawLine(0, 0, endX, 0, mPaint); //绘制线条
        }
        //绘制文字
        if (progress != 0) {
            endX = endX + mTextOffset / 2;
        }
        Rect textBound = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), textBound);
        int startY = -(textBound.bottom + textBound.top) / 2;
        mPaint.setColor(mTextColor);
        canvas.drawText(text, 0, text.length(), endX, startY, mPaint);
        //绘制未达到的进度
        boolean NeedUnReach = true; //是否不需要未达到的进度
        if ((endX + mTextOffset / 2 + textwidth) >= mRealWidth) {
            NeedUnReach = false;

        }
        if (NeedUnReach) {
            endX += mTextOffset / 2 + textwidth;
            mPaint.setColor(mUnreachColor);
            mPaint.setStrokeWidth(mBarHeight);
            canvas.drawLine(endX, 0, mRealWidth, 0, mPaint);
        }
        canvas.restore();
    }

    //测量高度
    private int measureHeight(int heightMeasureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);

        if (mode == MeasureSpec.EXACTLY) { //精确值
            result = size;
        } else {
            int textHeight = (int) (mPaint.descent() - mPaint.ascent()); //字体高度
            result = getPaddingTop() + getPaddingBottom() + Math.max(Math.abs(textHeight), mBarHeight); //线条 ,文字高度的最大值
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }
}
