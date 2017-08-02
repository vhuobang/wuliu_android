package com.arkui.transportation_owner.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.transportation_owner.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 基于基类的Fragment
 */
public class PersonalAuthFragment extends BaseFragment {
    private CommonDialog mCommonDialog;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_personal_auth, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);
        mCommonDialog = new CommonDialog();
        mCommonDialog.setTitle("个人认证").setContent("个人认证信息已提交到后台进行审核，请耐心等待！").setNoCancel();
    }

    @OnClick(R.id.bt_submit)
    public void onClick() {
        mCommonDialog.show(getFragmentManager(),"auth");
    }
}
