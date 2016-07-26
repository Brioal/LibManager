package com.brioal.libmanager.tool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brioal on 2016/7/20.
 */

public class DataLoader {
    private static DataLoader mLoder;

    public static DataLoader getInstance() {
        if (mLoder == null) {
            mLoder = new DataLoader();
        }
        return mLoder;
    }

    //获取本地测试的Activity列表
    public List<String> getTestList() {
        List<String> list = new ArrayList<>();
        list.add("不同颜色的圆形组件");
        list.add("背景渐变的引导界面");
        list.add("软键盘遮挡问题解决办法");
        list.add("原点内显示文字的自定义View");
        list.add("带文字的进度条");
        list.add("仿京东垂直文字跑马灯");
        list.add("冷启动优化实践1");
        list.add("冷启动优化实践2");
        list.add("冷启动优化实践3");
        return list;
    }
}
