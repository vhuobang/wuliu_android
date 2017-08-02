package com.arkui.fz_tools.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arkui.fz_tools.R;

/**
 * Created by nmliz on 2017/6/23.
 */

public class SuccessFullyDialog extends BaseDialogFragment {

    String mTitle;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        mIsCanceledOnTouch=false;
        return inflater.inflate(R.layout.dialog_success_fully,container,false);
    }

    @Override
    protected void initView(View mRootView) {
       TextView mTvName= (TextView) mRootView.findViewById(R.id.tv_name);
        if(mTitle!=null)
            mTvName.setText(mTitle);
    }

    public SuccessFullyDialog setTitle(String title) {
        mTitle = title;
        return this;
    }

}
