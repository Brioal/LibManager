package com.brioal.libmanager.tool;

import com.brioal.libmanager.MainActivity;
import com.brioal.libmanager.activity.AdTextViewActivity;
import com.brioal.libmanager.activity.BoardPaintActivity;
import com.brioal.libmanager.activity.CircleHeadActivity;
import com.brioal.libmanager.activity.CirclePointActivity;
import com.brioal.libmanager.activity.ColdStartPracticeOne;
import com.brioal.libmanager.activity.ColdStartPracticeTwo;
import com.brioal.libmanager.activity.GradualGuideActivity;
import com.brioal.libmanager.activity.LargeImageDisplayActivity;
import com.brioal.libmanager.activity.LineProgressActivity;
import com.brioal.libmanager.activity.RetrofitTestActivity;
import com.brioal.libmanager.activity.SoftInputAdjustActivity;
import com.brioal.libmanager.activity.WatchBoardActivity;
import com.brioal.libmanager.entity.DemoEntity;

import java.util.ArrayList;
import java.util.List;

/**数据加载类
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
    public List<DemoEntity> getTestList() {
        List<DemoEntity> list = new ArrayList<>();
        list.add(new DemoEntity("自定义View实现手表表盘", WatchBoardActivity.class));
        list.add(new DemoEntity("自定义画板", BoardPaintActivity.class));
        list.add(new DemoEntity("Retrofit实践", RetrofitTestActivity.class));
        list.add(new DemoEntity("大图加载", LargeImageDisplayActivity.class));
        list.add(new DemoEntity("冷启动优化实践1", ColdStartPracticeOne.class));
        list.add(new DemoEntity("冷启动优化实践2", ColdStartPracticeTwo.class));
        list.add(new DemoEntity("冷启动优化实践3", MainActivity.class));
        list.add(new DemoEntity("带文字的进度条", LineProgressActivity.class));
        list.add(new DemoEntity("仿京东垂直文字跑马灯", AdTextViewActivity.class));
        list.add(new DemoEntity("原点内显示文字的自定义View", CircleHeadActivity.class));
        list.add(new DemoEntity("软键盘遮挡问题解决办法", SoftInputAdjustActivity.class));
        list.add(new DemoEntity("背景渐变的引导界面", GradualGuideActivity.class));
        list.add(new DemoEntity("不同颜色的圆形组件", CirclePointActivity.class));
        return list;
    }
}
