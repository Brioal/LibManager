package com.brioal.libmanager.activity;

import android.view.MenuItem;

import com.brioal.baselib.swipeback.app.SwipeBackActivity;

/**
 * 基类,封装标题
 * Created by Brioal on 2016/7/21.
 */

public class TestActivity extends SwipeBackActivity {

    public static String title = "BaseActivity";

    @Override
    public void initBar() {
        super.initBar();
        try {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
