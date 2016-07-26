package com.brioal.libmanager.activity;

import android.os.Bundle;

import com.brioal.libmanager.R;

public class CircleHeadActivity extends TextBaseActivity {
    static {
        title = "圆点内显示文字的自定义View";
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_circle_head);
    }
}
