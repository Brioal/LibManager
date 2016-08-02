package com.brioal.libmanager.activity;

import android.os.Bundle;

import com.brioal.baselib.base.BaseActivity;
import com.brioal.libmanager.R;
import com.brioal.uilib.swipemenu.SwipeMenu;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SwipeMenuActivity extends BaseActivity {


    @Bind(R.id.swipe_menu)
    SwipeMenu mSwipeMenu;
    int index = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_swipe_menu);

        ButterKnife.bind(this);

    }


}
