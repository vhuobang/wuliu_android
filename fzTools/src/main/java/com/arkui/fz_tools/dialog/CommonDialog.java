package com.arkui.fz_tools.dialog;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arkui.fz_tools.R;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.utils.CornerUtils;

/**
 * Created by nmliz on 2017/6/22.
 * 通用的 删除或者其他提示框框
 */

public class CommonDialog extends BaseDialogFragment implements View.OnClickListener {
    private TextView mTvTitle;
    private TextView mTvContent;
    private String mTitle;
    private String mContent;
    private OnConfirmClick onConfirmClick;
    private TextView mTvConfirm;
    private TextView mTvCancel;
    private String mConfirmText;
    private boolean mIsCancel = true;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.layout_common_dialog, container, false);
    }

    @Override
    protected void initView(View mRootView) {
        mRootView.setBackgroundDrawable(CornerUtils.cornerDrawable(ContextCompat.getColor(getActivity(), R.color.white), dp2px(5)));
        mTvConfirm = (TextView) mRootView.findViewById(R.id.tv_confirm);
        mTvCancel = (TextView) mRootView.findViewById(R.id.tv_cancel);
        mTvTitle = (TextView) mRootView.findViewById(R.id.tv_title);
        mTvContent = (TextView) mRootView.findViewById(R.id.tv_content);
        mTvTitle.setText(mTitle);
        mTvContent.setText(mContent);
        mTvConfirm.setText(mConfirmText == null ? "确定" : mConfirmText);
        mTvConfirm.setOnClickListener(this);
        mTvCancel.setOnClickListener(this);

        mTvConfirm.setBackgroundDrawable(CornerUtils.cornerDrawable(ContextCompat.getColor(getActivity(), R.color.mainColor)
                , 0, 0, dp2px(5), 0));
        mTvCancel.setBackgroundDrawable(CornerUtils.cornerDrawable(0xFFEEEEEE
                , 0, 0, 0, dp2px(5)));
        if (!mIsCancel) {
            mTvConfirm.setBackgroundDrawable(CornerUtils.cornerDrawable(ContextCompat.getColor(getActivity(), R.color.mainColor)
                    , 0, 0, dp2px(5), dp2px(5)));
            mTvCancel.setVisibility(View.GONE);
        }
    }

    @Override
    protected float getWidthScale() {
        return 0.7f;
    }

    public CommonDialog setTitle(String title) {
        this.mTitle = title;
        return this;
    }

    public CommonDialog setContent(String content) {
        this.mContent = content;
        return this;
    }
    public String getContent(){
        if (mContent!=null){
            return mContent;
        }else {
            return "";
        }
    }

    public CommonDialog setConfirmText(String confirmText) {
        this.mConfirmText = confirmText;
        return this;
    }

    //隐藏掉取消按钮
    public CommonDialog setNoCancel() {
        mIsCancel = false;
        return this;
    }

    public CommonDialog setConfirmClick(OnConfirmClick onConfirmClick) {
        this.onConfirmClick = onConfirmClick;
        return this;
    }

    public CommonDialog setIsCanceledOnTouch(boolean IsCanceledOnTouch){
        this.mIsCanceledOnTouch=IsCanceledOnTouch;
        return this;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_confirm) {
            if (onConfirmClick != null) {
                onConfirmClick.onConfirmClick();
            }
        }
        dismiss();
    }
}
