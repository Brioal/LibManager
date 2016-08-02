package com.brioal.baselib.util;

import android.graphics.Color;

/**
 * 根据起始颜色,终止颜色与偏移值来产生渐变的颜色
 * Created by Brioal on 2016/7/21.
 */

public class ColorUtil {
    public static int getGradualColor(int fromColor, int toColor, float offset) {
        int fromR = Color.red(fromColor);
        int fromG = Color.green(fromColor);
        int fromB = Color.blue(fromColor);

        int toR = Color.red(toColor);
        int toG = Color.green(toColor);
        int toB = Color.blue(toColor);

        int diffR = toR - fromR;
        int diffG = toG - fromG;
        int diffB = toB - fromB;

        int red = fromR + (int) (diffR * offset);
        int green = fromG + (int) (diffG * offset);
        int blue = fromB + (int) (diffB * offset);

        return Color.rgb(red, green, blue);
    }
}
