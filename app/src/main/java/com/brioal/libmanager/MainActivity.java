package com.brioal.libmanager;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.brioal.baselib.base.BaseActivity;
import com.brioal.libmanager.adapter.MainTestAdapter;
import com.brioal.libmanager.itemanimator.ItemAnimatorFactory;
import com.brioal.libmanager.tool.DataLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MainActivity extends BaseActivity {


    @Bind(R.id.act_main_title)
    TextView mTvTitle;
    @Bind(R.id.act_main_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.main_test_list)
    RecyclerView mRecyclerView;
    @Bind(R.id.act_main_head)
    ImageView mHead;
    private int mContentViewHeight;
    private int mScreenWidth;
    private List<String> mList;
    private MainTestAdapter mAdapter;


    @Override
    public void initData() {
        super.initData();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        mScreenWidth = dm.widthPixels;
        Observable.timer(500, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                startAnimation();
            }
        });

    }

    //开始动画
    private void startAnimation() {
        setTheme(R.style.AppTheme_NoActionbar);//设置现主题
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //标题栏文字变化
        ViewCompat.animate(mTvTitle).alpha(1).start(); //逐渐可见
        ViewCompat.animate(mHead).translationY(10).scaleX(0.7f).scaleY(0.7f).translationX(mScreenWidth / 2 - 100).start();
        mList = new ArrayList<>();

        //列表添加动画
        mAdapter = new MainTestAdapter(mContext, mList);//设置空数据源
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setItemAnimator(ItemAnimatorFactory.slidein()); //设置Iem动画
        mRecyclerView.setAdapter(mAdapter);

        //ToolBar变化
        mToolbar.getViewTreeObserver().addOnPreDrawListener( //绘制内容之前要做的工作
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        mToolbar.getViewTreeObserver().removeOnPreDrawListener(this);
                        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

                        mToolbar.measure(widthSpec, heightSpec);
                        mContentViewHeight = mToolbar.getHeight();
                        collapseToolbar();
                        return true;
                    }
                });
    }

    private void collapseToolbar() { //收缩ToolBar
        int toolBarHeight;
        TypedValue tv = new TypedValue();
        getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true); //ToolBar该有的高度
        toolBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());//dp转换为像素值
        ValueAnimator valueHeightAnimator = ValueAnimator.ofInt(mContentViewHeight, toolBarHeight);
        valueHeightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ViewGroup.LayoutParams lp = mToolbar.getLayoutParams();
                lp.height = (Integer) animation.getAnimatedValue();
                mToolbar.setLayoutParams(lp);
            }
        });

        valueHeightAnimator.start();
        valueHeightAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation); //动画结束添加所有数据
                mAdapter.addAll(DataLoader.getInstance().getTestList());
            }
        });
    }


}
