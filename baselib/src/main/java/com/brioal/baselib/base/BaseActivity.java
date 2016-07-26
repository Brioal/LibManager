package com.brioal.baselib.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.brioal.baselib.interfaces.ActivityFormat;
import com.brioal.baselib.util.klog.KLog;


/**
 * Activity的基类
 * Created by mm on 2016/6/4.
 */

public class BaseActivity extends AppCompatActivity implements ActivityFormat {


    public String TAG = "BaseActivity";
    protected final int TYPE_SET_VIEW = 0;
    protected final int TYPE_UPDATE_VIEW = 1;
    protected Activity mContext;
    protected Runnable mThreadLocal = new Runnable() {
        @Override
        public void run() {
            loadDataLocal();
        }
    };
    protected Runnable mThreadNet = new Runnable() {
        @Override
        public void run() {
            loadDataNet();
        }
    };
    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == TYPE_SET_VIEW) {
                KLog.i(TAG, "SetView");
                setView();
            } else if (msg.what == TYPE_UPDATE_VIEW) {
                KLog.i(TAG, "UpdateView");
                updateView();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        KLog.i(TAG, "onCreate");
        initData();
        initView(savedInstanceState);
        initBar();
        new Thread(mThreadLocal).start();
        new Thread(mThreadNet).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        KLog.i(TAG, "onResume");
        initTheme();
    }

    @Override
    protected void onStart() {
        super.onStart();
        KLog.i(TAG, "onStart");
    }

    @Override

    protected void onPause() {
        super.onPause();
        KLog.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        KLog.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KLog.i(TAG, "onDestory");
        saveDataLocal();
    }

    @Override
    public void initData() {
        KLog.i(TAG, "initData");

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        KLog.i(TAG, "initView");

    }

    @Override
    public void initBar() {
        KLog.i(TAG, "initBar");

    }

    @Override
    public void initTheme() {
        KLog.i(TAG, "initTheme");
    }

    @Override
    public void loadDataLocal() {
        KLog.i(TAG, "loadDataLocal");
    }

    @Override
    public void loadDataNet() {
        KLog.i(TAG, "loadDataNet");
    }

    @Override
    public void setView() {
        KLog.i(TAG, "setView");
    }

    @Override
    public void updateView() {
        KLog.i(TAG, "UpdateView");
    }

    @Override
    public void saveDataLocal() {
        KLog.i(TAG, "saveDataLocal");
    }


    public static void startActivity(Context context, Class activity) {
        Intent intent = new Intent(context, activity);
        context.startActivity(intent);
    }
}
