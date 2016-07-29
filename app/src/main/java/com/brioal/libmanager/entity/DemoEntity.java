package com.brioal.libmanager.entity;

/**
 * 测试Demo的实体类
 * Created by brioal on 16-7-27.
 */

public class DemoEntity {
    private String mDesc;
    private Class mClass;

    public DemoEntity(String desc, Class aClass) {
        mDesc = desc;
        mClass = aClass;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String desc) {
        mDesc = desc;
    }

    public Class getclass() {
        return mClass;
    }

    public void setClass(Class aClass) {
        mClass = aClass;
    }
}
