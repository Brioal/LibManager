package com.brioal.libmanager.activity;

import android.os.Bundle;

import com.brioal.libmanager.R;
import com.brioal.uilib.paintboard.PaintBoard;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BoardPaintActivity extends TestBaseActivity {
    static {
        title = "自定义View画板";
    }

    @Bind(R.id.board_paint)
    PaintBoard mBoardPaint;

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setContentView(R.layout.act_board_paint);
        ButterKnife.bind(this);
    }

}
