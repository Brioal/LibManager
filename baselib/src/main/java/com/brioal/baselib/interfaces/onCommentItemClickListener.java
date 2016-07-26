package com.brioal.baselib.interfaces;

/**回复列表的点击监听
 * Created by Brioal on 2016/6/6.
 */
public interface onCommentItemClickListener {
    void onClickItem(String commentId, String parentId, String userId, String content);
}
