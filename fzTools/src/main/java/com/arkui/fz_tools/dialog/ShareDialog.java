package com.arkui.fz_tools.dialog;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_tools.R;

/**
 * Created by nmliz on 2017/6/26.
 */

public class ShareDialog extends BaseDialogFragment {
    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.dialog_share,container,false);
    }

    @Override
    protected void initView(View mRootView) {

    }

    @Override
    public int getGravity() {
        return Gravity.BOTTOM;
    }
}
