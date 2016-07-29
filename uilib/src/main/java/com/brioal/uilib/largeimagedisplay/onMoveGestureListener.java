package com.brioal.uilib.largeimagedisplay;

/**
 * 手势移动监听器
 * Created by brioal on 16-7-26.
 */

public interface onMoveGestureListener {
    boolean onMoveBegin(MoveGestureDetector detector);

    boolean onMove(MoveGestureDetector detector);

    void onMoveEnd(MoveGestureDetector detector);
}
