package com.brioal.libmanager.activity;

import android.os.Bundle;

import com.brioal.libmanager.R;

/**
 * 解决软件盘遮挡问题
 * 1.manifest文件添加windowSoftInputMode属性 adjustResize|stateVisible
 * 2.setContentView之前添加getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
 * 3.顶层容器换成ScrollView
 * 4.输入框获取焦点时隐藏上层某些组件
 */
public class SoftInputAdjustActivity extends TestBaseActivity {
    static {
        title = "软件盘遮挡解决办法";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_soft_input_adjust);
    }
}
