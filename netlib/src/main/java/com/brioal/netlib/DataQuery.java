package com.brioal.netlib;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;


/**数据查询类
 * Created by Brioal on 2016/6/4.
 */

public class DataQuery<T> {
    private final int TYPE_EQUAL = 0; //query.addWhereEqualTo()
    private final int TYPE_CONTAIN = 1; //query.addWhereContains()
    private final String TAG = "DataQueryInfo";

    /*
    查询多个数据

    条目限制 ,
    跳过的数量,
    排序规则 ,
    添加条件的类别 ,
    条件的键,
    条件的值,
    监听器
    * */
    public void getDatas(Context context, int queryLimit, int skipCount, String order, int conditionType, String conditionKey, Object conditionValue, FindListener<T> listener) {
        List<T> t = new ArrayList<>();
        BmobQuery<T> query = new BmobQuery<>();
        query.setLimit(queryLimit); //设置查询的数据条目
        query.setSkip(skipCount); //跳过的条目
        query.order(order); //设置排序方式
        if (conditionType != -1) {
            switch (conditionType) { //设置查询条件
                case 0:
                    query.addWhereEqualTo(conditionKey, conditionValue);
                    break;
                case 1:
                    query.addWhereContains(conditionKey, (String) conditionValue);
                    break;
                // TODO: 2016/6/4 其他用到再添加
            }
        }

        query.findObjects(context, listener);
    }

    /*
    根据Onjectid查询单个数据
    context
    objectId
    监听器
    */
    public void getData(Context context, String onjectId, GetListener<T> listener) {
        BmobQuery<T> query = new BmobQuery<>();
        query.getObject(context, onjectId, listener);
    }
}
