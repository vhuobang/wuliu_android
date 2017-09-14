package com.arkui.fz_tools.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_tools.R;
import com.arkui.fz_tools.listener.OnDialogClick;

/**
 * Created by nmliz on 2017/6/23.
 * 这是高级版 带分享按钮的
 */

public class SuccessFullyShareDialog extends BaseDialogFragment implements View.OnClickListener {


    OnDialogClick onConfirmClick;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        mIsCanceledOnTouch = false;
        return inflater.inflate(R.layout.dialog_success_fully_share, container, false);
    }

    @Override
    protected void initView(View mRootView) {
        mRootView.findViewById(R.id.tv_share).setOnClickListener(this);
        mRootView.findViewById(R.id.tv_cancel).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.tv_cancel){
            if(onConfirmClick!=null)
                onConfirmClick.onCancelClick();
        }else{
            if(onConfirmClick!=null)
                onConfirmClick.onConfirmClick();
        }
    }

    public void setOnConfirmClick(OnDialogClick onConfirmClick) {
        this.onConfirmClick = onConfirmClick;
    }

}
