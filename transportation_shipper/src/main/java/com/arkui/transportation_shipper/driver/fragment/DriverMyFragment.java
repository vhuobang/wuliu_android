package com.arkui.transportation_shipper.driver.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_tools.dialog.ShareDialog;
import com.arkui.transportation_shipper.R;
import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.transportation_shipper.common.activity.DriverLoginActivity;
import com.arkui.transportation_shipper.driver.activity.my.DriverAuthActivity;
import com.arkui.transportation_shipper.owner.activity.my.ContactServiceActivity;
import com.arkui.transportation_shipper.owner.activity.my.MyBalanceActivity;
import com.arkui.transportation_shipper.owner.activity.my.MyProfileActivity;
import com.arkui.transportation_shipper.owner.activity.my.SettingActivity;
import com.arkui.transportation_shipper.owner.activity.my.MyPointActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 基于基类的Fragment
 */
public class DriverMyFragment extends BaseFragment {

    private ShareDialog mShareDialog;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_driver_my, container, false);
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
                showActivity(DriverLoginActivity.class);
                getActivity().finish();
                break;
            case R.id.iv_setting:
                showActivity(SettingActivity.class);
                break;
            case R.id.iv_head:
                showActivity(MyProfileActivity.class);
                break;
            case R.id.ll_auth:
                showActivity(DriverAuthActivity.class);
                break;
        }
    }
}
