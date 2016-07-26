package com.brioal.libmanager.activity;

import android.view.MenuItem;

import com.brioal.baselib.swipeback.app.SwipeBackActivity;

/**
 * Created by Brioal on 2016/7/21.
 */

public class TextBaseActivity extends SwipeBackActivity {

    public static String title = "BaseActivity";

    @Override
    public void initBar() {
        super.initBar();
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
