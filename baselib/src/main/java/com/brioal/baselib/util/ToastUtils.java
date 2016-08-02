package com.brioal.baselib.util;

import android.content.Context;

import static android.R.id.message;

/**
 * 吐司显示类.
 */
public class ToastUtils {
    public static ExtraToast mToast;


    public static void showToast(Context context, String message) {
        showToast(context, message, ExtraToast.LENGTH_SHORT);
    }

    //显示字符串
    public static void showToast(final Context context, final String message, int duration) {
        if (mToast == null) {
            mToast = ExtraToast.makeText(context, message, duration);
        } else {
            mToast.setText(message);
        }
        mToast.show();
    }

    //显示资源文件
    public static void showToast(final Context context, final int messageResId, int duration) {
        if (mToast == null) {
            mToast = ExtraToast.makeText(context, messageResId, duration);
        } else {
            mToast.setText(message);
        }
        mToast.show();
    }

    //隐藏吐司
    public static void hideToast() {
        if (mToast == null) {
            return;
        }
        mToast.hide();
    }
}
