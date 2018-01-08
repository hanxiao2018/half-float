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
import com.example.hanxiao.dialogfragment.util.BusHolder;
import com.example.hanxiao.dialogfragment.util.ChildIndex;

public class DemoFlipperPage2View extends LinearLayout implements IViewController {

    private ImageView mRightIcon;
    private ImageView mLeftIcon;
    private TextView mTitle;

    public DemoFlipperPage2View(Context context) {
        super(context);
    }

    public DemoFlipperPage2View(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTitle = (TextView) findViewById(R.id.flipper_page_title);
        mRightIcon = (ImageView) findViewById(R.id.img_right);
        mRightIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                BusHolder.getBus().post(new FlipperRequestEvent(ChildIndex.DEMO_FLIPPER_PAGE2, true));
            }
        });

        mLeftIcon = (ImageView) findViewById(R.id.img_left);
        mRightIcon.setVisibility(View.GONE);
        mLeftIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                BusHolder.getBus().post(new FlipperRequestEvent(ChildIndex.DEMO_FLIPPER_PAGE1, false));
            }
        });
        mTitle.setText("第2页");

    }

    private void setTitle(String title) {
        mTitle.setText(title);
    }


    @Override
    public void updateView(UpdateViewEvent event) {
        setTitle(event.arg1);
    }
}
