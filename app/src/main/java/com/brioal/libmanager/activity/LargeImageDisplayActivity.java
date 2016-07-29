package com.brioal.libmanager.activity;

import android.os.Bundle;

import com.brioal.libmanager.R;
import com.brioal.uilib.largeimagedisplay.LargeImageDisplay;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LargeImageDisplayActivity extends TestActivity {

    static {
        title = "大图加载测试";
    }

    @Bind(R.id.act_large_image)
    LargeImageDisplay mIvImage;

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setContentView(R.layout.act_large_image_display);
        ButterKnife.bind(this);
        try {
            mIvImage.setInputStream(getAssets().open("qm.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
