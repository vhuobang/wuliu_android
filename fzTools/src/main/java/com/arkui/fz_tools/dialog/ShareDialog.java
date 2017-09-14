package com.arkui.fz_tools.dialog;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_tools.R;
import com.arkui.fz_tools.listener.OnDialogClick;

/**
 * Created by nmliz on 2017/6/26.
 */

public class ShareDialog extends BaseDialogFragment implements View.OnClickListener {

    OnDialogClick onConfirmClick;

    public void setOnConfirmClick(OnDialogClick onConfirmClick) {
        this.onConfirmClick = onConfirmClick;
    }

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.dialog_share,container,false);
    }

    @Override
    protected void initView(View mRootView) {
        mRootView.findViewById(R.id.tv_cancel).setOnClickListener(this);
        mRootView.findViewById(R.id.wx).setOnClickListener(this);
        mRootView.findViewById(R.id.qq).setOnClickListener(this);
        mRootView.findViewById(R.id.pyq).setOnClickListener(this);
    }

    @Override
    public int getGravity() {
        return Gravity.BOTTOM;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_cancel) {
            onConfirmClick.onCancelClick();
            dismiss();
        } else if (i == R.id.wx) {
            onConfirmClick.onConfirmClick("wx");
        } else if (i == R.id.qq) {
            onConfirmClick.onConfirmClick("qq");
        } else if (i == R.id.pyq) {
            onConfirmClick.onConfirmClick("pyq");
        }

    }
}
