package com.brioal.libmanager.activity;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.brioal.baselib.util.SizeUtil;
import com.brioal.libmanager.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ColdStartPracticeTwo extends TestActivity {

    @Bind(R.id.act_cold_one_title1)
    TextView mTvTitle1;
    @Bind(R.id.act_cold_one_title2)
    TextView mTvTitle2;
    @Bind(R.id.act_cold_one_button1)
    Button mButton1;
    @Bind(R.id.act_cold_one_button2)
    Button mButton2;
    @Bind(R.id.act_cold_one_container)
    LinearLayout mContainer;
    @Bind(R.id.act_cold_one_head)
    ImageView mIvHead;

    private boolean animationStarted = false;
    private int ITEM_DELAY = 200;

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setTheme(R.style.AppTheme_NoActionbar);
        setContentView(R.layout.act_cold_start_practice_two);
        ButterKnife.bind(this);
    }

    @Override
    public void initBar() {
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!hasFocus || animationStarted) {
            return;
        }
        animation();
        super.onWindowFocusChanged(hasFocus);

    }

    //开始动画
    private void animation() {
        ViewCompat.animate(mIvHead).translationY(-SizeUtil.Dp2Px(mContext,150)).setStartDelay(300).setDuration(1000).setInterpolator(new AccelerateDecelerateInterpolator()).start(); //head开始
        for (int i = 0; i < mContainer.getChildCount(); i++) {
            View v = mContainer.getChildAt(i);
            ViewPropertyAnimatorCompat viewAnimator = null;
            if (!(v instanceof Button)) {
                viewAnimator = ViewCompat.animate(v).translationY(50).alpha(1).setStartDelay(ITEM_DELAY * i + 500).setDuration(1000);
            } else {
                viewAnimator = ViewCompat.animate(v).translationY(50).scaleX(1).scaleY(1).setStartDelay(ITEM_DELAY * i + 500).setDuration(600);
            }
            viewAnimator.setInterpolator(new AccelerateDecelerateInterpolator()).start();
        }

    }
}
