package com.arkui.transportation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arkui.fz_tools.dialog.ShareDialog;
import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.fz_tools.utils.GlideUtils;
import com.arkui.transportation.R;
import com.arkui.transportation.activity.my.AuthActivity;
import com.arkui.transportation.activity.my.ContactServiceActivity;
import com.arkui.transportation.activity.my.InviteFriendActivity;
import com.arkui.transportation.activity.my.MyBalanceActivity;
import com.arkui.transportation.activity.my.MyInfoFeeActivity;
import com.arkui.transportation.activity.my.MyPointActivity;
import com.arkui.transportation.activity.my.MyProfileActivity;
import com.arkui.transportation.activity.my.SettingActivity;
import com.arkui.transportation.base.App;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 基于基类的Fragment
 */
public class MyFragment extends BaseFragment {

    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ll_auth)
    LinearLayout llAuth;
    @BindView(R.id.ll_balance)
    LinearLayout llBalance;
    @BindView(R.id.ll_point)
    LinearLayout llPoint;
    @BindView(R.id.ll_info_fee)
    LinearLayout llInfoFee;
    @BindView(R.id.ll_share)
    LinearLayout llShare;
    @BindView(R.id.ll_service)
    LinearLayout llService;
    @BindView(R.id.ll_driver_login)
    LinearLayout llDriverLogin;
    Unbinder unbinder;
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
        GlideUtils.getInstance().load(getActivity(), App.getUserEntity().getAvatar(),ivHead );
        tvName.setText(App.getUserEntity().getNickname());
    }

    @OnClick({R.id.ll_balance, R.id.ll_point, R.id.ll_share, R.id.ll_service, R.id.ll_driver_login, R.id.iv_setting, R.id.iv_head, R.id.ll_auth, R.id.ll_info_fee})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_balance:
                showActivity(MyBalanceActivity.class);
                break;
            case R.id.ll_point:
                showActivity(MyPointActivity.class);
                break;
            case R.id.ll_share:
                //mShareDialog.show(getChildFragmentManager(), "share");
                showActivity(InviteFriendActivity.class);
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
            case R.id.ll_info_fee:
                showActivity(MyInfoFeeActivity.class);
                break;
        }
    }


}
