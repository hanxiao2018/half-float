package com.example.hanxiao.dialogfragment.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hanxiao.dialogfragment.R;
import com.example.hanxiao.dialogfragment.controller.IViewController;
import com.example.hanxiao.dialogfragment.events.FlipperRequestEvent;
import com.example.hanxiao.dialogfragment.events.UpdateViewEvent;
import com.example.hanxiao.dialogfragment.test.data.UpdatePage1Data;
import com.example.hanxiao.dialogfragment.util.BusHolder;
import com.example.hanxiao.dialogfragment.util.ChildIndex;

public class DemoFlipperPage0View extends LinearLayout implements IViewController {
    private ImageView mRightIcon;
    private ImageView mLeftIcon;
    private TextView mPageTv;
    private TextView mTitle;


    public DemoFlipperPage0View(Context context) {
        super(context);
    }

    public DemoFlipperPage0View(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTitle = (TextView) findViewById(R.id.flipper_page_title);
        mPageTv = (TextView) findViewById(R.id.page_tv);
        mRightIcon = (ImageView) findViewById(R.id.img_right);
        mRightIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                BusHolder.getBus().post(new FlipperRequestEvent(ChildIndex.DEMO_FLIPPER_PAGE1, true));
            }
        });

        mLeftIcon = (ImageView) findViewById(R.id.img_left);
        mLeftIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                BusHolder.getBus().post(new FlipperRequestEvent(ChildIndex.DEMO_FLIPPER_PAGE0, false));
            }
        });

        mPageTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                方法1，简单的视图更新可以通过arg1等预置参数更新
//                UpdateViewEvent updateViewEvent = new UpdateViewEvent(ChildIndex.DEMO_FLIPPER_PAGE2);
//                updateViewEvent.whichChild = ChildIndex.DEMO_FLIPPER_PAGE2;
//                updateViewEvent.arg1 = "你好，page2！";

//                方法2，复杂的视图更新可以通过obj进行构造
                UpdateViewEvent<UpdatePage1Data> updateViewEvent = new UpdateViewEvent<>(ChildIndex.DEMO_FLIPPER_PAGE1);
                UpdatePage1Data updatePage1Data = new UpdatePage1Data();
                updatePage1Data.title = "我已改变!";
                updatePage1Data.body = "我是通过复杂方法进行构造的!";
                updateViewEvent.obj = updatePage1Data;
                BusHolder.getBus().post(updateViewEvent);
                Toast.makeText(getContext(), "已改变!", Toast.LENGTH_SHORT).show();
            }
        });
        mTitle.setText("第0页");
    }

    private void setTitle(String title) {
        mTitle.setText(title);
    }

    @Override
    public void updateView(UpdateViewEvent event) {
        setTitle(event.arg1);
    }
}
