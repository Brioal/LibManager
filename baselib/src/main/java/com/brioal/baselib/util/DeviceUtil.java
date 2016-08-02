package com.brioal.baselib.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;

import java.io.File;

/**设备信息相关类
 * 1.获取设备MAC地址
 * 2.获取设备厂商
 * 3.获取手机型号
 * 4.获取设备SD卡是否可用
 * 5.获取设备SD卡路径
 * Created by Brioal on 2016/8/1.
 */

public class DeviceUtil {

    //获取设备MAC地址
    //需要权限android.permission.ACCESS_WIFI_STATE
    public static String getMacDevice(Context context) {
        String macAddress;
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        macAddress = info.getMacAddress();
        if (null == macAddress) {
            return "";
        }
        macAddress = macAddress.replace(":", "");
        return macAddress;
    }

    //获取设备厂商
    public static String getManuFacturer() {
        String manuFacturer = Build.MANUFACTURER;
        return manuFacturer;
    }

    //获取手机型号
    public static String getMode() {
        String model = Build.MODEL;
        if (model == null) {
            model = model.trim().replaceAll("\\s", "");
        } else {
            model = "";
        }
        return model;
    }

    //获取设备SD卡是否可用
    public static boolean isSDCardEnable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()); //检测SD
    }

    //获取设备SD卡路径
    public static String getSDPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }
}
