package com.brioal.libmanager.activity;

import android.os.Bundle;

import com.brioal.libmanager.R;

public class ElasticScrollActivity extends TestBaseActivity {

    static {
        title = "弹性滑动测试";
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setContentView(R.layout.act_elastic_scroll_test);
    }

}
