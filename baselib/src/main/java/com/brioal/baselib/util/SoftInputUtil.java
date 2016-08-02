package com.brioal.baselib.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * 软键盘管理类
 * <p>
 * <p>
 * 1.避免输入法面板遮挡
 * 2.动态隐藏软键盘
 * 3.改变软键盘的状态,弹出时隐藏,隐藏时弹出
 * 4.动态隐藏软键盘
 * 5.动态显示软键盘
 * Created by Brioal on 2016/6/6.
 */
public class SoftInputUtil {
    // 在manifest.xml中activity中设置
    //android:windowSoftInputMode="stateVisible|adjustResize"

    //改变软键盘的状态,弹出时隐藏,隐藏时弹出
    public static void judgeSoftInut(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    //动态隐藏软键盘
    public static void hideSoftInput(Activity activity) {
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) activity
                    .getSystemService(INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    //动态隐藏软键盘
    public static void hideSoftInput(Context context, EditText edit) {
        edit.clearFocus();
        InputMethodManager inputmanger = (InputMethodManager) context
                .getSystemService(INPUT_METHOD_SERVICE);
        inputmanger.hideSoftInputFromWindow(edit.getWindowToken(), 0);
    }
    //动态显示软键盘
    public static void showSoftInput(Context context, EditText edit) {
        edit.setFocusable(true);
        edit.setFocusableInTouchMode(true);
        edit.requestFocus();
        InputMethodManager inputManager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(edit, 0);
    }
}
