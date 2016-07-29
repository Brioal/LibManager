package com.brioal.baselib.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 判断是否是数字的几种办法
 * Created by brioal on 16-7-26.
 */
public class DigitUtil {

    public static boolean isAllDigit(String string) {
        return string.matches("[0-9]{1,}");
    }

    //判断是否含有数字
    public static boolean isContainDigit(String s) {
        return s.matches(".*\\d+.*");  //.除\n以为的任何字符 *零次或者多次 + 一次或多次
    }

    //java自带的字符判断方式判断是否含有数字
    public static boolean isContainDight1(String string) {
        char[] chs = string.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            if (Character.isDigit(chs[i])) {
                //判断是否是数字的另外方法,同理可以判断是否全部为数字
                //判断assic码
                //48 <= chs[i] && chs[i] <= 57  数字的assic码在48~57之间
                //正则表达式
                //(chs[i] + "").matches("\\d");
                return true;
            }
        }
        return false;
    }

    //截取其中的数字
    public static List<String> getDigit(String string) {
        List<String> nums = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d+"); //数字
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            nums.add(matcher.group(0));
        }
        return nums;

    }

    //截取其中的非数字
    public static List<String> getNotDigit(String string) {
        List<String> nums = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\D+"); //非数字
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            nums.add(matcher.group(0));
        }
        return nums;

    }

}
