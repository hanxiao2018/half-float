package com.example.hanxiao.dialogfragment.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hanxiao.dialogfragment.R;
import com.example.hanxiao.dialogfragment.controller.IViewController;
import com.example.hanxiao.dialogfragment.events.FlipperRequestEvent;
import com.example.hanxiao.dialogfragment.events.UpdateViewEvent;
import com.example.hanxiao.dialogfragment.test.data.UpdatePage1Data;
import com.example.hanxiao.dialogfragment.util.BusHolder;
import com.example.hanxiao.dialogfragment.util.ChildIndex;

public class DemoFlipperPage1View extends LinearLayout implements IViewController {

    private ImageView mRightIcon;
    private ImageView mLeftIcon;
    private TextView mTitle;
    private TextView mBody;

    public DemoFlipperPage1View(Context context) {
        super(context);
    }

    public DemoFlipperPage1View(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mBody = (TextView) findViewById(R.id.body);
        mTitle = (TextView) findViewById(R.id.flipper_page_title);
        mRightIcon = (ImageView) findViewById(R.id.img_right);
        mRightIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                BusHolder.getBus().post(new FlipperRequestEvent(ChildIndex.DEMO_FLIPPER_PAGE2, true));
            }
        });

        mLeftIcon = (ImageView) findViewById(R.id.img_left);
        mLeftIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                BusHolder.getBus().post(new FlipperRequestEvent(ChildIndex.DEMO_FLIPPER_PAGE0, false));
            }
        });
        mTitle.setText("第1页");
    }

    private void setTitle(String title) {
        mTitle.setText(title);
    }

    private void setBody(String body) {
        mBody.setText(body);
    }

    @Override
    public void updateView(UpdateViewEvent event) {
        UpdatePage1Data data = (UpdatePage1Data) event.obj;
        setTitle(data.title);
        setBody(data.body);
    }
}
