package com.brioal.uilib.paintboard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.brioal.baselib.util.SizeUtil;
import com.brioal.uilib.R;

/**
 * 画板
 * Created by brioal on 16-7-28.
 */

public class PaintBoard extends View {
    private int mBoardColor; //画板颜色
    private int mPaintColor; //画笔颜色
    private int mPaintStrike; //画笔宽度

    private Paint mPaint;
    private Path mPath;


    public PaintBoard(Context context) {
        this(context, null);
    }

    public PaintBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        obtainStyledAttr(attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(mPaintColor);
        mPaint.setStrokeWidth(mPaintStrike);
        mPaint.setStyle(Paint.Style.STROKE);

        mPath = new Path();

        setBackgroundColor(mBoardColor);
    }

    private void obtainStyledAttr(AttributeSet attrs) {
        TypedArray array = null;
        try {
            array = getContext().obtainStyledAttributes(attrs, R.styleable.PaintBoard);
            mBoardColor = array.getColor(R.styleable.PaintBoard_paintBoardColor, getResources().getColor(R.color.colorWhite));
            mPaintColor = array.getColor(R.styleable.PaintBoard_paintPaintColor, getResources().getColor(R.color.colorLightBlack));
            mPaintStrike = (int) array.getDimension(R.styleable.PaintBoard_paintPaintStrike, SizeUtil.Sp2Px(getContext(), 2));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (array != null) {
                array.recycle();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float x = event.getX();
        float y = event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x, y);
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }
}
