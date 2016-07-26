package com.brioal.baselib.interfaces;

import android.os.Bundle;

/**
 * Activity的格式化接口
 * Created by Brioal on 2016/6/4.
 */

public interface ActivityFormat {
    void initData(); //初始化数据

    void initView(Bundle savedInstanceState); //初始化View

    void initBar();//初始化ToolBar

    void initTheme(); //初始化主题设置

    void loadDataLocal(); //加载本地数据

    void loadDataNet(); //加载网络数据

    void setView(); //设置View

    void updateView(); //更新View

    void saveDataLocal(); //保存数据到本地

}
