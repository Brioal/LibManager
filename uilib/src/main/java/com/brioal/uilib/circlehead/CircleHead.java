package com.brioal.uilib.circlehead;

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
 * 显示文字的圆点
 */
public class CircleHead extends View {
    private int mHeight; //高度
    private int mWidth; //宽度
    private int mRadius; //半径
    private String mText = "A"; //文字
    private Paint mPaintBack; //背景画笔
    private Paint mPaintText; //文字画笔
    private int mBackColor = Color.GRAY; //背景色
    private int mTextColor = Color.WHITE; //文字颜色
    private boolean isRandom = false; //是否开启随机颜色
    private Random random;

    public CircleHead(Context context) {
        this(context, null);
    }

    public CircleHead(Context context, AttributeSet attrs) {
        super(context, attrs);
        random = new Random();
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.CircleHead);
        mText = array.getString(R.styleable.CircleHead_circle_head_text);
        mBackColor = array.getColor(R.styleable.CircleHead_circlr_head_back, getResources().getColor(R.color.colorPrimary));
        mTextColor = array.getColor(R.styleable.CircleHead_circlr_head_text_color, Color.WHITE);
        isRandom = array.getBoolean(R.styleable.CircleHead_circlr_head_israndom, false);
        array.recycle();
        init();
    }

    private void init() {
        mPaintBack = new Paint();
        mPaintBack.setAntiAlias(true);
        mPaintBack.setDither(true);
        mPaintBack.setStyle(Paint.Style.FILL);
        if (isRandom) {
            mBackColor = getRandomColor(); //产生随机颜色
        }
        mPaintBack.setColor(mBackColor);

        mPaintText = new Paint();
        mPaintText.setAntiAlias(true);
        mPaintText.setDither(true);
        mPaintText.setColor(mTextColor);

    }

    //设置文字
    public void setmText(String mText) {
        this.mText = mText;
        invalidate();
    }

    //设置背景颜色
    public void setBackColor(int backColor) {
        mBackColor = backColor;
    }

    //设置文字颜色
    public void setTextColor(int textColor) {
        mTextColor = textColor;
    }

    //设置是否开启随机颜色
    public void setRandom(boolean isRandom) {
        this.isRandom = isRandom;
    }

    public int getRandomColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return Color.rgb(r, g, b);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mRadius = (Math.min(mWidth, mHeight) - 10) / 2; //获取半径
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mRadius, mPaintBack); //绘制外圆
        mPaintText.setTextSize(mRadius - 20); //自动设置字体大小
        Paint.FontMetrics metrics = mPaintText.getFontMetrics();
        float textWidth = mPaintText.measureText(mText); //获取字体的宽度
        float textHeight = metrics.bottom - metrics.top; //获取字体的高度
        float offsetWidth = (textWidth) / 2; //计算快递便宜
        float offsetHeight = mWidth / 2 - (metrics.bottom - (textHeight - mWidth) / 2); //计算高度偏移
        canvas.drawText(mText, mWidth / 2 - offsetWidth, mHeight / 2 + offsetHeight, mPaintText); //绘制文字
    }
}
