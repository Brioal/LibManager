package com.brioal.uilib.swipemenu;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

import com.brioal.baselib.util.SizeUtil;
import com.brioal.baselib.util.klog.KLog;

/**
 * 侧滑菜单的实现
 * Created by Brioal on 2016/7/31.
 */

public class SwipeMenu extends ViewGroup {
    private static final int TYPE1 = 1; //跟随拖动
    private static final int TYPE2 = 2; //跟随拖动,大小缩放
    private static final int TYPE3 = 3; //固定不动
    private static final int TYPE4 = 4;//固定不动,大小缩放
    private static final int TYPE5 = 5;//缓慢移动
    private static final int TYPE6 = 6;//缓慢移动,大小缩放
    private int mTouchSlop = 0;

    private float xIntercept = 0;
    private float yIntercept = 0;

    private float xLast = 0;
    private float yLast = 0;
    private int mMenuOffset = 100;

    private Scroller mScroller;
    private int mScreenWidth;
    private int mScreenHeight;

    private View mMenuView;
    private View mContentView;
    private int mType = TYPE1;
    private int mExpandMaxOffset = 0; //侧边拖动的偏移值 .不设置默认为全屏拖动
    private boolean isMeasured = false; //是否已经测量过
    private boolean isShowMenu = false; //是否已经显示了菜单


    public SwipeMenu(Context context) {
        this(context, null);
    }

    public SwipeMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTouchSlop(context);
    }

    //设置动画形式
    public void setType(int type) {
        mType = type;
        mMenuView.setTranslationX(0);
        mMenuView.setScaleX(1);
        mMenuView.setScaleY(1);
    }

    //设置侧边拖拽的偏移量 默认为全屏
    public void setExpandMaxOffset(int expandMaxOffset) {
        mExpandMaxOffset = expandMaxOffset;
    }

    //是否显示菜单
    public boolean isShowMenu() {
        if (getScrollX() <= 0) {
            isShowMenu = true;
        }
        return isShowMenu;
    }

    //初始化最小滑动距离
    public void initTouchSlop(Context context) {
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
        mScroller = new Scroller(context);
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
        mScreenHeight = metrics.heightPixels;
        mExpandMaxOffset = 100;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        boolean intercept = false;
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (!isShowMenu()) {
                    if (x >= SizeUtil.Dp2Px(getContext(), mExpandMaxOffset)) {
                        return false;
                    }
                }
                KLog.i(x + getScrollX());
                KLog.i(getScrollX() + SizeUtil.Dp2Px(getContext(), mExpandMaxOffset));
                if (x + getScrollX() < mScreenWidth + mExpandMaxOffset) {
                    float xDelta = x - xIntercept;
                    float yDelta = y - yIntercept;
                    if (Math.abs(xDelta) > Math.abs(yDelta) && Math.abs(xDelta) > mTouchSlop) { //X滑动主导
                        intercept = true;
                    } else {
                        intercept = false;
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }
        xLast = x;
        yLast = y;
        xIntercept = x;
        yIntercept = y;
        return intercept;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float x = event.getX();
        float y = event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float xDelta = x - xLast;
                float offset = xDelta;
                touchMove_deal(offset);
                break;
            case MotionEvent.ACTION_UP:
                touchUp_deal();
                break;
        }
        xLast = x;
        yLast = y;
        return super.onTouchEvent(event);
    }


    //滑动处理
    private void touchMove_deal(float offset) {
        if (getScrollX() - offset <= 0) {
            offset = 0;
        } else if (getScrollX() + mScreenWidth - offset > mScreenWidth * 2 - mMenuOffset) {
            offset = 0;
        }
        scrollBy((int) (-offset), 0); //跟随拖动
        //最小缩放值
        float scale = 1 - getScrollX() * 1.0f / (mScreenWidth - mMenuOffset) * (1 - mMinScale);
        boolean Change = true; //是否移动
        boolean Under = false; //是否层叠
        boolean Scale = false; //是否缩放
        switch (mType) {
            case TYPE1: //跟随手指移动,不层叠,不缩反
                Change = true;
                Under = false;
                Scale = false;
                break;
            case TYPE2: // 跟随手指移动,不层叠,缩放
                Change = true;
                Under = false;
                Scale = true;
                break;
            case TYPE3: //不移动,层叠,不缩放
                Change = false;
                Under = true;
                Scale = false;
                break;
            case TYPE4://不移动,层叠,缩放
                Change = false;
                Under = true;
                Scale = true;
                break;
            case TYPE5://移动,层叠,缩放
                Change = true;
                Under = true;
                Scale = true;
                break;
            case TYPE6://移动,层叠,不缩放
                Change = true;
                Under = true;
                Scale = false;
                break;
        }
        if (Under) { //层叠
            float transX = 0;
            transX = getScrollX();
            if (Change) { //移动
                transX = getScrollX() * scale;
            }
            mMenuView.setTranslationX(transX);
        }
        if (Scale) {
            mMenuView.setScaleX(scale);
            mMenuView.setScaleY(scale);
        }

    }


    private float mMinScale = 0.7f;

    //抬起处理
    private void touchUp_deal() {
        if (getScrollX() < (mScreenWidth - mMenuOffset) / 2) {
            //滑动菜单
            showMenu();
        } else {
            //滑动到内容
            showContent();
        }
    }


    //显示菜单1
    public void showMenu() {
        mScroller.startScroll(getScrollX(), 0, 0 - getScrollX(), 0);
        invalidate();
    }

    //显示内容1
    public void showContent() {
        mScroller.startScroll(getScrollX(), 0, mScreenWidth - mMenuOffset - getScrollX(), 0);
        invalidate();
    }


    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            float scale = 1 - getScrollX() * 1.0f / (mScreenWidth - mMenuOffset) * (1 - mMinScale);
            boolean Change = true; //是否移动
            boolean Under = false; //是否层叠
            boolean Scale = false; //是否缩放
            switch (mType) {
                case TYPE1: //跟随手指移动,不层叠,不缩反
                    Change = true;
                    Under = false;
                    Scale = false;
                    break;
                case TYPE2: // 跟随手指移动,不层叠,缩放
                    Change = true;
                    Under = false;
                    Scale = true;
                    break;
                case TYPE3: //不移动,层叠,不缩放
                    Change = false;
                    Under = true;
                    Scale = false;
                    break;
                case TYPE4://不移动,层叠,缩放
                    Change = false;
                    Under = true;
                    Scale = true;
                    break;
                case TYPE5://移动,层叠,缩放
                    Change = true;
                    Under = true;
                    Scale = true;
                    break;
                case TYPE6://移动,层叠,不缩放
                    Change = true;
                    Under = true;
                    Scale = false;
                    break;
            }
            if (Under) { //层叠
                float transX = 0;
                transX = getScrollX();
                if (Change) { //移动
                    transX = getScrollX() * scale;
                }
                mMenuView.setTranslationX(transX);
            }
            if (Scale) {
                mMenuView.setScaleX(scale);
                mMenuView.setScaleY(scale);
            }
            postInvalidate();
        }
        super.computeScroll();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isMeasured) {
            mMenuView = getChildAt(0);
            mContentView = getChildAt(1);
            measureChild(mMenuView, widthMeasureSpec, heightMeasureSpec);
            measureChild(mContentView, widthMeasureSpec, heightMeasureSpec);
            isMeasured = true;
        }
        setMeasuredDimension(mScreenWidth * 2 - mMenuOffset, mScreenHeight);
    }


    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        if (b) {
            mMenuView = getChildAt(0);
            mContentView = getChildAt(1);
            mContentView.setClickable(true);
            mMenuView.setClickable(true);

            mMenuView.layout(0, 0, mScreenWidth - mMenuOffset, mScreenHeight);
            mContentView.layout(mScreenWidth - mMenuOffset, 0, mScreenWidth - mMenuOffset + mScreenWidth, mScreenHeight);
            scrollTo(mScreenWidth - mMenuOffset, 0);
        }
    }
}
