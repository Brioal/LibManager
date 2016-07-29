package com.brioal.uilib.largeimagedisplay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.InputStream;

/**
 * 加载大图View
 * Created by brioal on 16-7-26.
 */

public class LargeImageDisplay extends View {
    private BitmapRegionDecoder mRegionDecoder;
    private int mImageWidth; //图片宽度
    private int mImageHeight; //图片高度
    private Rect mPaintRect = new Rect(); //绘制的区域
    private MoveGestureDetector mDetector;
    private static final BitmapFactory.Options OPTIONS = new BitmapFactory.Options();

    static {
        OPTIONS.inPreferredConfig = Bitmap.Config.RGB_565;
    }


    public void setInputStream(InputStream inputStream) {
        try {
            mRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, false); //实例化
            BitmapFactory.Options options = new BitmapFactory.Options();
            //获取图片尺寸
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, options);
            mImageWidth = options.outWidth;
            mImageHeight = options.outHeight;

            requestLayout();
            invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    public void init() {
        mDetector = new MoveGestureDetector(getContext(), new MoveGestureDetector.SimpleMoveGestureDetector() {
            @Override
            public boolean onMove(MoveGestureDetector detector) {
                int moveX = (int) detector.getMoveX();
                int moveY = (int) detector.getMoveY();
                if (mImageWidth > getWidth()) { //可左右滑动
                    mPaintRect.offset(-moveX, 0);
                    checkWidth();
                    invalidate();
                }

                if (mImageHeight > getHeight()) {
                    mPaintRect.offset(0, -moveY);
                    checkHeight();
                    invalidate();
                }
                return true;
            }
        });
    }

    //检查宽度
    private void checkWidth() {
        Rect rect = mPaintRect;
        int imageWidth = mImageWidth;

        if (rect.right > imageWidth) {
            rect.right = imageWidth;
            rect.left = imageWidth - getWidth();
        }

        if (rect.left < 0) {
            rect.left = 0;
            rect.right = getWidth();
        }
    }

    //检查高度
    private void checkHeight() {
        Rect rect = mPaintRect;
        int imageHeight = mImageHeight;

        if (rect.top > imageHeight) {
            rect.top = imageHeight;
            rect.bottom = imageHeight - getHeight();
        }

        if (rect.bottom < 0) {
            rect.bottom = 0;
            rect.top = getHeight();
        }
    }

    public LargeImageDisplay(Context context) {
        this(context, null);
    }

    public LargeImageDisplay(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onToucEvent(event);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = mRegionDecoder.decodeRegion(mPaintRect, OPTIONS);
        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int imageWidth = mImageWidth;
        int imageHeight = mImageHeight;

        mPaintRect.left = imageWidth / 2 - width / 2;
        mPaintRect.right = mPaintRect.left + width;
        mPaintRect.top = imageHeight / 2 - height / 2;
        mPaintRect.bottom = mPaintRect.top + height;
    }
}
