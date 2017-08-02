package com.arkui.transportation_owner.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_tools.dialog.ShareDialog;
import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.activity.my.AuthActivity;
import com.arkui.transportation_owner.activity.my.ContactServiceActivity;
import com.arkui.transportation_owner.activity.my.MyBalanceActivity;
import com.arkui.transportation_owner.activity.my.MyPointActivity;
import com.arkui.transportation_owner.activity.my.MyProfileActivity;
import com.arkui.transportation_owner.activity.my.SettingActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 基于基类的Fragment
 */
public class MyFragment extends BaseFragment {

    private ShareDialog mShareDialog;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);
        mShareDialog = new ShareDialog();
    }

    @OnClick({R.id.ll_balance, R.id.ll_point, R.id.ll_share, R.id.ll_service, R.id.ll_driver_login, R.id.iv_setting, R.id.iv_head, R.id.ll_auth})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_balance:
                showActivity(MyBalanceActivity.class);
                break;
            case R.id.ll_point:
                showActivity(MyPointActivity.class);
                break;
            case R.id.ll_share:
                mShareDialog.show(getChildFragmentManager(), "share");
                break;
            case R.id.ll_service:
                showActivity(ContactServiceActivity.class);
                break;
            case R.id.ll_driver_login:
              /*  showActivity(DriverLoginActivity.class);
                getActivity().finish();*/
                break;
            case R.id.iv_setting:
                showActivity(SettingActivity.class);
                break;
            case R.id.iv_head:
                showActivity(MyProfileActivity.class);
                break;
            case R.id.ll_auth:
                showActivity(AuthActivity.class);
                break;
        }
    }
}
