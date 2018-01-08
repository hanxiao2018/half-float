package com.example.hanxiao.dialogfragment.events;

/**
 * Created by wenzhi on 17/4/19.
 */

public class FlipperRequestEvent {
    public final boolean showNext;//方向标识，true则展示下一页，否则展示前一页
    public final int whichChild;//子view在半浮层中的位置，从0开始

    public FlipperRequestEvent(int whichChild, boolean showNext) {
        this.whichChild = whichChild;
        this.showNext = showNext;
    }
}
