package com.brioal.baselib.util;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * 软键盘管理类
 * Created by Brioal on 2016/6/6.
 */
public class SoftInputUtil {
    //改变软键盘的组黄台,弹出时隐藏,隐藏时弹出
    public static void judgeSoftInut(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
