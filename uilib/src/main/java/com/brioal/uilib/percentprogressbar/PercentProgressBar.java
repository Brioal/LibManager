package com.brioal.uilib.percentprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.brioal.uilib.R;


/**不同颜色的进度条
 * Created by Brioal on 2016/7/10.
 */

public class PercentProgressBar extends View {

    private int mColorRight = Color.GREEN; //作对的颜色
    private int mColorError = Color.RED; //做错的颜色
    private int mColorUndo = Color.GRAY; //未做的颜色

    private int mLineRadicus = 10; //线条的圆角半径
    private int mLineHeight = 10; //线条宽度
    private float mLineWidthRight; //对的部分线条宽度
    private float mLineWidthError; //错的部分线条宽度
    private int mTextSize = 30; //文字大小
    private int mTextWidth = 60; //文字所占的宽度

    private Paint mPaintRight; //正确部分的画笔
    private Paint mPaintError; //错误部分画笔
    private Paint mPaintUndo; //未做部分画笔
    private Paint mPaintText; //文字部分画笔


    private int mRightPercent = 0; //正确部分所占百分比
    private int mErrorPercent = 0; //错误部分所占百分比
    private int mWidth; //组件宽度
    private int mHeight; //组件高度

    private int mAll;
    private int mRight;
    private int mError;


    //所有记录  正确的次数 , 错误的次数
    public void setData(int allCount, int rightCount, int errorCount) {
        mAll = allCount;
        mRight = rightCount;
        mError = errorCount;

        this.mRightPercent = rightCount * 100 / allCount;
        this.mErrorPercent = errorCount * 100 / allCount;
        mLineWidthRight = mWidth * mRightPercent / 100;
        mLineWidthError = mWidth * mErrorPercent / 100;
        if (mRightPercent > 0 && mLineWidthRight < 100) {
            mLineWidthRight = 100;
        }
        if (mErrorPercent > 0 && mLineWidthError < 100) {
            mLineWidthError = 100;
        }

        if ((100 - mRightPercent - mErrorPercent) > 0 && (mLineWidthError + mLineWidthRight > mWidth - 50)) { //超出边界
            mLineWidthError -= 25;
            mLineWidthRight -= 25;
        }
        invalidate();
    }

    public PercentProgressBar(Context context) {
        this(context, null);
    }

    public PercentProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.PercentProgressBar);
        int allcount = array.getInt(R.styleable.PercentProgressBar_allcount, 1);
        int rightcount = array.getInt(R.styleable.PercentProgressBar_rightcount, 0);
        int errorcount = array.getInt(R.styleable.PercentProgressBar_errorcount, 0);
        this.mRightPercent = rightcount * 100 / allcount;
        this.mErrorPercent = errorcount * 100 / allcount;
        invalidate();
        array.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mLineWidthRight = mWidth * mRightPercent / 100;
        mLineWidthError = mWidth * mErrorPercent / 100;

        if (mRightPercent > 0 && mLineWidthRight < 100) {
            mLineWidthRight = 100;
        }
        if (mErrorPercent > 0 && mLineWidthError < 100) {
            mLineWidthError = 100;
        }

        if ((100 - mRightPercent - mErrorPercent) > 0 && (mLineWidthError + mLineWidthRight > mWidth - 50)) { //超出边界
            mLineWidthError -= 25;
            mLineWidthRight -= 25;
        }
        initPaint();
    }

    private void initPaint() {
        mPaintText = new Paint();
        mPaintText.setAntiAlias(true);
        mPaintText.setDither(true);
        mPaintText.setColor(Color.WHITE);
        mPaintText.setTextSize(mTextSize);

        mPaintError = new Paint();
        mPaintError.setAntiAlias(true);
        mPaintError.setDither(true);
        mPaintError.setColor(mColorError);
        mPaintError.setStyle(Paint.Style.FILL);

        mPaintRight = new Paint();
        mPaintRight.setAntiAlias(true);
        mPaintRight.setDither(true);
        mPaintRight.setColor(mColorRight);

        mPaintUndo = new Paint();
        mPaintUndo.setAntiAlias(true);
        mPaintUndo.setDither(true);
        mPaintUndo.setColor(mColorUndo);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float mLeft = 0;
        if (mErrorPercent != 0) { //存在错误部分
            //绘制错误线条
            RectF rectError = new RectF(mLeft, mHeight / 2 - mLineHeight / 2, mLeft + mLineWidthError, mHeight / 2 + mLineHeight / 2);
            canvas.drawRoundRect(rectError, mLineRadicus, mLineRadicus, mPaintError);
            mLeft = rectError.right;
        }
        if (mRightPercent != 0) {
            //绘制正确线条
            RectF rectRight = new RectF(mLeft, mHeight / 2 - mLineHeight / 2, mLeft + mLineWidthRight, mHeight / 2 + mLineHeight / 2);
            canvas.drawRoundRect(rectRight, mLineRadicus, mLineRadicus, mPaintRight);
            mLeft = rectRight.right;
        }
        if (mAll - mRight - mError > 0) {
            //绘制未完成的线条
            RectF rectUndo = new RectF(mLeft, mHeight / 2 - mLineHeight / 2, mWidth, mHeight / 2 + mLineHeight / 2);
            canvas.drawRoundRect(rectUndo, mLineRadicus, mLineRadicus, mPaintUndo);
        }

    }
}
