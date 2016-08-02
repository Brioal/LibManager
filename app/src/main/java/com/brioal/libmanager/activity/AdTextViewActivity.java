package com.brioal.libmanager.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.brioal.uilib.entity.AdEntity;
import com.brioal.uilib.AdTextView.ADTextView;
import com.brioal.libmanager.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AdTextViewActivity extends TestBaseActivity {

    static {
        title = "仿京东垂直文字跑马灯";
    }

    @Bind(R.id.ad_textview)
    ADTextView mAdTextview;
    @Bind(R.id.act_adtext_speed)
    Spinner mSpSpeed;
    @Bind(R.id.act_adtext_interval)
    Spinner mSpInterval;

    private List<AdEntity> mList = new ArrayList<>();

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setContentView(R.layout.act_ad_text_view);
        ButterKnife.bind(this);
        mList.add(new AdEntity("前缀1", "内容1", "连接1"));
        mList.add(new AdEntity("前缀2", "内容2", "连接2"));
        mList.add(new AdEntity("前缀3", "内容3", "连接3"));
        mList.add(new AdEntity("前缀4", "内容4", "连接4"));
        mAdTextview.setFrontColor(Color.RED);
        mAdTextview.setBackColor(Color.BLACK);
        mAdTextview.setmTexts(mList);

        mSpSpeed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int speed = 1;
                switch (position) {
                    case 0:
                        speed = 1;
                        break;
                    case 1:
                        speed = 2;
                        break;
                    case 2:
                        speed = 3;
                        break;
                    case 3:
                        speed = 4;
                        break;
                    case 4:
                        speed = 5;
                        break;
                }
                mAdTextview.setSpeed(speed);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpInterval.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int interval = 1;
                switch (position) {
                    case 0:
                        interval = 500;
                        break;
                    case 1:
                        interval = 1000;
                        break;
                    case 2:
                        interval = 1500;
                        break;
                    case 3:
                        interval = 2000;
                        break;
                    case 4:
                        interval = 2500;
                        break;
                }
                mAdTextview.setInterval(interval);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
