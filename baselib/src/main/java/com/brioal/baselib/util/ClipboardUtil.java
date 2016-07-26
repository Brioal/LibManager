package com.brioal.baselib.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * 剪贴板管理类
 * Created by Brioal on 2016/6/3.
 */

public class ClipboardUtil {
    //从剪贴板获取内容
    public static String getContent(Context context) {
        final ClipboardManager cm = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        ClipData data = cm.getPrimaryClip();
        String result = null;
        if (data != null) {
            ClipData.Item item = data.getItemAt(0);
            result = item.getText().toString();
        }
        return result;
    }

    //复制内容到剪贴板
    public static void setContent(Context context, String content) {
        ClipboardManager c = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        c.setText(content);//设置Clipboard 的内容
        ToastUtils.showToast(context, "复制内容到剪贴板成功", ExtraToast.LENGTH_SHORT);
    }
}
