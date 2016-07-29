package com.brioal.baselib.util;

import android.content.Context;
import android.util.TypedValue;

/**dp , sp , px 之间的转化
 * Created by Brioal on 2016/7/21.
 */

public class SizeUtil {
    //dpi转px
    public static float Dp2Px(Context context, float dpi) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpi, context.getResources().getDisplayMetrics());
    }

    //px转dp
    public static float Px2Dp(Context context, float px) {
        return  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, context.getResources().getDisplayMetrics());
    }

    //sp转px
    public static float Sp2Px(Context context, float sp) {
        return  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    //px转sp
    public static float Px2Sp(Context context, float px) {
        return  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, context.getResources().getDisplayMetrics());
    }
}
