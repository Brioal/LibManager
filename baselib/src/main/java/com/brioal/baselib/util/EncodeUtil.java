package com.brioal.baselib.util;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 加密工具类
 *
 * 1.MD5加密
 * 2.SHA加密
 * Created by Brioal on 2016/8/1.
 */

public class EncodeUtil {


    //MD5加密
    public static String encryptMD5(String data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return new BigInteger(md5.digest(data.getBytes())).toString(16);
    }

    //SHA加密
    public static String encryptSHA(String data) throws Exception {
        MessageDigest sha = MessageDigest.getInstance("SHA");
        return new BigInteger(sha.digest(data.getBytes())).toString(32);
    }


}
