package com.brioal.baselib.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by Brioal on 2016/6/17.
 */
public class DateUtil {
    //获取今天零时的毫秒值
    public  static long getZeroTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTimeInMillis();
    }

    //比较两个值是否在同一天
    public static boolean isSameDay(long first, long second) {
        if (DateUtil.getZeroTime(first) == DateUtil.getZeroTime(second)) {
            return true;
        }
        return false;
    }

    //获取几天前的日期
    public static int getDayFrom(int day) {
        Calendar cal = Calendar.getInstance();
        cal.roll(Calendar.DAY_OF_MONTH,-day);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.get(Calendar.DAY_OF_MONTH);
    }

    //获取昨天零时的毫秒值
    public static long yesterdayZeroTime() {
        Calendar cal = Calendar.getInstance();
        cal.roll(Calendar.DAY_OF_MONTH,-1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTimeInMillis();
    }
    //格式化花费的毫秒值时间
    public static String formatTimeCount(long time) {
        int seconds = (int) (time / 1000);
        int second = seconds % 60;
        int minute = seconds / 60 % 60;
        int hour = seconds / 60 / 60 % 60;
        String h = hour<10?"0"+hour:hour+"";
        String m = minute<10?"0"+minute:minute+"";
        String s = second<10?"0"+second:second+"";
        return h + ":" + m + ":" + s;
    }
    //格式化时间类毫秒值
    public static String formatTimeString(long time) {
        String result = "";
        Calendar calender = Calendar.getInstance();
        SimpleDateFormat f = new SimpleDateFormat("HH:mm");
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm:ss");
        calender.roll(Calendar.DAY_OF_MONTH,1);
        calender.set(Calendar.HOUR_OF_DAY,0);
        long today = DateUtil.getZeroTime(); //今天0点的时间
        System.out.println(format.format(today));
        calender.roll(Calendar.DAY_OF_MONTH,1);
        long yestaday = DateUtil.yesterdayZeroTime(); //z昨天0点的时间
        System.out.println(format.format(yestaday));
        if (time < yestaday) { //昨天之前
            result =format.format(time);
        } else if (time >= today) {
            result = "今天"+f.format(time);
        } else {
            result = "昨天 " + f.format(time);
        }
        return result;
    }

    //获取指定时间同一天的零点时间
    public static long getZeroTime(long time) {
        long current=System.currentTimeMillis();//当前时间毫秒数
        return current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
    }

}
