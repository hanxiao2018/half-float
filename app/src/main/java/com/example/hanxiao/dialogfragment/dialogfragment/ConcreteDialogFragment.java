package com.example.hanxiao.dialogfragment.dialogfragment;

import android.os.Bundle;

import com.example.hanxiao.dialogfragment.R;
import com.example.hanxiao.dialogfragment.controller.IViewController;
import com.example.hanxiao.dialogfragment.events.FlipperRequestEvent;
import com.example.hanxiao.dialogfragment.events.UpdateViewEvent;
import com.example.hanxiao.dialogfragment.util.BusHolder;
import com.squareup.otto.Subscribe;

/**
 * Created by wenzhi on 17/4/19.
 */

public class ConcreteDialogFragment extends BaseDialogFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BusHolder.getBus().register(this);
    }

    @Subscribe
    public void receiveFlipperRequestEvent(FlipperRequestEvent event) {
        if (event.showNext) {
            setNextDisplayedChild(event.whichChild);
            return;
        }
        if (mViewFlipper.getChildAt(0) == mViewFlipper.getCurrentView()) {
            dismiss();
            return;
        }
        setPreviousDisplayedChild(event.whichChild);
    }

    @Subscribe
    public void updateView(UpdateViewEvent event) {
        IViewController viewController = (IViewController) getChildViewInViewFlipper(event.whichChild);
        viewController.updateView(event);
    }

    @Override
    public void onPause() {
        super.onPause();
        //此处用来清除每次pause后的弹出动画，如果需要每次都展示进入动画，则可以屏蔽该代码
        if (getDialog() != null) {
            getDialog().getWindow().setWindowAnimations(R.style.window_exit_style);
        }
    }

    @Override
    protected int getContentLayout() {
        return R.layout.demo_base_container;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BusHolder.getBus().unregister(this);
    }
}
