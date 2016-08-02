package com.brioal.libmanager.activity;

import android.os.Bundle;

import com.brioal.libmanager.R;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class ColdStartPracticeOne extends TestBaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Observable.timer(1000, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                setTheme(R.style.LibAppTheme);
                setContentView(R.layout.act_cold_start_practice_one);
            }
        });
    }


    @Override
    public void initBar() {
    }
}
