package com.brioal.uilib.elasticscrollview;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.brioal.baselib.util.klog.KLog;

/**
 * 弹性滑动测试
 * Created by Brioal on 2016/7/30.
 */

public class ElasticScrollView extends ViewGroup {
    private int mTouchSlop; //触发滑动的最小尺寸

    private float mLastXIntercept = 0;
    private float mLastYIntercept = 0;

    private float mLastX = 0;
    private float mLastY = 0;

    private int mLeftBoard;
    private int mRightBoard;

    private Scroller mScroller;

    public ElasticScrollView(Context context) {
        this(context, null);
    }

    public ElasticScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTouchSlop(context);
    }

    private void initTouchSlop(Context context) {
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
        mScroller = new Scroller(context);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        float xIntercept = ev.getX();
        float yIntercept = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                float deltaX = xIntercept - mLastXIntercept;
                float deltaY = yIntercept - mLastYIntercept;
                //主导滑动的是X方向的&&滑动大于最小触发滑动操作
                if (Math.abs(deltaX) > Math.abs(deltaY) && Math.abs(deltaX) > mTouchSlop) {
                    intercept = true; //接收滑动事件
                } else {
                    intercept = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }
        mLastX = xIntercept;
        mLastY = yIntercept;
        mLastXIntercept = xIntercept;
        mLastYIntercept = yIntercept;
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float xTouch = event.getX();
        float yTouch = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished())
                    mScroller.abortAnimation();
                break;
            //能获取到移动事件说明已经是满足移动的条件了的
            case MotionEvent.ACTION_MOVE:
                float deltaX = xTouch - mLastX;
                float mScrollOffset = deltaX;
                KLog.i("deltaX" + deltaX);
                //边界处理
                if (getScrollX() - deltaX < mLeftBoard) {//左滑到达左边界
                    mScrollOffset = deltaX/3;
                } else if (getScrollX() + getWidth() - deltaX > mRightBoard) {//右滑超出右边界
                    mScrollOffset = deltaX/3;
                }
                scrollBy((int) -mScrollOffset, 0);
                break;
            case MotionEvent.ACTION_UP:
                //手指抬起时,根据滑动的尺寸返回来判断该滑动到哪个界面
                int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
                KLog.i("targetIndex:" + targetIndex);
                if (targetIndex>getChildCount()-1){
                    targetIndex = getChildCount()-1;
                    //如果超过左边界，则回弹到第一个View
                }else if (targetIndex<0){
                    targetIndex =0;
                }
                int offset = targetIndex * getWidth() - getScrollX();
                mScroller.startScroll(getScrollX(), 0, offset, 0);
                invalidate();
                break;
        }

        mLastX = xTouch;
        mLastY = yTouch;

        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        if (b) {
            int childCount = getChildCount();
            for (int j = 0; j < childCount; j++) {
                View childView = getChildAt(j);
                childView.layout(j * getMeasuredWidth(), 0, j * getMeasuredWidth() + getPaddingLeft() + childView.getMeasuredWidth(), getMeasuredHeight());
            }
            mLeftBoard = 0;
            mRightBoard = getChildCount() * getMeasuredWidth();
        }

    }
}
