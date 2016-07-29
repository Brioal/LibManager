package com.brioal.libmanager.activity;

import android.os.Bundle;

import com.brioal.libmanager.R;
import com.brioal.uilib.progressbar_text.LineProgressWithText;
import com.brioal.uilib.progressbar_text.SpinnerProgressBarWithText;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 线条带文字的进度条测试类
 */
public class LineProgressActivity extends TestActivity {


    static {
        title = "带文字的进度条";
    }

    @Bind(R.id.act_line_progress_pb_0)
    LineProgressWithText mProgressPb0;
    @Bind(R.id.act_line_progress_pb_1)
    LineProgressWithText mProgressPb1;
    @Bind(R.id.act_line_progress_pb_3)
    SpinnerProgressBarWithText mPb3;


    private int progress = 0;

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setContentView(R.layout.act_line_progress);
        ButterKnife.bind(this);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(TYPE_UPDATE_VIEW);
            }
        }, 1000, 80);
    }

    @Override
    public void updateView() {
        super.updateView();
        progress++;
        mProgressPb0.setProgress(progress % 100);
        mProgressPb1.setProgress(progress % 100);
        mPb3.setProgress(progress%100);
    }
}
