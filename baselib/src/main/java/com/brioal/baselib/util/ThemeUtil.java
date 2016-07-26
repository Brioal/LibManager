package com.brioal.baselib.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 从本地读取颜色属性
 * Created by Brioal on 2016/5/20.
 */

public class ThemeUtil {
    //保存颜色属性到本地
    public static void saveThemeColor(Context context, String color) {
        SharedPreferences preferences = context.getSharedPreferences("Brioal", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ThemeColor", color);
        editor.commit();
    }

    //从本地读取颜色属性
    public static String readThemeColor(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Brioal", Context.MODE_PRIVATE);
        return preferences.getString("ThemeColor", "#3eb6d1");
    }
}
