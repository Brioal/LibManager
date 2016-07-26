package com.brioal.rxlearnapp;

import android.os.Bundle;

import com.brioal.baselib.base.BaseActivity;
import com.brioal.rxlearnapp.activity.MainActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

public class LauncherActivity extends BaseActivity {

    @Override
    public void initData() {
        setTheme(R.style.AppTheme_NoActionbar);
        super.initData();
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setContentView(R.layout.act_launcher);
        //2秒后进入MainActivity
        Observable.timer(1, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                MainActivity.startActivity(mContext, MainActivity.class);
            }
        });
    }

}
