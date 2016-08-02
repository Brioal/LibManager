package com.brioal.libmanager.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.brioal.baselib.util.ColorUtil;
import com.brioal.baselib.util.StatusBarUtils;
import com.brioal.libmanager.R;
import com.brioal.libmanager.adapter.GuideViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 渐变背景的ViewPgaer使用
 * 其他地方与ViewPgaer的简单使用一样
 * 1.添加OnPageChangeListener
 * 2.根据offset来获取渐变的颜色值
 * 3.设置ViewPgaer的背景和标题栏为指定的颜色
 */
public class GradualGuideActivity extends TestBaseActivity {
    static {
        title = "背景渐变的引导界面";
    }

    @Bind(R.id.act_guide_vp_guide)
    ViewPager mVpGuide;

    int[] colors = new int[]{
            R.color.colorGuide1,
            R.color.colorGuide2,
            R.color.colorGuide3,
            R.color.colorGuide4
    };

    private GuideViewPager mAdapter;

    @Override
    public void initBar() {
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setContentView(R.layout.act_gradual_guide);
        ButterKnife.bind(this);
        mAdapter = new GuideViewPager(mContext);
        mVpGuide.setAdapter(mAdapter);
        mVpGuide.setBackgroundColor(getResources().getColor(R.color.colorGuide1));
        mVpGuide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int color = ColorUtil.getGradualColor(getResources().getColor(colors[position % colors.length]), getResources().getColor(colors[(position + 1) % colors.length]), positionOffset);
                mVpGuide.setBackgroundColor(color);
                StatusBarUtils.setColor(mContext, color);
            }

            @Override
            public void onPageSelected(int position) {
                mVpGuide.setBackgroundColor(colors[position]);
                StatusBarUtils.setColor(mContext, colors[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
