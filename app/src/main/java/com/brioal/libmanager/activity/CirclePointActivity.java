package com.brioal.libmanager.activity;

import android.os.Bundle;

import com.brioal.libmanager.R;

/**
 * 圆点组件测试类
 */
public class CirclePointActivity extends TestBaseActivity {
    static {
        title = "不同颜色的圆形组件";
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setContentView(R.layout.act_circle_point);
    }


}
