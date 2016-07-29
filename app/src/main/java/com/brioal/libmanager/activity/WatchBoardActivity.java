package com.brioal.libmanager.activity;

import android.os.Bundle;

import com.brioal.libmanager.R;

public class WatchBoardActivity extends TestActivity {
    static {
        title = "自定义View实现表盘";
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setContentView(R.layout.act_watch_board);
    }
}
