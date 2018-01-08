package com.example.hanxiao.dialogfragment.dialogfragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ViewFlipper;

import com.example.hanxiao.dialogfragment.R;

/**
 * Created by wenzhi on 17/4/19.
 */

public abstract class BaseDialogFragment extends DialogFragment {
    private FrameLayout mView;
    protected ViewFlipper mViewFlipper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = (FrameLayout) inflater.inflate(R.layout.view_flipper_base_layout, null);
        mViewFlipper = (ViewFlipper) mView.findViewById(R.id.view_flipper);
        inflater.inflate(getContentLayout(), mViewFlipper, true);
        return mView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDialog(getDialog());
    }

    private void initDialog(Dialog dialog) {
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(getStyle());
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.setBackgroundDrawable(new ColorDrawable());
    }

    /**
     * 当用户不自定义控件时,需要通过该方法获取相关view,以便为控件设置监听事件
     * 该方法可以获取ViewFlipper中的相应子布局,可解决id相同问题
     *
     * @param index,viewflipper中的第几个布局
     * @return
     */
    public View getChildViewInViewFlipper(int index) {
        return mViewFlipper.getChildAt(index);
    }

    public void showNext() {
        setInAndOutAnimation(showNextInAnimation(), showNextOutAnimation());
        mViewFlipper.showNext();
    }

    public void showPrevious() {
        setInAndOutAnimation(showPreviousInAnimation(), showPreviousOutAnimation());
        mViewFlipper.showPrevious();
    }

    public void setNextDisplayedChild(int whichChild) {
        setInAndOutAnimation(showNextInAnimation(), showNextOutAnimation());
        mViewFlipper.setDisplayedChild(whichChild);
    }

    public void setPreviousDisplayedChild(int whichChild) {
        setInAndOutAnimation(showPreviousInAnimation(), showPreviousOutAnimation());
        mViewFlipper.setDisplayedChild(whichChild);
    }

    private void setInAndOutAnimation(int inResID, int outResID) {
        mViewFlipper.setInAnimation(getActivity(), inResID);
        mViewFlipper.setOutAnimation(getActivity(), outResID);
    }

    /**
     * 获取填充的布局视图
     *
     * @return 布局的资源id，R.Layout.id
     */
    protected abstract int getContentLayout();

    /**
     * 获取下一页的跳转动画，如果满足不了子类需求，子类可进行复写
     *
     * @return
     */
    protected int showNextInAnimation() {
        return R.anim.view_anim_slide_in_from_right;
    }

    protected int showNextOutAnimation() {
        return R.anim.view_anim_slide_out_to_left;
    }

    protected int showPreviousInAnimation() {
        return R.anim.view_anim_slide_in_from_left;
    }

    protected int showPreviousOutAnimation() {
        return R.anim.view_anim_slide_out_to_right;
    }

    protected int getStyle() {
        return R.style.window_enter_and_exit_style;
    }
}
