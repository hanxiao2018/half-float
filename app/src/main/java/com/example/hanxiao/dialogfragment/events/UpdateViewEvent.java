package com.example.hanxiao.dialogfragment.events;

/**
 * Created by wenzhi on 17/4/20.
 */

public class UpdateViewEvent<T> {
    public int whichChild;//更新那个子页面
    public String arg1;//提供预置的简单参数便于视图更新
    public String arg2;
    public boolean flag1;//提供预置的boolean标识判断
    public boolean flag2;
    public T obj;

    public UpdateViewEvent(int whichChild) {
        this.whichChild = whichChild;
    }
}
