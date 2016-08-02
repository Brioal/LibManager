package com.brioal.baselib.interfaces;

/**
 * 格式化Fragment的接口
 * Created by Brioal on 2016/6/4.
 */

public interface FragmentFormat {
    void initData(); //初始化数据

    void initView(); //初始化 布局

    void loadDataLocal(); //加载本地数据

    void loadDataNet(); // 加载数据

    void setView(); // 数据显示到布局

    void updateView(); //更新数据到界面

    void saveDataLocal(); //保存数据到本地

}
