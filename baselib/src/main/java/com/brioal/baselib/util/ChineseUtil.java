package com.brioal.baselib.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 判断字符串中是否含有中文
 * Created by brioal on 16-7-27.
 */
public class ChineseUtil {
    //判断字符串中是否含有中文
    public static boolean IsChineseExist(String string) {
        return !(string.getBytes().length == string.length()); //字节数与字符数相同,则无汉字,因为一个汉子跟其他符号一样只占一个字符,但是却占两个字节
    }

    //获取字符串当中的中文
    public static List<String> getChineses(String string) {
        List<String> strings = new ArrayList<>();
        if (string.getBytes().length == string.length()) {
            return strings;
        }
        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]+");
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            strings.add(matcher.group(0));
        }
        return strings;
    }


}
