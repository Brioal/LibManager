package com.brioal.baselib.util;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**字体管理类
 * Created by Brioal on 2016/6/3.
 */

public class FontManager {
    //设置指定ViewGroup下的所有字体
    public static void changeFonts(ViewGroup root, Context context) {
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "font.ttf");
        for (int i = 0; i < root.getChildCount(); i++) {
            View v = root.getChildAt(i);
            if (v instanceof TextView) {
                ((TextView) v).setTypeface(tf);
            } else if (v instanceof Button) {
                ((Button) v).setTypeface(tf);
            } else if (v instanceof EditText) {
                ((EditText) v).setTypeface(tf);
            } else if (v instanceof ViewGroup) {
                changeFonts((ViewGroup) v, context);
            }
        }
    }
    //设置指定View的字体
    public static void changeFonts(View root, Context context) {
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "font.ttf");
            if (root instanceof TextView) {
                ((TextView) root).setTypeface(tf);
            } else if (root instanceof Button) {
                ((Button) root).setTypeface(tf);
            } else if (root instanceof EditText) {
                ((EditText) root).setTypeface(tf);
            } else if (root instanceof ViewGroup) {
                changeFonts((ViewGroup) root, context);
            }
        }
}
